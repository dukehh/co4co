package gh.dgile.subject.seeder;

import gh.dgile.subject.shared.GHproperties;
import gh.dgile.subject.shared.Helpers;
import gh.dgile.subject.staff.Staff;
import gh.dgile.subject.staff.StaffRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Scanner;

@Log4j2
@Component
public class SubjectSeeder {

  private String namesCSV;
  private String delimiterCSV;
  private boolean namesHasTitle;

  private final StaffRepository staffRepository;

  public SubjectSeeder(
    GHproperties ghPs,
    StaffRepository staffRepository) {
    this.staffRepository = staffRepository;

    this.namesCSV = ghPs.getCsvProps().getOrDefault("names", "names.csv");
    this.delimiterCSV = ghPs.getCsvProps().getOrDefault("delimiterCSV", ";");
    this.namesHasTitle = Boolean.parseBoolean(ghPs.getCsvProps().getOrDefault("has-title", "true"));
  }

  public int SubjectSeed() {
    String[] row;

    ClassLoader classLoader = getClass().getClassLoader();
    File file = new File(Objects.requireNonNull(classLoader.getResource(namesCSV)).getFile());
    try (Scanner scanner = new Scanner(file)) {
      if (namesHasTitle) {
        scanner.nextLine();
      }
      List<Staff> fileStaff = new ArrayList<>();
      while (scanner.hasNextLine()) {
        String line = scanner.nextLine();
        row = line.split(delimiterCSV, -1);
        fileStaff.add(new Staff(row[0], row[1], new BigDecimal((Helpers.getRandomNumber()))));
      }
      scanner.close();

      List<Staff> staffList = staffRepository.saveAll(fileStaff);

      for (Staff staff : staffList) {
        if ((int) (Math.random() * 10) == 0) {
          staff.setDeleted(true);
        }
      }

      staffRepository.saveAll(staffList);

      return staffList.size();

    } catch (IOException e) {
      e.printStackTrace();
      return 0;
    }
  }

}
