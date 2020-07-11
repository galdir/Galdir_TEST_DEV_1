package backend

import grails.gorm.services.Service

@Service(Company)
interface CompanyService {

    Company get(Serializable id)

    List<Company> list(Map args)

    Long count()

    void delete(Serializable id)

    //Company save(Company company)
    Company save(String name, String segment)

}