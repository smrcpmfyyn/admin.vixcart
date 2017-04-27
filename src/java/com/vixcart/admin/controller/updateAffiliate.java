/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.controller;

import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessUpdateAffiliate;
import com.vixcart.admin.req.mod.UpdateAffiliate;
import com.vixcart.admin.resp.mod.UpdateAffiliateFailureResponse;
import com.vixcart.admin.resp.mod.UpdateAffiliateSuccessResponse;
import com.vixcart.admin.result.UpdateAffiliateResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.support.controller.UserActivities;
import com.vixcart.admin.validation.UpdateAffiliateValidation;
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
public class updateAffiliate extends HttpServlet {

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
            String company = request.getParameter("cmp");
            String status = request.getParameter("st");
            String mpp = request.getParameter("mpp");
            String cf = request.getParameter("cf");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            Cookie ck2 = Servlets.getCookie(request, "comp");
            String ckCompany = "";
            if(ck2!=null){
                ckCompany = ck2.getValue();
            }
            UpdateAffiliate req = new UpdateAffiliate(at, ckCompany, company, status, mpp, cf);
            UpdateAffiliateValidation reqV = new UpdateAffiliateValidation(req);
            reqV.validation();
            UpdateAffiliateResult reqR = JSONParser.parseJSONUA(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAdmin_id(), req.getUtype(), "update_affiliate", "affiliate", "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessUpdateAffiliate process = new ProcessUpdateAffiliate(req);
                UpdateAffiliateSuccessResponse SResp = process.processRequest();
                process.closeConnection();
                ck.setValue(SResp.getAccessToken());
                ck2.setValue("");
                ck2.setMaxAge(0);
                response.addCookie(ck2);
                response.addCookie(ck);
                out.write(SResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                    ua.setEntryStatus("invalid");
                } else if (reqR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
                    BlockAdminUser bau = new BlockAdminUser(req.getAdmin_id());
                    bau.block();
                    ua.setEntryStatus("blocked");
//                    ua.addActivity();
                } else {
                    ua.setEntryStatus("invalid");
                }
                UpdateAffiliateFailureResponse FResp = new UpdateAffiliateFailureResponse(reqR, validSubmission);
                out.write(FResp.toString());
            } else {
                //exception response
            }
            ua.addActivity();
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(updateAffiliate.class.getName()).log(Level.SEVERE, null, ex);
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
