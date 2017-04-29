/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.controller;

import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessGetBrands;
import com.vixcart.admin.req.mod.GetBrands;
import com.vixcart.admin.resp.mod.GetBrandsFailureResponse;
import com.vixcart.admin.resp.mod.GetBrandsSuccessResponse;
import com.vixcart.admin.result.GetBrandsResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.support.controller.UserActivities;
import com.vixcart.admin.validation.GetBrandsValidation;
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
public class getBrands extends HttpServlet {

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
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            GetBrands req = new GetBrands(at);
            GetBrandsValidation reqV = new GetBrandsValidation(req);
            reqV.validation();
            GetBrandsResult reqR = JSONParser.parseJSONGetBrands(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAdmin_id(), req.getType(), "get_brands", "product management", "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessGetBrands process = new ProcessGetBrands(req);
                GetBrandsSuccessResponse rSucc = process.processRequest();
                process.closeConnection();
                ck.setValue(rSucc.getAccessToken());
                response.addCookie(ck);
                out.write(rSucc.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
//                    ua.setEntryStatus("invalid");
                } else if (reqR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
                    BlockAdminUser bau = new BlockAdminUser(req.getAdmin_id());
                    bau.block();
                    ua.setEntryStatus("blocked");
                    ua.addActivity();
                } else {
//                    ua.setEntryStatus("invalid");
                }
                GetBrandsFailureResponse rFail = new GetBrandsFailureResponse(reqR, validSubmission);
                out.write(rFail.toString());
            } else {
                //exception response
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(addUserType.class.getName()).log(Level.SEVERE, null, ex);
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
