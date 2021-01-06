package com.example.OPDB.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.UUID;

public class Pirate {

    private UUID id;
    private String name;
    private String crew;
    private String power;

    public Pirate(@JsonProperty("id") UUID id,
                  @JsonProperty("name") String name) {
        this.id = id;
        this.name = name;
    }

    public Pirate(UUID id, String name, String crew, String power) {
        this.id = id;
        this.name = name;
        this.crew = crew;
        this.power = power;
    }

    public String getCrew() {
        return crew;
    }

    public String getPower() {
        return power;
    }

    public UUID getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
