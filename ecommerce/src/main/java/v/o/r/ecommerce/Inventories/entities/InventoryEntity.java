package v.o.r.ecommerce.Inventories.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import v.o.r.ecommerce.common.interfaces.inventories.IInventory;
import v.o.r.ecommerce.products.entities.ProductsEntity;
import v.o.r.ecommerce.references.entities.ReferenceEntity;
import v.o.r.ecommerce.stores.entities.StoresEntity;

@Entity
@Table(name = "inventories",uniqueConstraints = {
    @UniqueConstraint(columnNames = {"product","reference","store"})
})
public class InventoryEntity implements IInventory{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Min(value = 1, message = "stocks must be greater than or equal to 1")
    @NotNull(message = "stocks cannot be null")
    private Integer stocks;

    //COMEBACK fix the unique constrains
    @ManyToOne
    @JoinColumn(name = "products",referencedColumnName = "id")
    private ProductsEntity product;

    @ManyToOne
    @JoinColumn(name = "reference",referencedColumnName = "id")
    private ReferenceEntity reference;

    @ManyToOne
    @JoinColumn(name = "stores",referencedColumnName = "id")
    private StoresEntity store;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getStocks() {
        return stocks;
    }

    public void setStocks(Integer stocks) {
        this.stocks = stocks;
    }

    public ProductsEntity getProduct() {
        return product;
    }

    public void setProduct(ProductsEntity idProduct) {
        this.product = idProduct;
    }

    public ReferenceEntity getReference() {
        return reference;
    }

    public void setReference(ReferenceEntity idReference) {
        this.reference = idReference;
    }

    public StoresEntity getStore() {
        return store;
    }

    public void setStore(StoresEntity idStore) {
        this.store = idStore;
    }


} 