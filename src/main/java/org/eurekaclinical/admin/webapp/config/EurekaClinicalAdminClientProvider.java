package org.eurekaclinical.admin.webapp.config;

/*-
 * #%L
 * Eureka Clinical Admin Webapp
 * %%
 * Copyright (C) 2012 - 2017 Emory University
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
import com.google.inject.Provider;

import org.eurekaclinical.admin.client.EurekaClinicalAdminClient;
/**
 *
 * @author Nita
 */
public class EurekaClinicalAdminClientProvider implements Provider<EurekaClinicalAdminClient> {

    private final String userServiceUrl;

    public EurekaClinicalAdminClientProvider(String inUserServiceUrl) {
        this.userServiceUrl = inUserServiceUrl;
    }

    @Override
    public EurekaClinicalAdminClient get() {
        return new EurekaClinicalAdminClient(this.userServiceUrl);
    }

}
