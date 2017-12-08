<!DOCTYPE html>
<html>
<title>WhereToGo</title>
<meta charset="UTF-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="https://www.w3schools.com/w3css/4/w3.css">
<link rel="stylesheet" href="https://fonts.googleapis.com/css?family=Raleway">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<style>
body,h1,h2,h3,h4,h5,h6 {font-family: "Raleway", Arial, Helvetica, sans-serif}
</style>
<body class="w3-light-grey">
<%
     	String login=(String)request.getParameter("login");
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
 <form action="user" >
 <input type="hidden" name="login" value=<%=login%>>
  	<button class="fa fa-user fa-fw w3-bar-item w3-button w3-right  w3-mobile" type="submit"><i class="w3-margin-right"></i> <%=login%></button>
  </form>



  

</div>
<!-- Header -->
<header class="w3-display-container w3-content" style="max-width:1500px;">
  <img class="w3-image" src="airline.jpg" alt="The Airline" style="min-width:1000px" width="1500" height="800">
  <div class="w3-display-left w3-padding w3-col l6 m8">
    <div class="w3-container w3-blue">
      <h2><i class=""></i>Search Flight</h2>
    </div>
    <div class="w3-container w3-white w3-padding-16">
    <form action="multicity.jsp" >
   		<input type="hidden" name="login" value=<%=login%>>
        <button class="w3-button w3-dark-grey" type="submit"><i class="fa fa-book fa-fw w3-margin-right"></i>Multi-City</button>
      </form>
      <form action="SearchFlight" method="post">
        <div class="w3-row-padding" style="margin:0 -16px;">
          <div class="w3-half w3-margin-bottom">
            <label><i class="fa fa-calendar-o"></i> Departing</label>
            <input class="w3-input w3-border" type="text" placeholder="YYYY-MM-DD" name="Departing" required>
          </div>
          <div class="w3-half">
            <label><i class="fa fa-calendar-o"></i> Returning</label>
            <input class="w3-input w3-border" type="text" placeholder="YYYY-MM-DD" name="Returning" required>
          </div>
        </div>
        <div class="w3-row-padding" style="margin:8px -16px;">
          <div class="w3-half w3-margin-bottom">
            <label><i class="fa fa-city"></i> OriginCity</label>
            <input class="w3-input w3-border" type="text" placeholder="Origin" name="Origin" required>
          </div>
          <div class="w3-half">
            <label><i class="fa fa-city"></i> DestinationCity</label>
          <input class="w3-input w3-border" type="text" placeholder="Destination" name="Destination" required>
          </div>
        </div>
        <input type="hidden" name="login" value=<%=login%>>
        <input type="radio" name="round" value="one-way" checked> one-way <input type="radio" name="round" value="round-trip"> Round-Trip<br>
        <input type="radio" name="nation" value="international" checked> international <input type="radio" name="nation" value="domestic"> domestic<br>
        My flight is flexible <input type="radio" name="flexible" value="Yes"> Yes <input type="radio" name="flexible" value="No" checked> No<br>
        <button class="w3-button w3-dark-grey" type="submit"><i class="fa fa-search w3-margin-right"></i> Search Flight</button>        
      </form>
    </div>
  </div>
</header>

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
  				<form action="PG.pdf" target="_blank">
 				<input type="hidden" name="login" value="-">		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>Programmer Guide</button>
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

<!-- End page content -->


<!-- Footer -->
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
