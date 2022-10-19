package com.pesonal.adsdk;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Pro_IPModel {
    @SerializedName("as")
    @Expose

    /* renamed from: as */
    private String f273as;
    @SerializedName("city")
    @Expose
    private String city;
    @SerializedName("country")
    @Expose
    private String country;
    @SerializedName("countryCode")
    @Expose
    private String countryCode;
    @SerializedName("isp")
    @Expose
    private String isp;
    @SerializedName("lat")
    @Expose
    private Double lat;
    @SerializedName("lon")
    @Expose
    private Double lon;
    @SerializedName("org")
    @Expose

    /* renamed from: org  reason: collision with root package name */
    private String f343org;
    @SerializedName("query")
    @Expose
    private String query;
    @SerializedName("region")
    @Expose
    private String region;
    @SerializedName("regionName")
    @Expose
    private String regionName;
    @SerializedName("status")
    @Expose
    private String status;
    @SerializedName("timezone")
    @Expose
    private String timezone;
    @SerializedName("zip")
    @Expose
    private String zip;

    public String getStatus() {
        return this.status;
    }

    public void setStatus(String str) {
        this.status = str;
    }

    public String getCountry() {
        return this.country;
    }

    public void setCountry(String str) {
        this.country = str;
    }

    public String getCountryCode() {
        return this.countryCode;
    }

    public void setCountryCode(String str) {
        this.countryCode = str;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String str) {
        this.region = str;
    }

    public String getRegionName() {
        return this.regionName;
    }

    public void setRegionName(String str) {
        this.regionName = str;
    }

    public String getCity() {
        return this.city;
    }

    public void setCity(String str) {
        this.city = str;
    }

    public String getZip() {
        return this.zip;
    }

    public void setZip(String str) {
        this.zip = str;
    }

    public Double getLat() {
        return this.lat;
    }

    public void setLat(Double d) {
        this.lat = d;
    }

    public Double getLon() {
        return this.lon;
    }

    public void setLon(Double d) {
        this.lon = d;
    }

    public String getTimezone() {
        return this.timezone;
    }

    public void setTimezone(String str) {
        this.timezone = str;
    }

    public String getIsp() {
        return this.isp;
    }

    public void setIsp(String str) {
        this.isp = str;
    }

    public String getOrg() {
        return this.f343org;
    }

    public void setOrg(String str) {
        this.f343org = str;
    }

    public String getAs() {
        return this.f273as;
    }

    public void setAs(String str) {
        this.f273as = str;
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String str) {
        this.query = str;
    }
}
