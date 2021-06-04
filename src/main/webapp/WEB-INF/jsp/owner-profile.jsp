<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
</head>
<body>
<%@ include file="/WEB-INF/jspf/owner-profile-nav.jspf" %>
<c:set var="owner" value="${ownerInfo}"/>


<c:if test="${not empty getAlert and getAlert=='success'}">
    <c:set var="getAlert" value="" scope="session"/>
    <script>
        $(document).ready(function () {
            $("#success-alert").show(1000);
            $("#success-alert").show().delay(5000).fadeOut();
        });
    </script>
</c:if>

<c:if test="${not empty getAlert and getAlert=='error'}">
    <c:set var="getAlert" value="" scope="session"/>
    <c:set var="errorMessage" value="" scope="session"/>
    <script>
        $(document).ready(function () {
            $("#error-alert").show(1000);
            $("#error-alert").show().delay(5000).fadeOut();
        });
    </script>
</c:if>
<div class="container">
    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">

            <div class="alert alert-success" role="alert" id="success-alert" style="display:none;">
                Profile edited!
            </div>
            <div class="row gutters d-flex justify-content-center">

                <div class="card pt-3">
                    <div class="card-body">
                        <div class="container text-center pb-3 pt-1">
                            <img class="rounded-circle "
                                 src="https://cdn1.savepice.ru/uploads/2021/5/30/67c93a93c80bfeee7030f77fa17d81e1-full.png"
                                 width="140" height="140">
                        </div>
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <b><h5 class="mb-2 text-success">Personal Details</h5></b>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="name"><b>Name</b></label>
                            <p class="form-control-static" id="name"><c:out value="${owner.name}"/></p>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="surname"><b>Surname</b></label>
                            <p class="form-control-static" id="surname"><c:out value="${owner.surname}"/></p>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="login"><b>Login</b></label>
                            <p class="form-control-static" id="login"><c:out value="${owner.login}"/></p>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="email"><b>E-mail</b></label>
                            <p class="form-control-static" id="email"><c:out value="${owner.email}"/></p>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="phone"><b>Phone</b></label>
                            <p class="form-control-static" id="phone"><c:out value="${owner.phone}"/></p>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="address"><b>Address</b></label>
                            <p class="form-control-static" id="address"><c:out value="${owner.city}"/>, <c:out value="${owner.region}"/></p>
                        </div>
                    </div>
                </div>
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="text-right">
                            <a href="/owner/profile/edit">
                                <button type="button" id="submit" name="submit" class="btn btn-outline-success"
                                        style="width:25%; min-width:80px"> Edit
                                </button>
                            </a>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        </div>
        </div>
        </div>
</div>
<footer class="container text-center py-3 pt-3 " id="foot" >
    <div class="form-group text-center pb-0">
        <img src="https://cdn1.savepice.ru/uploads/2021/6/1/668b9296ab01c33d699e77f73c97989b-full.png"
             width="160" height="160">
        <p>&copy; 2021 FrogProg Inc.</p>
    </div>

</footer>
</body>
</html>