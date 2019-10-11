package fundacion.modelo.converters;


import fundacion.modelo.dao.NivelEducativoFacade;
import fundacion.modelo.entidades.NivelEducativo;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "nivelEducativoConverter")
public class NivelEducativoConverter implements Converter{
    
    private NivelEducativoFacade nivelEducativoFacade;
    
    public NivelEducativoConverter(){
        nivelEducativoFacade = CDI.current().select(NivelEducativoFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return nivelEducativoFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof NivelEducativo) {
            return ((NivelEducativo) value).getIdnivelEducativo().toString();
        }
        return "";

    }
    
}
