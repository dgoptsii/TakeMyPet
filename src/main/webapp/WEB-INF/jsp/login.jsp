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

<div class=" pt-5 d-flex justify-content-center">
    <form role="form" name="login_form"
          action="${pageContext.request.contextPath}/login" method="post"
          class=" pt-5">
        <div class="form-outline mb-4">
            <h3 class="d-flex justify-content-center">Log in</h3>
        </div>
        <div class="form-group">
            <input type="text" name="username" id="form1Example1" class="form-control" placeholder="Login" required/>
        </div>
        <div class="form-group">
            <input type="password" name="password" id="form1Example2" class="form-control" placeholder="Password"
                   required/>
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
        <div class="text-center">
            <input type="submit" value="Login" id="submit" class="btn btn-success">
        </div>
    </form>
</div>

</body>
</html>

