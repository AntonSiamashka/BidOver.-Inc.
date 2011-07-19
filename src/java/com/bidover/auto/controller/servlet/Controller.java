/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.bidover.auto.controller.servlet;

import com.bidover.common.database.connectionpool.ConnectionPool;
import com.bidover.auto.controller.factory.CommandFactory;
import com.bidover.auto.controller.command.ICommand;
import com.bidover.common.daemon.MainFrame;
import com.bidover.common.daemon.TimeDaemonThread;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Jedai
 */
public class Controller extends HttpServlet {

    @Override
    public void init() throws ServletException {
        super.init();
        MainFrame frame = new MainFrame("Daemon");
        Thread thread = new Thread(new TimeDaemonThread(frame));
        thread.setDaemon(true);
        thread.start();
    }

    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        ICommand command = CommandFactory.createCommand(request, response);
        command.execute();
    }

    @Override
    public void destroy() {
        super.destroy();
        ConnectionPool.getConnectionPool().closeAll();
    }





    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /** 
     * Handles the HTTP <code>GET</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Handles the HTTP <code>POST</code> method.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /** 
     * Returns a short description of the servlet.
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
}
