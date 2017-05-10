package POC;

/**
 * Created by jo0235 on 5/10/17.
 */
public class Main {
    public static void main(String[] args){
        Foo foo = (Foo) DebugProxy.newInstance(new FooImpl());
        foo=(Foo) DebugProxy.newInstance(new FooImpl1());
        foo.bar(new Object[]{null});
        foo.bar(new Object[]{null});
        foo.bar(new Object[]{null});
    }
}
