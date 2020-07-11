package backend

class Stock {

    static belongsTo = [company: Company]
    double price
    Date priceDate

    static constraints = {


    }
}
