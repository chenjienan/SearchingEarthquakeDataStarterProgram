package com.duke.searchingEarthquakeData;


import java.util.ArrayList;

public class LargestQuakes {

    private ArrayList<QuakeEntry> list;

    public void findLargestQuakes(String source){
        EarthQuakeParser parser = new EarthQuakeParser();
        this.list  = parser.read(source);
        System.out.println("read data for "+list.size());

        ArrayList<QuakeEntry> abc = this.getLargest(list, 5);

        for (QuakeEntry loc : abc){
            System.out.println(loc);
        }
    }

    private int indexOfLargest(ArrayList<QuakeEntry> data){

        int maxIndex = 0;

        for (int i = 0; i < data.size(); i++){
            if (data.get(i).getMagnitude() > data.get(maxIndex).getMagnitude()){
                maxIndex = i;
            }
        }

        return maxIndex;
    }

    private ArrayList<QuakeEntry> getLargest(ArrayList<QuakeEntry> quakeData, int howMany){
        ArrayList<QuakeEntry> copy = new ArrayList<QuakeEntry>(quakeData);
        ArrayList<QuakeEntry> ret = new ArrayList<QuakeEntry>();

        for (int j = 0; j < howMany; j++) {
            if (!copy.isEmpty()) {
                ret.add(copy.get(indexOfLargest(copy)));
                copy.remove(indexOfLargest(copy));
            }
        }

        return ret;
    }
}
