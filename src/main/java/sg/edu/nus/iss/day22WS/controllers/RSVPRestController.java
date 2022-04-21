package sg.edu.nus.iss.day22WS.controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import jakarta.json.Json;
import jakarta.json.JsonArray;
import jakarta.json.JsonArrayBuilder;
import jakarta.json.JsonObjectBuilder;
import sg.edu.nus.iss.day22WS.model.RSVP;
import sg.edu.nus.iss.day22WS.service.RSVPService;

@RestController
@RequestMapping("/api")
public class RSVPRestController {

    @Autowired
    public RSVPService rSvc;

    @GetMapping(path = "/rsvps", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getAllRsvps() {
        List<RSVP> list = rSvc.getAllRsvps();

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        for (RSVP r : list) {
            arrBuilder.add(Json.createObjectBuilder()
                    .add("name", r.getName() != null ? r.getName() : "")
                    .add("email", r.getEmail() != null ? r.getEmail() : "")
                    .add("comments", r.getComments() != null ? r.getComments() : "")
                    .add("phone", r.getPhone() != null ? r.getPhone() : "")
                    .add("confirmationDate",
                            r.getConfirmationDate() != null ? r.getConfirmationDate().toString() : ""));

        }

        JsonArray rsvpsList = arrBuilder.build();

        System.out.println(">>>>>customersList: " + rsvpsList);
        return ResponseEntity.ok(rsvpsList.toString());
    }

    @GetMapping(path = "/rsvp", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getRsvpByName(@RequestParam String name) {
        List<RSVP> list = rSvc.getRsvpByName(name);

        JsonArrayBuilder arrBuilder = Json.createArrayBuilder();
        if (list.size() < 1) {
            return ResponseEntity.status(404)
                    .body(
                            arrBuilder.add(Json.createObjectBuilder()
                                    .add("error", "not found: %s".formatted(name)))
                                    .build().toString());
        } else {

            for (RSVP r : list) {
                arrBuilder.add(Json.createObjectBuilder()
                        .add("name", r.getName() != null ? r.getName() : "")
                        .add("email", r.getEmail() != null ? r.getEmail() : "")
                        .add("comments", r.getComments() != null ? r.getComments() : "")
                        .add("phone", r.getPhone() != null ? r.getPhone() : "")
                        .add("confirmationDate",
                                r.getConfirmationDate() != null ? r.getConfirmationDate().toString() : ""));

            }

            JsonArray rsvpsList = arrBuilder.build();

            System.out.println(">>>>>customersList: " + rsvpsList);
            return ResponseEntity.ok(rsvpsList.toString());
        }
    }

    @PostMapping(path="/rsvp", produces = MediaType.APPLICATION_JSON_VALUE, consumes = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ResponseEntity<String> addRsvp(@RequestBody MultiValueMap<String, String> form, Model model){
        boolean bool = rSvc.addRsvp(form);
        if (bool == true){
            return ResponseEntity.status(201).body("rsvp added successfully.");
        }
        return ResponseEntity.status(404).body("rsvp added unsuccessfully.");
    }

    @GetMapping(path="/rsvps/count", produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<String> getNumberOfRsvp(){
        Optional<Integer> opt = rSvc.getNumberOfRsvp();
        
        JsonObjectBuilder objBuilder = Json.createObjectBuilder();
        Integer count = opt.get();
        if(count == 0){
            return ResponseEntity.status(404)
            .body(
                objBuilder.add("error", "no result")
                          .add("RSVP count: ", count)
                          .build().toString()
            );
        }
        

        return ResponseEntity.status(201).body(objBuilder.add("RSVP count: ", count).build().toString());
       
    }

    // @GetMapping(path="/rsvps/count", produces = MediaType.APPLICATION_JSON_VALUE)
    // public ModelAndView getNumberOfRsvp(){
    //     Optional<Integer> opt = rSvc.getNumberOfRsvp();
        
    //         Integer count = opt.get();
    //         ModelAndView mav = new ModelAndView();
    //         if(count == 0){
    //             mav.addObject("error", "no result");
    //             mav.addObject("count", count);
    //             mav.setStatus(HttpStatus.BAD_REQUEST);
    //             mav.setViewName("index2");
    //             return mav;
    //         }
    //             mav.addObject("count", count);
    //             mav.setStatus(HttpStatus.CREATED);
    //             mav.setViewName("index2");
    //             return mav;
    // }

}

