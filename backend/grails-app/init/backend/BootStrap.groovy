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
    Calendar cActual = Calendar.getInstance()   
    Calendar cFim = Calendar.getInstance()   

    


    Random r = new Random()
    int days=0

    def init = { servletContext ->
        Company nitryx = companyService.save("Nitryx","AI")
        Company gerdau = companyService.save("Gerdau","metallurgy")
        Company hapvida = companyService.save("Hapvida","health")

        date = new Date(System.currentTimeMillis())
        ca.setTime(date) 
        cFim.setTime(date)
        cFim.add(Calendar.DATE,-30)

        while (ca.getTime()>cFim.getTime()){
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
