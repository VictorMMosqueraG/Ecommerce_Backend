package v.o.r.ecommerce.common.interfaces.stores;

public interface IStores {
    Long getId();

    void setId(Long id); 

    String getName();

    void setName(String name);

    String getAddress();

    void setAddress(String address);

    String getCity();

    void setCity(String city);

    String getDepartment();

    void setDepartment(String department);
}
