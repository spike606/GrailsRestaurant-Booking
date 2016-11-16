import restaurantgrails.Place
import restaurantgrails.Role
import restaurantgrails.User
import restaurantgrails.UserRole

class BootStrap {

    def init = { servletContext ->

        def adminRole = new Role(authority: 'ROLE_ADMIN').save()
        def userRole = new Role(authority: 'ROLE_USER').save()

        def user = new User(username: 'user', password: 'user', email: 'user@user.com',
                enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,
                    firstName: 'user', lastName: 'user', telephone: '000000000').save()
        def admin = new User(username: 'admin', password: 'admin', email: 'admin@admin.com',
                enabled: true, accountExpired: false, accountLocked: false, passwordExpired: false,
                firstName: 'admin', lastName: 'admin', telephone: '999999999').save()

        UserRole.create(user, userRole)
        UserRole.create(admin, adminRole)
        UserRole.withSession {
            it.flush()
            it.clear()
        }

        Place.findOrSaveWhere(tableNumber: "1", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "2", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "3", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "4", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "5", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "6", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "7", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "8", pricePerHour: "100")
        Place.findOrSaveWhere(tableNumber: "9", pricePerHour: "100")

    }
    def destroy = {
    }
}
