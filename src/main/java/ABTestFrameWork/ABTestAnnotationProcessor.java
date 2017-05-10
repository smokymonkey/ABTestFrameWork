package ABTestFrameWork;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * Created by jie on 3/20/17.
 */
@Component
public class ABTestAnnotationProcessor implements BeanPostProcessor {

    private ConfigurableListableBeanFactory configurableBeanFactory;
    Map<Class,Set<Object>> m =new HashMap<>();
    @Autowired
    public ABTestAnnotationProcessor(ConfigurableListableBeanFactory beanFactory) {
        this.configurableBeanFactory = beanFactory;

    }

    public void createProxy(Object bean,String beanName){

        for(Class<?> cls:bean.getClass().getInterfaces()){
            if(cls.isAnnotationPresent(TestInterface.class)) {
                System.out.println(beanName);
                for(Method method:cls.getMethods()){
                    if(method.isAnnotationPresent(TestMethod.class))
                        System.out.println(method.getName());
                }
            }
        }
    }
    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName)
            throws BeansException {

            for (Class c : bean.getClass().getInterfaces()) {
                if (c.isAnnotationPresent(TestInterface.class) && !beanName.equals("testCallProxy")) {
                    if (!m.containsKey(c)) {
                        m.put(c, new HashSet<>());
                    }
                    m.get(c).add(bean);
                }
            }

        for(Field f:bean.getClass().getDeclaredFields()){
            if(f.isAnnotationPresent(TestInterface.class)){
                f.setAccessible(true);
                try {
                    f.set(bean,configurableBeanFactory.getBean(TestCallProxy.class,m,f.getType()));
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
                f.setAccessible(false);

            }
        }


//        this.scanDataAccessAnnotation(bean, beanName);
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName)
            throws BeansException {
        return bean;
    }


}