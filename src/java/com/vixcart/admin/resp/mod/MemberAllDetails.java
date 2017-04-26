/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.vixcart.admin.resp.mod;

/**
 * @company techvay
 * @author rifaie
 */
public class MemberAllDetails {
    //Personal 15
    private final String mid;
    private final String mname;
    private final String madd1;
    private final String madd2;
    private final String mplace;
    private final String mpin;
    private final String mcity;
    private final String memail;
    private final String mmobile;
    private final String mdate;
    private final String mtype;
    private final String mstatus;
    private final String mupstatus;
    private final String mll;
    private final String mlc;
    //activities 10
    private final String pmu;
    private final String pp;
    private final String pu;
    private final String pa;
    private final String pr;
    private final String pdup;
    private final String pup;
    private final String pupa;
    private final String pupp;
    private final String pupr;
    //payment 4
    private final String ap;
    private final String ar;
    private final String lr;
    private final String lrd;
    //bank 5
    private final String mpan;
    private final String ban;
    private final String bi;
    private final String bn;
    private final String bb;

    public MemberAllDetails() {
        mid = "invalid";
        mname = "invalid";
        madd1 = "invalid";
        madd2 = "invalid";
        mcity = "invalid";
        mpan = "invalid";
        mpin = "invalid";
        mplace = "invalid";
        mupstatus = "invalid";
        mstatus = "invalid";
        memail = "invalid";
        mmobile = "invalid";
        mtype = "invalid";
        mll = "invalid";
        mlc = "invalid";
        pa = "invalid";
        pdup = "invalid";
        pmu = "invalid";
        pp = "invalid";
        pr = "invalid";
        pu = "invalid";
        pup = "invalid";
        pupa = "invalid";
        pupp = "invalid";
        pupr = "invalid";
        ap = "invalid";
        ar = "invalid";
        lr = "invalid";
        lrd = "invalid";
        ban = "invalid";
        bb = "invalid";
        bi = "invalid";
        bn = "invalid";
        mdate = "invalid";
    }

    public MemberAllDetails(String mid, String mname, String madd1, String madd2, String mplace, String mpin, String mcity, String memail, String mmobile, String mdate, String mtype, String mstatus, String mupstatus, String mll, String mlc, String pmu, String pp, String pu, String pa, String pr, String pdup, String pup, String pupa, String pupp, String pupr, String ap, String ar, String lr, String lrd, String mpan, String ban, String bi, String bn, String bb) {
        this.mid = mid;
        this.mname = mname;
        this.madd1 = madd1;
        this.madd2 = madd2;
        this.mplace = mplace;
        this.mpin = mpin;
        this.mcity = mcity;
        this.memail = memail;
        this.mmobile = mmobile;
        this.mdate = mdate;
        this.mtype = mtype;
        this.mstatus = mstatus;
        this.mupstatus = mupstatus;
        this.mll = mll;
        this.mlc = mlc;
        this.pmu = pmu;
        this.pp = pp;
        this.pu = pu;
        this.pa = pa;
        this.pr = pr;
        this.pdup = pdup;
        this.pup = pup;
        this.pupa = pupa;
        this.pupp = pupp;
        this.pupr = pupr;
        this.ap = ap;
        this.ar = ar;
        this.lr = lr;
        this.lrd = lrd;
        this.mpan = mpan;
        this.ban = ban;
        this.bi = bi;
        this.bn = bn;
        this.bb = bb;
    }

    public String getMid() {
        return mid;
    }

    @Override
    public String toString() {
        String memberDetails = "\"personal\":{" + "\"mid\":\"" + mid + "\",\" mname\":\"" + mname + "\",\" madd1\":\"" + madd1 + "\",\" madd2\":\"" + madd2 + "\",\" mplace\":\"" + mplace + "\",\" mpin\":\"" + mpin + "\",\" mcity\":\"" + mcity + "\",\" memail\":\"" + memail + "\",\" mmobile\":\"" + mmobile + "\",\" mdate\":\"" + mdate + "\",\" mtype\":\"" + mtype + "\",\" mstatus\":\"" + mstatus + "\",\" mupstatus\":\"" + mupstatus + "\",\" mll\":\"" + mll + "\",\" mlc\":\"" + mlc + "\"},";
        memberDetails += "\"activities\":{\"pmu\":\"" + pmu + "\", \"pp\":\"" + pp + "\", \"pu\":\"" + pu + "\", \"pa\":\"" + pa + "\", \"pr\":\"" + pr + "\", \"pdup\":\"" + pdup + "\", \"pup\":\"" + pup + "\", \"pupa\":\"" + pupa + "\", \"pupp\":\"" + pupp + "\", \"pupr\":\"" + pupr + "\"},";
        memberDetails += "\"payment\":{\"ap\":\"" + ap + "\", \"ar\":\"" + ar + "\", \"lr\":\"" + lr + "\", \"lrd\":\"" + lrd + "\"},"+"\"bank\":{\"mpan\", \"" + mpan + "\", \"ban\", \"" + ban + "\", \"bi\", \"" + bi + "\", \"bn\", \"" + bn + "\", \"bb\", \"" + bb + "\"}";
        return memberDetails;
    }    
    
}
