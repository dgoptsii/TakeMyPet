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

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
                <div class="row gutters row row-cols-1 row-cols-sm-2 row-cols-md-4">
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="petCategory">Pet</label>
                            <select class="form-control" id="petCategory">
                                <option>Small dog</option>
                                <option>Medium dog</option>
                                <option>Large dog</option>
                                <option>Cat</option>
                              </select>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="term">Term</label>
                            <select class="form-control" id="term">
                                <option>1 day</option>
                                <option>up to 3 days</option>
                                <option>up to 5 days</option>
                                <option>up to 10 days</option>
                                <option>up to 15 days</option>
                              </select>
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label for="address">Address</label>
                            <input type="text" class="form-range form-control" id="address">
                        </div>
                    </div>
                    <div class="col-xl-3 col-lg-6 col-md-6 col-sm-6 col-12">
                        <div class="form-group">
                            <label></label>
                            <div class="text-center">
                                <a href="#"><button type="button" id="submit" name="submit" class="btn btn-primary">Search</button></a>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="text-center col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                    <h6 class="pt-5 mb-2 text-primary">Search results</h6>
                </div>

                <div>
                    <c:forEach var="item" items="${postsList}">
                        <div class="card">
                            <div class="card-body">
                                <div class="row g-2">
                                    <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                        <h6 class="mb-2 text-primary">Post</h6>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                        <div class="form-col">
                                            <p><b>Host:</b> <c:out value="${item.host.name} ${item.host.surname}"/></p>
                                            <p><b>Pet:</b><span> <c:out value="${item.animal.name}"/></span></p>
                                            <p><b>Term:</b><span> <c:out value="${item.maxDays}"/> days</span></p>

                                        </div>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12">
                                        <div class="form-col text-right">
                                            <div class="form-group">
                                                <label for="rating"><b>Rating: <c:out value="${item.host.rating}"/></b></label>
                                                <div class="form-control-static">
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-warning fa fa-star"></i>
                                                    <i class="text-secondary fa fa-star"></i>
                                                    <i class="text-secondary fa fa-star"></i>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-xl-4 col-lg-4 col-md-4 col-sm-4 col-12 my-auto">
                                        <div class="form-col text-right">
                                            <a href="${pageContext.request.contextPath}/owner/posts/${item.id}"><button type="button" id="submit" name="submit" class="btn btn-primary mx-1 mt-1">See-></button></a>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </c:forEach>
                </div>

            </div>
        </div>
    </div>

</body>
</html>