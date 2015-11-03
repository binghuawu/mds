package data.domain.b;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

@Entity
@Table(name = "t_user")
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
public class BUser implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id", length = 10)
	private Long id;

	@Column(name = "user_name", length = 20, nullable = false)
	private String name;

	@OneToOne(mappedBy = "user")
	@JoinColumn
	private BUserDetail detail;

	@OneToMany
	@JoinColumn(name = "user_id")
	private Set<BProjects> projects;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Set<BProjects> getProjects() {
		return projects;
	}

	public void setProjects(Set<BProjects> projects) {
		this.projects = projects;
	}

	public BUserDetail getDetail() {
		return detail;
	}

	public void setDetail(BUserDetail detail) {
		this.detail = detail;
	}

}
