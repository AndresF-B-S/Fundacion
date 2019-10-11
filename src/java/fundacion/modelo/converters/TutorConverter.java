package fundacion.modelo.converters;


import fundacion.modelo.dao.TutorFacade;
import fundacion.modelo.entidades.Tutor;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "tutorConverter")
public class TutorConverter implements Converter{
    
    private TutorFacade tutorFacade;
    
    public TutorConverter(){
        tutorFacade = CDI.current().select(TutorFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return tutorFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof Tutor) {
            return ((Tutor) value).getIdTutor().toString();
        }
        return "";

    }
    
}
