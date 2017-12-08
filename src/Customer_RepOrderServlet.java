import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Customer_RepOrderServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {

		//
		  this.doPost(request, response);
	}

			/**
			 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
			 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
			String login=request.getParameter("login");	
//			System.out.print("login:"+login);
			if(login.equals("-")){
				
				request.setAttribute("message","Sorry, You have to log in or sign up first to book tickets." );
	        	RequestDispatcher dispatcher 
	        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
	        	dispatcher.forward(request, response);
	        	return;
			}
			String airId = (String) request.getParameter("airId");
			String flightNo = (String) request.getParameter("flightNo");
			String legNo = (String) request.getParameter("legNo");
			String accountNo = (String) request.getParameter("cID");
			String RepId = (String) request.getParameter("RepNo");
			
			

			Random rand = new Random();

			int  resrNo = rand.nextInt(900000) + 100000;
			String now = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());;
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        sql = "select l.DepTime,f.Fare,f.class "+
	        			"from leg l,fare f "+
	        			"where l.AirlineID = '"+airId+"' and l.FlightNo = '"+flightNo+"' and l.LegNo = '"+legNo+"' and f.AirlineID=l.AirlineID and f.FlightNo=l.FlightNo;";
	        	ResultSet rs = statement.executeQuery(sql);
	        	
	        	
	        	
		        if (rs.next()) {
		        	String depTime = rs.getString("DepTime"); 
		        	String fare = rs.getString("fare");
		            String airClass = rs.getString("class");
		            double result = Double.parseDouble(fare)/10;
		        	String bookingFee = ""+result;
		        
		        	Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "INSERT INTO Reservation VALUES ("+resrNo+", '"+now+"', "+bookingFee+", "+fare+", "+RepId+", "+accountNo+");";
		        	int rs2 = statement2.executeUpdate(sql);
		        	
			        if (rs2==1) {
			        	
			        }
			        else{
			        	request.setAttribute("message","Sorry,This Transaction cannot be completed at this time." );
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			        Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "INSERT INTO Includes VALUES ("+resrNo+", '"+airId+"', "+flightNo+", "+legNo+", '"+(depTime.substring(0,10))+"');";
		        	int rs3 = statement3.executeUpdate(sql);
		        	
			        if (rs3==1) {
			        	
			        }
			        else{
			        	request.setAttribute("message","Sorry,This Transaction cannot be completed at this time." );
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			        Statement statement4 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			        int  seatNo = rand.nextInt(201);
			        String[] seat={"A","B","C","D","E","F","G","H"};
			        String A=seat[rand.nextInt(8)];
		        	sql = "INSERT INTO ReservationPassenger VALUES("+resrNo+", "+accountNo+", "+accountNo+", '"+seatNo+A+"', '"+airClass+"', 'Standard');";
		        	int rs4 = statement4.executeUpdate(sql);
		        	
		        	if (rs4==1) {
				        	
				        }
		        	
				    else{
				        	request.setAttribute("message","Sorry,This Transaction cannot be completed at this time." );
				        	RequestDispatcher dispatcher 
				        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
				        	dispatcher.forward(request, response);
				        	connection.close();
				        	return;
				        }
		        	request.setAttribute("message", "order for " + airId + flightNo
							+ " is placed! Reservation Number is " + resrNo + ".");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
					dispatcher.forward(request, response);			      
		        
		        


		        }
		        else{
		        	request.setAttribute("message","Sorry,This Transaction cannot be completed at this time." );
		        	RequestDispatcher dispatcher 
		        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
		        	dispatcher.forward(request, response);
		        	connection.close();
		        	return;
		        }
		        connection.close();
		    
		        
	        }
			
			catch(Exception e){
					e.printStackTrace();
			}
	}
}
