package POC;

/**
 * Created by jo0235 on 5/10/17.
 */
public class FooImpl1 implements Foo {
    int count=0;
    @Override
    public boolean iamSelected(Object o) {
        count++;
        return count%2==0;
    }

    @Override
    public Object bar(Object obj) {
        System.out.println("This is FooImpl1");
        return null;
    }
}
