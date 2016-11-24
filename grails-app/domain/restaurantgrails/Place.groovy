package restaurantgrails

import groovy.transform.ToString

@ToString(includes=['tableNumber', 'pricePerHour'], includeNames=true, includePackage=false)
class Place {

    String tableNumber
    String pricePerHour

    static constraints = {
//        tableNumber readonly:true
    }
}
