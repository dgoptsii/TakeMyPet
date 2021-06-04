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

<c:set var="contract" value="${contractInfo}"/>
<c:set var="host" value="${contractInfo.host}"/>
<c:set var="owner" value="${contractInfo.owner}"/>

<div class="container">
    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">

            <div class="alert alert-danger" role="alert" id="error-alert" style="display:none;">
                Error was sent
            </div>

            <div class="card">
                <div class="card-header">
                    <h6 class="mb-2 text-success">Contract</h6>
                </div>
                <div class="card-body">
                    <div class="row">
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                            <div class="form-col">
                                <span><b>Owner details:</b><br> <c:out value="${owner.name} ${owner.surname}"/></span><br>
                                <span><c:out value="${owner.phone}"/></span><br>
<%--                                <span><c:out value="${owner.email}"/></span>--%>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                            <div class="form-col">
                                <span><b>Host details:</b><br> <c:out value="${host.name} ${host.surname}"/></span><br>
                                <span><c:out value="${host.phone}"/></span><br>
<%--                                <span><c:out value="${host.email}"/></span>--%>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                            <div class="form-col">
                                <span><b>Contract details:</b></span><br>
                                <span>Start date: <c:out value="${contract.startDate}"/></span><br>
                                <span>End date: <c:out value="${contract.endDate}"/></span>
                            </div>
                        </div>
                    </div>
                    <div class="row mt-4">
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                            <div class="form-col">
                                <p><b>Pet:</b> <c:out value="${contract.animal.name}"/></p>
                            </div>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                            <c:if test="${contract.status =='FINISHED' }">
                                <div class="form-col">
                                    <div class="form-group">
                                        <label for="rating"><b>Rating:</b> <c:out value="${contract.rating}"/></label>
                                        <div id="rating" class="form-control-static">
                                            <script>
                                                $(function () {
                                                    $("#rating").rateYo({
                                                        rating: <c:out value="${contract.rating}"/>
                                                    });
                                                    $("#rating").rateYo('option', 'readOnly', true);
                                                });
                                            </script>
                                        </div>
                                    </div>
                                </div>
                            </c:if>
                        </div>
                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                            <div class="form-col">
                                <p><b>Status: </b>
                                    <c:choose>
                                        <c:when test="${contract.status =='NEW'}">
                                            <span class="badge bg-info text-wrap" style="width: 5rem;">
                                                new
                                            </span>
                                        </c:when>

                                        <c:when test="${contract.status =='WAITING'}">
                                             <span class="badge bg-warning text-wrap" style="width: 5rem;">
                                                 waiting
                                             </span>
                                        </c:when>

                                        <c:when test="${contract.status =='ACTIVE'}">
                                            <span class="badge bg-success text-wrap" style="width: 5rem; color:white">
                                                active
                                            </span>
                                        </c:when>

                                        <c:when test="${contract.status =='FINISHED'}">
                                             <span class="badge bg-secondary text-wrap" style="width: 5rem; color:white">
                                                 finished
                                             </span>
                                        </c:when>
<%--                                        <c:when test="${contract.status =='ACTIVE'}">--%>
<%--                                            <span class="badge bg-success text-wrap"--%>
<%--                                                  style="width: 5rem;">in process</span>--%>
<%--                                        </c:when>--%>
<%--                                        <c:when test="${contract.status ==''}">--%>
<%--                                            <span class="badge bg-success text-wrap"--%>
<%--                                                  style="width: 5rem;">in process</span>--%>
<%--                                        </c:when>--%>
                                    </c:choose>
<%--                                    <span class="badge bg-info text-wrap" style="width: 5rem;">new</span>--%>
                                </p>
                            </div>
                        </div>
                    </div>
                    <div class="row">
                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                            <div class="text-right">
                                    <c:if test="${contract.status =='NEW' || contract.status =='WAITING'}">
                                        <%--<a href="#">--%>
                                        <button type="button"
                                                id="submit1" name="submit" class="btn btn-danger mx-1 mt-1"
                                                data-toggle="modal"  data-target="#edit-file-modal">
                                            Cancel
                                        </button>
                                        <%--</a>--%>
                                    </c:if>

                                    <button type="button"
                                            id="submit2" name="submit" class="btn btn-outline-danger mx-1 mt-1"
                                            data-toggle="modal"  data-target="#error-file-modal">
                                        Error
                                    </button>
                            </div>
                        </div>
                    </div>
                </div>

                <%@ include file="/WEB-INF/jspf/cancel_dialog.jspf" %>
                <%@ include file="/WEB-INF/jspf/error_dialog.jspf" %>
            </div>

