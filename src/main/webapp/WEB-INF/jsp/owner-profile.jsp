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
<div class="container">
    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
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
                            <p class="form-control-static" id="address"><c:out value="${owner.city}"/>, <c:out value="${owner.country}"/></p>
                        </div>
                    </div>
                </div>
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="text-right">
                            <a href="/owner/profile/edit">
                                <button type="button" id="submit" name="submit" class="btn btn-success"
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
</body>
</html>