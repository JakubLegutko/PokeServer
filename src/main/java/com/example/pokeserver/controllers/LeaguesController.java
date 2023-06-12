package com.example.pokeserver.controllers;

import PokeClient.java.generated.resources.League;
import com.example.pokeserver.data.User;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Objects;

import static com.example.pokeserver.controllers.Routes.LeaguesRoute;

@RestController
@RequestMapping(LeaguesRoute)
public class LeaguesController {
    @RequestMapping(method = RequestMethod.GET)
    public List<League> getAll()
    {
        return Collections.emptyList();
    }

    @RequestMapping(path = "/{leagueId}", method = RequestMethod.GET)
    public League get(String id)
    {
        return null;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<Void> add(League newLeague)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{leagueId}", method = RequestMethod.DELETE)
    public ResponseEntity<Void> delete(String id)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(path = "/{leagueId}", method = RequestMethod.PATCH)
    public ResponseEntity<Void> modify(String id, League leaguePatch)
    {
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
