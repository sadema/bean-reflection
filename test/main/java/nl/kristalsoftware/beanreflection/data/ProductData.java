package nl.kristalsoftware.beanreflection.data;

/**
 * Created by sjoerdadema on 15-06-15.
 */
public class ProductData {

    private String productid;

    private String description;

    public ProductData(String productid, String description) {
        this.productid = productid;
        this.description = description;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
