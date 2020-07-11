package backend

import grails.testing.mixin.integration.Integration
import grails.gorm.transactions.Rollback
import spock.lang.Specification
import org.hibernate.SessionFactory

@Integration
@Rollback
class StockServiceSpec extends Specification {

    StockService stockService
    SessionFactory sessionFactory

    private Long setupData() {
        // TODO: Populate valid domain instances and return a valid ID
        //new Stock(...).save(flush: true, failOnError: true)
        //new Stock(...).save(flush: true, failOnError: true)
        //Stock stock = new Stock(...).save(flush: true, failOnError: true)
        //new Stock(...).save(flush: true, failOnError: true)
        //new Stock(...).save(flush: true, failOnError: true)
        assert false, "TODO: Provide a setupData() implementation for this generated test suite"
        //stock.id
    }

    void "test get"() {
        setupData()

        expect:
        stockService.get(1) != null
    }

    void "test list"() {
        setupData()

        when:
        List<Stock> stockList = stockService.list(max: 2, offset: 2)

        then:
        stockList.size() == 2
        assert false, "TODO: Verify the correct instances are returned"
    }

    void "test count"() {
        setupData()

        expect:
        stockService.count() == 5
    }

    void "test delete"() {
        Long stockId = setupData()

        expect:
        stockService.count() == 5

        when:
        stockService.delete(stockId)
        sessionFactory.currentSession.flush()

        then:
        stockService.count() == 4
    }

    void "test save"() {
        when:
        assert false, "TODO: Provide a valid instance to save"
        Stock stock = new Stock()
        stockService.save(stock)

        then:
        stock.id != null
    }
}
