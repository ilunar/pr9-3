package DAO;
import com.pvt.relationships.Meeting;
public interface MeetingDAO {
    public void addMeeting(Meeting meet); 
    public Meeting getMeeting(Integer meetingId);
    public void deleteMeeting(Integer meetingId);
}
