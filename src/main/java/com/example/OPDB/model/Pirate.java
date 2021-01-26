package com.example.OPDB.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Pirate {
    private UUID id;
    public String name;
    private String epithet;
    private String crew;
    private String power;

    public Pirate(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name,
                  @JsonProperty("epithet") String epithet,
                  @JsonProperty("crew") String crew,
                  @JsonProperty("power")String power) {
        this.id = id;
        this.name = name;
        this.epithet = epithet;
        this.crew = crew;
        this.power = power;
    }




    public UUID getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public String getEpithet(){return epithet;}
    public String getCrew() { return crew; }
    public String getPower() { return power; }
}
