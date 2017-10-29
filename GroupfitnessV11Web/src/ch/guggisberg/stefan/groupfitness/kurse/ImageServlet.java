package ch.guggisberg.stefan.groupfitness.kurse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String coursImage = "C:\\Users\\\\guggi229\\Documents\\cours\\";
	
	
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		byte[] byteFile = null ;
		try {
			String id = request.getParameter("id");
			System.out.println("ID = " + id);
			Path file = Paths.get(coursImage+  id +".jpeg"); 
			if (file==null) file = Paths.get("/GroupfitnessV11Web/images/avatarneutral.png");
			System.out.println(coursImage  + " " +  id +".jpeg");
			byteFile = Files.readAllBytes(file);
		} catch (Exception e) {
			System.out.println("Es ist ein Fehler aufgetreten!");
		}
		response.reset();
		
		response.getOutputStream().write(byteFile);
	}

}