<%--            <div class="card">--%>
<%--                <div class="card-header">--%>
<%--                    <h6 class="mb-2 text-primary">Contract</h6>--%>
<%--                </div>--%>
<%--                <div class="card-body">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <span><b>Owner details:</b><br>Name Surname</span><br>--%>
<%--                                <span>000-000-000</span><br>--%>
<%--                                <span>example@gmail.com</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <span><b>Host details:</b><br>Name Surname</span><br>--%>
<%--                                <span>000-000-000</span><br>--%>
<%--                                <span>example@gmail.com</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <span><b>Contract details:</b></span><br>--%>
<%--                                <span>Start date: 05/20/21</span><br>--%>
<%--                                <span>End date: 05/23/21</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="row mt-4">--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <p><b>Pet:</b> dog</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <!-- empty -->--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <p><b>Status:</b>--%>
<%--                                    <span class="badge bg-success text-wrap" style="width: 5rem;">in process</span>--%>
<%--                                </p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">--%>
<%--                            <div class="text-right">--%>
<%--                                <a href="#">--%>
<%--                                    <button type="button" id="submit3" name="submit"--%>
<%--                                            class="btn btn-outline-danger mx-1 mt-1">Error--%>
<%--                                    </button>--%>
<%--                                </a>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>

<%--            <div class="card">--%>
<%--                <div class="card-header">--%>
<%--                    <h6 class="mb-2 text-primary">Contract</h6>--%>
<%--                </div>--%>
<%--                <div class="card-body">--%>
<%--                    <div class="row">--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <span><b>Owner details:</b><br>Name Surname</span><br>--%>
<%--                                <span>000-000-000</span><br>--%>
<%--                                <span>example@gmail.com</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <span><b>Host details:</b><br>Name Surname</span><br>--%>
<%--                                <span>000-000-000</span><br>--%>
<%--                                <span>example@gmail.com</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <span><b>Contract details:</b></span><br>--%>
<%--                                <span>Start date: 05/20/21</span><br>--%>
<%--                                <span>End date: 05/23/21</span>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                    <div class="row mt-4">--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <p><b>Pet:</b> dog</p>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                            <div class="form-col">--%>
<%--                                <div class="form-group">--%>
<%--                                    <label for="rating"><b>Rating: <c:out value="${item.rating}"/></b></label>--%>
<%--                                    <div id="rating" class="form-control-static">--%>
<%--                                        <script>--%>
<%--                                            $(function () {--%>
<%--                                                $("#rating").rateYo({--%>
<%--                                                    rating: <c:out value="${item.rating}"/>--%>
<%--                                                });--%>
<%--                                                $("#rating").rateYo('option', 'readOnly', true);--%>
<%--                                            });--%>
<%--                                        </script>--%>
<%--                                    </div>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            --%>
<%--                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">--%>
<%--                                <div class="form-col">--%>
<%--                                    <p><b>Status:</b>--%>
<%--                                        <span class="badge bg-secondary text-wrap" style="width: 5rem;">ended</span>--%>
<%--                                    </p>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                            --%>
<%--                        </div>--%>
<%--                        <div class="row">--%>
<%--                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">--%>
<%--                                <div class="text-right">--%>
<%--                                    <a href="#">--%>
<%--                                        <button type="button" id="submit" name="submit"--%>
<%--                                                class="btn btn-outline-danger mx-1 mt-1">Error--%>
<%--                                        </button>--%>
<%--                                    </a>--%>
<%--                                </div>--%>
<%--                            </div>--%>
<%--                        </div>--%>
<%--                    </div>--%>
<%--                </div>--%>
<%--            </div>--%>


        </div>
    </div>
        <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    <script type="text/javascript">
        $('#confirm-cancel-button').on("click", function () {
            location.href = "${pageContext.request.contextPath}/contracts/cancel/${contractInfo.id}";
        });
        $('#confirm-error-button').on("click", function () {

            $("#error-alert").show(1000);
            $("#error-alert").show().delay(5000).fadeOut();
            <%--location.href = "${pageContext.request.contextPath}/owner/contracts/cancel/${contractInfo.id}";--%>
        });

    </script>
</body>
</html>