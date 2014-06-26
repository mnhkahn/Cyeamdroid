package com.cyeam.cyeamdroid.model;

public class Bing {

    public final static String BING_BASE = "http://s.cn.bing.net";

    public static final String IMAGE = "image";

    public static final String STARTDATE = "startdate";

    public static final String FULLSTARTDATE = "fullstartdate";

    public static final String ENDDATE = "enddate";

    public static final String URL = "url";

    public static final String URLBASE = "urlBase";

    public static final String COPYRIGHT = "copyright";

    private String startDate;

    private String fullstartdate;

    private String enddate;

    private String url;

    private String urlBase;

    private String copyright;

    public static String getBingBase() {
        return BING_BASE;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getFullstartdate() {
        return fullstartdate;
    }

    public void setFullstartdate(String fullstartdate) {
        this.fullstartdate = fullstartdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrlBase() {
        return urlBase;
    }

    public void setUrlBase(String urlBase) {
        this.urlBase = urlBase;
    }

    public String getCopyright() {
        return copyright;
    }

    public void setCopyright(String copyright) {
        this.copyright = copyright;
    }


}
