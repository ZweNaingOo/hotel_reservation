<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <title>OTP Verification</title>
    <link rel="stylesheet" href="resources/css/otp.css">
</head>
<body>
    <div class="form-container">
        <form action="otp" method="post">
            <h1>Enter OTP</h1>
            <p>We have sent an OTP to your registered email ${OTPEmail.email}.</p>
            <input type="text" id="otp" placeholder="Enter OTP" required name="user_input"/>
            <div class="error-message" id="otp-error">
            	${error}
            </div> <!-- Error message for OTP -->
            <button type="submit">Verify OTP</button>
            <%-- <a href="resentOTP/${OTP.email}" id="resend-otp-link" class="resend-otp-link">Resend OTP</a> <!-- Resend OTP link -->
            <div class="message" id="resend-message">${resentMessage}</div> <!-- Message for resending OTP --> --%>
        </form>
    </div>
</body>
</html>
