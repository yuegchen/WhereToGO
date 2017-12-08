<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<title>WhereToGO</title>
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
//	System.out.print(id+"\n");
	String firstName = (String)request.getAttribute("cFirstName");
//	System.out.print(firstName+"\n");
	String lastName = (String)request.getAttribute("cLastName");
//	System.out.print(lastName+"\n");
	String cAddress=(String)request.getAttribute("cAddress");
//	System.out.print(cAddress+"\n");
	String cCity=(String)request.getAttribute("cCity");
//	System.out.print(cCity+"\n");
	String cState=(String)request.getAttribute("cState");
//	System.out.print(cState+"\n");
	String cZipCode=(String)request.getAttribute("cZipCode");
//	System.out.print(cZipCode+"\n");
	String cStartDate = (String)request.getAttribute("cStartDate");
//	System.out.print(Phone+"\n");
	String cHourlyRate=(String)request.getAttribute("cHourlyRate");
//	System.out.print(Rating+"\n");

	String[]cID=id.split(";");
	String[]cFN=firstName.split(";");
	String[]cLN=lastName.split(";");
	String[]cAD=cAddress.split(";");
	String[]cCT=cCity.split(";");
	String[]cST=cState.split(";");
	String[]cZP=cZipCode.split(";");
	String[]cSD=cStartDate.split(";");
	String[]cHR=cHourlyRate.split(";");
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
          <p><i class="fa fa-briefcase fa-fw w3-margin-right w3-large w3-text-teal"></i>Manager</p>
          <p><i class="fa fa-home fa-fw w3-margin-right w3-large w3-text-teal"></i><%=address%></p>
          <p><i class="fa fa-calendar fa-fw w3-margin-right w3-large w3-text-teal"></i><%=startDate%></p>
          <p><i class="fa fa-usd fa-fw w3-margin-right w3-large w3-text-teal"></i><%=hourlyRate%></p>
          
        </div>
        
        <div class="w3-container">
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="MSR">
        <input type ="text" name ="month" placeholder="Enter year and month" >
        <button  type="submit">Monthly Sale Report</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="LAF">
        <button  type="submit">Listing of All Flights</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="RFN">
        <input type ="text" name ="FlightNumber" placeholder="Enter Flightnumber" >
        <button  type="submit">Reservation by FlightNumber</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="RCN">
        <input type ="text" name ="CLastName" placeholder="Enter Customer Lastname" >
        <input type ="text" name ="CFirstName" placeholder="Enter Customer Firstname" >
        <button  type="submit">Reservation by Customer Name</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="LRFN">
        <input type ="text" name ="FlightNumber" placeholder="Enter Flightnumber" >
        <button  type="submit">List of Revenue by FlightNumber</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="LRCN">
        <input type ="text" name ="CLastName" placeholder="Enter Customer Lastname" >
        <input type ="text" name ="CFirstName" placeholder="Enter Customer Firstname" >
        <button  type="submit">List of Revenue by Customer Name</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="LRDC">
        <input type ="text" name ="City" placeholder="Enter Destination City" >
        <button  type="submit">List of Revenue by Destination City</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="MCR">
        <button  type="submit">Most Total Revenue Generated Customer Representative</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="MC">
        <button  type="submit">Most Total Revenue Generated Customer</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="MAF">
        <button  type="submit">A List of Most Active Flights</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="SRL">
        <input type ="text" name ="FlightNumber" placeholder="Enter Flightnumber" >
        <button  type="submit">Seats Reserved List</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="FLA">
        <input type ="text" name ="Airport" placeholder="Enter Airport" >
        <button  type="submit">Flights List by Airport </button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="OTF">
        <button  type="submit">On Time Flights</button>
        </form>
        <br>
        <form action="Manager">
        <input type="hidden" name="login" value=<%=login%>>
        <input type="hidden" name="action" value="DF">
        <button  type="submit">Delayed Flights</button>
        </form>
          
        </div>
      </div><br>

    <!-- End Left Column -->
    </div>

    <!-- Right Column -->
    <div class="w3-twothird">
    
      <div class="w3-container w3-card w3-white w3-margin-bottom">
        <h2 class="w3-text-grey w3-padding-16"><i class="fa fa-suitcase fa-fw w3-margin-right w3-xxlarge w3-text-teal"></i>Working Space</h2>
        <div class="w3-container">
        
        
		 
		  
        <form action="AddEmployee.jsp">
        <input type="hidden" name="login" value=<%=login%>>
        <button class="w3-button w3-white w3-border" type="submit">Add Employee</button>
        </form>
        
        <div class="w3-responsive" style="width:800px; height:300px; overflow:auto;">
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
                <th>StartDate</th>
                <th>HourlyRate</th>
                
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
                    <form action="EditEmployee.jsp" >
 				<input type="hidden" name="login" value=<%=login%>>
 				<input type="hidden" name="cID" value=<%=cID[n]%>>
 				<input type="hidden" name="cFN" value=<%=cFN[n]%>>
 				<input type="hidden" name="cLN" value=<%=cLN[n]%>>
 				<input type="hidden" name="cAD" value=<%=cAD[n]%>>
 				<input type="hidden" name="cCT" value=<%=cCT[n]%>>
 				<input type="hidden" name="cST" value=<%=cST[n]%>>
 				<input type="hidden" name="cZP" value=<%=cZP[n]%>>
 				<input type="hidden" name="cSD" value=<%=cSD[n]%>>
 				<input type="hidden" name="cHR" value=<%=cHR[n]%>>
 				<input type="hidden" name="type" value="employee">
  			<button class="fa fa-pencil-square-o w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  				</form>   
  				 </div>
                      <div class="w3-bar w3-light-grey">
                    <form action="Manager" >
 				<input type="hidden" name="login" value=<%=login%>>
 				<input type="hidden" name="action" value="cancel">
 				<input type="hidden" name="cID" value=<%=cID[n]%>>
 				<input type="hidden" name="type" value="employee">
  			<button class="fa fa-remove w3-bar-item w3-button w3-right  w3-mobile" type="submit" ><i class="w3-margin-right"></i> </button>
  				</form>   
  				 </div>  
                    </div>
                  </div>
                </div></td>
                <td><div contenteditable><%=cID[n]%></td>
                <td><div contenteditable><%=cFN[n]%></td>
                <td><div contenteditable><%=cLN[n]%></td>
                <td><div contenteditable><%=cAD[n]%></td>
                <td><div contenteditable><%=cCT[n]%></td>
                <td><div contenteditable><%=cST[n]%></td>
                <td><div contenteditable><%=cZP[n]%></td>
                <td><div contenteditable><%=cSD[n]%></td>
                <td><div contenteditable><%=cHR[n]%></td>
                
              </tr>
              <%
              	n++;
              }
              %>
              </table>
              </div>
        </div>
        <div class="w3-container">
		
        </div>
      </div>

      

    <!-- End Right Column -->
    </div>
    
  <!-- End Grid -->
  </div>
  
  <!-- End Page Container -->
</div>

<footer class="w3-container w3-teal w3-center w3-margin-top">
  <p>Find me on social media.</p>
  <i class="fa fa-facebook-official w3-hover-opacity"></i>
  <i class="fa fa-instagram w3-hover-opacity"></i>
  <i class="fa fa-snapchat w3-hover-opacity"></i>
  <i class="fa fa-pinterest-p w3-hover-opacity"></i>
  <i class="fa fa-twitter w3-hover-opacity"></i>
  <i class="fa fa-linkedin w3-hover-opacity"></i>
  <p>CSE 305 2017 Fall <a href="http://www3.cs.stonybrook.edu/~sas/courses/cse305/fall17/project/main.html" target="_blank" class="w3-hover-text-green">Final Project</a></p>
</footer>

</body>
</html>