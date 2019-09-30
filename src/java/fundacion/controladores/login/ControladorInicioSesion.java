package fundacion.controladores.login;

import fundacion.contralodores.script.ScriptController;
import fundacion.modelo.dao.UsuarioFacade;
import fundacion.modelo.entidades.Usuario;
import fundacion.utils.ArchivoUtils;
import fundacion.utils.MessageUtil;
import java.io.File;
import java.io.IOException;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.Part;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "controladorInicioSesion")
@SessionScoped
public class ControladorInicioSesion implements Serializable {

    @EJB
    private UsuarioFacade usuarioFacade;

    private Usuario usuario;

    private int documento;

    private String clave;
    
    private Part imagenUsuario;
    
    @Inject
    
    private ScriptController script;

    public String iniciarSesion() {
        usuario = usuarioFacade.iniciarSesion(documento, clave);

        if (usuario != null) {
            script.setScript(MessageUtil.ShowSuccessMessage("Bienvenido " + usuario.getNombres() + " " + usuario.getApellidos()));
            return "/app/dashboard.xhtml?faces-redirect=true";

        } else {
            script.setScript(MessageUtil.ShowErrorMessage("Usuario incorrecto o inexistente"));
            return "";

        }

    }

    public void cerrarSesion() {
        FacesContext fc = FacesContext.getCurrentInstance();
        ExternalContext ec = fc.getExternalContext();
        ec.invalidateSession();
        this.documento = 0;
        this.clave = "";
        this.usuario = null;

        try {
            ec.redirect(ec.getRequestContextPath() + "/index.xhtml?faces-redirect=true");
        } catch (IOException ex) {
            Logger.getLogger(ControladorInicioSesion.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    public boolean isValido() {
        if (usuario != null) {

            return true;
        }
        return false;
    }

    public void validarSesion() {
        if (!isValido()) {

            cerrarSesion();

        }

    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public int getDocumento() {
        return documento;
    }

    public void setDocumento(int documento) {
        this.documento = documento;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public Part getImagenUsuario() {
        return imagenUsuario;
    }

    public void setImagenUsuario(Part imagenUsuario) {
        this.imagenUsuario = imagenUsuario;
    }
    
    public String obtenerRutaFoto() {
        if (usuario == null) usuario = new Usuario();
        return ArchivoUtils.obtenerRutaFoto(usuario);
    }
    
    public String cambiarFoto() throws IOException {
        String ruta = FacesContext.getCurrentInstance().getExternalContext().getRealPath("/").replace("build" + File.separator, "");
        String extension = ArchivoUtils.obtenerExtensionImagen(imagenUsuario.getSubmittedFileName());
        String nombreArchivo = ArchivoUtils.crearNombreDeArchivoUsuario(usuario, extension);
        ruta = ruta + "resources" + File.separator + "images" + File.separator + "usuario" + File.separator + nombreArchivo;

        usuario.setRutaFoto(nombreArchivo);
        ArchivoUtils.guardarFoto(imagenUsuario, ruta);
        
        usuarioFacade.edit(usuario);
        
        script.setScript(MessageUtil.ShowSuccessMessage("Foto del usuario editada con exito"));
        
        return "cambiarFoto.xhtml?faces-redirect=true";
    }

}
