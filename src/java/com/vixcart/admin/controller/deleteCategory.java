
package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessDeleteCategory;
import com.vixcart.admin.req.mod.DeleteCategory;
import com.vixcart.admin.resp.mod.DeleteCategoryFailureResponse;
import com.vixcart.admin.resp.mod.DeleteCategorySuccessResponse;
import com.vixcart.admin.result.DeleteCategoryResult;
import com.vixcart.admin.validation.DeleteCategoryValidation;
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
public class deleteCategory extends HttpServlet {
   
    /** 
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code> methods.
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {

        response.setContentType("application/json");
        try (PrintWriter out = response.getWriter()) {
            String scat = request.getParameter("scat");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = ck.getValue();
            DeleteCategory req = new DeleteCategory(at, scat);
            DeleteCategoryValidation reqV = new DeleteCategoryValidation(req);
            reqV.validation();
            System.out.println("addTypV = " + reqV);
            DeleteCategoryResult reqR = JSONParser.parseJSONDeleteCategory(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessDeleteCategory process = new ProcessDeleteCategory(req);
                DeleteCategorySuccessResponse rSucc = process.processRequest();
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
                DeleteCategoryFailureResponse rFail = new DeleteCategoryFailureResponse(reqR, validSubmission);
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
