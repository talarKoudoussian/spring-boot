package company.repository;

import company.data.CompanyJPA;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyJPARepository extends CrudRepository<CompanyJPA, Long>{
}