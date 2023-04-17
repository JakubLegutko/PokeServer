package com.example.pokeserver.webservice;

import com.example.pokeserver.business.*;
import com.example.pokeserver.data.Guest;
import com.example.pokeserver.data.Room;
import com.example.pokeserver.data.User;
import com.example.pokeserver.util.DateUtils;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;

@RestController
@RequestMapping("/api")
public class WebserviceController {
    private final DateUtils dateUtils;
    private final ReservationService reservationService;
    private final RegistrationService registrationService;
    private final GuestService guestService;
    private final LoginService loginService;

    public WebserviceController(DateUtils dateUtils, ReservationService reservationService, RegistrationService registrationService, GuestService guestService, LoginService loginService) {
        this.dateUtils = dateUtils;
        this.reservationService = reservationService;
        this.registrationService = registrationService;
        this.guestService = guestService;
        this.loginService = loginService;
    }

    @RequestMapping(path = "/reservations", method = RequestMethod.GET)
    public List<RoomReservation> getReservations(@RequestParam(value = "date", required = false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
    }

    @RequestMapping(path = "/guests", method = RequestMethod.GET)
    public List<Guest> getGuests() {
        return this.guestService.getGuests();
    }

    @RequestMapping(path = "/guests", method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.CREATED)
    public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
    }

    @RequestMapping(path = "/rooms", method = RequestMethod.GET)
    public List<Room> getRooms() {
        return this.reservationService.getRooms();
    }

@RequestMapping(path = "/login", method = RequestMethod.POST)
    public void loginUser(@RequestBody User user) {
        int response =  this.loginService.loginUser(user);
        if (response == 401) {
            throw new RuntimeException("User does not exist");
        } else if (response == 201) {
            System.out.println("User logged in");
        }
    }
    @RequestMapping(path = "/register", method = RequestMethod.POST)
    public void registerUser(@RequestBody User user) {
        int response =  this.registrationService.registerUser(user);
        if (response == 401) {
            throw new RuntimeException("User already exists");
        } else if (response == 201) {
            System.out.println("User created");
        }
    }
}

