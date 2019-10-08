
package fundacion.controladores.tutor;

import fundacion.modelo.dao.TutorFacade;
import fundacion.modelo.entidades.Tutor;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "listaTutorController")
@SessionScoped
public class ListaTutorController implements Serializable {

    @EJB
    private TutorFacade tutorFacade;
    
    private List<Tutor> listaTutores;
    
    public ListaTutorController() {
    }

    public List<Tutor> getListaTutores() {
        listaTutores = tutorFacade.findAll();
        return listaTutores;
    }
    
    public String agregarTutor() {
        return "registroTutor.xhtml?faces-redirect=true";
    }
    
}
