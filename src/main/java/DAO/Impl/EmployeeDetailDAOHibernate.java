
package DAO.Impl;

import DAO.EmployeeDetailDAO;
import com.pvt.relationships.EmployeeDetail;
import org.hibernate.*;
import java.util.logging.*;


public class EmployeeDetailDAOHibernate implements EmployeeDetailDAO {
    private static final Logger log=Logger.getAnonymousLogger();
    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public EmployeeDetail getEmployeeDetail(Integer employeeId){
        Session session = null;
        EmployeeDetail eD = null;
        try {
            session = sessionFactory.openSession();
            eD = (EmployeeDetail) session.get(EmployeeDetail.class, employeeId);
        } catch (HibernateException e) {
            log.log(Level.WARNING,"can't getEmployeeDetail", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return eD;
    }

    public void updateEmployeeDetail(EmployeeDetail eD){
        Session session = null;
        try{
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.update(eD);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            log.log(Level.WARNING,"can't updateEmployeeDetail", e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
