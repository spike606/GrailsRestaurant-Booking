<!doctype html>
<html lang="en" class="no-js">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
    <meta http-equiv="X-UA-Compatible" content="IE=edge"/>
    <title>
        <g:layoutTitle default="Grails"/>
    </title>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>

    <asset:stylesheet src="application.css"/>

    <g:layoutHead/>

</head>
<body>

    <div class="navbar navbar-default navbar-static-top" role="navigation">
        <div class="container">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="${createLink(uri: '/')}">
                    <i class="fa grails-icon">
                        <asset:image src="grails-cupsonly-logo-white.svg"/>
                    </i> Restaurant Grails
                </a>
            </div>




            <div class="navbar-collapse collapse" aria-expanded="false" style="height: 0.8px;">
                <ul class="nav navbar-nav navbar-right">
                    <g:pageProperty name="page.nav" />
                    <sec:ifLoggedIn>
                        <li class="dropdown">
                            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false"><g:message code='main.booking'/><span class="caret"></span></a>
                            <ul class="dropdown-menu">
                            <li><g:link url="[action:'create',controller:'placeBooking']"><g:message code='main.book'/></g:link>
                                <sec:ifNotGranted roles="ROLE_ADMIN">
                                    <li><g:link url="[action:'index',controller:'placeBooking']"><g:message code='main.mybookings'/></g:link></li>
                                </sec:ifNotGranted>
                                <sec:ifAllGranted roles="ROLE_ADMIN">
                                    <li><g:link url="[action:'index',controller:'placeBooking']"><g:message code='main.allbookings'/></g:link></li>
                                </sec:ifAllGranted>
                            </ul>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifLoggedIn>
                        <sec:ifAllGranted roles="ROLE_ADMIN">
                            <li class="dropdown">
                                <g:link controller="place"><g:message code='main.places'/></g:link>
                            </li>
                            <li class="dropdown">
                                <g:link controller="user"><g:message code='main.adminpanel'/></g:link>
                            </li>
                        </sec:ifAllGranted>
                        <li class="dropdown">
                            <g:link controller='logout'><g:message code='main.logout'/></g:link>
                        </li>
                        <li class="dropdown" disabled="true">
                            <g:link><g:message code='main.loggedas'/><sec:username/></g:link>
                        </li>
                    </sec:ifLoggedIn>
                    <sec:ifNotLoggedIn>
                        <li class="dropdown">
                            <g:link controller='register'><g:message code='main.register'/></g:link>
                        </li>
                        <li class="dropdown">
                            <g:link controller='login'><g:message code='main.login'/></g:link>
                        </li>
                    </sec:ifNotLoggedIn>
                </ul>
            </div>
            <form method="get">
                <g:localeSelect name="lang" value="${locale}"/>
                <input type="submit" value="${message(code:'main.locale')}"/>
            </form>

        </div>


    </div>


    <g:layoutBody/>

    <div class="footer" role="contentinfo"></div>

    <div id="spinner" class="spinner" style="display:none;">
        <g:message code="spinner.alt" default="Loading&hellip;"/>
    </div>

    <asset:javascript src="application.js"/>



</body>
</html>
