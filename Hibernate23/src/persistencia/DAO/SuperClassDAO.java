package persistencia.DAO;

import org.hibernate.Session;

public class SuperClassDAO {
	private  Session s;
	
	public  Session getS() {
		return s;
	}

	public  void setS(Session s) {
		this.s = s;
	}
}
