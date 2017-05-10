package POC;

/**
 * Created by jo0235 on 5/10/17.
 */
public class FooImpl implements Foo {
    int count =1;
    public Object bar(Object obj)  {
        System.out.println("This is FooImpl");
        return null;
    }

    @Override
    public boolean iamSelected(Object o) {
        count++;
        return count%2==0;
    }
}
