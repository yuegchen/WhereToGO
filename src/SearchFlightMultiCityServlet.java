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

public class SearchFlightMultiCityServlet extends HttpServlet {
	
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
			String departing2=request.getParameter("Departing2");
			String origin=request.getParameter("Origin");
			String destination=request.getParameter("Destination");
			String origin2=request.getParameter("Origin2");
			String destination2=request.getParameter("Destination2");
			
           
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        
		        
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
		        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
		        		departing+ "' and l.DepAirportID=a1.Id and a1.City='"+origin+"' "+
		        		"and l.ArrAirportID=a2.Id and a2.City='"+destination+"' "+
		        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
		        	ResultSet rs = statement.executeQuery(sql);
		        	Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select l.airlineId,l.flightNo, l.legNo, l.DepTime, l.ArrTime, f.FareType,f.Fare,f.class,a1.Id as departId, a2.Id as arrId "+
			        		"From leg l,airport a1, airport a2,fare f "+"Where SUBSTR(l.DepTime,1,10)='"+
			        		departing2+ "' and l.DepAirportID=a1.Id and a1.City='"+origin2+"' "+
			        		"and l.ArrAirportID=a2.Id and a2.City='"+destination2+"' "+
			        		"and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
			        ResultSet rs2 = statement2.executeQuery(sql);
			        boolean notFound=true;
			        if (rs.next()) {
			        	notFound=false;
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
			        if (rs2.next()) {
			        	notFound=false;
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
			        if(notFound){
			        	request.setAttribute("login", login);
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/FlightNotFound.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			            request.setAttribute("origin", origin);
			            request.setAttribute("destination", destination);
			            request.setAttribute("origin2", origin2);
			            request.setAttribute("destination2", destination2);
			            request.setAttribute("login", login);
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/SearchResultMultiCity.jsp");
			            dispatcher.forward(request, response);
			        
		        connection.close();
		    
		        
	        }
			
			catch(Exception e){
					e.printStackTrace();
			}
	}
}			
				
				
				
				
				
