package gh.dgile.address.seeder;

import gh.dgile.address.entity.Address;
import gh.dgile.address.repository.AddressRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;
import java.util.stream.Stream;

@Log4j2
@Component
public class AddressSeeder {

  @Value("${address.addresses}")
  private String addressesCSV;

  @Value("${address.delimiter}")
  private String delimiterCSV;

  @Value("${address.has-title}")
  private boolean addressesHasTitle;

  private final AddressRepository addressRepository;

  public AddressSeeder(AddressRepository addressRepository) {
    this.addressRepository = addressRepository;
  }

  public int CsvSeed() {

    Stream.of(
            new Address("12345", "Stadt12345", "KreisPlön", "Sudetenstr.", "4"),
            new Address("22345", "Stadt12345", "KreisPlön", "Sudetenstr.", "4"),
            new Address("32345", "Stadt12345", "KreisPlön", "Sudetenstr.", "4"))
        .forEach(addressRepository::save);

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(Objects.requireNonNull(classLoader.getResource(addressesCSV)).getFile());
    try (Scanner scanner = new Scanner(file)) {
      if (addressesHasTitle) {
        scanner.nextLine();
      }
      List<Address> fileAddresses = new ArrayList<>();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        String[] row;
        row = line.split(delimiterCSV, -1);
        fileAddresses.add(new Address(row[0], row[1], row[2], row[3], ""));
      }
      scanner.close();

      log.info("\n====>: addressesCSV: {}", addressesCSV);
      return addressRepository.saveAll(fileAddresses).size();

    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }
}
