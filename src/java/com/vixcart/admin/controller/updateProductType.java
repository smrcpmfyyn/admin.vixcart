package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessUpdateProductType;
import com.vixcart.admin.req.mod.UpdateProductType;
import com.vixcart.admin.resp.mod.UpdateProductTypeFailureResponse;
import com.vixcart.admin.resp.mod.UpdateProductTypeSuccessResponse;
import com.vixcart.admin.result.UpdateProductTypeResult;
import com.vixcart.admin.validation.UpdateProductTypeValidation;
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
public class updateProductType extends HttpServlet {

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
            String ptypeId = request.getParameter("ptypeId");
            String ptype = request.getParameter("ptype");
            String on_status = request.getParameter("on_status");
            String off_status = request.getParameter("off_status");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = ck.getValue();
            UpdateProductType req = new UpdateProductType(at, ptypeId, ptype, on_status, off_status);
            UpdateProductTypeValidation reqV = new UpdateProductTypeValidation(req);
            reqV.validation();
            System.out.println("addTypV = " + reqV);
            UpdateProductTypeResult reqR = JSONParser.parseJSONUpdateProductType(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessUpdateProductType process = new ProcessUpdateProductType(req);
                UpdateProductTypeSuccessResponse rSucc = process.processRequest();
                process.closeConnection();
                ck.setValue(rSucc.getAccessToken());
                response.addCookie(ck);
                out.write(rSucc.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                } else if (reqR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
//                    BlockAdminUser bau = new BlockAdminUser(addTyp.getAdmin_id());
//                    bau.block();
                }
                UpdateProductTypeFailureResponse rFail = new UpdateProductTypeFailureResponse(reqR, validSubmission);
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
