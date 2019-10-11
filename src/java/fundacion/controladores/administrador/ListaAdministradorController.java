
package fundacion.controladores.administrador;

import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;


@Named(value = "listaAdministradorController")
@SessionScoped
public class ListaAdministradorController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    private List<Usuario> listaAdministradores;
    
    public ListaAdministradorController() {
    }

    public List<Usuario> getListaAdministradores() {
        listaAdministradores = usuarioFacade.findAllAdministradores();
        return listaAdministradores;
    }
    
    public String agregarAdministrador() {
        return "registroAdministrador.xhtml?faces-redirect=true";
    }
    
}
