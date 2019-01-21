package gh.dgile.address;

import gh.dgile.address.seeder.AddressSeeder;
import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class AddressCommandLineRunner implements CommandLineRunner {

  @Value("${server.port}")
  private String port;

  @Value("${address.default-recipients}")
  private String [] defaultRecipients;

  private AddressSeeder addressSeeder;

  public AddressCommandLineRunner(AddressSeeder addressSeeder) {
    this.addressSeeder = addressSeeder;
  }

  @Override
  public void run(String... args) {

    log.info("\n====>: {}", "--------------------");
    log.info("\n====>: addressSeederLineCount: {}", this.addressSeeder.CsvSeed());
    log.info("\n====>: Port: {}", this.port);
    log.info("\n====>: {}", "--------------------");
    for (int i = 0; i < 3; i++) {
      log.info("\n====>: address.default-recipients[{}]: {}", i, defaultRecipients[i]);
    }
  }

}