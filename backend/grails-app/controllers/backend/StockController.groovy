package backend

import grails.validation.ValidationException
import static org.springframework.http.HttpStatus.*

class StockController {

    StockService stockService

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond stockService.list(params), model:[stockCount: stockService.count()]
    }

    def show(Long id) {
        respond stockService.get(id)
    }

    def create() {
        respond new Stock(params)
    }

    def save(Stock stock) {
        if (stock == null) {
            notFound()
            return
        }

        try {
            stockService.save(stock)
        } catch (ValidationException e) {
            respond stock.errors, view:'create'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'stock.label', default: 'Stock'), stock.id])
                redirect stock
            }
            '*' { respond stock, [status: CREATED] }
        }
    }

    def edit(Long id) {
        respond stockService.get(id)
    }

    def update(Stock stock) {
        if (stock == null) {
            notFound()
            return
        }

        try {
            stockService.save(stock)
        } catch (ValidationException e) {
            respond stock.errors, view:'edit'
            return
        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'stock.label', default: 'Stock'), stock.id])
                redirect stock
            }
            '*'{ respond stock, [status: OK] }
        }
    }

    def delete(Long id) {
        if (id == null) {
            notFound()
            return
        }

        stockService.delete(id)

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'stock.label', default: 'Stock'), id])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'stock.label', default: 'Stock'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
