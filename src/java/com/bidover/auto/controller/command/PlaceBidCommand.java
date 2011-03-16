
package com.bidover.auto.controller.command;

import com.bidover.common.model.bean.User;
import com.bidover.common.upload.FileIOSet;
import com.bidover.util.UtilSets;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaceBidCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public PlaceBidCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

        public void execute() throws ServletException, IOException {

        String result = "{'status':0, 'message':'ERROR'}";
        try {
            UtilSets utl = new UtilSets();

            if (!utl.isDouble(request.getParameter("maxbid"))) result = "{'status':0, 'message':'Wrong number format!'}";
            else {
                    double bid = Double.parseDouble(request.getParameter("maxbid"));
                    double outbid = Double.parseDouble(request.getParameter("outbid"));
                    
                    if (bid < outbid) result = "{'status':0, 'message':'Your bid should be higher or equals outbid!'}";
                    else {
                            User user = (User) request.getSession().getAttribute("profile");
                            int UserId = user.getId();
                            int LotId = Integer.parseInt(request.getParameter("lotid"));
                            FileIOSet fio = new FileIOSet();
                            String fileName = LotId+"/bidhistory.dat";
                            String dataString = System.currentTimeMillis()+";"+UserId+";"+bid;
                            fio.appendStringToFile(fileName, dataString);

                            result = "{'status':1, 'lotid':'"+LotId+"'}";
                    }
            }
            response.getWriter().write(result);

//        } catch (NumberFormatException ex) {
//            Logger.getLogger(PlaceBidCommand.class.getName()).log(Level.SEVERE, null, ex);
//            result = "{'status':0, 'message':'Wrong number format!'}";
//            response.getWriter().write(result);
        } finally {
             response.getWriter().close();
        }
    }
}