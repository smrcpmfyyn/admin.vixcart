/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.db;

import com.mongodb.DBCollection;
import com.mongodb.DBObject;
import com.mongodb.MongoClientURI;
import com.mongodb.MongoClient;
import com.mongodb.client.FindIterable;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import static com.mongodb.client.model.Filters.*;
import com.mongodb.client.model.Projections;
import static com.mongodb.client.model.Updates.*;
import com.mongodb.client.result.UpdateResult;
import static com.mongodb.client.model.Projections.*;
import com.mongodb.client.model.Sorts;
import com.mongodb.operation.OrderBy;
import com.mongodb.util.JSON;
import com.vixcart.admin.jsn.JSONParser;
import com.vixcart.admin.message.ErrMsg;
import com.vixcart.admin.mongo.mod.AdminID;
import com.vixcart.admin.mongo.mod.UTA;
import com.vixcart.admin.mongo.mod.UUA;
import com.vixcart.admin.mongo.mod.VerifyToken;
import com.vixcart.admin.req.mod.ActivityFilter;
import com.vixcart.admin.req.mod.AddAffiliateUser;
import com.vixcart.admin.req.mod.AddUser;
import com.vixcart.admin.req.mod.EditUser;
import com.vixcart.admin.req.mod.FAUA;
import com.vixcart.admin.req.mod.NewPassword;
import com.vixcart.admin.resp.mod.Activity;
import com.vixcart.admin.resp.mod.Affiliates;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;
import java.util.regex.Pattern;
import org.bson.BSON;
import org.bson.Document;
import org.bson.conversions.Bson;

/**
 * @company techvay
 * @author rifaie
 */
public class MongoConnect {

    private final MongoDatabase db;

    public MongoConnect() throws Exception {
        MongoClientURI uri = new MongoClientURI("mongodb://localhost/");
        MongoClient mongoClient = new MongoClient(uri);
        db = mongoClient.getDatabase("vaydeal");
    }

//    public void addFP(Reg reg) throws Exception {
//        MongoCollection<Document> fp = db.getCollection("forgot_password");
//        Document doc = new Document("email", reg.getEmail()).append("token", reg.getFphash()).append("time", ""+System.currentTimeMillis()).append("status", "verified");
//        fp.insertOne(doc);
//    }
//
//    public void updateToken(ForgotPassword fp) throws Exception {
//        MongoCollection<Document> fgp = db.getCollection("forgot_password");
//        fgp.updateOne(eq("email", fp.getEmail()), combine(set("token", fp.getToken()), set("time", System.currentTimeMillis() + ""), set("status", "notverified")));
//    }
//
//    public VerifyToken verifyToken(String token) throws Exception {
//        MongoCollection<Document> fgp = db.getCollection("forgot_password");
//        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
//        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
//        return vt;
//    }
//
//    public void removeFP(Reg reg) throws Exception {
//        MongoCollection<Document> fp = db.getCollection("forgot_password");
//        fp.findOneAndDelete(Filters.eq("email", reg.getEmail()));
//    }
//
//    public String getEmail(String token) throws Exception {
//        MongoCollection<Document> fgp = db.getCollection("forgot_password");
//        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
//        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
//        return vt.getEmail();
//    }
//
    public long updateTokenStatus(NewPassword np) throws Exception {
        MongoCollection<Document> fgp = db.getCollection("admin_password_token");
        UpdateResult updateOne = fgp.updateOne(eq("admin_id", np.getAdminId()), combine(set("status", "verified")));
        return updateOne.getMatchedCount();
    }
//
    // Call During User Addition

    public void addAT(String admin_id, String token, String type) throws Exception {
        MongoCollection<Document> at = db.getCollection("admin_access_token");
        Document doc = new Document("profile_id", "" + admin_id).append("token", token).append("type", type).append("status", "not logged");
        at.insertOne(doc);
    }
    // Call During user Addition

    /**
     *
     * @param admin_id
     */
    public void addUpdateTypeActivity(String admin_id) {
        MongoCollection<Document> at = db.getCollection("update_type_activity");
        Document doc = new Document("admin_id", admin_id).append("update_type_id", "0");
        at.insertOne(doc);

    }

