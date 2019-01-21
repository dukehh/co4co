package gh.dgile.subject;

import gh.dgile.subject.seeder.ContactTypeSeeder;
import gh.dgile.subject.seeder.SubjectSeeder;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Log4j2
@Component
public class SubjectCommandLineRunner implements CommandLineRunner {

  @Value("${server.port}")
  private String port;

  private final SubjectSeeder subjectSeeder;
  private final ContactTypeSeeder contactTypeSeeder;

  public SubjectCommandLineRunner(SubjectSeeder subjectSeeder, ContactTypeSeeder contactTypeSeeder) {
    this.subjectSeeder = subjectSeeder;
    this.contactTypeSeeder = contactTypeSeeder;
  }

  @Override
  public void run(String... strings) {

    log.info("\n->: \t\t\t{}", "-------------------");
    log.info("\n->Port:\t\t\t\t\t\t{}", this.port);
    log.info("\n->subjectSeederLineCount:\t{}", this.subjectSeeder.SubjectSeed());
    log.info("\n->contactTypeSeeder:\t\t{}", this.contactTypeSeeder.contactTypeSeed());
    log.info("\n->: \t\t\t{}", "-------------------");

  }

}
