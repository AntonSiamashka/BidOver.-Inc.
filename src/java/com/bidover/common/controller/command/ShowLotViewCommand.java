package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.database.dao.BaseLotDAO;
import com.bidover.common.model.bean.Lot;
import com.bidover.common.database.dao.LotFactory;
import com.bidover.common.model.bean.User;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowLotViewCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public ShowLotViewCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        User user = (User) request.getSession().getAttribute("profile");
        int lotId = Integer.valueOf(request.getParameter("lotId"));
        int lotType = Integer.valueOf(request.getParameter("lotType"));
        BaseLotDAO lotDao = LotFactory.createLotDao(lotType);
        Lot lot = lotDao.findLotById(lotId);
        if (lot != null) {
            ResourceBundle set = ResourceBundle.getBundle("properties.uploading");
            String imgBuildPath = set.getString("IMG_BUILD_PATH");
            List<String> imgFiles = getFiles(imgBuildPath + lotId, lotId);
            request.setAttribute("qnt_files", imgFiles.size());
            request.setAttribute("img_files", imgFiles);
            request.setAttribute("lot", lot);
            boolean isOwner = false;
            if (user != null) {
                isOwner = lot.getSellerId().getId().equals(user.getId());
            }
            request.setAttribute("isOwner", isOwner);
        }
        request.getRequestDispatcher("./lot.jsp").forward(request, response);
    }

    private List<String> getFiles(String pathDir, Integer lotId) {
        List<String> files = new ArrayList<String>();
        File file = new File(pathDir);
        if (file.isDirectory()) {
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
