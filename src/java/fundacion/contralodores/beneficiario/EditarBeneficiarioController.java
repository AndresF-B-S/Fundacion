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

    private Part imagenBeneficiario;

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

    public String cancelar() {
        terminarConversacion();
        return "listaBeneficiarios.xhtml?faces-redirect=true";

    }

    public String prepararEditar(Beneficiario beneficiario) {
        iniciarConvesacion();
        this.beneficiarioSeleccionado = beneficiario;
        return "editarBeneficiario.xhtml?faces-redirect=true";

    }

    public String editar() {
        beneficiarioFacade.edit(beneficiarioSeleccionado);
        usuarioFacade.edit(beneficiarioSeleccionado.getUsuario());
        datoClinicoFacade.edit(beneficiarioSeleccionado.getDatosClinicosId());
        nivelEducativoFacade.edit(beneficiarioSeleccionado.getIdnivelEducativo());
        script.setScript(MessageUtil.ShowSuccessMessage("Beneficiario editado con exito"));
        return cancelar();
    }

    public String cambiarImagen() throws IOException {
        Usuario usuarioAsociado = beneficiarioSeleccionado.getUsuario();
        
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("build" + File.separator, "");
        String extension = ArchivoUtils.obtenerExtensionImagen(imagenBeneficiario.getSubmittedFileName());
        String nombreArchivo = ArchivoUtils.crearNombreDeArchivoUsuario(usuarioAsociado, extension);
        ruta = ruta + "resources" + File.separator + "images" + File.separator + "usuario" + File.separator + nombreArchivo;

        usuarioAsociado.setRutaFoto(nombreArchivo);
        ArchivoUtils.guardarFoto(imagenBeneficiario, ruta);

        usuarioFacade.edit(usuarioAsociado);
        
        script.setScript(MessageUtil.ShowSuccessMessage("Foto del beneficiario editada con exito"));

        return "";
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

    public Part getImagenBeneficiario() {
        return imagenBeneficiario;
    }

    public void setImagenBeneficiario(Part imagenBeneficiario) {
        this.imagenBeneficiario = imagenBeneficiario;
    }

    public String obtenerRutaFoto() {
        Usuario usuarioAsociado = beneficiarioSeleccionado.getUsuario();
        return ArchivoUtils.obtenerRutaFoto(usuarioAsociado);
    }

}
