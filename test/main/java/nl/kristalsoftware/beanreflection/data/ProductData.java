package nl.kristalsoftware.beanreflection.data;

/**
 * Created by sjoerdadema on 15-06-15.
 */
public class ProductData {

    private String productid;

    private String productname = "";

    public ProductData(String productid) {
        this.productid = productid;
    }

    public String getProductid() {
        return productid;
    }

    public void setProductid(String productid) {
        this.productid = productid;
    }

    public String getProductname() {
        return productname;
    }

    public void setProductname(String productname) {
        this.productname = productname;
    }

}
