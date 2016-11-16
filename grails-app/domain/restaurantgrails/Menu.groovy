package restaurantgrails

import grails.rest.Resource

@Resource(uri='/menu', formats=['json'], readOnly = true)
class Menu {

    String dishName
    String price
    static constraints = {
        dishName blank:false
        price blank: false
    }
}
