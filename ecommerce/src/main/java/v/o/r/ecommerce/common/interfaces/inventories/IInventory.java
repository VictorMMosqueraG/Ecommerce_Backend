package v.o.r.ecommerce.common.interfaces.inventories;

import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.stores.entities.StoresEntity;

public interface IInventory {
    Integer getStocks();

    void setStocks(Integer stocks);

    ProductsEntity getProduct();

    void setProduct(ProductsEntity idProduct);

    ReferenceEntity getReference();

    void setReference(ReferenceEntity idReference);

    StoresEntity getStore();

    void setStore(StoresEntity idStore);

}
