package company.service;

import company.data.CompanyJPA;

import java.util.List;

public interface CompanyService {
    public List<Object> getAllCompanies(int version);
    public Object getCompany(String id, int version);
    public Object addCompany(CompanyJPA company, int version);
    public Object updateCompany(String id, CompanyJPA company, int version);
    public boolean deleteCompany(String id, int version);
    public Object updatePartialCompany(String id, CompanyJPA company, int version);
}
