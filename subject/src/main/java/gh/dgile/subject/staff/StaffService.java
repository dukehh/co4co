package gh.dgile.subject.staff;

import gh.dgile.subject.subject.Subject;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import java.util.List;

@Log4j2
@SuppressWarnings("unchecked")
@Service
public class StaffService {

  private final EntityManager em;

  private final StaffRepository staffRepository;

  @Autowired
  public StaffService(StaffRepository staffRepository, EntityManager em) {
    this.staffRepository = staffRepository;
    this.em = em;
  }

  List<Subject> getAllStaff() {
    return staffRepository.findAll();
  }


  Subject findStaffByLastName(String lastName) {
    return staffRepository.findStaffByLastName(lastName);
  }

  String findStaffById(String strId) {

    Long id = Long.parseLong(strId, 10);

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
      return "\n->Empty: " + strId;
    } else {
      int i = 0; int j=0;
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

      return jStr;
    }
  }
}
