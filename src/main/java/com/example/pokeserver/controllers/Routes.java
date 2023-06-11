package com.example.pokeserver.controllers;

import PokeClient.java.generated.resources.League;

public class Routes {
    public final static String LeaguesRoute = "/api/league";
    public final static String CoursesRoute = "/api/league/{leagueId}/course";
    public final static String TasksRoute = "/api/league/{leagueId}/course/{courseId}/task";
    public final static String UsersRoute = "/api/user";
}
