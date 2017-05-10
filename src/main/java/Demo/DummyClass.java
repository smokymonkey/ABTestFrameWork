package Demo;

import ABTestFrameWork.TestInterface;
import Demo.ABTest;
import org.springframework.stereotype.Component;

/**
 * Created by jie on 3/20/17.
 */
@Component
public class DummyClass {
    public DummyClass(){
        System.out.println("DummyClass construction");
    }

    @TestInterface
    private ABTest abTest;
    public void foo(){
        abTest.test();
    }

}
