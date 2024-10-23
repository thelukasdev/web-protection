package Output;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletResponse;

@WebFilter("/*")
public class RedirectFilter implements Filter {


    private static final String[] ALLOWED_HOSTS = {"example.com", "allowed-website.com"};

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Überprüfen, ob es sich um eine Weiterleitung handelt
        if (response instanceof HttpServletResponse) {
            HttpServletResponse httpResponse = (HttpServletResponse) response;

            // Die Ziel-URL der Weiterleitung überprüfen
            String targetUrl = httpResponse.getHeader("Location");
            if (targetUrl != null && !isAllowedHost(targetUrl)) {
                // Ziel-URL ist nicht erlaubt, Weiterleitung verhindern
                httpResponse.sendError(HttpServletResponse.SC_FORBIDDEN, "Verbotene Weiterleitung");
                return;
            }
        }

        // Fortsetzen der Filterkette
        chain.doFilter(request, response);
    }

    private boolean isAllowedHost(String url) {
        // Überprüfen, ob die Ziel-URL auf der weißen Liste steht
        for (String allowedHost : ALLOWED_HOSTS) {
            if (url.startsWith(allowedHost)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialisierung des Filters
    }

    @Override
    public void destroy() {
        // Aufräumen bei Beendigung
    }
}