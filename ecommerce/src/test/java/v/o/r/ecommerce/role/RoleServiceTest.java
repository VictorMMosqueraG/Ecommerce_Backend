package v.o.r.ecommerce.role;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.NoSuchElementException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import v.o.r.ecommerce.common.enums.sortOrderEnum;
import v.o.r.ecommerce.permission.PermissionService;
import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.mockData.PermissionMockData;
import v.o.r.ecommerce.role.mockData.RoleMockData;
import v.o.r.ecommerce.roles.RoleService;
import v.o.r.ecommerce.roles.dto.CreateRoleDto;
import v.o.r.ecommerce.roles.dto.PaginationRoleDto;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.roles.repositories.RoleRepository;

public class RoleServiceTest {
    
    @Mock
    private RoleRepository roleRepository;

    @Mock
    private PermissionService permissionService;

    @InjectMocks
    private RoleService roleService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    //NOTE: Save test
    
    @Test
    public void testSaveRoleSuccessfully(){
        //Initialize variables
        CreateRoleDto createRoleDto = RoleMockData.createRoleDto();
        List<PermissionEntity> permissionEntity = 
            PermissionMockData.createPermissionEntity(createRoleDto.permission.get(0));
        RoleEntity roleEntity = RoleMockData.roleEntity(createRoleDto, permissionEntity);


        //Configure method when called
        when(permissionService
            .findByIdOrFail(createRoleDto.permission.get(0)))
                .thenReturn(Optional.of(permissionEntity.get(0)));
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);
        
        //Call method service
        RoleEntity role = roleService.save(createRoleDto);

        
        //Asserts
        assertEquals(createRoleDto.name, role.getName());
        assertEquals(createRoleDto.description, role.getDescription());
        assertEquals(createRoleDto.permission.get(0), role.getPermission().get(0).getId());

