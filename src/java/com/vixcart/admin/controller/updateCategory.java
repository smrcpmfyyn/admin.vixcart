package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessUpdateCategory;
import com.vixcart.admin.req.mod.UpdateCategory;
import com.vixcart.admin.resp.mod.UpdateCategoryFailureResponse;
import com.vixcart.admin.resp.mod.UpdateCategorySuccessResponse;
import com.vixcart.admin.result.UpdateCategoryResult;
import com.vixcart.admin.validation.UpdateCategoryValidation;
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
public class updateCategory extends HttpServlet {

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
            String categ = request.getParameter("categ");
            String on_status = request.getParameter("on_status");
            String off_status = request.getParameter("off_status");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = ck.getValue();
            UpdateCategory req = new UpdateCategory(at, categ, on_status, off_status);
            UpdateCategoryValidation reqV = new UpdateCategoryValidation(req);
            reqV.validation();
            System.out.println("addTypV = " + reqV);
            UpdateCategoryResult reqR = JSONParser.parseJSONUpdateCategory(reqV.toString());
            String validmission = reqR.getValidationResult();
            if (validmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessUpdateCategory process = new ProcessUpdateCategory(req);
                UpdateCategorySuccessResponse rSucc = process.processRequest();
                ck.setValue(rSucc.getAccessToken());
                response.addCookie(ck);
                out.write(rSucc.toString());
            } else if (validmission.startsWith(ErrMsg.ERR_ERR)) {
                if (reqR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                } else if (reqR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
//                    BlockAdminUser bau = new BlockAdminUser(addTyp.getAdmin_id());
//                    bau.block();
                }
                UpdateCategoryFailureResponse rFail = new UpdateCategoryFailureResponse(reqR, validmission);
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
