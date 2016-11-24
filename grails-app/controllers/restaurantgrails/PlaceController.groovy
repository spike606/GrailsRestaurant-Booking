package restaurantgrails

import ch.qos.logback.core.util.StatusPrinter

import static org.springframework.http.HttpStatus.*
import grails.transaction.Transactional

@Transactional(readOnly = true)
class PlaceController {

//    static allowedMethods = [save: "POST", update: "PUT", delete: "DELETE"]
    static allowedMethods = [update: "PUT"]


    def index(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        respond Place.list(params), model:[placeCount: Place.count()]
    }

    def show(Place place) {
        respond place
    }

    def create() {
        response.sendError(404)
//        respond new Place(params)
    }

    @Transactional
    def save(Place place) {
        if (place == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (place.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond place.errors, view:'create'
            return
        }

        place.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.created.message', args: [message(code: 'place.label', default: 'Place'), place.id])
                redirect place
            }
            '*' { respond place, [status: CREATED] }
        }
    }

    def edit(Place place) {
        respond place
    }

    @Transactional
    def update(Place place) {
        if (place == null) {
            transactionStatus.setRollbackOnly()
            notFound()
            return
        }

        if (place.hasErrors()) {
            transactionStatus.setRollbackOnly()
            respond place.errors, view:'edit'
            return
        }

        place.save flush:true

        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.updated.message', args: [" ", " "])
                redirect place
            }
            '*'{ respond place, [status: OK] }
        }
    }

    @Transactional
    def delete(Place place) {

//        if (place == null) {
//            transactionStatus.setRollbackOnly()
//            notFound()
//            return
//        }
//
//        place.delete flush:true
//
//        request.withFormat {
//            form multipartForm {
//                flash.message = message(code: 'default.deleted.message', args: [message(code: 'place.label', default: 'Place'), place.id])
//                redirect action:"index", method:"GET"
//            }
//            '*'{ render status: NO_CONTENT }
//        }
        response.sendError(404)
    }

    protected void notFound() {
        request.withFormat {
            form multipartForm {
                flash.message = message(code: 'default.not.found.message', args: [message(code: 'place.label', default: 'Place'), params.id])
                redirect action: "index", method: "GET"
            }
            '*'{ render status: NOT_FOUND }
        }
    }
}
