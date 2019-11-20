package com.weix.listview.tabale_recyclerview;

import java.util.List;

/**
 * Created by Administrator on 2019/11/15.
 */

public class TensionProcessData {


    /**
     * total : 110
     * rows : [{"zlcs":"1","dxc2":"0","zll2":"8.4","dxc1":"0","yy2":"0.3","yy1":"0.3","jlsj":"2019-06-30 10:28:37","zt2":"","zt1":"","ssid":"6e27eaf9-6394-4e17-ba32-e6c3ce2690d9","holeid":"390088","zll1":"9.7","scl2":"0.3","scl1":"1.6"}]
     */

    private int total;
    private List<RowsEntity> rows;

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<RowsEntity> getRows() {
        return rows;
    }

    public void setRows(List<RowsEntity> rows) {
        this.rows = rows;
    }

    public static class RowsEntity {
        /**
         * zlcs : 1
         * dxc2 : 0
         * zll2 : 8.4
         * dxc1 : 0
         * yy2 : 0.3
         * yy1 : 0.3
         * jlsj : 2019-06-30 10:28:37
         * zt2 :
         * zt1 :
         * ssid : 6e27eaf9-6394-4e17-ba32-e6c3ce2690d9
         * holeid : 390088
         * zll1 : 9.7
         * scl2 : 0.3
         * scl1 : 1.6
         */

        private String zlcs;
        private String dxc2;
        private String zll2;
        private String dxc1;
        private String yy2;
        private String yy1;
        private String jlsj;
        private String zt2;
        private String zt1;
        private String ssid;
        private String holeid;
        private String zll1;
        private String scl2;
        private String scl1;

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
