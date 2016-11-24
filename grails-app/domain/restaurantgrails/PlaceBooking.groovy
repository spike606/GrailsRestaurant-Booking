package restaurantgrails

class PlaceBooking {

    String date
    String hourStart
    String hourStop
    Place place
    static belongsTo = [user:User]//enabling cascade delete - when removing user booking will be also removed

//    static belongsTo = [place:Place]

    static constraints = {
        date nullable: true
        hourStart nullable: true
        hourStop nullable: true

    }
}
