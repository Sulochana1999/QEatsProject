
/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.dto;


import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

public class Restaurant
{
    // @JsonIgnore
    @Setter
    @Getter
    private String  restaurantId;
    @NotNull
    private String name;
    @NotNull
    private String city;
    @NotNull
    private String imageUrl;
    @Setter
    @Getter
    @NotNull
    private Double latitude;

    @Setter
    @Getter
    @NotNull
    private Double longitude;
    @NotNull
    private String opensAt;
    @NotNull
    private String closesAt;
    @NotNull
    private ArrayList<String> attributes;



}

