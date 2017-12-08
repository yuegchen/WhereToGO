<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>WhereToGo_Customer_rep</title>
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
	  	String Id = (String)request.getAttribute("id");
	  	String name = (String)request.getAttribute("firstName")+(String)request.getAttribute("lastName");
	  	String address=(String)request.getAttribute("address")+", "+(String)request.getAttribute("city")+", "
	  	+ (String)request.getAttribute("state")+", "+(String)request.getAttribute("zipCode");
	  	String startDate = (String)request.getAttribute("startDate");
	  	String hourlyRate=(String)request.getAttribute("hourlyRate");
	  	
	  	String id = (String)request.getAttribute("cID");
	  	String firstName = (String)request.getAttribute("cFirstName");
	  	String lastName = (String)request.getAttribute("cLastName");
	  	String cAddress=(String)request.getAttribute("cAddress");
	  	String cCity=(String)request.getAttribute("cCity");
	  	String cState=(String)request.getAttribute("cState");
	  	String cZipCode=(String)request.getAttribute("cZipCode");
	  	String Phone = (String)request.getAttribute("cPhone");
	  	String Rating=(String)request.getAttribute("cRating");
	  	String CreditCardNo=(String)request.getAttribute("cCreditCardNo");
	  	String CreationDate=(String)request.getAttribute("cCreationDate");
	  	String email = (String)request.getAttribute("cEmail");
	  	String[]cID=id.split(";");
	  	String[]cFN=firstName.split(";");
	  	String[]cLN=lastName.split(";");
	  	String[]cAD=cAddress.split(";");
	  	String[]cCT=cCity.split(";");
	  	String[]cST=cState.split(";");
	  	String[]cZP=cZipCode.split(";");
	  	String[]cPH=Phone.split(";");
	  	String[]cRT=Rating.split(";");
	  	String[]cCC=CreditCardNo.split(";");
	  	String[]cCD=CreationDate.split(";");
	  	String[]cEM=email.split(";");
	  	
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
 	<input type="hidden" name="login" value=" ">
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
            <h2><%=name%></h2>
          </div>
        </div>
        <div class="w3-container">
          <p><i class="fa fa-id-card-o fa-fw w3-margin-right w3-large w3-text-teal"></i><%=Id%></p>
          <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>Customer Representative</p>
          <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i><%=address%></p>
          <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i><%=startDate%></p>
          <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><%=hourlyRate%></p>
          
        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Customer Information</h2>
        <div class="w3-container">
        
        
		 
		  
        <form action="AddCustomer.jsp" >
 				<input type="hidden" name="login" value=<%=login%>>
 				<input type="hidden" name="action" value="edit">
 				<input type="hidden" name="type" value="employee">
        <button class="w3-button w3-white w3-border">Add Customer</button>
        </form>   
        <div class="w3-responsive" style="width:800px; height:500px; overflow:auto;">
  			<table class="w3-table-all">
              <tr>
              
              	<th>      </th>
                <th>Id(Account Number)</th>
                <th>First Name</th>
                <th>Last Name</th>
                <th>Address</th>
                <th>City</th>
                <th>State</th>
                <th>ZipCode</th>
                <th>CreditCardNo</th>
                <th>Phone</th>
                <th>Email</th>
                <th>CreationDate</th>
                <th>Rating</th>
                
              </tr>
              <%
              int i = cID.length;
              if(id.equals(""))
            	  i=0;
              int n=0;
              while(n<i){
            	  
              
              %>
              <tr>
              	<td>
                <div class="w3-bar w3-light-grey">
                  <div class="w3-dropdown-hover">
                    <button class="w3-button">Action</button>
                    <div class="w3-dropdown-content w3-bar-block w3-card-4">
                      <div class="w3-bar w3-light-grey">
                    <form action="EditCustomer.jsp" >
 				<input type="hidden" name="login" value=<%=login%> >
 				<input type="hidden" name="cID" value=<%=cID[n]%>>
 				<input type="hidden" name="cFN" value=<%=cFN[n]%>>
 				<input type="hidden" name="cLN" value=<%=cLN[n]%>>
 				<input type="hidden" name="cAD" value=<%=cAD[n]%>>
 				<input type="hidden" name="cCT" value=<%=cCT[n]%>>
 				<input type="hidden" name="cST" value=<%=cST[n]%>>
 				<input type="hidden" name="cZP" value=<%=cZP[n]%>>
 				<input type="hidden" name="cCC" value=<%=cCC[n]%>>
 				<input type="hidden" name="cPH" value=<%=cPH[n]%>>
 				<input type="hidden" name="cEM" value=<%=cEM[n]%>>
 				<input type="hidden" name="cCD" value=<%=cCD[n]%>>
 				<input type="hidden" name="cRT" value=<%=cRT[n]%>>
 				<input type="hidden" name="type" value="employee">
  			<button class="fa fa-pencil-square-o w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  				</form>   
  				 </div>
                      <div class="w3-bar w3-light-grey">
                    <form action="Customer_Rep" >
 				<input type="hidden" name="login" value=<%=login%>>
 				<input type="hidden" name="action" value="cancel">
 				<input type="hidden" name="cID" value=<%=cID[n]%>>
 				<input type="hidden" name="type" value="employee">
  			<button class="fa fa-remove w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  				</form>   
  				 </div>  
  				 
                      <div class="w3-bar w3-light-grey">
                    <form action="Customer_RepBooking.jsp" >
 				<input type="hidden" name="login" value=<%=login%>>
 				<input type="hidden" name="ID" value=<%=Id%>>
 				<input type="hidden" name="cID" value=<%=cID[n]%>>
 				<input type="hidden" name="cFN" value=<%=cFN[n]%>>
 				<input type="hidden" name="cLN" value=<%=cLN[n]%>>
  			<button class="fa fa-calendar-check-o w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  				</form>   
  				 </div>
                      <div class="w3-bar w3-light-grey">
                    <form action="Customer_Rep" >
 				<input type="hidden" name="login" value=<%=login%>>
 				<input type="hidden" name="cFN" value=<%=cFN[n]%>>
 				<input type="hidden" name="cLN" value=<%=cLN[n]%>>
 				<input type="hidden" name="action" value="fs">
  			<button class="fa fa-star-o w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  				</form>   
  				 </div>
                    </div>
                  </div>
                </div></td>
                <td><%=cID[n]%></td>
                <td><div  style="width: 100px;"><%=cFN[n]%></td>
                <td><div  style="width: 100px;"><%=cLN[n]%></td>
                <td><div  style="width: 100px;"><%=cAD[n]%></td>
                <td><div  style="width: 100px;"><%=cCT[n]%></td>
                <td><div  style="width: 100px;"><%=cST[n]%></td>
                <td><div  style="width: 100px;"><%=cZP[n]%></td>
                <td><div  style="width: 200px;"><%=cCC[n]%></td>
                <td><div  style="width: 100px;"><%=cPH[n]%></td>
                <td><div  style="width: 200x;"><%=cEM[n]%></td>
                <td><div  style="width: 100px;"><%=cCD[n]%></td>
                <td><div  style="width: 100px;"><%=cRT[n]%></td>
                
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
 				<input type="hidden" name="login" value="-">		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i> ER-diagram</button>
  				</form>  
               <form action="RelationModel.pdf" target="_blank">
 				<input type="hidden" name="login" value="-">		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>Relational Model</button>
  				</form>  
      <form action="SQLcode.pdf" target="_blank">
 				<input type="hidden" name="login" value="-">		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>SQL code</button>
  				</form>  
  		<form action="description.pdf" target="_blank">
 				<input type="hidden" name="login" value="-">		
  			<button class=" w3-button   w3-mobile" type="submit" ><i class="w3-margin-right"></i>Description</button>
  				</form> 
  		<form action="arch.pdf" target="_blank">
 				<input type="hidden" name="login" value="-">		
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
