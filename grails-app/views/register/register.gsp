<html>
<head>
	<meta name="layout" content="${layoutRegister}"/>
	<s2ui:title messageCode='spring.security.ui.register.title'/>
</head>
<body>

<script type="text/javascript">
	var onloadCallback = function() {
		grecaptcha.render('html_element', <recaptcha:renderParameters theme="dark" type="image" tabindex="2"/>);
	};
</script>
<s2ui:formContainer type='register' focus='username' width='800px'>
	<s2ui:form beanName='registerCommand' method="post" >
		<g:if test='${emailSent}'>
		<br/>
		<g:message code='spring.security.ui.register.sent'/>
		</g:if>
		<g:else>
			<recaptcha:ifEnabled>
				<recaptcha:recaptchaExplicit loadCallback="onloadCallback"/>
				<div id="html_element"></div>
			</recaptcha:ifEnabled>
		<br/>
		<table>
			<tbody>
			<s2ui:textFieldRow name='username' size='15' labelCodeDefault="${message(code:'spring.security.ui.register.username')}"/>
			<s2ui:textFieldRow name='firstName' size='15' labelCodeDefault="${message(code:'spring.security.ui.register.firstName')}"/>
			<s2ui:textFieldRow name='lastName' size='15' labelCodeDefault="${message(code:'spring.security.ui.register.lastname')}"/>
			<s2ui:textFieldRow name='email' size='25' labelCodeDefault="${message(code:'spring.security.ui.register.email')}"/>
			<s2ui:textFieldRow name='telephone' size='9' labelCodeDefault="${message(code:'spring.security.ui.register.telephone')}"/>
			<s2ui:passwordFieldRow name='password' size='15' labelCodeDefault="${message(code:'spring.security.ui.register.password')}"/>
			<s2ui:passwordFieldRow name='password2' size='15' labelCodeDefault="${message(code:'spring.security.ui.register.password2')}"/>
			</tbody>

		</table>
		<s2ui:submitButton elementId='submit' messageCode='spring.security.ui.register.submit'/>
		</g:else>
	</s2ui:form>
</s2ui:formContainer>
</body>
</html>
