package com.software.hotelmanagement.constants;

import com.software.hotelmanagement.beans.Hotel;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class HotelInit {
    public static final List<Hotel> hotels = List.of(
            new Hotel("Miami Beach", 80),
            new Hotel("Miami Downtown", 120),
            new Hotel("Miami Midtown", 100));
}
