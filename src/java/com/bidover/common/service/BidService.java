/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.service;

import com.bidover.common.model.bean.Bid;
import java.util.List;

/**
 *
 * @author Jedai
 */
public interface BidService {
    
    public boolean createBidHistory(Integer lotId);
    
    public boolean placeBid(Bid bid);
    
    public List<Bid> getBidHistory(Integer lotId);
    
    public Double getMaxBid(Integer lotId);
    
}
