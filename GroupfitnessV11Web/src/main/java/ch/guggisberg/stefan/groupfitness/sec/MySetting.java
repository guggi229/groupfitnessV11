package ch.guggisberg.stefan.groupfitness.sec;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@ManagedBean

public class MySetting {
	
	@EJB
	UserServiceRemote usr;

	public void sayHello() {
		usr.sayHello();
	}
}
