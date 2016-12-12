package org.uncertweb.aquacrop.data;

import java.io.Serializable;

/**
 * Created by driesj on 12/12/2016.
 */
public class DailyOutput implements Serializable {

    //Day Month  Year   DAP Stage   WC(1.20)   Rain     Irri   Surf   Infilt   RO    Drain       CR    Zgwt       Ex
    // 12     5  2007     1    1      322.1     2.8      0.0    0.0    2.7    0.0      0.0      0.0   -9.90      4.2

    //  E    E/Ex     Trx       Tr  Tr/Trx    ETx      ET   ET/ETx
    // 4.1     98      0.0      0.0   100      4.2     4.1     98
    short day;
    short month;
    int year;
    int DAP;
    int stage;
    double wc;
    double rain;
    double irrigation;
    double surf;
    double infilt;
    double RO;
    double drain;
    double cr;
    double zgwt;
    double ex;
    double e;
    double e_div_ex;
    double trx;
    double tr;
    double tr_div_trx;
    double etx;
    double et;
    double et_div_etx;

    public DailyOutput(short day, short month, int year, int DAP, int stage, double wc, double rain, double irrigation, double surf, double infilt, double RO, double drain, double cr, double zgwt, double ex, double e, double e_div_ex, double trx, double tr, double tr_div_trx, double etx, double et, double et_div_etx) {
        this.day = day;
        this.month = month;
        this.year = year;
        this.DAP = DAP;
        this.stage = stage;
        this.wc = wc;
        this.rain = rain;
        this.irrigation = irrigation;
        this.surf = surf;
        this.infilt = infilt;
        this.RO = RO;
        this.drain = drain;
        this.cr = cr;
        this.zgwt = zgwt;
        this.ex = ex;
        this.e = e;
        this.e_div_ex = e_div_ex;
        this.trx = trx;
        this.tr = tr;
        this.tr_div_trx = tr_div_trx;
        this.etx = etx;
        this.et = et;
        this.et_div_etx = et_div_etx;
    }

    public short getDay() {
        return day;
    }

    public short getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public int getDAP() {
        return DAP;
    }

    public int getStage() {
        return stage;
    }

    public double getWc() {
        return wc;
    }

    public double getRain() {
        return rain;
    }

    public double getIrrigation() {
        return irrigation;
    }

    public double getSurf() {
        return surf;
    }

    public double getInfilt() {
        return infilt;
    }

    public double getRO() {
        return RO;
    }

    public double getDrain() {
        return drain;
    }

    public double getCr() {
        return cr;
    }

    public double getZgwt() {
        return zgwt;
    }

    public double getEx() {
        return ex;
    }

    public double getE() {
        return e;
    }

    public double getE_div_ex() {
        return e_div_ex;
    }

    public double getTrx() {
        return trx;
    }

    public double getTr() {
        return tr;
    }

    public double getTr_div_trx() {
        return tr_div_trx;
    }

    public double getEtx() {
        return etx;
    }

    public double getEt() {
        return et;
    }

    public double getEt_div_etx() {
        return et_div_etx;
    }
}
