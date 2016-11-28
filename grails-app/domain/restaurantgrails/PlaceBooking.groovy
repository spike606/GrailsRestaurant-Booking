package restaurantgrails

class PlaceBooking {


    Date date
    int hourStart
    int hours
    Place place
    static belongsTo = [user:User]//enabling cascade delete - when removing user booking will be also removed

//    String toString(){
//        return
//    }

    static constraints = {
        date nullable: false
        hourStart nullable: false, range: 10..18
        hours nullable: false, range: 1..4
        place nullable: true
        user nullable: true

    }
}
