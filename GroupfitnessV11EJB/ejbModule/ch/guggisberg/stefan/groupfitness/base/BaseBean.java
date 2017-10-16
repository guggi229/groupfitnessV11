package ch.guggisberg.stefan.groupfitness.base;

import java.io.Serializable;
import java.util.Locale;
import java.util.ResourceBundle;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

/**
 * 
 * @author guggi229
 *
 * Generelle Elemente für die App
 *
 */
abstract public class BaseBean implements Serializable {

	private static final long serialVersionUID = 7832409288788294742L;

	public void showGlobalMessage(String message, String key){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,message, key);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}

	public void showGlobalErrorMessage(String message, String key){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,message, key);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}
	public String getText(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication().getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
		return bundle.getString(key);
	}
}