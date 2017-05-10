package ABTestFrameWork;

import Demo.ABTest;
import Demo.RunningContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Set;

/**
 * Created by jie on 5/9/17.
 */
@Component
@Scope("prototype")
public class TestCallProxy implements ABTest {
    private Map<Class,Set<?>> dispatchMap;
    private Class testClass;
    @Autowired
    private ApplicationContext applicationContext;
    public TestCallProxy(Map<Class,Set<?>> m,Class testClass){
        this.dispatchMap = m;
        this.testClass =testClass;
    }



    @Override
    public void test() {
        RunningContext runningContext= applicationContext.getBean(RunningContext.class);
        for(Object o:dispatchMap.get(testClass)){
            ABTest abTest=(ABTest)o;
            if(abTest.iamPicked(runningContext))
                abTest.test();
        }
    }

    @Override
    public boolean iamPicked(RunningContext runningContext) {
        return false;
    }
}
