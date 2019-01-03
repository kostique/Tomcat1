package com.coreteka.filters;


import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;

@WebFilter(filterName = "UserLoggingFilter", servletNames = "UserServlet")
public class UserLoggingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        UserRequestWrapper wrapper = new UserRequestWrapper((HttpServletRequest)request);
        wrapper.logRequest();
        wrapper.printRequestToConsole();
        chain.doFilter(wrapper, response);

    }

    public void init(FilterConfig config) throws ServletException {

    }
}
