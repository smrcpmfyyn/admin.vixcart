package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.result.UpdateSubCategoryResult;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateSubCategoryFailureResponse {

    private final UpdateSubCategoryResult req;
    private final String error;

    public UpdateSubCategoryFailureResponse(UpdateSubCategoryResult addTypR, String error) {
        this.req = addTypR;
        this.error = error;
    }

    @Override
    public String toString() {
        String json = "\"status\":\"" + ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        String resp;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[i];
            switch (parameter) {
                case "at":
                    String at = req.getAt();
                    resp = at.substring(at.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "admintype":
                    String admType = req.getAdmintype();
                    resp = admType.substring(admType.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "subcateg":
                    String stype = req.getSubcateg();
                    resp = stype.substring(stype.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}
