package com.example.se_project;

import android.location.Address;

public class LicensedPharmacy {

    private String pharmacy_name;
    private String pharmacist_in_charge;
    private String pharmacy_address;
    private Address address;

    LicensedPharmacy(String pharmacy_name,String pharmacist_in_charge,String pharmacy_address){
        this.pharmacy_name = pharmacy_name;
        this.pharmacist_in_charge = pharmacist_in_charge;
        this.pharmacy_address = pharmacy_address;
        address = null;
    }

    void setAddress(){
        String [] addressArray = pharmacy_address.trim().split(",");
    }
    public String getPharmacy_name(){
        return pharmacy_name;
    }

    public String getPharmacist_in_charge(){
        return pharmacist_in_charge;
    }

    public String getPharmacy_address(){
        return pharmacy_address;
    }
}
