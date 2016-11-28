import restaurantgrails.Menu
import restaurantgrails.Place
import restaurantgrails.PlaceBooking
import restaurantgrails.Role
import restaurantgrails.User
import restaurantgrails.UserRole

class BootStrap {

    def init = { servletContext ->

        Menu.findOrSaveWhere(dishName: "Spicy Beef Salad", price: 120)
        Menu.findOrSaveWhere(dishName: "Crushed peas & mozzarella on toast with pecorino", price: 160)
        Menu.findOrSaveWhere(dishName: "Crispy Roast Duck with Chinese Pancakes", price: 100)
        Menu.findOrSaveWhere(dishName: "Beer Bread", price: 100)
        Menu.findOrSaveWhere(dishName: "Roasted Squash Houmous", price: 90)
        Menu.findOrSaveWhere(dishName: "Slow-cooked Beef Short Ribs", price: 125)


        Place.findOrSaveWhere(tableNumber: "1", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "2", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "3", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "4", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "5", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "6", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "7", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "8", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "9", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "10", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "11", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "12", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "13", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "14", pricePerHour: 100)
        Place.findOrSaveWhere(tableNumber: "15", pricePerHour: 100)

        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def user = new User(username: 'user', password: 'user', email: 'krystusw@gmail.com',
                enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,
                    firstName: 'user', lastName: 'user', telephone: '000000000').save()


        def admin = new User(username: 'admin', password: 'admin', email: 'krystusw@gmail.com',
                enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,
                firstName: 'admin', lastName: 'admin', telephone: '999999999').save()

        UserRole.create(user, userRole)
        UserRole.create(admin, adminRole)
        UserRole.create(admin, userRole)
        UserRole.withSession {
            it.flush()
            it.clear()
        }



//        def userBoooking = new PlaceBooking(hourStart: 12, hours: 15, date: new Date(2016,12,22), place: Place.findWhere(("tableNumber"): "9"))
//        User.findWhere("username": "user").addToPlaceBookings(userBoooking).save()



    }
    def destroy = {
    }
}
