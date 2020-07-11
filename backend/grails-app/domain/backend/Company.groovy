package backend

class Company {

    String name
    String segment
    static hasMany = [stocks: Stock]

    static constraints = {
        name maxSize: 255, blank: false, unique: true
        segment maxSize: 255, blank: false
    }
}
