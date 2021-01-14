package com.ecomtrading.ecomtest_2;

public class ClientModel {


    /*
        postDataParams.put("community_name", comname.getText().toString().trim());
                    postDataParams.put("geographical_district", gid);
                    postDataParams.put("accessibility", Integer.parseInt(accid));
                    postDataParams.put("distance_to_ecom", Integer.parseInt(dtid));
                    postDataParams.put("connected_to_ecg", select);*/
    private String id;
    private String community_name;
    private String geographical_district;
    private String accessibility;
    private String distance_to_ecom;
    private String connected_to_ecg;
    private String date_of_license;
    private String latitude;
    private String longitude;
    private String CreatedBy;

    public String getCreatedBy() {
        return CreatedBy;
    }

    public void setCreatedBy(String createdBy) {
        CreatedBy = createdBy;
    }

    public String getCreatedDate() {
        return CreatedDate;
    }

    public void setCreatedDate(String createdDate) {
        CreatedDate = createdDate;
    }

    public String getUpdatedBy() {
        return UpdatedBy;
    }

    public void setUpdatedBy(String updatedBy) {
        UpdatedBy = updatedBy;
    }

    public String getUpdatedDate() {
        return UpdatedDate;
    }

    public void setUpdatedDate(String updatedDate) {
        UpdatedDate = updatedDate;
    }

    private String CreatedDate;
    private String UpdatedBy;
    private String UpdatedDate;
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getCommunity_name() {
        return community_name;
    }

    public void setCommunity_name(String community_name) {
        this.community_name = community_name;
    }

    public String getGeographical_district() {
        return geographical_district;
    }

    public void setGeographical_district(String geographical_district) {
        this.geographical_district = geographical_district;
    }

    public String getAccessibility() {
        return accessibility;
    }

    public void setAccessibility(String accessibility) {
        this.accessibility = accessibility;
    }

    public String getDistance_to_ecom() {
        return distance_to_ecom;
    }

    public void setDistance_to_ecom(String distance_to_ecom) {
        this.distance_to_ecom = distance_to_ecom;
    }

    public String getConnected_to_ecg() {
        return connected_to_ecg;
    }

    public void setConnected_to_ecg(String connected_to_ecg) {
        this.connected_to_ecg = connected_to_ecg;
    }

    public String getDate_of_license() {
        return date_of_license;
    }

    public void setDate_of_license(String date_of_license) {
        this.date_of_license = date_of_license;
    }

    public String getLatitude() {
        return latitude;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}

