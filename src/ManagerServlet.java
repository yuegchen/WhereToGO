import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ManagerServlet extends HttpServlet {
	
//	public static void main(String[] args) {
//		// TODO Auto-generated method stub
//
//	}
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		//
		  this.doPost(request, response);
	}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String login=request.getParameter("login");	
		String Action = request.getParameter("action");	
		if(Action.equals("add")){
			String FirstName=request.getParameter("FirstName");	
			String LastName=request.getParameter("LastName");
			String Address=request.getParameter("Address");
			String City=request.getParameter("City");
			String State=request.getParameter("State");
			String ZipCode=request.getParameter("ZipCode");
			String ID=request.getParameter("ID");
			String SSN=request.getParameter("SSN");
			String HourlyRate=request.getParameter("HourlyRate");
			String StartDate=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        
		        sql = "INSERT INTO Person"+"(Id,FirstName,LastName,Address,City,State,ZipCode)"+"values"+"("+ID+","+"'"+FirstName+"',"+"'"+LastName+"',"+"'"+Address+"',"+"'"+City+"',"+"'"+State+"',"+ZipCode+");";
		        int rs = statement.executeUpdate(sql);
		        if(rs==0){
					request.setAttribute("message","Sorry, wrong information enter!" );
		        	RequestDispatcher dispatcher 
		        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
		        	dispatcher.forward(request, response);
		        	return;
		        }
		        Statement statement1 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        
		        sql = "INSERT INTO Employee "+"(Id,SSN,IsManager,StartDate,HourlyRate) "+"values "+"("+ID+","+SSN+",false,"+"'"+StartDate+"',"+HourlyRate+");";
		        int rs1 = statement1.executeUpdate(sql);
		        if(rs1==0){
					request.setAttribute("message","Sorry, wrong information enter!" );
		        	RequestDispatcher dispatcher 
		        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
		        	dispatcher.forward(request, response);
		        	return;
		        }
		        
		      
		        RequestDispatcher dispatcher 
		        = request.getServletContext().getRequestDispatcher("/login");
		        dispatcher.forward(request, response);
    
		        connection.close();
		        
		}catch(Exception e){
					e.printStackTrace();
		}
		}else if(Action.equals("edit")){

			String cID = request.getParameter("cID");
			
			String firstName = (String)request.getParameter("cFirstName");
		  	String lastName = (String)request.getParameter("cLastName");
		  	String cAddress=(String)request.getParameter("cAddress");
		  	String cCity=(String)request.getParameter("cCity");
		  	String cState=(String)request.getParameter("cState");
		  	String cZipCode=(String)request.getParameter("cZipCode");
		  	String cStartDate = (String)request.getParameter("cStartDate");
		  	String cHourlyRate=(String)request.getParameter("cHourlyRate");
		  	
		  	try{
		  		Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		if(cHourlyRate != null) {
			  		Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Employee "+
							"Set HourlyRate = "+ cHourlyRate+" "+
							"WHERE Id = "+cID+";";
					statement2.executeUpdate(sql);
			  	}
		  		if(firstName != null) {
			  		Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Person "+
							"Set FirstName = "+"'"+ firstName+"' "+
							"WHERE Id = "+cID+";";
					statement3.executeUpdate(sql);
			  	}
		  		if(lastName != null) {
			  		Statement statement4 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Person "+
							"Set LastName = "+"'"+ lastName+"' "+
							"WHERE Id = "+cID+";";
					statement4.executeUpdate(sql);
			  	}
		  		if(cAddress != null) {
			  		Statement statement5 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Person "+
							"Set Address = "+"'"+ cAddress+"' "+
							"WHERE Id = "+cID+";";
					statement5.executeUpdate(sql);
			  	}
		  		if(cCity != null) {
			  		Statement statement6 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Person "+
							"Set City = "+"'"+ cCity+"' "+
							"WHERE Id = "+cID+";";
					statement6.executeUpdate(sql);
			  	}
		  		if(cState != null) {
			  		Statement statement7 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Person "+
							"Set State = "+"'"+ cState+"' "+
							"WHERE Id = "+cID+";";
					statement7.executeUpdate(sql);
			  	}
		  		if(cZipCode != null) {
			  		Statement statement8 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Person "+
							"Set ZipCode = "+ cZipCode+" "+
							"WHERE Id = "+cID+";";
					statement8.executeUpdate(sql);
			  	}
		  		if(cHourlyRate != null) {
			  		Statement statement9 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Employee "+
							"Set StartDate = "+"'"+ cStartDate+"' "+
							"WHERE Id = "+cID+";";
					statement9.executeUpdate(sql);
			  	}
		  	}catch(Exception e){
					e.printStackTrace();
		  	}
		  	


			//
			/**if(rs==1){}
			else{
				request.setAttribute("message","Sorry, This Transaction cannot be done at this time." );
	        	RequestDispatcher dispatcher 
	        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
	        	dispatcher.forward(request, response);
	        	return;
			}**/
			
			RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login");
			dispatcher.forward(request, response);
		
		}else if(Action.equals("cancel")){
			String cID = request.getParameter("cID");
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "DELETE FROM Employee "+
						"WHERE Id = "+cID+";";
				int rs = statement.executeUpdate(sql);
				if(rs==1){}
				else{
					request.setAttribute("message","Sorry, This Transaction cannot be done at this time." );
		        	RequestDispatcher dispatcher 
		        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
		        	dispatcher.forward(request, response);
		        	return;
				}
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
			

		}else if(Action.equals("MSR")){
			String month = request.getParameter("month");
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT R.ResrNo, R.TotalFare, R.bookingFee "+
		  				"FROM Reservation R "+
		  				"WHERE  SUBSTR(R.ResrDate,1,7) = '"+month+"';";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("ResrNo");
		  			items += ",";
		  			items += rs.getString("TotalFare");
		  			items += ",";
		  			items += rs.getString("bookingFee");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MonthSaleReport.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("LAF")){

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT F.AirlineID,F.FlightNo,F.NoOfSeats,F.DaysOperating,F.MinLengthOfStay,F.MaxLengthOfStay "+
		  				"FROM flight F;";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("AirlineID");
		  			items += ",";
		  			items += rs.getString("FlightNo");
		  			items += ",";
		  			items += rs.getString("NoOfSeats");
		  			items += ",";
		  			items += rs.getString("DaysOperating");
		  			items += ",";
		  			items += rs.getString("MinLengthOfStay");
		  			items += ",";
		  			items += rs.getString("MaxLengthOfStay");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/AllFlights.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		
		}else if(Action.equals("RFN")){
			String flightnumber = request.getParameter("FlightNumber");
			String airline = flightnumber.substring(0, 2);
			String fnumber = flightnumber.substring(2, 5);
			System.out.println(flightnumber);
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT distinct R.ResrNo,R.ResrDate,R.BookingFee,R.TotalFare,R.AccountNo "+
		  				"FROM Reservation R, Includes I "+
		  				"WHERE I.ResrNo = R.ResrNo AND I.FlightNo = "+fnumber +" AND I.AirlineID = '"+airline+"';";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("ResrNo");
		  			items += ",";
		  			items += rs.getString("ResrDate");
		  			items += ",";
		  			items += rs.getString("BookingFee");
		  			items += ",";
		  			items += rs.getString("TotalFare");
		  			items += ",";
		  			items += rs.getString("AccountNo");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ReservationFlightNumber.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("RCN")){
			String lastname = request.getParameter("CLastName");
			String firstname = request.getParameter("CFirstName");
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT distinct R.ResrNo,R.ResrDate,R.BookingFee,R.TotalFare,R.AccountNo "+
		  				"FROM Reservation R, Customer C, Person P "+
		  				"WHERE C.AccountNo = R.AccountNo AND C.Id = P.Id AND P.FirstName = '"+firstname+"' AND P.LastName = '"+lastname+"';";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("ResrNo");
		  			items += ",";
		  			items += rs.getString("ResrDate");
		  			items += ",";
		  			items += rs.getString("BookingFee");
		  			items += ",";
		  			items += rs.getString("TotalFare");
		  			items += ",";
		  			items += rs.getString("AccountNo");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/ReservationCustomerName.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("LRFN")){
			String flightnumber = request.getParameter("FlightNumber");
			String airline = flightnumber.substring(0, 2);
			String fnumber = flightnumber.substring(2, 5);
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT distinct R.ResrNo,R.TotalFare "+
		  				"FROM Reservation R,Includes I, Leg L "+
		  				"WHERE R.ResrNo = I.ResrNo AND I.AirlineID = L.AirlineID AND I.FlightNo = L.FlightNo AND I.LegNo = L.LegNo AND I.FlightNo = "+fnumber +" AND I.AirlineID = '"+airline+"';";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("ResrNo");
		  			items += ",";
		  			items += rs.getString("TotalFare");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/RevenueFlightNumber.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("LRCN")){
			String lastname = request.getParameter("CLastName");
			String firstname = request.getParameter("CFirstName");
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT distinct R.ResrNo,R.TotalFare "+
		  				"FROM Reservation R, Customer C, Person P "+
		  				"WHERE C.AccountNo = R.AccountNo AND C.Id = P.Id AND P.FirstName = '"+firstname+"' AND P.LastName = '"+lastname+"';";
		  				
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("ResrNo");
		  			items += ",";
		  			items += rs.getString("TotalFare");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/RevenueCustomerName.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("LRDC")){
			String city = request.getParameter("City");

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT distinct R.ResrNo,R.TotalFare "+
		  				"FROM Reservation R,Includes I, Leg L, Airport A "+
		  				"WHERE R.ResrNo = I.ResrNo AND I.AirlineID = L.AirlineID AND I.FlightNo = L.FlightNo AND I.LegNo = L.LegNo AND L.ArrAirportID = A.Id AND A.City = '"+city+"';";
		  				
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("ResrNo");
		  			items += ",";
		  			items += rs.getString("TotalFare");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/RevenueCity.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("MCR")){

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = 	"select er.Id, er.FirstName, er.LastName, max(er.SumRevenue) as sum "+
		  				"From eRevenue er;";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("Id");
		  			items += ",";
		  			items += rs.getString("FirstName");
		  			items += ",";
		  			items += rs.getString("LastName");
		  			items += ",";
		  			items += rs.getString("sum");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MostCR.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		
		}else if(Action.equals("MC")){

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = 	"select cr.Id, cr.FirstName, cr.LastName, max(cr.SumRevenue) as sum " +
		  				"From cRevenue cr;";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("Id");
		  			items += ",";
		  			items += rs.getString("FirstName");
		  			items += ",";
		  			items += rs.getString("LastName");
		  			items += ",";
		  			items += rs.getString("sum");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MostC.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		
		}else if(Action.equals("MAF")){

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
				sql = "select i.airlineId, i.flightno, count(distinct i.ResrNo) as popularity " + "from includes i "
						+ "group by concat(AirlineID,'-',FlightNo) " + "order by popularity desc " + "limit 5;";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
					items += rs.getString("airlineId");
					items += ",";
					items += rs.getString("flightNo");
					items += ",";
					items += rs.getString("popularity");
					items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MostActiveFlights.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		
		}else if(Action.equals("SRL")){
			String flightnumber = request.getParameter("FlightNumber");
			String airline = flightnumber.substring(0, 2);
			String fnumber = flightnumber.substring(2, 5);
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT distinct C.Id, P.FirstName, P.LastName "+
		  				"FROM ReservationPassenger RP,Reservation R, Customer C, Person P, Includes I "+
		  				"WHERE RP.ResrNo = R.ResrNo AND C.AccountNo = R.AccountNo AND C.Id = P.Id AND I.ResrNo = R.ResrNo AND I.FlightNo = "+fnumber+" AND I.AirlineID = '"+airline+"';";

		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("Id");
		  			items += ",";
		  			items += rs.getString("FirstName");
		  			items += ",";
		  			items += rs.getString("LastName");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/SeatsReserved.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("FLA")){
			String airport = request.getParameter("Airport");
			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "SELECT  distinct concat(L.AirlineID,'-',L.FlightNo) as af "+
		  				"FROM Leg L,Includes I "+
		  				"WHERE I.AirlineID = L.AirlineID AND I.FlightNo = L.FlightNo AND I.LegNo = L.LegNo AND (L.DepAirportID = '"+airport+"' OR L.ArrAirportID = '"+airport+"') ;";

		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("af");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/FlightsList.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		}else if(Action.equals("OTF")){

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "select f.airlineid,f.flightNo " +
		  				"from flight f "+
		  				"where f.ontime=true;";
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("airlineid");
		  			items += ",";
		  			items += rs.getString("flightNo");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/OnTimeFlights.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		
		}else if(Action.equals("DF")){

			try{
				Connection connection = ConnectionUtils.getMyConnection();
		  		String sql="";
		  		Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
		  		
		  		sql = "select f.airlineid,f.flightNo " +
		  				"from flight f "+
		  				"where f.ontime=false;";;
		  		ResultSet rs = statement.executeQuery(sql);
		  		String items="";
		  		while(rs.next()){
		  			items += rs.getString("airlineid");
		  			items += ",";
		  			items += rs.getString("FliflightNoghtNo");
		  			items += ";";
		  		}
		  		request.setAttribute("items", items);
		  		RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/DelayedFlights.jsp");
				dispatcher.forward(request, response);
			}catch(Exception e){
					e.printStackTrace();
		  	}
		
		}
	}	
}				
				
				
				
				
