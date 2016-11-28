package restaurantgrails

import com.megatome.grails.RecaptchaService
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode

class RegisterController extends grails.plugin.springsecurity.ui.RegisterController {
    RecaptchaService recaptchaService
    def springSecurityService // first you have to define the service


    def register(MyRegisterCommand registerCommand) {
        if(springSecurityService.getCurrentUser()){
            // don't allow registration while user is logged in
            redirect(uri:'/')
        }
        if (!request.post) {
            return [registerCommand: new MyRegisterCommand()]
        }

        def recaptchaOK = true
        //temp off
        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            recaptchaOK = false
        }
        if (registerCommand.hasErrors() || !recaptchaOK) {
            return [registerCommand: registerCommand]
        }

        def user = getDomainClassClass("restaurantgrails.User").newInstance(email: registerCommand.email,
                username: registerCommand.username,
                firstName: registerCommand.firstName,
                lastName: registerCommand.lastName,
                telephone: registerCommand.telephone,
                accountLocked: false,//temp false
                enabled: true)
//        def user = uiRegistrationCodeStrategy.createUser(registerCommand)

        String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
        RegistrationCode registrationCode = uiRegistrationCodeStrategy.register(user, registerCommand.password, salt)

        if (registrationCode == null || registrationCode.hasErrors()) {// null means problem creating the user
            flash.error = message(code: 'spring.security.ui.register.miscError')
            return [registerCommand: registerCommand]
        }

        sendVerifyRegistrationMail registrationCode, user, registerCommand.email

//        [emailSent: false, registerCommand: registerCommand]//send email off
        [emailSent: true, registerCommand: registerCommand]//send email on

    }
    void afterPropertiesSet() {
        super.afterPropertiesSet()

        MyRegisterCommand.User = User
        MyRegisterCommand.usernamePropertyName = usernamePropertyName

        forgotPasswordEmailBody = conf.ui.forgotPassword.emailBody ?: ''
        registerEmailBody = conf.ui.register.emailBody ?: ''
        registerEmailFrom = conf.ui.register.emailFrom ?: ''
        registerEmailSubject = conf.ui.register.emailSubject ?: ''
        registerPostRegisterUrl = conf.ui.register.postRegisterUrl ?: ''
        registerPostResetUrl = conf.ui.register.postResetUrl ?: ''
        successHandlerDefaultTargetUrl = conf.successHandler.defaultTargetUrl ?: '/'

        passwordMaxLength = conf.ui.password.maxLength instanceof Number ? conf.ui.password.maxLength : 64
        passwordMinLength = conf.ui.password.minLength instanceof Number ? conf.ui.password.minLength : 8
        passwordValidationRegex = conf.ui.password.validationRegex ?: '^.*(?=.*\\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&]).*$'
    }
}

class MyRegisterCommand extends RegisterCommand {

    protected static Class<?> User
    protected static String usernamePropertyName

    String username
    String email
    String password
    String password2
	String firstName
    String lastName
    int telephone

    static constraints = {
        username size: 4..15, validator: { value, command ->
            if (!value) {
                return
            }

            if (User.findWhere((usernamePropertyName): value)) {
                return 'registerCommand.username.unique'
            }
        }
        email email: true, size: 4..25
        password validator: RegisterController.passwordValidator
        password2 nullable: true, validator: RegisterController.password2Validator

        firstName blank: false, size: 4..15
        lastName blank: false, size: 4..15
        telephone blank: false, validator: {
            return (it.toString().size() >= 6 && it.toString().size() <= 9)
        }

    }

}
