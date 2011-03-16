/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.factory;

import com.bidover.tires.controller.command.AddTiresCommand;
import com.bidover.tires.controller.command.AddWheelsCommand;
import com.bidover.tires.controller.command.AddingTiresCommand;
import com.bidover.tires.controller.command.AddingWheelsCommand;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.tires.controller.command.HelloCommand;
import com.bidover.tires.controller.command.ICommand;
import com.bidover.tires.controller.command.SearchTiresCommand;
import com.bidover.tires.controller.command.SearchWheelsCommand;
import com.bidover.tires.controller.command.SearchingTiresCommand;
import com.bidover.tires.controller.command.SearchingWheelsCommand;
import com.bidover.tires.controller.command.ShowTiresViewCommand;
import com.bidover.tires.controller.command.ShowWheelsViewCommand;

/**
 *
 * @author Jedai
 *
 * Фабрика, которая по запросу создаёт необходимую команду.
 */
public class CommandFactory {

    public enum Commands {

        ADD_TIRES,
        ADDING_TIRES,
        ADD_WHEELS,
        ADDING_WHEELS,
        SEARCH_TIRES,
        SEARCH_WHEELS,
        SEARCHING_TIRES,
        SEARCHING_WHEELS,
        SHOW_TIRES_VIEW,
        SHOW_WHEELS_VIEW
    }

    public static ICommand createCommand(HttpServletRequest request, HttpServletResponse response) {
        ICommand command = null;
        String commandStr = (String) request.getParameter("command");
        Commands commandEnum;
        try {
            commandEnum = Commands.valueOf(commandStr.toUpperCase());
            switch (commandEnum) {
                case ADD_TIRES:
                    command = new AddTiresCommand();
                    break;
                case ADD_WHEELS:
                    command = new AddWheelsCommand();
                    break;
                case ADDING_TIRES:
                    command = new AddingTiresCommand();
                    break;
                case ADDING_WHEELS:
                    command = new AddingWheelsCommand();
                    break;
                case SEARCH_TIRES:
                    command = new SearchTiresCommand();
                    break;
                case SEARCH_WHEELS:
                    command = new SearchWheelsCommand();
                    break;
                case SEARCHING_TIRES:
                    command = new SearchingTiresCommand();
                    break;
                case SEARCHING_WHEELS:
                    command = new SearchingWheelsCommand();
                    break;
                case SHOW_TIRES_VIEW:
                    command = new ShowTiresViewCommand();
                    break;
                case SHOW_WHEELS_VIEW:
                    command = new ShowWheelsViewCommand();
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
