<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="placeBooking.new.label"  /></title>
    </head>
    <body>
        <a href="#create-placeBooking" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        <div class="nav" role="navigation">
            <ul>
                <li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
                <li><g:link class="list" action="index"><g:message code="placeBooking.label" /></g:link></li>
            </ul>
        </div>
        <div id="create-placeBooking" class="content scaffold-create" role="main">
            <h1><g:message code="placeBooking.new.label"  /></h1>
            <g:if test="${flash.message}">
            <div class="message" role="status">${flash.message}</div>
            </g:if>
            <g:hasErrors bean="${this.placeBooking}">
            <ul class="errors" role="alert">
                <g:eachError bean="${this.placeBooking}" var="error">
                <li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
                </g:eachError>
            </ul>
            </g:hasErrors>
            <g:img dir="images" file="tables.jpg" />
            <g:form action="Save">
                <fieldset class="form">
                    %{--<f:all bean="placeBooking"/>--}%
                <div class="fieldcontain">
                    <label ><g:message code='placeBooking.date.label'/></label>
                    <g:datePicker name="date" value="${date}" precision="day" years="${2016..2020}"></g:datePicker>
                </div>
                <div class="fieldcontain">
                    <label ><g:message code='placeBooking.hourStart.label'/></label>
                    <g:select name="hourStart" value="${hourStart}" from="${10..18}"/>
                </div>
                <div class="fieldcontain">
                    <label ><g:message code='placeBooking.hours.label'/></label>
                    <g:select name="hours" value="${hours}" from="${1..4}"/>
                </div>
                <div class="fieldcontain">
                    <label ><g:message code='placeBooking.place.label'/></label>
                    <g:select name="selectedTable" optionKey="${{it.tableNumber}}"
                              optionValue="${{it.tableNumber + " (${message(code:'placeBooking.price.label')}: " + it.pricePerHour + ")"}}" from="${places}" />

                </div>
                </fieldset>
                <fieldset class="buttons">
                    <g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
                </fieldset>
            </g:form>
        </div>
    </body>
</html>
