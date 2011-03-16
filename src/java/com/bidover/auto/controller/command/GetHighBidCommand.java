
package com.bidover.auto.controller.command;

//import bidoverdb.bean.User;
import com.bidover.common.upload.FileIOSet;
import com.bidover.util.UtilSets;
import com.bidover.util.DateSets;
//import java.util.logging.Level;
//import java.util.logging.Logger;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class GetHighBidCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

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
            String buyerId = null;
            UtilSets utl = new UtilSets();
            String fileName = lotId+"/bidhistory.dat";
            FileIOSet fio = new FileIOSet();
            if (fio.fileExists(fileName)) {
                String[] allStrings = fio.readStringsFromFile(fileName);
                biddingRecords = allStrings.length;
                String str = allStrings[biddingRecords-1];
                String parts[] = str.split(";");
                curbid = Double.parseDouble(parts[2]);
                outbid = curbid + utl.getBidInc(curbid);
                buyerId = parts[1];
            }

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