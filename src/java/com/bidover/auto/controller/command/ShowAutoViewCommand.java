
package com.bidover.auto.controller.command;

import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.common.model.bean.Lot;
import com.bidover.common.database.dao.LotDAO;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class ShowAutoViewCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowAutoViewCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        String autoIdTxt = request.getParameter("auto_id");
        AutoDAO autoDAO = new AutoDAO();
        Auto auto = autoDAO.findById(Integer.valueOf(autoIdTxt));
        if (auto != null) {
            Integer lotId = autoDAO.findLotIdByAutoId(Integer.valueOf(autoIdTxt));
            ResourceBundle set = ResourceBundle.getBundle("properties.uploading");
            String imgBuildPath = set.getString("IMG_BUILD_PATH");
            List<String> imgFiles = getFiles(imgBuildPath+"\\"+lotId, lotId);
            request.setAttribute("qnt_files", imgFiles.size());
            request.setAttribute("img_files", imgFiles);
            request.setAttribute("auto", auto);
// -------- my insert
            LotDAO lotDAO = new LotDAO();
            Lot lot = lotDAO.getLotByID(lotId);
            request.setAttribute("lot", lot);
// ---------
        }
        request.getRequestDispatcher("./auto.jsp").forward(request, response);
    }

    private List<String> getFiles(String pathDir, Integer lotId) {
        List<String> files = null;
        File file = new File(pathDir);
        if (file.isDirectory()) {
            files = new ArrayList<String>();
            File[] fileArray = file.listFiles();
            for (File tempFile : fileArray) {
                if (tempFile.getName().startsWith(String.valueOf(lotId)) && tempFile.getName().endsWith(String.valueOf("small.jpg"))) {
                    String[] s = tempFile.getName().split("_");
                    files.add(s[1]);
                }
            }
        }
        return files;
    }
}
