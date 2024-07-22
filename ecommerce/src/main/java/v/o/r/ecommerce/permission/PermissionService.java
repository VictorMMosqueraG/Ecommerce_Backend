package v.o.r.ecommerce.permission;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import java.util.function.Predicate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.common.interfaces.permission.IPermissionService;
import v.o.r.ecommerce.permission.dto.CreatePermission;
import v.o.r.ecommerce.permission.dto.PaginationPermissionDto;
import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.repositories.PermissionRepository;

@Service
public class PermissionService implements IPermissionService{
    
    @Autowired
    private PermissionRepository permissionRepository;

    public PermissionEntity save(CreatePermission createPermission){
        PermissionEntity permission = new PermissionEntity();
        
        permission.setName(createPermission.name);
        permission.setDescription(createPermission.description);

        permissionRepository.save(permission);
        return permission;
    }
    
    public Optional<PermissionEntity> findDetail(Long id){
        return this.findByIdOrFail(id);
    }

    public List<Map<String,Object>> find(PaginationPermissionDto paginationPermissionDto){
        //find all 
        List<PermissionEntity> foundPermission = this.permissionRepository.findAll();

        //destructuring pagination
        boolean flatten = paginationPermissionDto!=null 
            && paginationPermissionDto.isFlatten();

        int limit = paginationPermissionDto!=null && paginationPermissionDto.getLimit()!=0 
            ? paginationPermissionDto.getLimit():50;

        int offset = paginationPermissionDto!=null && paginationPermissionDto.getOffset()!=0 
            ? paginationPermissionDto.getOffset():0;

        String sortOrder = paginationPermissionDto!=null 
            ? paginationPermissionDto.getSortOrder():null;

        String name = paginationPermissionDto!=null 
            ? paginationPermissionDto.getName():null;

        //value if provide flatten and provide other value for throw exception
        if(flatten==true){
            if (flatten ==true && ( limit !=50  || offset != 0 || name != null || sortOrder!=null)) {
                throw new IllegalArgumentException("The PaginationPermissionDto object cannot have other fields besides 'flatten'.");
            }

            Map<String,Object> response = new LinkedHashMap<>();
            response.put("context","permission");
            response.put("total",foundPermission.size());
            response.put("data", foundPermission.stream()
                .map(permission->{
                    Map<String,Object> mapPermission = new LinkedHashMap<>();
                    mapPermission.put("id", permission.getId());
                    mapPermission.put("name", permission.getName());

                    return mapPermission;
            })
                .collect(Collectors.toList()));
            return Collections.singletonList(response);
        }

        //logic for sortOrder DESC, this is default ASC
        Comparator<Map<String, Object>> sort = 
            Comparator.comparing(m -> (String) m.get("name"));

        if (sortOrder != null && sortOrder.equalsIgnoreCase("desc")) {
            sort = sort.reversed();
        }

        //filter by name
        Predicate<PermissionEntity> nameFilter = permission -> name == null 
            || permission.getName().toLowerCase().contains(name.toLowerCase());

        Map<String,Object> response = new LinkedHashMap<>();
        response.put("context", "permission");
        response.put("total", foundPermission.size());
        response.put("data", foundPermission.stream()
            .filter(nameFilter)
            .map(permission ->{
                Map<String,Object> permissionMap = new LinkedHashMap<>();
                permissionMap.put("id",permission.getId());
                permissionMap.put("name", permission.getName());
                permissionMap.put("description", permission.getDescription());
                
                return permissionMap;
            })
            .sorted(sort)
            .skip(offset)
            .limit(limit)
            .collect(Collectors.toList()));  
            
        List<Map<String, Object>> resultList = new ArrayList<>();
        resultList.add(response);
        return resultList;    
    }


    //NOTE: methods bases
    public Optional<PermissionEntity> findById(Long id){
        Optional<PermissionEntity> foundPermission = permissionRepository.findById(id);
        return foundPermission;
    }

    public Optional<PermissionEntity> findByIdOrFail(Long id){
        Optional<PermissionEntity> foundPermission=id!=null 
            ? this.findById(id):null;
        if (foundPermission==null || foundPermission.isEmpty()) {
            throw new NoSuchElementException("the Permission with id "+id+ " is not found.");
        }
       
        return foundPermission;
    }
    
}
