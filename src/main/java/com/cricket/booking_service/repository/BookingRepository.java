package com.cricket.booking_service.repository;

import java.time.LocalDate;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.cricket.booking_service.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
	Optional<Booking> findByBookingDate(LocalDate bookingDate);


}