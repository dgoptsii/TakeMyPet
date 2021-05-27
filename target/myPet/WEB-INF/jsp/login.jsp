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

<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Login</title>
  <link rel="stylesheet" href="css/main-style.css">
  <link rel="stylesheet" href="css/nav-style.css">
  <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" >
  <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-gtEjrD/SeCtmISkJkNUaaKMoLD0//ElJ19smozuHV6z3Iehds+3Ulb9Bn9Plx0x4" crossorigin="anonymous"></script>
</head>

<body>
<nav class="navbar navbar-expand-lg navbar-dark bg-dark fixed-top">
  <a href="#" class="navbar-brand">Take<b>My</b>Pet</a>
</nav>

<div class=" pt-5 d-flex justify-content-center">
  <form role="form" name="login_form"
        action="${pageContext.request.contextPath}/login"  method="post"
        class=" pt-5">
    <div class="form-outline mb-4">
      <h3 class="d-flex justify-content-center">Log in</h3>
    </div>
    <div class="form-outline mb-4">
      <input type="text" name="username"  id="form1Example1" class="form-control" required/>
      <label class="form-label" for="form1Example1">Login</label>
    </div>
    <div class="form-outline mb-4">
      <input type="password" name="password"  id="form1Example2" class="form-control" required/>
      <label class="form-label" for="form1Example2">Password</label>
    </div>
<%--    <div class="row mb-4">--%>
<%--      <div class="col d-flex justify-content-center">--%>
<%--        <div class="form-check">--%>
<%--          <input--%>
<%--                  class="form-check-input"--%>
<%--                  type="checkbox"--%>
<%--                  value=""--%>
<%--                  id="form1Example3"--%>
<%--                  checked />--%>
<%--          <label class="form-check-label" for="form1Example3"> Remember me </label>--%>
<%--        </div>--%>
<%--      </div>--%>
<%--    </div>--%>
    <div class="text-right">
      <input type="submit" value="Login" id="submit" class="btn btn-primary">
    </div>
  </form>
</div>

</body>
</html>

