/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.db;

import com.vixcart.admin.imgpath.ImagePath;
import com.vixcart.admin.req.mod.AddAffiliate;
import com.vixcart.admin.req.mod.AddAffiliateUser;
import com.vixcart.admin.req.mod.AddBPaC;
import com.vixcart.admin.req.mod.AddBrand;
import com.vixcart.admin.req.mod.AddCategory;
import com.vixcart.admin.req.mod.AddMember;
import com.vixcart.admin.req.mod.AddPremiumPayment;
import com.vixcart.admin.req.mod.AddProductType;
import com.vixcart.admin.req.mod.AddSpecification;
import com.vixcart.admin.req.mod.AddSubCategory;
import com.vixcart.admin.req.mod.AddSuperCategory;
import com.vixcart.admin.req.mod.AddTaC;
import com.vixcart.admin.req.mod.AddUser;
import com.vixcart.admin.req.mod.AddUserType;
import com.vixcart.admin.req.mod.DeleteBPaC;
import com.vixcart.admin.req.mod.DeleteBrand;
import com.vixcart.admin.req.mod.DeleteCategory;
import com.vixcart.admin.req.mod.DeleteProductType;
import com.vixcart.admin.req.mod.DeleteSpecification;
import com.vixcart.admin.req.mod.DeleteSubCategory;
import com.vixcart.admin.req.mod.DeleteSuperCategory;
import com.vixcart.admin.req.mod.DeleteTaC;
import com.vixcart.admin.req.mod.EditUser;
import com.vixcart.admin.req.mod.GetAffiliateRequests;
import com.vixcart.admin.req.mod.GetAffiliateUsers;
import com.vixcart.admin.req.mod.GetBPaC;
import com.vixcart.admin.req.mod.GetBPaCs;
import com.vixcart.admin.req.mod.GetBrand;
import com.vixcart.admin.req.mod.GetBrands;
import com.vixcart.admin.req.mod.GetCategories;
import com.vixcart.admin.req.mod.GetCategory;
import com.vixcart.admin.req.mod.GetMembers;
import com.vixcart.admin.req.mod.GetProductType;
import com.vixcart.admin.req.mod.GetProductTypes;
import com.vixcart.admin.req.mod.GetSpecification;
import com.vixcart.admin.req.mod.GetSubCategory;
import com.vixcart.admin.req.mod.GetSuperCategory;
import com.vixcart.admin.req.mod.GetTaC;
import com.vixcart.admin.req.mod.GetTaCs;
import com.vixcart.admin.req.mod.LoadSpecifications;
import com.vixcart.admin.req.mod.NewPassword;
import com.vixcart.admin.req.mod.ResetAffiliateUser;
import com.vixcart.admin.req.mod.SearchBrand;
import com.vixcart.admin.req.mod.SearchProductType;
import com.vixcart.admin.req.mod.UpdateAffiliate;
import com.vixcart.admin.req.mod.UpdateBPaC;
import com.vixcart.admin.req.mod.UpdateBrand;
import com.vixcart.admin.req.mod.UpdateCategory;
import com.vixcart.admin.req.mod.UpdateProductType;
import com.vixcart.admin.req.mod.UpdateSpecification;
import com.vixcart.admin.req.mod.UpdateSubCategory;
import com.vixcart.admin.req.mod.UpdateSuperCategory;
import com.vixcart.admin.req.mod.UpdateTaC;
import com.vixcart.admin.req.mod.UpdateUserType;
import com.vixcart.admin.resp.mod.AffiliateCompany;
import com.vixcart.admin.resp.mod.AffiliateDetails;
import com.vixcart.admin.resp.mod.AffiliateRequest;
import com.vixcart.admin.resp.mod.AffiliateRequests;
import com.vixcart.admin.resp.mod.AffiliateUserDetails;
import com.vixcart.admin.resp.mod.AllAffiliates;
import com.vixcart.admin.resp.mod.AllPremiumPayments;
import com.vixcart.admin.resp.mod.BPaC;
import com.vixcart.admin.resp.mod.Brand;
import com.vixcart.admin.resp.mod.Category;
import com.vixcart.admin.resp.mod.MemberAllDetails;
import com.vixcart.admin.resp.mod.MemberDetails;
import com.vixcart.admin.resp.mod.PremiumPayments;
import com.vixcart.admin.resp.mod.ProductType;
import com.vixcart.admin.resp.mod.Specifications;
import com.vixcart.admin.resp.mod.SubCategory;
import com.vixcart.admin.resp.mod.SuperCategory;
import com.vixcart.admin.resp.mod.SuperUserType;
import com.vixcart.admin.resp.mod.TaC;
import com.vixcart.admin.resp.mod.User;
import com.vixcart.admin.resp.mod.UserDetails;
import com.vixcart.admin.resp.mod.UserIds;
import com.vixcart.admin.resp.mod.UserType;
import com.vixcart.admin.resp.mod.UserType1;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Random;

/**
 * @company techvay
 * @author rifaie
 */
public class DBConnect {

    private Connection con = null;

