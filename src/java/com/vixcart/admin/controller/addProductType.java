package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessAddProductType;
import com.vixcart.admin.req.mod.AddProductType;
import com.vixcart.admin.resp.mod.AddProductTypeFailureResponse;
import com.vixcart.admin.resp.mod.AddProductTypeSuccessResponse;
import com.vixcart.admin.result.AddProductTypeResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.support.controller.UserActivities;
import com.vixcart.admin.validation.AddProductTypeValidation;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class addProductType extends HttpServlet {

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
            String pType = request.getParameter("ptype");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            AddProductType req = new AddProductType(at, pType);
            AddProductTypeValidation reqV = new AddProductTypeValidation(req);
            reqV.validation();
            AddProductTypeResult reqR = JSONParser.parseJSONAddProductType(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAdmin_id(), req.getType(), "add_product_type", "product management", "valid");
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessAddProductType process = new ProcessAddProductType(req);
                AddProductTypeSuccessResponse rSucc = process.processRequest();
                process.closeConnection();
                ck.setValue(rSucc.getAccessToken());
                response.addCookie(ck);
                out.write(rSucc.toString());
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
                AddProductTypeFailureResponse rFail = new AddProductTypeFailureResponse(reqR, validSubmission);
                out.write(rFail.toString());
            } else {
                //exception response
            }
            ua.addActivity();
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
