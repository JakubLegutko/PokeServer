package com.example.pokeserver.data;

import java.util.List;

public record League(
    String id,
    LeagueType leagueType,
    String color,
    String imgSrc,
    List<String> courseIds
){}
