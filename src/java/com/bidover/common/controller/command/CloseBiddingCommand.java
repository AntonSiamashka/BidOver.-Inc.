
package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.database.dao.LotDAO;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class CloseBiddingCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;

    public CloseBiddingCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

        public void execute() throws ServletException, IOException {

        String result = "{'status':0, 'message':'ERROR'}";
        try {
            int lotId = Integer.parseInt(request.getParameter("lotId"));
            LotDAO lotDAO = new LotDAO();
            lotDAO.closeBidding(lotId);

            result = "{'status':1}";
            response.getWriter().write(result);
        }
        finally {
            response.getWriter().close();
        }
    }
}