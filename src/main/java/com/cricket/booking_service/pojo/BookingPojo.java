package com.cricket.booking_service.pojo;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingPojo {

	  int bookingId;
	    LocalDate date;
	    int gameWinTeam;
	    boolean gameSwitch;
}
