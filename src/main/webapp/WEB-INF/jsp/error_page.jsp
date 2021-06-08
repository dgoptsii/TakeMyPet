
<html>

<c:set var="title" value="Error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<%@ include file="/WEB-INF/jspf/simple-nav.jspf" %>

<%--<table id="main-container">--%>

<%--    <%@ include file="/WEB-INF/jspf/header.jspf" %>--%>

    <div class="container">
        <div class="row gutters d-flex justify-content-center">
            <div class="col-xl-9 col-lg-9 col-md-12 col-sm-12 col-12 mt-3">
                <div class="row gutters d-flex justify-content-center">
                    <div class="card pt-3">
                        <div class="card-body">
                            <table id="main-container">
                        <tr>
                            <td class="content">
                                <%-- CONTENT --%>
                                <h2 class="error">
                                    The following error occurred
                                </h2>
                                <%-- this way we get the error information (error 404)--%>
                                <c:set var="code" value="${requestScope['javax.servlet.error.status_code']}"/>

                                <c:if test="${not empty status}">
                                    <h3>Error code: ${status}</h3>
                                </c:if>
                                    <div>
                                        <h5>Exception: ${exception.message}</h5>
                                    </div>
                <%--                    <div>--%>
                <%--                        <p> ${exception.stackTrace}</p>--%>
                <%--                    </div>--%>
                                <%-- CONTENT --%>
                            </td>
                        </tr>
                            </table>
                        </div>
                    </div>
                </div>
            </div>
        </div>

<%--        <%@ include file="/WEB-INF/jspf/footer.jspf" %>--%>
    </div>

<%--</table>--%>

</body>
</html>