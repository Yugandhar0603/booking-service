package com.cricket.booking_service.pojo;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ScoresPojo {
	private int id;
    private int gameTotalOvers;
    private int strikerRuns;
    private int wideBall;
    private int gameWickets;
    private int gameBallNo;
    private int strikerPlayerId;
    private int nonStrikerPlayerId;
    private int bowlerId;
}
