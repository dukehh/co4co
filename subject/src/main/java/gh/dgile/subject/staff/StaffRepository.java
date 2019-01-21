package gh.dgile.subject.staff;

import gh.dgile.subject.subject.Subject;
import gh.dgile.subject.subject.SubjectRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface StaffRepository extends SubjectRepository {

  @Query(value = "select s from Staff s where s.lastName = :lastname")
  Subject findStaffByLastName(@Param("lastname") String lastname);

}
