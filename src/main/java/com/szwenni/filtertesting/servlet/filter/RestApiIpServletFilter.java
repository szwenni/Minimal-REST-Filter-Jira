package com.szwenni.filtertesting.servlet.filter;

import com.atlassian.plugin.servlet.PluginHttpRequestWrapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Enumeration;

public class RestApiIpServletFilter implements Filter{
    private static final Logger log = LoggerFactory.getLogger(RestApiIpServletFilter.class);

    public void init(FilterConfig filterConfig)throws ServletException{
        System.out.println(filterConfig);
    }

    public void destroy(){
    }


    public void doFilter(ServletRequest request,ServletResponse response,FilterChain chain)throws IOException,ServletException{
        //do some custom handling here
        String osAuthStatus = (String)request.getAttribute("os_authstatus");
        if(osAuthStatus != null) {
            System.out.println("Having REST request with basic auth");
            /*for (Enumeration<Object> e = request.getAttributeNames(); e.hasMoreElements();) {
                Object el = e.nextElement();
                System.out.println("ElementKey: "+el);
                System.out.println("ElementValue: "+request.getAttribute((String)el));
            }
*/
        
            if(request instanceof PluginHttpRequestWrapper) {
                PluginHttpRequestWrapper httpRequest = (PluginHttpRequestWrapper)request;
                String orginalIP = (String)httpRequest.getHeader("x-forwarded-for");
                System.err.println("orginalIP: "+orginalIP);
                if("192.168.10.1".equals(orginalIP)) {
                    chain.doFilter(request,response);
                } else {
                    System.err.println("blocking: "+((PluginHttpRequestWrapper) request).getPathInfo());
                    ((HttpServletResponse) response).sendError(HttpServletResponse.SC_UNAUTHORIZED, "Not allowed.");
                }
                
                
            }
            
            
        } else {
            chain.doFilter(request,response);
        }
        
       
        //System.out.println(request.getLocalAddr());

        //continue the request
        
    }

}