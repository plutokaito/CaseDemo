package thinking.in.spring.boot.samples.spring25.annotation;


import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Documented
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Component
// @Repository  // -- No bean named 'chineseNameRepository' is defined
public @interface StringRepository {
    String value() default "";
}
