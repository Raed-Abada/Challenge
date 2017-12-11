package tn.challenge;

/**
 * Created by Abada Raed on 13/12/2017.
 */

public class City {
    private String Name;
    private int Population;


    public City(String name, int population) {
        Name = name;
        Population = population;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public int getPopulation() {
        return Population;
    }

    @Override
    public String toString() {
        return Name + "  " + Population ;
    }

    public void setPopulation(int population) {
        Population = population;
    }
}
