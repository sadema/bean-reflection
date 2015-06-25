package nl.kristalsoftware.beanreflection.main;

import nl.kristalsoftware.beanreflection.data.ProductData;
import org.junit.After;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.List;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThat;

/**
 * Created by sjoerdadema on 15-06-15.
 */
@RunWith(WeldJunit4Runner.class)
public class BeanReflectionUtilsImplTest {

    private ProductData productData;

    @Inject
    private BeanReflectionUtils utils;

    @Inject
    private InjectTest injectTest;

    public BeanReflectionUtilsImplTest() {}

    @Before
    public void createBeforeEachTest() {
        injectTest.showMessage();
        productData = new ProductData("123");
        //utils = new BeanReflectionUtilsImpl();
    }

    @After
    public void destroyAfterEachTest() {
        productData = null;
        //utils = null;
    }

    @Test
    public void testGetProductidField() throws Exception {
        injectTest.showMessage();
        //ProductData productData = new ProductData("123");
        //BeanReflectionUtils utils = new BeanReflectionUtilsImpl();
        Field f = utils.getField(productData, "productid");
        assertNotNull("field is null", f);
        assertThat("name is not productid", f.getName(), is("productid"));
    }

    @Ignore
    @Test(expected = NoSuchFieldException.class)
    public void testNotExistentFieldname() throws Exception {
        //ProductData productData = new ProductData("123");
        //BeanReflectionUtils utils = new BeanReflectionUtilsImpl();
        Field f = utils.getField(productData, "NonExistentFieldname");
    }

    @Ignore
    @Test
    public void testGetArrayOfNonStaticFields() throws Exception {
        List<Field> fldList = utils.getNonStaticFields(productData.getClass());
        assertNotNull("fldList is null", fldList);
        assertThat("fldList has not two elements", fldList.size(), is(2));
    }
}