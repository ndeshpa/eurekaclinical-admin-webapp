package org.eurekaclinical.admin.webapp.config;

import javax.servlet.ServletContextEvent;

import org.eurekaclinical.admin.client.EurekaClinicalAdminClient;
import org.eurekaclinical.admin.webapp.props.AdminWebappProperties;
import org.eurekaclinical.common.config.ClientSessionListener;
import org.eurekaclinical.common.config.InjectorSupport;
import org.eurekaclinical.registry.client.EurekaClinicalRegistryClient;

import com.google.inject.Injector;
import com.google.inject.Module;
import com.google.inject.servlet.GuiceServletContextListener;

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

/**
 * AdminWebappListener is the entry point for all requests.
 * @author Nita
 *
 */


public class AdminWebappListener extends GuiceServletContextListener {
	
	private final AdminWebappProperties adminWebappProperties;
	private Injector injector;
	
	public AdminWebappListener() {
		this.adminWebappProperties = new AdminWebappProperties();
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		super.contextInitialized(servletContextEvent);
		servletContextEvent.getServletContext()
			.addListener(new ClientSessionListener());
        servletContextEvent.getServletContext().setAttribute(
                "adminWebAppProperties", this.adminWebappProperties);
	}
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		super.contextDestroyed(servletContextEvent);
		servletContextEvent.getServletContext().removeAttribute( "adminWebAppProperties");
	}
	@Override
	protected Injector getInjector() {
		 this.injector = new InjectorSupport(
	                new Module[]{
	                    new AppModule(this.adminWebappProperties),
	                    new ServletModule(this.adminWebappProperties)
	                },
	                this.adminWebappProperties).getInjector();
	        return this.injector;
	}

}
