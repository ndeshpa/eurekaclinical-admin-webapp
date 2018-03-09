package org.eurekaclinical.admin.webapp.config;

import java.util.HashMap;

/*-
 * #%L
 * Eureka! Clinical Admin Webapp
 * %%
 * Copyright (C) 2016 - 2017 Emory University
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


import java.util.Map;

import org.eurekaclinical.admin.servlet.EditServlet;
import org.eurekaclinical.admin.servlet.LoginServlet;
import org.eurekaclinical.admin.servlet.ProxyServlet;
import org.eurekaclinical.admin.webapp.props.AdminWebappProperties;
import org.eurekaclinical.common.config.WebappServletModule;
import org.eurekaclinical.common.servlet.DestroySessionServlet;
import org.eurekaclinical.common.servlet.LogoutServlet;
import org.eurekaclinical.common.servlet.SessionPropertiesServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletModule extends WebappServletModule {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(ServletModule.class);
	private final AdminWebappProperties properties;

	protected ServletModule(AdminWebappProperties inProperties) {
		super(inProperties);
		this.properties = inProperties;
	}

	@Override
	protected void setupServlets() {
		LOGGER.info("Setting up servlets in ServletModule");
		LOGGER.info("CAS LOGIN " + this.properties.getCasLoginUrl());
		LOGGER.info("CAS LOGOUT " + this.properties.getCasLogoutUrl());
		LOGGER.info("CAS URL " + this.properties.getCasUrl());
		LOGGER.info("Config dir " + this.properties.getConfigDir());
		LOGGER.info("URL " + this.properties.getUrl());
		LOGGER.info("User Srvc URL " + this.properties.getUserServiceUrl());
		LOGGER.info("Allowed web clients " + this.properties.getAllowedWebClientUrls().toString());
		LOGGER.info("Allowed web clients " + this.properties.getUserAgreementServiceUrl());
		
		serveLogin();
		serveProxyResource(); 
		serveLogout();
		serveDestroySession();
		serve("/get-session-properties").with(SessionPropertiesServlet.class); 
		
        //serve("/protected/edit").with(EditServlet.class);  
		//serve("/protected/login").with(LoginServlet.class);
		//serve("/proxy-resource/*").with(ProxyServlet.class);
		//serve("/logout").with(LogoutServlet.class);
		//serve("/destroy-session").with(DestroySessionServlet.class);
	}
	
  @Override
    protected Map<String, String> getCasValidationFilterInitParams() {
        Map<String, String> params = new HashMap<>();
        params.put("casServerUrlPrefix", this.properties.getCasUrl());
        params.put("serverName", this.properties.getProxyCallbackServer());
        params.put("proxyCallbackUrl", getCasProxyCallbackUrl());
        params.put("proxyReceptorUrl", getCasProxyCallbackPath());
        return params;
    }
}
