package org.jrm.data.garage;

public class Garage
{
    private Integer occupancy = 0;
    private Integer capacity;
    private String name;

    public Garage(Integer capacity, String name)
    {
        this.capacity = capacity;
        this.name = name;
    }

    /* Getters and setters */

    public Integer getOccupancy() {
        return occupancy;
    }

    public void setOccupancy(Integer occupancy) {
        this.occupancy = occupancy;
    }

    public Integer getCapacity() {
        return capacity;
    }

    public void setCapacity(Integer capacity) {
        this.capacity = capacity;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
