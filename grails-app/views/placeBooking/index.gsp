<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <g:set var="entityName" value="${message(code: 'placeBooking.label', default: 'PlaceBooking')}" />
        <title><g:message code="default.list.label" args="[entityName]" /></title>
    </head>
    <body>
        <a href="#list-placeBooking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
            </ul>
        </div>
        <div id="list-placeBooking" class="content scaffold-list" role="main">
            <h1><g:message code="default.list.label" args="[entityName]" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <f:table collection="${placeBookingList}"
                         properties="['date', 'hourStart', 'hourStop', 'user', 'place']" />
            </sec:ifAllGranted>
            <sec:ifNotGranted roles="ROLE_ADMIN">
                <f:table collection="${placeBookingList}"
                         properties="['date', 'hourStart', 'hourStop']" />
            </sec:ifNotGranted>

            <div class="pagination">
                <g:paginate total="${placeBookingCount ?: 0}" />
            </div>
        </div>
    </body>
</html>