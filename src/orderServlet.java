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
import java.util.Random;

public class orderServlet extends HttpServlet {
	
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
			String depTime = (String) request.getParameter("depTime");
			String fare = (String) request.getParameter("fare");
			String airClass= (String) request.getParameter("airClass");
			String bookingFee= (String) request.getParameter("bookingFee");
		

			Random rand = new Random();

			int  resrNo = rand.nextInt(900000) + 100000;
			String now = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());;
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        sql = "select p.firstName, p.lastname, p.id "+
	        			"from person p,customer c "+
	        			"where p.id=c.id and concat(p.FirstName,p.lastname)='"+login+"'"+";";
	        	ResultSet rs = statement.executeQuery(sql);      	
		        String accountNo="";
		        if (rs.next()) {
		        	accountNo= rs.getString("id");            
		        }
		        Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "INSERT INTO Reservation VALUES ("+resrNo+", '"+now+"', "+bookingFee+", "+fare+", NULL, "+accountNo+");";
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
		        	request.setAttribute("resrNo",resrNo );
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/order.jsp");
			            dispatcher.forward(request, response);
			        
//			      
		        
		        


		        connection.close();
		    
		        
	        }
			
			catch(Exception e){
					e.printStackTrace();
			}
	}
}			
				
				
				
				
				
