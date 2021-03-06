package ABTestFrameWork;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jie on 5/9/17.
 */

public class TestCallProxy implements java.lang.reflect.InvocationHandler {
    private Set<Object> objs=new HashSet<>();
    private static TestCallProxy instance=new TestCallProxy();
    private static Object proxyInstance=null;
    public static TestCallProxy getInstance(){
        return instance;
    }
    public static Object newInstance(Class[] c,ClassLoader classLoader) {
        if (proxyInstance==null)
            proxyInstance= java.lang.reflect.Proxy.newProxyInstance(
                    classLoader,
                    c,
                    instance);

        return proxyInstance;
    }
    public static Object newInstance(Object obj) {

        instance.addObject(obj);
        return newInstance(obj.getClass().getInterfaces(),obj.getClass().getClassLoader());

    }
    private TestCallProxy(){}

    public void addObject(Object obj){
        this.objs.add(obj);
    }
    private TestCallProxy(Object obj) {
        objs.add(obj);
    }


    public Object invoke(Object proxy, Method m, Object[] args)
            throws Throwable
    {
        Object result=null;
        try {
            System.out.println("before method " + m.getName());
            for(Object obj:objs){
                Selector selector=(Selector)obj;
                if (selector.iamSelected(new Object[]{null}))
                    result = m.invoke(obj, args);
            }

        } catch (InvocationTargetException e) {
            throw e.getTargetException();
        } catch (Exception e) {
            throw new RuntimeException("unexpected invocation exception: " +
                    e.getMessage());
        } finally {
            System.out.println("after method " + m.getName());
        }
        return result;
    }
}
