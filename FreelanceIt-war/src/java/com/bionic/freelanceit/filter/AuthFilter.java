/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bionic.freelanceit.filter;

import com.bionic.freelanceit.entities.UserInfo;
import com.bionic.freelanceit.manager.Config;
import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author qz
 */
public class AuthFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpSession session = req.getSession(false);
        if (session != null) {
            UserInfo userInfo = (UserInfo) session.getAttribute(UserInfo.USER_INFO);
            if (userInfo == null) {
                req.getRequestDispatcher(Config.getInstance().getProperty(Config.LOGIN))
                        .forward(request, response);
            }
        } else {
            req.getRequestDispatcher(Config.getInstance().getProperty(Config.LOGIN))
                    .forward(request, response);
        }
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
    }
}
