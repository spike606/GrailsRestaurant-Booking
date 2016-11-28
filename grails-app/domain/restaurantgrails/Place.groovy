package restaurantgrails

import groovy.transform.ToString

//@ToString(includes=['tableNumber', 'pricePerHour'], includeNames=true, includePackage=false)
class Place {

    String tableNumber
    int  pricePerHour

    static constraints = {
        pricePerHour blank:false, validator: {return it > 0}
    }

    String toString(){
        return tableNumber + " (" + pricePerHour + " PLN)"
    }
}
