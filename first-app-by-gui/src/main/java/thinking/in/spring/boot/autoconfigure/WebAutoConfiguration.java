package thinking.in.spring.boot.autoconfigure;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import thinking.in.spring.boot.config.WebConfiguration;

/**
 * @author ss3655
 * @date 2022.07.01
 */
//@ConditionalOnWebApplication
@Configuration
@Import(WebConfiguration.class)
public class WebAutoConfiguration {
}
