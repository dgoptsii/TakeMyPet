<%--
  Created by IntelliJ IDEA.
  User: Kata1
  Date: 26.05.2021
  Time: 14:21
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<!DOCTYPE html>
<html lang="en">

<%@ include file="/WEB-INF/jspf/head.jspf" %>
<body>
<%@ include file="/WEB-INF/jspf/simple-nav.jspf" %>

<div class="mt-5 row gutters d-flex justify-content-center">
    <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
        <div class="row gutters d-flex justify-content-center">
<div class="card pt-3">
    <div class="card-body pt-0">
        <div class="d-flex justify-content-center">
            <form role="form" name="login_form"
                  action="${pageContext.request.contextPath}/login" method="post">
                <div class="form-outline mb-4 text-center">
                    <i class="fa fa-user-circle green-color " style="color:#28a745; font-size:70px"></i>
                    <h3 class="d-flex justify-content-center">Log in</h3>
                </div>

                    <div class="form-group">
                        <input type="text" name="username" id="form1Example1" class="form-control" placeholder="Login"
                               required/>
                    </div>


                    <div class="form-group">
                        <input type="password" name="password" id="form1Example2" class="form-control" placeholder="Password"
                               required/>
                    </div>

                <div class="text-center">
                    <input type="submit" value="Login" id="submit" class="btn btn-success">
                </div>
            </form>
        </div>
    </div>
</div>
    </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>

