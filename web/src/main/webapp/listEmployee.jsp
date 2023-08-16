<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<html>
<head></head>
<body>
<h2>

<c:forEach var="employee" items="${currentZoo.eployees}">
            <table>
                 <tr>
                     <c:out value="${employee}"></c:out>
                 </tr>
            </table>
</c:forEach>

</h2>

</body>
</html>