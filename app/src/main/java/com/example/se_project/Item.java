package com.example.se_project;

import java.util.Comparator;

public class Item {
    private String name;
    private double distance;

    public String getName() {
        return name;
    }

    public double getDistance() {
        return distance;
    }

    /**
     * Comparator for sorting the list by Name.
     */
    public static Comparator<Item> nameComparator = new Comparator<Item>() {

        public int compare(Item s1, Item s2) {
            String name1 = s1.getName().toUpperCase();
            String name2 = s2.getName().toUpperCase();

            //ascending order
            return name1.compareTo(name2);

            //descending order
            //return name2.compareTo(name1);
        }};

    /**
     * Comparator for sorting the list by distance
     */
    public static Comparator<Item> distanceComparator = new Comparator<Item>() {

        public int compare(Item s1, Item s2) {

            double rollno1 = s1.getDistance();
            double rollno2 = s2.getDistance();

            /*For ascending order*/
            return  (int) (rollno1 - rollno2);

            /*For descending order*/
            //rollno2-rollno1;
        }
    };
}
