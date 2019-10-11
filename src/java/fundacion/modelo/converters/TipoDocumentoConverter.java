/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.converters;


import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.entidades.TipoDocumento;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;


@FacesConverter(value = "tipoDocumentoConverter")
public class TipoDocumentoConverter implements Converter{
    
    private TipoDocumentoFacade tipoDocumentoFacade;
    
    public TipoDocumentoConverter(){
        tipoDocumentoFacade = CDI.current().select(TipoDocumentoFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return tipoDocumentoFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof TipoDocumento) {
            return ((TipoDocumento) value).getIdtiposDocumento().toString();
        }
        return "";

    }
    
}
