package com.example.pokeserver.business;

import com.example.pokeserver.data.Guest;
import com.example.pokeserver.data.GuestRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GuestService {

    private final GuestRepository guestRepository;

    public GuestService(GuestRepository guestRepository) {
        this.guestRepository = guestRepository;
    }

    public List<Guest> getGuests() {
        List<Guest> guestList;

        guestList = this.guestRepository.findAll();
        return guestList;
    }
}
