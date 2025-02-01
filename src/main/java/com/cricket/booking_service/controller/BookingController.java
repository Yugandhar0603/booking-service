package com.cricket.booking_service.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.cricket.booking_service.entity.Booking;
import com.cricket.booking_service.pojo.BookingDTO;
import com.cricket.booking_service.pojo.BookingTeamPlayersPojo;
import com.cricket.booking_service.pojo.ScoresPojo;
import com.cricket.booking_service.pojo.TeamPojo;
import com.cricket.booking_service.service.BookingService;

@RestController
@RequestMapping("/bookings")
public class BookingController {

    @Autowired
    private BookingService bookingService;
    
    @GetMapping("/{bookingId}")
    public ResponseEntity<BookingDTO> getBookingsById(@PathVariable int bookingId) {
        BookingDTO bookingDTO = bookingService.getBookingsById(bookingId);
        return ResponseEntity.ok(bookingDTO);
    }
    @PostMapping
    public ResponseEntity<Booking> createBooking(@RequestBody Booking booking) {
        return new ResponseEntity<Booking>(bookingService.createBooking(booking),HttpStatus.OK);
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<Optional<Booking>> getBookingById(@PathVariable int id) {
        return new ResponseEntity<Optional<Booking>>(bookingService.getById(id),HttpStatus.OK);
    }
	// 	    SlotPojo slot = restClient
	// 			.get()
	// 			.uri("http://localhost:1719/slots/"+id)
	// 			.retrieve()
	// 			.body(SlotPojo.class);
    @GetMapping("/wholescore/{bookingId}")
    public ResponseEntity<BookingTeamPlayersPojo> getWholeScore(int bookingId){
        BookingTeamPlayersPojo wholeScore;
        RestClient restClient = RestClient.create();
        List<TeamPojo> teams=restClient
         .get()
         .uri("http://localhost:8090/booking/"+bookingId)
         .retrieve()
         .body(List.class)
         List<ScoresPojo> scores=restClient
         .get()
         .uri("http://localhost:8086")
         .retrieve()
         .body(List.class)
         Booking booking=bookingService.getById(bookingId).orElse(null);
         wholeScore.setBookingId(bookingId);
         wholeScore.setDate(booking.getBookingDate());
         wholeScore.setGameSwitch(false);
         wholeScore.setGameWinTeam(0);
         wholeScore.setTeams(teams);
         wholeScore.setScores(scores);
         return new ResponseEntity<>(wholeScore,HttpStatus.OK);
         
    }

    @GetMapping()
    public List<Booking> getAllBookings() {
        return bookingService.getAllBookings();
    }

}

