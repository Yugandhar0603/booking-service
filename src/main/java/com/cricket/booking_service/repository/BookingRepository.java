package com.cricket.booking_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.cricket.booking_service.entity.Booking;

public interface BookingRepository extends JpaRepository<Booking, Integer>{
}