package nl.kristalsoftware.beanreflection.main;

import org.junit.runners.BlockJUnit4ClassRunner;
import org.junit.runners.model.InitializationError;

/**
 * Created by sjoerdadema on 24-06-15.
 */
public class WeldJunit4Runner extends BlockJUnit4ClassRunner {

    public WeldJunit4Runner(Class<?> klass) throws InitializationError {
        super(klass);
    }

    @Override
    protected Object createTest() throws Exception {
        final Class<?> test = getTestClass().getJavaClass();
        return WeldContext.INSTANCE.getBean(test);
    }
}
