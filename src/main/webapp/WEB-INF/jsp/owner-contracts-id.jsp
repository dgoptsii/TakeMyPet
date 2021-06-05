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
<c:set var="owner" value="${ownerInfo}"/>

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
                                <span><b>Owner details:</b><br> <c:out
                                        value="${owner.name} ${owner.surname}"/></span><br>
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

                    <form id="request" role="form"
                          action="${pageContext.request.contextPath}/contract/rate/${contract.id}" method="post">
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
                                            <label for="rating"><b>Rating:</b> <c:out
                                                    value="${contract.rating}"/></label>
                                                <%--                                        <label for="rating"><b>Rating:</b> <span id="demo"></span></label>--%>
                                            <div class="str_Rating_detail">
                                                <!--star rating-->
                                                <div id="rating" data-rating="0" data-rateyo-read-only="false"
                                                     class="rateYo bookrating"></div>
                                                <input type="hidden" name="rating" class="bookrating">
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
                                             <span class="badge bg-secondary text-wrap"
                                                   style="width: 5rem; color:white">
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
                                                data-toggle="modal" data-target="#cancel-file-modal">
                                            Cancel
                                        </button>
                                        <%--</a>--%>
                                    </c:if>
                                    <c:if test="${contract.status =='FINISHED'}">
                                        <%--                                    <a href="#">--%>
<%--                                        <a href="${pageContext.request.contextPath}/contract/rate/${contract.id}">--%>
                                            <button type="button"
                                                    id="rate" name="submit" class="btn btn-warning mx-1 mt-1"
                                                    data-toggle="modal" data-target="#rate-file-modal">
<%--                                            >--%>
                                                Rate
                                            </button>
                                        </a>
                                        <%--                                    <button type="button"--%>
                                        <%--                                            id="rate" name="submit" class="btn btn-warning mx-1 mt-1">--%>
                                        <%--                                            &lt;%&ndash;                                            data-toggle="modal" data-target="#cancel-file-modal"&ndash;%&gt;--%>
                                        <%--                                        Rate--%>
                                        <%--                                    </button>--%>
                                        <%--                                    </a>--%>
                                    </c:if>

                                    <button type="button"
                                            id="submit2" name="submit" class="btn btn-outline-danger mx-1 mt-1"
                                            data-toggle="modal" data-target="#error-file-modal">
                                        Error
                                    </button>
                                </div>
                            </div>
                        </div>
                    </form>

                </div>
                <%@ include file="/WEB-INF/jspf/cancel_dialog.jspf" %>
                <%@ include file="/WEB-INF/jspf/error_dialog.jspf" %>
                <%@ include file="/WEB-INF/jspf/rate_dialog.jspf" %>
            </div>

        </div>
    </div>
    <%@ include file="/WEB-INF/jspf/footer.jspf" %>

    <script type="text/javascript">
        $('#confirm-rate-button').on("click", function () {
            $("#request").submit();
           // location.href = "${pageContext.request.contextPath}/contract/rate/${contractInfo.id}";
        });
        $('#confirm-cancel-button').on("click", function () {
            location.href = "${pageContext.request.contextPath}/contracts/cancel/${contractInfo.id}";
        });
        $('#confirm-error-button').on("click", function () {

            $("#error-alert").show(1000);
            $("#error-alert").show().delay(5000).fadeOut();
            <%--location.href = "${pageContext.request.contextPath}/owner/contracts/cancel/${contractInfo.id}";--%>
        });

    </script>
    <script>
        $(".rateYo").each(function (e) {

            var rating = $(this).attr("data-rating");
            var show = $(this).attr("demo");
            $(this).rateYo({
                onSet: function (rating) {
                    $(this).next().val(rating);
                    ratingFunc(rating, $(this).next().next().val());
                },
                rating: rating,
                fullStar: true
            });
        });

        function ratingFunc(rating, lang) {
        }
    </script>
</body>
</html>