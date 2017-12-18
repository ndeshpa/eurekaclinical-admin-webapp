package org.eurekaclinical.admin.webapp.config;

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


import org.eurekaclinical.admin.client.EurekaClinicalAdminClient;
import org.eurekaclinical.admin.webapp.clients.ServiceClientRouterTable;
import org.eurekaclinical.common.comm.clients.AuthorizingEurekaClinicalClient;
import org.eurekaclinical.common.comm.clients.ClientException;
import org.eurekaclinical.common.comm.clients.RouterTable;
import org.eurekaclinical.registry.client.EurekaClinicalRegistryClient;
import org.eurekaclinical.scribeupext.provider.GitHubProvider;
import org.eurekaclinical.scribeupext.provider.GlobusProvider;
import org.eurekaclinical.scribeupext.provider.Google2Provider;
import org.eurekaclinical.standardapis.props.CasEurekaClinicalProperties;
import org.eurekaclinical.user.client.EurekaClinicalUserClient;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.SessionScoped;

public class AppModule extends AbstractModule {
	
	private final AdminWebappProperties adminWebappProperties;
    private final EurekaClinicalAdminClientProvider clientProvider;
    private final EurekaClinicalRegistryClientProvider registryClientProvider;
    
    AppModule(AdminWebappProperties adminWebappProperties) {
    	this.adminWebappProperties = adminWebappProperties;
    	this.clientProvider = new EurekaClinicalAdminClientProvider(adminWebappProperties.getUserServiceUrl());
    	System.out.println("=========================================");
    	System.out.println(adminWebappProperties.getUserServiceUrl());
    	this.registryClientProvider = new EurekaClinicalRegistryClientProvider(
                adminWebappProperties.getRegistryServiceUrl());
    }

    @Override
    protected void configure() {
        bind(RouterTable.class).to(ServiceClientRouterTable.class);
        bind(AuthorizingEurekaClinicalClient.class).toProvider(this.clientProvider).in(SessionScoped.class);
        bind(EurekaClinicalAdminClient.class).toProvider(this.clientProvider).in(SessionScoped.class);
        bind(EurekaClinicalRegistryClient.class).toProvider(this.registryClientProvider).in(SessionScoped.class);
        bind(AdminWebappProperties.class).toInstance(this.adminWebappProperties);
        bind(CasEurekaClinicalProperties.class).toInstance(this.adminWebappProperties);
        bind(CasEurekaClinicalProperties.class).toInstance(this.adminWebappProperties);
    }
}
