package ch.guggisberg.stefan.groupfitness.sec;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import ch.guggisberg.stefan.groupfitness.services.UserServiceRemote;

@ManagedBean

public class MySetting {
	
	@EJB
	UserServiceRemote usr;
	
	public void getProp() throws IOException {
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		
		

//		
//		Properties prop = new Properties();
//		System.out.println("*******************************");
//		System.out.println("Laden der app.pro");
//		InputStream in = getClass().getResourceAsStream("test.properties");
//		System.out.println("*******************************");
//		System.out.println("Ausselesen");
//		prop.load(in);
//		in.close();
//		System.out.println("geschlossen");
//
//			System.out.println("*******************************");
//			System.out.println("*******************************");
//			System.out.println("*******************************");
//			System.out.println("*******************************");
//			System.out.println("*******************************");
//			
//			System.out.println(prop.getProperty("Test"));
//
//		} catch (IOException ex) {
//			ex.printStackTrace();
//		} finally {
//			if (input != null) {
//				try {
//					input.close();
//				} catch (IOException e) {
//					e.printStackTrace();
//				}
//			}
//		}
//		
	}
	
	public String sayHello() throws IOException, URISyntaxException {
		
		getProp();
		
		
		
//		Properties properties3 = new Properties();
//		properties3.load(getClass().getResourceAsStream("/app.properties"));
//		String test = properties3.getProperty("PfadBilderKurse");
//		System.out.println(test);
//		
//		
//		usr.sayHello();
		return "1";
	}
}
