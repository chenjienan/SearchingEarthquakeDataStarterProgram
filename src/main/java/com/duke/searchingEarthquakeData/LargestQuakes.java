package com.duke.searchingEarthquakeData;


import java.util.ArrayList;

public class LargestQuakes {

    private ArrayList<QuakeEntry> list;

    public void findLargestQuakes(String source){
        EarthQuakeParser parser = new EarthQuakeParser();
        this.list  = parser.read(source);
        System.out.println("read data for "+list.size());
    }

//    public int indexOfLargest(ArrayList<QuakeEntry> quakeData){
//
//    }
}
