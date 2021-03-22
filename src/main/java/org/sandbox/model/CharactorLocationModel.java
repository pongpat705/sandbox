package org.sandbox.model;

import lombok.Data;

import java.util.List;

@Data
public class CharactorLocationModel {
    private long id;
    private String name;
    private String status;
    private String species;
    private String type;
    private String gender;
    private LocationModel origin;
    private LocationModel location;
    private String image;
    private List<String> episode;
    private String url;
    private String created;

}
