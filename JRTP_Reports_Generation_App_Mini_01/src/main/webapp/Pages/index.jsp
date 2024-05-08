<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>

<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>


<html>
<head>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>Reports_Generation_App</title>
<link
	href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css"
	rel="stylesheet"
	integrity="sha384-QWTKZyjpPEjISv5WaRU9OFeRpok6YctnYmDr5pNlyT2bRjXh0JMhjY6hW+ALEwIH"
	crossorigin="anonymous">
</head>
<body>
	<div class="container">
		<h4 class="pb-3 pt-2">Reports_Generation_Application</h4>


		<form:form action="/search" modelAttribute="search" method="POST">


			<table>
				<tr>
					<td>Plan Name</td>
					<td><form:select path="planName">
							<form:option value="">-select-</form:option>
							<form:options items="${plan_names}"/>
						</form:select></td>

					<td>Plan Statuses</td>
					<td><form:select path="planStatus">
							<form:option value="">-select-</form:option>
							<form:options items="${plan_status}"/>
						</form:select></td>

					<td>Gender</td>
					<td><form:select path="gender">
							<form:option value="">-select</form:option>
							<form:option value="Male">Male</form:option>
							<form:option value="Fe-Male">Fe-Male</form:option>
						</form:select></td>
				</tr>

				<tr>
					<td>StartDate :</td>
					<td><form:input path="startDate" type="date" /></td>

					<td>EndDate :</td>
					<td><form:input path="endDate" type="date" /></td>
				</tr>

				<tr>
					<td><input type="submit" value="Search"
						class="btn btn-primary"></td>

				</tr>

			</table>
		</form:form>

		<hr />

        <table class="table">
  <thead>
    <tr>
      <th scope="col">Id</th>
      <th scope="col">Name </th>
      <th scope="col">Plan Name</th>
      <th scope="col">Plan Status</th>
      <th scope="col">Plan_Start_Date</th>
      <th scope="col">Plan_End_Date </th>
      <th scope="col">Gender</th>
      
    </tr>
  </thead> 
		<hr />

		Export : <a href="">Excel</a> <a href=""> Pdf</a>



	</div>



	<script
		src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/js/bootstrap.bundle.min.js"
		integrity="sha384-YvpcrYf0tY3lHB60NNkmXc5s9fDVZLESaAA55NDzOxhy9GkcIdslK1eN7N6jIeHz"
		crossorigin="anonymous"></script>
</body>
</html>