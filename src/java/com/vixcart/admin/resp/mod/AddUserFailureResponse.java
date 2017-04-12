/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

import com.vixcart.admin.message.ResponseMsg;
import com.vixcart.admin.result.AddUserResult;

/**
 * @company techvay
 * @author rifaie
 */
public class AddUserFailureResponse {
    private final AddUserResult aur;
    private final String error;

    public AddUserFailureResponse(AddUserResult aur, String error) {
        this.aur = aur;
        this.error = error;
    }
    
    @Override
    public String toString() {
        String json = "\"status\":\""+ResponseMsg.RESP_NOT_OK + "\",";
        String[] errors = error.split("#");
        
        String resp;
        for (int i = 1; i < errors.length; i++) {
            String parameter = errors[i];
            switch (parameter) {
                case "at":
                    String at = aur.getAt();
                    resp = at.substring(at.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "admintype":
                    String admType = aur.getAdmintype();
                    resp = admType.substring(admType.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "name":
                    String name = aur.getName();
                    resp = name.substring(name.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "address1":
                    String addr1 = aur.getAddress1();
                    resp = addr1.substring(addr1.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "address2":
                    String addr2 = aur.getAddress2();
                    resp = addr2.substring(addr2.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "place":
                    String place = aur.getPlace();
                    resp = place.substring(place.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "pin":
                    String pin = aur.getPin();
                    resp = pin.substring(pin.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "type":
                    String type = aur.getTypeid();
                    resp = type.substring(type.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "uname":
                    String uname = aur.getUname();
                    resp = uname.substring(uname.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
                case "email":
                    String email = aur.getEmail();
                    resp = email.substring(email.lastIndexOf(" ") + 1);
                    json += "\"" + parameter + "\"" + ":" + "\"" + resp + "\" ,";
                    break;
            }
        }
        json = json.substring(0, json.length() - 1);
        return "{" + json + "}";
    }
}
