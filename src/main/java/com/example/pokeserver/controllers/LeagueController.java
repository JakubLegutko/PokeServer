package com.example.pokeserver.controllers;

import com.example.pokeserver.data.League;
import com.example.pokeserver.data.LeagueRepository;
import com.example.pokeserver.data.LeagueType;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Collections;
import java.util.List;

@RestController
@Component
@RequiredArgsConstructor
@RequestMapping("/api/leagues")
public class LeagueController {

    private final LeagueRepository leagueRepository;

    @GetMapping
    public List<League> getAllLeagues() {
        return leagueRepository.findAll();
    }

    @GetMapping("/{id}")
    public League getLeagueById(@PathVariable Long id) {
        var leagueFound = leagueRepository.findById(id);

        return leagueFound.orElseGet(() -> new League("", LeagueType.BUG, "", "", Collections.emptyList()));
    }
}
