package nl.kristalsoftware.beanreflection.main;

import nl.kristalsoftware.beanreflection.data.ProductData;
import org.junit.Before;
import org.junit.Test;

import java.lang.reflect.Field;

import static org.junit.Assert.*;

/**
 * Created by sjoerdadema on 15-06-15.
 */
public class BeanReflectionUtilsImplTest {

    @Test
    public void testGetField() throws Exception {
        ProductData productData = new ProductData("123");
        BeanReflectionUtils utils = new BeanReflectionUtilsImpl();
        Field f = utils.getField(productData, "productid");
        assertNotNull("field is null", f);
    }
}