<!DOCTYPE html>
<html>
    <head>
        <meta name="layout" content="main" />
        <title><g:message code="list.label" /></title>
    </head>
    <body>
        <a href="#list-place" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
        %{--<div class="nav" role="navigation">--}%
            %{--<ul>--}%
                %{--<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>--}%
                %{--<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>--}%
            %{--</ul>--}%
        %{--</div>--}%
        <div id="list-place" class="content scaffold-list" role="main">
            <h1><g:message code="list.label" /></h1>
            <g:if test="${flash.message}">
                <div class="message" role="status">${flash.message}</div>
            </g:if>
            <f:table collection="${placeList}" properties="['tableNumber', 'pricePerHour']"/>

            <div class="pagination">
                <g:paginate total="${placeCount ?: 0}" />
            </div>
        </div>
    </body>
</html>