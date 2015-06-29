package nl.kristalsoftware.beanreflection.data;

/**
 * Created by sjoerdadema on 15-06-15.
 */
public class ProductData {

    private Integer productid;

    private String description;

    public ProductData(Integer productid, String description) {
        this.productid = productid;
        this.description = description;
    }

    public Integer getProductid() {
        return productid;
    }

    public void setProductid(Integer productid) {
        this.productid = productid;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
