<!doctype html>
<html>
<head>
    <meta name="layout" content="main"/>
    <title>Welcome to Restaurant Grails</title>

    <asset:link rel="icon" href="favicon.ico" type="image/x-ico" />
</head>
<body>
    <content tag="nav">

    <sec:ifLoggedIn>
        <li class="dropdown">
            <a href="#" class="dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true" aria-expanded="false">Booking<span class="caret"></span></a>
            <ul class="dropdown-menu">
                <li><g:link url="[action:'create',controller:'placeBooking']">Book place</g:link>
                <sec:ifNotGranted roles="ROLE_ADMIN">
                    <li><g:link url="[action:'index',controller:'placeBooking']">My bookings</g:link></li>
                </sec:ifNotGranted>
                <sec:ifAllGranted roles="ROLE_ADMIN">
                    <li><g:link url="[action:'index',controller:'placeBooking']">All bookings</g:link></li>
                </sec:ifAllGranted>
            </ul>
        </li>
    </sec:ifLoggedIn>
    <sec:ifLoggedIn>
        <sec:ifAllGranted roles="ROLE_ADMIN">
            <li class="dropdown">
                <g:link controller='place'>Places</g:link>
            </li>
            <li class="dropdown">
                <g:link controller='user'>Admin panel</g:link>
            </li>
        </sec:ifAllGranted>
            <li class="dropdown">
                <g:link controller='logout'>Logout</g:link>
            </li>
            <li class="dropdown" disabled="true">
                <g:link>Logged in as <sec:username/></g:link>
            </li>
        </sec:ifLoggedIn>
        <sec:ifNotLoggedIn>
            <li class="dropdown">
                <g:link controller='register'>Register</g:link>
            </li>
            <li class="dropdown">
                <g:link controller='login'>Login</g:link>
            </li>
        </sec:ifNotLoggedIn>

    </content>

    <div class="svg" role="presentation">
        <div class="grails-logo-container">
            <asset:image src="grails-cupsonly-logo-white.svg" class="grails-logo"/>
        </div>
    </div>

    <div id="content" role="main">
        <section class="row colset-2-its">
            <h1>Welcome to Restaurant Grails</h1>

            <p>
                Congratulations, you have successfully started your first Grails application! At the moment
                this is the default page, feel free to modify it to either redirect to a controller or display
                whatever content you may choose. Below is a list of controllers that are currently deployed in
                this application, click on each to execute its default action:
            </p>
        </section>
    </div>

</body>
</html>
