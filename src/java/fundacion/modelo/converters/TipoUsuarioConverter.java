/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.modelo.converters;


import fundacion.modelo.dao.TipoUsuarioFacade;
import fundacion.modelo.entidades.TipoUsuario;
import javax.enterprise.inject.spi.CDI;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

/**
 *
 * @author David
 */
@FacesConverter(value = "tipoUsuarioConverter")
public class TipoUsuarioConverter implements Converter{
    
    private TipoUsuarioFacade tipoUsuarioFacade;
    
    public TipoUsuarioConverter(){
        tipoUsuarioFacade = CDI.current().select(TipoUsuarioFacade.class).get();
    }
    
    @Override
    public Object getAsObject(FacesContext context, UIComponent component, String value) {
        if (value != null && !value.equals("")) {
            try {

                return tipoUsuarioFacade.find(Integer.valueOf(value));
            } catch (NumberFormatException numberFormatException) {
                numberFormatException.printStackTrace();
            }
        }
        return null;

    }

    @Override
    public String getAsString(FacesContext context, UIComponent component, Object value) {
        if (value != null && value instanceof TipoUsuario) {
            return ((TipoUsuario) value).getIdtiposUsuarios().toString();
        }
        return "";

    }
    
}
