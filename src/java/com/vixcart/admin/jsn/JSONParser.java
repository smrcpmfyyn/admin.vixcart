/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.jsn;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.mongo.mod.UTA;
import com.vixcart.admin.mongo.mod.UUA;
import com.vixcart.admin.mongo.mod.VerifyToken;
import com.vixcart.admin.req.mod.ActivityFilter;
import com.vixcart.admin.resp.mod.Activity;
import com.vixcart.admin.resp.mod.Affiliates;
import com.vixcart.admin.result.AddAffiliateResult;
import com.vixcart.admin.result.AddAffiliateUserResult;
import com.vixcart.admin.result.AddPremiumPaymentResult;
import com.vixcart.admin.result.AddUserResult;
import com.vixcart.admin.result.AddUserTypeResult;
import com.vixcart.admin.result.CheckUserIDResult;
import com.vixcart.admin.result.CheckUserTypeResult;
import com.vixcart.admin.result.DeleteAffiliateResult;
import com.vixcart.admin.result.DeleteAffiliateUsersResult;
import com.vixcart.admin.result.EditUserResult;
import com.vixcart.admin.result.EmpReportResult;
import com.vixcart.admin.result.FAUAResult;
import com.vixcart.admin.result.GetActivePremiumPaymentsResult;
import com.vixcart.admin.result.SearchAffiliatesResult;
import com.vixcart.admin.result.GetAffiliateResult;
import com.vixcart.admin.result.GetAffiliateUsersResult;
import com.vixcart.admin.result.GetAffiliatesResult;
import com.vixcart.admin.result.GetAllAffiliatesResult;
import com.vixcart.admin.result.GetAllPremiumPaymentsResult;
import com.vixcart.admin.result.GetNoOfMembersResult;
import com.vixcart.admin.result.GetNoOfWebsitesResult;
import com.vixcart.admin.result.GetPinResult;
import com.vixcart.admin.result.GetTotalPremiumPaymentsResult;
import com.vixcart.admin.result.GetUserIdsResult;
import com.vixcart.admin.result.GetUserResult;
import com.vixcart.admin.result.LogResult;
import com.vixcart.admin.result.RPResult;
import com.vixcart.admin.result.SuperUserTypesResult;
import com.vixcart.admin.result.UpdateAffiliateResult;
import com.vixcart.admin.result.UpdateUserTypeResult;
import com.vixcart.admin.result.UserTypesResult;
import com.vixcart.admin.result.UsersResult;
import com.vixcart.admin.validation.CheckUserTypeValidation;
import com.vixcart.admin.validation.EditUserValidation;
import com.vixcart.admin.validation.UsersValidation;
import java.io.IOException;
import org.bson.Document;

/**
 * @company techvay
 * @author rifaie
 */
