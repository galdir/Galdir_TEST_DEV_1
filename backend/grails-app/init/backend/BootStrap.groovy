package backend

import groovy.transform.CompileStatic
import java.text.SimpleDateFormat
import java.util.Calendar

@CompileStatic
class BootStrap {
    CompanyService companyService
    StockService stockService
    Date date
    Calendar ca = Calendar.getInstance()   
    

    //String pattern = 'yyyy-MM-dd hh:mm:ss'
    //String input = "2020-07-10 11:01:00"
    //Date date = new SimpleDateFormat(pattern).parse(input)
    Random r = new Random()
    int days=0
    //Date date = new Date(System.currentTimeMillis())

    def init = { servletContext ->
        Company nitryx = companyService.save("Nitryx","AI")
        Company gerdau = companyService.save("Gerdau","metallurgy")
        Company hapvida = companyService.save("Hapvida","health")

        //Stock s= new Stock(nitryx, 200.0d, date)
        date = new Date(System.currentTimeMillis())
        ca.setTime(date) 
        while (days<30){
            if(ca.get(Calendar.DAY_OF_WEEK)>1 && ca.get(Calendar.DAY_OF_WEEK)<7){
                if(ca.get(Calendar.HOUR_OF_DAY)>=10 && ca.get(Calendar.HOUR_OF_DAY)<18){            
                    double randomValue = 100 + (200-100) * r.nextDouble();
                    stockService.save(nitryx, randomValue, date)
                    randomValue = 5 + (8 - 5) * r.nextDouble();
                    stockService.save(gerdau, randomValue, date)
                    randomValue = 4 + (7 -4 ) * r.nextDouble();
                    stockService.save(hapvida, randomValue, date)        
                }
            }
            
            Calendar cActual = Calendar.getInstance()
            cActual.setTime(ca.getTime())
            
            ca.add(Calendar.MINUTE, -5)

            date=ca.getTime()
            
            if(cActual.get(Calendar.DAY_OF_MONTH)!=ca.get(Calendar.DAY_OF_MONTH)){
                System.out.println("Boostrapped Stocks of day "+ (-days))
                days++
            }
                    

        }

    }
    def destroy = {
    }
}