    public void addPasswordToken(String admin_id, String token) {
        MongoCollection<Document> at = db.getCollection("admin_password_token");
        Document doc = new Document("admin_id", admin_id).append("token", token).append("toe", "" + (System.currentTimeMillis() + 300000)).append("status", "not changed");
        at.insertOne(doc);
    }

    public void addUpdateUserActivity(String admin_id) {
        MongoCollection<Document> at = db.getCollection("update_edit_user_activity");
        Document doc = new Document("admin_id", admin_id).append("update_user_id", "0");
        at.insertOne(doc);
    }
//
//    public void removeAT(Reg reg) throws Exception {
//        MongoCollection<Document> at = db.getCollection("admin_access_token");
//        at.findOneAndDelete(Filters.eq("profile_id", ""+reg.getPid()));
//    }
//
//    public void addOTP(Reg reg) throws Exception {
//        MongoCollection<Document> otp = db.getCollection("otp");
//        Document doc = new Document("profile_id", ""+reg.getPid()).append("moving_factor", "3456232").append("truncation_offset", "12").append("toe", "" + (System.currentTimeMillis()+300000)).append("status", "verified");
//        otp.insertOne(doc);
//    }
//
//    public void removeOTP(Reg reg) throws Exception {
//        MongoCollection<Document> otp = db.getCollection("otp");
//        otp.findOneAndDelete(Filters.eq("profile_id", ""+reg.getPid()));
//    }
//
//    public boolean updateOTP(String profile_id, int mf, int to) throws Exception {
//        boolean status = false;
//        MongoCollection<Document> fgp = db.getCollection("otp");
//        UpdateResult updateOne = fgp.updateOne(eq("profile_id", profile_id), combine(set("moving_factor", "" + mf), set("truncation_offset", "" + to), set("toe", "" + (System.currentTimeMillis() + 300000)), set("status","notverified")));
//        if(updateOne.getMatchedCount() == 1){
//            status = true;
//        }
//        return status;
//    }
//
//    public String getProfileId(String accessToken) throws Exception {
//        MongoCollection<Document> gpi = db.getCollection("admin_access_token");
//        FindIterable<Document> find = gpi.find(Filters.and(eq("token", accessToken))).projection(exclude("token", "_id"));
//        ProfileId pid = null;
//        if (find != null) {
//            pid = JSONParser.parseJSONPID(find.first().toJson());
//        }else{
//            pid = new ProfileId();
//            pid.setProfile_id(ErrMsg.ERR_ACCESS_TOKEN);
//        }
//        return pid.getProfile_id();
//    }
//
//    public OTPParams getOTPParams(String profile_id) throws IOException {
//        MongoCollection<Document> gOTPP = db.getCollection("otp");
//        FindIterable<Document> find = gOTPP.find(Filters.and(eq("profile_id", profile_id),eq("status","notverified"))).projection(exclude("profile_id", "_id", "status"));
//        OTPParams otpparams = null;
//        if (find.iterator().hasNext()) {
//            otpparams = JSONParser.parseJSONOTPParam(find.first().toJson());
//        }
//        return otpparams;
//    }
//
//    public boolean updateAccessToken(String profile_id, String accessToken) {
//        boolean status = false;
//        MongoCollection<Document> fgp = db.getCollection("admin_access_token");
//        UpdateResult updateOne = fgp.updateOne(eq("profile_id", profile_id), combine(set("token", "" + accessToken)));
//        if(updateOne.getMatchedCount() == 1){
//            status = true;
//        }
//        return status;
//    }
//
//    public boolean updateOTPStatus(String profile_id) {
//        boolean status = false;
//        MongoCollection<Document> fgp = db.getCollection("otp");
//        UpdateResult updateOne = fgp.updateOne(eq("profile_id", profile_id), combine(set("status", "verified")));
//        if(updateOne.getMatchedCount() == 1){
//            status = true;
//        }
//        return status;
//    }

