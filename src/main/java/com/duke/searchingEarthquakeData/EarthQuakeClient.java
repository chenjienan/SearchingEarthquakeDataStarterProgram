package com.duke.searchingEarthquakeData;

import java.util.ArrayList;

public class EarthQuakeClient {
    public EarthQuakeClient() {
        // TODO Auto-generated constructor stub
    }

    public ArrayList<QuakeEntry> filterByMagnitude(ArrayList<QuakeEntry> quakeData, double magMin) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if (qe.getMagnitude() > magMin){
                answer.add(qe);
            }
        }
        return answer;
    }

    public ArrayList<QuakeEntry> filterByDistanceFrom(ArrayList<QuakeEntry> quakeData, double distMax, Location from) {
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if (qe.getLocation().distanceTo(from) < distMax){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void dumpCSV(ArrayList<QuakeEntry> list){
        System.out.println("Latitude,Longitude,Magnitude,Info");
        for(QuakeEntry qe : list){
            System.out.printf("%4.2f,%4.2f,%4.2f,%s\n",
                qe.getLocation().getLatitude(),
                qe.getLocation().getLongitude(),
                qe.getMagnitude(),
                qe.getInfo());
        }

    }

    public void bigQuakes() {
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        String source = "data/nov20quakedata.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

    }

    public void closeToMe(){
        EarthQuakeParser parser = new EarthQuakeParser();
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";

        ClassLoader classLoader = EarthQuakeParser.class.getClassLoader();
        String source = classLoader.getResource("data/nov20quakedatasmall.atom").getPath();

        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        // This location is Durham, NC
        //Location city = new Location(35.988, -78.907);

        // This location is Bridgeport, CA
        Location city =  new Location(38.17, -118.82);

        ArrayList<QuakeEntry> assignment2 = this.filterByDistanceFrom(list, 1000000.00, city);
        for (QuakeEntry loc : assignment2){
            System.out.println(loc);
        }
    }

    public void createCSV(){
        EarthQuakeParser parser = new EarthQuakeParser();
        String source = "data/nov20quakedatasmall.atom";
        //String source = "http://earthquake.usgs.gov/earthquakes/feed/v1.0/summary/all_week.atom";
        ArrayList<QuakeEntry> list  = parser.read(source);
        dumpCSV(list);
        System.out.println("# quakes read: " + list.size());
        for (QuakeEntry qe : list) {
            System.out.println(qe);
        }
    }

    public ArrayList<QuakeEntry> filterByDepth(ArrayList<QuakeEntry> quakeData, double minDepth, double maxDepth){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();
        for(QuakeEntry qe : quakeData){
            if (qe.getDepth() > minDepth && qe.getDepth() < maxDepth){
                answer.add(qe);
            }
        }
        return answer;
    }

    public void quakesOfDepth(){
        EarthQuakeParser parser = new EarthQuakeParser();

        ClassLoader classLoader = EarthQuakeParser.class.getClassLoader();
        String source = classLoader.getResource("data/nov20quakedatasmall.atom").getPath();

        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> assignment2 = this.filterByDepth(list, -10000.0, -5000.0);
        for (QuakeEntry loc : assignment2){
            System.out.println(loc);
        }
        System.out.println("Found "+assignment2.size()+ " quakes that match that criteria");
    }

    public ArrayList<QuakeEntry> filterByPhrase(ArrayList<QuakeEntry> quakeData, String where, String phrase){
        ArrayList<QuakeEntry> answer = new ArrayList<QuakeEntry>();

        if (where.equals("any")){
            for (QuakeEntry qe : quakeData) {
                if (qe.getInfo().contains(phrase)){
                    answer.add(qe);
                }
            }
        }
        else if (where.equals("start")){
            for (QuakeEntry qe : quakeData) {
                if (qe.getInfo().startsWith(phrase)){
                    answer.add(qe);
                }
            }
        }
        else if (where.equals("end")){
            for (QuakeEntry qe : quakeData) {
                if (qe.getInfo().endsWith(phrase)){
                    answer.add(qe);
                }
            }
        }
        return answer;
    }

    public void filterByPhrase(){
        EarthQuakeParser parser = new EarthQuakeParser();

        ClassLoader classLoader = EarthQuakeParser.class.getClassLoader();
        String source = classLoader.getResource("data/nov20quakedatasmall.atom").getPath();

        ArrayList<QuakeEntry> list  = parser.read(source);
        System.out.println("read data for "+list.size()+" quakes");

        ArrayList<QuakeEntry> assignment3 = this.filterByPhrase(list, "any", "Can");
        for (QuakeEntry loc : assignment3){
            System.out.println(loc);
        }
        System.out.println("Found "+assignment3.size()+ " quakes that match that criteria");
    }
}
