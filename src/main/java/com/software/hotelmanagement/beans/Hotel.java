package com.software.hotelmanagement.beans;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.Objects;

@Data
@AllArgsConstructor
public class Hotel {

    @JsonProperty("hotel_name")
    private String name;

    @JsonProperty("per_day_rate")
    private int perDayRate;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Hotel hotel = (Hotel) o;
        return perDayRate == hotel.perDayRate && Objects.equals(name, hotel.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, perDayRate);
    }
}
