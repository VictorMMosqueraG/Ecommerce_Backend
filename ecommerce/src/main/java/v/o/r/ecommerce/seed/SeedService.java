package v.o.r.ecommerce.seed;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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

    @Autowired
    private PasswordEncoder passwordEncoder;

    public void InitializeData(){
        this.dataPermission();
        this.dataRole();
        this.dataUser();
        this.dataStore();
        this.dataPerson();
    }   

    private void dataPermission(){
        //NOTE: user
        PermissionEntity permission1 = new PermissionEntity();
        permission1.setName("User.write.all");
        permission1.setDescription("create and update all user");
        permissionRepository.save(permission1);
    
        PermissionEntity permission2 = new PermissionEntity();
        permission2.setName("User.write");
        permission2.setDescription("create all user");
        permissionRepository.save(permission2);
    
        PermissionEntity permission3 = new PermissionEntity();
        permission3.setName("User.read.all");
        permission3.setDescription("read all user and read specif user");
        permissionRepository.save(permission3);
    
        PermissionEntity permission4 = new PermissionEntity();
        permission4.setName("User.read");
        permission4.setDescription("read specif user");
        permissionRepository.save(permission4);
    
        //NOTE: stores
        PermissionEntity permission5 = new PermissionEntity();
        permission5.setName("Store.write.all");
        permission5.setDescription("create and update all store");
        permissionRepository.save(permission5);
        
        PermissionEntity permission6 = new PermissionEntity();
        permission6.setName("Store.write");
        permission6.setDescription("create all store");
        permissionRepository.save(permission6);
    
        PermissionEntity permission7 = new PermissionEntity();
        permission7.setName("Store.read.all");
        permission7.setDescription("read all store and read specif store");
        permissionRepository.save(permission7);
    
        PermissionEntity permission8 = new PermissionEntity();
        permission8.setName("Store.read");
        permission8.setDescription("read specif store");
        permissionRepository.save(permission8);
    
        //NOTE: roles
        PermissionEntity permission9 = new PermissionEntity();
        permission9.setName("Role.create.all");
        permission9.setDescription("create and update all role");
        permissionRepository.save(permission9);
    
        PermissionEntity permission10 = new PermissionEntity();
        permission10.setName("Role.create");
        permission10.setDescription("create all role");
        permissionRepository.save(permission10);
    
        PermissionEntity permission11 = new PermissionEntity();
        permission11.setName("Role.read.all");
        permission11.setDescription("read all role and read specif role");
        permissionRepository.save(permission11);
    
        PermissionEntity permission12 = new PermissionEntity();
        permission12.setName("Role.read");
        permission12.setDescription("read specif role");
        permissionRepository.save(permission12);
        
        //NOTE: reference
        PermissionEntity permission13 = new PermissionEntity();
        permission13.setName("Reference.create.all");
        permission13.setDescription("create and update all reference");
        permissionRepository.save(permission13);
        
        PermissionEntity permission14 = new PermissionEntity();
        permission14.setName("Reference.create");
        permission14.setDescription("create all reference");
        permissionRepository.save(permission14);
    
        PermissionEntity permission15 = new PermissionEntity();
        permission15.setName("Reference.read.all");
        permission15.setDescription("read all reference and read specif reference");
        permissionRepository.save(permission15);
    
        PermissionEntity permission16 = new PermissionEntity();
        permission16.setName("Reference.read");
        permission16.setDescription("read specif reference");
        permissionRepository.save(permission16);
    
        //NOTE: products
        PermissionEntity permission17 = new PermissionEntity();
        permission17.setName("Products.create.all");
        permission17.setDescription("create and update all products");
        permissionRepository.save(permission17);
        
        PermissionEntity permission18 = new PermissionEntity();
        permission18.setName("Products.create");
        permission18.setDescription("create all products");
        permissionRepository.save(permission18);
    
        PermissionEntity permission19 = new PermissionEntity();
        permission19.setName("Products.read.all");
        permission19.setDescription("read all products and read specif products");
        permissionRepository.save(permission19);
    
        PermissionEntity permission20 = new PermissionEntity();
        permission20.setName("Products.read");
        permission20.setDescription("read specif products");
        permissionRepository.save(permission20);
    
        //NOTE: person
        PermissionEntity permission21 = new PermissionEntity();
        permission21.setName("Person.create.all");
        permission21.setDescription("create and update all person");
        permissionRepository.save(permission21);
        
        PermissionEntity permission22 = new PermissionEntity();
        permission22.setName("Person.create");
        permission22.setDescription("create all person");
        permissionRepository.save(permission22);
    
        PermissionEntity permission23 = new PermissionEntity();
        permission23.setName("Person.read.all");
        permission23.setDescription("read all person and read specif person");
        permissionRepository.save(permission23);
    
        PermissionEntity permission24 = new PermissionEntity();
        permission24.setName("Person.read");
        permission24.setDescription("read specif person");
        permissionRepository.save(permission24);
    
        //NOTE: permission
        PermissionEntity permission25 = new PermissionEntity();
        permission25.setName("Permission.create.all");
        permission25.setDescription("create and update all permission");
        permissionRepository.save(permission25);
        
        PermissionEntity permission26 = new PermissionEntity();
        permission26.setName("Permission.create");
        permission26.setDescription("create all permission");
        permissionRepository.save(permission26);
    
        PermissionEntity permission27 = new PermissionEntity();
        permission27.setName("Permission.read.all");
        permission27.setDescription("read all permission and read specif permission");
        permissionRepository.save(permission27);
    
        PermissionEntity permission28 = new PermissionEntity();
        permission28.setName("Permission.read");
        permission28.setDescription("read specif permission");
        permissionRepository.save(permission28);
    
        //NOTE: method_of_pay
        PermissionEntity permission29 = new PermissionEntity();
        permission29.setName("MethodOfPay.create.all");
        permission29.setDescription("create and update all method_of_pay");
        permissionRepository.save(permission29);
        
        PermissionEntity permission30 = new PermissionEntity();
        permission30.setName("MethodOfPay.create");
        permission30.setDescription("create all method_of_pay");
        permissionRepository.save(permission30);
    
        PermissionEntity permission31 = new PermissionEntity();
        permission31.setName("MethodOfPay.read.all");
        permission31.setDescription("read all method_of_pay and read specif method_of_pay");
        permissionRepository.save(permission31);
    
        PermissionEntity permission32 = new PermissionEntity();
        permission32.setName("MethodOfPay.read");
        permission32.setDescription("read specif method_of_pay");
        permissionRepository.save(permission32);
    
        //NOTE: inventory
        PermissionEntity permission33= new PermissionEntity();
        permission33.setName("Inventory.create.all");
        permission33.setDescription("create and update all inventory");
        permissionRepository.save(permission33);
        
        PermissionEntity permission34 = new PermissionEntity();
        permission34.setName("Inventory.create");
        permission34.setDescription("create all inventory");
        permissionRepository.save(permission34);
    
        PermissionEntity permission35 = new PermissionEntity();
        permission35.setName("Inventory.read.all");
        permission35.setDescription("read all inventory and read specif inventory");
        permissionRepository.save(permission35);
    
        PermissionEntity permission36 = new PermissionEntity();
        permission36.setName("Inventory.read");
        permission36.setDescription("read specif inventory");
        permissionRepository.save(permission36);
    
        //NOTE: categories
        PermissionEntity permission37= new PermissionEntity();
        permission37.setName("Category.create.all");
        permission37.setDescription("create and update all category");
        permissionRepository.save(permission37);
        
        PermissionEntity permission38 = new PermissionEntity();
        permission38.setName("Category.create");
        permission38.setDescription("create all category");
        permissionRepository.save(permission38);
    
        PermissionEntity permission39 = new PermissionEntity();
        permission39.setName("Category.read.all");
        permission39.setDescription("read all category and read specif category");
        permissionRepository.save(permission39);
    
        PermissionEntity permission40 = new PermissionEntity();
        permission40.setName("Category.read");
        permission40.setDescription("read specif category");
        permissionRepository.save(permission40);
    }
    

    private void dataRole(){
        //NOTE: super user
        List<Long> permissionIds1 = Arrays.asList(
       1L,
            3L,
            5L,
            7L,
            9L,
            11L,
            13L,
            14L,
            17L,
            19L,
            21L,
            23L,
            25L,
            27L,
            29L,
            31L,
            33L,
            35L,
            37L,
            39L
        );
        List<PermissionEntity> permissions1 = permissionRepository.findAllById(permissionIds1);
        RoleEntity roleEntity1 = new RoleEntity();
        roleEntity1.setName("Super user");
        roleEntity1.setDescription("super user with all privileges");
        roleEntity1.setPermission(permissions1);
        roleRepository.save(roleEntity1);

        //NOTE admin
        List<Long> permissionIds2 = Arrays.asList(2L,4L, 6L,8L, 14L,16L,18L,20L,22L,24L,34L,36L,38L,40L);
        List<PermissionEntity> permissions2 = permissionRepository.findAllById(permissionIds2);
    
        RoleEntity roleEntity2 = new RoleEntity();
        roleEntity2.setName("admin");
        roleEntity2.setDescription("admin");
        roleEntity2.setPermission(permissions2);
        roleRepository.save(roleEntity2);

        //NOTE user
        List<Long> permissionIds3 = Arrays.asList(4L, 16L,20L,24L,32L,40L);
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
     
        userEntity1.setPassword(passwordEncoder.encode("mySecretPassword"));
        userEntity1.setRole(role1.get(0));

        userRepository.save(userEntity1);

        List<Long> roleId2 = Arrays.asList(2L);
        List<RoleEntity> role2 = roleRepository.findAllById(roleId2);

        UserEntity userEntity2 = new UserEntity();
        userEntity2.setEmail("victormmg2004@gmail.com");
        userEntity2.setPassword(passwordEncoder.encode("mySecretPassword"));
        userEntity2.setRole(role2.get(0));

        userRepository.save(userEntity2);

        List<Long> roleId3 = Arrays.asList(3L);
        List<RoleEntity> role3 = roleRepository.findAllById(roleId3);

        UserEntity userEntity3 = new UserEntity();
        userEntity3.setEmail("david.jhoe@gmail.com");
        userEntity3.setPassword(passwordEncoder.encode("mySecretPassword"));
        userEntity3.setRole(role3.get(0));

        userRepository.save(userEntity3);

        List<Long> roleId4= Arrays.asList(1L);
        List<RoleEntity> role4 = roleRepository.findAllById(roleId4);

        UserEntity userEntity4 = new UserEntity();
        userEntity4.setEmail("jhoe.doe@gmail.com");
        userEntity4.setPassword(passwordEncoder.encode("mySecretPassword"));
        userEntity4.setRole(role4.get(0));

        userRepository.save(userEntity4);

        List<Long> roleId5= Arrays.asList(2L);
        List<RoleEntity> role5 = roleRepository.findAllById(roleId5);

        UserEntity userEntity5 = new UserEntity();
        userEntity5.setEmail("david@gmail.com");
        userEntity5.setPassword(passwordEncoder.encode("mySecretPassword"));
        userEntity5.setRole(role5.get(0));

        userRepository.save(userEntity5);

        List<Long> roleId6= Arrays.asList(3L);
        List<RoleEntity> role6 = roleRepository.findAllById(roleId6);

        UserEntity userEntity6 = new UserEntity();
        userEntity6.setEmail("jhoan@gmail.com");
        userEntity6.setPassword(passwordEncoder.encode("mySecretPassword"));
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
