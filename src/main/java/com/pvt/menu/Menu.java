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
            System.out.println("*****Для добавления,поиска,удаления Employee наберите 1");
            System.out.println("*****Для перезаписи,поиска EmployeeDetail наберите 2");
            System.out.println("*****Для добавления,поиска,удаления Department наберите 3");
            System.out.println("*****Для добавления,поиска,удаления Meeting наберите 4");
            System.out.println("*****Для выхода наберите 5");
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
            System.out.println("*****Для добавления Employee наберите 1");
            System.out.println("*****Для поиска Employee наберите 2");
            System.out.println("*****Для удаления Employee наберите 3");
            System.out.println("*****Для отправки Employee на конференцию наберите 4");
            System.out.println("*****Для возврата в предыдущее меню наберите 5");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            Employee emp=new Employee();
            System.out.println("*****введите firstname");
            emp.setFirstName(in.nextLine());
            System.out.println("*****введите lastname");
            emp.setLastName(in.nextLine());
            System.out.println("*****введите cellphone");
            emp.setCellPhone(in.nextLine());
            p = Pattern.compile("[0-9]{1,}");
            
            do{
                System.out.println("*****введите id department");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);
            Department dep=departmentDAO.getDepartment(Integer.parseInt(choice));
            if (dep==null){
                System.out.println("*****department c id="+choice+" не существует");
                employeeMenu();
            }
            emp.setDepartment(dep);
            
            EmployeeDetail eDet=new EmployeeDetail();
            System.out.println("*****введите street");
            eDet.setStreet(in.nextLine());
            System.out.println("*****введите sity");
            eDet.setSity(in.nextLine());
            System.out.println("*****введите state");
            eDet.setState(in.nextLine());
            System.out.println("*****введите country");
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
                System.out.println("*****введите id искомого Employee");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            EmployeeDAO employeeDAO = context.getBean(EmployeeDAO.class);
            Employee emp=employeeDAO.getEmployee(Integer.parseInt(choice));
            if (emp!=null){
                System.out.println("ответ="+emp.toString());
            }
            else{
                System.out.println("*****Employee с заданным id не существует");
            }
            employeeMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****введите id удаляемого Employee");
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
                System.out.println("*****введите meetingId");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            Integer meetingId=Integer.parseInt(choice);
            do{
                System.out.println("*****введите Id Employee, отправляемого на конференцию");
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
            System.out.println("*****Для обновления EmployeeDetail наберите 1");
            System.out.println("*****Для поиска EmployeeDetail по Id Employee наберите 2");
            System.out.println("*****Для возврата в предыдущее меню наберите 3");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            int id;
            do{
                System.out.println("*****введите id Employee");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            id=Integer.parseInt(choice);
            EmployeeDetailDAO employeeDetailDAO = context.getBean(EmployeeDetailDAO.class);
            EmployeeDetail eDet=employeeDetailDAO.getEmployeeDetail(id);
            if (eDet!=null){
                in=new Scanner(System.in);
                System.out.println("*****введите street");
                eDet.setStreet(in.nextLine());
                System.out.println("*****введите sity");
                eDet.setSity(in.nextLine());
                System.out.println("*****введите state");
                eDet.setState(in.nextLine());
                System.out.println("*****введите country");
                eDet.setCountry(in.nextLine());
                System.out.println(eDet);
                
                employeeDetailDAO.updateEmployeeDetail(eDet);
                //Factory.getInstance().getEmployeeDetailDAO().updateEmployeeDetail(eDet);
                System.out.println("*****EmployeeDetail с id="+id+" перезаписан");
            }
            else{
                System.out.println("*****Employee с заданным id не существует");
            }
            employeeDetailMenu();
        }
        if (choice.equalsIgnoreCase("2")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            int id;
            do{
                System.out.println("*****введите id Employee");
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
                System.out.println("*****объекта с заданным id не существует");
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
            System.out.println("*****Для добавления Department наберите 1");
            System.out.println("*****Для поиска Department по id наберите 2");
            System.out.println("*****Для удаления Department наберите 3");
            System.out.println("*****Для возврата в предыдущее меню наберите 4");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            Department dep=new Department();
            System.out.println("*****введите departmentName");
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
                System.out.println("*****введите id department");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            DepartmentDAO departmentDAO = context.getBean(DepartmentDAO.class);
            Department dep=departmentDAO.getDepartment(Integer.parseInt(choice));
            if (dep!=null){
                System.out.println(dep.toString());
            }
            else{
                System.out.println("*****department с заданным id не существует");
            }
            departmentMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****введите id удаляемого department");
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
            System.out.println("*****Для добавления Meeting наберите 1");
            System.out.println("*****Для поиска Meeting по id наберите 2");
            System.out.println("*****Для удаления Meeting наберите 3");
            System.out.println("*****Для возврата в предыдущее меню наберите 4");
            choice = in.nextLine();
            m = p.matcher(choice);
        }while(!m.matches());
        if (choice.equalsIgnoreCase("1")){
            in=new Scanner(System.in);
            Meeting meet=new Meeting();
            System.out.println("*****введите название конференции");
            meet.setTopic(in.nextLine());
            MeetingDAO meetingDAO = context.getBean(MeetingDAO.class);
            meetingDAO.addMeeting(meet);
            meetingMenu();
        }
        if (choice.equalsIgnoreCase("2")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****введите meetingId");
                choice = in.nextLine();
                m = p.matcher(choice);
            } while(!m.matches());
            MeetingDAO meetingDAO = context.getBean(MeetingDAO.class);
            Meeting meet=meetingDAO.getMeeting(Integer.parseInt(choice));
            if (meet!=null){
                System.out.println(meet.toString());
            }
            else{
                System.out.println("*****meeting с заданным id не существует");
            }
            meetingMenu();
        }
        if (choice.equalsIgnoreCase("3")){
            in=new Scanner(System.in);
            p = Pattern.compile("[0-9]{1,}");
            do{
                System.out.println("*****введите id удаляемого meeting");
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
    
    
    
        
