package com.example.se_project;

public class UVI {
    double uvi_index;

    public UVI(double uvi_index){
        this.uvi_index = uvi_index;
    }

    double getUvi_index(){
        return uvi_index;
    }

    /*https://data.gov.sg/dataset/ultraviolet-index-uvi*/
    public String safety_levels(){
        if (0<=uvi_index&&uvi_index<=2) return "Low";
        if(3<=uvi_index && uvi_index <=5) return "Moderate";
        if (6<=uvi_index&&uvi_index<=7) return "High";
        if(8<=uvi_index && uvi_index<=10) return "Very High";
        if (uvi_index>=11) return "Extreme";
        return "impossible";
    }

    /*https://www.epa.gov/sunsafety/uv-index-scale-1*/
    public String health_effects(){
        if (0<=uvi_index&&uvi_index<=2) return "A UV Index reading of 0 to 2 means low danger from the sun's UV rays for the average person";
        if(3<=uvi_index && uvi_index <=5) return "A UV Index reading of 3 to 5 means moderate risk of harm from unprotected sun exposure.";
        if (6<=uvi_index&&uvi_index<=7) return "A UV Index reading of 6 to 7 means high risk of harm from unprotected sun exposure. Protection against skin and eye damage is needed.";
        if(8<=uvi_index && uvi_index<=10) return "A UV Index reading of 8 to 10 means very high risk of harm from unprotected sun exposure. Take extra precautions because unprotected skin and eyes will be damaged and can burn quickly.";
        if (uvi_index>=11) return "A UV Index reading of 11 or more means extreme risk of harm from unprotected sun exposure. Take all precautions because unprotected skin and eyes can burn in minutes.";
        return "impossible";
    }

    public String precaution(){
        if (0<=uvi_index&&uvi_index<=2) return "Wear sunglasses on bright days.";
        if(3<=uvi_index && uvi_index <=5) return "Stay in shade near midday when the sun is strongest.";
        if (6<=uvi_index&&uvi_index<=7) return "If outdoors, seek shade and wear protective clothing, a wide-brimmed hat, and UV-blocking sunglasses.";
        if(8<=uvi_index && uvi_index<=10) return "Generously apply broad spectrum SPF 30+ sunscreen every 2 hours, even on cloudy days, and after swimming or sweating.";
        if (uvi_index>=11) return "Try to avoid sun exposure between 10 a.m. and 4 p.m.";
        return "impossible";
    }

}
