package fundacion.contralodores.beneficiario;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.BeneficiarioFacade;
import fundacion.modelo.dao.MaterialViviendaFacade;
import fundacion.modelo.dao.TechoFacade;
import fundacion.modelo.dao.TipoViviendaFacade;
import fundacion.modelo.dao.ViviendaFacade;
import fundacion.modelo.entidades.Beneficiario;
import fundacion.modelo.entidades.MaterialVivienda;
import fundacion.modelo.entidades.Techo;
import fundacion.modelo.entidades.TipoVivienda;
import fundacion.modelo.entidades.Vivienda;
import fundacion.utils.MessageUtil;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;


@Named(value = "agregarViviendaController")
@SessionScoped
public class AgregarViviendaController implements Serializable {

    @EJB

    private BeneficiarioFacade beneficiarioFacade;

    private Beneficiario beneficiarioSeleccionado;

    @EJB
    private ViviendaFacade viviendaFacade;

    private Vivienda vivienda;

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

    private List<Vivienda> listaVivienda = new ArrayList<>();
    
    @Inject
    private ScriptController script;

    public AgregarViviendaController() {

    }

    @PostConstruct

    public void init() {

        vivienda = new Vivienda();

        listaTipoVivienda = tipoViviendaFacade.findAll();

        listaMaterialVivienda = materialViviendaFacade.findAll();

        listaTecho = techoFacade.findAll();

    }

    public void iniciarConversacion() {
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
        return "listaBeneficiarios.xhtml";

    }

    public String agregar() {

        vivienda.setIdusuarioBeneficiario(beneficiarioSeleccionado);
        viviendaFacade.create(vivienda);
        listaVivienda.add(vivienda);
        vivienda = new Vivienda();
        script.setScript(MessageUtil.ShowSuccessMessage("Vivienda creada con exito"));
        return cancelar();

    }

    public String preparar(Beneficiario beneficiario) {

        iniciarConversacion();
        this.beneficiarioSeleccionado = beneficiario;
        return "agregarVivienda.xhtml?faces-redirect=true";

    }

    public Beneficiario getBeneficiarioSeleccionado() {
        return beneficiarioSeleccionado;
    }

    public void setBeneficiarioSeleccionado(Beneficiario beneficiarioSeleccionado) {
        this.beneficiarioSeleccionado = beneficiarioSeleccionado;
    }

    public Vivienda getVivienda() {
        return vivienda;
    }

    public void setVivienda(Vivienda vivienda) {
        this.vivienda = vivienda;
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

    public List<Vivienda> getListaVivienda() {
//        return listaVivienda;
        return viviendaFacade.findAll();
    }

    public void setListaVivienda(List<Vivienda> listaVivienda) {
        this.listaVivienda = listaVivienda;
    }
    
    public String obtenerDistribucionVivienda(Vivienda v) {
        if (v == null)
            return "";
        
        String distribucion = "";
        
        if (v.getNumerodormitorios() != null)
            distribucion += v.getNumerodormitorios() + " dormitorios";
        
        if (v.getComedor() != null)
            if (v.getComedor() == 1)
                distribucion += "<br/>Comedor";
        
        if (v.getCocina() != null)
            if (v.getCocina()== 1)
                distribucion += "<br/>Cocina";
        
        if (v.getBanioprivado() != null)
            if (v.getBanioprivado()== 1)
                distribucion += "<br/>Baño privado";
        
        if (v.getBaniocolectivo() != null)
            if (v.getBaniocolectivo()== 1)
                distribucion += "<br/>Baño colectivo";
        
        return distribucion;
    }

}