    public boolean updateAccessToken(String admin_id, String accessToken) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("admin_access_token");
        UpdateResult updateOne = fgp.updateOne(eq("profile_id", admin_id), combine(set("token", "" + accessToken), set("status", "logged")));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public AdminID getAdminID(String at) throws IOException {
        MongoCollection<Document> gpi = db.getCollection("admin_access_token");
        FindIterable<Document> find = gpi.find(Filters.and(eq("token", at), eq("status", "logged"))).projection(exclude("token", "_id", "status"));
        AdminID aid = null;
        if (find.iterator().hasNext()) {
            aid = JSONParser.parseJSONAID(find.first().toJson());
        } else {
            aid = new AdminID();
            aid.setProfile_id(ErrMsg.ERR_ACCESS_TOKEN);
        }
        return aid;
    }

    public boolean updateTypeActivity(String admin_id, String type_id) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("update_type_activity");
        UpdateResult updateOne = fgp.updateOne(eq("admin_id", admin_id), combine(set("update_type_id", "" + type_id)));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public UTA getUpdateTypeActivity(String admin_id) throws IOException {
        MongoCollection<Document> gpi = db.getCollection("update_type_activity");
        FindIterable<Document> find = gpi.find(Filters.and(eq("admin_id", admin_id))).projection(exclude("_id"));
        UTA uta = JSONParser.parseJSONUTA(find.first().toJson());
        return uta;
    }

    public UUA getUpdateUserActivity(String admin_id) throws IOException {
        MongoCollection<Document> gpi = db.getCollection("update_edit_user_activity");
        FindIterable<Document> find = gpi.find(Filters.and(eq("admin_id", admin_id))).projection(exclude("_id"));
        UUA uua = JSONParser.parseJSONUUA(find.first().toJson());
        return uua;
    }

    public ArrayList getPin() {
        ArrayList<String> allPin = new ArrayList<>();
        MongoCollection<Document> gpi = db.getCollection("pincode");
        FindIterable<Document> find = gpi.find().projection(exclude("_id"));
        for (Document document : find) {
            allPin.add(document.toJson());
        }
        return allPin;
    }

    public boolean checkPin(String pin) {
        MongoCollection<Document> gpi = db.getCollection("pincode");
        long count = gpi.count(Filters.and(eq("pin", pin)));
        return count > 0;
    }

    public void addUser(AddUser au) throws Exception {
        Random ran = new Random();
        String token = "" + System.currentTimeMillis() + ran.nextLong();
        addAT(au.getNew_admin_id(), token, au.getUserType());
        addPasswordToken(au.getNew_admin_id(), au.getPasswordToken());
        addUpdateTypeActivity(au.getNew_admin_id());
        addUpdateUserActivity(au.getNew_admin_id());
    }

    public void removeUser(String new_admin_id) {
        removeAT(new_admin_id);
        removePasswordToken(new_admin_id);
        removeUpdateTypeActivity(new_admin_id);
        removeUpdateUserActivity(new_admin_id);
    }

    private void removeAT(String new_admin_id) {
        MongoCollection<Document> otp = db.getCollection("admin_access_token");
        otp.findOneAndDelete(Filters.eq("profile_id", "" + new_admin_id));
    }

    private void removeUpdateTypeActivity(String new_admin_id) {
        MongoCollection<Document> otp = db.getCollection("update_type_activity");
        otp.findOneAndDelete(Filters.eq("admin_id", "" + new_admin_id));
    }
    
    private void removeUpdateUserActivity(String new_admin_id) {
        MongoCollection<Document> otp = db.getCollection("update_edit_user_activity");
        otp.findOneAndDelete(Filters.eq("admin_id", "" + new_admin_id));
    }

    private void removePasswordToken(String new_admin_id) {
        MongoCollection<Document> otp = db.getCollection("admin_password_token");
        otp.findOneAndDelete(Filters.eq("admin_id", "" + new_admin_id));
    }

