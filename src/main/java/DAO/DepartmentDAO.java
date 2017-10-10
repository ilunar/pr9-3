package DAO;
import com.pvt.relationships.Department;
public interface DepartmentDAO {
    public void addDepartment(Department dep);
    public Department getDepartment(Integer departmentId);
    public void deleteDepartment(Integer departmentId);
}
