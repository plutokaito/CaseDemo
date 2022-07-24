package thinking.in.springboot.samples.spring50.annotations;

import org.springframework.core.annotation.AliasFor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Transactional
@Service(value = "transactionService")
public @interface TransactionService {

  //  @AliasFor(attribute = "value")
    String name() default "";

//    @AliasFor("name")
    String value() default "txManager";

   // @AliasFor(attribute = "transactionManager", annotation = Transactional.class)
   // String transactionManager() default "txManager";
}
