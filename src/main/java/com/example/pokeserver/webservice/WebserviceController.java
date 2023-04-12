package com.example.pokeserver.webservice;

import com.example.pokeserver.business.GuestService;
import com.example.pokeserver.business.ReservationService;
import com.example.pokeserver.business.RoomReservation;
import com.example.pokeserver.data.Guest;
import com.example.pokeserver.data.Room;
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
private final GuestService guestService;

public WebserviceController(DateUtils dateUtils, ReservationService reservationService, GuestService guestService) {
    this.dateUtils = dateUtils;
    this.reservationService = reservationService;
    this.guestService = guestService;
}
@RequestMapping(path="/reservations",method = RequestMethod.GET)
        public List<RoomReservation> getReservations(@RequestParam(value="date",required=false) String dateString) {
        Date date = this.dateUtils.createDateFromDateString(dateString);
        return this.reservationService.getRoomReservationsForDate(date);
               }
@RequestMapping(path="/guests", method = RequestMethod.GET)
        public List<Guest> getGuests() {
        return this.guestService.getGuests();
               }
@RequestMapping(path="/guests", method = RequestMethod.POST)
@ResponseStatus(HttpStatus.CREATED)
        public void addGuest(@RequestBody Guest guest) {
        this.reservationService.addGuest(guest);
               }
@RequestMapping(path="/rooms", method = RequestMethod.GET)
        public List<Room> getRooms() {
        return this.reservationService.getRooms();
               }

}

