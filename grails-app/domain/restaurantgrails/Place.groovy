package restaurantgrails

class Place {

    String tableNumber
    String pricePerHour

    static hasMany = [placeBookings: PlaceBooking]
//    PlaceBooking placeBooking

    static constraints = {
    }
}
