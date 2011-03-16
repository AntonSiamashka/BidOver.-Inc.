/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package test;

import com.bidover.tires.database.dao.TiresContourDAO;
import com.bidover.tires.database.dao.TiresDiameterDAO;
import com.bidover.tires.database.dao.TiresSeasonDAO;
import com.bidover.tires.database.dao.TiresWidthDAO;
import com.bidover.tires.database.dao.WheelsBCDDAO;
import com.bidover.tires.database.dao.WheelsDiameterDAO;
import com.bidover.tires.database.dao.WheelsEDDAO;
import com.bidover.tires.database.dao.WheelsWidthDAO;
import com.bidover.tires.model.bean.TiresContour;
import com.bidover.tires.model.bean.TiresDiameter;
import com.bidover.tires.model.bean.TiresSeason;
import com.bidover.tires.model.bean.TiresWidth;
import com.bidover.tires.model.bean.WheelsBCD;
import com.bidover.tires.model.bean.WheelsDiameter;
import com.bidover.tires.model.bean.WheelsED;
import com.bidover.tires.model.bean.WheelsWidth;

/**
 *
 * @author Jedai
 */
public class Test {

    public static void main(String[] args) {
        //addTires();
        //System.out.println("Tires end");
        //addWheels();
        //System.out.println("Wheels end");
    }

    public static void addTires() {

        Double[] widthArray = new Double[]{7d, 7.5, 30d, 31d, 32d, 33d, 35d, 135d, 145d, 155d, 165d, 175d, 185d, 195d, 205d, 215d, 225d, 235d, 245d, 255d, 265d, 275d, 285d, 295d, 305d, 315d, 325d, 345d};
        Double[] contourArray = new Double[]{9.5, 10.5, 11.5, 12.5, 13.5, 15.5, 25d, 30d, 25d, 40d, 45d, 50d, 55d, 60d, 65d, 70d, 75d, 80d, 85d};
        Double[] diameterArray = new Double[]{12d, 13d, 14d, 15d, 16d, 16.5, 17d, 18d, 19d, 20d, 21d, 22d, 24d, 26d};
        String[] seasonArray = new String[]{"лето", "зима", "всесезонная"};

        for (Double el : widthArray) {
            TiresWidthDAO elDao = new TiresWidthDAO();
            TiresWidth cEl = new TiresWidth();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
        for (Double el : contourArray) {
            TiresContourDAO elDao = new TiresContourDAO();
            TiresContour cEl = new TiresContour();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
        for (Double el : diameterArray) {
            TiresDiameterDAO elDao = new TiresDiameterDAO();
            TiresDiameter cEl = new TiresDiameter();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
        for (String el : seasonArray) {
            TiresSeasonDAO elDao = new TiresSeasonDAO();
            TiresSeason cEl = new TiresSeason();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
    }

    public static void addWheels() {
        Double[] widthArray = new Double[]{4.5, 5d, 5.5, 6d, 6.5, 7d, 7.5, 8d, 8.5, 9d, 9.5, 10d, 10.5, 11d, 11.5, 12d, 12.5, 13d};
        Double[] diameterArray = new Double[]{13d, 14d, 15d, 16d, 17d, 18d, 19d, 20d, 21d, 22d, 24d};
        String[] bcdArray = new String[]{"3*112", "4*98", "4*100", "4*108", "4*114,3", "5*98", "5*100", "5*105", "5*108", "5*110", "5*112", "5*114,3",
            "5*115", "5*118", "5*120", "5*120,65", "5*127", "5*130", "5*135", "5*139,7", "5*140",
            "5*150", "5*160", "5*165", "6*114,3", "6*127", "6*130", "6*135", "6*139,7", "8*165,1"};
        String[] edArray = new String[]{"меньше 0", "0-10", "10-20", "20-30", "30-40", "40-50", "больше 50"};
        for (Double el : widthArray) {
            WheelsWidthDAO elDao = new WheelsWidthDAO();
            WheelsWidth cEl = new WheelsWidth();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
        for (Double el : diameterArray) {
            WheelsDiameterDAO elDao = new WheelsDiameterDAO();
            WheelsDiameter cEl = new WheelsDiameter();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
        for (String el : bcdArray) {
            WheelsBCDDAO elDao = new WheelsBCDDAO();
            WheelsBCD cEl = new WheelsBCD();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
        for (String el : edArray) {
            WheelsEDDAO elDao = new WheelsEDDAO();
            WheelsED cEl = new WheelsED();
            cEl.setValue(el);
            System.out.println(elDao.add(cEl));
        }
    }
}
