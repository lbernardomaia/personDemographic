<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
    <meta http-equiv="content-type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <meta name="description" content="">
    <meta name="author" content="">
    <meta http-equiv="cache-control" content="max-age=0" />
    <meta http-equiv="cache-control" content="no-cache" />
    <meta http-equiv="expires" content="0" />
    <meta http-equiv="expires" content="Tue, 01 Jan 1980 1:00:00 GMT" />
    <meta http-equiv="pragma" content="no-cache" />

    <title>Person Demographic</title>

    <link href="<c:url value="bootstrap/bootstrap.css" />" rel="stylesheet">
    <link href="<c:url value="bootstrap/offcanvas.css" />" rel="stylesheet">

    <script src="<c:url value="bootstrap/ie-emulation-modes-warning.js" />"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<nav class="navbar navbar-fixed-top navbar-inverse">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="home">Person Demographic</a>
        </div>
    </div><!-- /.container -->
</nav><!-- /.navbar -->

<div class="container">

    <div class="row row-offcanvas row-offcanvas-right">

        <div class="col-xs-12 col-sm-9">
            <p class="pull-right visible-xs">
                <button type="button" class="btn btn-primary btn-xs" data-toggle="offcanvas">Toggle nav</button>
            </p>
            <div class="row">
                <div class="col-xs-12">
                    <h2>List All Records</h2>

                    <c:choose>
                        <c:when test="${empty persons}">
                            <div class="alert alert-warning" role="alert">
                                No records have been created
                            </div>
                        </c:when>
                        <c:otherwise>
                            <table class="table table-striped">
                                <thead>
                                <tr>
                                    <th>Name</th>
                                    <th>PPSN</th>
                                    <th>Date Of Birth</th>
                                    <th>Mobile Phone</th>
                                </tr>
                                </thead>
                                <tbody>
                                <c:forEach items="${persons}" var="person">
                                    <tr>
                                        <td>${person.name}</td>
                                        <td>${person.ppsNumber}</td>
                                        <td><fmt:formatDate value="${person.dateBirth}" pattern="dd/MM/yyyy" /></td>
                                        <td>${person.mobilePhone}</td>
                                    </tr>
                                </c:forEach>
                                </tbody>
                            </table>
                        </c:otherwise>
                    </c:choose>
                    <button type="button" class="btn btn-link"><a href="home">Back to Home</a></button>   <br />
               </div>
            </div><!--/row-->
        </div><!--/.col-xs-12.col-sm-9-->

        <div class="col-xs-6 col-sm-3 sidebar-offcanvas" id="sidebar">
            <div class="list-group">
                <a href="home" class="list-group-item ">Home</a>
                <a href="new" class="list-group-item ">Add a new Record</a>
                <a href="list" class="list-group-item active">List all records</a>
            </div>
        </div><!--/.sidebar-offcanvas-->
    </div><!--/row-->

    <hr>

    <footer>
        <p></p>
    </footer>

</div><!--/.container-->


<!-- Bootstrap core JavaScript
================================================== -->
<!-- Placed at the end of the document so the pages load faster -->
<script src="<c:url value="jquery-ui/jquery-1.12.4.js" />"></script>
<script src="<c:url value="bootstrap/bootstrap.js" />"></script>
<script src="<c:url value="bootstrap/ie10-viewport-bug-workaround.js" />"></script>
<script src="<c:url value="bootstrap/offcanvas.js" />"></script>
</body>
</html>