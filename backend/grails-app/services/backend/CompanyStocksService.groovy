package backend

import grails.gorm.transactions.Transactional

@Transactional
class CompanyStocksService {


    def getStocks(String companyName, int numbersOfHoursUntilNow){

        long start=System.currentTimeMillis()

        def companyNitryx = Company.findByName(companyName)
     
        Date date = new Date(start)
        Calendar ca = Calendar.getInstance()
        Calendar cStock = Calendar.getInstance()
        ca.setTime(date)  


        def stocks=Stock.where {company == companyNitryx}.findAll()

        ca.add(Calendar.HOUR_OF_DAY,-numbersOfHoursUntilNow)
        ca.set(Calendar.MINUTE,0)

        cStock.setTime(stocks[0].priceDate)


        int i=0
        while(cStock.getTime()>=ca.getTime() && i<stocks.size){
            println("DATE: " + stocks[i].priceDate + " PRICE: " + stocks[i].price)
            //println stocks[i].price
            i++
            
            cStock.setTime(stocks[i].priceDate)
            
        }

        long end = System.currentTimeMillis()

        println 'total time ' + start-end      

        println 'stocks retrieved ' + i
    }
}
