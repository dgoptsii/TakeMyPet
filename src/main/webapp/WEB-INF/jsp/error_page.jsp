
<html>

<c:set var="title" value="Error" scope="page"/>
<%@ include file="/WEB-INF/jspf/head.jspf" %>

<body>

<table id="main-container">

<%--    <%@ include file="/WEB-INF/jspf/header.jspf" %>--%>

    <div class="container">
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
                    <div>
                        <p> ${exception.stackTrace}</p>
                    </div>

                <%-- CONTENT --%>
            </td>
        </tr>

<%--        <%@ include file="/WEB-INF/jspf/footer.jspf" %>--%>
    </div>
</table>
</body>
</html>