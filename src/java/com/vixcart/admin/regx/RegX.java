/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.regx;

/**
 * @company techvay
 * @author rifaie
 */
public final class RegX {

    /**
     * Regular expression for email
     */
    public static final String REGX_EMAIL = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
    public static final String REGX_MOBILE = "\\d{10}";
    public static final String REGX_OTP = "\\d{6}";
    public static final String REGX_DIGIT = "\\d+";
    public static final String REGX_FLOAT = "[0]*\\.?[0-9]+";
    public static final String REGX_SUPER_TYPE = "\\d+";
    public static final String REGX_ADMIN_UNAME = "[A-Za-z0-9_]+";   
    public static final String REGX_REFERENCE_NO = "[A-Za-z0-9]+";   
    public static final String REGX_STRING = "[A-Z ]+"; // needs to be tested
    public static final String REGX_STRING_UPPER_AND_LOWER = "[A-Za-z]+"; // needs to be tested
    public static final String REGX_ACTIVITY = "[a-z_]+"; // needs to be tested
    public static final String REGX_FILE_NAME = "^[^*&%\\s]+$"; // needs to be tested
    public static final String REGX_NULL_STRING = "[A-Z ]*"; // needs to be tested
    public static final String REGX_FTR = "[A-Za-z:;0-9]*"; // needs to be tested
    public static final String REGX_TYPE = "[A-Za-z ]+"; // needs to be tested
    public static final String REGX_COMPANY = "[A-Za-z0-9_@#+= ]+"; // needs to be tested
    public static final String REGX_TOKEN = "[A-Za-z0-9+/=]+";
    public static final String REGX_B64ENCODE = "^(?:[A-Za-z0-9+/]{4})*(?:[A-Za-z0-9+/]{2}==|[A-Za-z0-9+/]{3}=|[A-Za-z0-9+/]{4})$";
//    public static final String [] REGX_SQLINJ = {"/\\w*((\\%27)|(\\'))((\\%6F)|o|(\\%4F))((\\%72)|r|(\\%52))/ix","/((\\%27)|(\\'))union/ix",
//                                                 "/((\\%27)|(\\'))select/ix","/((\\%27)|(\\'))update/ix","/((\\%27)|(\\'))drop/ix","/((\\%27)|(\\'))insert/ix",
//                                                 "/((\\%27)|(\\'))delete/ix"};
//    public static final String [] REGX_XSS = {"/((\\%3C)|<)((\\%2F)|\\/)*[a-z0-9\\%]+((\\%3E)|>)/ix","/((\\%3C)|<)((\\%69)|i|(\\%49))((\\%6D)|m|(\\%4D))((\\%67)|g|(\\%47))[^\\n]+((\\%3E)|>)/I"};
    
}
