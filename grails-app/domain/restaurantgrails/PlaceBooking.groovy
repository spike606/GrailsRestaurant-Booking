package restaurantgrails

class PlaceBooking {

    String date
    String hourStart
    String hourStop

    static belongsTo = [userAccountOld:UserAccountOld]//enabling cascade delete - when removing user booking will be also removed

    static hasOne = [place:Place]

    static constraints = {
    }
}
