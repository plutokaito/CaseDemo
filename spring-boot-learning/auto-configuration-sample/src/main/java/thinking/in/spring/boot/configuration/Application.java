package thinking.in.spring.boot.configuration;

import org.springframework.boot.WebApplicationType;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class Application {
    public static void main(String[] args) {
        Class<?> bootstrapClass = Application.class;

        ConfigurableApplicationContext context = new SpringApplicationBuilder(bootstrapClass)
                .web(WebApplicationType.NONE)
                .run();
        System.out.println("当前引导类 Bean :" + context.getBean(bootstrapClass));
        context.close();
    }
}
