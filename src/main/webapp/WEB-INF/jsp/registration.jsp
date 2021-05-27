
<html>

<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<div class="container myFormHello">
    <div class="row centered-form">
        <div class="col-xs-12 col-sm-8 col-md-4 col-sm-offset-2 col-md-offset-4">
            <div class="panel panel-default">
                <div class="panel-heading">
                    <h3 class="panel-title">Welcome</h3>

                    <p><a href="login">
                        Login</a>
                    </p>

                </div>
                <div class="panel-body">


                    <form role="form" id="registration_form" name="registration_form"
                          action="${pageContext.request.contextPath}/registration" method="post" >
<%--                          onsubmit="return validate(this);">--%>

                        <div class="form-group">
                            <input type="text" name="login" id="login" class="form-control input-sm"
                                   placeholder="Login">
                        </div>

                        <div class="form-group">
                            <input type="email" name="email" id="email" class="form-control input-sm"
                                   placeholder="Email">
                        </div>

                        <div class="row">
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password" id="password" class="form-control input-sm"
                                           placeholder="Password">
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 col-md-6">
                                <div class="form-group">
                                    <input type="password" name="password_confirmation" id="password_confirmation"
                                           class="form-control input-sm"
                                           placeholder="Confirm password">
                                </div>
                            </div>
                        </div>

                        <input type="submit" value="Go"
                               class="btn btn-info btn-block">

                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>