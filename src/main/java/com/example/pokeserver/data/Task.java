package com.example.pokeserver.data;

import lombok.Data;

import java.util.List;

@Data
public class Task {
    String id;
    String question;
    List<Answer> answers;
}