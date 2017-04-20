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
import com.vixcart.admin.result.AddBaCResult;
import com.vixcart.admin.result.AddBrandResult;
import com.vixcart.admin.result.AddCategoryResult;
import com.vixcart.admin.result.AddPremiumPaymentResult;
import com.vixcart.admin.result.AddProductTypeResult;
import com.vixcart.admin.result.AddSpecificationResult;
import com.vixcart.admin.result.AddSubCategoryResult;
import com.vixcart.admin.result.AddSuperCategoryResult;
import com.vixcart.admin.result.AddTaCResult;
import com.vixcart.admin.result.AddUserResult;
import com.vixcart.admin.result.AddUserTypeResult;
import com.vixcart.admin.result.ChangeAffiliateUserStatusResult;
import com.vixcart.admin.result.CheckUserIDResult;
import com.vixcart.admin.result.CheckUserTypeResult;
import com.vixcart.admin.result.DeleteAffiliateResult;
import com.vixcart.admin.result.DeleteAffiliateUsersResult;
import com.vixcart.admin.result.DeleteBaCResult;
import com.vixcart.admin.result.DeleteBrandResult;
import com.vixcart.admin.result.DeleteCategoryResult;
import com.vixcart.admin.result.DeleteSpecificationResult;
import com.vixcart.admin.result.DeleteSubCategoryResult;
import com.vixcart.admin.result.DeleteSuperCategoryResult;
import com.vixcart.admin.result.DeleteTaCResult;
import com.vixcart.admin.result.EditUserResult;
import com.vixcart.admin.result.EmpReportResult;
import com.vixcart.admin.result.FAUAResult;
import com.vixcart.admin.result.GetActivePremiumPaymentsResult;
import com.vixcart.admin.result.GetAffiliateResult;
import com.vixcart.admin.result.GetAffiliateUsersResult;
import com.vixcart.admin.result.GetAffiliatesResult;
import com.vixcart.admin.result.GetAllAffiliatesResult;
import com.vixcart.admin.result.GetAllBrandsResult;
import com.vixcart.admin.result.GetAllPremiumPaymentsResult;
import com.vixcart.admin.result.GetAllProductTypesResult;
import com.vixcart.admin.result.GetAllTaCResult;
import com.vixcart.admin.result.GetBPaC2Result;
import com.vixcart.admin.result.GetBPaCResult;
import com.vixcart.admin.result.GetBrandResult;
import com.vixcart.admin.result.GetCategoriesResult;
import com.vixcart.admin.result.GetCategoryResult;
import com.vixcart.admin.result.GetNoOfMembersResult;
import com.vixcart.admin.result.GetNoOfWebsitesResult;
import com.vixcart.admin.result.GetPinResult;
import com.vixcart.admin.result.GetProductTypeResult;
import com.vixcart.admin.result.GetProductTypesResult;
import com.vixcart.admin.result.GetSpecificationResult;
import com.vixcart.admin.result.GetSubCategoriesResult;
import com.vixcart.admin.result.GetSubCategoryResult;
import com.vixcart.admin.result.GetSuperCategoriesResult;
import com.vixcart.admin.result.GetSuperCategoryResult;
import com.vixcart.admin.result.GetTaCResult;
import com.vixcart.admin.result.GetTotalPremiumPaymentsResult;
import com.vixcart.admin.result.GetUserIdsResult;
import com.vixcart.admin.result.GetUserResult;
import com.vixcart.admin.result.LoadSpecificationsResult;
import com.vixcart.admin.result.LogResult;
import com.vixcart.admin.result.RPResult;
import com.vixcart.admin.result.SearchAffiliatesResult;
import com.vixcart.admin.result.SearchBrandResult;
import com.vixcart.admin.result.SearchProductTypeResult;
import com.vixcart.admin.result.SuperUserTypesResult;
import com.vixcart.admin.result.UpdateAffiliateResult;
import com.vixcart.admin.result.UpdateBaCResult;
import com.vixcart.admin.result.UpdateBrandResult;
import com.vixcart.admin.result.UpdateCategoryResult;
import com.vixcart.admin.result.UpdateProductTypeResult;
import com.vixcart.admin.result.UpdateSpecificationResult;
import com.vixcart.admin.result.UpdateSubCategoryResult;
import com.vixcart.admin.result.UpdateSuperCategoryResult;
import com.vixcart.admin.result.UpdateTaCResult;
import com.vixcart.admin.result.UpdateUserTypeResult;
import com.vixcart.admin.result.UserTypesResult;
import com.vixcart.admin.result.UsersResult;
import java.io.IOException;

/**
 * @company techvay
 * @author rifaie
 */
public final class JSONParser {

    private static final ObjectMapper MAPPER = new ObjectMapper();

