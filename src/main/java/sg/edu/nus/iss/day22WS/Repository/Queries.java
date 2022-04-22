package sg.edu.nus.iss.day22WS.Repository;

public interface Queries {
    public static final String SQL_SELECT_ALL_RSVPS = "select * from rsvp";
    public static final String SQL_SELECT_RSVP_BY_NAME = "select * from rsvp where name like CONCAT('%',?,'%')";
    public static final String SQL_SELECT_RSVP_BY_EMAIL = "select * from rsvp where email = ?";
    public static final String SQL_UPDATE_RSVP = "update rsvp set name = ?, phone = ?, confirmation_date = ?, comments = ? where email = ?";
    public static final String SQL_INSERT_RSVP = "insert into rsvp(email, name, phone, confirmation_date, comments) values (?, ?, ?, ?, ?)";
    public static final String SQL_COUNT_ALL_RSVP = "select count(*) as count from rsvp";
    public static final String SQL_DELETE_RSVP_BY_EMAIL = "delete from rsvp where email = ?";
}
   