    public boolean updateUserIDActivity(String admin_id, String uid) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("update_edit_user_activity");
        UpdateResult updateOne = fgp.updateOne(eq("admin_id", admin_id), combine(set("update_user_id", "" + uid)));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public VerifyToken verifyToken(String token) throws Exception {
        MongoCollection<Document> fgp = db.getCollection("admin_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
        return vt;
    }

    public VerifyToken getAdminId(String token) throws IOException {

        MongoCollection<Document> fgp = db.getCollection("admin_password_token");
        FindIterable<Document> find = fgp.find(Filters.and(eq("token", token))).projection(exclude("token", "_id"));
        VerifyToken vt = JSONParser.parseJSONVT(find.first().toJson());
        return vt;
    }

    public void updateUser(EditUser eu) {
        updateAT(eu.getUpdatedAdminId(), eu.getUserType());
    }

    private boolean updateAT(String updatedAdminId, String userType) {
        boolean status = false;
        MongoCollection<Document> fgp = db.getCollection("admin_access_token");
        UpdateResult updateOne = fgp.updateOne(eq("profile_id", updatedAdminId), combine(set("type", "" + userType)));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public void addActivity(String act) {
        MongoCollection collection = db.getCollection("admin_user_activities");
        Document doc = Document.parse(act);
        collection.insertOne(doc);
    }

    public boolean logout(String at) {
        boolean status = false;
        MongoCollection<Document> collection = db.getCollection("admin_access_token");
        UpdateResult updateOne = collection.updateOne(eq("token", at), combine(set("status", "not logged")));
        if (updateOne.getMatchedCount() == 1) {
            status = true;
        }
        return status;
    }

    public void filterAdminUserActivity(String filter, int pageno, int pageSize) {
        MongoCollection<Document> collection = db.getCollection("admin_user_activities");
        FindIterable<Document> find = null;
        if (filter.length() == 0) {
            find = collection.find().sort(Sorts.descending("dateTime")).skip((pageno - 1) * pageSize).limit(pageSize).projection(exclude("_id"));
        } else {
            ArrayList<Bson> filters = new ArrayList<>();
            getFilters(filter, filters);
            find = collection.find(Filters.and(filters)).sort(Sorts.descending("dateTime")).skip((pageno - 1) * pageSize).limit(pageSize).projection(exclude("_id"));
        }
        for (Document document : find) {
            System.out.println(document.toJson());

        }
    }

    private void getFilters(String filter, ArrayList<Bson> filters) {
        String[] filterArray = filter.split(";");
        for (String params : filterArray) {
            String[] paramsArray = params.split(":");
            filters.add(eq(paramsArray[0], paramsArray[1]));
        }
    }

    public ArrayList<Activity> getAllFAUA(FAUA req) throws IOException {
        ArrayList<Activity> al = new ArrayList<>();
        MongoCollection<Document> collection = db.getCollection("admin_user_activities");
        FindIterable<Document> find = null;
        if(!isFilterParamsExists(req.getFtr())){
            find = collection.find().sort(Sorts.descending("dateTime")).skip((req.getPageNo() - 1) * req.getMaxEntries()).limit(req.getMaxEntries()).projection(exclude("_id"));
        }else{
            ArrayList<Bson> filters = new ArrayList<>();
            getFilter(req.getFtr(), filters);
            find = collection.find(Filters.and(filters)).sort(Sorts.descending("dateTime")).skip((req.getPageNo() - 1) * req.getMaxEntries()).limit(req.getMaxEntries()).projection(exclude("_id"));
        }
        for (Document document : find) {
            Activity act = JSONParser.parseJSONActivity(document.toJson());
            al.add(act);
        }
        return al;
    }

    private boolean isFilterParamsExists(ActivityFilter ftr) {
        if(ftr.getUid() != null){
            return true;
        }else if(ftr.getActivity() != null){
            return true;
        }else if(ftr.getuType() != null){
            return true;
        }else if(ftr.getEntryStatus() != null){
            return true;
        }
        return false;
    }
    
    private void getFilter(ActivityFilter ftr, ArrayList<Bson> filters){
        if(ftr.getUid() != null){
            addUIDFilter(ftr.getUid(),filters);
        }
        if(ftr.getuType() != null){
            addUTypeFilter(ftr.getuType(),filters);
        }
        if(ftr.getActivity() != null){
            addActivityFilter(ftr.getActivity(),filters);
        }
        if(ftr.getEntryStatus() != null){
            addEntryStatusFilter(ftr.getEntryStatus(),filters);
        }
    }

    private void addUIDFilter(String uid, ArrayList<Bson> filters) {
        filters.add(eq("uid", uid));
    }

    private void addUTypeFilter(String[] uType, ArrayList<Bson> filters) {
        if(uType.length>1){
            ArrayList<Bson> uTypeFilters = new ArrayList<>();
            for (String uT : uType) {
                uTypeFilters.add(eq("uType", uT));
            }
            filters.add(Filters.or(uTypeFilters));
        }else{
            filters.add(eq("uType", uType[0]));
        }
    }

    private void addActivityFilter(String[] activity, ArrayList<Bson> filters) {
        if(activity.length>1){
            ArrayList<Bson> activityFilters = new ArrayList<>();
            for (String uT : activity) {
                activityFilters.add(eq("activity", uT));
            }
            filters.add(Filters.or(activityFilters));
        }else{
            filters.add(eq("activity", activity[0]));
        }
    }

    private void addEntryStatusFilter(String[] entryStatus, ArrayList<Bson> filters) {
        if(entryStatus.length>1){
            ArrayList<Bson> entryStatusFilters = new ArrayList<>();
            for (String uT : entryStatus) {
                entryStatusFilters.add(eq("entryStatus", uT));
            }
            filters.add(Filters.or(entryStatusFilters));
        }else{
            filters.add(eq("entryStatus", entryStatus[0]));
        }
    }

    public void addAffiliate(String company) {
        MongoCollection<Document> at = db.getCollection("search_affiliate");
        Document doc = new Document("query", "" + company).append("query_type", "company");
        at.insertOne(doc);
    }

    public ArrayList<Affiliates> searchAffiliates(String str) throws IOException {
        Pattern p = Pattern.compile(str+"\\w*");
        ArrayList<Affiliates> qRes = new ArrayList<>();
        MongoCollection<Document> fgp = db.getCollection("search_affiliate");
        FindIterable<Document> find = fgp.find(Filters.regex("query", p)).limit(7).projection(exclude("query_type", "_id"));
        MongoCursor<Document> itr = find.iterator();
        System.out.println(find.first().toJson());
        while(itr.hasNext()){
            Affiliates af = JSONParser.parseJSONA(itr.next().toJson());
            System.out.println(af.toString());
            qRes.add(af);
        }
        return qRes;
    }

    public String getSearchAffiliateQueryType(String query) {
        String queryType = "nothing";
        MongoCollection<Document> fgp = db.getCollection("search_affiliate");
        FindIterable<Document> find = fgp.find(Filters.eq("query", query)).limit(7).projection(exclude("query", "_id"));
        MongoCursor<Document> itr = find.iterator();
        if(itr.hasNext()){
            queryType = itr.next().getString("query_type");
        }
        return queryType;
    }

    public void addAffiliateUser(AddAffiliateUser req) {
        Random ran = new Random();
        String token = "" + System.currentTimeMillis() + ran.nextLong();
        addAUAT(req.getNew_user_id(), token);
        addAUPasswordToken(req.getNew_user_id(), req.getPasswordToken());
    }

    private void addAUAT(String new_user_id, String token) {
        MongoCollection<Document> at = db.getCollection("affiliate_user_access_token");
        Document doc = new Document("user_id", "" + new_user_id).append("token", token).append("status", "not logged");
        at.insertOne(doc);
    }

    private void addAUPasswordToken(String new_user_id, String passwordToken) {
        MongoCollection<Document> at = db.getCollection("affiliate_user_password_token");
        Document doc = new Document("user_id", new_user_id).append("token", passwordToken).append("toe", "" + (System.currentTimeMillis() + 300000)).append("status", "not changed");
        at.insertOne(doc);
    }

    public void removeAffiliateUser(String new_user_id) {
        removeAUAT(new_user_id);
        removeAUPasswordToken(new_user_id);
    }

    private void removeAUAT(String new_user_id) {
        MongoCollection<Document> otp = db.getCollection("affiliate_user_access_token");
        otp.findOneAndDelete(Filters.eq("user_id", "" + new_user_id));
    }

    private void removeAUPasswordToken(String new_user_id) {
        MongoCollection<Document> otp = db.getCollection("affiliate_user_password_token");
        otp.findOneAndDelete(Filters.eq("user_id", "" + new_user_id));
    }

    

}
