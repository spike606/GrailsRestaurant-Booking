package restaurantgrails

class UserAccountOld {

    String login
    String firstName
    String lastName
    String email
    String telephone

    static hasMany = [placeBookings:PlaceBooking]

    static constraints = {
    }
}
