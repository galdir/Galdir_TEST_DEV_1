package backend

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*
import org.grails.web.json.*

class CompanyStocksSTDController {

    CompanyService companyService

    static responseFormats = ['json', 'xml']

    //static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        //respond companyService.list(params), model:[companyCount: companyService.count()]

        //respond '[{"name":"Afghanistan","code":"AF"},{"name":"Aland Islands","code":"AX"},{"name":"Albania","code":"AL"}]'

        def companies = companyService.list(params)     
        def companyCount= companyService.count()
        def companiesStd = new JSONArray()
        def cJson 
        def cStockStd
        double sum , standardDeviation, mean ;
        for( int i=0;i<companyCount;i++){
            sum = 0.0;
            standardDeviation = 0.0;
            mean = 0.0;
            cJson = new JSONObject()
            cJson.put("name",companies[i].name)
            cJson.put("segment",companies[i].segment)
            
            //calculates company stocks STD
            int stocksLen=companies[i].stocks.size()

            for(int k=0;k<stocksLen;k++){
                sum += companies[i].stocks[k].price
            }

            mean = sum/stocksLen;

            for(int k=0;k<stocksLen;k++){
                standardDeviation += Math.pow(companies[i].stocks[k].price - mean ,2)
            }

            standardDeviation=Math.sqrt(standardDeviation/stocksLen);
            standardDeviation=Math.round(standardDeviation * 100.0) / 100.0

            cJson.put("stocksSTD",standardDeviation)
            companiesStd.put(i, cJson)
        }
        
        respond companiesStd
        
    }

    def show(Long id) {
        respond companyService.get(id)
    }

    def create() {
        respond new Company(params)
    }

    def save(Company company) {
        if (company == null) {
            notFound()
            return
        }

        try {
            companyService.save(company)
        } catch (ValidationException e) {
            respond company.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'company.label', default: 'Company'), company.id])
                redirect company
            }
            '*' { respond company, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond companyService.get(id)
    }

    def update(Company company) {
        if (company == null) {
            notFound()
            return
        }

        try {
            companyService.save(company)
        } catch (ValidationException e) {
            respond company.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'company.label', default: 'Company'), company.id])
                redirect company
            }
            '*'{ respond company, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        companyService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'company.label', default: 'Company'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'company.label', default: 'Company'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