        //Verify
        verify(permissionService).findByIdOrFail(createRoleDto.permission.get(0));
        verify(roleRepository).save(any(RoleEntity.class));
    }

    @Test
    public void testSavePermissionNotFound(){
        //Initialize variable
        CreateRoleDto createRoleDto = RoleMockData.createRoleDto();

        //Configure method when called
        when(permissionService.findByIdOrFail(
            createRoleDto.permission.get(0)))
            .thenThrow(new NoSuchElementException(
                "Permission with id " + createRoleDto.permission.get(0) + " not found.")
            );

        //Asserts
        assertThrows(NoSuchElementException.class, ()-> roleService.save(createRoleDto));

        //Verify
        verify(permissionService).findByIdOrFail(createRoleDto.permission.get(0));
    }

    @Test
    public void testSaveRoleWithoutDescription(){
        //Initialize variables
        CreateRoleDto createRoleDto = RoleMockData.createRoleDto();
        createRoleDto.description=null;
        List<PermissionEntity> permissionEntity = 
        PermissionMockData.createPermissionEntity(createRoleDto.permission.get(0));
        RoleEntity roleEntity = RoleMockData.roleEntity(createRoleDto, permissionEntity);

        //Configure method when called
        when(permissionService.findByIdOrFail(
            createRoleDto.permission.get(0)
            )).thenReturn(Optional.of(permissionEntity.get(0)));
        when(roleRepository.save(any(RoleEntity.class))).thenReturn(roleEntity);

        //Call method service
        RoleEntity role = roleService.save(createRoleDto);
        
        //Assert
        assertEquals(createRoleDto.name, role.getName());
        assertEquals(createRoleDto.description, role.getDescription());
        assertEquals(createRoleDto.permission.get(0), role.getPermission().get(0).getId());

        //Verify
        verify(permissionService).findByIdOrFail(createRoleDto.permission.get(0));
        verify(roleRepository).save(any(RoleEntity.class));
    }

    //NOTE Find test
    @Test
    public void testFindRole(){
        //Initialize variable
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        List<RoleEntity> roles = RoleMockData.listRole();

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(roles);

        //Call method service
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("role", response.get("context"));
        assertEquals(2, data.size());

        //Verify
        verify(roleRepository).findAll();
    }

    @Test
    public void testFindRoleWithFlatten(){
        //Initialize variable
        List<RoleEntity> role = RoleMockData.listRoleFlatten();
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        paginationRoleDto.setFlatten(true);

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(role);

        //Call method service 
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("role", response.get("context"));
        assertEquals(1, response.get("total"));
        assertEquals(1L, data.get(0).get("id"));
        assertEquals("User", data.get(0).get("name"));

        //Verify
        verify(roleRepository).findAll();
    }

    @Test
    public void testFindWithFlattenAdditionalField(){
        //Initialize variable
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();

        //Configure method when called
        when(roleRepository.findAll())
            .thenThrow(new IllegalArgumentException(
                "The PaginationRoleDto object cannot have other fields besides 'flatten'."
            )
        );

        //Assert
        assertThrows(IllegalArgumentException.class, ()-> roleService.find(paginationRoleDto));
    }

    @Test
    public void testFindWithLimit(){
        //Initialize variables
        List<RoleEntity> roles = RoleMockData.listRole();
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        paginationRoleDto.setLimit(1);

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(roles);

        //Call method service
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("role", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals(1, data.size());
        assertEquals("Super User", data.get(0).get("name"));

        //Verify
        verify(roleRepository).findAll();
    }   

    @Test
    public void testFindWithOffset(){
        //Initialize variable
        List<RoleEntity> roles = RoleMockData.listRole();
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        paginationRoleDto.setOffset(1);

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(roles);

        //Call method service
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals("role", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals(1, data.size());
        assertEquals("User", data.get(0).get("name"));

        //Verify
        verify(roleRepository).findAll();
    }

    @Test
    public void testFindWithSortOrder(){
        //Initialize variable
        List<RoleEntity> roles = RoleMockData.listRole();
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        paginationRoleDto.setSortOrder(sortOrderEnum.DESC);

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(roles);

        //Call method service
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals(2, data.size());
        assertEquals("role", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals("Super User", data.get(0).get("name"));

        //Verify
        verify(roleRepository).findAll();
    }

    @Test
    public void testFindWithName(){
        List<RoleEntity> roles = RoleMockData.listRole();
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        paginationRoleDto.setName("Super User");

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(roles);

        //Call method service
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals(1, data.size());
        assertEquals("role", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals("Super User", data.get(0).get("name"));

        //Verify
        verify(roleRepository).findAll();   
    }

    @Test
    public void testFindWithPermission(){
        List<RoleEntity> roles = RoleMockData.listRole();
        PaginationRoleDto paginationRoleDto = new PaginationRoleDto();
        paginationRoleDto.setPermission("USER.WRITE.ALL");

        //Configure method when called
        when(roleRepository.findAll()).thenReturn(roles);

        //Call method service
        List<Map<String,Object>> result = roleService.find(paginationRoleDto);

        Map<String, Object> response = result.get(0);
        @SuppressWarnings("unchecked")
        List<Map<String, Object>> data = (List<Map<String, Object>>) response.get("data");

        //Asserts
        assertEquals(2, data.size());
        assertEquals("role", response.get("context"));
        assertEquals(2, response.get("total"));
        assertEquals("Super User", data.get(0).get("name"));

        //Verify
        verify(roleRepository).findAll();   
    }

    //NOTE: Find detail Test
    @Test
    public void testFindDetail(){
        //Initialize variable
        Long id = 1L;
        Optional<RoleEntity> roles = RoleMockData.findDetail();

        //Configure method when called
        when(roleRepository.findById(id)).thenReturn(roles);

        //Call method service
        Optional<RoleEntity> result = roleService.findDetail(id);

        //Asserts
        assertEquals(roles, result);

        //Verify
        verify(roleRepository).findById(id);
    }

    @Test
    public void testFindDetailRoleNotFound(){
        //Initialize variable
        Long id = 1L;

        //Configure method when called
        when(roleRepository.findById(id))
            .thenThrow(new NoSuchElementException("Role with id " + id + " not found." )
        );

        //Asserts
        assertThrows(NoSuchElementException.class, ()-> roleService.findDetail(id));

        //Verify
        verify(roleRepository).findById(id);
    }

}
