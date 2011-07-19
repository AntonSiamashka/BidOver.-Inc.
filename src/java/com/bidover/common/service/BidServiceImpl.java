/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.service;

import com.bidover.common.database.dao.BidHistoryDAO;
import com.bidover.common.model.bean.Bid;
import java.util.List;

/**
 *
 * @author Jedai
 */
public class BidServiceImpl implements BidService {

    private BidHistoryDAO bidHistoryDAO = new BidHistoryDAO();
    
    public boolean createBidHistory(Integer lotId) {
        return bidHistoryDAO.createBidHistory(lotId);
    }

    public boolean placeBid(Bid bid) {
        return bidHistoryDAO.addBid(bid);
    }

    public List<Bid> getBidHistory(Integer lotId) {
        return bidHistoryDAO.findAll(lotId);
    }

    public Double getMaxBid(Integer lotId) {
        return bidHistoryDAO.getMaxBid(lotId);
    }
    
}
