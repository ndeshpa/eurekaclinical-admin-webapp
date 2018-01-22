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

import org.eurekaclinical.admin.webapp.props.AdminWebappProperties;
import org.eurekaclinical.common.servlet.DestroySessionServlet;
import org.eurekaclinical.common.servlet.LoginServlet;
import org.eurekaclinical.common.config.AbstractAuthorizingServletModule;
import org.eurekaclinical.common.servlet.LogoutServlet;
import org.eurekaclinical.common.servlet.PostMessageLoginServlet;
import org.eurekaclinical.common.servlet.ProxyServlet;
import org.eurekaclinical.common.servlet.SessionPropertiesServlet;

import com.google.inject.Singleton;

public class ServletModule extends AbstractAuthorizingServletModule {
		
	private final AdminWebappProperties properties;

	protected ServletModule(AdminWebappProperties inProperties) {
		super(inProperties);
		this.properties = inProperties;
	}

	@Override
	protected void setupServlets() {
		serve("/login*").with(LoginServlet.class);
		serve("/proxy-resource/*").with(ProxyServlet.class);
		serve("/logout").with(LogoutServlet.class); 
		serve("/destroy-session").with(DestroySessionServlet.class); 
		serve("/get-session-properties").with(SessionPropertiesServlet.class);      
		serve("/get-session").with(PostMessageLoginServlet.class);				
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
