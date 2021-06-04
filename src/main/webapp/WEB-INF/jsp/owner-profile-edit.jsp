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

<c:set var="owner" value="${ownerInfo}"/>
<%@ include file="/WEB-INF/jspf/owner-profile-nav.jspf" %>

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
        <div class="card">
            <div class="card-body">
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <h5 class="mb-2 text-success">Personal Details</h5>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >Name</label>
                            <input type="text" class="form-control"  value="<c:out value="${owner.name}"/>" id="name" placeholder="Name"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >Surname</label>
                            <input type="text" class="form-control" value="<c:out value="${owner.surname}"/>" id="surname" placeholder="Surname"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >E-mail</label>
                            <input type="email" class="form-control" value="<c:out value="${owner.email}"/>" id="eMail" placeholder="E-mail"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >Phone number</label>
                            <input type="text" class="form-control" value="<c:out value="${owner.phone}"/>" id="phone" placeholder="Phone number"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >Login</label>
                            <input type="text" class="form-control" value="<c:out value="${owner.login}"/>" id="inputLogin" placeholder="Login"/>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <%--         empty            --%>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >Region</label>
                            <%@ include file="/WEB-INF/jspf/regions.jspf" %>
                        </div>
                    </div>
                    <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label >City</label>
                            <input type="text" class="form-control" value="<c:out value="${owner.city}"/>" id="inputCity" placeholder="City">
                        </div>
                    </div>
                </div>
                <div class="row gutters">
                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                        <div class="text-right">

                            <a href="/owner/profile">
                                <button type="button" id="submit" name="submit" class="btn btn-outline-success"
                                        style="width:25%; min-width:80px"> Save
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
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>