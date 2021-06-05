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
        <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-5">
            <div class="card">
                <div class="alert alert-danger" role="alert" id="error-alert" style="display:none;">
                    Error:
                    <c:if test="${not empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </div>
                <div class="card-header">
                    <h6 class="mb-2 text-success">Post</h6>
                </div>
                <form id="request" action="${pageContext.request.contextPath}/host/posts/edit/${post.id}" method="post">
                    <div class="card-body">
                        <div class="row gutters">
<%--                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">--%>
<%--                                <div class="form-group">--%>
<%--&lt;%&ndash;    &lt;%&ndash;                                <label for="petCategory">Pet</label>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    <select class="form-control text-center" id="petCategory" name="animal">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        <c:forEach var="item" items="${animals}">&ndash;%&gt;--%>
<%--&lt;%&ndash;                                            <option value="${item.id}" ${ (post.animal.id==item.id) ? 'selected' : ''} >${item.name}</option>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                        </c:forEach>&ndash;%&gt;--%>
<%--&lt;%&ndash;                                    </select>&ndash;%&gt;&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                    <form:select path="animal" class="form-control" id="petCategory">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        <c:forEach var="item" items="${animals}">&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                            <option value="${item.id}" ${ (animal==item.id) ? 'selected' : ''} >${item.name}</option>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                        </c:forEach>&ndash;%&gt;--%>
<%--                                    &lt;%&ndash;                                      </form:select>&ndash;%&gt;--%>
<%--                                </div>--%>
<%--                            </div>--%>
                            <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                <div class="form-group">
                                    <label for="daysRange">Term: <span id="demo"></span> days</label>
                                </div>
                                <div class="form-group">
                                    <input type="range" name="maxDays" class="slider" min="1" max="14"
                                           id="daysRange" value="${post.maxDays}">
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
                            <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                <div class="text-right">
<%--                                    <a href="#">--%>
                                        <button type="button"
                                                id="submit2" name="submit" class="btn btn-success"
                                                data-toggle="modal" data-target="#edit-file-modal">
                                            Save
                                        </button>
<%--                                        <button type="button" id="submit" name="submit" class="btn btn-success">Save--%>
<%--                                        </button>--%>
<%--                                    </a>--%>
                                </div>
                            </div>
                        </div>
                    </div>
                    <%@ include file="/WEB-INF/jspf/edit_dialog.jspf" %>
                </form>
            </div>
        </div>
    </div>
</div>
<%@ include file="/WEB-INF/jspf/footer.jspf" %>


<script type="text/javascript">
    $('#confirm-edit-button').on("click", function () {
        $("#request").submit();
    });
</script>
</body>
</html>