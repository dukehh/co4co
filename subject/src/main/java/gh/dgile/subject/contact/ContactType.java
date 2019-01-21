package gh.dgile.subject.contact;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Version;

import gh.dgile.subject.subject.Subject;
import lombok.AccessLevel;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Data
@NoArgsConstructor
@ToString(includeFieldNames = false)
@Entity
@Table(name = "ContactType")
public class ContactType implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Setter(AccessLevel.NONE)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Version // for detect change between select and update
  @Column(name = "version_lock", columnDefinition = "integer DEFAULT 0", nullable = false)
  private long version = 0L;

  @Column(name = "contact_type_address", nullable = false)
  private String contactTypeAddress;

  @Enumerated(EnumType.STRING)
  private ContactTypesEnum types;

  @ManyToOne
  @JoinColumn(name = "fk_subject")
  private Subject subject;

  public ContactType(String contactTypeAddress, ContactTypesEnum types) {
    super();
    this.contactTypeAddress = contactTypeAddress;
    this.types = types;
  }

}
