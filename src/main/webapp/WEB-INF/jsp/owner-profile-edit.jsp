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

<%--<c:set var="owner" value="${ownerInfo}"/>--%>
<%@ include file="/WEB-INF/jspf/owner-profile-nav.jspf" %>

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
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">

            <div class="alert alert-danger" role="alert" id="error-alert" style="display:none;">
                Error:
                <c:if test="${not empty errorMessage}">
                    <c:out value="${errorMessage}"/>
                </c:if>
            </div>


            <div class="card">
                <div class="card-body">

                    <form:form id="request" action="${pageContext.request.contextPath}/owner/profile/edit" method="post" modelAttribute="owner"
                    onclick="">

                        <form:input path="id" type="hidden" value="${owner.id}" />
                        <div class="row gutters">
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <h5 class="mb-2 text-success">Personal Details</h5>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Name</label>
                                    <form:input path="name" class="form-control" value="${owner.name}" id="name"  placeholder="Name" />
<%--                                    <input type="text" name="" class="form-control" value="<c:out value="${owner.name}"/>" id="name"--%>
<%--                                           placeholder="Name"/>--%>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Surname</label>
                                    <form:input path="surname" type="text" class="form-control" value="${owner.surname}"
                                    id="surname" placeholder="Surname"/>

<%--                                    <input type="text" class="form-control" value="<c:out value="${owner.surname}"/>"--%>
<%--                                           id="surname" placeholder="Surname"/>--%>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>E-mail</label>
                                    <form:input path="email" type="email" class="form-control" value="${owner.email}"
                                    id="eMail" placeholder="E-mail" />

<%--                                    <input type="email" class="form-control" value="<c:out value="${owner.email}"/>"--%>
<%--                                           id="eMail" placeholder="E-mail"/>--%>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Phone number</label>
                                    <form:input path="phone" type="text" class="form-control" value="${owner.phone}"  id="phone" placeholder="Phone number"/>
<%--                                    <input type="text" class="form-control" value="<c:out value="${owner.phone}"/>"--%>
<%--                                           id="phone" placeholder="Phone number"/>--%>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Login</label>
                                    <form:input path="login" type="text" class="form-control" value="${owner.login}"
                                    id="inputLogin" placeholder="Login" />
<%--                                    <input type="text" class="form-control" value="<c:out value="${owner.login}"/>"--%>
<%--                                           id="inputLogin" placeholder="Login"/>--%>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <%--         empty            --%>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>Region</label>
                                    <%@ include file="/WEB-INF/jspf/regions.jspf" %>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label>City</label>
                                    <form:input path="city" type="text" class="form-control" value="${owner.city}"
                                    id="inputCity" placeholder="City" />
<%--                                    <input type="text" class="form-control" value="<c:out value="${owner.city}"/>"--%>
<%--                                           id="inputCity" placeholder="City">--%>
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

                                    <%--                                <button type="button"--%>
                                    <%--                                        id="submit2" name="submit" class="btn btn-outline-danger mx-1 mt-1"--%>
                                    <%--                                        data-toggle="modal"  data-target="#error-file-modal">--%>
                                    <%--                                    Error--%>
                                    <%--                                </button>--%>

                                    <%--                              <a href="/owner/profile">&ndash;%&gt;--%>
                                    <%--                                    <button type="button" id="submit" name="submit" class="btn btn-outline-success"--%>
                                    <%--                                            style="width:25%; min-width:80px"> Save--%>
                                    <%--                                    </button>--%>
                                    <%--                               </a>--%>
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


<script type="text/javascript">
    // $('#confirm-edit-button').on("click", function () {
    //     $("#request").submit();
    // });
</script>
</body>
</html>