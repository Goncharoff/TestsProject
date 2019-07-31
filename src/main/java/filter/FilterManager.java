package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class FilterManager {
  public static void process(HttpServletRequest request,
                             HttpServletResponse response, OnIntercept callback) throws IOException, ServletException {
    FilterChain filterChain = new FilterChainImpl(
            new SecurityFilter(callback),
            new VisitorCounterFilter()
    );
    filterChain.doFilter(request, response);
  }
}
