package com.cricket.booking_service.entity;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "bookings")
public class Booking {
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id")
    private int id;

    @Column(name="stadium_slot_id")
    private int stadiumSlotId; // One-to-One with StadiumSlot

    @Column(name="user_id")
    private int userId; // One-to-One with UserRoles

    @Column(name="booking_date")
    private LocalDate bookingDate;

    @Column(name="status")
    private String status; // Example: CONFIRMED, PENDING, CANCELLED
}

