package ch.guggisberg.stefan.groupfitness.services;

import java.io.Serializable;
import java.util.Locale;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import org.apache.log4j.Logger;
import ch.guggisberg.stefan.groupfitness.entities.User;

/**
 * 
 * @author guggi229
 * 
 * This bean handle the login.
 * Value User
 * NON INERIT!
 *
 */
@ManagedBean(name="loginBean")
@SessionScoped
@Deprecated
public final class LoginBean implements Serializable  {
	private static final long serialVersionUID = -3070846601605974845L;
	private static Logger log = Logger.getLogger(LoginBean.class);

	// DB Verbindung
	@PersistenceContext
	private EntityManager em;

	// Logindaten
	private String  email;
	private String pass;
	private boolean loggedIn;

	//User
	@Inject
	private User user;

	/**
	 * Check the login Data
	 * @return
	 */
	public String checkLogin(){
		Query query = em.createNativeQuery("Select Userid from groupfitness.user u WHERE u.UserEmail='" + email + "' AND u.UserPassword='" + pass + "'");
		try {
			Integer result=0;
			result =  (Integer) query.getSingleResult();
			if (result.intValue()>0){
				
				// User Entität aus DB laden
				user = em.find(User.class, result.longValue());
				log.info("User mit Email " + email + "hat korrekt eingeloggt");
				loggedIn=true;
				
				// Local für diesen User setzen.
				UIViewRoot viewRoot = FacesContext.getCurrentInstance().getViewRoot();
				viewRoot.setLocale(new Locale(user.getUserLang()));
				return "secured/welcome";
			}
		} catch (NoResultException e) {
			loggedIn=false;
			log.info("User mit Email " + email + "hat ist nicht eingeloggt");
		}
		return "/login";

	}

	/**
	 * Zerstört die Session!
	 * 
	 * @return Startpage
	 */
	public String doLogout(){
		loggedIn = false;																
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();	// Usersession auf dem Backend löschen
		return "/login";
	}
	
	// Getter and Setter
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public void setPass(String pass) {
		this.pass = pass;
	}
	public boolean isLoggedIn() {
		return loggedIn;
	}

	public String getPass() {
		return pass;
	}

	public void setLoggedIn(boolean loggedIn) {
		this.loggedIn = loggedIn;
	}

	public boolean isLogged(){
		return loggedIn;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	
	
}