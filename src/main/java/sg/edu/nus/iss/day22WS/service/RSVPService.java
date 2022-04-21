package sg.edu.nus.iss.day22WS.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.iss.day22WS.Repository.RSVPRepository;
import sg.edu.nus.iss.day22WS.model.RSVP;

@Service
public class RSVPService {

    @Autowired
    private RSVPRepository rRepo;

    public List<RSVP> getAllRsvps() {
        List<RSVP> list = rRepo.getAllRsvps();
        return list;
    }

    public List<RSVP> getRsvpByName(String name) {
        List<RSVP> list = rRepo.getRsvpByName(name);
        return list;
    }

    public boolean addRsvp(MultiValueMap<String, String> form) {
        boolean bool = rRepo.addRsvp(form);
        return bool;
    }

    public Optional<Integer> getNumberOfRsvp() {
        Optional<Integer> opt = rRepo.getNumberOfRsvp();

        return opt;
    }

}
