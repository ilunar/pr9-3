package DAO.Impl;

import DAO.MeetingDAO;
import com.pvt.relationships.*;
import org.hibernate.*;
import java.util.logging.*;

public class MeetingDAOHibernate implements MeetingDAO {
    private static final Logger log=Logger.getAnonymousLogger();
    
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addMeeting(Meeting meet){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(meet);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.WARNING,"can't addMeeting", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Meeting getMeeting(Integer meetingId){
        Session session = null;
        Meeting meet = null;
        try {
            session = sessionFactory.openSession();
            meet = (Meeting) session.get(Meeting.class, meetingId);
            if(meet!=null){
                Hibernate.initialize(meet.getEmployees());
            }
        } catch (HibernateException e) {
            log.log(Level.WARNING,"can't getMeeting", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return meet;
    }

    public void deleteMeeting(Integer meetingId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Meeting meet = (Meeting) session.get(Meeting.class, meetingId);
            if (meet!=null){
                for (Employee emp:meet.getEmployees()){
                    emp.getMeetings().remove(meet);
                }
                session.delete(meet);
                System.out.println("*****Meeting with a id="+meetingId+" delete");
            }
            else {System.out.println("*****incorrect meetingId");}
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.WARNING,"can't deleteMeeting", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
