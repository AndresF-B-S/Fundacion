/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.controladores.correo;

import fundacion.utils.Mail;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;


@Named(value = "correoController")
@RequestScoped
public class CorreoController {

    private String nombres;
    private String apellidos;
    private String email;
    private String tipoSolicitud;
    private String mensaje;
    
    public CorreoController() {
    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getTipoSolicitud() {
        return tipoSolicitud;
    }

    public void setTipoSolicitud(String tipoSolicitud) {
        this.tipoSolicitud = tipoSolicitud;
    }
    
    public String enviarCorreos() {
        String mensajeAFundacion = nombres + " " + apellidos + " se interesó en " + tipoSolicitud + "<br/>"
                + "Su correo es <strong>" + email + "</strong><br/>"
                + "Este es su mensaje<br/>"
                + mensaje,
                mensajeAInteresado = "Buen dia " + nombres + " " + apellidos + "<br/>",
                asuntoAInteresado = "Información sobre ";
        
        switch (tipoSolicitud) {
            case "informacion":
                mensajeAInteresado += "Esta es la información de la fundacion"; // TODO: completar la información para la fundación
                asuntoAInteresado += "la fundacion";
                break;
            case "donacion":
                mensajeAInteresado += "Esta es la información acerca de las donaciones";  // TODO: completar la información para la danacion
                asuntoAInteresado += "las donaciones";
                break;
        }
        
        try {
            // este se le envia a la fundacion
            Mail.sendMail("fundacionpasosdefe01@gmail.com", "Hay un interesado en la fundacion", mensajeAFundacion);
            // este es para el interesado
            Mail.sendMail(email, asuntoAInteresado, mensajeAInteresado);
        } catch (Exception ex) {
            //
        }
        return "";
    }
}
