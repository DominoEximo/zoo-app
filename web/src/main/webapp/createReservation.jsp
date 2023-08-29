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
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script>
      $( function() {
        $( "#reservationDate" ).datepicker({
                                                                                                                         dateFormat: 'yy-mm-dd',
                                                                                                                         changeMonth: true,
                                                                                                                         changeYear: true
                                                                                                                     });
        $( "#visitDate" ).datepicker({
                                                                                                                   dateFormat: 'yy-mm-dd',
                                                                                                                   changeMonth: true,
                                                                                                                   changeYear: true
                                                                                                               });
      } );
    </script>
</head>
<body>
    <div class="container">
      <h2>Zoo</h2>
      <form action= "createReservationServlet" method="GET">
        <input type="hidden" name="zoo" value="${currentZoo.name}">
        <div class="form-group">
          <label for="id">Name</label>
          <input type="text" class="form-control" id="id" placeholder="ID" name="id">
        </div>
        <div class="form-group">
          <label for="name">Name</label>
          <input type="text" class="form-control" id="name" placeholder="Name" name="name">
        </div>
        <div class="form-group">
          <label for="reservationDate">Reservation Date</label>
          <input type="text" class="form-control" id="reservationDate" placeholder="dd/MM/yyyy" name="reservationDate">
        </div>
        <div class="form-group ">
          <label for="visitDate">Visit Date</label>
          <input type="text" class="form-control" id="visitDate" placeholder="dd/MM/yyyy" name="visitDate">
        </div>
        <div class="form-group">
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketType1" name="ticketType" value="adult">Adult
          </label>
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketType2" name="ticketType" value="kid">Kid
          </label>
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketType3" name="ticketType" value="retired">Retired
          </label>
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketType4" name="ticketType" value="group">Group
          </label>
        </div>
        <div class="form-group">
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketVariant1" name="ticketVariant" value="fullDay">Full Day
          </label>
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketVariant2" name="ticketVariant" value="afternoon">Afternoon
          </label>
          <label class="radio-inline">
            <input type="radio" class="form-check-input" id="ticketVariant3" name="ticketVariant" value="forenoon">Forenoon
          </label>
        </div>
        <button type="submit" class="btn btn-default">Add</button>
      </form>
    </div>

</body>
</html>