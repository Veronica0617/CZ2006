package com.example.se_project;

public class Food {
    String main_food_group,company_name,product_name,brand_name,product_weight,information;

    public Food(String main_food_group, String company_name, String product_name, String brand_name, String product_weight) {
        this.main_food_group = main_food_group;
        this.company_name = company_name;
        this.product_name = product_name;
        this.brand_name = brand_name;
        this.product_weight = product_weight;

        this.information="Name:"+product_name+"\n"+"Company: "+company_name+"\n"+"Brand: "+brand_name+"\n"+"Weight: "+product_weight+"\n";
    }

    public String getProduct_name() {
        return product_name;
    }

    public String getInformation() {
        return information;
    }
}
