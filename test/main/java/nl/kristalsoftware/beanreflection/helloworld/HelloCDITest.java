package nl.kristalsoftware.beanreflection.helloworld;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.JavaArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import javax.enterprise.inject.spi.BeanManager;
import javax.inject.Inject;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.*;

/**
 * Created by sjoerdadema on 25-06-15.
 */
@RunWith(Arquillian.class)
public class HelloCDITest {
    @Deployment
    public static Archive<?> createTestArchive() {
        return ShrinkWrap.create(JavaArchive.class).addClass(HelloWorld.class).addAsManifestResource(EmptyAsset.INSTANCE, "beans.xml");
    }

    @Inject
    private BeanManager beanManager;

    @Inject
    private HelloWorld helloWorld;

    @Test
    public void testCDIBootstrap() {
        assertNotNull("beanManager not injected", beanManager);
        assertFalse("no beans from BeanManager class", beanManager.getBeans(BeanManager.class).isEmpty());
        assertThat("no Hello World output", helloWorld.getText(), is("Hello World"));
    }
}
