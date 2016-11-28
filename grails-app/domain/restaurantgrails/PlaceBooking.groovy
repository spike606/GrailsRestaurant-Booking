package restaurantgrails

import java.time.LocalDate
import java.time.LocalTime

class PlaceBooking {

    Date date
    int hourStart
    int hourStop
    Place place
    static belongsTo = [user:User]//enabling cascade delete - when removing user booking will be also removed


    static constraints = {
        date nullable: false
        hourStart nullable: false, range: 10..18
        hourStop nullable: false, range: 1..4
        place nullable: true
        user nullable: true

    }
}
