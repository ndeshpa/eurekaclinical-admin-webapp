package org.eurekaclinical.admin.webapp.props;

import java.util.List;

import org.eurekaclinical.standardapis.props.CasEurekaClinicalProperties;

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
 * AdminWebappProperties maintains list to properties for servers and urls
 * @author Nita
 *
 */


public class AdminWebappProperties extends CasEurekaClinicalProperties {

    //constructor made public to enable this class to be in package props as recommended in the wiki:
    //https://github.com/eurekaclinical/dev-wiki/wiki/Structure-of-Eureka%21-Clinical-microservices
    public AdminWebappProperties() {
        super("/etc/ec-admin");
    }

    @Override
    public String getProxyCallbackServer() {
        return getValue("eurekaclinical.adminwebapp.callbackserver");
    }

    @Override
    public String getUrl() {
        return getValue("eurekaclinical.adminwebapp.url");
    }
    
     public String getUserServiceUrl() {
            return this.getValue("eurekaclinical.userservice.url");
     }
    
    public String getRegistryServiceUrl() {
        return getValue("eurekaclinical.registryservice.url");
    }
      
    public String getUserAgreementServiceUrl() {
        return getValue("eurekaclinical.useragreementservice.url");
    }

    public boolean isDemoMode() {
        return Boolean.parseBoolean(getValue("eurekaclinical.adminwebapp.demomode"));
    }

    @Override
    public List<String> getAllowedWebClientUrls() {
        return getStringListValue("eurekaclinical.adminwebapp.allowedwebclients");
    }
    
    public String getProtempaServiceUrl() {
        return getValue("eurekaclinical.protempaservice.url");
    }


}
