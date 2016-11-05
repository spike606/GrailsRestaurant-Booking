package restaurantgrails

class UserAccount {

    String login
    String firstName
    String lastName
    String email
    String telephone

    static hasMany = [placeBookings:PlaceBooking]

    static constraints = {
    }
}
