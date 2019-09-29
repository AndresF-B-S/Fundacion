
package fundacion.controladores.usuarios;

import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "listaUsuarioController")
@SessionScoped
public class ListaUsuarioController implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;
    
    private List<Usuario> listaUsuarios;
    
    public ListaUsuarioController() {
    }
    
    @PostConstruct
    
    public void init(){
        listaUsuarios = usuarioFacade.findAll();
    
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }
    
    public String agregarUsuario() {
        return "registroUsuario.xhtml?faces-redirect=true";
    }
    
}
