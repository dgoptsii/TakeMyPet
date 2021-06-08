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

<c:if test="${not empty getAlert and getAlert=='success'}">
    <c:set var="getAlert" value="" scope="session"/>
<%--    <c:set var="message" value="" scope="session"/>--%>
    <script>
        $(document).ready(function () {
            $("#success-alert").show(1000);
            $("#success-alert").show().delay(5000).fadeOut();
        });
    </script>

</c:if>

<div class="container">
    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">

            <div class="alert alert-success" role="alert" id="success-alert" style="display:none;">
                <c:if test="${not empty SuccessMessage}">
                    <c:out value="${SuccessMessage}"/>
                </c:if>
            </div>

            <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                <b><h5 class="mt-2 mb-2 text-success">List of contracts</h5></b>
            </div>

            <div class="card pt-3">
                <div class="card-body">
                    <form id="request"
                          action="${pageContext.request.contextPath}/owner/contracts?status=${status}">

                        <div class="row gutters row row-cols-1 row-cols-sm-2 row-cols-md-4">
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4  col-12">
                                <div class="form-group">
                                    <select class="form-control text-center" name="status">

                                        <option value="ALL" ${ (status=='ALL') ? 'selected' : ''}>All</option>
                                        <option value="NEW" ${ (status=='NEW') ? 'selected' : ''}>New</option>
                                        <option value="WAITING" ${ (status=='WAITING') ? 'selected' : ''}>Waiting
                                        </option>
                                        <option value="ACTIVE" ${ (status=='ACTIVE') ? 'selected' : ''}>Active</option>
                                        <option value="FINISHED" ${ (status=='FINISHED') ? 'selected' : ''}>Finished
                                        </option>
                                        <option value="RATED" ${ (status=='RATED') ? 'selected' : ''}>Rated</option>
                                        <option value="EMERGENCY" ${ (status=='EMERGENCY') ? 'selected' : ''}>
                                            Emergency
                                        </option>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                <div class="form-group">
                                    <div class="text-center" id="searchB">
                                        <input type="submit" class="btn btn-success" value='Search'/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </form>
                </div>
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
                                    <b><h6 class="mb-2 text-success">Contract</h6></b>
                                </div>
                                <div class="card-body">
                                    <div class="row g-2">
                                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                            <div class="form-col">
                                                <p><b>Owner:</b> <c:out
                                                        value="${item.owner.name} ${item.owner.surname}"/></p>
                                                <p><b>Pet:</b><span> <c:out value="${item.animal.name}"/></span></p>
                                                <p><b>Status:</b>

                                                    <c:choose>
                                                        <c:when test="${item.status =='NEW'}">
           <span class="badge bg-info text-wrap" style="width: 5rem; color:white">
                                                new
                                            </span>

                                                        </c:when>
                                                        <c:when test="${item.status =='WAITING'}">
        <span class="badge bg-warning text-wrap" style="width: 5rem; ">
                                                waiting
                                            </span>
                                                        </c:when>
                                                        <c:when test="${item.status =='ACTIVE'}">
           <span class="badge bg-success text-wrap" style="width: 5rem; color:white">
                                                active
                                            </span>

                                                        </c:when>
                                                        <c:when test="${item.status =='FINISHED'}">
     <span class="badge bg-secondary text-wrap" style="width: 5rem; color:white">
                                                finished
                                            </span>

                                                        </c:when>
                                                        <c:when test="${item.status =='RATED'}">
     <span class="badge bg-secondary text-wrap" style="width: 5rem; color:white">
                                                rated
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
                                                <p><b>Start date:</b> <c:out value="${item.startDate}"/></p>
                                                <p><b>End date:</b> <c:out value="${item.endDate}"/></p>
                                                <c:if test="${item.status =='RATED' }">
                                                    <div class="form-group">
                                                        <label for="rating"><b>Rating:</b> <c:out
                                                                value="${item.rating}"/></label>
                                                        <div id="rating" class="str_Rating_detail">
                                                            <div data-rating="<c:out value="${item.rating}"/>"
                                                                 data-rateyo-read-only="false"
                                                                 class="rateYo bookrating px-0"></div>
                                                        </div>
                                                    </div>
                                                </c:if>
                                            </div>
                                        </div>
                                        <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 my-auto">
                                            <div class="form-col text-right">
                                                <a href="${pageContext.request.contextPath}/owner/contracts/${item.id}">
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
                        <nav aria-label="Navigation">
                            <ul class="pagination">
                                <c:if test="${currentPage != 1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/owner/contracts?page=${currentPage-1}">
                                        Previous</a>
                                    </li>
                                </c:if>

                                <c:forEach begin="1" end="${totalPages}" var="i">
                                    <c:choose>
                                        <c:when test="${currentPage eq i}">
                                            <li class="page-item active"><a class="page-link">
                                                    ${i} <span class="sr-only">(current)</span></a>
                                            </li>
                                        </c:when>
                                        <c:otherwise>
                                            <li class="page-item"><a class="page-link"
                                                                     href="${pageContext.request.contextPath}/owner/contracts?page=${i}">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${currentPage lt totalPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/owner/contracts?page=${currentPage+1}">
                                        Next
                                    </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </div>
                </c:otherwise>
            </c:choose>
        </div>
    </div>
</div>


<script>
    $(".rateYo").each(function (e) {
        var rating = $(this).attr("data-rating");
        $(this).rateYo({
            onSet: function (rating) {
                ratingFunc(rating, $(this).next().next().val());
            },
            rating: rating,
        });
        $(this).rateYo('option', 'readOnly', true);
    });

    function ratingFunc(rating) {
    }
</script>
<footer class="container text-center py-3 pt-3 " id="foot">
    <div class="form-group text-center pb-0">
        <img src="https://cdn1.savepice.ru/uploads/2021/6/1/df08565d31e0a625add74d94afbf176b-full.png"
             width="160" height="160">
        <p>&copy; 2021 FrogProg Inc.</p>
    </div>

</footer>
</body>
</html>