package ssafy.haruman;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

@SpringBootApplication
@EnableJpaAuditing
public class HarumanApplication {

    public static void main(String[] args) {
        SpringApplication.run(HarumanApplication.class, args);
    }

}
