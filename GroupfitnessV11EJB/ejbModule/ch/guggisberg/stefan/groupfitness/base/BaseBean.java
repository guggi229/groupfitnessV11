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
 * Generelle Elemente für die App.
 * 
 * Lädt das richtige Message Bundle für die Übersetzung
 *
 */
abstract public class BaseBean implements Serializable {

	private static final long serialVersionUID = 7832409288788294742L;

	public void showGlobalMessage(String key, String jsfInfo){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,getText(key), jsfInfo);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}

	public void showGlobalErrorMessage(String key, String jsfInfo){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,getText(key), jsfInfo);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}
	private String getText(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication().getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
		return bundle.getString(key);
	}
}