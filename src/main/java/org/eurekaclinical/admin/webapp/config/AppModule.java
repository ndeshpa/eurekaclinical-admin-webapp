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
/**
 * AppModule sets up binding for client and provider classes.
 * @author Nita
 */


import org.eurekaclinical.admin.client.EurekaClinicalAdminClient;
import org.eurekaclinical.admin.webapp.clients.ServiceClientRouterTable;
import org.eurekaclinical.admin.webapp.props.AdminWebappProperties;
import org.eurekaclinical.common.comm.clients.AuthorizingEurekaClinicalClient;
import org.eurekaclinical.common.comm.clients.RouterTable;
import org.eurekaclinical.protempa.client.EurekaClinicalProtempaClient;
import org.eurekaclinical.registry.client.EurekaClinicalRegistryClient;
import org.eurekaclinical.standardapis.props.CasEurekaClinicalProperties;
import org.eurekaclinical.useragreement.client.EurekaClinicalUserAgreementClient;

import com.google.inject.AbstractModule;
import com.google.inject.servlet.SessionScoped;

public class AppModule extends AbstractModule {
    
    private final AdminWebappProperties adminWebappProperties;
    private final EurekaClinicalAdminClientProvider clientProvider;
    private final EurekaClinicalRegistryClientProvider registryClientProvider;
    private final EurekaClinicalUserAgreementClientProvider userAgreementClientProvider;
    private final EurekaClinicalProtempaClientProvider protempaClientProvider;
    
    AppModule(AdminWebappProperties adminWebappProperties) {
        this.adminWebappProperties = adminWebappProperties;
        this.clientProvider = new EurekaClinicalAdminClientProvider(adminWebappProperties.getUserServiceUrl());
        this.userAgreementClientProvider = new EurekaClinicalUserAgreementClientProvider(
                adminWebappProperties.getUserAgreementServiceUrl());
        this.registryClientProvider = new EurekaClinicalRegistryClientProvider(
                adminWebappProperties.getRegistryServiceUrl());
        this.protempaClientProvider = new EurekaClinicalProtempaClientProvider(
                adminWebappProperties.getProtempaServiceUrl());
    }

    @Override
    protected void configure() {
        bind(RouterTable.class).to(ServiceClientRouterTable.class);
        bind(AuthorizingEurekaClinicalClient.class).toProvider(this.clientProvider).in(SessionScoped.class);
        bind(EurekaClinicalAdminClient.class).toProvider(this.clientProvider).in(SessionScoped.class);
        bind(EurekaClinicalRegistryClient.class).toProvider(this.registryClientProvider).in(SessionScoped.class);
        bind(EurekaClinicalUserAgreementClient.class).toProvider(this.userAgreementClientProvider).in(SessionScoped.class);
        bind(AdminWebappProperties.class).toInstance(this.adminWebappProperties);
        bind(CasEurekaClinicalProperties.class).toInstance(this.adminWebappProperties);
        bind(EurekaClinicalProtempaClient.class).toProvider(this.protempaClientProvider).in(SessionScoped.class);
    }
}
