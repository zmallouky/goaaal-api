package com.goaaal.api.domain.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@AllArgsConstructor
@NoArgsConstructor
@Setter
@Getter
public class Match {
    private String id;
    private String hometeam;
    private String awayteam;
    private String hometeamScore;
    private String awayteamScore;
    private LocalDateTime matchTime;
    private String creatorId;
}
