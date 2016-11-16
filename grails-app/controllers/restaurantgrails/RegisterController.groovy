package restaurantgrails

import com.megatome.grails.RecaptchaService
import grails.databinding.BindUsing
import grails.plugin.springsecurity.authentication.dao.NullSaltSource
import grails.plugin.springsecurity.ui.RegisterCommand
import grails.plugin.springsecurity.ui.RegistrationCode
import org.springframework.web.context.request.RequestContextHolder as RCH

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
        if (!recaptchaService.verifyAnswer(session, request.getRemoteAddr(), params)) {
            recaptchaOK = false
        }
        if (registerCommand.hasErrors() || !recaptchaOK) {
            return [registerCommand: registerCommand]
        }

        def user = uiRegistrationCodeStrategy.createUser((RegisterCommand)registerCommand)
        String salt = saltSource instanceof NullSaltSource ? null : registerCommand.username
        RegistrationCode registrationCode = uiRegistrationCodeStrategy.register(user, registerCommand.password, salt)

        if (registrationCode == null || registrationCode.hasErrors()) {
            // null means problem creating the user
            flash.error = message(code: 'spring.security.ui.register.miscError')
            return [registerCommand: registerCommand]
        }

        sendVerifyRegistrationMail registrationCode, user, registerCommand.email

        [emailSent: true, registerCommand: registerCommand]
    }
}

class MyRegisterCommand extends RegisterCommand{
    RecaptchaService recaptchaService

//
//    @BindUsing({ obj, source -> source["g-recaptcha-response"] })
//    String gRecaptchaResponse

    static constraints = {

//        gRecaptchaResponse blank: false, validator: {value, command ->
//
//            def params = ["g-recaptcha-response" : value];
//
//            if (!command.recaptchaService.verifyAnswer(RCH.requestAttributes.session, RCH.requestAttributes.session.request.getRemoteAddr(), params)) {
//                return false
//            }
//
//
//        }
        importFrom grails.plugin.springsecurity.ui.RegisterCommand
    }
}
