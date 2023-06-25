package com.example.pokeserver.data;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum LeagueType {
    FAIRY("fairy"),
    GROUND("ground"),
    STEEL("steel"),
    ELECTRIC("electric"),
    GRASS("grass"),
    ROCK("rock"),
    GHOST("ghost"),
    FIRE("fire"),
    BUG("bug");

    private final String type;
}
