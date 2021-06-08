<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ include file="/WEB-INF/jspf/directive/taglib.jspf" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <%@ include file="/WEB-INF/jspf/head.jspf" %>
    <!-- datepicker -->
    <script src="https://unpkg.com/gijgo@1.9.13/js/gijgo.min.js" type="text/javascript"></script>
    <link href="https://unpkg.com/gijgo@1.9.13/css/gijgo.min.css" rel="stylesheet" type="text/css" />
    
</head>
<body>
<%@ include file="/WEB-INF/jspf/owner-profile-nav.jspf" %>

    <c:set var="post" value="${postInfo}"/>
    <c:set var="host" value="${postInfo.host}"/>


    <c:if test="${not empty getAlert and getAlert=='error'}">
        <c:set var="getAlert" value="" scope="session"/>
        <c:set var="errorMessage" value="" scope="session"/>
        <script>
            $(document).ready(function () {
                $("#error-alert").show(1000);
                $("#error-alert").show().delay(5000).fadeOut();
            });
        </script>
    </c:if>

    <div class="container">



        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">

                <div class="alert alert-danger" role="alert" id="error-alert" style="display:none;">
                    Error:
                    <c:if test="${not empty errorMessage}">
                        <c:out value="${errorMessage}"/>
                    </c:if>
                </div>
                <form id="request"
                      action="${pageContext.request.contextPath}/owner/createContract/${post.id}" method="post">
                    <div class="card">
                        <div class="card-header">
                            <h6 class="mb-2 text-success">Contract</h6>
                        </div>
                        <div class="card-body">
                            <div class="row gutters">
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-col">
                                        <p><b>Host:</b> <c:out value="${host.name} ${host.surname}"/></p>
                                        <p><b>Pet:</b><span> <c:out value="${post.animal.name}"/></span></p>
                                        <p><b>Max term:</b><span> <c:out value="${post.maxDays}"/> days</span></p>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-col">
                                        <p><b>Address:</b> <c:out value="${host.city}"/>, <c:out value="${host.region}"/></p>
                                        <p><b>Phone:</b><span> <c:out value="${host.phone}"/></span></p>
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-col">
                                        Start Date: <input type="text"  value="<c:out value="${startDate}"/>" name="startDate" id="startDate" width="276" />
                                    </div>
                                </div>
                                <div class="col-xl-6 col-lg-6 col-md-6 col-sm-6 col-12">
                                    <div class="form-col align-right">
                                        End Date: <input type="text" value="<c:out value="${endDate}"/>" name="endDate" id="endDate" width="276" />
                                    </div>
                                </div>
                                <script>
                                    var today = new Date(new Date().getFullYear(), new Date().getMonth(), new Date().getDate());
                                    $('#startDate').datepicker({
                                        uiLibrary: 'bootstrap4',
                                        iconsLibrary: 'fontawesome',
                                        minDate: today,
                                        maxDate: function () {
                                            return $('#endDate').val();
                                        }
                                    });
                                    $('#endDate').datepicker({
                                        uiLibrary: 'bootstrap4',
                                        iconsLibrary: 'fontawesome',
                                        minDate: function () {
                                            return $('#startDate').val();
                                        }
                                    });
                                </script>
                                <div class="col-xl-12 col-lg-12 col-md-12 col-sm-12 col-12">
                                    <div class="my-2 text-right">
<%--                                        <button  class="btn btn-primary mx-1 mt-1" id="confirm-button">--%>
<%--                                            Submit--%>
<%--                                        </button>--%>
                                        <input type="submit" class="btn btn-outline-success mx-1 mt-1" value='Submit'/>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </form>
        </div>
    </div>
    </div>
<script type="text/javascript">

    function myFunction() {
        // Declare variables
        // var input, filter, table, tr, td, i, txtValue;
        // var input = document.getElementById("myInput");

        $('#confirm-button').on("click", function () {
            var input = document.getElementById("startDate").value;
            echo(input);
            $('<input>', {
                // type: 'hidden',
                name: 'startDate',
                value: input
            }).appendTo('#request');
        });
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