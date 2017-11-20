package ch.guggisberg.stefan.groupfitness.sec;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URISyntaxException;
import java.util.Properties;
import java.util.Set;

import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;

import ch.guggisberg.stefan.groupfitness.entities.Kurs;
import ch.guggisberg.stefan.groupfitness.entities.User;
import ch.guggisberg.stefan.groupfitness.exceptions.UserNotFoundException;
import ch.guggisberg.stefan.groupfitness.services.UserService;

@ManagedBean

public class MySetting {
	
	@EJB
	UserService usr;
	
	public void getProp() throws IOException, UserNotFoundException {
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		System.out.println("*******************************");
		
		User user = usr.getUserWithSkills(13L);
		Set<Kurs> list = user.getKannUnterrichten();
		System.out.println("Kann unterrichten:");
		
		for (Kurs kurs : list) {
			System.out.println(kurs.getKursNameDe());
		}
		
//		Set<Kurs> kurse = user.getKurse();
//		for (Kurs kurs : kurse) {
//			System.out.println(kurs.getKursDescriptionDe());
//		}
	}
	
	public String sayHello() throws IOException, URISyntaxException, UserNotFoundException {
		
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
