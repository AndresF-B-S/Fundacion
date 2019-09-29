package fundacion.controladores.vivienda;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.MaterialViviendaFacade;
import fundacion.modelo.dao.TechoFacade;
import fundacion.modelo.dao.TipoViviendaFacade;
import fundacion.modelo.dao.ViviendaFacade;
import fundacion.modelo.entidades.MaterialVivienda;
import fundacion.modelo.entidades.Techo;
import fundacion.modelo.entidades.TipoVivienda;
import fundacion.modelo.entidades.Vivienda;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "editarViviendaController")
@ConversationScoped
public class EditarViviendaController implements Serializable {

    @EJB
    private ViviendaFacade viviendaFacade;

    private Vivienda viviendaSeleccionada;

    @EJB
    private TipoViviendaFacade tipoViviendaFacade;

    private List<TipoVivienda> listaTipoVivienda;

    @EJB
    private MaterialViviendaFacade materialViviendaFacade;

    private List<MaterialVivienda> listaMaterialVivienda;

    @EJB
    private TechoFacade techoFacade;

    private List<Techo> listaTecho;

    @Inject
    private Conversation conversacion;
    
    @Inject
    private ScriptController script;

    public EditarViviendaController() {
    }

    @PostConstruct
    public void init() {

        listaTipoVivienda = tipoViviendaFacade.findAll();

        listaMaterialVivienda = materialViviendaFacade.findAll();

        listaTecho = techoFacade.findAll();

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
        script.setScript("swal.fire({"
                    + "type: 'success', "
                    + "title: 'Exito', "
                    + "text: 'Se editó la vivienda', "
                    + "footer: ''});");
        return "listaViviendas.xhtml?faces-redirect=true";

    }
    
    public String prepararEditar(Vivienda vivienda){
        iniciarConversacon();
        this.viviendaSeleccionada = vivienda;
        return"editarVivienda.xhtml?faces-redirect=true";

    }
    
    public String editar(){
        viviendaFacade.edit(viviendaSeleccionada);
        return cancelar();
    
    }

    public Vivienda getViviendaSeleccionada() {
        return viviendaSeleccionada;
    }

    public void setViviendaSeleccionada(Vivienda viviendaSeleccionada) {
        this.viviendaSeleccionada = viviendaSeleccionada;
    }

    public List<TipoVivienda> getListaTipoVivienda() {
        return listaTipoVivienda;
    }

    public List<MaterialVivienda> getListaMaterialVivienda() {
        return listaMaterialVivienda;
    }

    public List<Techo> getListaTecho() {
        return listaTecho;
    }
    
    
    
    
}
