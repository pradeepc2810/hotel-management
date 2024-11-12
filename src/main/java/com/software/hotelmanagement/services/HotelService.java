package com.software.hotelmanagement.services;

import com.software.hotelmanagement.Exception.NoHotelsFoundException;
import com.software.hotelmanagement.beans.Hotel;
import com.software.hotelmanagement.constants.HotelInit;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
public class HotelService {

    //fetch global hotel list
    List<Hotel> hotelList = Optional.ofNullable(HotelInit.hotels).orElse(new ArrayList<>());

    /*
        find the cheapest hotel based on per day rate
        o/p: hotel with minimum rate will be returned
    */
    public Hotel findCheapestHotel() throws NoHotelsFoundException {
        Hotel hotel = hotelList.stream().sorted(Comparator.comparingInt(Hotel::getPerDayRate)).findFirst().orElse(null);
        if (hotel == null) {
            throw new NoHotelsFoundException();
        }
        return hotel;
    }
}
