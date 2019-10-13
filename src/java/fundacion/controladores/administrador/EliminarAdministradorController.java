/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.controladores.administrador;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.Usuario;
import fundacion.utils.ArchivoUtils;
import fundacion.utils.MessageUtil;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;


@Named(value = "eliminarAdministradorController")
@ConversationScoped
public class EliminarAdministradorController implements Serializable {
    
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private Usuario usuarioEditable;
    
    @Inject
    private Conversation conversacion;
    
    @Inject
    private ScriptController script;

    public EliminarAdministradorController() {        
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
        return "listaAdministrador.xhtml?faces-redirect=true";
    
    }
    
    public String prepararEliminar(Usuario usuario){
        iniciarConversacion();
        this.usuarioEditable = usuario;
        return "eliminarAdministrador.xhtml?faces-redirect=true";
    }
    
    public String eliminar(){
        usuarioFacade.remove(usuarioEditable);
        script.setScript(MessageUtil.ShowSuccessMessage("Administrador eliminado con exito"));
        return cancelar();
    
    }
    
    public Usuario getUsuarioEditable() {
        return usuarioEditable;
    }

    public void setUsuarioEditable(Usuario usuarioEditable) {
        this.usuarioEditable = usuarioEditable;
    }
    
    public String obtenerRutaFoto() {
        return ArchivoUtils.obtenerRutaFoto(usuarioEditable);
    }
}
