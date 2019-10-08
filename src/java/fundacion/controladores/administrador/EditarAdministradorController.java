/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.controladores.administrador;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.dao.TipoUsuarioFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.TipoDocumento;
import fundacion.modelo.entidades.TipoUsuario;
import fundacion.modelo.entidades.Usuario;
import fundacion.utils.ArchivoUtils;
import fundacion.utils.MessageUtil;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "editarAdministradorController")
@ConversationScoped
public class EditarAdministradorController implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuarioEditable;
    
    @EJB
    private TipoDocumentoFacade tipoDocumentoFacade;
    
    private List<TipoDocumento> listaTipoDocumento;
    
    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;
    
    private List<TipoUsuario> listaTipoUsuario;
    
    @Inject
    private Conversation conversacion;
    
    @Inject
    private ScriptController script;
    
    private Part imagenUsuario;

    public EditarAdministradorController() {
        
    }
    
    @PostConstruct
    
    public void init(){
        listaTipoDocumento = tipoDocumentoFacade.findAll();
        
        listaTipoUsuario = tipoUsuarioFacade.findAll();
    }
    
    public void iniciarConversacion(){
        if (conversacion.isTransient()) {
            conversacion.begin();
            
        }
    }
    
    public void terminarConversacion(){
        if (!conversacion.isTransient()) {
            conversacion.end();
            
        }
    
    }
    
    public String cancelar(){
        terminarConversacion();
        return "listaAdministrador.xhtml";
    
    }
    
    public String prepararEditar(Usuario usuario){
        iniciarConversacion();
        this.usuarioEditable = usuario;
        return "editarAdministrador.xhtml";

    }
    
    public String editar(){
        usuarioFacade.edit(usuarioEditable);
        return cancelar();
    
    }
    
    public String editarFoto() throws IOException {
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("build" + File.separator, "");
        String extension = ArchivoUtils.obtenerExtensionImagen(imagenUsuario.getSubmittedFileName());
        String nombreArchivo = ArchivoUtils.crearNombreDeArchivoUsuario(usuarioEditable, extension);
        ruta = ruta + "resources" + File.separator + "images" + File.separator + "usuario" + File.separator + nombreArchivo;

        usuarioEditable.setRutaFoto(nombreArchivo);
        ArchivoUtils.guardarFoto(imagenUsuario, ruta);
        
        usuarioFacade.edit(usuarioEditable);
        
        script.setScript(MessageUtil.ShowSuccessMessage("Foto del usuario editada con exito"));
        
        return "";
    }

    public Usuario getUsuarioEditable() {
        return usuarioEditable;
    }

    public void setUsuarioEditable(Usuario usuarioEditable) {
        this.usuarioEditable = usuarioEditable;
    }

    public List<TipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public List<TipoUsuario> getListaTipoUsuario() {
        return listaTipoUsuario;
    }

    public Part getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(Part imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }
    
    public String obtenerRutaFoto() {
        return ArchivoUtils.obtenerRutaFoto(usuarioEditable);
    }
}
