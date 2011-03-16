/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.tires.controller.command;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.bidover.tires.properties.PathProperties;

/**
 *
 * @author Jedai
 */
public class HelloCommand implements ICommand {

    public String execute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        return PathProperties.createPathProperties().getProperty(PathProperties.INDEX_PAGE);
    }
}
