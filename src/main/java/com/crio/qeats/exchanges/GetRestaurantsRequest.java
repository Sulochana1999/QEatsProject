/*
 *
 *  * Copyright (c) Crio.Do 2019. All rights reserved
 *
 */

package com.crio.qeats.exchanges;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor

public class GetRestaurantsRequest {
    @NotNull
    @Min(value=-90)
    @Max(value=90)
    private Double lattitude;
    @NotNull
    @Min(value=-180)
    @Max(value=180)
    private Double longitude;


    // private String  saerchFor;




}

