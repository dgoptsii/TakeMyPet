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
<c:if test="${not empty getAlert and getAlert=='error'}">
    <c:set var="getAlert" value="" scope="session"/>
    <%--    <c:set var="errorMessage" value="" scope="session"/>--%>
    <script>
        $(document).ready(function () {
            $("#error-alert").show(1000);
            $("#error-alert").show().delay(5000).fadeOut();
        });
    </script>
</c:if>


    <div class="container">
        <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-5">
        <div class="card">
            <div class="card-body">
                <form:form id="request" action="${pageContext.request.contextPath}/host/profile/edit" method="post" modelAttribute="host" onclick="">
                <form:input path="id" type="hidden" value="${host.id}" />
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h5 class="mb-2 text-success">Personal Details</h5>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="name">Name</label>
                            <form:input path="name" class="form-control" value="${host.name}" id="name"  placeholder="Name" />
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="surname">Surname</label>
                            <form:input path="surname" type="text" class="form-control" value="${host.surname}"
                                        id="surname" placeholder="Surname"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="eMail">E-mail</label>
                            <form:input path="email" type="email" class="form-control" value="${host.email}"
                                        id="eMail" placeholder="E-mail" />
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="phone">Phone</label>
                            <form:input path="phone" type="text" class="form-control" value="${host.phone}"  id="phone" placeholder="Phone number"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="inputLogin">Login</label>
                            <form:input path="login" type="text" class="form-control" value="${host.login}"
                                        id="inputLogin" placeholder="Login" />
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                    <%--         empty            --%>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >Country</label>
                            <%@ include file="/WEB-INF/jspf/regions.jspf" %>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="inputCity">City</label>
                            <form:input path="city" type="text" class="form-control" value="${host.city}"
                                        id="inputCity" placeholder="City" />
                        </div>
                    </div>
                </div>
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h5 class="mt-3 mb-2 text-success">Pet details</h5>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group text-center">
                            <label id="textInput" for="numPets">Max number of animals hosted together: <span id="demo"></span> </label>
                             <div class="slidecontainer">
                                 <form:input path="maxAnimals" type="range" min="1" max="5" value="${host.maxAnimals}" class="slider" id="numPets"/>
<%--                                 <form:input path="city" type="text" class="form-control" value="${host.maxAnimals}"--%>
<%--                                             id="inputCity" placeholder="City" />--%>
                            </div>
                            <script>
                                var slider = document.getElementById("numPets");
                                var output = document.getElementById("demo");
                                output.innerHTML = slider.value;

                                slider.oninput = function() {
                                    output.innerHTML = this.value;
                                }
                            </script>
                        </div>
                    </div>
                </div>
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="text-right">
                                <button type="button" id="submit" name="submit" class="btn btn-outline-success"
                                        style="width:25%; min-width:80px"
                                        data-toggle="modal" data-target="#edit-file-modal"> Save
                                </button>
                        </div>
                    </div>
                </div>
                    <%@ include file="/WEB-INF/jspf/edit_dialog.jspf" %>
                </form:form>
            </div>
        </div>
        </div>
        </div>
        </div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>