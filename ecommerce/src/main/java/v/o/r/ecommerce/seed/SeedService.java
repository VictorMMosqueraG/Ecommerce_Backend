package v.o.r.ecommerce.seed;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.repositories.PermissionRepository;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.roles.repositories.RoleRepository;
import v.o.r.ecommerce.users.entities.UserEntity;
import v.o.r.ecommerce.users.repositories.UserRepository;

@Service
public class SeedService {
    @Autowired
    private PermissionRepository permissionRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;


    public void InitializeData(){
        this.dataPermission();
        this.dataRole();
        this.dataUser();
    }   

    private void dataPermission(){
        PermissionEntity permission1 = new PermissionEntity();
        permission1.setName("User.write.all");
        permission1.setDescription("create,update and read all user");

        permissionRepository.save(permission1);

        PermissionEntity permission2 = new PermissionEntity();
        permission2.setName("User.write");
        permission2.setDescription("create and read all user");

        permissionRepository.save(permission2);

        PermissionEntity permission3 = new PermissionEntity();
        permission3.setName("Product.write.all");
        permission3.setDescription("create and read all Product");

        permissionRepository.save(permission3);

        PermissionEntity permission4 = new PermissionEntity();
        permission4.setName("Product.write");
        permission4.setDescription("create and read all Product");

        permissionRepository.save(permission4);

        PermissionEntity permission5 = new PermissionEntity();
        permission5.setName("Reference.write.all");
        permission5.setDescription("create and read all Reference");

        permissionRepository.save(permission5);
        
        PermissionEntity permission6 = new PermissionEntity();
        permission6.setName("Reference.write");
        permission6.setDescription("create and read all Reference");

        permissionRepository.save(permission6);
    }

    private void dataRole(){
        List<Long> permissionIds1 = Arrays.asList(1L, 2L,3L, 4L,5L,6L);
        List<PermissionEntity> permissions1 = permissionRepository.findAllById(permissionIds1);
        
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setName("Super user");
        roleEntity1.setDescription("super user");
        roleEntity1.setPermission(permissions1);

        roleRepository.save(roleEntity1);

        List<Long> permissionIds2 = Arrays.asList(1L, 2L,3L, 4L);
        List<PermissionEntity> permissions2 = permissionRepository.findAllById(permissionIds2);
        
        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setName("admin");
        roleEntity2.setDescription("admin");
        roleEntity2.setPermission(permissions2);

        roleRepository.save(roleEntity2);

        List<Long> permissionIds3 = Arrays.asList(1L, 2L);
        List<PermissionEntity> permissions3 = permissionRepository.findAllById(permissionIds3);
        
        RoleEntity roleEntity3 = new RoleEntity();
        roleEntity3.setName("user");
        roleEntity3.setDescription("user");
        roleEntity3.setPermission(permissions3);

        roleRepository.save(roleEntity3);
    }

    private void dataUser(){
        List<Long> roleId1 = Arrays.asList(1L);
        List<RoleEntity> role1 = roleRepository.findAllById(roleId1);

        UserEntity userEntity1 = new UserEntity();
        userEntity1.setEmail("Victormmosquerag@gmail.com");
        userEntity1.setPassword("9dcaec017e2f62d0b679bf60b13c8b2d");
        userEntity1.setRole(role1.get(0));

        userRepository.save(userEntity1);

        List<Long> roleId2 = Arrays.asList(2L);
        List<RoleEntity> role2 = roleRepository.findAllById(roleId2);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("victormmg2004@gmail.com");
        userEntity2.setPassword("5a9ab1689f8c3e55684f7a7b5247e1e8");
        userEntity2.setRole(role2.get(0));

        userRepository.save(userEntity2);

        List<Long> roleId3 = Arrays.asList(3L);
        List<RoleEntity> role3 = roleRepository.findAllById(roleId3);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("david.jhoe@gmail.com");
        userEntity3.setPassword("c5f47d2ae1ee805b7f8bcb35f6f6dcb6");
        userEntity3.setRole(role3.get(0));

        userRepository.save(userEntity3);

        List<Long> roleId4= Arrays.asList(1L);
        List<RoleEntity> role4 = roleRepository.findAllById(roleId4);

        UserEntity userEntity4 = new UserEntity();
        userEntity4.setEmail("jhoe.doe@gmail.com");
        userEntity4.setPassword("bf5b8b2d59a7f182ef5a38e4d277f1f2");
        userEntity4.setRole(role4.get(0));

        userRepository.save(userEntity4);

        List<Long> roleId5= Arrays.asList(2L);
        List<RoleEntity> role5 = roleRepository.findAllById(roleId5);

        UserEntity userEntity5 = new UserEntity();
        userEntity5.setEmail("david@gmail.com");
        userEntity5.setPassword("73f77c34e54ec4400bfe0f779e7a5364");
        userEntity5.setRole(role5.get(0));

        userRepository.save(userEntity5);

        List<Long> roleId6= Arrays.asList(3L);
        List<RoleEntity> role6 = roleRepository.findAllById(roleId6);

        UserEntity userEntity6 = new UserEntity();
        userEntity6.setEmail("jhoan@gmail.com");
        userEntity6.setPassword("3d1cde6c6062a3e38ff52d11dbf5172e");
        userEntity6.setRole(role6.get(0));

        userRepository.save(userEntity6);
    }
}
