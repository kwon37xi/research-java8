package ch02_stream;

public class City {
    private String country;
    private String name;
    private int population;

    public City(String country, String name, int population) {
        this.country = country;
        this.name = name;
        this.population = population;
    }

    public String getCountry() {
        return country;
    }

    public String getName() {
        return name;
    }

    public int getPopulation() {
        return population;
    }

    @Override
    public String toString() {
        return "City{" +
                "country='" + country + '\'' +
                ", name='" + name + '\'' +
                ", population=" + population +
                '}';
    }
}
