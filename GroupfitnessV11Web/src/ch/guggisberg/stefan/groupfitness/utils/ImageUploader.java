package ch.guggisberg.stefan.groupfitness.utils;


import java.io.IOException;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.servlet.http.Part;

@ManagedBean
@SessionScoped
public class ImageUploader {

    private Part file1;
    private Part file2;

    public Part getFile1() {
        return file1;
    }

    public void setFile1(Part file1) {
        this.file1 = file1;
    }

  
    public void setFile2(Part file2) {
        this.file2 = file2;
    }

    public String upload() throws IOException {
        // C:\Users\guggi229\Documents
        file1.write("C:\\Users\\guggi229\\Documents\\"+getFilename(file1));
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