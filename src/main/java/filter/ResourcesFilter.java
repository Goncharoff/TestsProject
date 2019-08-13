package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;

@WebFilter("/*")
public class ResourcesFilter extends BaseFilter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        super.init(filterConfig);
    }

//    @Override
  //  public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
//        HttpServletRequest req = (HttpServletRequest) servletRequest;
//        String path = req.getRequestURI().substring(req.getContextPath().length());
//
//        if (path.startsWith("/resources/")) {
//            filterChain.doFilter(servletRequest, servletResponse);
//        } else {
//            servletRequest.getRequestDispatcher(path).forward(servletRequest, servletResponse);
//        }
    //}

    public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {

            filterChain.doFilter(servletRequest, servletResponse);
    }

        @Override
    public void destroy() {
        super.destroy();
    }
}
