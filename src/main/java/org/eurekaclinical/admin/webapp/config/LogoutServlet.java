package org.eurekaclinical.admin.webapp.config;

/*-
 * #%L
 * Eureka! Clinical Common
 * %%
 * Copyright (C) 2016 Emory University
 * %%
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 * 
 *      http://www.apache.org/licenses/LICENSE-2.0
 * 
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 * #L%
 */

import java.io.IOException;
import java.net.URI;
import java.util.List;

import javax.inject.Inject;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import javax.inject.Singleton;

import org.eurekaclinical.standardapis.props.CasEurekaClinicalProperties;

@Singleton
public class LogoutServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;

    private final CasEurekaClinicalProperties webappProperties;

    @Inject
    public LogoutServlet(CasEurekaClinicalProperties inProperties) {
        this.webappProperties = inProperties;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        if (session != null) {
            session.invalidate();
        }
              
        /*
         * We need to redirect here rather than forward so that 
         * logout.jsp gets a request object without a user. Otherwise,
         * the button bar will think we're still logged in.
         */
  
        String url = req.getRequestURL().toString();
        String host = url.substring(0,url.indexOf(req.getContextPath()));
        
        String casUrl = webappProperties.getCasLogoutUrl();
        String casPath = casUrl.substring(casUrl.indexOf("/cas"));
        resp.sendRedirect(host+casPath);  
    }
}