public final class JSONParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static LogResult parseJSONLog(String lv) throws Exception{
        LogResult res;
        res = MAPPER.readValue(lv, LogResult.class);
        return res;
    }

    public static AdminID parseJSONAID(String admin) throws IOException {
        AdminID res;
        res = MAPPER.readValue(admin, AdminID.class);
        return res;
    }

    public static EmpReportResult parseJSONEmpR(String empRV) throws IOException {
        EmpReportResult res;
        res = MAPPER.readValue(empRV, EmpReportResult.class);
        return res;
    }

    public static UsersResult parseJSONUsers(String userV) throws IOException {
        UsersResult res;
        res = MAPPER.readValue(userV, UsersResult.class);
        return res;
    }

    public static UserTypesResult parseJSONTypes(String typeV) throws IOException {
        UserTypesResult res;
        res = MAPPER.readValue(typeV, UserTypesResult.class);
        return res;
    }

    public static SuperUserTypesResult parseJSONSTypes(String sTypeV) throws IOException {
        SuperUserTypesResult res;
        res = MAPPER.readValue(sTypeV, SuperUserTypesResult.class);
        return res;
    }

    public static AddUserTypeResult parseJSONAddType(String addTypV) throws IOException {
        AddUserTypeResult res;
        res = MAPPER.readValue(addTypV, AddUserTypeResult.class);
        return res;
    }

    public static CheckUserTypeResult parseJSONCheckType(String ctv) throws IOException {
        CheckUserTypeResult res;
        res = MAPPER.readValue(ctv, CheckUserTypeResult.class);
        return res;
    }

    public static UTA parseJSONUTA(String uta) throws IOException {
        UTA res;
        res = MAPPER.readValue(uta, UTA.class);
        return res;
    }

    public static UpdateUserTypeResult parseJSONUUT(String uutv) throws IOException {
        UpdateUserTypeResult res;
        res = MAPPER.readValue(uutv, UpdateUserTypeResult.class);
        return res;
    }

    public static GetPinResult parseJSONGP(String gpv) throws IOException {
        GetPinResult res;
        res = MAPPER.readValue(gpv, GetPinResult.class);
        return res;
    }

    public static AddUserResult parseJSONAU(String auv) throws IOException {
        AddUserResult res;
        res = MAPPER.readValue(auv, AddUserResult.class);
        return res;
    }

    public static GetUserResult parseJSONGU(String guv) throws IOException {
        GetUserResult res;
        res = MAPPER.readValue(guv, GetUserResult.class);
        return res;
    }

    public static CheckUserIDResult parseJSONCheckUID(String cuv) throws IOException {
        CheckUserIDResult res;
        res = MAPPER.readValue(cuv, CheckUserIDResult.class);
        return res;
    }

    public static VerifyToken parseJSONVT(String vt) throws IOException {
        VerifyToken res;
        res = MAPPER.readValue(vt, VerifyToken.class);
        return res;
    }

    public static RPResult parseJSONRP(String rp) throws IOException {
        RPResult res;
        res = MAPPER.readValue(rp, RPResult.class);
        return res;
    }

    public static UUA parseJSONUUA(String uua) throws IOException {
        UUA res;
        res = MAPPER.readValue(uua, UUA.class);
        return res;
    }

    public static EditUserResult parseJSONEU(String euv) throws IOException {
        EditUserResult res;
        res = MAPPER.readValue(euv, EditUserResult.class);
        return res;
    }

    public static GetNoOfWebsitesResult parseJSONGNOW(String gnow) throws IOException {
        GetNoOfWebsitesResult res;
        res = MAPPER.readValue(gnow, GetNoOfWebsitesResult.class);
        return res;
    }

    public static GetNoOfMembersResult parseJSONGNOM(String reqv) throws IOException {
        GetNoOfMembersResult res;
        res = MAPPER.readValue(reqv, GetNoOfMembersResult.class);
        return res;
    }

    public static GetAllAffiliatesResult parseJSONGAA(String reqv) throws IOException {
        GetAllAffiliatesResult res;
        res = MAPPER.readValue(reqv, GetAllAffiliatesResult.class);
        return res;
    }

    public static DeleteAffiliateResult parseJSONDA(String reqv) throws IOException {
        DeleteAffiliateResult res;
        res = MAPPER.readValue(reqv, DeleteAffiliateResult.class);
        return res;
    }

    public static GetUserIdsResult parseJSONGUIDs(String reqv) throws IOException {
        GetUserIdsResult res;
        res = MAPPER.readValue(reqv, GetUserIdsResult.class);
        return res;
    }

    public static ActivityFilter parseJSONAF(String ftr) throws IOException {
        ActivityFilter res;
        res = MAPPER.readValue(ftr, ActivityFilter.class);
        return res;
    }

    public static FAUAResult parseJSONFAUA(String reqv) throws IOException {
        FAUAResult res;
        res = MAPPER.readValue(reqv, FAUAResult.class);
        return res;
    }

    public static Activity parseJSONActivity(String act) throws IOException {
        Activity res;
        res = MAPPER.readValue(act, Activity.class);
        return res;
    }

    public static AddAffiliateResult parseJSONADDAFF(String reqv) throws IOException {
        AddAffiliateResult res;
        res = MAPPER.readValue(reqv, AddAffiliateResult.class);
        return res;
    }

    public static GetAffiliateResult parseJSONGA(String reqv) throws IOException {
        GetAffiliateResult res;
        res = MAPPER.readValue(reqv, GetAffiliateResult.class);
        return res;
    }

    public static UpdateAffiliateResult parseJSONUA(String reqv) throws IOException {
        UpdateAffiliateResult res;
        res = MAPPER.readValue(reqv, UpdateAffiliateResult.class);
        return res;
    }

    public static GetAllPremiumPaymentsResult parseJSONGAPP(String reqv) throws IOException {
        GetAllPremiumPaymentsResult res;
        res = MAPPER.readValue(reqv, GetAllPremiumPaymentsResult.class);
        return res;
    }

    public static GetActivePremiumPaymentsResult parseJSONGAcPP(String reqv) throws IOException {
        GetActivePremiumPaymentsResult res;
        res = MAPPER.readValue(reqv, GetActivePremiumPaymentsResult.class);
        return res;
    }

    public static GetTotalPremiumPaymentsResult parseJSONGTPP(String reqv) throws IOException {
        GetTotalPremiumPaymentsResult res;
        res = MAPPER.readValue(reqv, GetTotalPremiumPaymentsResult.class);
        return res;
    }

    public static AddPremiumPaymentResult parseJSONAddPP(String reqv) throws IOException {
        AddPremiumPaymentResult res;
        res = MAPPER.readValue(reqv, AddPremiumPaymentResult.class);
        return res;
    }

    public static SearchAffiliatesResult parseJSONSAR(String reqv) throws IOException {
        SearchAffiliatesResult res;
        res = MAPPER.readValue(reqv, SearchAffiliatesResult.class);
        return res;
    }

    public static Affiliates parseJSONA(String af) throws IOException {
        Affiliates res;
        res = MAPPER.readValue(af, Affiliates.class);
        return res;
    }

    public static GetAffiliateUsersResult parseJSONGAUR(String reqv) throws IOException {
        GetAffiliateUsersResult res;
        res = MAPPER.readValue(reqv, GetAffiliateUsersResult.class);
        return res;
    }

    public static DeleteAffiliateUsersResult parseJSONDAU(String reqv) throws IOException {
        DeleteAffiliateUsersResult res;
        res = MAPPER.readValue(reqv, DeleteAffiliateUsersResult.class);
        return res;
    }

    public static GetAffiliatesResult parseJSONGAs(String reqv) throws IOException {
        GetAffiliatesResult res;
        res = MAPPER.readValue(reqv, GetAffiliatesResult.class);
        return res;
    }

    public static AddAffiliateUserResult parseJSONAAU(String reqv) throws IOException {
        AddAffiliateUserResult res;
        res = MAPPER.readValue(reqv, AddAffiliateUserResult.class);
        return res;
    }
        
}
