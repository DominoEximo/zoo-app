package hu.neuron.mentoring.zooapp.beans;

import hu.neuron.mentoring.zooapp.core.entity.Cleaner;
import hu.neuron.mentoring.zooapp.core.entity.Employee;
import hu.neuron.mentoring.zooapp.core.entity.GondoZoo;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.CleanerDaoService;
import hu.neuron.mentoring.zooapp.service.daoservice.DaoService.GondoZooDaoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.context.annotation.SessionScope;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Controller("employeeView")
@SessionScope
public class EmployeeView implements Serializable {

    @Autowired
    private GondoZooDaoService gondoZooDaoService;
    @Autowired
    private CleanerDaoService cleanerDaoService;

    private Employee selectedEmployee;

    public EmployeeView() {
    }

    public GondoZooDaoService getGondoZooDaoService() {
        return gondoZooDaoService;
    }

    public void setGondoZooDaoService(GondoZooDaoService gondoZooDaoService) {
        this.gondoZooDaoService = gondoZooDaoService;
    }

    public CleanerDaoService getCleanerDaoService() {
        return cleanerDaoService;
    }

    public void setCleanerDaoService(CleanerDaoService cleanerDaoService) {
        this.cleanerDaoService = cleanerDaoService;
    }

    public Employee getSelectedEmployee() {
        return selectedEmployee;
    }

    public void setSelectedEmployee(Employee selectedEmployee) {
        this.selectedEmployee = selectedEmployee;
    }



    public List<Employee> getEmployees(Integer zooId){
        List<Employee> employees = new ArrayList<>();
        employees.addAll(cleanerDaoService.findByZoo(zooId));
        employees.addAll(gondoZooDaoService.findByZoo(zooId));
        return employees;
    }

    public void deleteEmployee(){
        if(selectedEmployee instanceof GondoZoo){
            gondoZooDaoService.delete(gondoZooDaoService.findById(selectedEmployee.getId()));
        } else if (selectedEmployee instanceof Cleaner) {
            cleanerDaoService.delete(cleanerDaoService.findById(selectedEmployee.getId()));
        }
    }
}
