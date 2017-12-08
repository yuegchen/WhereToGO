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

public class SearchFlightServlet extends HttpServlet {
	
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
			String departing=request.getParameter("Departing");
			String returning=request.getParameter("Returning");
			String origin=request.getParameter("Origin");
			String destination=request.getParameter("Destination");
			String round=request.getParameter("round");
			String nation=request.getParameter("nation");
			String flexible=request.getParameter("flexible");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            String strDate = departing;
            Date date = null;
			try {
				date = sdf.parse(strDate);
			} catch (ParseException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
				RequestDispatcher dispatcher=null;
				request.setAttribute("login", login);
				dispatcher = request.getServletContext().getRequestDispatcher("/DateNotCorrect.jsp");
	        	dispatcher.forward(request, response);
			}
           
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        if(round.equals("one-way")&&flexible.equals("No")){
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
		        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
		        		departing+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
		        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
		        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
		        	ResultSet rs = statement.executeQuery(sql);
		   		 	
			        // Fetch on the ResultSet        
			        // Move the cursor to the next record.
			        if (rs.next()) {
			            String airId = rs.getString("airlineId");
			            String flightNo = rs.getString("flightNo");
			            String legNo = rs.getString("legNo");
			            String depTime = rs.getString("DepTime");
			            String arrTime = rs.getString("ArrTime"); 
			            String fareType = rs.getString("fareType");
			            String fare = rs.getString("fare");
			            String airClass = rs.getString("class");
			            String departId = rs.getString("departId");
			            String arrId = rs.getString("arrId");
			            request.setAttribute("departId", departId);
			            request.setAttribute("arrId", arrId);
			            request.setAttribute("airClass", airClass);
			            request.setAttribute("airId", airId);
			            request.setAttribute("flightNo", flightNo);
			            request.setAttribute("legNo", legNo);
			            request.setAttribute("depTime", depTime);
			            request.setAttribute("arrTime", arrTime);
			            request.setAttribute("origin", origin);
			            request.setAttribute("destination", destination);
			            request.setAttribute("fareType", fareType);
			            request.setAttribute("fare", fare);
			            request.setAttribute("login", login);
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/SearchResult1wayExact.jsp");
			            dispatcher.forward(request, response);
			        }
			        else{
			        	RequestDispatcher dispatcher 
	                    = request.getServletContext().getRequestDispatcher("/FlightNotFound.jsp");
			        	dispatcher.forward(request, response);
			        }
		        }
		        else if(round.equals("round-trip")&&flexible.equals("No")){
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
		        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
		        		departing+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
		        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
		        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
		        	ResultSet rs = statement.executeQuery(sql);
		        	Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		returning+ "' and l.DepAirportID=a1.Id and a1.City='"+destination+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+origin+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs2 = statement2.executeQuery(sql);
			        // Fetch on the ResultSet        
			        // Move the cursor to the next record.
			        if (rs.next()&&rs2.next()) {
			            String airId1 = rs.getString("airlineId");
			            String flightNo1 = rs.getString("flightNo");
			            String legNo1 = rs.getString("legNo");
			            String depTime1 = rs.getString("DepTime");
			            String arrTime1 = rs.getString("ArrTime"); 
			            String fareType1 = rs.getString("fareType");
			            String fare1 = rs.getString("fare");
			            String airClass1 = rs.getString("class");
			            String departId1 = rs.getString("departId");
			            String arrId1 = rs.getString("arrId");
			            String airId2 = rs2.getString("airlineId");
			            String flightNo2 = rs2.getString("flightNo");
			            String legNo2 = rs2.getString("legNo");
			            String depTime2 = rs2.getString("DepTime");
			            String arrTime2 = rs2.getString("ArrTime"); 
			            String fareType2 = rs2.getString("fareType");
			            String fare2 = rs2.getString("fare");
			            String airClass2 = rs2.getString("class");
			            String departId2 = rs2.getString("departId");
			            String arrId2 = rs2.getString("arrId");
			            request.setAttribute("departId2", departId2);
			            request.setAttribute("arrId2", arrId2);
			            request.setAttribute("airClass2", airClass2);
			            request.setAttribute("airId2", airId2);
			            request.setAttribute("flightNo2", flightNo2);
			            request.setAttribute("legNo2", legNo2);
			            request.setAttribute("depTime2", depTime2);
			            request.setAttribute("arrTime2", arrTime2);
			            request.setAttribute("fareType2", fareType2);
			            request.setAttribute("fare2", fare2);
			            
			            request.setAttribute("departId1", departId1);
			            request.setAttribute("arrId1", arrId1);
			            request.setAttribute("airClass1", airClass1);
			            request.setAttribute("airId1", airId1);
			            request.setAttribute("flightNo1", flightNo1);
			            request.setAttribute("legNo1", legNo1);
			            request.setAttribute("depTime1", depTime1);
			            request.setAttribute("arrTime1", arrTime1);
			            request.setAttribute("origin", origin);
			            request.setAttribute("destination", destination);
			            request.setAttribute("fareType1", fareType1);
			            request.setAttribute("fare1", fare1);
			            request.setAttribute("login", login);
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/SearchResultRoundExact.jsp");
			            dispatcher.forward(request, response);
			        }
			        else{
			        	RequestDispatcher dispatcher 
	                    = request.getServletContext().getRequestDispatcher("/FlightNotFound.jsp");
			        	dispatcher.forward(request, response);
			        }
		        }
		        else if(round.equals("one-way")&&flexible.equals("Yes")){
		        	
		        	 Calendar calendar = Calendar.getInstance();
		             calendar.setTime(date);
		             calendar.add(Calendar.DATE, -1);
		             String yesterday = sdf.format(calendar.getTime());
		             calendar.add(Calendar.DATE, +2);
		             String tomorrow = sdf.format(calendar.getTime());
		             
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
		        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
		        		yesterday+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
		        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
		        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
		        	ResultSet rs = statement.executeQuery(sql);
		        	
		        	Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		departing+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs2 = statement2.executeQuery(sql);

			        Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		tomorrow+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs3 = statement3.executeQuery(sql);
			        
			        boolean notfound=true;
			        if (rs.next()) {
			        	notfound=false;
			            String airId1 = rs.getString("airlineId");
			            String flightNo1 = rs.getString("flightNo");
			            String legNo1 = rs.getString("legNo");
			            String depTime1 = rs.getString("DepTime");
			            String arrTime1 = rs.getString("ArrTime"); 
			            String fareType1 = rs.getString("fareType");
			            String fare1 = rs.getString("fare");
			            String airClass1 = rs.getString("class");
			            String departId1 = rs.getString("departId");
			            String arrId1 = rs.getString("arrId");
			            
			            request.setAttribute("departId1", departId1);
			            request.setAttribute("arrId1", arrId1);
			            request.setAttribute("airClass1", airClass1);
			            request.setAttribute("airId1", airId1);
			            request.setAttribute("flightNo1", flightNo1);
			            request.setAttribute("legNo1", legNo1);
			            request.setAttribute("depTime1", depTime1);
			            request.setAttribute("arrTime1", arrTime1);
			            
			            request.setAttribute("fareType1", fareType1);
			            request.setAttribute("fare1", fare1);			            
			        }
			        else{
			        	request.setAttribute("airId1", "Not Found");
			        }
			        if(rs2.next()){
			        	notfound=false;
			            String airId2 = rs2.getString("airlineId");
			            String flightNo2 = rs2.getString("flightNo");
			            String legNo2 = rs2.getString("legNo");
			            String depTime2 = rs2.getString("DepTime");
			            String arrTime2 = rs2.getString("ArrTime"); 
			            String fareType2 = rs2.getString("fareType");
			            String fare2 = rs2.getString("fare");
			            String airClass2 = rs2.getString("class");
			            String departId2 = rs2.getString("departId");
			            String arrId2 = rs2.getString("arrId");
			            
			            request.setAttribute("departId2", departId2);
			            request.setAttribute("arrId2", arrId2);
			            request.setAttribute("airClass2", airClass2);
			            request.setAttribute("airId2", airId2);
			            request.setAttribute("flightNo2", flightNo2);
			            request.setAttribute("legNo2", legNo2);
			            request.setAttribute("depTime2", depTime2);
			            request.setAttribute("arrTime2", arrTime2);
			            request.setAttribute("fareType2", fareType2);
			            request.setAttribute("fare2", fare2);
			        }
			        else{
			        	request.setAttribute("airId2", "Not Found");
			        }
			        if(rs3.next()){
			        	notfound=false;
			            String airId3 = rs3.getString("airlineId");
			            String flightNo3 = rs3.getString("flightNo");
			            String legNo3 = rs3.getString("legNo");
			            String depTime3 = rs3.getString("DepTime");
			            String arrTime3 = rs3.getString("ArrTime"); 
			            String fareType3 = rs3.getString("fareType");
			            String fare3 = rs3.getString("fare");
			            String airClass3 = rs3.getString("class");
			            String departId3 = rs3.getString("departId");
			            String arrId3 = rs3.getString("arrId");
			            
			            request.setAttribute("departId3", departId3);
			            request.setAttribute("arrId3", arrId3);
			            request.setAttribute("airClass3", airClass3);
			            request.setAttribute("airId3", airId3);
			            request.setAttribute("flightNo3", flightNo3);
			            request.setAttribute("legNo3", legNo3);
			            request.setAttribute("depTime3", depTime3);
			            request.setAttribute("arrTime3", arrTime3);
			            request.setAttribute("fareType3", fareType3);
			            request.setAttribute("fare3", fare3);
			        }
			        else{
			        	request.setAttribute("airId3", "Not Found");
			        }
			        if(notfound){
			        	request.setAttribute("login", login);
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/FlightNotFound.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			            request.setAttribute("origin", origin);
			            request.setAttribute("destination", destination);
			            request.setAttribute("login", login);
			            request.setAttribute("departing", departing);
			            request.setAttribute("yesterday", yesterday);
			            request.setAttribute("tomorrow", tomorrow);
			            
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/SearchResult1wayFlexible.jsp");
			            dispatcher.forward(request, response);
			     
		        }
		        else if(round.equals("round-trip")&&flexible.equals("Yes")){
			        String strDate2 = returning;
		            Date date2 = null;
		 			try {
		 				date2 = sdf.parse(strDate2);
		 			} catch (ParseException e1) {
		 				// TODO Auto-generated catch block
		 				e1.printStackTrace();
		 				RequestDispatcher dispatcher=null;
		 				request.setAttribute("login", login);
		 				dispatcher = request.getServletContext().getRequestDispatcher("/DateNotCorrect.jsp");
		 	        	dispatcher.forward(request, response);
		 			}
		        	
		        	 Calendar calendar = Calendar.getInstance();
		             calendar.setTime(date);
		             calendar.add(Calendar.DATE, -1);
		             String yesterday = sdf.format(calendar.getTime());
		             calendar.add(Calendar.DATE, +2);
		             String tomorrow = sdf.format(calendar.getTime());
		             
		             
		             calendar.setTime(date2);
		             calendar.add(Calendar.DATE, -1);
		             String returnyesterday = sdf.format(calendar.getTime());
		             calendar.add(Calendar.DATE, +2);
		             String returntomorrow = sdf.format(calendar.getTime());
		             
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
		        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
		        		yesterday+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
		        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
		        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
		        	ResultSet rs = statement.executeQuery(sql);
		        	
		        	Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		departing+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs2 = statement2.executeQuery(sql);

			        Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		tomorrow+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs3 = statement3.executeQuery(sql);
			        
			        Statement statement4 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		returnyesterday+ "' and l.DepAirportID=a1.Id and a1.City='"+destination+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+origin+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs4 = statement4.executeQuery(sql);
			        
			        Statement statement5 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		returning+ "' and l.DepAirportID=a1.Id and a1.City='"+destination+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+origin+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs5 = statement5.executeQuery(sql);
			        
			        Statement statement6 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		returntomorrow+ "' and l.DepAirportID=a1.Id and a1.City='"+destination+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+origin+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs6 = statement6.executeQuery(sql);
			        
			        boolean notfound=true;
			        if (rs.next()) {
			        	notfound=false;
			            String airId1 = rs.getString("airlineId");
			            String flightNo1 = rs.getString("flightNo");
			            String legNo1 = rs.getString("legNo");
			            String depTime1 = rs.getString("DepTime");
			            String arrTime1 = rs.getString("ArrTime"); 
			            String fareType1 = rs.getString("fareType");
			            String fare1 = rs.getString("fare");
			            String airClass1 = rs.getString("class");
			            String departId1 = rs.getString("departId");
			            String arrId1 = rs.getString("arrId");
			            
			            request.setAttribute("departId1", departId1);
			            request.setAttribute("arrId1", arrId1);
			            request.setAttribute("airClass1", airClass1);
			            request.setAttribute("airId1", airId1);
			            request.setAttribute("flightNo1", flightNo1);
			            request.setAttribute("legNo1", legNo1);
			            request.setAttribute("depTime1", depTime1);
			            request.setAttribute("arrTime1", arrTime1);
			            
			            request.setAttribute("fareType1", fareType1);
			            request.setAttribute("fare1", fare1);			            
			        }
			        else{
			        	request.setAttribute("airId1", "Not Found");
			        }
			        if(rs2.next()){
			        	notfound=false;
			            String airId2 = rs2.getString("airlineId");
			            String flightNo2 = rs2.getString("flightNo");
			            String legNo2 = rs2.getString("legNo");
			            String depTime2 = rs2.getString("DepTime");
			            String arrTime2 = rs2.getString("ArrTime"); 
			            String fareType2 = rs2.getString("fareType");
			            String fare2 = rs2.getString("fare");
			            String airClass2 = rs2.getString("class");
			            String departId2 = rs2.getString("departId");
			            String arrId2 = rs2.getString("arrId");
			            
			            request.setAttribute("departId2", departId2);
			            request.setAttribute("arrId2", arrId2);
			            request.setAttribute("airClass2", airClass2);
			            request.setAttribute("airId2", airId2);
			            request.setAttribute("flightNo2", flightNo2);
			            request.setAttribute("legNo2", legNo2);
			            request.setAttribute("depTime2", depTime2);
			            request.setAttribute("arrTime2", arrTime2);
			            request.setAttribute("fareType2", fareType2);
			            request.setAttribute("fare2", fare2);
			        }
			        else{
			        	request.setAttribute("airId2", "Not Found");
			        }
			        if(rs3.next()){
			        	notfound=false;
			            String airId3 = rs3.getString("airlineId");
			            String flightNo3 = rs3.getString("flightNo");
			            String legNo3 = rs3.getString("legNo");
			            String depTime3 = rs3.getString("DepTime");
			            String arrTime3 = rs3.getString("ArrTime"); 
			            String fareType3 = rs3.getString("fareType");
			            String fare3 = rs3.getString("fare");
			            String airClass3 = rs3.getString("class");
			            String departId3 = rs3.getString("departId");
			            String arrId3 = rs3.getString("arrId");
			            
			            request.setAttribute("departId3", departId3);
			            request.setAttribute("arrId3", arrId3);
			            request.setAttribute("airClass3", airClass3);
			            request.setAttribute("airId3", airId3);
			            request.setAttribute("flightNo3", flightNo3);
			            request.setAttribute("legNo3", legNo3);
			            request.setAttribute("depTime3", depTime3);
			            request.setAttribute("arrTime3", arrTime3);
			            request.setAttribute("fareType3", fareType3);
			            request.setAttribute("fare3", fare3);
			        }
			        else{
			        	request.setAttribute("airId3", "Not Found");
			        }
			        if(rs4.next()){
			        	notfound=false;
			            String airId4 = rs4.getString("airlineId");
			            String flightNo4 = rs4.getString("flightNo");
			            String legNo4 = rs4.getString("legNo");
			            String depTime4 = rs4.getString("DepTime");
			            String arrTime4 = rs4.getString("ArrTime"); 
			            String fareType4 = rs4.getString("fareType");
			            String fare4 = rs4.getString("fare");
			            String airClass4 = rs4.getString("class");
			            String departId4 = rs4.getString("departId");
			            String arrId4 = rs4.getString("arrId");
			            
			            request.setAttribute("departId4", departId4);
			            request.setAttribute("arrId4", arrId4);
			            request.setAttribute("airClass4", airClass4);
			            request.setAttribute("airId4", airId4);
			            request.setAttribute("flightNo4", flightNo4);
			            request.setAttribute("legNo4", legNo4);
			            request.setAttribute("depTime4", depTime4);
			            request.setAttribute("arrTime4", arrTime4);
			            request.setAttribute("fareType4", fareType4);
			            request.setAttribute("fare4", fare4);
			        }
			        else{
			        	request.setAttribute("airId4", "Not Found");
			        }
			        
			        if(rs5.next()){
			        	notfound=false;
			            String airId5 = rs5.getString("airlineId");
			            String flightNo5 = rs5.getString("flightNo");
			            String legNo5 = rs5.getString("legNo");
			            String depTime5 = rs5.getString("DepTime");
			            String arrTime5 = rs5.getString("ArrTime"); 
			            String fareType5 = rs5.getString("fareType");
			            String fare5 = rs5.getString("fare");
			            String airClass5 = rs5.getString("class");
			            String departId5 = rs5.getString("departId");
			            String arrId5 = rs5.getString("arrId");
			            
			            request.setAttribute("departId5", departId5);
			            request.setAttribute("arrId5", arrId5);
			            request.setAttribute("airClass5", airClass5);
			            request.setAttribute("airId5", airId5);
			            request.setAttribute("flightNo5", flightNo5);
			            request.setAttribute("legNo5", legNo5);
			            request.setAttribute("depTime5", depTime5);
			            request.setAttribute("arrTime5", arrTime5);
			            request.setAttribute("fareType5", fareType5);
			            request.setAttribute("fare5", fare5);
			        }
			        else{
			        	request.setAttribute("airId5", "Not Found");
			        }
			        
			        if(rs6.next()){
			        	notfound=false;
			            String airId6 = rs6.getString("airlineId");
			            String flightNo6 = rs6.getString("flightNo");
			            String legNo6 = rs6.getString("legNo");
			            String depTime6 = rs6.getString("DepTime");
			            String arrTime6 = rs6.getString("ArrTime"); 
			            String fareType6 = rs6.getString("fareType");
			            String fare6 = rs6.getString("fare");
			            String airClass6 = rs6.getString("class");
			            String departId6 = rs6.getString("departId");
			            String arrId6 = rs6.getString("arrId");
			            
			            request.setAttribute("departId6", departId6);
			            request.setAttribute("arrId6", arrId6);
			            request.setAttribute("airClass6", airClass6);
			            request.setAttribute("airId6", airId6);
			            request.setAttribute("flightNo6", flightNo6);
			            request.setAttribute("legNo6", legNo6);
			            request.setAttribute("depTime6", depTime6);
			            request.setAttribute("arrTime6", arrTime6);
			            request.setAttribute("fareType6", fareType6);
			            request.setAttribute("fare6", fare6);
			        }
			        else{
			        	request.setAttribute("airId6", "Not Found");
			        }
			        
			        if(notfound){
			        	request.setAttribute("login", login);
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/FlightNotFound.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			            request.setAttribute("origin", origin);
			            request.setAttribute("destination", destination);
			            request.setAttribute("login", login);
			            request.setAttribute("departing", departing);
			            request.setAttribute("yesterday", yesterday);
			            request.setAttribute("tomorrow", tomorrow);
			            request.setAttribute("returnyesterday", returnyesterday);
			            request.setAttribute("returning", returning);
			            request.setAttribute("returntomorrow", returntomorrow);
			            
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/SearchResultRoundFlexible.jsp");
			            dispatcher.forward(request, response);
			     
		        }

		        connection.close();
		    
		        
	        }
			
			catch(Exception e){
					e.printStackTrace();
			}
	}
}			
				
				
				
				
				
