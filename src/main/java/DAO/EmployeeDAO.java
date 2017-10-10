package DAO;
import com.pvt.relationships.Employee;
public interface EmployeeDAO {
    public void addEmployee(Employee e);
    public Employee getEmployee(Integer employeeId);
    public void deleteEmployee(Integer employeeId);
    void sendEmployeeToMeeting(Integer employeeId, Integer meetingId);
}
