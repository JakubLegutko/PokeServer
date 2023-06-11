package com.example.pokeserver.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static com.example.pokeserver.controllers.Routes.TasksRoute;

@RestController
@RequestMapping(TasksRoute)
public class TasksController {
}
