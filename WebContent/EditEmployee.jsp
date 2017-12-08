<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>WhereToGo_Customer</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel='stylesheet' href='https://fonts.googleapis.com/css?family=Roboto'>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
html,body,h1,h2,h3,h4,h5,h6 {font-family: "Roboto", sans-serif}
</style>

<body class="w3-light-grey">
	  <%
     	String login=(String)request.getParameter("login");
	  	String id = (String)request.getParameter("cID");
	  	String firstName = (String)request.getParameter("cFN");
	  	String lastName = (String)request.getParameter("cLN");
	  	String cAddress=(String)request.getParameter("cAD");
	  	String cCity=(String)request.getParameter("cCT");
	  	String cState=(String)request.getParameter("cST");
	  	String cZipCode=(String)request.getParameter("cZP");
	  	String cStartDate=(String)request.getParameter("cSD");
	  	String cHourlyRate = (String)request.getParameter("cHR");

      %>
<!-- Navigation Bar -->
<div class="w3-bar w3-white w3-large">
  <form action="index2.jsp" >
  <input type="hidden" name="login" value=<%=login%>>
  	<button class="w3-bar-item w3-button w3-blue w3-mobile" type="submit" disabled><i class="w3-margin-right"></i> WhereToGo</button>
  </form>
  <a href="#Help" class="w3-bar-item w3-button w3-mobile">Help</a>
  <a href="#contact" class="w3-bar-item w3-button w3-mobile">Contact</a>
 
 <form action="index.jsp" >
 	<input type="hidden" name="login" value="-">
  	<button class="fa fa-times-circle-o w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  </form>

</div>
<!-- Page Container -->
<div class="w3-content w3-margin-top" style="max-width:1400px;">

  <!-- The Grid -->
  <div class="w3-row-padding">
  
    <!-- Left Column -->
    <div class="w3-third">
    
      <div class="w3-white w3-text-grey w3-card-4">
        <div class="w3-display-container">
          <img src="airline.jpg" style="width:100%" alt="Avatar">
          <div class="w3-display-bottomleft w3-container w3-text-black">
            <h2><%=login%></h2>
          </div>
        </div>
       
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Edit Employee Information</h2>
        <div class="w3-container">
        
        
		 
		  
        
        <button class="w3-button w3-dark-grey" type="button" name="back" onclick="history.back()">Back</button>
        
        <div class="w3-responsive" style="width:700px; height:300px;overflow:auto;">
  			<table class="w3-table-all">
              <tr>
              <th></th>
              <th>Id(Account Number)</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>StartDate</th>
                <th>HourlyRate</th>
              </tr>
              <tr>
              	<form action="Manager" method="post">
              	  <input type="hidden" name="action" value="edit">
  				 <input type="hidden" name="login" value=<%=login%>>
  				 <input type="hidden" name="cID" value=<%=id%>>
  				 <input type="hidden" name="type" value="employee">
  				<td><button class="w3-button w3-dark-grey" type="submit">Submit</button> </td>  
               	<td><%=id%></td>
               	
                <td><input class="w3-input w3-border" type="text"  name="cFirstName"  style="width: 100px;" value=<%=firstName%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cLastName"  style="width: 100px;" value=<%=lastName%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cAddress"  style="width: 100px;" value=<%=cAddress%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cCity"  style="width: 100px;" value=<%=cCity%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cState"  style="width: 100px;" value=<%=cState%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cZipCode"  style="width: 100px;" value=<%=cZipCode%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cStartDate"  style="width: 100px;" value=<%=cStartDate%>></td>
                <td><input class="w3-input w3-border" type="text"  name="cHourlyRate"  style="width: 100px;" value=<%=cHourlyRate%>></td>

                     
      			</form>
              </tr>
              </table>
              </div>
        </div>
      </div>

      

    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  
  <!-- Page content -->
  <div class="w3-content" style="max-width:1532px;">

    <div class="w3-row-padding" id="Help">
      <div class="w3-col l4 m7">
        <h3>Help</h3>
        <h6>WhereToGo is a website for booking air ticket. You can search for One-Way, Round-Trip, Multi-City, Domestic and International flights. But to make a reservation, you have to login or register first as a customer. Thanks for your understanding.</h6>
      <p>We accept: <i class="fa fa-credit-card w3-large"></i> <i class="fa fa-cc-mastercard w3-large"></i> <i class="fa fa-cc-amex w3-large"></i> <i class="fa fa-cc-cc-visa w3-large"></i><i class="fa fa-cc-paypal w3-large"></i></p>
      </div>
    </div>

    <div class="w3-container" id="contact">
      <h2>Contact</h2>
      <p>If you have any questions, do not hesitate to ask them.</p>
      <i class="fa fa-envelope w3-text-red" style="width:30px"></i> Email: yuege.chen@stonybrook.edu<br>
      <i class="fa fa-envelope w3-text-red" style="width:30px"></i> Email: zijun.he@stonybrook.edu<br>
      <i class="fa fa-envelope w3-text-red" style="width:30px"> </i> Email: nan.jiang.1@stonybrook.edu<br>
    </div>
  <!-- End Page Container -->
  </div>

  <footer class="w3-padding-32 w3-black w3-center w3-margin-top">
    <div class="w3-xlarge w3-padding-16">
      <i class="fa fa-facebook-official w3-hover-opacity"></i>
      <i class="fa fa-instagram w3-hover-opacity"></i>
      <i class="fa fa-snapchat w3-hover-opacity"></i>
      <i class="fa fa-pinterest-p w3-hover-opacity"></i>
      <i class="fa fa-twitter w3-hover-opacity"></i>
      <i class="fa fa-linkedin w3-hover-opacity"></i>
    </div>
    <p>CSE 305 2017 Fall <a href="http://www3.cs.stonybrook.edu/~sas/courses/cse305/fall17/project/main.html" target="_blank" class="w3-hover-text-green">Final Project</a></p>
  </footer>

</body>
</html>
