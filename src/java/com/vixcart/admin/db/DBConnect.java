/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.vixcart.admin.db;

import com.vixcart.admin.imgpath.ImagePath;
import com.vixcart.admin.req.mod.AddAffiliate;
import com.vixcart.admin.req.mod.AddAffiliateUser;
import com.vixcart.admin.req.mod.AddBaC;
import com.vixcart.admin.req.mod.AddBrand;
import com.vixcart.admin.req.mod.AddCategory;
import com.vixcart.admin.req.mod.AddPremiumPayment;
import com.vixcart.admin.req.mod.AddProductType;
import com.vixcart.admin.req.mod.AddSpecification;
import com.vixcart.admin.req.mod.AddSubCategory;
import com.vixcart.admin.req.mod.AddSuperCategory;
import com.vixcart.admin.req.mod.AddTaC;
import com.vixcart.admin.req.mod.AddUser;
import com.vixcart.admin.req.mod.AddUserType;
import com.vixcart.admin.req.mod.DeleteBaC;
import com.vixcart.admin.req.mod.DeleteBrand;
import com.vixcart.admin.req.mod.DeleteCategory;
import com.vixcart.admin.req.mod.DeleteSpecification;
import com.vixcart.admin.req.mod.DeleteSubCategory;
import com.vixcart.admin.req.mod.DeleteSuperCategory;
import com.vixcart.admin.req.mod.DeleteTaC;
import com.vixcart.admin.req.mod.EditUser;
import com.vixcart.admin.req.mod.GetAffiliateUsers;
import com.vixcart.admin.req.mod.GetBPaC;
import com.vixcart.admin.req.mod.GetBPaC2;
import com.vixcart.admin.req.mod.GetBrand;
import com.vixcart.admin.req.mod.GetCategories;
import com.vixcart.admin.req.mod.GetCategory;
import com.vixcart.admin.req.mod.GetProductType;
import com.vixcart.admin.req.mod.GetProductTypes;
import com.vixcart.admin.req.mod.GetSpecification;
import com.vixcart.admin.req.mod.GetSubCategory;
import com.vixcart.admin.req.mod.GetSuperCategory;
import com.vixcart.admin.req.mod.GetTaC;
import com.vixcart.admin.req.mod.LoadSpecifications;
import com.vixcart.admin.req.mod.NewPassword;
import com.vixcart.admin.req.mod.SearchBrand;
import com.vixcart.admin.req.mod.SearchProductType;
import com.vixcart.admin.req.mod.UpdateAffiliate;
import com.vixcart.admin.req.mod.UpdateBaC;
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
import com.vixcart.admin.resp.mod.AffiliateUserDetails;
import com.vixcart.admin.resp.mod.AllAffiliates;
import com.vixcart.admin.resp.mod.AllPremiumPayments;
import com.vixcart.admin.resp.mod.BPaC;
import com.vixcart.admin.resp.mod.Brand;
import com.vixcart.admin.resp.mod.Category;
import com.vixcart.admin.resp.mod.PremiumPayments;
import com.vixcart.admin.resp.mod.ProductType;
import com.vixcart.admin.resp.mod.ProductTypes;
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
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @company techvay
 * @author rifaie
 */
public final class DBConnect {

    private Connection con = null;

    private ResultSet rs;

    private final PreparedStatement checkUname, checkNBUname, checkNBAdminID;
//    private final PreparedStatement checkEmail, checkMobile;
//    
//    private final PreparedStatement checkVKey,checkEmailStatus,verifyEmail;
//
//    private final PreparedStatement addEmail, addMobile, addLogin, addSeller, getID, getPID;
//    
//    private final PreparedStatement deleteSeller;
//    
//    private final PreparedStatement checkVerifiedEmail,checkNotVerifiedEmail,getName;
//    
    private PreparedStatement changePassword;
//    
//    private final PreparedStatement checkVerifiedMobile,checkUpdateStatus;
//    
    private final PreparedStatement getPassDSalt;
//    
//    private final PreparedStatement getMobile;
//    
//    private final PreparedStatement updateMVS;
//    
    private final PreparedStatement updateLog;

    private final PreparedStatement getTypeID, getSubtypes;

    private final PreparedStatement blockAdmin;

    private final PreparedStatement getAllUsers;

    private final PreparedStatement getAllTypes;

    private final PreparedStatement getAllSuperTypes;

    private final PreparedStatement checkType, checkSuperType, addType;

    private final PreparedStatement updateUserType, checkTypeWithID;

    private final PreparedStatement deleteUserType;

    private final PreparedStatement checkEmail;

