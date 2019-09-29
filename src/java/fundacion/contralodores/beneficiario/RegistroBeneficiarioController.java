package fundacion.contralodores.beneficiario;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.BeneficiarioFacade;
import fundacion.modelo.dao.DatoClinicoFacade;
import fundacion.modelo.dao.NivelEducativoFacade;
import fundacion.modelo.dao.TipoUsuarioFacade;
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
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.inject.Named;
import javax.faces.view.ViewScoped;
import javax.inject.Inject;
import javax.servlet.ServletContext;
import javax.servlet.http.Part;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "registroBeneficiarioController")
@ViewScoped
public class RegistroBeneficiarioController implements Serializable {

    @EJB
    private BeneficiarioFacade beneficiarioFacade;

    private Beneficiario beneficiario;

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

    private List<Tutor> listaTurores;

    @EJB
    private TipoUsuarioFacade tipoUsuarioFacade;

    private Part imagenBeneficiario;

    @Inject
    private ScriptController script;

    public RegistroBeneficiarioController() {
    }

    @PostConstruct

    public void init() {

        usuario = new Usuario();

        beneficiario = new Beneficiario();

        listaNivelEducativo = nivelEducativoFacade.findAll();

        datoClinico = new DatoClinico();

        listaTurores = tutorFacade.findAll();

    }

    public String registrarBeneficiario() {
        
        try {

            usuario.setClave("");

            usuario.setIdtiposUsuarios(tipoUsuarioFacade.find(3));
            
            String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("build" + File.separator, "");
            String extension = ArchivoUtils.obtenerExtensionImagen(imagenBeneficiario.getSubmittedFileName());
            String nombreArchivo = ArchivoUtils.crearNombreDeArchivoUsuario(usuario, extension);
            ruta = ruta + "resources" + File.separator + "images" + File.separator + "usuario" + File.separator + nombreArchivo;

            usuario.setRutaFoto(nombreArchivo);
            ArchivoUtils.guardarFoto(imagenBeneficiario, ruta);

            usuarioFacade.create(usuario);

            Integer ultimoUsuarioId = usuarioFacade.obtenerUltimoIdUsuario();

            beneficiario.setUsuariosIdusuario(ultimoUsuarioId);

            usuario.setIdusuario(ultimoUsuarioId);

            beneficiario.setUsuario(usuario);

            datoClinicoFacade.create(datoClinico);

            Integer ultimoDatoCinicoId = datoClinicoFacade.obtenerUltimoIdDatoClinico();

            datoClinico.setId(ultimoDatoCinicoId);

            beneficiario.setDatosClinicosId(datoClinico);
            
            beneficiarioFacade.create(beneficiario);

            script.setScript(MessageUtil.ShowSuccessMessage("Beneficiario creado con exito"));
            
            usuario = new Usuario();
            beneficiario = new Beneficiario();
            datoClinico = new DatoClinico();

            return "listaBeneficiarios.xhtml?faces-redirect=true";
        } catch (Exception e) {
            e.printStackTrace(System.err);
            script.setScript(MessageUtil.ShowExceptionMessage(e));
            return "";
        }

    }

    public Beneficiario getBeneficiario() {
        return beneficiario;
    }

    public void setBeneficiario(Beneficiario beneficiario) {
        this.beneficiario = beneficiario;
    }

    public List<NivelEducativo> getListaNivelEducativo() {
        return listaNivelEducativo;
    }

    public DatoClinico getDatoClinico() {
        return datoClinico;
    }

    public void setDatoClinico(DatoClinico datoClinico) {
        this.datoClinico = datoClinico;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public List<Tutor> getListaTurores() {
        return listaTurores;
    }

    public Part getImagenBeneficiario() {
        return imagenBeneficiario;
    }

    public void setImagenBeneficiario(Part imagenBeneficiario) {
        this.imagenBeneficiario = imagenBeneficiario;
    }

}
