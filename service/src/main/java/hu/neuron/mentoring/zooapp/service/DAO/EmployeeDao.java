package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.*;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class EmployeeDao implements Dao<Employee>{

    private Connection conn;
    private PreparedStatement myStmt = null;
    private ResultSet zooResult = null;

    public EmployeeDao(Connection conn){
        this.conn = conn;
    }
    @Override
    public List<Employee> findById(int id) {
        List<Employee> employees = new ArrayList<>();
        try{
        myStmt = conn.prepareStatement("select * from employee where id = ?");
        myStmt.setInt(1,id);
        ResultSet employeeResult = myStmt.executeQuery();
        while(employeeResult.next()) {
            String type = employeeResult.getString("type");
            String employeeName = employeeResult.getString("name");
            Date birthDate = employeeResult.getDate("birthDate");
            Date appointmentDate = employeeResult.getDate("appointmentDate");
            Character gender = employeeResult.getString("gender").charAt(0);
            if (type.equals("director")) {
                employees.add(new Director(id, employeeName, birthDate, appointmentDate, gender));
            } else if (type.equals("gondoZoo")) {
                String animalsFromSQL = employeeResult.getString("suppliedAnimals");
                String[] animals = animalsFromSQL.split(" ");
                List<Species> suppliedAnimals = new ArrayList<>();
                for (String animal : animals) {
                    switch (animal.toUpperCase()) {
                        case ("TIGER"): {
                            suppliedAnimals.add(Species.TIGER);
                            break;
                        }
                        case ("LION"): {
                            suppliedAnimals.add(Species.LION);
                            break;
                        }
                        case ("BEAR"): {
                            suppliedAnimals.add(Species.BEAR);
                            break;
                        }
                        case ("GIRAFFE"): {
                            suppliedAnimals.add(Species.GIRAFFE);
                            break;
                        }
                        case ("PENGUIN"): {
                            suppliedAnimals.add(Species.PENGUIN);
                            break;
                        }
                        case ("WHALE"): {
                            suppliedAnimals.add(Species.WHALE);
                            break;
                        }
                        case ("RHINO"): {
                            suppliedAnimals.add(Species.RHINO);
                            break;
                        }
                        case ("SEAL"): {
                            suppliedAnimals.add(Species.SEAL);
                            break;
                        }
                        case ("FOX"): {
                            suppliedAnimals.add(Species.FOX);
                            break;
                        }
                        case ("WOLF"): {
                            suppliedAnimals.add(Species.WOLF);
                            break;
                        }
                        case ("PEACOCK"): {
                            suppliedAnimals.add(Species.PEACOCK);
                            break;
                        }
                    }
                }
                employees.add(new GondoZoo(id, employeeName, birthDate, appointmentDate, gender, suppliedAnimals));
            } else if (type.equals("cleaner")) {
                String cleanedAreasFromSQL = employeeResult.getString("cleanedAreas");
                String[] areas = cleanedAreasFromSQL.split(" ");
                List<CleanedArea> cleanedAreas = new ArrayList<>();

                for (String area : areas) {
                    switch (area.toUpperCase()) {
                        case ("TERRARIUM"):
                            cleanedAreas.add(CleanedArea.valueOf("TERRARIUM")); //REAFACTOR
                            break;
                        case ("POOL"):
                            cleanedAreas.add(CleanedArea.POOL);
                            break;
                        case ("CAGE"):
                            cleanedAreas.add(CleanedArea.CAGE);
                            break;
                        case ("RUNWAY"):
                            cleanedAreas.add(CleanedArea.RUNWAY);
                            break;
                    }
                }
                employees.add(new Cleaner(id, employeeName, birthDate, appointmentDate, gender, cleanedAreas));

            }
            conn.commit();
        }
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
        return employees;
    }

    @Override
    public List<Employee> getAll() {
        return null;
    }


    public void save(Employee newEmployee,String type) {
        try{
            myStmt = conn.prepareStatement("INSERT INTO employee VALUES (?,?,?,?,?,?,?,?)");
            myStmt.setInt(1,newEmployee.getId());
            myStmt.setString(2, type);
            myStmt.setString(3,newEmployee.getName());
            myStmt.setDate(4,newEmployee.getBirthDate());
            myStmt.setDate(5,newEmployee.getAppointmentDate());
            myStmt.setString(6, String.valueOf(newEmployee.getGender()));
            if (newEmployee instanceof GondoZoo){
                myStmt.setString(7, ((GondoZoo) newEmployee).getSuppliedAnimals().toString());
                myStmt.setString(8,null);
            }else if (newEmployee instanceof Cleaner){
                myStmt.setString(7,null);
                myStmt.setString(8,((Cleaner) newEmployee).getCleanedAreas().toString());
            }else {
                myStmt.setString(7,null);
                myStmt.setString(8,null);
            }
            myStmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Employee employee, String[] params) {

    }

    @Override
    public void delete(Employee employee) {
        try {
            myStmt = conn.prepareStatement("DELETE FROM employee where id = ? and name = ?");
            myStmt.setInt(1,employee.getId());
            myStmt.setString(2,employee.getName());
            myStmt.executeUpdate();
            conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }
}
