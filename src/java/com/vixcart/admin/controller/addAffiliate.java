/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.controller;

import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessAddAffiliate;
import com.vixcart.admin.req.mod.AddAffiliate;
import com.vixcart.admin.resp.mod.AddAffiliateFailureResponse;
import com.vixcart.admin.resp.mod.AddAffiliateSuccessResponse;
import com.vixcart.admin.result.AddAffiliateResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.support.controller.UserActivities;
import com.vixcart.admin.validation.AddAffiliateValidation;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author rifaie
 */
public class addAffiliate extends HttpServlet {

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
            DiskFileItemFactory factory = new DiskFileItemFactory();
            ServletFileUpload upload = new ServletFileUpload(factory);
            String company = "";
            String link = "";
            String fileName = "";
            long fileSize = 0;
            @SuppressWarnings("unchecked")
            List<FileItem> formItems = upload.parseRequest(request);
            if (formItems != null && formItems.size() > 0) {
                // iterates over form's fields
                for (FileItem item : formItems) {
                    if (item.isFormField()) {
                        switch (item.getFieldName()) {
                            case "cmp":
                                company = item.getString();
                                break;
                            case "lk":
                                link = item.getString();
                                break;
                        }

                    } else {
                        switch (item.getFieldName()) {
                            case "lg":
                                fileName = item.getName();
                                fileSize = item.getSize();
                        }
                    }
                }
                Cookie ck = Servlets.getCookie(request, "at");
                String at = "";
                if (ck != null) {
                    at = ck.getValue();
                }
                AddAffiliate req = new AddAffiliate(at, company, link, fileName, fileSize);
                AddAffiliateValidation reqV = new AddAffiliateValidation(req);
                reqV.validation();
                AddAffiliateResult reqR = JSONParser.parseJSONADDAFF(reqV.toString());
                String validSubmission = reqR.getValidationResult();
                UserActivities ua = new UserActivities(req.getAdmin_id(), req.getUtype(), "add_affiliate", "affiliate", "valid");
                if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                    ProcessAddAffiliate process = new ProcessAddAffiliate(req);
                    AddAffiliateSuccessResponse SResp = process.processRequest();
                    ck.setValue(SResp.getAccessToken());
                    response.addCookie(ck);
                    out.write(SResp.toString());
                } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                    if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                        // do nothing
                    } else if (reqR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
                        BlockAdminUser bau = new BlockAdminUser(req.getAdmin_id());
                        bau.block();
                        ua.setEntryStatus("blocked");
                    }
                    ua.setEntryStatus("invalid");
                    AddAffiliateFailureResponse FResp = new AddAffiliateFailureResponse(reqR, validSubmission);
                    out.write(FResp.toString());
                } else {
                    //exception response
                }
                ua.addActivity();
                out.flush();
                out.close();
            }
        } catch (Exception ex) {
            Logger.getLogger(addAffiliate.class.getName()).log(Level.SEVERE, null, ex);
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
