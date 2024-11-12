package com.software.hotelmanagement;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.software.hotelmanagement.Exception.NoHotelsFoundException;
import com.software.hotelmanagement.beans.Hotel;
import com.software.hotelmanagement.constants.HotelInit;
import com.software.hotelmanagement.controller.ApiController;
import com.software.hotelmanagement.services.HotelService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(ApiController.class)
@ExtendWith(MockitoExtension.class)
class HotelManagementApplicationTests {

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private HotelService hotelService;

	@Test
	void getCheapestHotelTest() throws Exception {
		Hotel hotel = new Hotel("Miami Beach", 80);

		when(hotelService.findCheapestHotel()).thenReturn(hotel);

		MvcResult response = mockMvc.perform(get("/hotels/cheapestHotel")
				.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isOk())
				.andReturn();

		Hotel actualResponse = objectMapper.readValue(response.getResponse().getContentAsString(), Hotel.class);

		assertEquals(hotel, actualResponse);
	}

	@Test
	void NoHotelsFoundExceptionTest() throws Exception {
		when(hotelService.findCheapestHotel()).thenThrow(new NoHotelsFoundException());

		MvcResult result = mockMvc.perform(get("/hotels/cheapestHotel")
						.contentType(MediaType.APPLICATION_JSON))
				.andExpect(status().isInternalServerError())
				.andReturn();

		// Convert JSON response to an ErrorResponse object
		String jsonResponse = result.getResponse().getContentAsString();
		assertEquals("{\"statusCode\":500,\"message\":\"No Hotels Found.\"}", jsonResponse);
	}

}
