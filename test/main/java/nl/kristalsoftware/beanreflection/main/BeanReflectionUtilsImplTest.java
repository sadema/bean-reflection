package nl.kristalsoftware.beanreflection.main;

import nl.kristalsoftware.beanreflection.data.ProductData;
import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;
import java.lang.reflect.Field;
import java.util.logging.Logger;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by sjoerdadema on 15-06-15.
 */
@RunWith(Arquillian.class)
public class BeanReflectionUtilsImplTest {

    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class)
                .addClass(BeanReflectionUtilsImpl.class)
                .addClass(LogProducer.class)
                .addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private Logger log;

    @Inject
    private BeanManager beanManager;

    @Test
    public void testCDIBootstrap() throws Exception {
        log.info("Start testCDIBootstrap");
        assertNotNull("beanManager not injected", beanManager);
        assertFalse("no beans from BeanManager class", beanManager.getBeans(BeanManager.class).isEmpty());
    }

    @Test
    public void testGetProductidField() throws Exception {
        ProductData productData = new ProductData("123");
        BeanReflectionUtils utils = new BeanReflectionUtilsImpl();
        Field f = utils.getField(productData, "productid");
        assertNotNull("field is null", f);
        assertThat("name is not productid", f.getName(), is("productid"));
    }

    @Test(expected = NoSuchFieldException.class)
    public void testThrowNoSuchFieldException() throws Exception {
        ProductData productData = new ProductData("123");
        BeanReflectionUtils utils = new BeanReflectionUtilsImpl();
        Field f = utils.getField(productData, "NonExistentFieldname");
    }
}