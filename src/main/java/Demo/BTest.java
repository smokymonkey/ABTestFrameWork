package Demo;

import org.springframework.stereotype.Component;

/**
 * Created by jie on 3/12/17.
 */
@Component
public class BTest implements ABTest {
    public BTest(){
        System.out.println("BTest construction");
    }
    public void test() {
        System.out.println("This is B test");
    }

    @Override
    public boolean iamPicked(RunningContext runningContext) {
        return runningContext.getSeed()==2;
    }
}
