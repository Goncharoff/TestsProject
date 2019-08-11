package filter;

import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Class for instantiating filter chains, registering filters, and initiate them.
 */
public class FilterManager {

    //Should not be instanced.
    private FilterManager() {
    }

    public static void process(HttpServletRequest request,
                               HttpServletResponse response, OnIntercept callback) throws IOException, ServletException {

        FilterChain filterChain = new FilterChainImpl(
                new EncodingFilter()
               // new SecurityFilter(callback)
        );

        filterChain.doFilter(request, response);
    }
}
