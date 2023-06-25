package com.example.pokeserver.data;

import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "leagues")
@Getter
@Setter
@NoArgsConstructor
public class League {
    @Id
    @Column(name = "league_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = false)
    private String name;

    @ManyToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    @JoinTable(
            name = "leagues_users",
            joinColumns = @JoinColumn(name = "league_id"),
            inverseJoinColumns = @JoinColumn(name = "user_id")
    )
    private Set<User> users = new HashSet<>();

    @Column(nullable = false, unique = false)
    private LeagueType leagueType;

    @Column(nullable = false, unique = false)
    private String color;

    @Column(nullable = false, unique = false)
    private String imageSrc;

    @ElementCollection
    private List<String> courseIds;

}
