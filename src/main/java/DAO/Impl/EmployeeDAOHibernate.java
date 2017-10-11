package DAO.Impl;

import DAO.EmployeeDAO;
import com.pvt.relationships.*;
import java.util.logging.*;
import org.hibernate.*;

public class EmployeeDAOHibernate implements EmployeeDAO {
    private static final Logger log=Logger.getAnonymousLogger();
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void addEmployee(Employee emp){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.save(emp);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.WARNING,"can't addEmployee", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Employee getEmployee(Integer employeeId){
        Session session = null;
        Employee emp = null;
        String hql="from Employee E where E.employeeId=:employeeId";
        try {
            session = sessionFactory.openSession();
            Query q=session.createQuery(hql);
            q.setParameter("employeeId", employeeId);
            emp = (Employee)q.uniqueResult();
            if(emp!=null){
                Hibernate.initialize(emp.getMeetings());
                Hibernate.initialize(emp.getDepartment());
            }
        } catch (HibernateException e) {
            log.log(Level.WARNING,"can't getEmployee", e);
            session.getTransaction().rollback();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return emp;
    }
    
    public void deleteEmployee(Integer employeeId){
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Employee emp = (Employee) session.get(Employee.class, employeeId);
            if (emp!=null){
                session.delete(emp);
                System.out.println("*****Employee with a id="+employeeId+" delete");
            }
            else {System.out.println("*****incorrect employeeId");}
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.WARNING,"can't deleteEmployee", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
    public void sendEmployeeToMeeting(Integer employeeId, Integer meetingId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Meeting meet= (Meeting) session.get(Meeting.class, meetingId);
            Employee emp = (Employee) session.get(Employee.class, employeeId);
            if (emp!=null&& meet!=null){
                emp.getMeetings().add(meet);
                System.out.println("*****Employee with a id="+employeeId+" send to meeting with a id= "+meetingId);
            }
            else {System.out.println("*****incorrect employeeId or meetingId");}
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            log.log(Level.WARNING,"can't sendEmployeeToMeeting", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
