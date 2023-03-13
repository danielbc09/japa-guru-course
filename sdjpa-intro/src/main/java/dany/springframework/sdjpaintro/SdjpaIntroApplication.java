package dany.springframework.sdjpaintro;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication(scanBasePackages = {"dany.springframework.sdjpaintro.bootstrap"})
public class SdjpaIntroApplication {
  public static void main(String[] args) {
    SpringApplication.run(SdjpaIntroApplication.class, args);
  }
}
