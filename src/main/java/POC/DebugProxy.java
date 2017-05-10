package POC;

import ABTestFrameWork.Selector;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by jo0235 on 5/10/17.
 */
public class DebugProxy implements java.lang.reflect.InvocationHandler {

    private Set<Object> objs=new HashSet<>();
    private static DebugProxy instance=null;
    private static Object proxyInstance=null;
    public static Object newInstance(Object obj) {

        if (instance==null)
            instance=new DebugProxy(obj);
        else
            instance.addObject(obj);
        if (proxyInstance==null)
            proxyInstance= java.lang.reflect.Proxy.newProxyInstance(
                    obj.getClass().getClassLoader(),
                    obj.getClass().getInterfaces(),
                    instance);

        return proxyInstance;
    }

    private void addObject(Object obj){
        this.objs.add(obj);
    }
    private DebugProxy(Object obj) {
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
                if (selector.iamSelected(args[0]))
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