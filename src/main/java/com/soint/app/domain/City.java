package com.soint.app.domain;

import java.util.Objects;

public class City {

    private String countryName;
    private String cityName;
    private String stateName;
    private Coords coords;

    public String getCountryName() {
        return countryName;
    }

    public void setCountryName(String countryName) {
        this.countryName = countryName;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getStateName() {
        return stateName;
    }

    public void setStateName(String stateName) {
        this.stateName = stateName;
    }

    public Coords getCoords() {
        return coords;
    }

    public void setCoords(Coords coords) {
        this.coords = coords;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        City city = (City) o;
        return Objects.equals(countryName, city.countryName) &&
                Objects.equals(cityName, city.cityName) &&
                Objects.equals(stateName, city.stateName);
    }

    @Override
    public int hashCode() {
        return Objects.hash(countryName, cityName, stateName);
    }
}
