// Copyright 2011, Google Inc. All Rights Reserved.
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.
package me.mandala.kite;

import java.util.logging.Level;
import java.util.logging.Logger;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * This class defines the methods for basic operations of create, update & retrieve
 * for kitesprotoartifact entity
 * 
 * @author 
 *
 */
public class KitesProtoArtifact {

	  private static final Logger logger = Logger.getLogger(Util.class.getCanonicalName());

	/**
	 * Checks if the entity is existing and if it is not, it creates the entity
	 * else it updates the entity
	 * 
	 * @param name
	 *          : username for the kitesprotoartifact
	 * @param description
	 *          : description for the kitesprotoartifact        	                    
	 * @param kitesprotoartifactid
	 *          : first name of the kitesprotoartifact
	 * @param contentid
	 *          : contentid of kitesprotoartifact
	 * @param type
	 *          : type of the kitesprotoartifact
	 * @param url
	 *          : url of the kitesprotoartifact 
	 */
	//@todo: contentid generation code, kitesprotoartifactid generation
	public static void createOrUpdateKitesProtoArtifact(String name, String description, String kitesprotoartifactid,
			String contentid, String type, String url) {
		Entity kitesprotoartifact = getSingleKitesProtoArtifact(name);
		if (kitesprotoartifact == null) {
			kitesprotoartifact = new Entity("KitesProtoArtifact");
			kitesprotoartifact.setProperty("name", name);
			kitesprotoartifact.setProperty("description", description);
			kitesprotoartifact.setProperty("kitesprotoartifactid", kitesprotoartifactid);
			kitesprotoartifact.setProperty("contentid", contentid);
			kitesprotoartifact.setProperty("type", type);
			kitesprotoartifact.setProperty("url", url);
		  	logger.log(Level.INFO, "Creating the artifact entity");
		} else {
			if (kitesprotoartifactid != null && !"".equals(kitesprotoartifactid)) {
				kitesprotoartifact.setProperty("kitesprotoartifactid", kitesprotoartifactid);
			}
			if (contentid != null && !"".equals(contentid)) {
				kitesprotoartifact.setProperty("contentid", contentid);
			}
		}
		Util.persistEntity(kitesprotoartifact);
	}

	/**
	 * List all the kitesprotoartifacts available
	 * 
	 * @return an iterable list with all the kitesprotoartifacts
	 */
	public static Iterable<Entity> getAllKitesProtoArtifacts() {
		Iterable<Entity> entities = Util.listEntities("KitesProtoArtifact", null, null);
		return entities;
	}

	/**
	 * Searches for a kitesprotoartifact and returns the entity as an iterable The search is
	 * performed by creating a query and searching for the attribute
	 * 
	 * @param kitesprotoartifactName
	 *          : username of the kitesprotoartifact
	 * @return iterable with the kitesprotoartifacts searched for
	 */
	public static Iterable<Entity> getKitesProtoArtifact(String kitesprotoartifactName) {
		Iterable<Entity> entities = Util.listEntities("KitesProtoArtifact", "name",	kitesprotoartifactName);
		return entities;
	}

	/**
	 * Searches for a kitesprotoartifact and returns the entity as an iterable The search is
	 * key based instead of query
	 * 
	 * @param kitesprotoartifactName
	 *          : username of the kitesprotoartifact
	 * @return the entity with the username as key
	 */
	public static Entity getSingleKitesProtoArtifact(String kitesprotoartifactName) {
		Key key = KeyFactory.createKey("KitesProtoArtifact", kitesprotoartifactName);
		return Util.findEntity(key);
	}
}
