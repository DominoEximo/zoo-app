<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>
<%@page import="hu.neuron.mentoring.zooapp.service.*"%>
<%@ taglib prefix="c" uri="jakarta.tags.core"%>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt"%>
<%@ taglib prefix="fn" uri="jakarta.tags.functions"%>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css">
    <%@ include file="header.jsp"%>
</head>
<body>
<div class="container">

        <c:forEach var="zoo" items="${Zoos}">
            <table id="zoos" class="table">
                 <thead>
                        <tr>
                              <th scope="col">${zoo.name}</th>
                        </tr>
                 </thead>
                 <tbody>
                    <table id=${zoo.name} class="table">
                          <thead>
                            <tr>
                              <th scope="col">Name</th>
                              <th scope="col">Reservation Date</th>
                              <th scope="col">Visit Date</th>
                              <th scope="col">Price</th>
                            </tr>
                          </thead>
                          <tbody>
                            <c:forEach var="reservation" items="${Reservations.findByZoo(zoo)}">

                                             <tr>
                                                <td>
                                                    <c:out value="${reservation.name}"></c:out>
                                                </td>

                                                <td>
                                                    <c:out value="${reservation.reservationDate}"></c:out>
                                                </td>

                                                <td>
                                                    <c:out value="${reservation.visitDate}"></c:out>
                                                </td>
                                                <td>
                                                    <c:out value="${reservation.price}"></c:out>
                                                </td>
                                             </tr>

                            </c:forEach>
                          </tbody>
                    </table>
                 </tbody>
            </table>

        </c:forEach>



</div>
</body>


</html>