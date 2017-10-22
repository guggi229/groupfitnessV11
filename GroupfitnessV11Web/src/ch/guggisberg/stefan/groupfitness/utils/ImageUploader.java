package ch.guggisberg.stefan.groupfitness.utils;


import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class ImageUploader {

    private Part file;
   

    public Part getFile() {
        return file;
    }

    public void setFile1(Part file1) {
        this.file = file1;
    }

    public String upload() throws IOException {
        // C:\Users\guggi229\Documents
        file.write("C:\\Users\\guggi229\\Documents\\"+getFilename(file));
         return "success";
    }

    private static String getFilename(Part part) {
        for (String cd : part.getHeader("content-disposition").split(";")) {
            if (cd.trim().startsWith("filename")) {
                String filename = cd.substring(cd.indexOf('=') + 1).trim().replace("\"", "");
                return filename.substring(filename.lastIndexOf('/') + 1).substring(filename.lastIndexOf('\\') + 1); // MSIE fix.
            }
        }
        return null;
    }
}