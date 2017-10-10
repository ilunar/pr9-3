package DAO;
import com.pvt.relationships.EmployeeDetail;
public interface EmployeeDetailDAO {
    public EmployeeDetail getEmployeeDetail(Integer employeeId);
    public void updateEmployeeDetail(EmployeeDetail eD);
}
