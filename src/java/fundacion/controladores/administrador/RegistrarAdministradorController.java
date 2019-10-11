package fundacion.controladores.administrador;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.dao.TipoUsuarioFacade;
import fundacion.modelo.dao.TutorFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.TipoDocumento;
import fundacion.modelo.entidades.TipoUsuario;
import fundacion.modelo.entidades.Tutor;
import fundacion.modelo.entidades.Usuario;
import fundacion.utils.ArchivoUtils;
import fundacion.utils.MessageUtil;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.Part;


@Named(value = "registrarAdministradorController")
@ViewScoped
public class RegistrarAdministradorController implements Serializable {
    
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
    
    @Inject
    private ScriptController script;
    
    private Tutor tutor;
    
    private Part imagenUsuario;
    
    public RegistrarAdministradorController()  {
    }
    
    @PostConstruct
    
    public void init(){
        
        usuario = new Usuario();
        
        listaTipoDocumento = tipoDocumentoFacade.findAll();
        
        listaTipoUsuario = tipoUsuarioFacade.findAll();
        
        tutor = new Tutor();
        
    }
    
    public String createUser() throws IOException{
        
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("build" + File.separator, "");
        String extension = ArchivoUtils.obtenerExtensionImagen(imagenUsuario.getSubmittedFileName());
        String nombreArchivo = ArchivoUtils.crearNombreDeArchivoUsuario(usuario, extension);
        ruta = ruta + "resources" + File.separator + "images" + File.separator + "usuario" + File.separator + nombreArchivo;

        usuario.setRutaFoto(nombreArchivo);
        ArchivoUtils.guardarFoto(imagenUsuario, ruta);
        
        usuario.setIdtiposUsuarios(tipoUsuarioFacade.find(1));
        usuarioFacade.create(usuario);
        
        script.setScript(MessageUtil.ShowSuccessMessage("Administrador creado con exito"));
        
        return "listaAdministrador.xhtml?faces-redirect=true";
    
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

    public Part getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(Part imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }
    
}
