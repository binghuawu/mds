package data.domain.b;

import java.io.Serializable;

public class BProjectDetail implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String user;

	private String prj;

	public BProjectDetail(String user, String prj) {
		super();
		this.user = user;
		this.prj = prj;
	}

	public String getUser() {
		return user;
	}

	public void setUser(String user) {
		this.user = user;
	}

	public String getPrj() {
		return prj;
	}

	public void setPrj(String prj) {
		this.prj = prj;
	}
}
