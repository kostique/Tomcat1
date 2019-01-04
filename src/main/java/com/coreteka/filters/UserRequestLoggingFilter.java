package com.coreteka.filters;


import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;

//@WebFilter(filterName = "UserRequestLoggingFilter", servletNames = "UserServlet")
public class UserRequestLoggingFilter implements Filter {

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String body = getRequestBody((HttpServletRequest) request);
        printRequestToConsole((HttpServletRequest) request, body);
        UserRequestWrapper wrapper = new UserRequestWrapper((HttpServletRequest) request, body);
        chain.doFilter(wrapper, response);
    }

    private String getRequestBody(HttpServletRequest request) throws IOException {
        BufferedReader bufferedReader = request.getReader();
        StringBuilder builder = new StringBuilder();
        String line;

        while ((line = bufferedReader.readLine()) != null) {
            builder.append(line);
            builder.append(System.lineSeparator());
        }
        bufferedReader.close();
        return builder.toString();
    }

    private void printRequestToConsole(HttpServletRequest request, String body) {
        System.out.println();
        System.out.println();
        System.out.println(request.getMethod() + "  " + request.getRequestURL());
        System.out.println(request.getProtocol());
        System.out.println("Content-Type: " + request.getContentType());
        System.out.println(body);
        System.out.println();
    }

    public void init(FilterConfig config) {

    }

    public void destroy() {

    }
}
