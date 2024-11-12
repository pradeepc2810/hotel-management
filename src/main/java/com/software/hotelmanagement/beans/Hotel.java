package com.software.hotelmanagement.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Hotel {

    @JsonProperty("hotel_name")
    private String name;

    @JsonProperty("per_day_rate")
    private int perDayRate;
}
