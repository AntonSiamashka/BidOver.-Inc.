/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.controller.command;

import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.database.dao.LotDAO;
import com.bidover.common.model.bean.Bid;
import com.bidover.common.service.BidService;
import com.bidover.common.service.BidServiceImpl;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class ShowBidHistoryCommand implements ICommand {

    private HttpServletRequest request;
    private HttpServletResponse response;
    private BidService bidService = new BidServiceImpl();

    public ShowBidHistoryCommand(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;
    }

    public void execute() throws ServletException, IOException {
        int lotId = Integer.parseInt(request.getParameter("lotId"));
        List<Bid> bidHistory = bidService.getBidHistory(lotId);
        
    }
}