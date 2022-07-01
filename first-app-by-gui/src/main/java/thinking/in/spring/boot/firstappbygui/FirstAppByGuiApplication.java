package thinking.in.spring.boot.firstappbygui;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

//@SpringBootApplication(scanBasePackages = "think.in.spring.boot.config")
//@Configuration
@EnableAutoConfiguration
public class FirstAppByGuiApplication {

	public static void main(String[] args) {
		SpringApplication.run(FirstAppByGuiApplication.class, args);
	}

}
