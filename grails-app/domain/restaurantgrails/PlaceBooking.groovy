package restaurantgrails

class PlaceBooking {

    String date
    String hourStart
    String hourStop

    static belongsTo = [user:User, place:Place]//enabling cascade delete - when removing user booking will be also removed

//    static belongsTo = [place:Place]

    static constraints = {
    }
}
