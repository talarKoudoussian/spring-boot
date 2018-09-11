package company.service;

import company.data.CompanyJPA;
import company.repository.CompanyJPARepository;
import employee.utility.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service("companyService")
public class CompanyServiceImpl implements CompanyService {

    @Autowired
    CompanyJPARepository companyJPARepository;

    @Override
    public List<Object> getAllCompanies(int version) {

        List<Object> companiesList = new ArrayList<>();

        switch (version){
            case 1:
            default: {
                List<CompanyJPA> companies = getAllCompaniesJPA();
                for (CompanyJPA company: companies) {
                    companiesList.add(company);
                }
                break;
            }
        }
        return companiesList;
    }

    private List<CompanyJPA> getAllCompaniesJPA() {
        List<CompanyJPA> companies =  new ArrayList<>();

        Iterable<CompanyJPA> allCompanies = companyJPARepository.findAll();
        allCompanies.forEach(companies::add);

        return companies;
    }

    @Override
    public Object getCompany(String id, int version) {
        Object selectedCompany = null;

        switch (version){
            case 1:
            default: {
                selectedCompany = getCompanyJPA(id);
                break;
            }
        }

        return selectedCompany;
    }

    private CompanyJPA getCompanyJPA(String id) {
        CompanyJPA selectedCompany = null;

        if(id.matches("\\d+")){
            Long compId = Long.valueOf(id);
            Optional<CompanyJPA> companyOpt = companyJPARepository.findById(compId);

            if(companyOpt.isPresent()){
                selectedCompany = companyOpt.get();
            }
        }
        return selectedCompany;
    }

    @Override
    public Object addCompany(CompanyJPA company, int version) {
        Object addedCompany = null;

        switch (version) {
            case 1:
            default:
                addedCompany = addedCompanyJPA(company);
                break;
        }

        return addedCompany;
    }

    private CompanyJPA addedCompanyJPA(CompanyJPA companyJPA) {
        CompanyJPA company = null;

        String dateNow = new Date().dateNowToIso();
        companyJPA.setAddedDate(dateNow);

        if(!(companyJPA.isNameEmpty() || companyJPA.isStatusEmpty())) {
            company = companyJPARepository.save(companyJPA);
        }

        return company;
    }

    @Override
    public Object updateCompany(String id, CompanyJPA company, int version) {
        Object updatedCompany = null;

        switch (version) {
            case 1:
            default:
                updatedCompany = updateCompanyJPA(id, company);
                break;
        }

        return updatedCompany;
    }

    private CompanyJPA updateCompanyJPA(String id, CompanyJPA companyJPA) {
        CompanyJPA updatedCompany = null;

        if(id.matches("\\d+")) {
            Long compId = Long.valueOf(id);
            Optional<CompanyJPA> companyOpt = companyJPARepository.findById(compId);

            if(companyOpt.isPresent()) {

                if(!(companyJPA.isNameEmpty() || companyJPA.isStatusEmpty())) {
                    CompanyJPA selectedCompany = companyOpt.get();
                    selectedCompany.setName(companyJPA.getName());
                    selectedCompany.setLocation(companyJPA.getLocation());
                    selectedCompany.setPhoneNumber(companyJPA.getPhoneNumber());
                    selectedCompany.setStatus(companyJPA.getStatus());

                    updatedCompany = companyJPARepository.save(selectedCompany);

                }
            }
        }

        return updatedCompany;
    }

    @Override
    public boolean deleteCompany(String id, int version) {
        boolean isCompanyDeleted = false;

        switch (version) {
            case 1:
            default:
                isCompanyDeleted = deleteCompanyJPA(id);
                break;
        }

        return  isCompanyDeleted;
    }

    private boolean deleteCompanyJPA(String id) {
        boolean isCompanyDeleted = false;

        if(id.matches("\\d+")) {
            Long compId = Long.valueOf(id);
            boolean isExistingCompany = companyJPARepository.existsById(compId);

            if(isExistingCompany) {
                companyJPARepository.deleteById(compId);
                isCompanyDeleted = true;
            }
        }

        return isCompanyDeleted;
    }

    @Override
    public Object updatePartialCompany(String id, CompanyJPA company, int version) {
        Object updatedCompany = null;

        switch (version) {
            case 1:
            default:
                updatedCompany = updatePartialCompanyJPA(id, company);
                break;
        }

        return updatedCompany;
    }

    private CompanyJPA updatePartialCompanyJPA(String id, CompanyJPA companyJPA) {
        CompanyJPA updatedCompany = null;

        if(id.matches("\\d+")) {
            Long compId = Long.valueOf(id);
            Optional<CompanyJPA> companyOpt = companyJPARepository.findById(compId);

            if(companyOpt.isPresent()) {
                CompanyJPA selectedCompany = companyOpt.get();

                if(!companyJPA.isNameEmpty()) {
                    selectedCompany.setName(companyJPA.getName());
                }

                if(!companyJPA.isLocationEmpty()) {
                    selectedCompany.setLocation(companyJPA.getLocation());
                }

                if(!companyJPA.isPhoneNumberEmpty()) {
                    selectedCompany.setPhoneNumber(companyJPA.getPhoneNumber());
                }

                if(!companyJPA.isStatusEmpty()) {
                    selectedCompany.setStatus(companyJPA.getStatus());
                }

                updatedCompany = companyJPARepository.save(selectedCompany);
            }
        }

        return updatedCompany;
    }


}
