package restaurantgrails

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlaceBookingController {

    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]

    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond PlaceBooking.list(params), model:[placeBookingCount: PlaceBooking.count()]
    }

    def show(PlaceBooking placeBooking) {
        respond placeBooking
    }

    def create() {
        respond new PlaceBooking(params)
    }

    @Transactional
    def save(PlaceBooking placeBooking) {
        if (placeBooking == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (placeBooking.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond placeBooking.errors, view:'create'
            return
        }

        placeBooking.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'placeBooking.label', default: 'PlaceBooking'), placeBooking.id])
                redirect placeBooking
            }
            '*' { respond placeBooking, [status: CREATED] }
        }
    }

    def edit(PlaceBooking placeBooking) {
        respond placeBooking
    }

    @Transactional
    def update(PlaceBooking placeBooking) {
        if (placeBooking == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (placeBooking.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond placeBooking.errors, view:'edit'
            return
        }

        placeBooking.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [message(code: 'placeBooking.label', default: 'PlaceBooking'), placeBooking.id])
                redirect placeBooking
            }
            '*'{ respond placeBooking, [status: OK] }
        }
    }

    @Transactional
    def delete(PlaceBooking placeBooking) {

        if (placeBooking == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        placeBooking.delete flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.deleted.message', args: [message(code: 'placeBooking.label', default: 'PlaceBooking'), placeBooking.id])
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
