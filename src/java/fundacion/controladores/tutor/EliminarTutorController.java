/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.controladores.tutor;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.TutorFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.Tutor;
import fundacion.utils.ArchivoUtils;
import fundacion.utils.MessageUtil;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;


@Named(value = "eliminarTutorController")
@ConversationScoped
public class EliminarTutorController implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    @EJB
    private TutorFacade tutorFacade;
    
    private Tutor tutorEditable;
    
    @Inject
    private Conversation conversacion;
    
    @Inject
    private ScriptController script;

    public EliminarTutorController() {
        
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
        return "listaTutor.xhtml?faces-redirect=true";
    
    }
    
    public String prepararEliminar(Tutor usuario){
        iniciarConversacion();
        this.tutorEditable = usuario;
        return "eliminarTutor.xhtml?faces-redirect=true";

    }
    
    public String eliminar(){
        tutorFacade.remove(tutorEditable);
        usuarioFacade.remove(usuarioFacade.find(tutorEditable.getIdusuario().getIdusuario()));
        script.setScript(MessageUtil.ShowSuccessMessage("Tutor eliminado con exito"));
        return cancelar();
    
    }

    public String obtenerRutaFoto() {
        return ArchivoUtils.obtenerRutaFoto(tutorEditable.getIdusuario());
    }
    
    public Tutor getTutorEditable() {
        return tutorEditable;
    }

    public void setTutorEditable(Tutor tutorEditable) {
        this.tutorEditable = tutorEditable;
    }
    
}
