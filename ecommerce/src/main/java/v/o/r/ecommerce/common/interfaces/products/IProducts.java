package v.o.r.ecommerce.common.interfaces.products;

import java.util.List;



public interface IProducts {

    Long getId();
    void setId(Long id);
    String getName();
    void setName(String name);
    String getDescription();
    void setDescription(String description);
    List<Long> getCategory();
    void setCategory(List<Long> category);
    String getPrice();
    void setPrice(String price);

} 