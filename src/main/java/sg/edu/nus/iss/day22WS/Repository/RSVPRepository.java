package sg.edu.nus.iss.day22WS.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.util.MultiValueMap;

import sg.edu.nus.iss.day22WS.model.RSVP;
import static sg.edu.nus.iss.day22WS.Repository.Queries.*;

@Repository
public class RSVPRepository {

    @Autowired
    private JdbcTemplate template;

    public List<RSVP> getAllRsvps() {

        final List<RSVP> list = new LinkedList<>();

        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_ALL_RSVPS);
        while (rs.next()) {
            RSVP rsvp = RSVP.create(rs);
            list.add(rsvp);
        }

        return list;
    }

    public List<RSVP> getRsvpByName(String name) {
        
        final List<RSVP> list = new LinkedList<>();


        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_RSVP_BY_NAME, name);
        while (rs.next()) {
            RSVP rsvp = RSVP.create(rs);
            list.add(rsvp);
        }

        return list;
    }

    public boolean addRsvp(MultiValueMap<String, String> form) {
        String email = form.getFirst("email");
        final SqlRowSet rs = template.queryForRowSet(SQL_SELECT_RSVP_BY_EMAIL, email);
        int added;

        if(rs.next()){
            added = template.update(SQL_UPDATE_RSVP, form.getFirst("name"), form.getFirst("phone"), form.getFirst("confirmationDate"), form.getFirst("comments"), email); 
        }
        else{
        added = template.update(SQL_INSERT_RSVP, email, form.getFirst("name"), form.getFirst("phone"), form.getFirst("confirmationDate"), form.getFirst("comments"));
        }

        
        return added > 0;
    }

    public Optional<Integer> getNumberOfRsvp() {
        final SqlRowSet rs = template.queryForRowSet(SQL_COUNT_ALL_RSVP);
        Integer count;
        if(rs.next()){
           count = rs.getInt("count");
           return Optional.of(count);
        }
        return Optional.empty();
    }

}
