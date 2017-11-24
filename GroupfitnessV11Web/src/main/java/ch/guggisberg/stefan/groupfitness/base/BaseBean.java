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
 * Generelle Elemente f�r die App.
 * 
 * L�dt das richtige Message Bundle f�r die �bersetzung
 *
 */



abstract public class BaseBean implements Serializable {
	
	private static final long serialVersionUID = 7832409288788294742L;

	/**
	 * 
	 * @param key Ist eine Schl�ssel in der Message Bundle
	 * @param jsfInfo Wird in dieser Version nicht ben�tigt.
	 */
	public void showGlobalMessage(String key, String jsfInfo){
		
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_INFO,getText(key), jsfInfo);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}
	/**
	 * Zeigt Fehlermeldungen im Globale Scope an.
	 * @param key Ist eine Schlüssel in der Message Bundle
	 * @param jsfInfo Wird in dieser Version nicht benötigt.
	 */
	public void showGlobalErrorMessage(String key, String jsfInfo){
		FacesMessage facesMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR,getText(key), jsfInfo);
		FacesContext fc = FacesContext.getCurrentInstance();
		fc.addMessage(null, facesMsg);
	}
	 public void showLocalErrrorMessage()  {
	FacesMessage msg =
			new FacesMessage("E-mail validation failed.",
					"Die Emaailadresse hat ein falsches Format!");
	msg.setSeverity(FacesMessage.SEVERITY_ERROR);
	// throw new ValidatorException(msg);
	}
	
	/**
	 * Sucht die Übersetzung mit dem Key
	 * @param key
	 * @return übersetzer Text
	 */
	public String getText(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication().getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
		return bundle.getString(key);
	}
	

	
	
}