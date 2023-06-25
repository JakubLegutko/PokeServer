package com.example.pokeserver.data;

import lombok.Data;

import java.util.List;

@Data
public class Course {
    String id;
    String title;
    List<String> taskIds;
    String imageSrc;
}
