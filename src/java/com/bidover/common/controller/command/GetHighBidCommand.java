
package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.service.BidService;
import com.bidover.common.service.BidServiceImpl;
import com.bidover.util.UtilSets;
import com.bidover.util.DateSets;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetHighBidCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private BidService bidService = new BidServiceImpl();

    public GetHighBidCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

        public void execute() throws ServletException, IOException {

        String result = "{'status':0, 'message':'ERROR'}";
        try {
            int lotId = Integer.parseInt(request.getParameter("lotid"));
            long expirationTime = Long.parseLong(request.getParameter("etime"));
            int biddingRecords = 0;
            double curbid = 0;
            double outbid = 0;
            UtilSets utl = new UtilSets();
            
            curbid = bidService.getMaxBid(lotId);
            outbid = curbid + utl.getBidInc(curbid);

            long dif = expirationTime - System.currentTimeMillis();
            if (dif > 0) {
                DateSets dt = new DateSets();
                result = "{'status':1, 'timeleft':'"+dt.getTimeLeft(dif)+"', 'maxbid':'"+utl.getNumFormated(curbid)+"', 'nbids':'"+biddingRecords+"', 'outbid':'"+utl.getNumFormated(outbid)+"'}";
            }
            else result = "{'status':2, 'lotid':"+lotId+"}";
            response.getWriter().write(result);

        } finally {
             response.getWriter().close();
        }
    }
}