/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.factory;

import com.bidover.common.controller.command.CloseBiddingCommand;
import com.bidover.common.controller.command.BuyNowCommand;
import com.bidover.common.controller.command.GetHighBidCommand;
import com.bidover.auto.controller.command.*;
import com.bidover.common.controller.command.PlaceBidCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Nomad
 */
public class CommandFactory {

    public enum Commands {
        CHG_LNG,
        SIGNUP,
        LOGIN,
        REGISTRATION,
        ADD_AUTO,
        ADD_BUTTON_TO_ADD,
        ADD_MAKE_TO_ADD,
        ADD_MODEL_TO_ADD,
        ADD_MODEL_TO_SEARCH,
        ADD_MODIFICATION_TO_ADD,
        ADD_OPTION_TO_ADD,
        ADD_OTHER_TO_ADD,
        ADD_SALES_DETAILS_TO_ADD,
        ADD_YEAR_TO_SEARCH,
        CHANGE_ATD,
        CHANGE_LOCATION,
        CHANGE_USER_INFO,
        LOG_OUT,
        SEARCH,
        SHOW_AUTO_BY_MAKE,
        SHOW_AUTO_BY_USER,
        SHOW_AUTO_VIEW,
        SHOW_SEARCH,
        REMIND_PASSWORD,
        CHANGE_PASSWORD,
        ADD_IMAGE,
        SHOW_PREVIEW,
        IS_MAIL_EXISTS,
        PLACE_BID,
        GET_HIGH_BID,
        BUY_NOW,
        CLOSE_BIDDING,
        HELP;
    }
 
    public static ICommand createCommand(HttpServletRequest request, HttpServletResponse response) {
        ICommand command = null;
        String commandStr = (String) request.getParameter("command");
        Commands commandEnum;
        try {
            commandEnum = Commands.valueOf(commandStr.toUpperCase());
            switch (commandEnum) {
                case CHG_LNG:
                    command = new ChangeLanguageCommand(request, response);
                    break;
                case SIGNUP:
                    command = new SignupCommand(request, response);
                    break;
                case LOGIN:
                    command = new LoginCommand(request, response);
                    break;
                case REGISTRATION:
                    command = new RegistrationCommand(request, response);
                    break;
                case ADD_AUTO:
                    command = new AddAutoCommand(request, response);
                    break;
                case ADD_BUTTON_TO_ADD:
                    command = new AddButtonToAddCommand(request, response);
                    break;
                case ADD_MAKE_TO_ADD:
                    command = new AddMakeToAddCommand(request, response);
                    break;
                case ADD_MODEL_TO_ADD:
                    command = new AddModelToAddCommand(request, response);
                    break;
                case ADD_MODEL_TO_SEARCH:
                    command = new AddModelToSearchCommand(request, response);
                    break;
                case ADD_MODIFICATION_TO_ADD:
                    command = new AddModificationToAddCommand(request, response);
                    break;
                case ADD_OPTION_TO_ADD:
                    command = new AddOptionToAddCommand(request, response);
                    break;
                case ADD_OTHER_TO_ADD:
                    command = new AddOtherToAddCommand(request, response);
                    break;
                case ADD_SALES_DETAILS_TO_ADD:
                    command = new AddSalesDetailsToAddCommand(request, response);
                    break;
                case ADD_YEAR_TO_SEARCH:
                    command = new AddYearToSearchCommand(request, response);
                    break;
                case CHANGE_ATD:
                    command = new ChangeATDCommand(request, response);
                    break;
                case CHANGE_LOCATION:
                    command = new ChangeLocationCommand(request, response);
                    break;
                case CHANGE_USER_INFO:
                    command = new ChangeUserInfoCommand(request, response);
                    break;
                case LOG_OUT:
                    command = new LogoutCommand(request, response);
                    break;
                case SEARCH:
                    command = new SearchCommand(request, response);
                    break;
                case SHOW_AUTO_BY_MAKE:
                    command = new ShowAutoByMakeCommand(request, response);
                    break;
                case SHOW_AUTO_BY_USER:
                    command = new ShowAutoByUserCommand(request, response);
                    break;
                case SHOW_AUTO_VIEW:
                    command = new ShowAutoViewCommand(request, response);
                    break;
                case SHOW_SEARCH:
                    command = new ShowSearchCommand(request, response);
                    break;
                case REMIND_PASSWORD:
                    command = new RemindPasswordCommand(request, response);
                    break;
                case CHANGE_PASSWORD:
                    command = new ChangePasswordCommand(request, response);
                    break;
                case ADD_IMAGE:
                    command = new AddImageCommand(request, response);
                    break;
                case SHOW_PREVIEW:
                    command = new ShowPreviewCommand(request, response);
                    break;
                case IS_MAIL_EXISTS:
                    command = new IsMailExistsCommand(request, response);
                    break;
                case PLACE_BID:
                    command = new PlaceBidCommand(request, response);
                    break;
                case GET_HIGH_BID:
                    command = new GetHighBidCommand(request, response);
                    break;
               case BUY_NOW:
                    command = new BuyNowCommand(request, response);
                    break;
               case CLOSE_BIDDING:
                    command = new CloseBiddingCommand(request, response);
                    break;
                case HELP:
                    command = new HelpCommand(request, response);
                    break;
                default:
                    command = new HelloCommand(request, response);
                    break;
            }
        } catch (Exception ex) {
            command = new HelloCommand(request, response);
        }
        return command;
    }
}
