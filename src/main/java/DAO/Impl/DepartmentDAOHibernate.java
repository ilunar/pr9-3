
package DAO.Impl;

import DAO.DepartmentDAO;
import com.pvt.relationships.Department;
import org.hibernate.*;

public class DepartmentDAOHibernate implements DepartmentDAO {

    private SessionFactory sessionFactory;
    
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    public void addDepartment(Department dep) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            session.persist(dep);
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.out.println("can't addDepartment,"+e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }

    public Department getDepartment(Integer departmentId){
        Session session = null;
        Department dep = null;
        try {
            session = sessionFactory.openSession();
            dep = (Department) session.get(Department.class, departmentId);
            if (dep!=null){
                Hibernate.initialize(dep.getEmployees());
            }
        } catch (HibernateException e) {
            System.out.println("can't getDepartment,"+e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
        return dep;
    }

    public void deleteDepartment(Integer departmentId) {
        Session session = null;
        try {
            session = sessionFactory.openSession();
            session.beginTransaction();
            Department dep = (Department) session.get(Department.class, departmentId);
            if (dep!=null){
                session.delete(dep);
                System.out.println("*****Department with a id="+departmentId+" delete");
            }
            else {System.out.println("*****incorrect departmentId");}
            session.getTransaction().commit();
        } catch (HibernateException e) {
            session.getTransaction().rollback();
            System.out.println("can't deleteDepartment,"+ e);
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }
    }
}
