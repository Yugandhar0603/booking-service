package com.cricket.booking_service.pojo;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPlayersPojo{
    private int tPlayerId;
    private int teamId; // Foreign Key referencing Teams.id
    private int playerId; // Foreign Key referencing Players.id
    private int playerRuns;
    private int playerWickets;
    private int playerCatches;
}