package com.hwi.health.InterFaces;

public class Util {
   public static String getActivity1Number(String x){

        if(x.equals("No regular exercise")==true)
            return "1";
        if(x.equals("Exercise>20 min,1-3 days/week")==true)
            return "2";
        if(x.equals("Exercise>60 min, 5-7 days/week")==true)
            return "3";
        if(x.equals("Athlete or very active")==true)
            return "4";

        return x;
    }

    public static   String getActivity2Number(String x){

        if(x.equals("Sitting /desk jobs")==true)
            return "1";
        if(x.equals("Standing  for long times")==true)
            return "2";
        if(x.equals("Active e.g. waiter")==true)
            return "3";
        if(x.equals("Heavy activity, e.g. carpenter")==true)
            return "4";
        if(x.equals("Strenuous e.g.Labourers .")==true)
            return "5";
        return x;
    }

    public static  String getVegNumber(String food_habits){
        if (food_habits.equals("Non Vegetarian")) {
            return "3";
        } else if (food_habits.equals("Vegetarian")) {
            return "2";
        } else if (food_habits.equals("Eggetarian")) {
            return "1";
        }

        return food_habits;
    }
}
