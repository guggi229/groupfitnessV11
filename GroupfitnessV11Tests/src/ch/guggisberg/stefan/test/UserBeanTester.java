package ch.guggisberg.stefan.test;

import static org.junit.Assert.*;

import java.util.Hashtable;

import javax.naming.Context;
import javax.naming.InitialContext;


import org.junit.Before;
import org.junit.Test;

import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

public class UserBeanTester {

	private UserServiceRemote usr;

	@Before
	public void setUp() throws Exception {


		try {
			final Hashtable<String, Comparable> jndiProperties =
					new Hashtable<>();

			jndiProperties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");

			jndiProperties.put("jboss.naming.client.ejb.context", true);

			jndiProperties.put(Context.PROVIDER_URL, "http-remoting://locahlhost:8080");
			jndiProperties.put(Context.SECURITY_PRINCIPAL, "admin");
			jndiProperties.put(Context.SECURITY_CREDENTIALS, "admin");

			final Context context = new InitialContext(jndiProperties);

			final String lookupName = "GroupfitnessV11-0.0.1-SNAPSHOT/GroupfitnessV11EJB-0.0.1-SNAPSHOT/UserService!ch.guggisberg.stefan.groupfitness.services.UserServiceRemote";

			usr = (UserServiceRemote) context.lookup(lookupName);


		} catch (Exception e) {
			System.out.println(e);
		}


	}
	@Test 
	public void test() {
		assertNull(usr);
	}



}
