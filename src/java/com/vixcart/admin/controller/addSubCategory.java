
package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessAddSubCategory;
import com.vixcart.admin.req.mod.AddSubCategory;
import com.vixcart.admin.resp.mod.AddSubCategoryFailureResponse;
import com.vixcart.admin.resp.mod.AddSubCategorySuccessResponse;
import com.vixcart.admin.result.AddSubCategoryResult;
import com.vixcart.admin.support.controller.BlockAdminUser;
import com.vixcart.admin.validation.AddSubCategoryValidation;
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
public class addSubCategory extends HttpServlet {
   
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
            String category = request.getParameter("category");
            String subCategory = request.getParameter("subCategory");
            Cookie ck = Servlets.getCookie(request, "at");
            String at = ck.getValue();
            AddSubCategory addCateg = new AddSubCategory(at,category,subCategory);
            AddSubCategoryValidation addCategV = new AddSubCategoryValidation(addCateg);
            addCategV.validation();
            System.out.println("addCategV = " + addCategV);
            AddSubCategoryResult addCategR = JSONParser.parseJSONAddSubCategory(addCategV.toString());
            String validSubmission = addCategR.getValidationResult();
            if(validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)){
                ProcessAddSubCategory pAddTyp = new ProcessAddSubCategory(addCateg);
                AddSubCategorySuccessResponse addTypSResp = pAddTyp.processRequest();
                ck.setValue(addTypSResp.getAccessToken());
                response.addCookie(ck);
                out.write(addTypSResp.toString());
            }else if(validSubmission.startsWith(ErrMsg.ERR_ERR)){
                if (addCategR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                } else if (addCategR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
                    BlockAdminUser bau = new BlockAdminUser(addCateg.getAdmin_id());
                    bau.block();
                }
                AddSubCategoryFailureResponse usersFResp = new AddSubCategoryFailureResponse(addCategR, validSubmission);
                out.write(usersFResp.toString());
            }else{
                //exception response
            }
            out.flush();
            out.close();
        } catch (Exception ex) {
            Logger.getLogger(addSubCategory.class.getName()).log(Level.SEVERE, null, ex);
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