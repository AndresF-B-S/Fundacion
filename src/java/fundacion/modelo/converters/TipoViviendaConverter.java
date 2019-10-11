package fundacion.modelo.converters;


import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.dao.TipoViviendaFacade;
import fundacion.modelo.entidades.TipoDocumento;
import fundacion.modelo.entidades.TipoVivienda;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "tipoViviendaConverter")
public class TipoViviendaConverter implements Converter{
    
    private TipoViviendaFacade tipoViviendaFacade;
    
    public TipoViviendaConverter(){
        tipoViviendaFacade = CDI.current().select(TipoViviendaFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return tipoViviendaFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof TipoVivienda) {
            return ((TipoVivienda) value).getId().toString();
        }
        return "";

    }
    
}
