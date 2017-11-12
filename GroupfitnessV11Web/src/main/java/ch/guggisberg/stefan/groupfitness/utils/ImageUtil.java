package ch.guggisberg.stefan.groupfitness.utils;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;


/**
 * Die Klasse ist für die Bilder verantwortlich
 * @author guggi229
 *
 */
public class ImageUtil {

	/**
	 * Berechnet das Verhätnis von alte Breite und neue Breite und wendet dieses Verhältniss auf die Höhe an.
	 * 
	 * 
	 * 
	 * @param originFile Original File
	 * @param newWitdh Neue Breite
	 * @return File Liefert das Bild proportinial verkleinert zurück
	 * @throws IOException 
	 */
	public static File imageResizerFile (File originFile, int newWitdh) throws IOException{
		BufferedImage bimg = ImageIO.read(originFile);
		float ratio =   (float) newWitdh / bimg.getWidth();
		int newHeigth = (int) (bimg.getHeight()*ratio);
		imageResizer(originFile, newWitdh, newHeigth);
		return originFile;

	}
	/**
	 * 
	 * @param originFile Original File
	 * @param newWitdh Neue breite
	 * @param newHeigth Neue Höhe
	 * @return Neues File
	 * @throws IOException
	 */
	public static File imageResizer(File originFile, int newWitdh, int newHeigth) throws IOException {
		Image img = ImageIO.read(originFile);
		BufferedImage tempJPG = resizeImage(img, newWitdh, newHeigth);
		System.out.println("*************");
		System.out.println("*************");
		System.out.println("*************");
		System.out.println("*************");
		System.out.println(originFile.getPath());

		File newFileJPG = new File(originFile.getPath());

		ImageIO.write(tempJPG, "jpg", newFileJPG);

		return null;

	}
	/**
	 * 
	 * @param image Original Bild
	 * @param width Neue Breite
	 * @param height Neue Höhe
	 * @return Neues Bild
	 */
	private static BufferedImage resizeImage(Image image, int width, int height) {
		final BufferedImage bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
		final Graphics2D graphics2D = bufferedImage.createGraphics();
		graphics2D.setComposite(AlphaComposite.Src);
		//below three lines are for RenderingHints for better image quality at cost of higher processing time
		graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,RenderingHints.VALUE_INTERPOLATION_BILINEAR);
		graphics2D.setRenderingHint(RenderingHints.KEY_RENDERING,RenderingHints.VALUE_RENDER_QUALITY);
		graphics2D.setRenderingHint(RenderingHints.KEY_ANTIALIASING,RenderingHints.VALUE_ANTIALIAS_ON);
		graphics2D.drawImage(image, 0, 0, width, height, null);
		graphics2D.dispose();
		return bufferedImage;

	}
}
