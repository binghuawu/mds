package data.domain.a;

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
	private AProject prj;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private AUser user;

	public AProject getPrj() {
		return prj;
	}

	public void setPrj(AProject prj) {
		this.prj = prj;
	}

	public AUser getUser() {
		return user;
	}

	public void setUser(AUser user) {
		this.user = user;
	}
}
