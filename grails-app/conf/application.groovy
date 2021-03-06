// Added by the Spring Security Core plugin:
grails.plugin.springsecurity.userLookup.userDomainClassName = 'restaurantgrails.User'
grails.plugin.springsecurity.userLookup.authorityJoinClassName = 'restaurantgrails.UserRole'
grails.plugin.springsecurity.authority.className = 'restaurantgrails.Role'
grails.plugin.springsecurity.controllerAnnotations.staticRules = [
	[pattern: '/',               access: ['permitAll']],
	[pattern: '/error',          access: ['permitAll']],
	[pattern: '/index',          access: ['permitAll']],
	[pattern: '/index.gsp',      access: ['permitAll']],
	[pattern: '/shutdown',       access: ['permitAll']],
	[pattern: '/assets/**',      access: ['permitAll']],
	[pattern: '/**/js/**',       access: ['permitAll']],
	[pattern: '/**/css/**',      access: ['permitAll']],
	[pattern: '/**/images/**',   access: ['permitAll']],
	[pattern: '/**/favicon.ico', access: ['permitAll']],
	[pattern: '/user/**', 		 access: ['ROLE_ADMIN']],
	[pattern: '/role/**', 		 access: ['ROLE_ADMIN']],
	[pattern: '/securityInfo/**',access: ['ROLE_ADMIN']],
	[pattern: '/register/**',access: ['permitAll']],
	[pattern: '/menu/**',access: ['permitAll']],
	[pattern: '/place/**',access: ['ROLE_ADMIN']],
	[pattern: '/placeBooking/**',access: ['ROLE_ADMIN']],
	[pattern: '/registationCode/**',access: ['ROLE_ADMIN']],
	[pattern: '/dbconsole/**',access: ['ROLE_ADMIN']],
	[pattern: '/plugins/**',access: ['ROLE_USER']]
]

grails.plugin.springsecurity.filterChain.chainMap = [
	[pattern: '/assets/**',      filters: 'none'],
	[pattern: '/**/js/**',       filters: 'none'],
	[pattern: '/**/css/**',      filters: 'none'],
	[pattern: '/**/images/**',   filters: 'none'],
	[pattern: '/**/favicon.ico', filters: 'none'],
	[pattern: '/**',             filters: 'JOINED_FILTERS']
]
grails.plugin.springsecurity.logout.postOnly = false// allows logout to work


grails {
	mail {
		host = "smtp.gmail.com"
		port = 587
		username = "ticktwo.ticktwo@gmail.com"
		password = "TickTwoAdmin"
		props = ["mail.smtp.auth":"true",
				 "mail.smtp.starttls.enable" : "true"]


	}
}
grails.mail.default.from="ticktwo.ticktwo@gmail.com"

//grails.plugin.springsecurity.ui.register.emailBody = 'Welcome $user.username! Your account was created. Thank You!'
grails.plugin.springsecurity.ui.register.emailSubject = 'Restaurant Grails Welcome!'
grails.plugin.wkhtmltopdf.binary = "C:\\Program Files\\wkhtmltopdf\\bin\\wkhtmltopdf.exe"
