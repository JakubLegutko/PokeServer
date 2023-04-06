package com.example.pokeserver.web;

import com.example.pokeserver.business.GuestService;
import com.example.pokeserver.data.Guest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
@RequestMapping("/guests")
public class GuestController {
    private final GuestService guestService;

    public GuestController(GuestService guestService) {
        this.guestService = guestService;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String getGuests(Model model) {
        List<Guest> guests = this.guestService.getGuests();
        model.addAttribute("guests", guests);
        return "guests";
    }
}
