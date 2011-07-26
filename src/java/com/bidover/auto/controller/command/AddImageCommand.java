package com.bidover.auto.controller.command;

import com.bidover.auto.database.dao.AutoDAO;
import com.sun.image.codec.jpeg.JPEGCodec;
import com.sun.image.codec.jpeg.JPEGEncodeParam;
import com.sun.image.codec.jpeg.JPEGImageEncoder;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

public class AddImageCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private String imgPath;
    private String imgBuildPath;
    private String tempDir;

    public AddImageCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
        ResourceBundle set = ResourceBundle.getBundle("properties.uploading");
        imgPath = set.getString("IMG_PATH");
        imgBuildPath = set.getString("IMG_BUILD_PATH");
        tempDir = set.getString("TEMP_DIR");
    }

    public void execute() throws ServletException, IOException {
        //проверяем является ли полученный запрос multipart/form-data
        boolean isMultipart = ServletFileUpload.isMultipartContent(request);
        if (!isMultipart) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
            return;
        }

        // Создаём класс фабрику
        DiskFileItemFactory factory = new DiskFileItemFactory();

        // Максимальный буфера данных в байтах,
        // при его привышении данные начнут записываться на диск во временную директорию
        // устанавливаем один мегабайт
        factory.setSizeThreshold(1024 * 1024);

        // устанавливаем временную директорию
        factory.setRepository(new File(tempDir));

        //Создаём сам загрузчик
        ServletFileUpload upload = new ServletFileUpload(factory);

        //максимальный размер данных который разрешено загружать в байтах
        //по умолчанию -1, без ограничений. Устанавливаем 10 мегабайт.
        upload.setSizeMax(1024 * 1024 * 10);

        try {
            List items = upload.parseRequest(request);
            Iterator iter = items.iterator();

            while (iter.hasNext()) {
                FileItem item = (FileItem) iter.next();

                if (item.isFormField()) {
                    //если принимаемая часть данных является полем формы
                    processFormField(item);
                } else {
                    //в противном случае рассматриваем как файл
                    processUploadedFile(item);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            return;
        }

        //перенаправляем на страницу с уведомлением о успешной загрузке файлов
        response.sendRedirect("Controller.do?command=SHOW_AUTO_BY_USER");
        //request.getRequestDispatcher("/profile.jsp").forward(request, response);
    }

    /**
     * Сохраняет файл на сервере, в папке upload.
     * Сама папка должна быть уже создана.
     *
     * @param item
     * @throws Exception
     */
    private void processUploadedFile(FileItem item) throws Exception {
        //если если размер равен нулю
        //скорее всего пользователь оставил поле пустым
        //игнорируем его
        if (item.getSize() == 0) {
            return;
        }

        File uploadetFile = null;
        File uploadetBuildFile = null;
        //выбираем файлу имя, пока не найдём свободное
        String imgPathTemp = "";
        String imgBuildPathTemp = "";
        int i = 1;
        AutoDAO autoDAO = new AutoDAO();
        Integer autoId = Integer.valueOf(request.getParameter("id"));
        Integer lotId = autoDAO.findLotIdByAutoId(autoId);
        File lotDir = new File(imgPath + autoId+"\\");
        File lotBuildDir = new File(imgBuildPath + autoId+"\\");
        if(!lotDir.exists()){
            lotDir.mkdir();
        }
        if(!lotBuildDir.exists()){
            lotBuildDir.mkdir();
        }
        
        do {
            imgPathTemp = imgPath + autoId + "\\" + lotId + "_" + i + "_" + ".jpg";
            imgBuildPathTemp = imgBuildPath + autoId + "\\" + lotId + "_" + i + "_" + ".jpg";
            uploadetFile = new File(imgPathTemp);
            uploadetBuildFile = new File(imgBuildPathTemp);
            i++;
        } while (uploadetFile.exists());
        
        //uploadetFile = new File("C:\\TMP\\1.jpg");
        //uploadetBuildFile = new File("C:\\TMP\\build\\1.jpg");

        //создаём файл
        uploadetFile.createNewFile();
        uploadetBuildFile.createNewFile();
        //записываем в него данные

        item.write(uploadetFile);
        item.write(uploadetBuildFile);
        InputStream t = new FileInputStream(new File(imgPathTemp));

        ByteArrayOutputStream out = this.createThumbImage(t, 100, 100);
        writeImg(out, imgPathTemp, imgBuildPathTemp);
    }

    /**
     * Выводит на консоль имя параметра и значение
     * @param item
     */
    private void processFormField(FileItem item) {
        System.out.println(item.getFieldName() + "=" + item.getString());
    }

    private ByteArrayOutputStream createThumbImage(InputStream p_image, int p_width, int p_height) throws Exception {

        InputStream imageStream = new BufferedInputStream(p_image);
        Image image = (Image) ImageIO.read(imageStream);

        int thumbWidth = p_width;
        int thumbHeight = p_height;

        // Make sure the aspect ratio is maintained, so the image is not skewed
        double thumbRatio = (double) thumbWidth / (double) thumbHeight;
        int imageWidth = image.getWidth(null);
        int imageHeight = image.getHeight(null);
        double imageRatio = (double) imageWidth / (double) imageHeight;
        if (thumbRatio < imageRatio) {
            thumbHeight = (int) (thumbWidth / imageRatio);
        } else {
            thumbWidth = (int) (thumbHeight * imageRatio);
        }

        // Draw the scaled image
        BufferedImage thumbImage = new BufferedImage(thumbWidth,
                thumbHeight, BufferedImage.TYPE_INT_RGB);
        Graphics2D graphics2D = thumbImage.createGraphics();
        graphics2D.setRenderingHint(RenderingHints.KEY_INTERPOLATION,
                RenderingHints.VALUE_INTERPOLATION_BILINEAR);
        graphics2D.drawImage(image, 0, 0, thumbWidth, thumbHeight, null);

        // Write the scaled image to the outputstream
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        JPEGImageEncoder encoder = JPEGCodec.createJPEGEncoder(out);
        JPEGEncodeParam param = encoder.getDefaultJPEGEncodeParam(thumbImage);
        int quality = 100; // Use between 1 and 100, with 100 being highest quality
        quality = Math.max(0, Math.min(quality, 100));
        param.setQuality((float) quality / 100.0f, false);
        encoder.setJPEGEncodeParam(param);
        encoder.encode(thumbImage);
        ImageIO.write(thumbImage, "jpg", out);
        return out;
    }

    void writeImg(ByteArrayOutputStream out, String path, String pathBuild) throws FileNotFoundException, IOException {
        path = path.substring(0, path.length() - 4) + "small.jpg";
        File f = new File(path);
        FileOutputStream fw = new FileOutputStream(f);
        pathBuild = pathBuild.substring(0, pathBuild.length() - 4) + "small.jpg";
        File f1 = new File(pathBuild);
        FileOutputStream fw1 = new FileOutputStream(f1);
        fw.write(out.toByteArray());
        fw1.write(out.toByteArray());
        fw.flush();
        fw1.flush();
        fw.close();
        fw1.close();
        out.flush();
        out.flush();
        out.close();
    }
}
