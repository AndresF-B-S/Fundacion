package fundacion.contralodores.beneficiario;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.BeneficiarioFacade;
import fundacion.modelo.dao.DatoClinicoFacade;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.dao.ViviendaFacade;
import fundacion.modelo.entidades.Beneficiario;
import fundacion.modelo.entidades.Usuario;
import fundacion.modelo.entidades.Vivienda;
import fundacion.utils.MessageUtil;
import javax.inject.Named;
import javax.enterprise.context.ConversationScoped;
import java.io.Serializable;
import javax.ejb.EJB;
import javax.enterprise.context.Conversation;
import javax.inject.Inject;

@Named(value = "eliminarBeneficiarioController")
@ConversationScoped
public class EliminarBeneficiarioController implements Serializable {

    @EJB
    private BeneficiarioFacade beneficiarioFacade;

    private Beneficiario beneficiarioSeleccionado;

    @EJB
    private UsuarioFacade usuarioFacade;

    @EJB
    private DatoClinicoFacade datoClinicoFacade;

    @EJB
    private ViviendaFacade viviendaFacade;

    @Inject
    Conversation conversacion;

    @Inject
    private ScriptController script;

    public EliminarBeneficiarioController() {
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

    public String cancelar() {
        terminarConversacion();
        return "listaBeneficiarios.xhtml?faces-redirect=true";

    }

    public String prepararEliminar(Beneficiario beneficiario) {
        iniciarConvesacion();
        this.beneficiarioSeleccionado = beneficiario;
        return "eliminarBeneficiario.xhtml?faces-redirect=true";

    }

    public String eliminar() {
        if (beneficiarioSeleccionado.getViviendaList() != null || !beneficiarioSeleccionado.getViviendaList().isEmpty()) {
            for (Vivienda v : beneficiarioSeleccionado.getViviendaList()) {
                viviendaFacade.remove(v);
                System.out.println("se elimino vivienda");
            }
        }

        datoClinicoFacade.remove(beneficiarioSeleccionado.getDatosClinicosId());        
        beneficiarioFacade.remove(beneficiarioSeleccionado);
        usuarioFacade.remove(usuarioFacade.find(beneficiarioSeleccionado.getUsuario().getIdusuario()));
        script.setScript(MessageUtil.ShowSuccessMessage("Beneficiario eliminado con exito"));
        
        return cancelar();
    }

    public Beneficiario getBeneficiarioSeleccionado() {
        return beneficiarioSeleccionado;
    }

    public void setBeneficiarioSeleccionado(Beneficiario beneficiarioSeleccionado) {
        this.beneficiarioSeleccionado = beneficiarioSeleccionado;
    }

}
