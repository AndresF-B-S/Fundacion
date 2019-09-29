package fundacion.contralodores.beneficiario;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.BeneficiarioFacade;
import fundacion.modelo.dao.DatoClinicoFacade;
import fundacion.modelo.dao.NivelEducativoFacade;
import fundacion.modelo.dao.TutorFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.Beneficiario;
import fundacion.modelo.entidades.DatoClinico;
import fundacion.modelo.entidades.NivelEducativo;
import fundacion.modelo.entidades.Tutor;
import fundacion.modelo.entidades.Usuario;
import fundacion.utils.MessageUtil;
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
@Named(value = "editarBeneficiarioController")
@ConversationScoped
public class EditarBeneficiarioController implements Serializable {

    @EJB
    private BeneficiarioFacade beneficiarioFacade;

    private Beneficiario beneficiarioSeleccionado;

    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;

    @EJB

    private DatoClinicoFacade datoClinicoFacade;

    private DatoClinico datoClinico;

    @EJB
    private NivelEducativoFacade nivelEducativoFacade;

    private List<NivelEducativo> listaNivelEducativo;

    @EJB
    private TutorFacade tutorFacade;

    private List<Tutor> listaTutores;

    @Inject
    Conversation conversacion;
    
    @Inject
    private ScriptController script;

    public EditarBeneficiarioController() {
    }

    @PostConstruct

    public void init() {
        listaNivelEducativo = nivelEducativoFacade.findAll();

        listaTutores = tutorFacade.findAll();

    }

    public void iniciarConvesacion() {
        if (conversacion.isTransient()) {
            conversacion.begin();
        }
    }

    public void terminarConversacion() {
        if (!conversacion.isTransient()) {
            conversacion.end();
        }

    }
    
    public String cancelar(){
        terminarConversacion();        
        return "listaBeneficiarios.xhtml?faces-redirect=true";
    
    }
    
    public String prepararEditar(Beneficiario beneficiario){
        iniciarConvesacion();
        this.beneficiarioSeleccionado = beneficiario;
        return"editarBeneficiario.xhtml?faces-redirect=true";
    
    }
    
    public String editar(){
        beneficiarioFacade.edit(beneficiarioSeleccionado);
        script.setScript(MessageUtil.ShowSuccessMessage("Beneficiario editado con exito"));
        return cancelar();
    }

    public Beneficiario getBeneficiarioSeleccionado() {
        return beneficiarioSeleccionado;
    }

    public void setBeneficiarioSeleccionado(Beneficiario beneficiarioSeleccionado) {
        this.beneficiarioSeleccionado = beneficiarioSeleccionado;
    }

    public List<NivelEducativo> getListaNivelEducativo() {
        return listaNivelEducativo;
    }

    public List<Tutor> getListaTutor() {
        return listaTutores;
    }
    
    

}
