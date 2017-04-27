
package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessGetSubCategories;
import com.vixcart.admin.req.mod.GetSubCategories;
import com.vixcart.admin.resp.mod.SubCategoryFailureResponse;
import com.vixcart.admin.resp.mod.SubCategorySuccessResponse;
import com.vixcart.admin.result.GetSubCategoriesResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.support.controller.UserActivities;
import com.vixcart.admin.validation.GetSubCategoriesValidation;
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
public class getAllSubCategories extends HttpServlet {
   
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
            Cookie ck = Servlets.getCookie(request, "at");
            String at = "";
            if (ck != null) {
                at = ck.getValue();
            }
            GetSubCategories req = new GetSubCategories(at);
            GetSubCategoriesValidation reqV = new GetSubCategoriesValidation(req);
            reqV.validation();
            GetSubCategoriesResult reqR = JSONParser.parseJSONGSubCategs(reqV.toString());
            String validSubmission = reqR.getValidationResult();
            UserActivities ua = new UserActivities(req.getAdmin_id(), req.getType(), "get_all_sub_categories", "product management", "valid");
            if(validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                ProcessGetSubCategories process = new ProcessGetSubCategories(req);
                SubCategorySuccessResponse sCategsSResp = process.processRequest();
                process.closeConnection();
                ck.setValue(sCategsSResp.getAccessToken());
                response.addCookie(ck);
                out.write(sCategsSResp.toString());
            }else if(validSubmission.startsWith(ErrMsg.ERR_ERR)){
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
                SubCategoryFailureResponse usersFResp = new SubCategoryFailureResponse(reqR, validSubmission);
                out.write(usersFResp.toString());
            }else{
                //exception response
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(superUserTypes.class.getName()).log(Level.SEVERE, null, ex);
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
