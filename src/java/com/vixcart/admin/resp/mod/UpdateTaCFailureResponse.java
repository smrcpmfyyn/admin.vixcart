package com.vixcart.admin.resp.mod;

// <editor-fold defaultstate="collapsed" desc="packages">
import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.result.UpdateTaCResult;

// </editor-fold>
/**
 *
 * @author Vineeth K
 */
public class UpdateTaCFailureResponse {

    private final UpdateTaCResult req;
    private final String error;

    public UpdateTaCFailureResponse(UpdateTaCResult req, String error) {
        this.req = req;
        this.error = error;
    }

    @Override
    public String toString() {
        String json = "\"status\":\"" + ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        String resp;
        String param;
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
                case "tac":
                    param = req.getTac();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;                
                case "on_status":
                    param = req.getOn_status();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "off_status":
                    param = req.getOff_status();
                    resp = param.substring(param.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}
