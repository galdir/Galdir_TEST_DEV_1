import backend.*
import java.util.Calendar


String companyName='Nitryx'
 def companyNitryx = Company.findByName(companyName)

println companyNitryx.name
println companyNitryx.segment

println 'last stocks'

Date date = new Date(System.currentTimeMillis())
Calendar ca = Calendar.getInstance()
Calendar cStock = Calendar.getInstance()
ca.setTime(date)  

//println 'date from now calendar ' + ca.getTime()
//println 'hour from now calendar ' + ca.get(Calendar.HOUR_OF_DAY)

def stocks=Stock.where {company == companyNitryx}.findAll()

println stocks.size

ca.add(Calendar.HOUR_OF_DAY,-30)
ca.set(Calendar.MINUTE,0)
//lastHour=ca.get(Calendar.HOUR_OF_DAY)

cStock.setTime(stocks[0].priceDate)
//stockHour=cStock.get(Calendar.HOUR_OF_DAY)

i=0
while(cStock.getTime()>=ca.getTime()){
    println stocks[i].priceDate
    println stocks[i].price
   // println i        
    i++
    
    cStock.setTime(stocks[i].priceDate)
    stockHour=cStock.get(Calendar.HOUR_OF_DAY)
    
    //cStock.setTime(stocks[i].priceDate)
    //println 'calendar from stock ' + cStock.getTime()
    //println 'hour from stock calendar' + cStock.get(Calendar.HOUR_OF_DAY)
    //println 'hour from now calendar' + ca.get(Calendar.HOUR_OF_DAY)
    //if(ca.get(Calendar.HOUR_OF_DAY)!=cStock.get(Calendar.HOUR_OF_DAY)){
    //    ca.add(Calendar.HOUR_OF_DAY,-1)
    //    hours++
    //    println 'hora incrementada'
    //}
}

CompanyStocksService css=new CompanyStocksService()

css.getStocks("Nitryx",2)

