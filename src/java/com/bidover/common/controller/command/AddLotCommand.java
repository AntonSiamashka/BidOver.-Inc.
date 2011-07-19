/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.controller.command;

import com.bidover.common.model.bean.Lot;
import com.bidover.common.model.bean.User;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class AddLotCommand implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
        return null;
    }

    public void addLot(HttpServletRequest request, HttpServletResponse response, Lot lot) throws ServletException, IOException {
        String sales_duration = request.getParameter("sales_duration");
        String location_code = request.getParameter("location_code");
        //String condition_code = request.getParameter("condition");
        String forbid_bidding = request.getParameter("forbid_bidding");
        String fixed_price = request.getParameter("fixed_price");
        String floor_price = request.getParameter("floor_price");
        String starting_bid = request.getParameter("starting_bid");
        String fixed_price_only = request.getParameter("fixed_price_only");
        String qnt_items_available = request.getParameter("qnt_items_available");
        String currency_code = request.getParameter("currency_code");
        String payment_method = request.getParameter("payment_method");
        String payment_instructions = request.getParameter("payment_instructions");
        String free_shipping = request.getParameter("free_shipping");
        String paid_shipping = request.getParameter("paid_shipping");
        String handling_time = request.getParameter("handling_time");
        String description = request.getParameter("description");
        Double fixed_priceD = (("".equals(fixed_price) || fixed_price == null) ? 0. : Double.valueOf(fixed_price));
        Double floor_priceD = (("".equals(floor_price) || floor_price == null) ? 0 : Double.valueOf(floor_price));
        Double starting_bidD = (("".equals(starting_bid) || starting_bid == null) ? 0 : Double.valueOf(starting_bid));
        Double shipping_cost = (double) 0;
        User user = (User) request.getSession().getAttribute("profile");
        lot.setSellerId(user);
        //           lot.setBuyerId(new User());
        lot.setStatus((byte) 0);

        long millisCurrentTime = System.currentTimeMillis();
        long millisSaleDuration = Long.parseLong(sales_duration) * 24 * 3600 * 1000;
        long millisSaleClosing = millisCurrentTime + millisSaleDuration;
        lot.setDistributionTime(millisCurrentTime);
        lot.setExpirationTime(millisSaleClosing);
        lot.setLocation(location_code);
        //Condition condition = new Condition();
        //condition.setId(Integer.valueOf(condition_code));
        //lot.setCondition(condition);
        lot.setForbidBidding(Byte.valueOf(forbid_bidding));
        lot.setFixedPrice(fixed_priceD);
        lot.setFixedPriceOnly(Byte.valueOf(fixed_price_only));
        lot.setItemsQnt(Integer.valueOf(qnt_items_available));
        lot.setFloorPrice(floor_priceD);
        lot.setStartingBid(starting_bidD);
        lot.setCurrency(currency_code);
        lot.setPaymentMethod(payment_method);
        lot.setPaymentInstructions(payment_instructions);
        lot.setFreeShipping(free_shipping);
        lot.setPaidShipping(paid_shipping);
        lot.setHandlingTime(Integer.valueOf(handling_time));
        lot.setShippingCost(shipping_cost);
        lot.setDescription(description);
    }
}
