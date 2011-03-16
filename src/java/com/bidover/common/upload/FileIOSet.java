
package com.bidover.common.upload;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.FileInputStream;
//import java.io.OutputStreamWriter;
//import java.io.FileOutputStream;
import java.io.FileNotFoundException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Locale;
import java.util.ResourceBundle;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.image.BufferedImage;
import javax.imageio.*;
import javax.servlet.http.HttpServlet;
import javax.servlet.ServletException;

public class FileIOSet extends HttpServlet {

	private static final long serialVersionUID = 1L;
	protected static String DEFAULT_ENCODING = "UTF-8";
	public static String imgPath;
	public static String filePath;

	public FileIOSet() {
		ResourceBundle set = ResourceBundle.getBundle("com.bidover.common.upload.ioBundle", Locale.ENGLISH);
		imgPath = set.getString("IMGPATH");
		filePath = set.getString("FILEPATH");
	}

	public boolean fileExists(final String dirName) {
		return (new File(imgPath + dirName)).exists();
	}

	public void fileDelete(final String dirName) {
		(new File(imgPath + dirName)).delete();
		(new File(imgPath + dirName.substring(3))).delete();
	}

	public void mkDir(final String dirName) {
		(new File(imgPath + dirName)).mkdir();
	}

	public File fileSave(final String dirName, String fileName) {
		return new File(imgPath + dirName, fileName);
	}

	public void imgResize(final String pathIn, final String pathOut, final int FIXED_WIDTH)throws IOException, ServletException  {
		final File f = new File( imgPath + pathIn );
		final BufferedImage originalImage = ImageIO.read(f);
		final int height = (int) Math.ceil((double) (originalImage.getHeight() * FIXED_WIDTH) / originalImage.getWidth());
		final BufferedImage smallImage = new BufferedImage(FIXED_WIDTH, height, BufferedImage.TYPE_3BYTE_BGR);
		final Graphics2D smallGraphics = smallImage.createGraphics();
		smallGraphics.drawImage(originalImage.getScaledInstance(FIXED_WIDTH, height, Image.SCALE_SMOOTH), null, null);
		ImageIO.write(smallImage, "jpg", new File( imgPath + pathOut ));
	}

	public int getImgQnt(final String dirName) throws ClassNotFoundException {
		int qnt = 0;
		File dirFile = new File(imgPath+dirName);
		if (dirFile.exists()){
			String dirList[] = dirFile.list();
			qnt = dirList.length;
		}
		return qnt;
	}

    public String[] doFileFilterListing(String dirName, String ff) {
        final String fileFilter = ff;

        File dir = new File(imgPath+dirName);
        FilenameFilter filter = null;

        if (fileFilter != null) {
            filter = new FilenameFilter() {
                public boolean accept(File dir, String name) {
                    return name.startsWith(fileFilter);
                }
            };
        }

        String[] children = dir.list(filter);
        return children;
    }

    public String[] doFileListing(String dirName) {
        File dir = new File(imgPath+dirName);
        String[] fileList = dir.list();
        return fileList;
    }

    public String[] readStringsFromFile(String fileName) {
    	  BufferedReader br = null;
    	  try {
    	    br = new BufferedReader(new InputStreamReader(new FileInputStream(filePath+fileName), DEFAULT_ENCODING));
    	  }
    	  catch (UnsupportedEncodingException e) { return null; }
    	  catch (FileNotFoundException e) { return null; }

    	  String str = null;
    	  ArrayList<String> allStrings = new ArrayList<String>();
    	  try {
    	    while ((str = br.readLine()) != null) { allStrings.add(str); }
    	  }
    	  catch (IOException e) { return null; }
    	  catch (NullPointerException e) { return null; }

    	  try {
    	    br.close();
    	  }
    	  catch (IOException e) { return null; }

    	  return (String[]) allStrings.toArray(new String[0]);
    }

    public boolean createNewEmptyFile(String fileName) throws IOException {      
        boolean blnCreated = false;
        try {
            File file = new File(fileName);
            blnCreated = file.createNewFile();
        }
        catch(IOException e) {
        }

        return blnCreated;
    }

    public boolean appendStringToFile(String fileName, String dataString) throws IOException {
    	try {
    	        BufferedWriter out = new BufferedWriter(new FileWriter(filePath+fileName, true));
//    	        BufferedWriter out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(filePath+fileName), DEFAULT_ENCODING));
    	        out.write(dataString);
    	        out.write('\n');
    	        out.close();
    	}
    	catch (IOException e) { return false; }
    	return true;
    }

    public boolean renameFile(String fileName, String fileNameNew) {
    		File f = new File(fileName);
    		File f1 = new File(fileNameNew);
    	    if(!f.renameTo(f1)) return false;
    	    else return true;
    }
}
