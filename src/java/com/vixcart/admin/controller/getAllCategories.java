package com.vixcart.admin.controller;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.CorrectMsg;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.processreq.ProcessGetCategories;
import com.vixcart.admin.req.mod.GetCategories;
import com.vixcart.admin.resp.mod.CategoryFailureResponse;
import com.vixcart.admin.resp.mod.CategorySuccessResponse;
import com.vixcart.admin.result.GetCategoriesResult;
import com.vixcart.admin.validation.GetCategoriesValidation;
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
public class getAllCategories extends HttpServlet {

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
            String at = ck.getValue();
//            System.out.println("at = " + at);
            GetCategories sCategs = new GetCategories(at);
            GetCategoriesValidation sCategV = new GetCategoriesValidation(sCategs);
            sCategV.validation();
//            out.println("sCategV = " + sCategV);
            GetCategoriesResult typeR = JSONParser.parseJSONGCateg(sCategV.toString());
            String validSubmission = typeR.getValidationResult();
            if (validSubmission.startsWith(CorrectMsg.CORRECT_MESSAGE)) {
                ProcessGetCategories pCategs = new ProcessGetCategories(sCategs);
                CategorySuccessResponse sCategsSResp = pCategs.processRequest();
                ck.setValue(sCategsSResp.getAccessToken());
                response.addCookie(ck);
                System.out.println("sCategsSResp = " + sCategsSResp);
                out.write(sCategsSResp.toString());
            } else if (validSubmission.startsWith(ErrMsg.ERR_ERR)) {
                if (typeR.getAt().startsWith(ErrMsg.ERR_MESSAGE)) {
                    // do nothing
                } else if (typeR.getAdmintype().startsWith(ErrMsg.ERR_MESSAGE)) {
//                    BlockAdminUser bau = new BlockAdminUser(sCategs.getAdmin_id());
//                    bau.block();
                }
                CategoryFailureResponse usersFResp = new CategoryFailureResponse(typeR, validSubmission);
                out.write(usersFResp.toString());
            } else {
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
