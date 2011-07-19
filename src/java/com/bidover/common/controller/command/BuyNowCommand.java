package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.database.dao.UserDAO;
import com.bidover.common.model.bean.User;
import com.bidover.common.database.dao.LotDAO;
import com.bidover.auto.model.bean.Auto;
import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.auto.database.dao.MakeDAO;
import com.bidover.auto.database.dao.ModelDAO;
import com.bidover.auto.database.dao.ModificationDAO;
import com.bidover.common.model.bean.Bid;
import com.bidover.common.service.BidService;
import com.bidover.common.service.BidServiceImpl;
import com.bidover.util.UtilSets;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.sql.SQLException;
import java.io.IOException;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BuyNowCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private BidService bidService = new BidServiceImpl();

    public BuyNowCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {

        String result = "{'status':0, 'message':'ERROR'}";
        try {
            int lotId = Integer.parseInt(request.getParameter("lotId"));
            double lotFixedPrice = Double.parseDouble(request.getParameter("lotFixedPrice"));
            UtilSets utl = new UtilSets();
            LotDAO lotDAO = new LotDAO();
            int qnt_items_available = lotDAO.getLotQnt(lotId);
            User user = (User) request.getSession().getAttribute("profile");
            int userId = user.getId();
            UserDAO userDAO = new UserDAO();
            double userBalance = userDAO.getUserBalance(userId);
            final int fee = 1;

            if (!utl.isInt(request.getParameter("lotQnt"))) {
                result = "{'status':0, 'message':'INCORRECT NUMBER FORMAT!'}";
            } else if (Integer.parseInt(request.getParameter("lotQnt")) < 1) {
                result = "{'status':0, 'message':'QNT LESS THAN 1!'}";
            } else if (qnt_items_available < Integer.parseInt(request.getParameter("lotQnt"))) {
                result = "{'status':0, 'message':'QNT MORE THAN AVAILABLE!'}";
            } else if (userBalance < fee) {
                result = "{'status':0, 'message':'You have not sufficient funds on the account! Your balance is: " + userBalance + " USD.'}";
            } else {


                Bid bidBean = new Bid();
                bidBean.setBid(lotFixedPrice);
                bidBean.setUser(user);
                bidBean.setDate(new Date(System.currentTimeMillis()));
                bidBean.setLotId(lotId);
                bidService.placeBid(bidBean);

                if (qnt_items_available == Integer.parseInt(request.getParameter("lotQnt"))) {
                    // CHANGE LOT STATUS
                    lotDAO.setLotAsSold(lotId);

                    // DECREMENT QNT AUTOLOTS
                    AutoDAO autoDAO = new AutoDAO();
                    MakeDAO makeDAO = new MakeDAO();
                    ModelDAO modelDAO = new ModelDAO();
                    ModificationDAO modificationDAO = new ModificationDAO();
                    Auto auto = autoDAO.findPreviewByLotId(lotId);
                    //////////////////////////!!!!!!!!!!!!!!!!
                  /*  makeDAO.decrement(auto.getIdMake().getId());
                    modelDAO.decrement(auto.getIdModel().getId());
                    modificationDAO.decrement(auto.getIdModification().getId());*/

                } else {
                    // DECREMENT QNT ITEMS OF LOT
                    int newQnt = qnt_items_available - Integer.parseInt(request.getParameter("lotQnt"));
                    lotDAO.setLotQnt(lotId, newQnt);
                }

                // DECREMENT BUYER BALANCE
                //              OR SUBSCRIPTION
                userDAO.setUserBalance(userBalance - fee, userId);

                // DELETE LOT FROM WATCHLIST
                // ...
                // SEND EMAILS TO BARGAINERS
                // ...
                result = "{'status':1}";
            }
            response.getWriter().write(result);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(BuyNowCommand.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(BuyNowCommand.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            response.getWriter().close();
        }
    }
}