    private final PreparedStatement addToUser, addToLog, addToEmail, removeUser;

    private final PreparedStatement checkSerialNo, getUser;
    private PreparedStatement getSuperCategories, getAllSuperCategories, addSuperCategory, checkSuperCategory, checkSuperCategoryById, deleteSuperCategory;
    private PreparedStatement getVisibiltyStatusById;
    private PreparedStatement getAllCategories, addCategory, checkCategory, checkCategoryById, deleteCategory;
    private PreparedStatement getAllSubCategories, addSubCategory, checkSubCategory, checkSubCategoryById, deleteSubCategory;
    private PreparedStatement checkVisibilityStatusById;
    private PreparedStatement updateSuperCategory;
    private PreparedStatement updateSubCategory;
    private PreparedStatement updateCategory;

    /**
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public DBConnect() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection("jdbc:mysql://worddb.c8s8lmxdo3ux.ap-south-1.rds.amazonaws.com:3306/vaydeal", "worduser", "sooraj123");

        checkUname = con.prepareStatement("SELECT count(*) FROM admin_logger WHERE uname = ?");
        checkNBUname = con.prepareStatement("SELECT count(*) FROM admin_logger_not_blocked WHERE uname = ?");
        checkNBAdminID = con.prepareStatement("SELECT count(*) FROM admin_logger_not_blocked WHERE admin_id = ?");

        getPassDSalt = con.prepareStatement("SELECT salt,password,admin_id,type FROM admin_logger WHERE uname = ?");
        updateLog = con.prepareStatement("UPDATE admin_login SET lastlogged = now(), logcount = logcount+1 WHERE uname = ?");

        getTypeID = con.prepareStatement("Select id FROM admin_user_type WHERE type = ?");
        getSubtypes = con.prepareStatement("SELECT type,id FROM admin_user_type WHERE supertype = ?");

        blockAdmin = con.prepareStatement("UPDATE admin_login SET blockstatus = 2 WHERE admin_id = ?");

        getAllUsers = con.prepareStatement("SELECT slno,name,type,lastlogged,status FROM admin_users ");

        getAllTypes = con.prepareStatement("SELECT id,type,superType FROM admin_types ");

        getAllSuperTypes = con.prepareStatement("SELECT id,type FROM admin_types ");

        checkType = con.prepareStatement("SELECT count(*) FROM admin_types WHERE type = ?");
        checkSuperType = con.prepareStatement("SELECT count(*) FROM admin_types WHERE id = ?");
        addType = con.prepareStatement("INSERT INTO admin_user_type(type,supertype) VALUES (?,?)");

        updateUserType = con.prepareStatement("UPDATE admin_user_type SET supertype = ? WHERE id = ?");
        checkTypeWithID = con.prepareStatement("SELECT count(*) FROM admin_types WHERE type = ? and id = ?");

        deleteUserType = con.prepareStatement("DELETE FROM admin_user_type WHERE id = ?");

        checkEmail = con.prepareStatement("SELECT count(*) FROM admin_users WHERE email = ?");

        addToUser = con.prepareStatement("INSERT INTO admin_user(user_id,name,designation,address1,address2,place,pin,type) VALUES(?,?,?,?,?,?,?,?)");
        addToLog = con.prepareStatement("INSERT INTO admin_login(uname,password,salt,admin_id) VALUES(?,?,?,?)");
        addToEmail = con.prepareStatement("INSERT INTO admin_email(email,admin_id) VALUES(?,?)");
        removeUser = con.prepareStatement("DELETE FROM admin_user WHERE user_id = ?");

        checkSerialNo = con.prepareStatement("SELECT count(*) FROM admin_users WHERE slno = ?");
        getUser = con.prepareStatement("SELECT name,designation,address1,address2,place,pin,type,email,uname,status FROM admin_users WHERE slno = ?");
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
        getAllSuperCategories = con.prepareStatement("SELECT su.super_category_id, su.super_category, su.online_visibility_status, su.offline_visibility_status FROM super_categories su");
        getSuperCategories = con.prepareStatement("SELECT su.super_category_id, su.super_category FROM super_categories su");
        addSuperCategory = con.prepareStatement("INSERT INTO super_category (super_category) values (?)");
        checkSuperCategory = con.prepareStatement("SELECT count(*) FROM super_categories WHERE super_category=?");
        checkSuperCategoryById = con.prepareStatement("SELECT count(*) FROM super_categories WHERE super_category_id=?");
        deleteSuperCategory = con.prepareStatement("UPDATE super_category SET online_visibility_status=?, offline_visibility_status=? WHERE super_category_id=?");
        updateSuperCategory = con.prepareStatement("UPDATE super_category SET online_visibility_status=?, offline_visibility_status=? WHERE super_category_id=?");

        getVisibiltyStatusById = con.prepareStatement("SELECT status FROM visibility_status WHERE status_id=?");
        checkVisibilityStatusById = con.prepareStatement("SELECT count(*) FROM visibility_status WHERE status_id=?");

        getAllCategories = con.prepareStatement("SELECT c.category_id, c.category, su.super_category, c.online_visibility_status, c.offline_visibility_status FROM categories AS c join super_categories AS su ON c.super_category=su.super_category_id");
        addCategory = con.prepareStatement("INSERT INTO category (category, super_category) values (?,?)");
        checkCategory = con.prepareStatement("SELECT count(*) FROM categories WHERE category=?");
        checkCategoryById = con.prepareStatement("SELECT count(*) FROM categories WHERE category_id=?");
        deleteCategory = con.prepareStatement("UPDATE category SET online_visibility_status=?, offline_visibility_status=? WHERE category_id=?");
        updateCategory = con.prepareStatement("UPDATE category SET online_visibility_status=?, offline_visibility_status=? WHERE category_id=?");

        getAllSubCategories = con.prepareStatement("SELECT c.sub_category_id, c.sub_category, su.category, c.online_visibility_status, c.offline_visibility_status FROM sub_categories c join categories su ON c.category=su.category_id");
        addSubCategory = con.prepareStatement("INSERT INTO sub_category (sub_category, category) values (?,?)");
        checkSubCategory = con.prepareStatement("SELECT count(*) FROM sub_categories WHERE sub_category=?");
        checkSubCategoryById = con.prepareStatement("SELECT count(*) FROM sub_categories WHERE sub_category_id=?");
        deleteSubCategory = con.prepareStatement("UPDATE sub_category SET online_visibility_status=?, offline_visibility_status=? WHERE sub_category_id=?");
        updateSubCategory = con.prepareStatement("UPDATE sub_category SET online_visibility_status=?, offline_visibility_status=? WHERE sub_category_id=?");

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
        int count = 0;
        checkUname.setString(1, uname);
        rs = checkUname.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        checkUname.close();
        return count;
    }

    public List<String> getPassDSalt(String uname) throws SQLException {
        List<String> proD = new ArrayList<>();
        getPassDSalt.setString(1, uname);
        rs = getPassDSalt.executeQuery();
        if (rs.next()) {
            proD.add(rs.getString("salt"));
            proD.add(rs.getString("password"));
            proD.add(rs.getString("admin_id"));
            proD.add(rs.getString("type"));
        }
        return proD;
    }

    public boolean updateLog(String uName) throws SQLException {
        updateLog.setString(1, uName);
        int c = updateLog.executeUpdate();
        if (c == 1) {
            return true;
        } else {
            return false;
        }
    }

    public HashSet<String> getSubTypes(String type) throws SQLException {
        int typeID = getTypeID(type);
        HashSet<String> types = new HashSet<>();
        types.add(type);
        if (typeID != 0) {
            getSubtypes.setInt(1, typeID);
            rs = getSubtypes.executeQuery();
            while (rs.next()) {
                types.add(rs.getString(1));
            }
        }
        rs.close();
        return types;
    }

    private int getTypeID(String type) throws SQLException {
        getTypeID.setString(1, type);
        rs = getTypeID.executeQuery();
        int id = 0;
        if (rs.next()) {
            id = rs.getInt(1);
        }
        rs.close();
        getTypeID.close();
        return id;
    }

    public boolean blockAdmin(String admin_id) throws SQLException {
        blockAdmin.setString(1, admin_id);
        int count = blockAdmin.executeUpdate();
        if (count == 1) {
            return true;
        }
        blockAdmin.close();
        return false;
    }

    public int checkNBUname(String uname) throws SQLException {
        checkNBUname.setString(1, uname);
        rs = checkNBUname.executeQuery();
        int c = 0;
        if (rs.next()) {
            c = rs.getInt(1);
        }
        rs.close();
        checkNBUname.close();
        return c;
    }

    public boolean checkNBAdminID(String admin_id) throws SQLException {
        checkNBAdminID.setString(1, admin_id);
        rs = checkNBAdminID.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) == 1) {
                return true;
            }
        }
        return false;
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
        ArrayList<UserType> allType = new ArrayList<>();
        rs = getAllTypes.executeQuery();
        while (rs.next()) {
            UserType t = new UserType(rs.getString("id"), rs.getString("type"), rs.getString("superType"));
            allType.add(t);
        }
        rs.close();
        getAllTypes.close();
        return allType;
    }

    public ArrayList<UserType1> getAllUserTypes() throws SQLException {
        ArrayList<UserType1> allType = new ArrayList<>();
        rs = getAllTypes.executeQuery();
        while (rs.next()) {
            UserType1 t = new UserType1(rs.getString("type"));
            allType.add(t);
        }
        rs.close();
        getAllTypes.close();
        return allType;
    }

    public ArrayList<SuperUserType> getAllSuperTypes() throws SQLException {
        ArrayList<SuperUserType> allType = new ArrayList<>();
        rs = getAllSuperTypes.executeQuery();
        while (rs.next()) {
            SuperUserType t = new SuperUserType(rs.getString("id"), rs.getString("type"));
            allType.add(t);
        }
        rs.close();
        getAllSuperTypes.close();
        return allType;
    }

    public int checkType(String type) throws SQLException {
        int count = 0;
        checkType.setString(1, type);
        rs = checkType.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        checkType.close();
        return count;
    }

    public int checkSuperType(String superType) throws SQLException {
        int count = 0;
        checkSuperType.setString(1, superType);
        rs = checkSuperType.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        return count;
    }

    public boolean addType(AddUserType addTyp) throws SQLException {
        addType.setString(1, addTyp.getType());
        addType.setString(2, addTyp.getSuperType());
        int c = addType.executeUpdate();
        addType.close();
        return c == 1;
    }

    public boolean updateUserType(UpdateUserType updateTyp) throws SQLException {
        updateUserType.setString(1, updateTyp.getSuperType());
        updateUserType.setString(2, updateTyp.getTypeid());
        int c = updateUserType.executeUpdate();
        updateUserType.close();
        return c == 1;
    }

    public boolean checkSubtypeSuperType(String superType, String typeid) throws SQLException {
        getSubtypes.setString(1, typeid);
        rs = getSubtypes.executeQuery();
        HashSet<String> hs = new HashSet<>();
        while (rs.next()) {
            hs.add(rs.getString(2));
        }
        hs.remove(typeid);
        rs.close();
        if (hs.contains(superType)) {
            return false;
        }
        return true;
    }

    public int checkTypeWithID(String type, String typeid) throws SQLException {
        int count = 0;
        checkTypeWithID.setString(1, type);
        checkTypeWithID.setString(2, typeid);
        rs = checkTypeWithID.executeQuery();
        if (rs.next()) {
            count = rs.getInt(1);
        }
        rs.close();
        checkTypeWithID.close();
        return count;
    }

    public boolean deleteUserType(String typeID) throws SQLException {
        deleteUserType.setString(1, typeID);
        int c = deleteUserType.executeUpdate();
        deleteUserType.close();
        return c == 1;
    }

    public int checkEmail(String email) throws SQLException {
        checkEmail.setString(1, email);
        rs = checkEmail.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public void addUser(AddUser au) throws SQLException {
        addToUser(au);
        addToLog(au);
        addToEmail(au);
    }

    private void addToUser(AddUser au) throws SQLException {
        addToUser.setString(1, au.getNew_admin_id());
        addToUser.setString(2, au.getName());
        addToUser.setString(3, au.getDesignation());
        addToUser.setString(4, au.getAddress1());
        addToUser.setString(5, au.getAddress2());
        addToUser.setString(6, au.getPlace());
        addToUser.setString(7, au.getPin());
        addToUser.setString(8, au.getUtype());
        addToUser.executeUpdate();
    }

    private void addToLog(AddUser au) throws SQLException {
        addToLog.setString(1, au.getUname());
        addToLog.setString(2, au.getPassword());
        addToLog.setString(3, au.getSalt());
        addToLog.setString(4, au.getNew_admin_id());
        addToLog.executeUpdate();
    }

    private void addToEmail(AddUser au) throws SQLException {
        addToEmail.setString(1, au.getEmail());
        addToEmail.setString(2, au.getNew_admin_id());
        addToEmail.executeUpdate();
    }

    public void removeUser(String new_admin_id) throws SQLException {
        removeUser.setString(1, new_admin_id);
        removeUser.executeUpdate();
    }

    public int checkSerialNumber(String slno) throws SQLException {
        checkSerialNo.setString(1, slno);
        rs = checkSerialNo.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        checkSerialNo.close();
        rs.close();
        return count;
    }

    public UserDetails getUserDetails(String slno) throws SQLException {
        getUser.setString(1, slno);
        rs = getUser.executeQuery();
        UserDetails ud;
        if (rs.next()) {
            ud = new UserDetails(rs.getString("name"), rs.getString("designation"), rs.getString("address1"), rs.getString("address2"), rs.getString("place"), rs.getString("pin"), rs.getString("type"), rs.getString("email"), rs.getString("uname"), rs.getString("status"));
        } else {
            ud = new UserDetails("invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid", "invalid");
        }
        return ud;
    }

    public int changePassword(NewPassword np) throws SQLException {
        changePassword = con.prepareStatement("UPDATE admin_login SET password = ? ,salt = ? WHERE admin_id = ?");
        changePassword.setString(1, np.getNewPassword());
        changePassword.setString(2, np.getSalt());
        changePassword.setString(3, np.getAdminId());
        return changePassword.executeUpdate();
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
        return rs.getString(1);
    }

    public int getNoOfWebsites() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliates");
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int getNoOfMembers() throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM members");
        rs = ps.executeQuery();
        rs.next();
        return rs.getInt(1);
    }

    public int getMaxPageNo(String view, int max) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM " + view);
        rs = ps.executeQuery();
        rs.next();
        double noOfAffiliates = rs.getInt(1);
        if (noOfAffiliates > 0) {
            return (int) Math.ceil(noOfAffiliates / max);
        }
        return 0;
    }

    public int getMaxPageNo(String view, int max, String whereCondition) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM " + view + " WHERE " + whereCondition);
        rs = ps.executeQuery();
        rs.next();
        double noOfAffiliates = rs.getInt(1);
        if (noOfAffiliates > 0) {
            return (int) Math.ceil(noOfAffiliates / max);
        }
        return 0;
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
        if (affl == 1) {
            return true;
        }
        return false;
    }

    public boolean blockAffiliate(String affiliate) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate SET status = 2 WHERE `company` = ?");
        ps.setString(1, affiliate);
        int affl = ps.executeUpdate();
        if (affl == 1) {
            return true;
        }
        return false;
    }

    public UserIds getUserIds(String str) throws SQLException {
        UserIds uids = new UserIds();
        PreparedStatement ps = con.prepareStatement("SELECT * FROM admin_users WHERE user_id LIKE '" + str + "%' LIMIT 10");
        rs = ps.executeQuery();
        while (rs.next()) {
            uids.addUserID(rs.getString(1));
        }
        return uids;
    }

    public boolean checkActivity(String activity) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM admin_activities WHERE activity = ?");
        ps.setString(1, activity);
        rs = ps.executeQuery();
        if (rs.next()) {
            if (rs.getInt(1) == 1) {
                return true;
            }
        }
        return false;
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
            ad = new AffiliateDetails("invalid", "invalid", "invalid", "invalid");
        }
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
        switch (req.getQueryType()) {
            case "company":
                ps = con.prepareStatement("SELECT affiliate,affiliate_user_name,affiliate_user_id,status,last_logged FROM vaydeal.affiliate_logger WHERE affiliate = ? LIMIT ?,?");
                int me = Integer.parseInt(req.getMaxEntries());
                int pn = Integer.parseInt(req.getPageNo());
                int start = (pn - 1) * me;
                ps.setString(1, req.getQuery());
                ps.setInt(2, start);
                ps.setInt(3, pn);
                rs = ps.executeQuery();
                while (rs.next()) {
                    AffiliateUserDetails auds = new AffiliateUserDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4));
                    aud.add(auds);
                }
                break;
            case "user":
                ps = con.prepareStatement("SELECT affiliate,affiliate_user_name,affiliate_user_id,status,last_logged FROM vaydeal.affiliate_logger WHERE affiliate_user_id=?");
                ps.setString(1, req.getQuery());
                rs = ps.executeQuery();
                if (rs.next()) {
                    AffiliateUserDetails auds = new AffiliateUserDetails(rs.getString(1), rs.getString(2), rs.getString(3), rs.getString(5), rs.getString(4));
                    aud.add(auds);
                }
                break;
        }
    }

    public boolean validateAuid(String auid) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT count(*) FROM affiliate_logger_not_blocked WHERE affiliate_user_id = ?");
        ps.setString(1, auid);
        rs = ps.executeQuery();
        rs.next();
        int affl = rs.getInt(1);
        if (affl == 1) {
            return true;
        }
        return false;
    }

    public boolean blockAffiliateUsers(String auid) throws SQLException {
        PreparedStatement ps = con.prepareStatement("UPDATE affiliate_user SET affiliate_user_status = 2 WHERE affiliate_user_id = ?");
        ps.setString(1, auid);
        int affl = ps.executeUpdate();
        if (affl == 1) {
            return true;
        }
        return false;
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
        PreparedStatement ps = con.prepareStatement("SELECT affiliate_user_email FROM affiliate_logger_not_blocked WHERE affiliate_user_email = ?");
        ps.setString(1, email);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
        return count;
    }

    public int checkAffiliateUserMobile(String mobile) throws SQLException {
        PreparedStatement ps = con.prepareStatement("SELECT affiliate_user_mobile FROM affiliate_logger_not_blocked WHERE affiliate_user_mobile = ?");
        ps.setString(1, mobile);
        rs = ps.executeQuery();
        int count = 0;
        if (rs.next()) {
            count = rs.getInt(1);
        }
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
        Random random = new Random();
        new_admin_id = "" + (random.nextInt(9999999) + 45573456);
        while (!al.contains(new_admin_id)) {
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

    public String getVisibilityStatusById(String status_id) {
        String result = "";
        try {
            getVisibiltyStatusById.setString(1, status_id);
            ResultSet res = getVisibiltyStatusById.executeQuery();
            while (res.next()) {
                result = res.getString(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DBConnect.class.getName()).log(Level.SEVERE, null, ex);
        }
        return result;
    }

    public ArrayList<SuperCategory> getAllSuperCategories() throws SQLException {
        ArrayList<SuperCategory> result = new ArrayList<>();
        ResultSet res = getAllSuperCategories.executeQuery();
        while (res.next()) {
            SuperCategory sc = new SuperCategory(res.getString(1), res.getString(2), getVisibilityStatusById(res.getString(3)), getVisibilityStatusById(res.getString(4)));
            result.add(sc);
        }
        return result;
    }

    public boolean addSuperCategory(AddSuperCategory req) throws SQLException {
        boolean res = false;
        addSuperCategory.setString(1, req.getSuper_category());
        int exe = addSuperCategory.executeUpdate();
        return exe == 1;
    }

    public ArrayList<Category> getAllCategories() throws SQLException {
        ArrayList<Category> categs = new ArrayList<>();
        ResultSet res = getAllCategories.executeQuery();
        while (res.next()) {
            Category category = new Category(res.getString(1), res.getString(2), res.getString(3), getVisibilityStatusById(res.getString(4)), getVisibilityStatusById(res.getString(5)));
            categs.add(category);
        }
        return categs;
    }

    public ArrayList<SubCategory> getAllSubCategories() throws SQLException {
        ArrayList<SubCategory> sub = new ArrayList<>();
        ResultSet res = getAllSubCategories.executeQuery();
        while (res.next()) {
            SubCategory category = new SubCategory(res.getString(1), res.getString(2), res.getString(3), getVisibilityStatusById(res.getString(4)), getVisibilityStatusById(res.getString(5)));
            sub.add(category);
        }
        return sub;
    }

    public int checkSuperCategory(String name) throws SQLException {
        int res = 0;
        checkSuperCategory.setString(1, name);
        ResultSet r = checkSuperCategory.executeQuery();
        if (r.next()) {
            res = r.getInt(1);
        }
        return res;
    }

    public int checkCategory(String name) throws SQLException {
        int res = 0;
        checkCategory.setString(1, name);
        ResultSet r = checkCategory.executeQuery();
        if (r.next()) {
            res = r.getInt(1);
        }
        return res;
    }

    public boolean addCategory(AddCategory req) throws SQLException {
        boolean res = false;
        addCategory.setString(1, req.getCategory());
        addCategory.setString(2, req.getSuperCategory());
        int exe = addCategory.executeUpdate();
        return exe == 1;
    }

    public int checkSuperCategoryById(String id) throws SQLException {
        int res = 0;
        checkSuperCategoryById.setString(1, id);
        ResultSet r = checkSuperCategoryById.executeQuery();
        if (r.next()) {
            res = r.getInt(1);
        }
        return res;
    }

    public int checkCategoryById(String id) throws SQLException {
        int res = 0;
        checkCategoryById.setString(1, id);
        System.out.println("checkCategoryById = " + checkCategoryById);
        ResultSet r = checkCategoryById.executeQuery();
        if (r.next()) {
            res = r.getInt(1);
        }
        return res;
    }

    public int checkSubCategory(String category) throws SQLException {
        int res = 0;
        checkSubCategory.setString(1, category);
        ResultSet r = checkSubCategory.executeQuery();
        if (r.next()) {
            res = r.getInt(1);
        }
        return res;
    }

    public boolean addSubCategory(AddSubCategory req) throws SQLException {
        boolean res = false;
        addSubCategory.setString(1, req.getSubCategory());
        addSubCategory.setString(2, req.getCategory());
        int exe = addSubCategory.executeUpdate();
        return exe == 1;
    }

    public boolean deleteSuperCategory(DeleteSuperCategory req) throws SQLException {
        boolean res = false;
        deleteSuperCategory.setString(1, "2");
        deleteSuperCategory.setString(2, "2");
        deleteSuperCategory.setString(3, req.getSuper_category());
        int exe = deleteSuperCategory.executeUpdate();
        return exe == 1;
    }

    public boolean deleteCategory(DeleteCategory req) throws SQLException {
        boolean res = false;
        deleteCategory.setString(1, "2");
        deleteCategory.setString(2, "2");
        deleteCategory.setString(3, req.getCategory());
        int exe = deleteCategory.executeUpdate();
        return exe == 1;
    }

    public boolean deleteSubCategory(DeleteSubCategory req) throws SQLException {
        boolean res = false;
        deleteSubCategory.setString(1, "2");
        deleteSubCategory.setString(2, "2");
        deleteSubCategory.setString(3, req.getSub_category());
        int exe = deleteSubCategory.executeUpdate();
        return exe == 1;
    }

    public int checkSubCategoryById(String name) throws SQLException {
        int res = 0;
        checkSubCategoryById.setString(1, name);
        res = checkSubCategoryById.executeUpdate();
        return res;
    }

    public int checkVisibilityStatusById(String name) throws SQLException {
        int res = 0;
        checkVisibilityStatusById.setString(1, name);
        res = checkVisibilityStatusById.executeUpdate();
        return res;
    }

    public boolean updateSuperCategory(UpdateSuperCategory req) throws SQLException {
        boolean res = false;
        updateSuperCategory.setString(1, req.getOn_status());
        updateSuperCategory.setString(2, req.getOff_status());
        updateSuperCategory.setString(3, req.getSuper_category());
        int exe = updateSuperCategory.executeUpdate();
        return exe == 1;
    }

    public boolean updateSubCategory(UpdateSubCategory req) throws SQLException {
        boolean res = false;
        updateSubCategory.setString(1, req.getOn_status());
        updateSubCategory.setString(2, req.getOff_status());
        updateSubCategory.setString(3, req.getSubcat());
        int exe = updateSubCategory.executeUpdate();
        return res;
    }

    public boolean updateCategory(UpdateCategory req) throws SQLException {
        boolean res = false;
        updateCategory.setString(1, req.getOn_status());
        updateCategory.setString(2, req.getOff_status());
        updateCategory.setString(3, req.getCateg());
        int exe = updateCategory.executeUpdate();
        return res;
    }

    public boolean addSpecification(AddSpecification req) throws SQLException {
        boolean res = false;
        int c = 0;
        List<String> specific = req.getSpecific();
        for (String spec : specific) {
            PreparedStatement stmt = con.prepareStatement("INSERT INTO product_type_and_specification (product_type, specification) VALUES (?,?)");
            stmt.setString(1, req.getpType());
            stmt.setString(2, spec);
            c += stmt.executeUpdate();
        }
        return specific.size() == c;
    }

    public boolean addProductType(AddProductType req) throws SQLException {
        boolean res = false;
        PreparedStatement stmt = con.prepareStatement("INSERT INTO product_type (product_type) values (?)");
        stmt.setString(1, req.getpType());
        int c = stmt.executeUpdate();
        return c == 1;
    }

    public boolean deleteTaC(DeleteTaC req) throws SQLException {
        boolean res = false;
        PreparedStatement stmt = con.prepareStatement("UPDATE product_type_and_category SET online_visibility_status=? and offline_visibility_status=? WHERE id=?");
        stmt.setString(1, "2");
        stmt.setString(2, "2");
        stmt.setString(3, req.getTaC());
        int c = stmt.executeUpdate();
        return c == 1;
    }

    public boolean deleteSpecification(DeleteSpecification req) {
        boolean res = false;
        return res;
    }

    public boolean addTaC(AddTaC req) throws SQLException {
        boolean res = false;
        PreparedStatement stmt = con.prepareStatement("INSERT INTO product_type_and_category (product_type, category) values (?,?)");
        stmt.setString(1, req.getpType());
        stmt.setString(2, req.getCategory());
        int c = stmt.executeUpdate();
        return c == 1;
    }

    public boolean updateTaC(UpdateTaC req) {
        boolean res = false;
        return res;
    }

    public boolean updateSpecification(UpdateSpecification req) {
        boolean res = false;
        return res;
    }

    public boolean updateProductType(UpdateProductType req) {
        boolean res = false;
        return res;
    }

    public int checkPType(String ptype) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM product_type WHERE product_type=?");
        stmt.setString(1, ptype);
        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }

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

    public int checkTaCById(String tacId) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM product_type_and_category WHERE id=?");
        stmt.setString(1, tacId);

        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }

    public int checkBrand(String brand) {
        int res = 0;
        return res;
    }

    public boolean updateBrand(UpdateBrand req) {
        boolean res = false;
        return res;
    }

    public boolean updateBaC(UpdateBaC req) {
        boolean res = false;
        return res;
    }

    public boolean deleteBrand(DeleteBrand req) {
        boolean res = false;
        return res;
    }

    public boolean deleteBaC(DeleteBaC req) {
        boolean res = false;
        return res;
    }

    public boolean addBrand(AddBrand req) {
        boolean res = false;
        return res;
    }

    public boolean addBaC(AddBaC req) {
        boolean res = false;
        return res;
    }

    public int checkTacId(String tacid) {
        int res = 0;
        return res;
    }

    public int checkPTypeId(String ptypeid) {
        int res = 0;
        return res;
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

    public Category getCategories(GetCategories req) {
        Category res = null;
        return res;
    }

    public Category getCategory(GetCategory req) {
        Category res = null;
        return res;
    }

    public ArrayList<ProductType> getAllProductTypes(String no, String offset) throws SQLException {
        ArrayList<ProductType> res = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM brands LIMIT(?,?)");
        int n = Integer.parseInt(no);
        stmt.setInt(2, n);
        stmt.setInt(1, n * Integer.parseInt(offset));
        ResultSet exe = stmt.executeQuery();
        while (exe.next()) {
            ProductType pt = new ProductType(exe.getString(1), exe.getString(2), exe.getString(3), exe.getString(4));
            res.add(pt);
        }
        return res;
    }

    public ArrayList<Brand> getAllBrands(String no, String offset) throws SQLException {
        ArrayList<Brand> res = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM brands LIMIT(?,?)");
        int n = Integer.parseInt(no);
        stmt.setInt(2, n);
        stmt.setInt(1, n * Integer.parseInt(offset));
        ResultSet exe = stmt.executeQuery();
        while (exe.next()) {
            Brand brand = new Brand(exe.getString(1), exe.getString(2), exe.getString(3), exe.getString(4));
            res.add(brand);
        }
        return res;
    }

    public ArrayList<TaC> getAllTaC(String no, String offset) throws SQLException {
        ArrayList<TaC> res = new ArrayList<>();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM tac_mapping LIMIT(?,?)");
        int n = Integer.parseInt(no);
        stmt.setInt(2, n);
        stmt.setInt(1, n * Integer.parseInt(offset));
        ResultSet exe = stmt.executeQuery();
        while (exe.next()) {
            TaC tac = new TaC(exe.getString(1), exe.getString(2), exe.getString(3), exe.getString(4), exe.getString(5));
            res.add(tac);
        }
        return res;
    }

    public BPaC getBPaC(GetBPaC req) {
        BPaC res = null;
        return res;
    }

    public ArrayList<ProductType> searchProductType(SearchProductType req) {
        ArrayList<ProductType> res = null;
        return res;
    }

    public ArrayList<Brand> searchBrand(SearchBrand req) {
        ArrayList<Brand> res = null;
        return res;
    }

    public ArrayList<Specifications> loadSpecifications(LoadSpecifications req) {
        ArrayList<Specifications> res = null;
        return res;
    }

    public TaC getTaC(GetTaC req) {
        TaC res = null;
        return res;
    }

    public SuperCategory getSuperCategory(GetSuperCategory req) {
        SuperCategory res = null;
        return res;
    }

    public SubCategory getSubCategory(GetSubCategory req) {
        SubCategory res = null;
        return res;
    }

    public Specifications getSpecification(GetSpecification req) {
        Specifications res = null;
        return res;
    }

    public ProductTypes getProductTypes(GetProductTypes req) {
        ProductTypes res = null;
        return res;
    }

    public ProductType getProductType(GetProductType req) {
        ProductType res = null;
        return res;
    }

    public Brand getBrand(GetBrand req) {
        Brand res = null;
        return res;
    }

    public BPaC getBPaC2(GetBPaC2 req) {
        BPaC res = null;
        return res;
    }

    public int checkPSpecId(String id) throws SQLException {
        int res = 0;
        PreparedStatement stmt = con.prepareStatement("SELECT COUNT(*) FROM product_type_and_specification WHERE id=?");
        stmt.setString(1, id);

        ResultSet exe = stmt.executeQuery();
        if (exe.next()) {
            res = exe.getInt(1);
        }
        return res;
    }
}
