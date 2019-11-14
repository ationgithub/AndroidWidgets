package com.weix.charts_xcl;

import java.util.List;

public class ChartData {
    int total;
    List<DateXY> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<DateXY> getRows() {
        return rows;
    }

    public void setRows(List<DateXY> rows) {
        this.rows = rows;
    }

    public class DateXY {
        //            "zlcs":"1",
//                    "dxc2":"0",
//                    "zll2":"5.9",
//                    "dxc1":"0",
//                    "yy2":"0.2",
//                    "yy1":"0.3",
//                    "jlsj":"2019-06-30 10:14:57",
//                    "zt2":"",
//                    "zt1":"",
//                    "ssid":"7c4e4055-a3bc-43ce-8ea6-83f78faf4ffa",
//                    "holeid":"390085",
//                    "zll1":"8.4",
//                    "scl2":"2.3",
//                    "scl1":"4.5"
        String zlcs;
        String dxc2;
        String zll2;
        String dxc1;
        String yy2;
        String yy1;
        String jlsj;
        String zt2;
        String zt1;
        String ssid;
        String holeid;
        String zll1;
        String scl2;
        String scl1;

        public String getZlcs() {
            return zlcs;
        }

        public void setZlcs(String zlcs) {
            this.zlcs = zlcs;
        }

        public String getDxc2() {
            return dxc2;
        }

        public void setDxc2(String dxc2) {
            this.dxc2 = dxc2;
        }

        public String getZll2() {
            return zll2;
        }

        public void setZll2(String zll2) {
            this.zll2 = zll2;
        }

        public String getDxc1() {
            return dxc1;
        }

        public void setDxc1(String dxc1) {
            this.dxc1 = dxc1;
        }

        public String getYy2() {
            return yy2;
        }

        public void setYy2(String yy2) {
            this.yy2 = yy2;
        }

        public String getYy1() {
            return yy1;
        }

        public void setYy1(String yy1) {
            this.yy1 = yy1;
        }

        public String getJlsj() {
            return jlsj;
        }

        public void setJlsj(String jlsj) {
            this.jlsj = jlsj;
        }

        public String getZt2() {
            return zt2;
        }

        public void setZt2(String zt2) {
            this.zt2 = zt2;
        }

        public String getZt1() {
            return zt1;
        }

        public void setZt1(String zt1) {
            this.zt1 = zt1;
        }

        public String getSsid() {
            return ssid;
        }

        public void setSsid(String ssid) {
            this.ssid = ssid;
        }

        public String getHoleid() {
            return holeid;
        }

        public void setHoleid(String holeid) {
            this.holeid = holeid;
        }

        public String getZll1() {
            return zll1;
        }

        public void setZll1(String zll1) {
            this.zll1 = zll1;
        }

        public String getScl2() {
            return scl2;
        }

        public void setScl2(String scl2) {
            this.scl2 = scl2;
        }

        public String getScl1() {
            return scl1;
        }

        public void setScl1(String scl1) {
            this.scl1 = scl1;
        }
    }
}
