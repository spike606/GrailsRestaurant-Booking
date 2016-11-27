package restaurantgrails

import grails.plugin.springsecurity.SpringSecurityUtils
import grails.plugin.springsecurity.annotation.Secured
import org.grails.plugins.wkhtmltopdf.WkhtmltoxService
import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Secured('ROLE_USER')
@Transactional(readOnly = true)
class PlaceBookingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def springSecurityService
    WkhtmltoxService wkhtmltoxService

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        def currentUser = User.findWhere("username": springSecurityService.authentication.principal.username)

        if(SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")){
            respond PlaceBooking.list(params), model:[placeBookingCount: PlaceBooking.count()]
        }else{
            respond PlaceBooking.findAllByUser(currentUser), model:[placeBookingCount: currentUser.placeBookings.size()]

        }
    }

    def show(PlaceBooking placeBooking) {
        respond placeBooking
    }

    def create() {
        respond new PlaceBooking(params), model:[places: Place.all]
    }


    @Transactional
    def save(PlaceBooking placeBooking) {


        if (placeBooking == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        placeBooking.place = Place.findWhere("tableNumber": params.selectedTable)
        placeBooking.hourStop = placeBooking.hourStart.toInteger() + placeBooking.hourStop.toInteger()
        placeBooking.user = User.findWhere("username": springSecurityService.authentication.principal.username)
        if (placeBooking.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond placeBooking.errors, view:'create'
            return
        }

        placeBooking.save flush:true


        def total = ((placeBooking.hourStop.toInteger() - placeBooking.hourStart.toInteger())* placeBooking.place.pricePerHour.toInteger()).toString()
        def date = placeBooking.date.getAt(Calendar.YEAR).toString() + " " + placeBooking.date.getAt(Calendar.MONTH).toString() + " "+ placeBooking.date.getAt(Calendar.DAY_OF_MONTH).toString()
        def byte[] pdfData = wkhtmltoxService.makePdf(
                view: "receipeTemplate",
                model: [placeBooking: placeBooking,
                        total: total,
                        date: date])

        sendMail {
            multipart(true)
            to "$placeBooking.user.email"
            subject "Restaurant Grails - booking confirmation"
            body 'Please see attachment.'
            attachBytes "Booking.pdf", "application/pdf", pdfData

        }

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [" - check your email ", " "])
                redirect placeBooking
            }
            '*' { respond placeBooking, [status: CREATED] }
        }
    }

    def edit(PlaceBooking placeBooking) {
        def hours = placeBooking.hourStop.toInteger() -  placeBooking.hourStart.toInteger()
        respond placeBooking, model:[places: Place.all, date: placeBooking.date, hourStart: placeBooking.hourStart,hourStop: hours, tableNumber: placeBooking.place.tableNumber]
    }

    @Transactional
    def update(PlaceBooking placeBooking) {
        if (placeBooking == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }
        placeBooking.place = Place.findWhere("tableNumber": params.selectedTable)
        placeBooking.hourStop = placeBooking.hourStart.toInteger() + placeBooking.hourStop.toInteger()
        placeBooking.user = User.findWhere("username": springSecurityService.authentication.principal.username)
        if (placeBooking.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond placeBooking.errors, view:'edit'
            return
        }

        placeBooking.save flush:true

        if(!SpringSecurityUtils.ifAllGranted("ROLE_ADMIN")){
            def total = ((placeBooking.hourStop.toInteger() - placeBooking.hourStart.toInteger())* placeBooking.place.pricePerHour.toInteger()).toString()
            def date = placeBooking.date.getAt(Calendar.YEAR).toString() + " " + placeBooking.date.getAt(Calendar.MONTH).toString() + " "+ placeBooking.date.getAt(Calendar.DAY_OF_MONTH).toString()
            def byte[] pdfData = wkhtmltoxService.makePdf(
                    view: "receipeTemplate",
                    model: [placeBooking: placeBooking,
                            total: total,
                            date: date])

            sendMail {
                multipart(true)
                to "$placeBooking.user.email"
                subject "Restaurant Grails - booking confirmation - UPDATE"
                body 'Please see attachment.'
                attachBytes "Booking.pdf", "application/pdf", pdfData

            }

        }
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: ["", " "])
                redirect placeBooking
            }
            '*'{ respond placeBooking, [status: OK] }
        }



    }

    @Transactional
    @Secured('ROLE_ADMIN')
    def delete(PlaceBooking placeBooking) {

        if (placeBooking == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        placeBooking.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [" ", " "])
                redirect action:"index", method:"GET"
            }
            '*'{ render status: NO_CONTENT }
        }
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'placeBooking.label', default: 'PlaceBooking'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
