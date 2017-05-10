package Demo;

import ABTestFrameWork.TestInterface;
import ABTestFrameWork.TestMethod;

/**
 * Created by jie on 3/12/17.
 *
 */
@TestInterface
public interface ABTest {
    @TestMethod
    void test();
    boolean iamPicked(RunningContext runningContext);
}
