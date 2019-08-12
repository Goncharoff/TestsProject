package filter;

import java.io.IOException;
import java.util.Arrays;
import java.util.Iterator;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

/**
 * Iterates filters and executes filter do chain.
 */
public class FilterChainImpl implements FilterChain {
  private Iterator<Filter> filters;

  protected FilterChainImpl(Filter... filters) {
    this.filters = Arrays.asList(filters).iterator();
  }

  @Override
  public void doFilter(ServletRequest request, ServletResponse response) throws IOException, ServletException {
    if (filters.hasNext()) {
      Filter filter = filters.next();
      filter.doFilter(request, response, this);
    }
  }

}
