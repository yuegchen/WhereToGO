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
	  	String items = (String)request.getAttribute("items");
	  	String rn = (String)request.getAttribute("rn");
	  	
	  	String[] legs=items.split(";");
	 
      %>
<!-- Navigation Bar -->
<div class="w3-bar w3-white w3-large">
  <form action="index2.jsp" >
  <input type="hidden" name="login" value=<%=login%>>
  	<button class="w3-bar-item w3-button w3-blue w3-mobile" type="submit"><i class="w3-margin-right"></i> WhereToGo</button>
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
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Reservation <%=rn%> </h2>
        <div class="w3-container">
        
        
		 
		  
        
        <button class="w3-button w3-dark-grey" type="button" name="back" onclick="history.back()">Back</button>
        
        <div class="w3-responsive" style="width:700px; height:500px;">
  			<table class="w3-table-all">
              <tr>
              
                <th >airlineId</th>
                <th>flightNo</th>
                <th>legNo</th>
                <th>DepAirportID</th>
                 <th>DepTime</th>
                <th>ArrAirportID</th>
                <th>ArrTime</th>
              </tr>
              <%
              int i = legs.length;
              int n=0;
              while(n<i){
            	 
              
              %>
              <tr>
              	
               	<td><%=(legs[n].split(","))[0]%> </td>
                <td><%=(legs[n].split(","))[1]%></td>
                <td><%=(legs[n].split(","))[2]%></td>
                <td><%=(legs[n].split(","))[3]%></td>
                <td><%=(legs[n].split(","))[4]%></td>
                <td><%=(legs[n].split(","))[5]%></td>
                <td><%=(legs[n].split(","))[6]%></td>
                
              </tr>
              <%
              	n++;
              }
              %>
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
       <form action="ER.pdf" target="_blank">
 				<input type="hidden" name="login" value=<%=login%>>		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i> ER-diagram</button>
  				</form>  
               <form action="RelationModel.pdf" target="_blank">
 				<input type="hidden" name="login" value=<%=login%>>		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>Relational Model</button>
  				</form>  
      <form action="SQLcode.pdf" target="_blank">
 				<input type="hidden" name="login" value=<%=login%>>		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>SQL code</button>
  				</form>  
  		<form action="description.pdf" target="_blank">
 				<input type="hidden" name="login" value=<%=login%>>		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>Description</button>
  				</form> 
  		<form action="arch.pdf" target="_blank">
 				<input type="hidden" name="login" value=<%=login%>>		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>architectural diagram</button>
  				</form> 
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
