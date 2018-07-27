package org.eurekaclinical.admin.webapp.clients;

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
import javax.inject.Inject;

import org.eurekaclinical.admin.client.EurekaClinicalAdminClient;
import org.eurekaclinical.common.comm.clients.Route;
import org.eurekaclinical.common.comm.clients.RouterTable;
import org.eurekaclinical.protempa.client.EurekaClinicalProtempaClient;
import org.eurekaclinical.registry.client.EurekaClinicalRegistryClient;
import org.eurekaclinical.useragreement.client.EurekaClinicalUserAgreementClient;

/**
 * ServiceClientRouterTable to map incoming routes to clients 
 *
 * @author Nita
 */
public class ServiceClientRouterTable implements RouterTable {

    private final EurekaClinicalAdminClient client;
    private final EurekaClinicalUserAgreementClient userAgreementclient;
    private final EurekaClinicalRegistryClient registryClient;
    private final EurekaClinicalProtempaClient protempaClient;

    @Inject
    public ServiceClientRouterTable(EurekaClinicalAdminClient inClient,
            EurekaClinicalRegistryClient inRegistryClient, 
            EurekaClinicalUserAgreementClient inUserAgreementclient, 
            EurekaClinicalProtempaClient inProtempaClient) {
        this.client = inClient;
        this.registryClient = inRegistryClient;
        this.userAgreementclient = inUserAgreementclient;
        this.protempaClient = inProtempaClient;
    }

    @Override
    public Route[] load() {
        return new Route[]{
            new Route("/components", "/api/protected/components", this.registryClient),
            new Route("/useragreements/current", "/api/protected/useragreements/current", this.userAgreementclient),
            new Route("/useragreements", "/api/protected/useragreements", this.userAgreementclient),
            new Route("/jobs/all", "/api/protected/jobs/all", this.protempaClient),
            new Route("/jobs", "/api/protected/jobs", this.protempaClient),
            new Route("/", "/api/protected/", this.client)
        };
    }

}
