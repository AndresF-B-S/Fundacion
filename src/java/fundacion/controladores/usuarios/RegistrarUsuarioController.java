package fundacion.controladores.usuarios;

import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.dao.TipoUsuarioFacade;
import fundacion.modelo.dao.TutorFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.TipoDocumento;
import fundacion.modelo.entidades.TipoUsuario;
import fundacion.modelo.entidades.Tutor;
import fundacion.modelo.entidades.Usuario;
import java.io.Serializable;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "registrarUsuarioController")
@ViewScoped
public class RegistrarUsuarioController implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuario;
    
    @EJB
    private TipoDocumentoFacade tipoDocumentoFacade;
    
    private List<TipoDocumento> listaTipoDocumento;
    
    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;
    
    private List<TipoUsuario> listaTipoUsuario;
    
    @EJB
    private TutorFacade tutorFacade;
    
    private Tutor tutor;
    
    
    public RegistrarUsuarioController()  {
    }
    
    @PostConstruct
    
    public void init(){
        
        usuario = new Usuario();
        
        listaTipoDocumento = tipoDocumentoFacade.findAll();
        
        listaTipoUsuario = tipoUsuarioFacade.findAll();
        
        tutor = new Tutor();
        
    }
    
    public String createUser(){
        
        usuarioFacade.create(usuario);
        
        if (usuario.getIdtiposUsuarios().getIdtiposUsuarios() == 3) {
            Integer ultimoUsuario = usuarioFacade.obtenerUltimoIdUsuario();
            usuario.setIdusuario(ultimoUsuario);
            tutor.setIdusuario(usuario);
            
            tutorFacade.create(tutor);
        }
        
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Usuario creado con exito "));
        
        return "listaUsuario.xhtml?faces-redirect=true";
    
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<TipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public void setListaTipoDocumento(List<TipoDocumento> listaTipoDocumento) {
        this.listaTipoDocumento = listaTipoDocumento;
    }

    public List<TipoUsuario> getListaTipoUsuario() {
        return listaTipoUsuario;
    }

    public void setListaTipoUsuario(List<TipoUsuario> listaTipoUsuario) {
        this.listaTipoUsuario = listaTipoUsuario;
    }
    
    
    
}
