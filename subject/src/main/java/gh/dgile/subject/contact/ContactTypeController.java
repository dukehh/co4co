package gh.dgile.subject.contact;

import lombok.extern.log4j.Log4j2;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Log4j2
@RestController
public class ContactTypeController {

  private final ContactTypeService contactTypeService;

  public ContactTypeController(ContactTypeService contactTypeService) {
    this.contactTypeService = contactTypeService;
  }

  @RequestMapping("/allcontacttypes")
  public List<ContactType> getAllStaff() {
    return contactTypeService.getAllSContactTypes();
  }

}
