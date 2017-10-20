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
import javax.servlet.http.HttpSession;
import org.apache.log4j.Logger;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
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
@ManagedBean(name="loginBeanV2")
@SessionScoped
public final class LoginBeanV2 extends BaseBean implements Serializable  {
	private static final long serialVersionUID = -3070846601605974845L;
	private static Logger log = Logger.getLogger(LoginBeanV2.class);

	public String logout() {
		FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
	     return "/home.xhtml?faces-redirect=true";
	}
	
	public String login() {
		return "customer/customersHome";
	}
	
}