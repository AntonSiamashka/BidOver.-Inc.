/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.detail.controller.factory;

import com.bidover.detail.controller.command.AddDetailCommand;
import com.bidover.detail.controller.command.AddingDetailCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.detail.controller.command.HelloCommand;
import com.bidover.detail.controller.command.ICommand;
import com.bidover.detail.controller.command.SearchDetailCommand;
import com.bidover.detail.controller.command.SearchingDetailCommand;
import com.bidover.detail.controller.command.ShowDetailViewCommand;
import com.bidover.detail.controller.command.ShowItemCommand;
import com.bidover.detail.controller.command.ShowModelCommand;
import com.bidover.detail.controller.command.ShowSearchItemCommand;
import com.bidover.detail.controller.command.ShowSearchModelCommand;
import com.bidover.detail.controller.command.ShowSearchSubCategoryCommand;
import com.bidover.detail.controller.command.ShowSearchYearCommand;
import com.bidover.detail.controller.command.ShowSubCategoryCommand;
import com.bidover.detail.controller.command.ShowYearCommand;

/**
 *
 * @author Jedai
 *
 * Фабрика, которая по запросу создаёт необходимую команду.
 */
public class CommandFactory {

    public enum Commands {

        ADD_DETAIL,
        SHOW_SUBCATEGORY,
        SHOW_ITEM,
        SHOW_MODEL,
        SHOW_YEAR,
        ADDING_DETAIL,
        SEARCH_DETAIL,
        SHOW_SEARCH_SUBCATEGORY,
        SHOW_SEARCH_ITEM,
        SHOW_SEARCH_MODEL,
        SHOW_SEARCH_YEAR,
        SEARCHING_DETAIL,
        SHOW_DETAIL_VIEW
    }

    public static ICommand createCommand(HttpServletRequest request, HttpServletResponse response) {
        ICommand command = null;
        String commandStr = (String) request.getParameter("command");
        Commands commandEnum;
        try {
            commandEnum = Commands.valueOf(commandStr.toUpperCase());
            switch (commandEnum) {
                case ADD_DETAIL:
                    command = new AddDetailCommand();
                    break;
                case SHOW_SUBCATEGORY:
                    command = new ShowSubCategoryCommand();
                    break;
                case SHOW_ITEM:
                    command = new ShowItemCommand();
                    break;
                case SHOW_MODEL:
                    command = new ShowModelCommand();
                    break;
                case SHOW_YEAR:
                    command = new ShowYearCommand();
                    break;
                case ADDING_DETAIL:
                    command = new AddingDetailCommand();
                    break;
                case SEARCH_DETAIL:
                    command = new SearchDetailCommand();
                    break;
                case SHOW_SEARCH_SUBCATEGORY:
                    command = new ShowSearchSubCategoryCommand();
                    break;
                case SHOW_SEARCH_ITEM:
                    command = new ShowSearchItemCommand();
                    break;
                case SHOW_SEARCH_MODEL:
                    command = new ShowSearchModelCommand();
                    break;
                case SHOW_SEARCH_YEAR:
                    command = new ShowSearchYearCommand();
                    break;
                case SEARCHING_DETAIL:
                    command = new SearchingDetailCommand();
                    break;
                case SHOW_DETAIL_VIEW:
                    command = new ShowDetailViewCommand();
                    break;
            }
        } catch (NullPointerException ex) {
            command = new HelloCommand();
        } catch (IllegalArgumentException ex) {
            command = new HelloCommand();
        }
        return command;
    }
}
