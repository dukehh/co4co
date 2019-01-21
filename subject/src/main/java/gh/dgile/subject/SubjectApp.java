package gh.dgile.subject;

import gh.dgile.subject.shared.GHproperties;
import lombok.extern.log4j.Log4j2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@Log4j2
@SpringBootApplication
@EnableConfigurationProperties(GHproperties.class)
public class SubjectApp {

  public static void main(String[] args) {
    SpringApplication.run(SubjectApp.class, args);

  }

 }
