package com.coreteka.filters;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.Enumeration;

public class UserRequestLoggingFilter implements Filter {

    private static final Logger LOGGER = LogManager.getLogger(UserRequestLoggingFilter.class.getName());
    private StringBuffer logMessageBuffer = new StringBuffer();


    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws ServletException, IOException {
        String body = getRequestBody((HttpServletRequest) request);
        logRequestToConsole((HttpServletRequest) request, body);
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

    private void logRequestToConsole(HttpServletRequest request, String body) {
        logMessageBuffer.setLength(0);
        logMessageBuffer.append("\n\n")
                .append(request.getMethod())
                .append("  ")
                .append(request.getRequestURL())
                .append("\n")
                .append(request.getProtocol()).append("\n")
                .append("Content-Type: " ).append(request.getContentType()).append("\n")
                .append(body).append("\n");
        LOGGER.info(logMessageBuffer.toString());
    }

    public void init(FilterConfig config) {
    }

    public void destroy() {
    }
}