package gh.dgile.subject.staff;

import gh.dgile.subject.subject.Subject;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.EntityManager;
import java.util.List;

@Log4j2
@RestController
public class StaffController {

  private EntityManager em;

  private final StaffService staffService;

  public StaffController(StaffService staffService) {
    this.staffService = staffService;
  }

  @RequestMapping("/allstaff")
  public List<Subject> getAllStaff() {
    return staffService.getAllStaff();
  }


  @SuppressWarnings("unchecked")
  @RequestMapping("/allstaffcontacts")
  public List<Object[]> getAllStaffContacts() {

    List<Object[]> results = em.createQuery("SELECT s.firstName, s.lastName, c.types FROM Staff s JOIN ContactType c ON s.id = c.subject").getResultList();
    for (Object[] result : results) {
      log.info(result[0] + " " + result[1] + " - " + result[2]);
    }
    return results;

  }

  @RequestMapping("/lastname/{lastName}")
  public Subject findStaffByLastName(@PathVariable("lastName") String lastName) {
    return staffService.findStaffByLastName(lastName);
  }
  @RequestMapping("/{id}")
  public String findStaffById(@PathVariable("id") String id) {
    return staffService.findStaffById(id);
  }

}
