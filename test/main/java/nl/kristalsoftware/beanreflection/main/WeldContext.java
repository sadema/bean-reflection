package nl.kristalsoftware.beanreflection.main;

import org.jboss.weld.environment.se.WeldContainer;
import org.jboss.weld.environment.se.Weld;

/**
 * Created by sjoerdadema on 24-06-15.
 */
public class WeldContext {

    public static final WeldContext INSTANCE = new WeldContext();

    private final Weld weld;
    private final WeldContainer container;

    private WeldContext() {
        this.weld = new Weld();
        this.container = weld.initialize();

        Runtime.getRuntime().addShutdownHook(new Thread() {
            @Override
            public void run() {
                weld.shutdown();
            }
        });
    }

    public <T> T getBean(Class<T> type) {
        return container.instance().select(type).get();
    }

}
