/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.controladores.usuarios;

import fundacion.modelo.dao.TipoDocumentoFacade;
import fundacion.modelo.dao.TipoUsuarioFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.TipoDocumento;
import fundacion.modelo.entidades.TipoUsuario;
import fundacion.modelo.entidades.Usuario;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "editarUsuarioController")
@ConversationScoped
public class EditarUsuarioController implements Serializable {
    
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
    

    public EditarUsuarioController() {
        
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
        return "userList.xhtml";
    
    }
    
    public String prepararEditar(Usuario usuario){
        iniciarConversacion();
        this.usuarioEditable = usuario;
        return "editarUsuario.xhtml";

    }
    
    public String editar(){
        usuarioFacade.edit(usuarioEditable);
        return cancelar();
    
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
    
    
}
