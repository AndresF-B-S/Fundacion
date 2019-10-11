package fundacion.controladores.blog;

import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.faces.context.FacesContext;

@Named(value="blogController")
@RequestScoped
public class BlogController {

    public BlogController() {
    }
    
    public String getBlogPath() {
        String contextPath = FacesContext.getCurrentInstance()
                .getExternalContext().getRequestContextPath();
        contextPath = contextPath.replace("Fundacion", "blog");
        return contextPath;
    }

}
