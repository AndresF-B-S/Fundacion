package fundacion.contralodores.beneficiario;

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
import fundacion.utils.ArchivoUtils;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.enterprise.context.ConversationScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "verDetallesBeneficiarioController")
@ConversationScoped
public class VerDetallesBeneficiarioController implements Serializable {

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

    public VerDetallesBeneficiarioController() {
    }

    @PostConstruct

    public void init() {
        listaNivelEducativo = nivelEducativoFacade.findAll();

        listaTutores = tutorFacade.findAll();
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
        return "listaBeneficiarios.xhtml?faces-redirect=true";
    }

    public String prepararVerBeneficiario(Beneficiario beneficiario) {
        iniciarConversacion();
        beneficiarioSeleccionado = beneficiario;
        return "verDetalleBeneficiario.xhtml?faces-redirect=true";
    }

    public String volver() {
        return cancelar();
    }

    public Beneficiario getBeneficiarioSeleccionado() {
        return beneficiarioSeleccionado;
    }

    public void setBeneficiarioSeleccionado(Beneficiario beneficiarioSeleccionado) {
        this.beneficiarioSeleccionado = beneficiarioSeleccionado;
    }

    public DatoClinico getDatoClinico() {
        return datoClinico;
    }
    
    
    public List<NivelEducativo> getListaNivelEducativo() {
        return listaNivelEducativo;
    }

    public List<Tutor> getListaTutores() {
        return listaTutores;
    }
    
    public String getPathImagenBeneficiario(Usuario u) {
        return ArchivoUtils.obtenerRutaFoto(u);
    }

}
