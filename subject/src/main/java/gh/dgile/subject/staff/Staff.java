package gh.dgile.subject.staff;

import java.math.BigDecimal;

import javax.persistence.Cacheable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import gh.dgile.subject.subject.Subject;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@Data
@EqualsAndHashCode(callSuper = false)
@NoArgsConstructor
//@ToString(includeFieldNames = false)
@Entity
@Table(name = "staff")
@Cacheable
public class Staff extends Subject {

  private static final long serialVersionUID = 1L;

  @Column(name = "salary", nullable = false)
  private BigDecimal salary;

  public Staff(String firstName, String lastName, BigDecimal salary) {
    super(firstName, lastName);
    this.salary = salary;
  }

  @Override
  public String toString() {
    return "\n->Staff{" + super.toString() + " " + getSalary() + "}";
  }
}
