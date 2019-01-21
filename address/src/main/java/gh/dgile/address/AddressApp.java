package gh.dgile.address;

import gh.dgile.address.shared.GHproperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties(GHproperties.class)
public class AddressApp {

  public static void main(String[] args) {
    SpringApplication.run(AddressApp.class, args);
  }
}