    private ResultSet rs;

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://worddb.c8s8lmxdo3ux.ap-south-1.rds.amazonaws.com:3306/vaydeal", "worduser", "sooraj123");

//        checkEmail = connect.prepareStatement("SELECT count(*) FROM seller_email WHERE email = ?");
//        checkMobile = connect.prepareStatement("SELECT count(*) FROM seller_mobile WHERE mobile = ?");
//
//        getPID = connect.prepareStatement("SELECT profile_id FROM seller");
//        getID = connect.prepareStatement("SELECT AUTO_INCREMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = 'vaydeal' AND TABLE_NAME = 'seller'");
//        addSeller = connect.prepareStatement("INSERT INTO seller (id,name,profile_id) VALUES (?,?,?)");
//        addEmail = connect.prepareStatement("INSERT INTO seller_email (email,seller_id,hash) VALUES (?,?,?)");
//        addMobile = connect.prepareStatement("INSERT INTO seller_mobile (mobile,seller_id) VALUES (?,?)");
//        addLogin = connect.prepareStatement("INSERT INTO seller_login (seller_email,password,salt) VALUES (?,?,?)");
//        
//        deleteSeller = connect.prepareStatement("DELETE FROM vaydeal.seller WHERE profile_id = ?");
//        
//        checkVKey = connect.prepareStatement("SELECT count(*) FROM seller_email WHERE hash = ?");
//        checkEmailStatus = connect.prepareStatement("SELECT status FROM seller_email WHERE hash = ?");
//        verifyEmail = connect.prepareStatement("UPDATE seller_email SET status = 2 WHERE hash =?");
//        
//        checkVerifiedEmail = connect.prepareStatement("SELECT count(*) FROM sellers_mobile_not_verified WHERE email = ?");
//        checkNotVerifiedEmail = connect.prepareStatement("SELECT count(*) FROM seller_email WHERE email = ? AND status = 1 AND type = 1");
//        getName = connect.prepareStatement("SELECT name FROM sellers_mobile_not_verified WHERE email = ?");
//        
//        
//        checkVerifiedMobile = connect.prepareStatement("SELECT count(*) FROM sellers_mobile_verified WHERE email = ?");
//        checkUpdateStatus = connect.prepareStatement("SELECT count(*) FROM sellers_profile_updated WHERE email = ?");
//        
//        
//        getMobile = connect.prepareStatement("SELECT mobile FROM sellers_mobile_not_verified WHERE profile_id = ?");
//        
//        updateMVS = connect.prepareStatement("UPDATE seller_mobile SET status = 2 WHERE seller_id = (SELECT id FROM seller WHERE profile_id = ?)");
//        
//        getSuperCategories = con.prepareStatement("SELECT su.super_category_id, su.super_category FROM super_categories su");
    }

    /**
     *
     * @throws SQLException
     */
    public void closeConnection() throws SQLException {
        con.close();
    }

    public void commit() throws SQLException {
        con.commit();
    }

    public int checkUname(String uname) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_logger WHERE uname = ?");
        int count = 0;
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public List<String> getPassDSalt(String uname) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT salt,password,admin_id,type FROM admin_logger WHERE uname = ?");
        List<String> proD = new ArrayList<>();
        ps.setString(1, uname);
        rs = ps.executeQuery();
        if (rs.next()) {
            proD.add(rs.getString("salt"));
            proD.add(rs.getString("password"));
            proD.add(rs.getString("admin_id"));
            proD.add(rs.getString("type"));
        }
        rs.close();
        ps.close();
        return proD;
    }

    public boolean updateLog(String uName) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE admin_login SET lastlogged = now(), logcount = logcount+1 WHERE uname = ?");
        ps.setString(1, uName);
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public HashSet<String> getSubTypes(String type) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT type,id FROM admin_user_types WHERE supertype = ?");
        int typeID = getTypeID(type);
        HashSet<String> types = new HashSet<>();
        types.add(type);
        if (typeID != 0) {
            ps.setInt(1, typeID);
            rs = ps.executeQuery();
            while (rs.next()) {
                types.add(rs.getString(1));
            }
        }
        rs.close();
        ps.close();
        return types;
    }

    private int getTypeID(String type) throws SQLException {
        PreparedStatement ps = con.prepareStatement("Select id FROM admin_user_types WHERE type = ?");
        ps.setString(1, type);
        rs = ps.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return id;
    }

    public boolean blockAdmin(String admin_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE admin_login SET blockstatus = 2 WHERE admin_id = ?");
        ps.setString(1, admin_id);
        int count = ps.executeUpdate();
        ps.close();
        return count == 1;
    }

    public int checkNBUname(String uname) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_logger_not_blocked WHERE uname = ?");
        ps.setString(1, uname);
        rs = ps.executeQuery();
        int c = 0;
        if (rs.next()) {
            c = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return c;
    }

    public boolean checkNBAdminID(String admin_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_logger_not_blocked WHERE admin_id = ?");
        ps.setString(1, admin_id);
        rs = ps.executeQuery();
        boolean flag = false;
        if (rs.next()) {
            if (rs.getInt(1) == 1) {
                flag = true;
            }
        }
        rs.close();
        ps.close();
        return flag;
    }

    public boolean checkAdminID(String admin_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_logger WHERE admin_id = ?");
        ps.setString(1, admin_id);
        rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) == 1) {
                return true;
            }
        }
        return false;
    }

    public ArrayList<User> getAllUsers() throws SQLException {
        PreparedStatement getAllUsers = con.prepareStatement("SELECT slno,name,type,lastlogged,status FROM admin_users ");
        ArrayList<User> allUsers = new ArrayList<>();
        rs = getAllUsers.executeQuery();
        while (rs.next()) {
            User u = new User(rs.getString("name"), rs.getString("type"), rs.getString("lastlogged"), rs.getString("slno"), rs.getString("status"));
            allUsers.add(u);
        }
        rs.close();
        getAllUsers.close();
        return allUsers;
    }

    public ArrayList<UserType> getAllTypes() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id,type,superType FROM admin_types ");
        ArrayList<UserType> allType = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            UserType t = new UserType(rs.getString("id"), rs.getString("type"), rs.getString("superType"));
            allType.add(t);
        }
        rs.close();
        ps.close();
        return allType;
    }

    public ArrayList<UserType1> getAllUserTypes() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id,type,superType FROM admin_types ");
        ArrayList<UserType1> allType = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            UserType1 t = new UserType1(rs.getString("type"));
            allType.add(t);
        }
        rs.close();
        ps.close();
        return allType;
    }

    public ArrayList<SuperUserType> getAllSuperTypes() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT id,type FROM admin_types ");
        ArrayList<SuperUserType> allType = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            SuperUserType t = new SuperUserType(rs.getString("id"), rs.getString("type"));
            allType.add(t);
        }
        rs.close();
        ps.close();
        return allType;
    }

    public int checkType(String type) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_types WHERE type = ?");
        int count = 0;
        ps.setString(1, type);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public int checkSuperType(String superType) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_types WHERE id = ?");
        int count = 0;
        ps.setString(1, superType);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public boolean addType(AddUserType addTyp) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO admin_user_type(type,supertype) VALUES (?,?)");
        ps.setString(1, addTyp.getType());
        ps.setString(2, addTyp.getSuperType());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean updateUserType(UpdateUserType updateTyp) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE admin_user_type SET supertype = ? WHERE id = ?");
        ps.setString(1, updateTyp.getSuperType());
        ps.setString(2, updateTyp.getTypeid());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean checkSubtypeSuperType(String superType, String typeid) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT type,id FROM admin_user_type WHERE supertype = ?");
        ps.setString(1, typeid);
        rs = ps.executeQuery();
        HashSet<String> hs = new HashSet<>();
        while (rs.next()) {
            hs.add(rs.getString(2));
        }
        hs.remove(typeid);
        rs.close();
        ps.close();
        if (hs.contains(superType)) {
            return false;
        }
        return true;
    }

    public int checkTypeWithID(String type, String typeid) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_types WHERE type = ? and id = ?");
        int count = 0;
        ps.setString(1, type);
        ps.setString(2, typeid);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public boolean deleteUserType(String typeID) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM admin_user_type WHERE id = ?");
        ps.setString(1, typeID);
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public int checkEmail(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_users WHERE email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public void addUser(AddUser au) throws SQLException {
        addToUser(au);
        addToLog(au);
        addToEmail(au);
    }

    private void addToUser(AddUser au) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO admin_user(user_id,name,designation,address1,address2,place,pin,type) VALUES(?,?,?,?,?,?,?,?)");
        ps.setString(1, au.getNew_admin_id());
        ps.setString(2, au.getName());
        ps.setString(3, au.getDesignation());
        ps.setString(4, au.getAddress1());
        ps.setString(5, au.getAddress2());
        ps.setString(6, au.getPlace());
        ps.setString(7, au.getPin());
        ps.setString(8, au.getUtype());
        ps.executeUpdate();
        ps.close();
    }

    private void addToLog(AddUser au) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO admin_login(uname,password,salt,admin_id) VALUES(?,?,?,?)");
        ps.setString(1, au.getUname());
        ps.setString(2, au.getPassword());
        ps.setString(3, au.getSalt());
        ps.setString(4, au.getNew_admin_id());
        ps.executeUpdate();
        ps.close();
    }

    private void addToEmail(AddUser au) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO admin_email(email,admin_id) VALUES(?,?)");
        ps.setString(1, au.getEmail());
        ps.setString(2, au.getNew_admin_id());
        ps.executeUpdate();
        ps.close();
    }

    public void removeUser(String new_admin_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM admin_user WHERE user_id = ?");
        ps.setString(1, new_admin_id);
        ps.executeUpdate();
        ps.close();
    }

    public int checkSerialNumber(String slno) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_users WHERE slno = ?");
        ps.setString(1, slno);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        ps.close();
        rs.close();
        return count;
    }

    public UserDetails getUserDetails(String slno) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT name,designation,address1,address2,place,pin,type,email,uname,status FROM admin_users WHERE slno = ?");
        ps.setString(1, slno);
        rs = ps.executeQuery();
        UserDetails ud;
        if (rs.next()) {
            ud = new UserDetails(rs.getString("name"), rs.getString("designation"), rs.getString("address1"), rs.getString("address2"), rs.getString("place"), rs.getString("pin"), rs.getString("type"), rs.getString("email"), rs.getString("uname"), rs.getString("status"));
        } else {
            ud = new UserDetails("invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid");
        }
        rs.close();
        ps.close();
        return ud;
    }

    public int changePassword(NewPassword np) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE admin_login SET password = ? ,salt = ? WHERE admin_id = ?");
        ps.setString(1, np.getNewPassword());
        ps.setString(2, np.getSalt());
        ps.setString(3, np.getAdminId());
        int flag = ps.executeUpdate();
        ps.close();
        return flag;
    }

    public boolean changePassword(ResetAffiliateUser req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate_user_login SET password = ? WHERE affiliate_user_id = ?");
        ps.setString(1, req.getPassword());
        ps.setString(2, req.getUser_id());
        int c = ps.executeUpdate();
        return c == 1;
    }

    public void updateUser(EditUser eu) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE admin_user SET name = ?,designation = ?,address1 = ?, address2 = ?,place = ?, pin = ?, type = ? WHERE slno = ?");
        ps.setString(1, eu.getName());
        ps.setString(2, eu.getDesignation());
        ps.setString(3, eu.getAddress1());
        ps.setString(4, eu.getAddress2());
        ps.setString(5, eu.getPlace());
        ps.setString(6, eu.getPin());
        ps.setString(7, eu.getUtype());
        ps.setString(8, eu.getId());
        ps.executeUpdate();
        ps.close();
    }

    public String getType(String type) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT type FROM admin_types WHERE id = ?");
        ps.setString(1, type);
        rs = ps.executeQuery();
        String userType = "";
        if (rs.next()) {
            userType = rs.getString(1);
        }
        rs.close();
        ps.close();
        return userType;
    }

    public String getAdminID(String uid) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT user_id FROM admin_users WHERE slno = ?");
        ps.setString(1, uid);
        rs = ps.executeQuery();
        rs.next();
        String aid = rs.getString(1);
        rs.close();
        ps.close();
        return aid;
    }

    public int getNoOfWebsites() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliates");
        rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        return count;
    }

    public int getNoOfMembers() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM member_profile");
        rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        return count;
    }

    public int getMaxPageNo(String view, int max) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM " + view);
        rs = ps.executeQuery();
        rs.next();
        double noOfAffiliates = rs.getInt(1);
        int mpn = 0;
        if (noOfAffiliates > 0) {
            mpn = (int) Math.ceil(noOfAffiliates / max);
        }
        rs.close();
        ps.close();
        return mpn;
    }

    public int getMaxPageNo(String view, int max, String whereCondition) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM " + view + " WHERE " + whereCondition);
        rs = ps.executeQuery();
        rs.next();
        double noOfAffiliates = rs.getInt(1);
        int mpn = 0;
        if (noOfAffiliates > 0) {
            mpn = (int) Math.ceil(noOfAffiliates / max);
        }
        rs.close();
        ps.close();
        return mpn;
    }

    public ArrayList<AllAffiliates> getAllAffiliates(int offset, int me) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM vaydeal.affiliates LIMIT ?,?");
        ps.setInt(1, offset);
        ps.setInt(2, me);
        ArrayList<AllAffiliates> allAffiliates = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            AllAffiliates af = new AllAffiliates(rs.getString("affiliate_id"), rs.getString("company"), rs.getString("website"), ImagePath.getLOGO_PATH() + rs.getString("company") + "/logo." + rs.getString("extension"), rs.getString("status"));
            allAffiliates.add(af);
        }
        rs.close();
        ps.close();
        return allAffiliates;
    }

    public boolean validateAffiliate(String aff) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliates WHERE company = ?");
        ps.setString(1, aff);
        rs = ps.executeQuery();
        rs.next();
        int affl = rs.getInt(1);
        rs.close();
        ps.close();
        return affl == 1;
    }

    public boolean blockAffiliate(String affiliate) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate SET status = 2 WHERE `company` = ?");
        ps.setString(1, affiliate);
        int affl = ps.executeUpdate();
        ps.close();
        blockUsersofAffiliate(affiliate);
        return affl == 1;
    }

    public UserIds getUserIds(String str) throws SQLException {
        UserIds uids = new UserIds();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM admin_users WHERE user_id LIKE '" + str + "%' LIMIT 10");
        rs = ps.executeQuery();
        while (rs.next()) {
            uids.addUserID(rs.getString(1));
        }
        rs.close();
        ps.close();
        return uids;
    }

    public boolean checkAdminActivity(String activity) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_activities WHERE activity = ?");
        ps.setString(1, activity);
        rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        return count == 1;
    }

    public int checkCompany(String company) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliates WHERE company = ?");
        int count = 0;
        ps.setString(1, company);
        rs = ps.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public boolean checkLink(String urlString) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliates WHERE website = ?");
        boolean flag = false;
        ps.setString(1, urlString);
        rs = ps.executeQuery();
        rs.next();
        if (rs.getInt(1) == 0) {
            flag = true;
        }
        rs.close();
        ps.close();
        return flag;
    }

    public boolean addAffiliate(AddAffiliate req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO affiliate ( company, website, extension) VALUES (?, ?, ?)");
        String ext = req.getFileName().substring(req.getFileName().lastIndexOf(".") + 1);
        ps.setString(1, req.getCompany());
        ps.setString(2, req.getLink());
        ps.setString(3, ext);
        int c = ps.executeUpdate();
        boolean flag = false;
        if (c == 1) {
            flag = true;
        }
        ps.close();
        return flag;
    }

    public AffiliateDetails getAffiliateDetails(String company) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT company,max_premium_payment,company_factor,status FROM affiliates WHERE company = ?");
        ps.setString(1, company);
        rs = ps.executeQuery();
        AffiliateDetails ad;
        if (rs.next()) {
            ad = new AffiliateDetails(rs.getString("company"), rs.getString("max_premium_payment"), rs.getString("company_factor"), rs.getString("status"));
        } else {
            ad = new AffiliateDetails();
        }
        rs.close();
        ps.close();
        return ad;
    }

    public boolean updateAffiliate(UpdateAffiliate req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate SET status = ?,max_premium_payment = ?,company_factor = ? WHERE `company` = ?");
        ps.setString(1, req.getStatus());
        ps.setString(2, req.getMpp());
        ps.setString(3, req.getCf());
        ps.setString(4, req.getCompany());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public ArrayList<AllPremiumPayments> getAllPremiumPayments(int offset, int me) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT company,SUM(amount) amount,MIN(end_time_stamp) expiry FROM affiliate_premium_payments WHERE status = 'active' GROUP BY company ORDER BY SUM(amount) DESC LIMIT ?,?");
        ps.setInt(1, offset);
        ps.setInt(2, me);
        ArrayList<AllPremiumPayments> allPremiumPayments = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            AllPremiumPayments af = new AllPremiumPayments(rs.getString("company"), rs.getString("amount"), rs.getString("expiry"));
            allPremiumPayments.add(af);
        }
        rs.close();
        ps.close();
        return allPremiumPayments;
    }

    public ArrayList<PremiumPayments> getActivePremiumPayments(int offset, int me, String company) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT company,amount,time_stamp,reference_no,status FROM affiliate_premium_payments WHERE company = ? and status = 'active' ORDER BY time_stamp DESC LIMIT ?,?");
        ps.setString(1, company);
        ps.setInt(2, offset);
        ps.setInt(3, me);
        ArrayList<PremiumPayments> activePremiumPayments = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            PremiumPayments af = new PremiumPayments(rs.getString("company"), rs.getString("amount"), rs.getString("time_stamp"), rs.getString("reference_no"), rs.getString("status"));
            activePremiumPayments.add(af);
        }
        rs.close();
        ps.close();
        return activePremiumPayments;
    }

    public ArrayList<PremiumPayments> getTotalPremiumPayments(int offset, int me, String company) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT company,amount,time_stamp,reference_no,status FROM affiliate_premium_payments WHERE company = ? ORDER BY time_stamp DESC LIMIT ?,?");
        ps.setString(1, company);
        ps.setInt(2, offset);
        ps.setInt(3, me);
        ArrayList<PremiumPayments> totalPremiumPayments = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            PremiumPayments af = new PremiumPayments(rs.getString("company"), rs.getString("amount"), rs.getString("time_stamp"), rs.getString("reference_no"), rs.getString("status"));
            totalPremiumPayments.add(af);
        }
        rs.close();
        ps.close();
        return totalPremiumPayments;
    }

    public boolean addPremiumPayment(AddPremiumPayment req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO `vaydeal`.`affiliate_premium_payments` (`company`, `amount`, `reference_no`,`time_stamp`,`end_time_stamp`) VALUES ( ?, ?, ?,CURDATE(),DATE_ADD(CURDATE(), INTERVAL 1 YEAR))");
        ps.setString(1, req.getCompany());
        ps.setString(2, req.getAmount());
        ps.setString(3, req.getReferenceNo());
        int c = ps.executeUpdate();
        boolean flag = false;
        if (c == 1) {
            flag = true;
        }
        ps.close();
        return flag;
    }

    public void getAffiliateUserDetails(GetAffiliateUsers req, ArrayList<AffiliateUserDetails> aud) throws SQLException {
        PreparedStatement ps;
        int me = Integer.parseInt(req.getMaxEntries());
        int pn = Integer.parseInt(req.getPageNo());
        int start = (pn - 1) * me;
        switch (req.getQueryType()) {
            case "company":
                ps = con.prepareStatement("SELECT affiliate,affiliate_user_name,affiliate_user_id,affiliate_user_status,last_logged FROM vaydeal.affiliate_logger WHERE affiliate = ? LIMIT ?,?");
                ps.setString(1, req.getQuery());
                ps.setInt(2, start);
                ps.setInt(3, me);
                rs = ps.executeQuery();
                while (rs.next()) {
                    AffiliateUserDetails auds = new AffiliateUserDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4));
                    aud.add(auds);
                }
                rs.close();
                ps.close();
                break;
            case "user":
                ps = con.prepareStatement("SELECT affiliate,affiliate_user_name,affiliate_user_id,affiliate_user_status,last_logged FROM vaydeal.affiliate_logger WHERE affiliate_user_id=?");
                ps.setString(1, req.getQuery());
                rs = ps.executeQuery();
                if (rs.next()) {
                    AffiliateUserDetails auds = new AffiliateUserDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4));
                    aud.add(auds);
                }
                rs.close();
                ps.close();
                break;
            case "all":
                ps = con.prepareStatement("SELECT affiliate,affiliate_user_name,affiliate_user_id,affiliate_user_status,last_logged FROM vaydeal.affiliate_logger LIMIT ?,?");
                ps.setInt(1, start);
                ps.setInt(2, me);
                rs = ps.executeQuery();
                while (rs.next()) {
                    AffiliateUserDetails auds = new AffiliateUserDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4));
                    aud.add(auds);
                }
                rs.close();
                ps.close();
                break;
        }
    }

    public boolean validateAuid(String auid) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, auid);
        rs = ps.executeQuery();
        rs.next();
        int affl = rs.getInt(1);
        rs.close();
        ps.close();
        return affl == 1;
    }

    public boolean blockAffiliateUsers(ArrayList<String> auids) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate_user SET affiliate_user_status = 2 WHERE affiliate_user_id = ?");
        int affl = 0;
        for (String auid : auids) {
            ps.setString(1, auid);
            affl += ps.executeUpdate();
        }
        ps.close();
        return affl == auids.size();
    }

    public void getAffiliates(ArrayList<AffiliateCompany> affiliates) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT company FROM vaydeal.affiliates ");
        rs = ps.executeQuery();
        while (rs.next()) {
            AffiliateCompany ac = new AffiliateCompany(rs.getString("company"));
            affiliates.add(ac);
        }
        rs.close();
        ps.close();
    }

    public int checkAffiliateUserEmail(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public int checkAffiliateUserMobile(String mobile) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_mobile = ?");
        ps.setString(1, mobile);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return count;
    }

    public String getNewAdminID() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT user_id FROM admin_users");
        ArrayList<String> al = new ArrayList<>();
        String new_admin_id = "";
        rs = ps.executeQuery();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        Random random = new Random();
        new_admin_id = "" + (random.nextInt(9999999) + 45573456);
        while (al.contains(new_admin_id)) {
            new_admin_id = "" + (random.nextInt(9999999) + 45573456);
        }
        return new_admin_id;
    }

    public String getNewAffiliateUserId() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT affiliate_user_id FROM affiliate_logger");
        ArrayList<String> al = new ArrayList<>();
        String new_affiliate_user_id = "";
        rs = ps.executeQuery();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        Random random = new Random();
        new_affiliate_user_id = "" + (random.nextInt(9999999) + 45573456);
        while (al.contains(new_affiliate_user_id)) {
            new_affiliate_user_id = "" + (random.nextInt(9999999) + 45573456);
        }
        return new_affiliate_user_id;
    }

    public void addAffliateUser(AddAffiliateUser req) throws SQLException {
        addAffiliateUserDetails(req);
        addAffiliateUserLogin(req);
    }

    private void addAffiliateUserDetails(AddAffiliateUser req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO affiliate_user(affiliate_user_id,affiliate,affiliate_user_name,affiliate_user_email,affiliate_user_mobile,affiliate_user_type) VALUES(?,?,?,?,?,1)");
        ps.setString(1, req.getNew_user_id());
        ps.setString(2, req.getAffiliate());
        ps.setString(3, req.getName());
        ps.setString(4, req.getEmail());
        ps.setString(5, req.getMobile());
        ps.executeUpdate();
        ps.close();
    }

    private void addAffiliateUserLogin(AddAffiliateUser req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO affiliate_user_login(affiliate_user_id,password,salt,last_logged) VALUES (?,?,?,NOW())");
        ps.setString(1, req.getNew_user_id());
        ps.setString(2, req.getPassword());
        ps.setString(3, req.getSalt());
        ps.executeUpdate();
        ps.close();
    }

    public void removeAffiliateUser(String new_user_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM affiliate_user WHERE affiliate_user_id = ?");
        ps.setString(1, new_user_id);
        ps.executeUpdate();
        ps.close();
    }

    public String getVisibilityStatusById(String status_id) throws SQLException {
        String result = "";
        PreparedStatement ps = con.prepareStatement("SELECT status FROM visibility_status WHERE status_id=?");
        ps.setString(1, status_id);
        rs = ps.executeQuery();
        while (rs.next()) {
            result = rs.getString(1);
        }
        rs.close();
        ps.close();
        return result;
    }

    public ArrayList<SuperCategory> getAllSuperCategories() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT su.super_category_id, su.super_category, su.online_visibility_status, su.offline_visibility_status FROM super_categories su");
        ArrayList<SuperCategory> result = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            SuperCategory sc = new SuperCategory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            result.add(sc);
        }
        rs.close();
        ps.close();
        return result;
    }

    public boolean addSuperCategory(AddSuperCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO super_category (super_category) values (?)");
        ps.setString(1, req.getSuper_category());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public ArrayList<Category> getAllCategories() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT c.category_id, c.category, c.super_category, c.online_visibility_status, c.offline_visibility_status FROM categories AS c ");
        ArrayList<Category> categs = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            Category category = new Category(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            categs.add(category);
        }
        rs.close();
        ps.close();
        return categs;
    }

    public ArrayList<SubCategory> getAllSubCategories() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT c.sub_category_id, c.sub_category, c.category, c.online_visibility_status, c.offline_visibility_status FROM sub_categories ");
        ArrayList<SubCategory> sub = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            SubCategory category = new SubCategory(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            sub.add(category);
        }
        rs.close();
        ps.close();
        return sub;
    }

    public int checkSuperCategory(String name) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM super_categories WHERE super_category=?");
        ps.setString(1, name);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }

    public int checkCategory(String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM categories WHERE category=?");
        int res = 0;
        ps.setString(1, name);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }

    public boolean addCategory(AddCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO category (category, super_category) values (?,?)");
        ps.setString(1, req.getCategory());
        ps.setString(2, req.getSuperCategory());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public int checkSuperCategoryById(String id) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM super_categories WHERE super_category_id=?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }

    public int checkCategoryById(String id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM categories WHERE category_id=?");
        int res = 0;
        ps.setString(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }

    public int checkSubCategory(String category) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM sub_categories WHERE sub_category=?");
        int res = 0;
        ps.setString(1, category);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }

    public boolean addSubCategory(AddSubCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO sub_category (sub_category, category) values (?,?)");
        ps.setString(1, req.getSubCategory());
        ps.setString(2, req.getCategory());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public boolean deleteSuperCategory(DeleteSuperCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE super_category SET online_visibility_status=?, offline_visibility_status=? WHERE super_category_id=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getSuper_category());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public boolean deleteCategory(DeleteCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE category SET online_visibility_status=?, offline_visibility_status=? WHERE category_id=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getCategory());
        int exe = ps.executeUpdate();
        ps.close();  
        return exe == 1;
    }

    public boolean deleteSubCategory(DeleteSubCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE sub_category SET online_visibility_status=?, offline_visibility_status=? WHERE sub_category_id=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getSub_category());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public int checkSubCategoryById(String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM sub_categories WHERE sub_category_id=?");
        int res = 0;
        ps.setString(1, name);
        rs = ps.executeQuery();
        rs.next();
        res = rs.getInt(1);
        rs.close();
        ps.close();
        return res;
    }

    public int checkVisibilityStatusById(String name) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM visibility_status WHERE status_id=?");
        int res = 0;
        ps.setString(1, name);
        rs = ps.executeQuery();
        rs.next();
        res = rs.getInt(1);
        rs.close();
        ps.close();
        return res;
    }

    public boolean updateSuperCategory(UpdateSuperCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE super_category SET online_visibility_status=?, offline_visibility_status=? WHERE super_category_id=?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getSuper_category());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public boolean updateSubCategory(UpdateSubCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE sub_category SET online_visibility_status=?, offline_visibility_status=? WHERE sub_category_id=?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getSubcat());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public boolean updateCategory(UpdateCategory req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE category SET online_visibility_status=?, offline_visibility_status=? WHERE category_id=?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getCateg());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public boolean addSpecification(AddSpecification req) throws SQLException {
        int c = 0;
        List<String> specific = req.getSpecific();
        for (String spec : specific) {
            PreparedStatement ps = con.prepareStatement("INSERT INTO product_type_and_specification (product_type, specification) VALUES (?,?)");
            ps.setString(1, req.getpType());
            ps.setString(2, spec);
            c += ps.executeUpdate();
            ps.close();
        }
        return specific.size() == c;
    }

    public boolean addProductType(AddProductType req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO product_type (product_type) values (?)");
        ps.setString(1, req.getpType());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean deleteTaC(DeleteTaC req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE product_type_and_category SET online_visibility_status=? and offline_visibility_status=? WHERE id=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getTaC());
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean addBrand(AddBrand req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO brand (brand) values (?)");
        ps.setString(1, req.getBrand());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    public int checkTaC(String category, String type) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM tac_mapping WHERE category = ? AND product_type = ?");
        ps.setString(1, category);
        ps.setString(2, type);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c;
    }

    public boolean addTaC(AddTaC req) throws SQLException {
        PreparedStatement stmt = con.prepareStatement("INSERT INTO product_type_and_category (product_type, category) values (?,?)");
        stmt.setString(1, req.getpType());
        stmt.setString(2, req.getCategory());
        int c = stmt.executeUpdate();
        return c == 1;
    }
    
    private void blockUsersofAffiliate(String affiliate) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate_user SET affiliate_user_status = 2 WHERE affiliate = ?");
        ps.setString(1, affiliate);
        ps.executeUpdate();
        ps.close();
    }
    
    public boolean deleteBrand(DeleteBrand req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE brand SET online_visibility_status=?, offline_visibility_status=? WHERE brand=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getBrand());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public int checkTaCById(String tacId) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tac_mapping WHERE id=?");
        ps.setString(1, tacId);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public ArrayList<Brand> getAllBrands(String maxEntries, String pageNo) throws SQLException {
        ArrayList<Brand> res = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM brands LIMIT(?,?)");
        int n = Integer.parseInt(maxEntries);
        stmt.setInt(2, n);
        stmt.setInt(1, n * (Integer.parseInt(pageNo)-1));
        ResultSet exe = stmt.executeQuery();
        while (exe.next()) {
            Brand brand = new Brand(exe.getString(1), exe.getString(2), exe.getString(3), exe.getString(4));
            res.add(brand);
        }
        return res;
    }
    
    public ArrayList<ProductType> getAllProductTypes(String pageNo, String maxEntries) throws SQLException {
        ArrayList<ProductType> res = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM brands LIMIT(?,?)");
        int n = Integer.parseInt(maxEntries);
        ps.setInt(2, n);
        ps.setInt(1, n * (Integer.parseInt(pageNo)-1));
        rs = ps.executeQuery();
        while (rs.next()) {
            ProductType pt = new ProductType(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            res.add(pt);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public ArrayList<Category> getCategories(GetCategories req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT c.category_id, c.category, c.super_category, c.online_visibility_status, c.offline_visibility_status FROM categories AS c ");
        ArrayList<Category> categs = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            Category category = new Category(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            categs.add(category);
        }
        rs.close();
        ps.close();
        return categs;
    }
    
    public ArrayList<ProductType> getProductTypes(GetProductTypes req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM product_types ");
        ArrayList<ProductType> res = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            ProductType ptype = new ProductType(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            res.add(ptype);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public ProductType getProductType(GetProductType req) throws SQLException {
        ProductType res = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM product_types WHERE product_type_id = ?");
        ps.setString(1, req.getPtid());
        rs = ps.executeQuery();
        rs.next();
        res = new ProductType(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        rs.close();
        ps.close();
        return res;
    }
    
    public SubCategory getSubCategory(GetSubCategory req) throws SQLException {
        SubCategory res = null;
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM sub_categories WHERE sub_category_id=?");
        stmt.setString(1, req.getSubcategid());
        ResultSet r = stmt.executeQuery();
        if (r.next()) {
            res = new SubCategory(r.getString(1), r.getString(2), r.getString(3), r.getString(4), r.getString(5));
        }else{
            res = new SubCategory("invalid", "invalid", "invalid", "invalid", "invalid");
        }
        return res;
    }
    
    public int checkTacId(String tacid) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM tac_mapping WHERE id=?");
        ps.setString(1, tacid);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public int checkPTypeId(String ptypeid) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM product_types WHERE product_type_id=?");
        ps.setString(1, ptypeid);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public TaC getTaC(GetTaC req) throws SQLException {
        TaC res = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tac_mapping WHERE id = ?");
        ps.setString(1, req.getTacid());
        rs = ps.executeQuery();
        res = new TaC(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        rs.close();
        ps.close();
        return res;
    }
    
    public int checkBrand(String brand) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM brands WHERE brand=?");
        ps.setString(1, brand);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean updateBrand(UpdateBrand req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE brand SET online_visibility_status=?, offline_visibility_status=? WHERE brand=?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getBrand());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public Brand getBrand(GetBrand req) throws SQLException {
        Brand res = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM brands WHERE brand=?");
        ps.setString(1, req.getBrand());
        rs = ps.executeQuery();
        if (rs.next()) {
            res = new Brand(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean updateTaC(UpdateTaC req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE product_type_and_category SET online_visibility_status=?, offline_visibility_status=? WHERE id = ?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getTac());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public ArrayList<Brand> getBrands(GetBrands req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM brands ");
        ArrayList<Brand> res = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            Brand ptype = new Brand(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4));
            res.add(ptype);
        }
        rs.close();
        ps.close();
        return res;
    }

    public ArrayList<TaC> getTaCs(GetTaCs req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tac_mapping");
        ArrayList<TaC> res = new ArrayList<>();
        rs = ps.executeQuery();
        while (rs.next()) {
            TaC ptype = new TaC(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5));
            res.add(ptype);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean updateProductType(UpdateProductType req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE product_type SET online_visibility_status=?, offline_visibility_status=? WHERE product_type_id=?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(4, req.getpTypeid());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public Category getCategory(GetCategory req) throws SQLException {
        Category res = null;
        PreparedStatement ps = con.prepareStatement("SELECT c.category_id, c.category, c.super_category, c.online_visibility_status, c.offline_visibility_status FROM categories AS c WHERE c.category_id=?");
        ps.setString(1, req.getCategid());
        rs = ps.executeQuery();
        if (rs.next()) {
            res = new Category(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public ArrayList<TaC> getAllTaC(String pageNO, String maxEntries) throws SQLException {
        ArrayList<TaC> res = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM tac_mapping LIMIT(?,?)");
        int n = Integer.parseInt(maxEntries);
        ps.setInt(2, n);
        ps.setInt(1, n * (Integer.parseInt(pageNO)-1));
        rs = ps.executeQuery();
        while (rs.next()) {
            TaC tac = new TaC(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5));
            res.add(tac);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public SuperCategory getSuperCategory(GetSuperCategory req) throws SQLException {
        SuperCategory res = null;
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM super_categories WHERE super_category_id=?");
        stmt.setString(1, req.getSupcategid());
        ResultSet r = stmt.executeQuery();
        if (r.next()) {
            res = new SuperCategory(r.getString(1), r.getString(2), r.getString(3), r.getString(4));
        }else{
            res = new SuperCategory("invalid", "invalid", "invalid", "invalid");
        }
        return res;
    }
    
    public int checkPType(String ptype) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM product_types WHERE product_type=?");
        ps.setString(1, ptype);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public ArrayList<Specifications> loadSpecifications(LoadSpecifications req) throws SQLException {
        ArrayList<Specifications> res = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM taspec_mapping WHERE product_type = ?");
        ps.setString(1, req.getPtype());
        rs = ps.executeQuery();
        while(rs.next()){
            Specifications spec = new Specifications(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            res.add(spec);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public int checkSpecid(String id) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM taspec_mapping WHERE id=?");
        ps.setString(1, id);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean deleteSpecification(DeleteSpecification req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE product_type_and_specification SET online_visibility_status=?, offline_visibility_status=?, filter_visibility_status=? WHERE id=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, "2");
        ps.setString(4, req.getpSpecId());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public Specifications getSpecification(GetSpecification req) throws SQLException {
        Specifications res = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM taspec_mapping WHERE id = ?");
        ps.setString(1, req.getSpecid());
        rs = ps.executeQuery();
        rs.next();
        res = new Specifications(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4),rs.getString(5),rs.getString(6));
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean updateSpecification(UpdateSpecification req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE product_type_and_specification SET online_visibility_status=?, offline_visibility_status=?, filter_visibility_status = ? WHERE id = ?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getFltr_status());
        ps.setString(4, req.getSpecid());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public ArrayList<BPaC> getBPaCs(GetBPaCs req) throws SQLException {
        ArrayList<BPaC> res = new ArrayList<>();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bpac_mapping WHERE brand = ?");
        ps.setString(1, req.getBrand());
        rs = ps.executeQuery();
        while(rs.next()){
            BPaC bpac = new BPaC(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            res.add(bpac);
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean updateBPaC(UpdateBPaC req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE brand_product_type_and_category SET online_visibility_status=?, offline_visibility_status=? WHERE bpac_id = ?");
        ps.setString(1, req.getOn_status());
        ps.setString(2, req.getOff_status());
        ps.setString(3, req.getBpac());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }
    
    public boolean deleteBPaC(DeleteBPaC req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE brand_product_type_and_category SET online_visibility_status=?, offline_visibility_status=? WHERE bpac_id = ?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getBpacid());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    

    

    

    

    public int checkSpecific(String specific, String ptype) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM product_type_and_specific WHERE product_type=? AND specification=?");
        stmt.setString(1, ptype);
        stmt.setString(2, specific);

        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }

    

    

    

    

    

    

    public boolean addBPaC(AddBPaC req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO brand_product_type_and_category (brand, tac_id) values (?,?)");
        ps.setString(1, req.getBrand());
        ps.setString(2, req.getTac());
        int exe = ps.executeUpdate();
        ps.close();
        return exe == 1;
    }

    

    

    public int checkSpecId(String specid) {
        int res = 0;
        return res;
    }

    public int checkTaCOffset(String no, String offset) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM product_type_and_category LIMIT(?,?)");
        int n = Integer.parseInt(no);
        stmt.setInt(2, n);
        stmt.setInt(1, n * Integer.parseInt(offset));
        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }

    public int checkPTypeOffset(String no, String offset) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM product_type LIMIT(?,?)");
        int n = Integer.parseInt(no);
        stmt.setInt(2, n);
        stmt.setInt(1, n * Integer.parseInt(offset));
        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }

    public int checkBrandOffset(String no, String offset) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM brand LIMIT(?,?)");
        int n = Integer.parseInt(no);
        stmt.setInt(2, n);
        stmt.setInt(1, n * Integer.parseInt(offset));
        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }

    

    

    

    

    

    public ArrayList<Brand> searchBrand(SearchBrand req) {
        ArrayList<Brand> res = null;
        return res;
    }

    

    

    

    

    

    

    

    

    

    
    
    
/////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////

    public BPaC getBPaC(GetBPaC req) throws SQLException {
        BPaC res = null;
        PreparedStatement ps = con.prepareStatement("SELECT * FROM bpac_mapping WHERE bpac_id = ?");
        ps.setString(1, req.getBpacid());
        rs = ps.executeQuery();
        if (rs.next()) {
            res = new BPaC(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
        }
        rs.close();
        ps.close();
        return res;
    }
    
    public boolean checkNBAffiliateID(String user_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, user_id);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public boolean changeAffiliateUserStatus(String user_id, String status) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate_user SET affiliate_user_status = ? WHERE affiliate_user_id = ?");
        ps.setString(1, status);
        ps.setString(2, user_id);
        int c = ps.executeUpdate();
        return c == 1;
    }

    public boolean checkAffiliateUserId(String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_logger WHERE affiliate_user_id = ?");
        ps.setString(1, param);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public void getUserDetails(String param, ArrayList<String> al) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT affiliate_user_email, affiliate_user_name FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, param);
        rs = ps.executeQuery();
        rs.next();
        al.add(rs.getString(1));
        al.add(rs.getString(2));
        rs.close();
        ps.close();
    }

    public boolean checkAffiliateActivity(String activity) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_activities WHERE activity = ?");
        ps.setString(1, activity);
        rs = ps.executeQuery();
        rs.next();
        int count = rs.getInt(1);
        rs.close();
        ps.close();
        return count == 1;
    }

    public void getAffiliateRequests(GetAffiliateRequests req, ArrayList<AffiliateRequests> ar) throws SQLException {
        String where = "";
        int me = Integer.parseInt(req.getMaxEntries());
        int start = Integer.parseInt(req.getPageNo()) * me;
        switch (req.getStatus()) {
            case "1":
                where = "WHERE affiliate_request_status = 'pending' ";
                break;
            case "2":
                where = "WHERE affiliate_request_status = 'processing' ";
                break;
            case "3":
                where = "WHERE affiliate_request_status = 'completed' ";
                break;
        }
        PreparedStatement ps = con.prepareStatement("SELECT affiliate_request_id,affiliate_request_company,affiliate_request_website,affiliate_request_date,affiliate_request_update_date,affiliate_request_status FROM affiliate_requests " + where + "LIMIT ?,?");
        rs = ps.executeQuery();
        ps.setInt(1, start);
        ps.setInt(2, me);
        while (rs.next()) {
            AffiliateRequests aR = new AffiliateRequests(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            ar.add(aR);
        }
        rs.close();
        ps.close();
    }

    public boolean checkreqId(String reqId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_requests WHERE affiliate_request_id = ?");
        ps.setString(1, reqId);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public AffiliateRequest getAffiliateRequest(String reqId) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM affiliate_requests WHERE affiliate_request_id = ?");
        ps.setString(1, reqId);
        rs = ps.executeQuery();
        AffiliateRequest ar = null;
        if (rs.next()) {
            ar = new AffiliateRequest(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9));
        } else {
            ar = new AffiliateRequest("invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid");
        }
        rs.close();
        ps.close();
        return ar;
    }

    public boolean changeAffiliateRequestStatus(String request_id, String status) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate_request SET affiliate_request_status = ? WHERE affiliate_request_id = ?");
        ps.setString(1, status);
        ps.setString(2, request_id);
        int c = ps.executeUpdate();
        ps.close();
        return c == 1;
    }

    public boolean checkMemberMobile(String mobile) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM member_profile WHERE member_mobile = ?");
        ps.setString(1, mobile);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 0;
    }

    public boolean checkMemberEmail(String email) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM member_profile WHERE member_email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 0;
    }

    public String getNewMemberId() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT member_id FROM member_logger");
        ArrayList<String> al = new ArrayList<>();
        String new_member_id = "";
        rs = ps.executeQuery();
        while (rs.next()) {
            al.add(rs.getString(1));
        }
        rs.close();
        ps.close();
        Random random = new Random();
        new_member_id = "" + (random.nextInt(9999999) + 45573456);
        while (al.contains(new_member_id)) {
            new_member_id = "" + (random.nextInt(9999999) + 45573456);
        }
        return new_member_id;
    }

    public void addMember(AddMember req) throws SQLException {
        addMemberDetails(req);
        addMemberLogin(req);
    }

    private void addMemberDetails(AddMember req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO member(member_id,member_type,member_name,member_email,member_mobile,member_date) VALUES(?,?,?,?,?,CURDATE())");
        ps.setString(1, req.getNew_member_id());
        ps.setString(2, req.getmType());
        ps.setString(3, req.getName());
        ps.setString(4, req.getEmail());
        ps.setString(5, req.getMobile());
        ps.executeUpdate();
        ps.close();
    }

    private void addMemberLogin(AddMember req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("INSERT INTO member_login(member_id,password,salt,last_logged) VALUES (?,?,?,NOW())");
        ps.setString(1, req.getNew_member_id());
        ps.setString(2, req.getPassword());
        ps.setString(3, req.getSalt());
        ps.executeUpdate();
        ps.close();
    }

    public void removeMember(String new_member_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("DELETE FROM member WHERE member_id = ?");
        ps.setString(1, new_member_id);
        ps.executeUpdate();
        ps.close();
    }

    public void getMemberDetails(GetMembers req, ArrayList<MemberDetails> md) throws SQLException {
        PreparedStatement ps = null;
        int me = Integer.parseInt(req.getMaxEntries());
        int pn = Integer.parseInt(req.getPageNo());
        int start = (pn - 1) * me;
        switch (req.getQueryType()) {
            case "member":
                ps = con.prepareStatement("SELECT a.member_id,a.member_name,b.product_month_uploaded,b.product_day_updated,b.amount_pending,a.member_status FROM member_profile a INNER JOIN member_report b ON a.member_id = b.member_id WHERE member_id = ? LIMIT ?,?");
                ps.setString(1, req.getQuery());
                ps.setInt(2, start);
                ps.setInt(3, me);
                break;
            case "all":
                ps = con.prepareStatement("SELECT a.member_id,a.member_name,b.product_month_uploaded,b.product_day_updated,b.amount_pending,a.member_status FROM member_profile a INNER JOIN member_report b ON a.member_id = b.member_id LIMIT ?,?");
                ps.setInt(1, start);
                ps.setInt(2, me);
                break;
        }
        rs = ps.executeQuery();
        while (rs.next()) {
            MemberDetails mds = new MemberDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6));
            md.add(mds);
        }
        rs.close();
        ps.close();
    }

    public boolean checkMemberId(String param) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM member_logger WHERE member_id = ?");
        ps.setString(1, param);
        rs = ps.executeQuery();
        rs.next();
        int c = rs.getInt(1);
        rs.close();
        ps.close();
        return c == 1;
    }

    public boolean changeMemberStatus(String member_id, String status) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE member SET member_status = ? WHERE member_id = ?");
        ps.setString(1, status);
        ps.setString(2, member_id);
        int c = ps.executeUpdate();
        return c == 1;
    }

    public MemberAllDetails getMemberAllDetails(String member_id) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT * FROM member_profile WHERE member_id = ?");
        ps.setString(1, member_id);
        rs = ps.executeQuery();
        MemberAllDetails ad;
        if (rs.next()) {
            ad = new MemberAllDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5), rs.getString(6), rs.getString(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getString(12), rs.getString(13), rs.getString(14), rs.getString(15), rs.getString(16), rs.getString(17), rs.getString(18), rs.getString(19), rs.getString(20), rs.getString(21), rs.getString(22), rs.getString(23), rs.getString(24), rs.getString(25), rs.getString(26), rs.getString(27), rs.getString(28), rs.getString(29), rs.getString(30), rs.getString(31), rs.getString(32), rs.getString(33), rs.getString(34));
        } else {
            ad = new MemberAllDetails();
        }
        rs.close();
        ps.close();
        return ad;
    }

    public boolean deleteProductType(DeleteProductType req) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE product_type SET online_visibility_status=?, offline_visibility_status=? WHERE product_type_id=?");
        ps.setString(1, "2");
        ps.setString(2, "2");
        ps.setString(3, req.getPtype());
        int exe = ps.executeUpdate();
        ps.close();  
        return exe == 1;
    }

    public int checkBpacid(String param) throws SQLException {
        int res = 0;
        PreparedStatement ps = con.prepareStatement("SELECT COUNT(*) FROM bpac_mapping WHERE bpac_id=?");
        ps.setString(1, param);
        rs = ps.executeQuery();
        if (rs.next()) {
            res = rs.getInt(1);
        }
        rs.close();
        ps.close();
        return res;
    }

}
