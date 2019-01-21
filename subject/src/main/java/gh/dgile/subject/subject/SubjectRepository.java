package gh.dgile.subject.subject;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubjectRepository extends JpaRepository <Subject, Long>{

  @Override
  @Query(value = "select e from #{#entityName} e where e.isDeleted=false")
  List<Subject> findAll();

  //recycle bin
  @Query(value = "select e from #{#entityName} e where e.isDeleted=true")
  List<Subject> recycleBin();

  @Query(value = "update #{#entityName} e set e.isDeleted=true where e.id=?1")
  @Modifying
  @Transactional
  void softDelete(Long id);

}
