package com.example.se_project;

import java.util.ArrayList;
import java.util.Collections;
/**
 * @author Veronica
 * The ItemList class, used to display the list of products, etc.
 */
public class ItemList {
    private ArrayList<Item> arraylist = new ArrayList<>();
    /**
     * Generate a ItemList.
     */
    public ItemList(ArrayList<Item> origin)
    {
        ArrayList<Item> arrayList = (ArrayList<Item>) origin.clone();
    }
    /**
     * Sort by name
     */
    public void sortByName()
    {
        System.out.println("Name Sorting:");
        Collections.sort(arraylist, Item.nameComparator);

        for(Item str: arraylist){
            System.out.println(str);
        }
    }

    /**
     * Sort by distance.
     */
    public void sortByDistance()
    {
        System.out.println("Distance Sorting:");
        Collections.sort(arraylist, Item.distanceComparator);

        for(Item str: arraylist){
            System.out.println(str);
        }
    }

}
