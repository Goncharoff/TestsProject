package filter;

import java.io.IOException;
import java.util.Optional;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.annotation.WebInitParam;


/**
 * Filter for encoding. Sets page encoding, or if not defined sets UTF-8.
 */
@WebFilter(servletNames = {"/*"}, initParams = {@WebInitParam(name = "encoding", value = "UTF-8")})
public class EncodingFilter extends BaseFilter {
    private String encoding;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
        this.encoding = filterConfig.getInitParameter("encoding");
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String enc = Optional.ofNullable(request.getParameter("encoding"))
                .orElse("UTF-8");


        response.setCharacterEncoding(enc);

        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {

    }
}
