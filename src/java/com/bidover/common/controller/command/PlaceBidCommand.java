
package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.model.bean.Bid;
import com.bidover.common.model.bean.User;
import com.bidover.common.service.BidService;
import com.bidover.common.service.BidServiceImpl;
import com.bidover.common.upload.FileIOSet;
import com.bidover.util.UtilSets;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class PlaceBidCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private BidService bidService = new BidServiceImpl();

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
                            int LotId = Integer.parseInt(request.getParameter("lotid"));
                            
                            
                            Bid bidBean = new Bid();
                            bidBean.setBid(bid);
                            bidBean.setUser(user);
                            bidBean.setDate(new Date(System.currentTimeMillis()));
                            bidBean.setLotId(LotId);
                            bidService.placeBid(bidBean);
                            
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