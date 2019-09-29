package fundacion.contralodores.beneficiario;

import fundacion.modelo.dao.BeneficiarioFacade;
import fundacion.modelo.entidades.Beneficiario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewScoped;

/**
 *
 * @author Andres F.B.S
 */
@Named(value = "listaBeneficiarioController")
@ViewScoped
public class ListaBeneficiarioController implements Serializable {

    @EJB
    private BeneficiarioFacade beneficiarioFacade;
    
    private List<Beneficiario> listaBeneficiario;
    
    private Beneficiario beneficiarioSeleccionado;
    
    
    public ListaBeneficiarioController() {
    }
    
    @PostConstruct
    
    public void init(){
        
        listaBeneficiario = beneficiarioFacade.findAll();
        
    }
    
    

    public List<Beneficiario> getListaBeneficiario() {
        return listaBeneficiario;
    }

    public String agregarBeneficiario() {
        return "registroBeneficiario.xhtml?faces-redirect=true";
    }
    
    
}
