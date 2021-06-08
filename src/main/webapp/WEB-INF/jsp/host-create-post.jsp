<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

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
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-5">
            <div class="card">
                <div class="card-header">
                    <h6 class="mb-2 text-success">Post</h6>
                </div>
                <form action="${pageContext.request.contextPath}/host/createPost" method="post">
                    <div class="card-body">
                        <div class="row gutters">
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">

                                    <label for="petCategory">Pet</label>
                                    <select class="form-control" id="petCategory" name="pet">
                                        <c:forEach var="animal" items="${animals}">
                                            <option>${animal.name}</option>
                                        </c:forEach>
                                    </select>
                                </div>
                            </div>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-col">
                                    <label for="daysRange">Term: <span id="demo"></span>  days</label>
                                    <div class="form-group">
                                    <input type="range" class="slider" min="1" max="14" step="1.0"
                                           id="daysRange" name="days">
                                    <script>
                                        var slider = document.getElementById("daysRange");
                                        var output = document.getElementById("demo");
                                        output.innerHTML = slider.value;

                                        slider.oninput = function() {
                                            output.innerHTML = this.value;
                                        }
                                    </script>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
                                    <a href="/host/posts">
                                        <input type="submit" id="submit" name="submit" value="Create"
                                               class="btn btn-outline-success">
                                    </a>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>
</body>
</html>