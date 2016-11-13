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
    }
    def destroy = {
    }
}
