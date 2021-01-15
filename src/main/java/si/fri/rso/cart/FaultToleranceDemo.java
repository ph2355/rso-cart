package si.fri.rso.cart;

import com.kumuluz.ee.configuration.cdi.ConfigBundle;
import com.kumuluz.ee.configuration.cdi.ConfigValue;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
@ConfigBundle("misc")
public class FaultToleranceDemo {

    @ConfigValue(watch = true)
    private Boolean faultToleranceDemo;

    public Boolean getFaultToleranceDemo() {
        return faultToleranceDemo;
    }

    public void setFaultToleranceDemo(Boolean faultToleranceDemo) {
        this.faultToleranceDemo = faultToleranceDemo;
    }
}
