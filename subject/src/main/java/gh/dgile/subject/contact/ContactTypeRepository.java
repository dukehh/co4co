package gh.dgile.subject.contact;

import gh.dgile.subject.subject.Subject;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ContactTypeRepository extends JpaRepository<ContactType, Long> {

  @Query(value = "select c from #{#entityName} c where c.subject = ?1")
  List<ContactType> findContactTypesBySubjectId(Subject subject);

}
