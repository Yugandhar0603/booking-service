package com.cricket.booking_service.pojo;

import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class BookingTeamPlayersPojo{
    int bookingId;
    LocalDate date;
    int gameWinTeam;
    boolean gameSwitch;
    List<ScoresPojo> scores;
    List<TeamPojo> teams;

}