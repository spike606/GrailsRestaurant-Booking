<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="placeBooking.label" /></title>
    </head>
    <body>
        <a href="#list-placeBooking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="create" action="create"><g:message code="placeBooking.new.label" /></g:link></li>
            </ul>
        </div>
        <div id="list-placeBooking" class="content scaffold-list" role="main">
            <h1><g:message code="placeBooking.label" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <sec:ifAllGranted roles="ROLE_ADMIN">
                <f:table collection="${placeBookingList}"
                         properties="['date', 'hourStart', 'hours', 'user', 'place']" />
            </sec:ifAllGranted>
            <sec:ifNotGranted roles="ROLE_ADMIN">
                <f:table collection="${placeBookingList}"
                         properties="['date', 'hourStart', 'hours']" />
            </sec:ifNotGranted>

            <div class="pagination">
                <g:paginate total="${placeBookingCount ?: 0}" />
            </div>
        </div>
    </body>
</html>