package com.cricket.booking_service.pojo;


import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TeamPojo{
    private int id;
    private String name;
    List<TeamPlayersPojo> teamPlayers;

}