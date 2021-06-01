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

<div class="container">
    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
            <div class="row gutters">
                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12 pb-3 mt-3">
                    <div class="text-center">
                        <button type="button" id="submit1" name="submit" class="btn btn-success">+ Add post</button>
                    </div>
                </div>
                <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <b> <h5 class="mt-2 mb-2 text-inverse">List of posts</h5></b>
                </div>
            </div>

            <div>
                <c:choose>
                    <c:when test="${(not empty message)}">
                        <p> ${message} </p>
                    </c:when>

                    <c:otherwise>

                        <c:forEach var="item" items="${postsList}">
                            <div class="card">

                                <div class="card-header">
                                    <b>    <h6 class="mb-2 text-success">Post</h6></b>
                                </div>
                                <div class="card-body">
                                    <div class="row g-2">
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <p><b>Pet:</b><span> <c:out value="${item.animal.name}"/></span></p>
                                        </div>
                                        <div class="text-right col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">

                                            <c:choose>
                                                <c:when test="${item.status =='ACTIVE'}">
                                                    <input class="btn btn btn-outline-success actionUser"
                                                           type="button"
                                                           data-toggle="modal" data-target="#edit-file-modal"
                                                           id="delete_button"
                                                           name="${item.id}"
                                                           value="Active">

                                                </c:when>
                                                <c:otherwise>
                                                    <input class="btn btn-outline-secondary actionUser"
                                                           type="button"
                                                           data-toggle="modal" data-target="#edit-file-modal"
                                                           id="delete_button"
                                                           name="${item.id}"
                                                           value="Blocked">
                                                </c:otherwise>
                                            </c:choose>
                                        </div>
                                        <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <p><b>Term:</b><span> <c:out value="${item.maxDays}"/> days</span></p>
                                        </div>
                                        <div class="text-right col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                            <a href="#"><button type="button" id="edit_b" name="submit" class="btn btn-outline-warning mr-1 mt-1">Edit</button></a>
                                            <a href="#"><button type="button" id="delete_b" name="submit" class="btn btn-outline-danger mt-1">Delete</button></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>

                        <nav aria-label="Navigation">
                            <ul class="pagination">
                                <c:if test="${currentPage != 1}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/host/posts?page=${currentPage-1}&animal=${animal}&maxDays=${maxDays}">
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
                                                                     href="${pageContext.request.contextPath}/host/posts?page=${i}&animal=${animal}&maxDays=${maxDays}">${i}</a>
                                            </li>
                                        </c:otherwise>
                                    </c:choose>
                                </c:forEach>

                                <c:if test="${currentPage lt totalPages}">
                                    <li class="page-item"><a class="page-link"
                                                             href="${pageContext.request.contextPath}/host/posts?page=${currentPage+1}&animal=${animal}&maxDays=${maxDays}">
                                        Next
                                    </a>
                                    </li>
                                </c:if>
                            </ul>
                        </nav>
                    </c:otherwise>
                </c:choose>
            </div>

        </div>
    </div>
</div>

<footer class="container text-center py-3 pt-3 " id="foot" >
    <div class="form-group text-center pb-0">
        <img src="https://cdn1.savepice.ru/uploads/2021/5/31/7c924beb31e24833a66bb65a73234d46-full.png"
             width="140" height="140">
        <p>&copy; 2021 FrogProg Inc.</p>
    </div>

</footer>
</body>
</html>