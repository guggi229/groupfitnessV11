package ch.guggisberg.stefan.groupfitness.converter;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.FacesConverter;

/**
 * 
 * @author guggi229
 *
 * Konvetiert Java 8 DateTime in utils.date für JSF 2.x
 */

@FacesConverter(value="localTimeConverter")
public class LocalTimeConverter implements javax.faces.convert.Converter {

    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
          return LocalTime.parse(value);
    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        LocalTime dateValue = (LocalTime) value;
        return dateValue.format(DateTimeFormatter.ofPattern("HH:mm"));
    }
    
}