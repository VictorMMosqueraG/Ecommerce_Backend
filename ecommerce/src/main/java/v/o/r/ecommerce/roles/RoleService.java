package v.o.r.ecommerce.roles;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.enums.sortOrderEnum;
import v.o.r.ecommerce.common.interfaces.roles.IRoleService;
import v.o.r.ecommerce.permission.PermissionService;
import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.dto.PaginationRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.roles.repositories.RoleRepository;

@Service
public class RoleService implements IRoleService {
    
    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PermissionService permissionService;

    public void save(CreateRoleDto createRoleDto){
        RoleEntity role = new RoleEntity();

        role.setName(createRoleDto.name);
        role.setDescription(createRoleDto.description);

       //NOTE: valid if the permission send if exist
        List<PermissionEntity> findPermissions = new ArrayList<>();
        createRoleDto.permission.stream()
            .map(permission -> permissionService.findByIdOrFail(permission))
            .distinct()//ignore item duplicate
            .forEach(permission -> permission.ifPresent(findPermissions::add));
   
        role.setPermission(findPermissions);

        roleRepository.save(role);
    }

    public Optional<RoleEntity> findRoleById(Long id){
        return roleRepository.findById(id);
    }

    public Optional<RoleEntity> findRoleByIdOrFail(Long id){
        Optional<RoleEntity> foundRole = id!=null ? findRoleById(id):null;

        if(foundRole==null || foundRole.isEmpty()){
            throw new NoSuchElementException("Role with id " + id + " not found.");
        }

        return foundRole;
    }

    public List<Map<String,Object>> find(PaginationRoleDto paginationRoleDto){

        //find all data 
        List<RoleEntity> foundRole = roleRepository.findAll();

        //destructuring paginationRoleDto
        boolean flatten = paginationRoleDto != null && paginationRoleDto.getFlatten();
        int limit = paginationRoleDto!=null && paginationRoleDto.getLimit() != 0 ? paginationRoleDto.getLimit() : 50;
        int offset = paginationRoleDto!=null && paginationRoleDto.getOffset() != 0 ? paginationRoleDto.getOffset() : 0;
        sortOrderEnum sortOrder = paginationRoleDto!=null ? paginationRoleDto.getSortOrder() : null;
        String name = paginationRoleDto!=null ? paginationRoleDto.getName() : null;
        String permission = paginationRoleDto!=null ? paginationRoleDto.getPermission():null;


        //validate if provided flatten
        if(flatten==true){
            if (flatten ==true && ( limit !=50  || offset != 0 || name != null || permission != null || sortOrder!=null)) {
                throw new IllegalArgumentException("The PaginationRoleDto object cannot have other fields besides 'flatten'.");
            }

            Map<String,Object> response = new LinkedHashMap<>();
            response.put("context", "role");
            response.put("total", foundRole.size());
            response.put("data", foundRole.stream().map(role ->{
                    Map<String,Object> roleMap = new LinkedHashMap<>();

                    roleMap.put("id", role.getId());
                    roleMap.put("name", role.getName());
                    return roleMap;
                })
                .collect(Collectors.toList()));
            return Collections.singletonList(response);
        }

        //logic for sortOrder DESC, this is default ASC
        Comparator<Map<String, Object>> sort = Comparator.comparing(m -> (String) m.get("name"));

        if (sortOrder != null && sortOrder==sortOrderEnum.ASC) {
            sort = sort.reversed();
        }

        //filter based on name or permission if provided
        Predicate<RoleEntity> nameFilter = role -> name == null || role.getName().toLowerCase().contains(name.toLowerCase());

    
        Map<String,Object> response = new LinkedHashMap<>();
        response.put("context", "role");
        response.put("total", foundRole.size());
        response.put("data", foundRole.stream()
            .filter(nameFilter)
            .map(role->{
                Map<String,Object> roleMap = new LinkedHashMap<>();
                roleMap.put("id", role.getId());
                roleMap.put("name", role.getName());
                roleMap.put("description", role.getDescription());
                roleMap.put("permission", role.getPermission());

                return roleMap;
            })
            .sorted(sort)
            .skip(offset)
            .limit(limit)
            .collect(Collectors.toList())
        );

        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(response);
        return resultList;        
    }

}
