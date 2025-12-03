package fibrastar;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling; // <--- IMPORTANTE

@SpringBootApplication
@EnableScheduling // <--- AGREGA ESTA LÍNEA
public class SistemaGestionApplication {

    public static void main(String[] args) {
        SpringApplication.run(SistemaGestionApplication.class, args);
    }
}