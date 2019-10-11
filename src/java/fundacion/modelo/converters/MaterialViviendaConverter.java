package fundacion.modelo.converters;


import fundacion.modelo.dao.MaterialViviendaFacade;
import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.dao.TipoViviendaFacade;
import fundacion.modelo.entidades.MaterialVivienda;
import fundacion.modelo.entidades.TipoDocumento;
import fundacion.modelo.entidades.TipoVivienda;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "materialViviendaConverter")
public class MaterialViviendaConverter implements Converter{
    
    private MaterialViviendaFacade materialViviendaFacade;
    
    public MaterialViviendaConverter(){
        materialViviendaFacade = CDI.current().select(MaterialViviendaFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return materialViviendaFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof MaterialVivienda) {
            return ((MaterialVivienda) value).getId().toString();
        }
        return "";

    }
    
}