    public static LogResult parseJSONLog(String lv) throws Exception {
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

    public static AddSubCategoryResult parseJSONAddSubCategory(String toString) throws IOException {
        AddSubCategoryResult res = null;
        res = MAPPER.readValue(toString, AddSubCategoryResult.class);
        return res;
    }

    public static DeleteSuperCategoryResult parseJSONDeleteSuperCategory(String toString) throws IOException {
        DeleteSuperCategoryResult res = null;
        res = MAPPER.readValue(toString, DeleteSuperCategoryResult.class);
        return res;
    }

    public static DeleteCategoryResult parseJSONDeleteCategory(String toString) throws IOException {
        DeleteCategoryResult res = null;
        res = MAPPER.readValue(toString, DeleteCategoryResult.class);
        return res;
    }

    public static DeleteSubCategoryResult parseJSONDeleteSubCategory(String toString) throws IOException {
        DeleteSubCategoryResult res = null;
        res = MAPPER.readValue(toString, DeleteSubCategoryResult.class);
        return res;
    }

    public static UpdateSuperCategoryResult parseJSONUpdateSuperCategory(String toString) throws IOException {
        UpdateSuperCategoryResult res = null;
        res = MAPPER.readValue(toString, UpdateSuperCategoryResult.class);
        return res;
    }

    public static UpdateSubCategoryResult parseJSONUpdateSubCategory(String toString) throws IOException {
        UpdateSubCategoryResult res = null;
        res = MAPPER.readValue(toString, UpdateSubCategoryResult.class);
        return res;
    }

    public static UpdateCategoryResult parseJSONUpdateCategory(String toString) throws IOException {
        UpdateCategoryResult res = null;
        res = MAPPER.readValue(toString, UpdateCategoryResult.class);
        return res;
    }

    public static AddProductTypeResult parseJSONAddProductType(String toString) throws IOException {
        AddProductTypeResult res = null;
        res = MAPPER.readValue(toString, AddProductTypeResult.class);
        return res;
    }

    public static UpdateSpecificationResult parseJSONUpdateSpecification(String toString) throws IOException {
        UpdateSpecificationResult res = null;
        res = MAPPER.readValue(toString, UpdateSpecificationResult.class);
        return res;
    }

    public static DeleteSpecificationResult parseJSONDeleteSpecification(String toString) throws IOException {
        DeleteSpecificationResult res = null;
        res = MAPPER.readValue(toString, DeleteSpecificationResult.class);
        return res;
    }

    public static AddSpecificationResult parseJSONAddSpecification(String toString) throws IOException {
        AddSpecificationResult res = null;
        res = MAPPER.readValue(toString, AddSpecificationResult.class);
        return res;
    }

    public static DeleteTaCResult parseJSONDeleteTaC(String toString) throws IOException {
        DeleteTaCResult res = null;
        res = MAPPER.readValue(toString, DeleteTaCResult.class);
        return res;
    }

    public static UpdateProductTypeResult parseJSONUpdateProductType(String toString) throws IOException {
        UpdateProductTypeResult res = null;
        res = MAPPER.readValue(toString, UpdateProductTypeResult.class);
        return res;
    }

    public static UpdateTaCResult parseJSONUpdateTaC(String toString) throws IOException {
        UpdateTaCResult res = null;
        res = MAPPER.readValue(toString, UpdateTaCResult.class);
        return res;
    }

    public static AddTaCResult parseJSONAddTaC(String toString) throws IOException {
        AddTaCResult res = null;
        res = MAPPER.readValue(toString, AddTaCResult.class);
        return res;
    }

    public static AddBrandResult parseJSONAddBrand(String toString) throws IOException {
        AddBrandResult res = null;
        res = MAPPER.readValue(toString, AddBrandResult.class);
        return res;
    }

    public static UpdateBaCResult parseJSONUpdateBaC(String toString) throws IOException {
        UpdateBaCResult res ;
        res = MAPPER.readValue(toString, UpdateBaCResult.class);
        return res;
    }

    public static DeleteBaCResult parseJSONDeleteBaC(String toString) throws IOException {
        DeleteBaCResult res = null;
        res = MAPPER.readValue(toString, DeleteBaCResult.class);
        return res;
    }

    public static AddBaCResult parseJSONAddBaC(String toString) throws IOException {
        AddBaCResult res = null;
        res = MAPPER.readValue(toString, AddBaCResult.class);
        return res;
    }

    public static DeleteBrandResult parseJSONDeleteBrand(String toString) throws IOException {
        DeleteBrandResult res = null;
        res = MAPPER.readValue(toString, DeleteBrandResult.class);
        return res;
    }

    public static UpdateBrandResult parseJSONUpdateBrand(String toString) throws IOException {
        UpdateBrandResult res = null;
        res = MAPPER.readValue(toString, UpdateBrandResult.class);
        return res;
    }

    public static LoadSpecificationsResult parseJSONLoadSpecifications(String toString) throws IOException {
        LoadSpecificationsResult res = null;
        res = MAPPER.readValue(toString, LoadSpecificationsResult.class);
        return res;
    }

    public static GetSpecificationResult parseJSONGetSpecification(String toString) throws IOException {
        GetSpecificationResult res ;
        res = MAPPER.readValue(toString, GetSpecificationResult.class);
        return res;
    }

    public static GetBrandResult parseJSONGetBrand(String toString) throws IOException {
        GetBrandResult res ;
        res = MAPPER.readValue(toString, GetBrandResult.class);
        return res;
    }

    public static GetProductTypesResult parseJSONGetProductTypes(String toString) throws IOException {
        GetProductTypesResult res = null;
        res = MAPPER.readValue(toString, GetProductTypesResult.class);
        return res;
    }

    public static GetProductTypeResult parseJSONGetProductType(String toString) throws IOException {
        GetProductTypeResult res = null;
        res = MAPPER.readValue(toString, GetProductTypeResult.class);
        return res;
    }

    public static GetSuperCategoryResult parseJSONGetSuperCategory(String toString) throws IOException {
        GetSuperCategoryResult res = null;
        res = MAPPER.readValue(toString, GetSuperCategoryResult.class);
        return res;
    }

    public static GetTaCResult parseJSONGetTaC(String toString) throws IOException {
        GetTaCResult res = null;
        res = MAPPER.readValue(toString, GetTaCResult.class);
        return res;
    }

    public static GetAllBrandsResult parseJSONGetAllBrands(String toString) throws IOException {
        GetAllBrandsResult res = null;
        res = MAPPER.readValue(toString, GetAllBrandsResult.class);
        return res;
    }

    public static GetBPaC2Result parseJSONGetBPaC2(String toString) throws IOException {
        GetBPaC2Result res = null;
        res = MAPPER.readValue(toString, GetBPaC2Result.class);
        return res;
    }

    public static GetSubCategoryResult parseJSONGetSubCategory(String toString) throws IOException {
        GetSubCategoryResult res = null;
        res = MAPPER.readValue(toString, GetSubCategoryResult.class);
        return res;
    }

    public static GetCategoryResult parseJSONGetCategory(String toString) throws IOException {
        GetCategoryResult res = null;
        res = MAPPER.readValue(toString, GetCategoryResult.class);
        return res;
    }

    public static GetBPaCResult parseJSONGetBPaC(String toString) throws IOException {
        GetBPaCResult res = null;
        res = MAPPER.readValue(toString, GetBPaCResult.class);
        return res;
    }

    public static SearchBrandResult parseJSONSearchBrand(String toString) throws IOException {
        SearchBrandResult res = null;
        res = MAPPER.readValue(toString, SearchBrandResult.class);
        return res;
    }

    public static GetCategoriesResult parseJSONGetCategories(String toString) throws IOException {
        GetCategoriesResult res = null;
        res = MAPPER.readValue(toString, GetCategoriesResult.class);
        return res;
    }

    public static GetAllTaCResult parseJSONGetAllTaC(String toString) throws IOException {
        GetAllTaCResult res = null;
        res = MAPPER.readValue(toString, GetAllTaCResult.class);
        return res;
    }

    public static GetAllProductTypesResult parseJSONGetAllProductTypes(String toString) throws IOException {
        GetAllProductTypesResult res = null;
        res = MAPPER.readValue(toString, GetAllProductTypesResult.class);
        return res;
    }

    public static SearchProductTypeResult parseJSONSearchProductType(String toString) throws IOException {
        SearchProductTypeResult res = null;
        res = MAPPER.readValue(toString, SearchProductTypeResult.class);
        return res;
    }

        public static GetSuperCategoriesResult parseJSONGSC(String gsc) throws IOException {
        GetSuperCategoriesResult res;
        res = MAPPER.readValue(gsc, GetSuperCategoriesResult.class);
        return res;
    }

    public static GetSubCategoriesResult parseJSONGSubCategs(String gsc) throws IOException {
        GetSubCategoriesResult res;
        res = MAPPER.readValue(gsc, GetSubCategoriesResult.class);
        return res;
    }

    public static GetCategoriesResult parseJSONGCateg(String gsc) throws IOException {
        GetCategoriesResult res;
        res = MAPPER.readValue(gsc, GetCategoriesResult.class);
        return res;
    }


    public static AddSuperCategoryResult parseJSONAddSuperCategory(String addTypV) throws IOException {
        AddSuperCategoryResult res;
        res = MAPPER.readValue(addTypV, AddSuperCategoryResult.class);
        return res;
    }

    public static AddCategoryResult parseJSONAddCategory(String addTypV) throws IOException {
        AddCategoryResult res;
        res = MAPPER.readValue(addTypV, AddCategoryResult.class);
        return res;
    }

    public static ChangeAffiliateUserStatusResult parseJSONCAUSR(String reqv) throws IOException {
        ChangeAffiliateUserStatusResult res;
        res = MAPPER.readValue(reqv, ChangeAffiliateUserStatusResult.class);
        return res;
    }

    
}
