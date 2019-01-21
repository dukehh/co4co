package gh.dgile.subject.contact;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ContactTypeService {

  private ContactTypeRepository contactTypeRepository;

  public ContactTypeService(ContactTypeRepository contactTypeRepository) {
    this.contactTypeRepository = contactTypeRepository;
  }

  List<ContactType> getAllSContactTypes() {
    return contactTypeRepository.findAll();
  }


}
