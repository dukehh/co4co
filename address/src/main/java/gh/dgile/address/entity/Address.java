package gh.dgile.address.entity;

import lombok.*;

import javax.persistence.*;
import java.io.Serializable;

@Data
@NoArgsConstructor
@ToString(includeFieldNames = false)
@NamedQuery(name = "find_all_Addresses", query = "select s from Address s")
@Entity
@Table(name = "Address")
public class Address implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  @Setter(AccessLevel.NONE)
  @Column(name = "id", updatable = false, nullable = false)
  private Long id;

  @Version // for detect change between select and update
  @Column(name = "version_lock", columnDefinition = "integer DEFAULT 0", nullable = false)
  private long version = 0L;
  
  @Column(name = "postal_code", nullable = false)
  private String postalCode;

  @Column(name = "city", nullable = false)
  private String city;
  
  @Column(name = "district")
  private String districtName;
  
  @Column(name = "street", nullable = false)
  private String street;
  
  @Column(name = "street_number")
  private String streetNumber;

  public Address(String postalCode, String city, String districtName, String street, String streetNumber) {
    super();
    this.postalCode = postalCode;
    this.city = city;
    this.districtName = districtName;
    this.street = street;
    this.streetNumber = streetNumber;
  }

  
}
