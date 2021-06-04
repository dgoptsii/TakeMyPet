<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>

<%--  DO NOT TOUCH! (c) DARIA
  <style>--%>
<%-- --%>
<%--        .form-control:focus {--%>
<%--            border-color: #ff80ff;--%>
<%--            box-shadow: 0px 1px 1px rgba(0, 0, 0, 0.075) inset, 0px 0px 8px rgba(255, 100, 255, 0.5);--%>
<%--        }--%>
<%--    </style>--%>

</head>
<body>
<%@ include file="/WEB-INF/jspf/owner-profile-nav.jspf" %>

<c:if test="${not empty getAlert and getAlert=='success'}">
    <c:set var="getAlert" value="" scope="session"/>
    <script>
        $(document).ready(function () {
            $("#success-alert").show(1000);
            $("#success-alert").show().delay(5000).fadeOut();
        });
    </script>
</c:if>

<div class="container">

    <div class="row gutters d-flex justify-content-center">
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-5">
            <div class="alert alert-success" role="alert" id="success-alert" style="display:none;">
                Contract created!
            </div>

            <div class="card pt-3">
                <div class="card-body">
            <form id="request"
                  action="${pageContext.request.contextPath}/owner/posts?page=${currentPage}&animal=${animal}&maxDays=${maxDays}">
                <div class="row gutters row row-cols-1 row-cols-sm-2 row-cols-md-4">
                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4  col-12">
                        <div class="form-group">
                            <select class="form-control text-center" id="petCategory" name="animal">
<%--                                <option value="" disabled selected>Animal</option>--%>
                                <option value="0" ${ (animal=='0') ? 'selected' : ''} >Choose animal</option>
                                <c:forEach var="item" items="${animals}">
                                    <option value="${item.id}" ${ (animal==item.id) ? 'selected' : ''} >${item.name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4  col-12">
                        <div class="form-group text-center">
<%--                            <select class="form-control text-center" id="term">--%>
<%--                                <option>1 day</option>--%>
<%--                                <option>up to 3 days</option>--%>
<%--                                <option>up to 5 days</option>--%>
<%--                                <option>up to 10 days</option>--%>
<%--                                <option>up to 15 days</option>--%>
<%--                              </select>--%>
                        <label id="textInput" for="nDays">Days: <span id="demo"></span> </label>
                        <div class="slidecontainer">
                            <input type="range" min="1" max="14" value="${maxDays}" name="maxDays" class="slider" id="nDays">
                        </div>
                        <script>
                            var slider = document.getElementById("nDays");
                            var output = document.getElementById("demo");
                            output.innerHTML = slider.value;

                            slider.oninput = function() {
                                output.innerHTML = this.value;
                            }
                            </script>
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
<%--                    </div>--%>
<%--                </div>--%>

            <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
               <b> <h5 class="pt-5 mb-2 text-success">Search results</h5></b>
            </div>

            <div>

                <c:choose>
                    <c:when test="${(not empty message)}">
                        <p> ${message} </p>
                    </c:when>

                    <c:otherwise>
                        <div>
                            <c:forEach var="item" items="${posts}">
                                <div class="card">
                                    <div class="card-body">
                                        <div class="row g-2">
                                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                                <h6 class="mb-2 text-success">Post</h6>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                                <div class="form-col">
                                                    <p><b>Host:</b> <c:out
                                                            value="${item.host.name} ${item.host.surname}"/></p>
                                                    <p><b>Pet:</b><span> <c:out value="${item.animal.name}"/></span></p>
                                                    <p><b>Term:</b><span> <c:out value="${item.maxDays}"/> days</span>
                                                    </p>

                                                </div>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                                <div class="form-col text-left">
                                                    <div class="form-group">
                                                        <label for="rating" class="pl-1"><b>Rating: <c:out value="${item.host.rating}"/></b></label>
<%--                                                        <div id="rating" class="form-control-static">--%>
<%--                                                            <script>--%>
<%--                                                                $(function () {--%>
<%--                                                                    $("#rating").rateYo({--%>
<%--                                                                        rating: <c:out value="${item.host.rating}"/>--%>
<%--                                                                    });--%>
<%--                                                                    $("#rating").rateYo('option', 'readOnly', true);--%>
<%--                                                                });--%>
<%--                                                            </script>--%>
<%--                                                          --%>
                                                                 <div id="rating" class="str_Rating_detail">
                                                                    <div data-rating="<c:out value="${item.host.rating}"/>" data-rateyo-read-only="false" class="rateYo bookrating px-0" ></div>
                                                                </div>

<%--                                                        </div>--%>
                                                    </div>
                                                </div>
                                            </div>
                                            <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 my-auto">
                                                <div class="form-col text-right">
                                                    <a href="${pageContext.request.contextPath}/owner/posts/${item.id}">
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
                            <nav aria-label="Navigation for admin">
                                <ul class="pagination">
                                    <c:if test="${currentPage != 1}">
                                        <li class="page-item"><a class="page-link"
                                                                 href="${pageContext.request.contextPath}/owner/posts?page=${currentPage-1}&animal=${animal}&maxDays=${maxDays}">
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
                                                <li class="page-item">
                                                    <a class="page-link"
                                                       href="${pageContext.request.contextPath}/owner/posts?page=${i}&animal=${animal}&maxDays=${maxDays}">${i}</a>
                                                </li>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:forEach>

                                    <c:if test="${currentPage lt totalPages}">
                                        <li class="page-item">
                                            <a class="page-link"
                                               href="${pageContext.request.contextPath}/owner/posts?page=${currentPage+1}&animal=${animal}&maxDays=${maxDays}">
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
                // starWidth: "20px",
                // spacing: "5px",
                // precision: 2,
            });
            $(this).rateYo('option', 'readOnly', true);
        });
        function ratingFunc(rating){}
    </script>

        <footer class="container text-center py-3 pt-3 " id="foot" >
        <div class="form-group text-center pb-0">
            <img src="https://cdn1.savepice.ru/uploads/2021/6/1/478a03651046359a001893198fcc996e-full.png"
                 width="140" height="140">
            <p>&copy; 2021 FrogProg Inc.</p>
        </div>

    </footer>
</div>
</body>
</html>