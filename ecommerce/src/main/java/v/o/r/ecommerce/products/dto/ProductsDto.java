package v.o.r.ecommerce.products.dto;

import java.util.List;

import v.o.r.ecommerce.common.utils.EnumUtils;

public class ProductsDto {
    public String name;
    public String description;
    public String price;
    public List<Long> categories;
    public EnumUtils money;
    
}
