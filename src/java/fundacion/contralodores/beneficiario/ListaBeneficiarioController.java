package fundacion.contralodores.beneficiario;

import fundacion.modelo.dao.BeneficiarioFacade;
import fundacion.modelo.entidades.Beneficiario;
import javax.inject.Named;
import java.io.Serializable;
import java.util.List;
import javax.ejb.EJB;
import javax.faces.view.ViewScoped;


@Named(value = "listaBeneficiarioController")
@ViewScoped
public class ListaBeneficiarioController implements Serializable {

    @EJB
    private BeneficiarioFacade beneficiarioFacade;
    
    private List<Beneficiario> listaBeneficiario;
    
    private Beneficiario beneficiarioSeleccionado;
    
    
    public ListaBeneficiarioController() {
    }

    public List<Beneficiario> getListaBeneficiario() {
        listaBeneficiario = beneficiarioFacade.findAll();
        return listaBeneficiario;
    }

    public String agregarBeneficiario() {
        return "registroBeneficiario.xhtml?faces-redirect=true";
    }
    
    
}
