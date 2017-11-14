package ch.guggisberg.stefan.groupfitness.kurse;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.MessageFormat;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class ImageServlet
 */
@WebServlet("/image")
public class ImageServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private final String imageFolderPath = "D:\\Documents\\cours\\";
	private static Logger log = Logger.getLogger(ImageServlet.class);

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) {
		byte[] byteFile = null ;
		String id = request.getParameter("id");
		log.info(MessageFormat.format("Suche Bild ID {0}", id));
		Path path = Paths.get(imageFolderPath+  id +".jpeg");
		File file = new File(imageFolderPath+  id +".jpeg");
		if(file.exists()) {
			try {
				byteFile = Files.readAllBytes(path);
				response.reset();
				response.getOutputStream().write(byteFile);
			} catch (IOException e) {
				log.info(MessageFormat.format("Es gibt noch kein Bild für {0}.", id));
				response.reset();
			}
			
		}

	}

}
