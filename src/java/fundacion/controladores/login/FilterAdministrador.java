package fundacion.controladores.login;

import java.io.IOException;
import javax.inject.Inject;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebFilter(filterName = "FilterAdministrador", urlPatterns = {"/app/*"})
public class FilterAdministrador implements Filter {

    private static final boolean debug = true;

    @Inject
    private ControladorInicioSesion sc;

    public FilterAdministrador() {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response,
            FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest hsrequest = (HttpServletRequest) request;
        HttpServletResponse hsresponse = (HttpServletResponse) response;
        if (sc.getUsuario()!= null && sc.getUsuario().getIdtiposUsuarios().getIdtiposUsuarios() == 1) {
            chain.doFilter(request, response);
        } else {
            hsresponse.sendRedirect(hsrequest.getContextPath() + "/index.xhtml");
        }
    }

}
