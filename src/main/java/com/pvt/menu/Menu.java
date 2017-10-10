package com.pvt.menu;

import DAO.*;
import com.pvt.relationships.*;
import java.util.Scanner;
import java.util.regex.*;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Menu {
    ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("myspring.xml");
    String choice;
    Scanner in=null;
    Pattern p = null;
    Matcher m = null;
    public void startMenu(){
        in=new Scanner(System.in);
        p = Pattern.compile("[1-5]{1}");
        m = null;
        do{
            System.out.println("*****ррпрпрр Employee �������� 1");
            System.out.println("*****��� ����������,������ EmployeeDetail �������� 2");
            System.out.println("*****��� ����������,������,�������� Department �������� 3");
            System.out.println("*****��� ����������,������,�������� Meeting �������� 4");
            System.out.println("*****��� ������ �������� 5");
            choice = in.nextLine();
            m = p.matcher(choice);
        } while(!m.matches());
        if (choice.equalsIgnoreCase("1")){employeeMenu();}
        if (choice.equalsIgnoreCase("2")){employeeDetailMenu();}
        if (choice.equalsIgnoreCase("3")){departmentMenu();}
        if (choice.equalsIgnoreCase("4")){meetingMenu();}
        if (choice.equalsIgnoreCase("5")){
            context.close();
            System.exit(0);
        }
    }
    
    public void employeeMenu(){
        in=new Scanner(System.in);
        p = Pattern.compile("[1-5]{1}");
        do{
            System.out.println("*****��� ���������� Employee �������� 1");
            System.out.println("*****��� ������ Employee �������� 2");
            System.out.println("*****��� �������� Employee �������� 3");
            System.out.println("*****��� �������� Employee �� ����������� �������� 4");
            System.out.println("*****��� �������� � ���������� ���� �������� 5");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            Employee emp=new Employee();
            System.out.println("*****������� firstname");
            emp.setFirstName(in.nextLine());
            System.out.println("*****������� lastname");
            emp.setLastName(in.nextLine());
            System.out.println("*****������� cellphone");
            emp.setCellPhone(in.nextLine());
            p = Pattern.compile("[0-9]{1,}");
            
            do{
                System.out.println("*****������� id department");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);
            Department dep=departmentDAO.getDepartment(Integer.parseInt(choice));
            if (dep==null){
                System.out.println("*****department c id="+choice+" �� ����������");
                employeeMenu();
            }
            emp.setDepartment(dep);
            
            EmployeeDetail eDet=new EmployeeDetail();
            System.out.println("*****������� street");
            eDet.setStreet(in.nextLine());
            System.out.println("*****������� sity");
            eDet.setSity(in.nextLine());
            System.out.println("*****������� state");
            eDet.setState(in.nextLine());
            System.out.println("*****������� country");
            eDet.setCountry(in.nextLine());
            emp.setEmployeeDetail(eDet);
            eDet.setEmployee(emp);
            
            EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
            employeeDAO.addEmployee(emp);
            
            employeeMenu();
            }
        if (choice.equalsIgnoreCase("2")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� id �������� Employee");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
            Employee emp=employeeDAO.getEmployee(Integer.parseInt(choice));
            if (emp!=null){
                System.out.println("�����="+emp.toString());
            }
            else{
                System.out.println("*****Employee � �������� id �� ����������");
            }
            employeeMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� id ���������� Employee");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
            employeeDAO.deleteEmployee(Integer.parseInt(choice));
            employeeMenu();
        }
        if (choice.equalsIgnoreCase("4")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� meetingId");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            Integer meetingId=Integer.parseInt(choice);
            do{
                System.out.println("*****������� Id Employee, ������������� �� �����������");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            Integer employeeId=Integer.parseInt(choice);
            
            EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
            employeeDAO.sendEmployeeToMeeting(employeeId, meetingId);
            
            employeeMenu();
        }
        if (choice.equalsIgnoreCase("5")){
            startMenu();
        }
    }
    public void employeeDetailMenu(){
        in=new Scanner(System.in);
        p = Pattern.compile("[1-3]{1}");
        do{
            System.out.println("*****��� ���������� EmployeeDetail �������� 1");
            System.out.println("*****��� ������ EmployeeDetail �� Id Employee �������� 2");
            System.out.println("*****��� �������� � ���������� ���� �������� 3");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            int id;
            do{
                System.out.println("*****������� id Employee");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            id=Integer.parseInt(choice);
            EmployeeDetailDAO employeeDetailDAO = context.getBean(EmployeeDetailDAO.class);
            EmployeeDetail eDet=employeeDetailDAO.getEmployeeDetail(id);
            if (eDet!=null){
                in=new Scanner(System.in);
                System.out.println("*****������� street");
                eDet.setStreet(in.nextLine());
                System.out.println("*****������� sity");
                eDet.setSity(in.nextLine());
                System.out.println("*****������� state");
                eDet.setState(in.nextLine());
                System.out.println("*****������� country");
                eDet.setCountry(in.nextLine());
                System.out.println(eDet);
                
                employeeDetailDAO.updateEmployeeDetail(eDet);
                //Factory.getInstance().getEmployeeDetailDAO().updateEmployeeDetail(eDet);
                System.out.println("*****EmployeeDetail � id="+id+" �����������");
            }
            else{
                System.out.println("*****Employee � �������� id �� ����������");
            }
            employeeDetailMenu();
        }
        if (choice.equalsIgnoreCase("2")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            int id;
            do{
                System.out.println("*****������� id Employee");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            id=Integer.parseInt(choice);
            EmployeeDetailDAO employeeDetailDAO = context.getBean(EmployeeDetailDAO.class);
            EmployeeDetail eDet=employeeDetailDAO.getEmployeeDetail(id);
            //EmployeeDetail eDet=Factory.getInstance().getEmployeeDetailDAO().getEmployeeDetail(id);
            if (eDet!=null){
                System.out.println("Id="+id+" "+eDet.toString());
            }
            else{
                System.out.println("*****������� � �������� id �� ����������");
            }
            employeeDetailMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            startMenu();
        }
    }
    public void departmentMenu(){
        in=new Scanner(System.in);
        p = Pattern.compile("[1-4]{1}");
        do{
            System.out.println("*****��� ���������� Department �������� 1");
            System.out.println("*****��� ������ Department �� id �������� 2");
            System.out.println("*****��� �������� Department �������� 3");
            System.out.println("*****��� �������� � ���������� ���� �������� 4");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            Department dep=new Department();
            System.out.println("*****������� departmentName");
            dep.setDepartmentName(in.nextLine());
            DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);
            departmentDAO.addDepartment(dep);
            //Factory.getInstance().getDepartmentDAO().addDepartment(dep);
            departmentMenu();
        }
        if (choice.equalsIgnoreCase("2")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� id department");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);
            Department dep=departmentDAO.getDepartment(Integer.parseInt(choice));
            if (dep!=null){
                System.out.println(dep.toString());
            }
            else{
                System.out.println("*****department � �������� id �� ����������");
            }
            departmentMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� id ���������� department");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);
            departmentDAO.deleteDepartment(Integer.parseInt(choice));
            //Factory.getInstance().getDepartmentDAO().deleteDepartment(Integer.parseInt(choice));
            departmentMenu();
        }
        if (choice.equalsIgnoreCase("4")){
            startMenu();
        }
    }
    public void meetingMenu(){
        in=new Scanner(System.in);
        p = Pattern.compile("[1-4]{1}");
        do{
            System.out.println("*****��� ���������� Meeting �������� 1");
            System.out.println("*****��� ������ Meeting �� id �������� 2");
            System.out.println("*****��� �������� Meeting �������� 3");
            System.out.println("*****��� �������� � ���������� ���� �������� 4");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            Meeting meet=new Meeting();
            System.out.println("*****������� �������� �����������");
            meet.setTopic(in.nextLine());
            MeetingDAO meetingDAO = context.getBean(MeetingDAO.class);
            meetingDAO.addMeeting(meet);
            meetingMenu();
        }
        if (choice.equalsIgnoreCase("2")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� meetingId");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            MeetingDAO meetingDAO = context.getBean(MeetingDAO.class);
            Meeting meet=meetingDAO.getMeeting(Integer.parseInt(choice));
            if (meet!=null){
                System.out.println(meet.toString());
            }
            else{
                System.out.println("*****meeting � �������� id �� ����������");
            }
            meetingMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****������� id ���������� meeting");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            MeetingDAO meetingDAO = context.getBean(MeetingDAO.class);
            meetingDAO.deleteMeeting(Integer.parseInt(choice));
            meetingMenu();
        }
        if (choice.equalsIgnoreCase("4")){
            startMenu();
        }
    }
}
    
    
    
        
