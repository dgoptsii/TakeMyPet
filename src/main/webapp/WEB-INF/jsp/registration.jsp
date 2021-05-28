<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/simple-nav.jspf" %>
<div class=" pt-5 d-flex justify-content-center">
    <form role="form" id="registration_form" name="registration_form"
          action="${pageContext.request.contextPath}/registration" method="post" class=" pt-5">
        <%--    onsubmit="return validate(this);">--%>
        <div class="form-outline mb-4">
            <h3 class="d-flex justify-content-center">Register</h3>
        </div>
        <div class="row">
            <div class="form-group col-md-6">
                <input type="text" name="login" id="lastname" class="form-control input-sm"
                       placeholder="Last name" required>

            </div>
            <div class="form-group col-md-6">
                <input type="text" name="login" id="firstname" class="form-control input-sm"
                       placeholder="First name" required>
            </div>
        </div>
        <div class="form-group">
            <input type="text" name="login" id="login" class="form-control input-sm"
                   placeholder="Login" required>
        </div>

        <div class="form-group">
            <input type="email" name="email" id="email" class="form-control input-sm"
                   placeholder="Email" required>
        </div>
            <div class="form-group">
                <input type="email" name="address" id="address" class="form-control input-sm"
                       placeholder="Address" required>
            </div>

        <div class="form-group">
            <div class="form-check">
                <input class="form-check-input" type="radio" name="radios" id="exampleRadios1" value="host" checked>
                <label class="form-check-label">
                    Host
                </label>
            </div>

            <div class="form-check">
                <input class="form-check-input" type="radio" name="radios" id="exampleRadios2" value="owner">
                <label class="form-check-label">
                    Owner
                </label>
            </div>
        </div>

        <div class="row">
            <div class="form-group col-md-6">
                <input type="password" name="password" id="password" class="form-control input-sm"
                       placeholder="Password" required>
            </div>
            <div class="form-group col-md-6">
                <input type="password" name="password_confirmation" id="password_confirmation"
                       class="form-control input-sm"
                       placeholder="Confirm password" required>
            </div>
        </div>

        <div class="text-center">
            <input type="submit" value="Register" id="register" class="btn btn-success">
        </div>
    </form>
</div>
</div>
</body>
</html>