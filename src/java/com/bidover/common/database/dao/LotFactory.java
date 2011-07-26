/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.common.database.dao;

import com.bidover.auto.database.dao.AutoDAO;
import com.bidover.common.constants.CommonConstants;

/**
 *
 * @author Jedai
 */
public class LotFactory {

    public static BaseLotDAO createLotDao(int lotType) {
        switch (lotType) {
            case CommonConstants.AUTO_LOT_TYPE:
                return new AutoDAO();
        }
        return null;
    }
}
