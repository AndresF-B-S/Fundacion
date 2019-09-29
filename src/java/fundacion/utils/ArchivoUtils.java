/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fundacion.utils;

import fundacion.modelo.entidades.Usuario;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;
import javax.faces.context.FacesContext;
import javax.servlet.http.Part;

/**
 *
 * @author Andres F.B.S
 */
public class ArchivoUtils {

    public static void guardarFoto(Part archivo, String ruta) throws IOException {
        File fileImage = new File(ruta);
        try (InputStream in = archivo.getInputStream()) {
            try (OutputStream out = new FileOutputStream(fileImage)) {
                int read = 0;
                byte[] bytes = new byte[1024];

                while ((read = in.read(bytes)) != -1) {
                    out.write(bytes, 0, read);
                }
                out.flush();
            }
        }
    }
    
    public static String obtenerExtensionImagen(String nombreArchivo) {
        String[] partes = nombreArchivo.split("\\.");        
        String extension = partes[partes.length-1];
        return extension;
    }
    
    public static String crearNombreDeArchivoUsuario(Usuario usuario, String extension) {
        String uuid = UUID.randomUUID().toString().split("\\-")[0];
        String nombreArchivo = usuario.getNombres().trim().replace(" ", "-").toLowerCase() + "-" 
                + usuario.getApellidos().trim().replace(" ", "-").toLowerCase() 
                + "-" + uuid + "." + extension;
        return nombreArchivo;
    }
    
    public static String obtenerRutaFoto(Usuario usuario) {
        String path = FacesContext.getCurrentInstance().
            getExternalContext().getRequestContextPath()  + "/resources/images/usuario/";
        
        if (usuario.getRutaFoto() == null || usuario.getRutaFoto().length() == 0)
            return path + "user.png";
        
        return path + usuario.getRutaFoto();
    }
}
