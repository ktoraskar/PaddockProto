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

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;

/**
 * This class defines the methods for basic operations of create, update & retrieve
 * for kitesproto entity
 * 
 * @author 
 *
 */
public class KitesProto {

	/**
	 * Checks if the entity is existing and if it is not, it creates the entity
	 * else it updates the entity
	 * 
	 * @param name
	 *          : username for the kitesproto
	 * @param kitesprotoid
	 *          : first name of the kitesproto
	 * @param lastName
	 *          : last name of the kitesproto
	 * @param categoryid
	 *          : categoryid ids of kitesproto
	 */
	public static void createOrUpdateKitesProto(String name, String description, String kitesprotoid,
			String categoryid) {
		Entity kitesproto = getSingleKitesProto(name);
		if (kitesproto == null) {
			kitesproto = new Entity("KitesProto");
			kitesproto.setProperty("name", name);
			kitesproto.setProperty("description", description);
			kitesproto.setProperty("kitesprotoid", kitesprotoid);
			kitesproto.setProperty("categoryid", categoryid);
		} else {
			if (kitesprotoid != null && !"".equals(kitesprotoid)) {
				kitesproto.setProperty("kitesprotoid", kitesprotoid);
			}
			if (categoryid != null && !"".equals(categoryid)) {
				kitesproto.setProperty("categoryid", categoryid);
			}
		}
		Util.persistEntity(kitesproto);
	}

	/**
	 * List all the kitesprotos available
	 * 
	 * @return an iterable list with all the kitesprotos
	 */
	public static Iterable<Entity> getAllKitesProtos() {
		Iterable<Entity> entities = Util.listEntities("KitesProto", null, null);
		return entities;
	}

	/**
	 * Searches for a kitesproto and returns the entity as an iterable The search is
	 * performed by creating a query and searching for the attribute
	 * 
	 * @param kitesprotoName
	 *          : username of the kitesproto
	 * @return iterable with the kitesprotos searched for
	 */
	public static Iterable<Entity> getKitesProto(String kitesprotoName) {
		Iterable<Entity> entities = Util.listEntities("KitesProto", "name",	kitesprotoName);
		return entities;
	}

	/**
	 * Searches for a kitesproto and returns the entity as an iterable The search is
	 * key based instead of query
	 * 
	 * @param kitesprotoName
	 *          : username of the kitesproto
	 * @return the entity with the username as key
	 */
	public static Entity getSingleKitesProto(String kitesprotoName) {
		Key key = KeyFactory.createKey("KitesProto", kitesprotoName);
		return Util.findEntity(key);
	}
}
