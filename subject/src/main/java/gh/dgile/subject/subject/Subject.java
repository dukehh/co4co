package gh.dgile.subject.subject;

import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;



@Data
@EqualsAndHashCode(callSuper=false)
@NoArgsConstructor
@ToString(includeFieldNames = false)
@Entity
@Cacheable
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public abstract class Subject implements Serializable {

	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Setter(AccessLevel.NONE)
	@Column(name = "id", updatable = false, nullable = false)
	private Long id;

	@Version // for detect change between select and update
	@Column(name = "version_lock", columnDefinition = "integer DEFAULT 0", nullable = false)
	private long version = 0L;

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	@UpdateTimestamp
	private LocalDateTime lastUpdatedDate;

	@CreationTimestamp
	@Setter(AccessLevel.NONE)
	private LocalDateTime createdDate;

	@Column(name = "is_deleted")
	private boolean isDeleted = false;

	public Subject(String firstName, String lastName) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
	}

}
