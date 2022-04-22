package sg.edu.nus.iss.day22WS;

import static org.junit.Assert.*;

import java.util.Calendar;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.iss.day22WS.Repository.RSVPRepository;
import sg.edu.nus.iss.day22WS.model.RSVP;
import sg.edu.nus.iss.day22WS.service.RSVPService;

@SpringBootTest
public class TestRsvpService {

    @Autowired 
    private RSVPService rSvc;

    @Autowired 
    private RSVPRepository rRepo;

    private MultiValueMap<String, String> form;

    public TestRsvpService() {
        form = new LinkedMultiValueMap<String, String>();
        form.add("name", "barney");
        form.add("email", "barney@gmail.com");
        form.add("phone", "555-12345");
        form.add("comments", "new friend");
        form.add("confirmationDate", "2022-01-01");
	}

    @BeforeEach
    public void setup(){
        rRepo.addRsvp(form);
    }

    @AfterEach
	public void tearDown() {
		rRepo.deleteRsvpByEmail(form.getFirst("email"));
	}

    @Test
	void insertBarneyShouldPass() {
		boolean bool = rSvc.addRsvp(form);
		
        if (bool == true){
            assertTrue(true);
            return;
        }
		fail("Cannot add");
	}

    @Test
    void getAllShouldEqualCount(){
        List<RSVP> list = rSvc.getAllRsvps();
        int count = list.size();
        Optional<Integer> getCount = rSvc.getNumberOfRsvp();

        if(getCount.isPresent()){
            int count2 = getCount.get();
            if(count == count2){
                assertTrue(true);
                return;
            }else{
                fail("Count not accurate.");
            }
        }

    }
    
}
