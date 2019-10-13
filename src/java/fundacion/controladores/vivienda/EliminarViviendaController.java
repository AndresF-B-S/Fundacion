package fundacion.controladores.vivienda;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.ViviendaFacade;
import fundacion.modelo.entidades.Vivienda;
import fundacion.utils.MessageUtil;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.inject.Inject;


@Named(value = "eliminarViviendaController")
@ConversationScoped
public class EliminarViviendaController implements Serializable {

    @EJB
    private ViviendaFacade viviendaFacade;

    private Vivienda viviendaSeleccionada;

    @Inject
    private Conversation conversacion;
    
    @Inject
    private ScriptController script;

    public EliminarViviendaController() {
    }

    public void iniciarConversacon() {

        if (conversacion.isTransient()) {

            conversacion.begin();
        }

    }

    public void terminarConversacion() {
        if (!conversacion.isTransient()) {

            conversacion.end();

        }

    }

    public String cancelar() {
        terminarConversacion();
        
        return "listaViviendas.xhtml?faces-redirect=true";

    }
    
    public String prepararEliminar(Vivienda vivienda){
        iniciarConversacon();
        this.viviendaSeleccionada = vivienda;
        return"eliminarVivienda.xhtml?faces-redirect=true";

    }
    
    public String eliminar(){
        viviendaFacade.remove(viviendaSeleccionada);
        script.setScript(MessageUtil.ShowSuccessMessage("Vivienda eliminada con exito"));
        return cancelar();
    
    }

    public Vivienda getViviendaSeleccionada() {
        return viviendaSeleccionada;
    }

    public void setViviendaSeleccionada(Vivienda viviendaSeleccionada) {
        this.viviendaSeleccionada = viviendaSeleccionada;
    }
    
}
