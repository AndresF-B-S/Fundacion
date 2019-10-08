/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.controladores.tutor;

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
@Named(value = "editarTutorController")
@ConversationScoped
public class EditarTutorController implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Tutor tutorEditable;
    
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
    
    private Part imagenTutor;

    public EditarTutorController() {
        
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
        return "listaTutor.xhtml";
    
    }
    
    public String prepararEditar(Tutor usuario){
        iniciarConversacion();
        this.tutorEditable = usuario;
        return "editarTutor.xhtml";

    }
    
    public String editar(){
        usuarioFacade.edit(tutorEditable.getIdusuario());
        return cancelar();
    
    }
    
    public String editarFoto() throws IOException {
        Usuario usuarioAsociado = tutorEditable.getIdusuario();
        
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("build" + File.separator, "");
        String extension = ArchivoUtils.obtenerExtensionImagen(imagenTutor.getSubmittedFileName());
        String nombreArchivo = ArchivoUtils.crearNombreDeArchivoUsuario(usuarioAsociado, extension);
        ruta = ruta + "resources" + File.separator + "images" + File.separator + "usuario" + File.separator + nombreArchivo;

        usuarioAsociado.setRutaFoto(nombreArchivo);
        ArchivoUtils.guardarFoto(imagenTutor, ruta);
        
        usuarioFacade.edit(usuarioAsociado);
        
        script.setScript(MessageUtil.ShowSuccessMessage("Foto del usuario editada con exito"));
        
        return "";
    }

    public Tutor getTutorEditable() {
        return tutorEditable;
    }

    public void setTutorEditable(Tutor tutorEditable) {
        this.tutorEditable = tutorEditable;
    }

    public List<TipoDocumento> getListaTipoDocumento() {
        return listaTipoDocumento;
    }

    public List<TipoUsuario> getListaTipoUsuario() {
        return listaTipoUsuario;
    }

    public Part getImagenTutor() {
        return imagenTutor;
    }

    public void setImagenTutor(Part imagenTutor) {
        this.imagenTutor = imagenTutor;
    }
    
    public String obtenerRutaFoto() {
        return ArchivoUtils.obtenerRutaFoto(tutorEditable.getIdusuario());
    }
}
