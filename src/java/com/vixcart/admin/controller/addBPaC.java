/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.controller;

import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessAddBPaC;
import com.vixcart.admin.req.mod.AddBPaC;
import com.vixcart.admin.resp.mod.AddBPaCFailureResponse;
import com.vixcart.admin.resp.mod.AddBPaCSuccessResponse;
import com.vixcart.admin.result.AddBPaCResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.support.controller.UserActivities;
import com.vixcart.admin.validation.AddBPaCValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author rifaie
 */
public class addBPaC extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String brand = request.getParameter("brand");
            String tac = request.getParameter("tac");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            AddBPaC req = new AddBPaC(at, brand, tac);
            AddBPaCValidation reqV = new AddBPaCValidation(req);
            reqV.validation();
            AddBPaCResult reqR = JSONParser.parseJSONAddBPaC(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAdmin_id(), req.getType(), "add_affiliate", "affiliate", "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessAddBPaC process = new ProcessAddBPaC(req);
                AddBPaCSuccessResponse addTypSResp = process.processRequest();
                process.closeConnection();
                ck.setValue(addTypSResp.getAccessToken());
                response.addCookie(ck);
                out.write(addTypSResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                    ua.setEntryStatus("invalid");
                } else if (reqR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
                    BlockAdminUser bau = new BlockAdminUser(req.getAdmin_id());
                    bau.block();
                    ua.setEntryStatus("blocked");
                } else {
                    ua.setEntryStatus("invalid");
                }
                AddBPaCFailureResponse usersFResp = new AddBPaCFailureResponse(reqR, validSubmission);
                out.write(usersFResp.toString());
            } else {
                //exception response
            }
            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(addBPaC.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
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
     *
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
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
