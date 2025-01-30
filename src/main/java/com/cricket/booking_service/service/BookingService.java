package com.cricket.booking_service.service;




import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cricket.booking_service.entity.Booking;
import com.cricket.booking_service.exception.ResourceNotFoundException;
import com.cricket.booking_service.pojo.BookingDTO;
import com.cricket.booking_service.repository.BookingRepository;

@Service
public class BookingService {

    @Autowired
    private BookingRepository bookingRepository;
    
    public BookingDTO getBookingsById(int bookingId) {
        Booking booking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new ResourceNotFoundException("Booking not found with ID: " + bookingId));

        return new BookingDTO(
                booking.getId(),
                booking.getBookingDate()
        );
    }



    // Booking CRUD Operations
    public Booking createBooking(Booking booking) {
        return bookingRepository.save(booking);
    }
    public Optional<Booking> getBookingById(int id) {
    	Optional<Booking> booking=bookingRepository.findById(id);
        return booking;
    }
//    public Booking getBookingById(Long id) {
//        return bookingRepository.findById(id).orElseThrow(() -> new RuntimeException("Booking not found"));
//    }

    public List<Booking> getAllBookings() {
        return bookingRepository.findAll();
    }

    public Booking updateBooking(int id, Booking updatedBooking) {
    	// Fetch the existing booking by ID
    	 // Fetch the existing booking by ID
        Optional<Booking> optionalBooking = bookingRepository.findById(id);

        if (optionalBooking.isPresent()) {
            Booking existingBooking = optionalBooking.get();

            // Update fields with new values
            existingBooking.setStadiumSlotId(updatedBooking.getStadiumSlotId());
            existingBooking.setUserId(updatedBooking.getUserId());
            existingBooking.setBookingDate(updatedBooking.getBookingDate());
            existingBooking.setStatus(updatedBooking.getStatus());

            // Save the updated booking back to the database
            return bookingRepository.save(existingBooking);
        } else {
            return null;
        }
    }

    public void deleteBooking(int id) {
       
        bookingRepository.deleteById(id);
    }
}
//    // Team CRUD Operations
//    public List<Team> getAllTeams() {
//        return teamRepository.findAll();
//    }

//    public Team getTeamById(Long id) {
//        return teamRepository.findById(id).orElseThrow(() -> new RuntimeException("Team not found"));
//    }

   


