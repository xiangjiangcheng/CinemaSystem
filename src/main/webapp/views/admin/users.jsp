<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ include file="../templates/header.jsp"%>
<link rel="stylesheet" href="<c:url value='/static/styles/common.css' />" />
</head>

<body>
<%@ include file="../templates/navbar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-12">

            <table id="userTable">
                <thead></thead>
                <tbody></tbody>
            </table>
        </div>
    </div>
</div>

<%@ include file="../templates/footer.jsp"%>
</body>
</html>