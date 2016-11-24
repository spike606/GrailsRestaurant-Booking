package restaurantgrails

class Place {

    String tableNumber
    String pricePerHour

    static constraints = {
        tableNumber readonly:true
    }
}
