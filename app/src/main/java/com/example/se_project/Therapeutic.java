package com.example.se_project;

public class Therapeutic {
    String license_no, product_name, license_holder, approval_date, forensic_classification, atc_code, dosage_form, route_of_administration,
            manufacturer, country_of_manufacturer, active_ingredients,strength;

    public Therapeutic(String license_no, String product_name, String license_holder, String approval_date, String forensic_classification, String atc_code, String dosage_form, String route_of_administration, String manufacturer, String country_of_manufacturer, String active_ingredients, String strength) {
        this.license_no = license_no;
        this.product_name = product_name;
        this.license_holder = license_holder;
        this.approval_date = approval_date;
        this.forensic_classification = forensic_classification;
        this.atc_code = atc_code;
        this.dosage_form = dosage_form;
        this.route_of_administration = route_of_administration;
        this.manufacturer = manufacturer;
        this.country_of_manufacturer = country_of_manufacturer;
        this.active_ingredients = active_ingredients;
        this.strength = strength;
    }

    public String getLicense_no() {
        return license_no;
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getLicense_holder() {
        return license_holder;
    }

    public String getApproval_date() {
        return approval_date;
    }

    public String getForensic_classification() {
        return forensic_classification;
    }

    public String getAtc_code() {
        return atc_code;
    }

    public String getDosage_form() {
        return dosage_form;
    }

    public String getRoute_of_administration() {
        return route_of_administration;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public String getCountry_of_manufacturer() {
        return country_of_manufacturer;
    }

    public String getActive_ingredients() {
        return active_ingredients;
    }

    public String getStrength() {
        return strength;
    }
}
