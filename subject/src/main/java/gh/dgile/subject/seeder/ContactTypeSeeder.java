package gh.dgile.subject.seeder;

import gh.dgile.subject.contact.ContactType;
import gh.dgile.subject.contact.ContactTypeRepository;
import gh.dgile.subject.contact.ContactTypesEnum;
import gh.dgile.subject.shared.Helpers;
import gh.dgile.subject.staff.StaffRepository;
import gh.dgile.subject.subject.Subject;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

import static gh.dgile.subject.shared.Helpers.getRandomNumberNoDecimals;

@Log4j2
@Component
public class ContactTypeSeeder {

  private final StaffRepository staffRepository;
  private final ContactTypeRepository contactTypeRepository;

  public ContactTypeSeeder(
      StaffRepository staffRepository, ContactTypeRepository contactTypeRepository) {
    this.staffRepository = staffRepository;
    this.contactTypeRepository = contactTypeRepository;
  }

  public int contactTypeSeed() {

    List<Subject> staffList = staffRepository.findAll();
    List<ContactType> cts = new ArrayList<>();

    int max = 6;
    int[] arr = new int[max];

    for (Subject staff : staffList) {
      int j = getRandomNumberNoDecimals(0, max, 1);
      arr[j]++;
      ContactType ct;
      switch (j) {
        case 5:
          cts.add(new ContactType(String.valueOf((int) Helpers.getRandomNumber()), ContactTypesEnum.PHONE));
          ct = cts.get(cts.size() - 1);
          ct.setSubject(staff);
        case 4:
          cts.add(
              new ContactType(
                  String.valueOf((int) Helpers.getRandomNumber()) + "@abc.com", ContactTypesEnum.EMAIL));
          ct = cts.get(cts.size() - 1);
          ct.setSubject(staff);
        case 3:
          cts.add(
              new ContactType(
                  String.valueOf((int) Helpers.getRandomNumber()) + "XING.com", ContactTypesEnum.XING));
          ct = cts.get(cts.size() - 1);
          ct.setSubject(staff);
        case 2:
          cts.add(
              new ContactType(
                  String.valueOf((int) Helpers.getRandomNumber()) + "linkedIn.com",
                  ContactTypesEnum.LINKEDIN));
          ct = cts.get(cts.size() - 1);
          ct.setSubject(staff);
        case 1:
          cts.add(new ContactType("00" + (int) Helpers.getRandomNumber(), ContactTypesEnum.MOBILE));
          ct = cts.get(cts.size() - 1);
          ct.setSubject(staff);
        default:
          break;
      }
    }
    log.info("\n->Verteilung CTS: {}", arr);

    return contactTypeRepository.saveAll(cts).size();
  }

}
