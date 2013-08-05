package me.mandala.kite;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;

import javax.servlet.ServletException;
import javax.servlet.http.*;
import javax.servlet.RequestDispatcher;

import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import me.mandala.kite.Util;

import java.util.logging.Logger;


@SuppressWarnings("serial")
public class KiteProtoArtifactServlet extends BaseServlet {
	/*public void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws IOException {
		resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");
	}*/
	private static final Logger logger = Logger.getLogger(KiteProtoArtifactServlet.class.getCanonicalName());

	/**
	 * Get the requested kiteproto entities in JSON format
	 */
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		super.doGet(req, resp);
		logger.log(Level.INFO, "Obtaining kiteprotoartifact listing");
		/*resp.setContentType("text/plain");
		resp.getWriter().println("Hello, world");*/
		
		RequestDispatcher dispatcher = 
		       getServletContext().getRequestDispatcher("/Create-Artifact.html");
		dispatcher.forward(req, resp);
		
		return;
	}
	
	/**
	 * Redirect the call to doDelete or doPut method
	 */
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String action = req.getParameter("action");
		if (action.equalsIgnoreCase("delete")) {
			doDelete(req, resp);
			return;
		} else if (action.equalsIgnoreCase("put")) {
			doPut(req, resp);
			return;
		}
	}
	
	/**
	 * Insert the new kitesprotoartifact
	 */
	protected void doPut(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		logger.log(Level.INFO, "Creating KitesProto Artifact");
		String nameKey = req.getParameter("m-name");
		String description = req.getParameter("m-desc");
		String kitesprotoid = "tbd";//req.getParameter("m-desc");
		String contentid = req.getParameter("m-contentid");
		String type = req.getParameter("m-contenttype");
		String url = req.getParameter("m-contenturl");
		KitesProtoArtifact.createOrUpdateKitesProtoArtifact(nameKey, description, kitesprotoid, contentid, type, url);
	}

	/**
	 * Delete the kitesproto
	 */
	protected void doDelete(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String kitesprotoName = req.getParameter("id");
    logger.log(Level.INFO, "Deleting User {0}", kitesprotoName);
	  Key key = KeyFactory.createKey("KitesProto", kitesprotoName);
	  try {
	    // CASCADE_ON_DELETE
	    Iterable<Entity> entities = Util.listChildKeys("Order", key);
	    final List<Key> orderkeys = new ArrayList<Key>();
	    final List<Key> linekeys = new ArrayList<Key>();
	    for (Entity e : entities) {
	  	  orderkeys.add(e.getKey());
	  	  Iterable<Entity> lines = Util.listEntities("LineItem", "orderID",String.valueOf(e.getKey().getId()));
	  	  for(Entity en : lines){
	  		  linekeys.add(en.getKey());
	  	  }
	    }
	    Util.deleteEntity(linekeys);
	    Util.deleteEntity(orderkeys);
	  	//Util.deleteFromCache(key);
	    Util.deleteEntity(key);
		} catch (Exception e) {
			//String msg = Util.getErrorResponse(e);
			//resp.getWriter().print(msg);
		}
	}
	
	
}
