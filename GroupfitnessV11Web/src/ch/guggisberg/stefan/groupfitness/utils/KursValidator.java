package ch.guggisberg.stefan.groupfitness.utils;

import java.util.Locale;
import java.util.ResourceBundle;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

import ch.guggisberg.stefan.groupfitness.base.BaseBean;
@FacesValidator("ch.guggisberg.stefan.groupfitness.utils.KursValidator")
public class KursValidator extends BaseBean  implements Validator {

	private static final long serialVersionUID = -925520855177022909L;

	@Override
	public void validate(FacesContext arg0, UIComponent arg1, Object value) throws ValidatorException {
		
		if (value == null || value.toString().equals("")) {
			System.out.println(value);
			
			showLocalErrrorMessage();
			
		}

	}
	
	public String getText(String key) {
		FacesContext facesContext = FacesContext.getCurrentInstance();
		String messageBundleName = facesContext.getApplication().getMessageBundle();
		Locale locale = facesContext.getViewRoot().getLocale();
		ResourceBundle bundle = ResourceBundle.getBundle(messageBundleName, locale);
		return bundle.getString(key);
	}


}
