
package fundacion.controladores.vivienda;

import fundacion.modelo.dao.ViviendaFacade;
import fundacion.modelo.entidades.Vivienda;
import java.io.Serializable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.faces.view.ViewScoped;


@Named(value = "listaViviendaController")
@ViewScoped
public class ListaViviendaController implements Serializable{

    @EJB
    private ViviendaFacade viviendaFacade;
    
    private List<Vivienda> listaVivienda;
          
    public ListaViviendaController() {
    }
    
    @PostConstruct
    
    public void init(){
        
        listaVivienda = viviendaFacade.findAll();
    
    }

    public List<Vivienda> getListaVivienda() {
        return listaVivienda;
    }
    
    
    
}
