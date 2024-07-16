package v.o.r.ecommerce.seed;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import v.o.r.ecommerce.permission.entities.PermissionEntity;
import v.o.r.ecommerce.permission.repositories.PermissionRepository;
import v.o.r.ecommerce.persons.entities.PersonEntity;
import v.o.r.ecommerce.persons.repositories.PersonRepository;
import v.o.r.ecommerce.roles.entities.RoleEntity;
import v.o.r.ecommerce.roles.repositories.RoleRepository;
import v.o.r.ecommerce.stores.entities.StoresEntity;
import v.o.r.ecommerce.stores.repositories.StoreRepository;
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

    @Autowired
    private StoreRepository storeRepository;

    @Autowired 
    private PersonRepository personRepository;

    public void InitializeData(){
        this.dataPermission();
        this.dataRole();
        this.dataUser();
        this.dataStore();
        this.dataPerson();
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

    private void dataStore() {
        StoresEntity storeEntity1 = new StoresEntity();
        storeEntity1.setName("storeSmall");
        storeEntity1.setCity("Cali");
        storeEntity1.setAddress("Street 01 #20");
        storeEntity1.setDepartment("Cauca");
        storeRepository.save(storeEntity1);
    
        StoresEntity storeEntity2 = new StoresEntity();
        storeEntity2.setName("storeMedium");
        storeEntity2.setCity("Medellin");
        storeEntity2.setAddress("Street 45 #18");
        storeEntity2.setDepartment("Antioquia");
        storeRepository.save(storeEntity2);
    
        StoresEntity storeEntity3 = new StoresEntity();
        storeEntity3.setName("storeLarge");
        storeEntity3.setCity("Bogota");
        storeEntity3.setAddress("Street 100 #22");
        storeEntity3.setDepartment("Cundinamarca");
        storeRepository.save(storeEntity3);
    
        StoresEntity storeEntity4 = new StoresEntity();
        storeEntity4.setName("storeExtraLarge");
        storeEntity4.setCity("Barranquilla");
        storeEntity4.setAddress("Street 32 #14");
        storeEntity4.setDepartment("Atlantico");
        storeRepository.save(storeEntity4);
    
        StoresEntity storeEntity5 = new StoresEntity();
        storeEntity5.setName("storePremium");
        storeEntity5.setCity("Cartagena");
        storeEntity5.setAddress("Street del Mar #5");
        storeEntity5.setDepartment("Bolivar");
        storeRepository.save(storeEntity5);
    
        StoresEntity storeEntity6 = new StoresEntity();
        storeEntity6.setName("storeBasic");
        storeEntity6.setCity("Santa Marta");
        storeEntity6.setAddress("Street 50 #10");
        storeEntity6.setDepartment("Magdalena");
        storeRepository.save(storeEntity6);
    
        StoresEntity storeEntity7 = new StoresEntity();
        storeEntity7.setName("storeExclusive");
        storeEntity7.setCity("Pereira");
        storeEntity7.setAddress("Street Sur #25");
        storeEntity7.setDepartment("Risaralda");
        storeRepository.save(storeEntity7);
    }
    
    private void dataPerson(){
        List<Long> userId1 = Arrays.asList(1L);
        List<UserEntity> userEntity1 = userRepository.findAllById(userId1);
        PersonEntity personEntity1 = new PersonEntity();

        personEntity1.setFirstName("victor");
        personEntity1.setLastName("mosquera");
        personEntity1.setMethodOfPay("cash");
        personEntity1.setPhoneNumber("3207775152");
        personEntity1.setAddress("cr 20 #20-40");
        personEntity1.setCity("cali");
        personEntity1.setDepartment("cauca");
        personEntity1.setUser(userEntity1.get(0));
        personRepository.save(personEntity1);

        List<Long> userId2 = Arrays.asList(2L);
        List<UserEntity> userEntity2 = userRepository.findAllById(userId2);
        PersonEntity personEntity2 = new PersonEntity();
        personEntity2.setFirstName("ana");
        personEntity2.setLastName("garcia");
        personEntity2.setMethodOfPay("credit card");
        personEntity2.setPhoneNumber("3101234567");
        personEntity2.setAddress("av 15 #10-30");
        personEntity2.setCity("bogota");
        personEntity2.setDepartment("bogota");
        personEntity2.setUser(userEntity2.get(0));
        personRepository.save(personEntity2);
    
        List<Long> userId3 = Arrays.asList(3L);
        List<UserEntity> userEntity3 = userRepository.findAllById(userId3);
        PersonEntity personEntity3 = new PersonEntity();
        personEntity3.setFirstName("luis");
        personEntity3.setLastName("fernandez");
        personEntity3.setMethodOfPay("debit card");
        personEntity3.setPhoneNumber("3123456789");
        personEntity3.setAddress("cl 50 #60-70");
        personEntity3.setCity("medellin");
        personEntity3.setDepartment("antioquia");
        personEntity3.setUser(userEntity3.get(0));
        personRepository.save(personEntity3);

        List<Long> userId4 = Arrays.asList(4L);
        List<UserEntity> userEntity4 = userRepository.findAllById(userId4);
        PersonEntity personEntity4 = new PersonEntity();
        personEntity4.setFirstName("maria");
        personEntity4.setLastName("lopez");
        personEntity4.setMethodOfPay("paypal");
        personEntity4.setPhoneNumber("3159876543");
        personEntity4.setAddress("cl 30 #40-50");
        personEntity4.setCity("barranquilla");
        personEntity4.setDepartment("atlantico");
        personEntity4.setUser(userEntity4.get(0));
        personRepository.save(personEntity4);

        List<Long> userId5 = Arrays.asList(5L);
        List<UserEntity> userEntity5 = userRepository.findAllById(userId5);
        PersonEntity personEntity5 = new PersonEntity();
        personEntity5.setFirstName("carlos");
        personEntity5.setLastName("martinez");
        personEntity5.setMethodOfPay("bank transfer");
        personEntity5.setPhoneNumber("3176543210");
        personEntity5.setAddress("av 5 #6-7");
        personEntity5.setCity("cartagena");
        personEntity5.setDepartment("bolivar");
        personEntity5.setUser(userEntity5.get(0));
        personRepository.save(personEntity5);

        List<Long> userId6 = Arrays.asList(6L);
        List<UserEntity> userEntity6 = userRepository.findAllById(userId6);
        PersonEntity personEntity6 = new PersonEntity();
        personEntity6.setFirstName("andrea");
        personEntity6.setLastName("rodriguez");
        personEntity6.setMethodOfPay("cash");
        personEntity6.setPhoneNumber("3181239876");
        personEntity6.setAddress("cl 25 #30-40");
        personEntity6.setCity("cucuta");
        personEntity6.setDepartment("santander");
        personEntity6.setUser(userEntity6.get(0));
        personRepository.save(personEntity6);
    }
}
