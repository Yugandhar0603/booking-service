package com.cricket.booking_service.controller;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClient;

import com.cricket.booking_service.entity.Booking;
import com.cricket.booking_service.pojo.BookingDTO;
import com.cricket.booking_service.pojo.BookingPojo;
import com.cricket.booking_service.pojo.BookingTeamPlayersPojo;
import com.cricket.booking_service.pojo.ScoresPojo;
import com.cricket.booking_service.pojo.TeamPojo;
import com.cricket.booking_service.service.BookingService;

@CrossOrigin("*")
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
    @GetMapping("/date/{date}")
    public ResponseEntity<Booking> getByBookingDate(@PathVariable LocalDate bookingDate){

    	return new ResponseEntity<Booking>(bookingService.getByBookingDate(bookingDate),HttpStatus.OK);
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
    public ResponseEntity<BookingTeamPlayersPojo> getWholeScore(@PathVariable int bookingId){
    	System.out.println(bookingId);
        BookingTeamPlayersPojo wholeScore=new BookingTeamPlayersPojo();
        RestClient restClient = RestClient.create();
        List<TeamPojo> teams=restClient
         .get()
         .uri("http://localhost:9090/teams/booking/teamplayers/"+bookingId)//http://localhost:9090/teams/booking/teamplayers/401
         .retrieve()
         .body(List.class);
         List<ScoresPojo> scores=restClient
         .get()
         .uri("http://localhost:8086/scores")
         .retrieve()
         .body(List.class);
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
    @PutMapping("/update")
    public ResponseEntity<BookingTeamPlayersPojo> updateScore(@RequestBody BookingTeamPlayersPojo updateScore){
    	System.out.println(updateScore);
    	if(!updateScore.isGameSwitch()) {
    		RestClient restClient = RestClient.create();
    		ScoresPojo requestBody=updateScore.getScores().get(0);
    		System.out.println(requestBody);
            ScoresPojo updateScores1=restClient
             .put()
             .uri("http://localhost:8086/scores/update")//http://localhost:9090/teams/booking/teamplayers/401
             .body(requestBody)
             .retrieve()
             .body(ScoresPojo.class);
//            updateScore.getScores().set(1, updateScores1);
    	}
    	else {
    	RestClient restClient = RestClient.create();
		ScoresPojo requestBody1=updateScore.getScores().get(1);
        ScoresPojo updateScores2=restClient
         .put()
         .uri("http://localhost:8086/scores/update")//http://localhost:9090/teams/booking/teamplayers/401
         .body(requestBody1)
         .retrieve()
         .body(ScoresPojo.class);
//        updateScore.getScores().set(0, updateScores2)
    	}
    	return new ResponseEntity<BookingTeamPlayersPojo>(updateScore,HttpStatus.OK);
    }

}

