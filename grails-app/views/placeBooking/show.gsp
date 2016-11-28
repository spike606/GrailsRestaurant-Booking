<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="placeBooking.new.label" /></title>
    </head>
    <body>
        <a href="#show-placeBooking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="placeBooking.new.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="placeBooking.label"  /></g:link></li>
                <li><g:link class="create" action="create"><g:message code="placeBooking.new.label" /></g:link></li>
            </ul>
        </div>
        <div id="show-placeBooking" class="content scaffold-show" role="main">
            <h1><g:message code="placeBooking.booking.label"/></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <sec:ifLoggedIn>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <f:display bean="placeBooking"/>
                </sec:ifAllGranted>
                <sec:ifNotGranted roles="ROLE_ADMIN">
                    <ol class="property-list">
                        <li class="fieldcontain">
                            <span class="property-label">Date</span>
                            <div class="property-value"><f:display bean="placeBooking" property="date"/></div>
                        </li>
                        <li class="fieldcontain">
                            <span class="property-label">Hour Start</span>
                            <div class="property-value"><f:display bean="placeBooking" property="hourStart"/></div>
                        <li class="fieldcontain">
                            <span class="property-label">Hour Stop</span>
                            <div class="property-value"><f:display bean="placeBooking" property="hours"/></div>
                        </li>
                        <li class="fieldcontain">
                            <span class="property-label">Place</span>
                            <div class="property-value">${placeBooking.place.tableNumber} (price per hour: ${placeBooking.place.pricePerHour})</div>
                        </li>
                    </ol>
                </sec:ifNotGranted>
            </sec:ifLoggedIn>

            <g:form resource="${this.placeBooking}" method="DELETE">
                <fieldset class="buttons">
                    <g:link class="edit" action="edit" resource="${this.placeBooking}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
                    <sec:ifLoggedIn>
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <input class="delete" type="submit" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
                        </sec:ifAllGranted>
                    </sec:ifLoggedIn>
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
