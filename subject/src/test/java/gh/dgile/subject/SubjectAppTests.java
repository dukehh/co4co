package gh.dgile.subject;

import gh.dgile.subject.contact.ContactType;
import gh.dgile.subject.contact.ContactTypeRepository;
import gh.dgile.subject.subject.Subject;
import gh.dgile.subject.subject.SubjectRepository;
import lombok.extern.log4j.Log4j2;

import java.util.List;
import java.util.stream.Stream;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@SuppressWarnings("JpaQlInspection")
@Log4j2
@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional
public class SubjectAppTests {

  @Autowired
  private SubjectRepository staffRepository;

  @Autowired
  private ContactTypeRepository contactTypeRepository;

  @Autowired
  EntityManager em;

  @Test
  public void csv_test() {
    log.info("\n====>: csv_test: {}", "csv_test");
  }

  @Test
  public void recycleBin_test() {

    log.info("\n====>: --------------");
    for (Subject subject : staffRepository.recycleBin()) {
      log.info("\n====>: recycleBin_test: {}", subject.toString());
    }
    log.info("\n====>: recycleBinTestSize: {}", staffRepository.recycleBin().size());
    log.info("\n====>: --------------");
  }

  @Test
  public void findContactTypesBySubjectId_test() {

    int i = 1;
    for (Subject staff : staffRepository.findAll()) {
      if (i <= 20) {
        log.info(
          "\n====>: " + i + ".) findContactTypesBySubjectId: {}",
          contactTypeRepository.findContactTypesBySubjectId(staff));
        i++;
      }
    }
  }

  @Test
  public void emFindContactTypeById_test() {
    ContactType ct = em.find(ContactType.class, 1L);
    log.info("\n====>: ct: {}", ct);
  }

  @Before
  public void init() {
  }

  @After
  public void close() {
  }

  @SuppressWarnings("unchecked")
  @Test
  public void joinQuery_test() {
    List<Object[]> results =
      em.createQuery(
        "SELECT s.firstName, s.lastName, c.types FROM Staff s LEFT JOIN ContactType c ON s.id = c.subject ORDER BY s.lastName")
        .getResultList();
    for (Object[] result : results) {
      log.info(result[1] + ", " + result[0] + " - " + result[2]);
    }
  }

  @SuppressWarnings("unchecked")
  @Test
  public void joinQueryId_test() {
    for (int k = 1; k <= 100; k++) {
      Long id = (long) k;

      List<Object[]> results =
        em.createQuery(
          "SELECT s.id, s.firstName, s.lastName, s.createdDate, c.types "
            + "FROM Staff s "
            + "JOIN ContactType c "
            + "ON s.id = c.subject "
            + "where s.id=:id")
          .setParameter("id", id)
          .getResultList();

      if (results.isEmpty()) {
        log.info("\n====>: Empty-strId: {}", id);
      } else {
        int i = 0;
        int j = 0;
        String jStr = "\n[\n{\n" +
            "\"Id\": " + "\"" + results.get(i)[j++].toString() + "\",\n" +
            "\"Firstname\": " + "\"" + results.get(i)[j++] + "\",\n" +
            "\"Lastname\": " + "\"" + results.get(i)[j++] + "\",\n" +
            "\"CreatedDate\": " + "\"" + results.get(i)[j++] + "\",\n" +
            "\"Contact\": " + "{\n\t\"Type" + (i + 1) + "\": \"" + results.get(i)[j] + "\"";

        i++;
        for (; i < results.size(); i++) {
          jStr = jStr.concat(",\n\t\"Type" + (i + 1) + "\": \"" + results.get(i)[j] + "\"");
        }
        jStr = jStr.concat("\n\t}\n}\n]");
        log.info("\n====>: jStr: {}", jStr);
      }
    }
  }

  @Test
  public void streamOf_test() {
    Stream.of(
      "a", "b", "c", "d", "e", "f"
    ).forEach(o -> log.info("\n====>: o: {}", o));
  }
}
