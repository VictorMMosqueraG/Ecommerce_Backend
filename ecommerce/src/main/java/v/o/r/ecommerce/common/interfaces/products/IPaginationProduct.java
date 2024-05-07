package v.o.r.ecommerce.common.interfaces.products;

public interface IPaginationProduct {
    public boolean isFlatten();

    public void setFlatten(boolean flatten);

    public int getLimit();

    public void setLimit(int limit);

    public int getOffset();

    public void setOffset(int offset);

    public String getSortOrder();

    public void setSortOrder(String sortOrder);

    public String getName();

    public void setName(String name);

    public String getPrice();

    public void setPrice(String price);

    public String getNameCategories();

    public void setNameCategories(String nameCategories);
}
