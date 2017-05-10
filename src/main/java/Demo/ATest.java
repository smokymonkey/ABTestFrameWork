package Demo;

import org.springframework.stereotype.Component;

/**
 * Created by jie on 3/12/17.
 */
@Component
public class ATest implements ABTest {
    int count =1;
    public ATest(){
        System.out.println("ATest construction");
    }
    public void test() {
        System.out.println("This is A test");
    }

//    @Override
//    public boolean iamPicked(RunningContext runningContext) {
//        return runningContext.getSeed()==1;
//    }

    @Override
    public boolean iamSelected(Object o) {
        count++;
        return count%2==0;
    }
}
