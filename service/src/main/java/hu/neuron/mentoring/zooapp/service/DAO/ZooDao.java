package hu.neuron.mentoring.zooapp.service.DAO;

import hu.neuron.mentoring.zooapp.service.Connection.ConnectionManager;
import hu.neuron.mentoring.zooapp.service.Director;
import hu.neuron.mentoring.zooapp.service.Zoo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
@Component
public class ZooDao implements Dao<Zoo>{

    private Connection conn;
    private PreparedStatement myStmt = null;
    private ResultSet zooResult = null;

    @Autowired
    public ZooDao(){
    }
    public void connect(Connection conn){
        this.conn = conn;
    }

    @Override
    public List<Zoo> findById(int id) {
        return null;

    }

    @Override
    public List<Zoo> getAll() {

        List<Zoo> zooList = new ArrayList<>();
        try {
            myStmt = conn.prepareStatement("select id,name from zoo");

            zooResult = myStmt.executeQuery();
            while (zooResult.next()) {
                String name = zooResult.getString("name");
                Integer id = zooResult.getInt("id");
                Zoo zoo = new Zoo(name);
                zoo.setId(id);
                try {
                    myStmt = conn.prepareStatement("select * from employee where id = ?");

                    myStmt.setInt(1,id);
                    ResultSet employeeResult = myStmt.executeQuery();
                    while(employeeResult.next()){
                        String type = employeeResult.getString("type");
                        String employeeName = employeeResult.getString("name");
                        Date birthDate = employeeResult.getDate("birthDate");
                        Date appointmentDate = employeeResult.getDate("appointmentDate");
                        Character gender = employeeResult.getString("gender").charAt(0);
                        if (type.equals("director")){
                            zoo.setDirector(new Director(zoo.getId(),employeeName,birthDate,appointmentDate,gender));}}
                        } catch (SQLException e) {
                            throw new RuntimeException(e);
                        }
                zooList.add(zoo);
            }
            conn.commit();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return zooList;
    }

    public void save(Zoo newZoo) {
        try {


        myStmt = conn.prepareStatement("INSERT INTO zoo VALUES(?,?,?,?,?,?)");
        myStmt.setInt(1,newZoo.getId());
        myStmt.setString(2, newZoo.getName());
        myStmt.setInt(3,newZoo.getId());
        myStmt.setInt(4,newZoo.getId());
        myStmt.setInt(5,newZoo.getId());
        myStmt.setInt(6,newZoo.getId());
        myStmt.executeUpdate();
        myStmt = conn.prepareStatement("INSERT INTO employee(id,type,name,birthDate,appointmentDate,gender) VALUES(?,?,?,?,?,?)");
        myStmt.setInt(1,newZoo.getId());
        myStmt.setString(2, "director");
        myStmt.setString(3,newZoo.getDirector().getName());
        myStmt.setDate(4,newZoo.getDirector().getBirthDate());
        myStmt.setDate(5,newZoo.getDirector().getAppointmentDate());
        myStmt.setString(6, String.valueOf(newZoo.getDirector().getGender()));
        myStmt.executeUpdate();
        conn.commit();
        }catch (SQLException e){
            throw new RuntimeException(e);
        }
    }

    @Override
    public void update(Zoo o, String[] params) {
    }

    @Override
    public void delete(Zoo zoo) {
        try {
        myStmt = conn.prepareStatement("SELECT id from reservation where zooID = ?");
        myStmt.setInt(1,zoo.getId());
        ResultSet reservationResult = myStmt.executeQuery();
        while(reservationResult.next()){
            Integer ticketID = reservationResult.getInt("id");
            myStmt = conn.prepareStatement("DELETE FROM ticket where id = ?");
            myStmt.setInt(1,ticketID);
            myStmt.executeUpdate();
        }
        myStmt = conn.prepareStatement("DELETE from reservation where zooID = ?");
        myStmt.setInt(1,zoo.getId());
        myStmt.executeUpdate();
        myStmt = conn.prepareStatement("DELETE from animal where id = ?");
        myStmt.setInt(1,zoo.getId());
        myStmt.executeUpdate();
        myStmt = conn.prepareStatement("DELETE from ticket where id = ");
        myStmt = conn.prepareStatement("DELETE from employee where id = ?");
        myStmt.setInt(1,zoo.getId());
        myStmt.executeUpdate();
        myStmt = conn.prepareStatement("DELETE from zoo where id = ?");
        myStmt.setInt(1,zoo.getId());
        myStmt.executeUpdate();
        conn.commit();
        }catch (SQLException e){
        throw new RuntimeException(e);}
    }

    public static List<String> filterListByTerm(List<String> list, String term) {

        List<String> matching = list.stream()
                .filter(e -> e.toLowerCase().startsWith(term))
                .collect(Collectors.toList());

        return matching;
    }
    public List<String> zooNames(){

        List<String> list = new ArrayList<>();

        for (Zoo zoo : this.getAll()){
            list.add(zoo.getName());
        }

        return list;
    }
}
