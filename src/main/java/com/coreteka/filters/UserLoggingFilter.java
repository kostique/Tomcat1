package com.coreteka.filters;

import com.coreteka.entities.User;
import com.coreteka.exceptions.InvalidUserAttributesNumberException;
import com.google.gson.Gson;
import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

@WebFilter(filterName = "UserLoggingFilter", value = {"/users", "/users/*"})
public class UserLoggingFilter implements Filter {
    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {

        UserRequestWrapper wrapper = new UserRequestWrapper((HttpServletRequest)request);
        wrapper.setReader();
        wrapper.logRequest();
        chain.doFilter(wrapper, response);
    }

    public void init(FilterConfig config) throws ServletException {

    }
}
