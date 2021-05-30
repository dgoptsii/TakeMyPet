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
            <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <b><h5 class="mt-2 mb-2 text-success">List of contracts</h5></b>
            </div>

            <c:choose>
            <c:when test="${(not empty message)}">
                <p> ${message} </p>
            </c:when>

            <c:otherwise>
                <div>
                    <c:forEach var="item" items="${contractsList}">
                        <div class="card">
                            <div class="card-header">
                                <b> <h6 class="mb-2 text-success">Contract</h6></b>
                            </div>
                            <div class="card-body">
                                <div class="row g-2">
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                        <div class="form-col">
                                            <p><b>Owner:</b> <c:out value="${item.owner.name}"/> <c:out
                                                    value="${item.owner.surname}"/></p>
                                            <p><b>Pet:</b><span> <c:out value="${item.animal.name}"/></span></p>
                                            <p><b>Status:</b>




                                                <c:choose>
                                                    <c:when test="${item.status =='ACTIVE'}">
       <span class="badge bg-success text-wrap" style="width: 5rem; color:white">
                                            active
                                        </span>

                                                    </c:when>
                                                    <c:when test="${item.status =='WAITING'}">
    <span class="badge bg-warning text-wrap" style="width: 5rem; ">
                                            waiting
                                        </span>
                                                    </c:when>
                                                    <c:when test="${item.status =='FINISHED'}">
 <span class="badge bg-secondary text-wrap" style="width: 5rem; color:white">
                                            finished
                                        </span>
                                                    </c:when>
                                                    <c:otherwise>

                                        <span class="badge bg-danger text-wrap" style="width: 5rem; color:white">
                                            emergency
                                        </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </p>
                                        </div>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                        <div class="form-col">
                                            <p><b>Start date:</b><span> ${item.startDate}</span></p>
                                            <p><b>End date:</b><span> ${item.endDate}</span></p>
                                        </div>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 my-auto">
                                        <div class="form-col text-right">
                                            <a href="${pageContext.request.contextPath}/host/contracts/${item.id}">
                                                <button type="button" id="submit" name="submit"
                                                        class="btn btn-outline-success mx-1 mt-1">See->
                                                </button>
                                            </a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>
            </c:otherwise>


        </div>
    </div>
</div>

</body>
</html>