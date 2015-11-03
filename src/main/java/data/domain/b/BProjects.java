package data.domain.b;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "t_projects")
public class Projects implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Id
	@ManyToOne
	@JoinColumn(name = "prj_id")
	private Project prj;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private BUser user;

	public Project getPrj() {
		return prj;
	}

	public void setPrj(Project prj) {
		this.prj = prj;
	}

	public BUser getUser() {
		return user;
	}

	public void setUser(BUser user) {
		this.user = user;
	}
}
