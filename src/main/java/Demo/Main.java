package Demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Created by jie on 3/12/17.
 */
@Configuration
@EnableAutoConfiguration
@ComponentScan({"Demo","ABTestFrameWork"})
public class Main {

    @Autowired
    DummyClass dc;
    public static void main(String[] args){
        ConfigurableApplicationContext ctx = SpringApplication.run(Main.class, args);
        ctx.getBean(Main.class).foo();
    }
    public void foo(){
        dc.foo();
    }
    public Main(){
        System.out.println("This is the main program");
    }
}
