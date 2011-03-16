
package com.bidover.common.daemon;


import com.bidover.common.model.bean.Lot;
import com.bidover.common.database.dao.LotDAO;
import java.util.Date;
import javax.servlet.http.HttpServletRequest;


public class TimeDaemonThread implements Runnable {

    private LotDAO lotDAO;
    private MainFrame frame;
    private static long MIN_DURATION = 3600 * 24 * 3 * 1000;
    private HttpServletRequest request;

    public TimeDaemonThread(MainFrame frame) {
        this.frame = frame;
    }

    @Override
    public void run() {
        try {
            Lot lot = null;
            long current_time = System.currentTimeMillis();
            long interval;
            frame.insertString("* thread started at:" + new Date(current_time));
            lotDAO = new LotDAO();
           /* while (true) {
                lot = lotDAO.getNextLot();
                if (lot != null) {
                    current_time = System.currentTimeMillis();
                    frame.insertString("Current time: " + new Date(current_time));
                    frame.insertString("Next closing lot: " + lot.getId());
                    frame.insertString("Next min time: " + new Date(lot.getExpirationTime()));
                    interval = lot.getExpirationTime() + 60000 - current_time;
                    if (interval <= 0) {
                        // CLOSE BIDDING
                        doAction();                     
                        lotDAO.closeBidding(lot.getId());
                    } else if (interval < MIN_DURATION) {
                        frame.insertString("Sleep till: " + new Date(current_time + interval));
                        frame.insertString("Z-z-z-z...");
                        Thread.sleep(interval);
                    } else {
                        frame.insertString("Sleep till: " + new Date(current_time + MIN_DURATION));
                        frame.insertString("Z-z-z-z...");
                        Thread.sleep(MIN_DURATION);
                    }
                } else {
                    frame.insertString("no data");
                    frame.insertString("Sleep till: " + new Date(current_time + MIN_DURATION));
                    frame.insertString("Z-z-z-z...");
                    Thread.sleep(MIN_DURATION);
                }
            }*/

        } catch (Exception e) {
        }

    }

    private void doAction() {
        frame.insertString("ACTION!!!");

    }
}
