package employee.repository;

import employee.data.EmployeeJPA;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeJPARepository extends CrudRepository<EmployeeJPA, Long> {

    @Query(value = "SELECT employee_id, first_name, last_name, added_date, employment_status, datasource, company_id  FROM employee WHERE  company_id = :company_id", nativeQuery = true)
    List<EmployeeJPA> getEmployees(@Param("company_id") Long companyId);

    @Query(value = "SELECT * FROM employee WHERE company_id = :company_id AND employee_id = :employee_id", nativeQuery = true)
    Optional<EmployeeJPA> getEmployee(@Param("company_id") Long companyId, @Param("employee_id") Long employeeId);
}
