package org.eurekaclinical.admin.client;

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


import java.net.URI;
import java.util.List;

import org.eurekaclinical.common.comm.clients.AuthorizingEurekaClinicalClient;
import org.eurekaclinical.common.comm.clients.ClientException;
import org.eurekaclinical.user.client.comm.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.sun.jersey.api.client.GenericType;

public class EurekaClinicalAdminClient extends AuthorizingEurekaClinicalClient {
		
	private static final GenericType<List<User>> UserList = new GenericType<List<User>>() {
    };
    
    private final URI userServiceUrl;

	public EurekaClinicalAdminClient(String inUserServiceUrl) {
		super(null);
		this.userServiceUrl = URI.create(inUserServiceUrl);
	}

	@Override
	protected URI getResourceUrl() {
		return this.userServiceUrl;
	}
	
	 @Override
	    public List<User> getUsers() throws ClientException {
	        final String path = "/api/protected/users";
	        return doGet(path, UserList);
	    }

	    @Override
	    public User getMe() throws ClientException {
	        String path = "/api/protected/users/me";
	        return doGet(path, User.class);
	    }

	    @Override
	    public User getUserById(Long inUserId) throws ClientException {
	        final String path = "/api/protected/users/" + inUserId;
	        return doGet(path, User.class);
	    }
	    
	    public void updateUser(User inUser, Long userId) throws ClientException {
	        final String path = "/api/protected/users/" + userId;
	        doPut(path, inUser);
	    }

}
