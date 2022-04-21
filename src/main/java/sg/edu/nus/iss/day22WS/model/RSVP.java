package sg.edu.nus.iss.day22WS.model;

import java.util.Date;

import org.springframework.jdbc.support.rowset.SqlRowSet;

public class RSVP {
    private String name;
    private String email;
    private String phone;
    private Date confirmationDate;
    private String comments;
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getPhone() {
        return phone;
    }
    public void setPhone(String phone) {
        this.phone = phone;
    }
    public Date getConfirmationDate() {
        return confirmationDate;
    }
    public void setConfirmationDate(Date confirmationDate) {
        this.confirmationDate = confirmationDate;
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public static RSVP create(SqlRowSet rs) {
        RSVP rsvp = new RSVP();
        rsvp.setEmail(rs.getString("email"));
        rsvp.setName(rs.getString("name"));
        rsvp.setPhone(rs.getString("phone"));
        rsvp.setComments(rs.getString("comments"));
        rsvp.setConfirmationDate(rs.getDate("confirmation_date"));

        return rsvp;
    }
    
}
