<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="template" tagdir="/WEB-INF/tags"%>


<!DOCTYPE html>
<html lang="en">
<head>
    <title>Bootstrap Case</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <%@ include file="header.jsp"%>
    <link rel="stylesheet" href="//code.jquery.com/ui/1.13.2/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-3.6.0.js"></script>
    <script src="https://code.jquery.com/ui/1.13.2/jquery-ui.js"></script>
    <script>
      $( function() {

        $( "#name" ).autocomplete({
          source: "ZooNames",
          minLength: 1,
        });
      } );
      </script>
</head>
<body>
<div class="container">
      <h2>Select a Zoo</h2>
      <form action= "animals" method="GET">
        <div class="form-group ui-widget">
          <label for="name">Name of Zoo:</label>
          <input type="text" class="form-control" id="name" placeholder="Name of Zoo" name="name">
        </div>


        <button type="submit" class="btn btn-default">Search</button>
      </form>
    </div>

</body>
</html>