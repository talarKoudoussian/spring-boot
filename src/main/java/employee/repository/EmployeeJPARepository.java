package employee.repository;

import employee.data.EmployeeJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeJPARepository extends CrudRepository<EmployeeJPA, Long> {

}
