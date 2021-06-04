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
<%@ include file="/WEB-INF/jspf/host-profile-nav.jspf" %>

<c:set var="host" value="${hostInfo}"/>

<div class="container">
    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
            <div class="row gutters d-flex justify-content-center">

                <div class="card pt-3">
                    <div class="card-body">
                        <div class="container text-center pb-3 pt-0">
                            <img class="rounded-circle "
                                 src="https://cdn1.savepice.ru/uploads/2021/5/30/fbc4b4a1ae5ca8a06640150fdade766b-full.png"
                                 width="140" height="140">
                        </div>
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <b><h5 class="mb-2 text-success">Personal Details</h5></b>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="name"><b>Name</b></label>
                                    <p class="form-control-static" id="name"><c:out value="${host.name}"/></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="surname"><b>Surname</b></label>
                                    <p class="form-control-static" id="surname"><c:out value="${host.surname}"/></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="login"><b>Login</b></label>
                                    <p class="form-control-static" id="login"><c:out value="${host.login}"/></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="email"><b>E-mail</b></label>
                                    <p class="form-control-static" id="email"><c:out value="${host.email}"/></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="phone"><b>Phone</b></label>
                                    <p class="form-control-static" id="phone"><c:out value="${host.phone}"/></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="address"><b>Address</b></label>
                                    <p class="form-control-static" id="address"><c:out value="${host.city}"/>, <c:out
                                            value="${host.region}"/></p>
                                </div>
                            </div>
                        </div>
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <b><h5 class="mt-3 mb-2 text-success">Pet details</h5></b>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="numPets"><b>Max number of pets hosted together</b></label>
                                    <p class="form-control-static" id="numPets"><c:out value="${host.maxAnimals}"/></p>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="rating"><b>Rating: <c:out value="${host.rating}"/></b></label>
                                    <div id="rating" class="form-control-static">
                                        <script>
                                            $(function () {
                                                $("#rating").rateYo({
                                                    rating: <c:out value="${host.rating}"/>
                                                });
                                                $("#rating").rateYo('option', 'readOnly', true);
                                            });
                                        </script>
                                    </div>
                                </div>
                            </div>
                        </div>
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="/host/profile/edit">
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
        <img src="https://cdn1.savepice.ru/uploads/2021/6/1/6ff0a9e27c95d25ab8d339685dc69b80-full.png"
             width="160" height="160">
        <p>&copy; 2021 FrogProg Inc.</p>
    </div>

</footer>
</body>
</html>