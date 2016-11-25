package restaurantgrails

import java.time.LocalDate
import java.time.LocalTime

class PlaceBooking {

    Date date
    String hourStart
//    String hourStart
    String hourStop
    Place place
    static belongsTo = [user:User]//enabling cascade delete - when removing user booking will be also removed

//    static belongsTo = [place:Place]

    static constraints = {
        date nullable: true
        hourStart nullable: true
        hourStop nullable: true
        place nullable: true
        user nullable: true

    }
}
