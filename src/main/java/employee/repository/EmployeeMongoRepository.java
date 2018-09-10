package employee.repository;

import employee.data.EmployeeMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "employees")
public interface EmployeeMongoRepository extends MongoRepository<EmployeeMongo, Long> {
    Optional<EmployeeMongo> findByEmployeeId(Long employeeId);
    Optional<EmployeeMongo> findBy_id(String employeeId);
    void deleteBy_id(String employeeId);
    void deleteByEmployeeId(Long employeeId);
}
