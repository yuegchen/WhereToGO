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

public class userServlet extends HttpServlet {
	
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
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        sql = "select p.firstName, p.lastname, p.id ,p.Address, p.City, p.State, p.ZipCode, c.email, c.CreditCardNo,c.Phone,c.Rating,c.CreationDate "+
"from person p,customer c " +
"where p.id=c.id and concat(p.FirstName,p.lastname)='"+login+"';";
	        	ResultSet rs = statement.executeQuery(sql);
//	        	
		        
		        if (rs.next()) {
		        	String id = rs.getString("Id");
		        	String firstName = rs.getString("FirstName");
		        	String lastName = rs.getString("LastName");
		        	String address = rs.getString("Address");
		        	String city = rs.getString("City");
		        	String state = rs.getString("State");
		        	String zipCode = rs.getString("ZipCode");
		        	String email = rs.getString("email");
		        	String CreditCardNo = rs.getString("CreditCardNo");
		        	String Phone = rs.getString("Phone");
		        	String Rating = rs.getString("Rating");
		        	String CreationDate = rs.getString("CreationDate");
		            request.setAttribute("id",id );
		            request.setAttribute("Phone",Phone );
		            request.setAttribute("Rating",Rating );
		            request.setAttribute("address",address );
		            request.setAttribute("email",email );
		            request.setAttribute("city",city );
		            request.setAttribute("state",state );
		            request.setAttribute("zipCode",zipCode );
		            request.setAttribute("CreditCardNo",CreditCardNo );
		            request.setAttribute("CreationDate",CreationDate );
		        }
		        Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select distinct r.ResrNo,r.ResrDate,r.BookingFee,r.TotalFare "+
"from person p,customer c, reservation r, includes i "+
"where p.id=c.id and concat(p.FirstName,p.lastname)='"+login+"' and c.AccountNo=r.AccountNo and r.ResrNo=i.ResrNo and i.Date>='2011-01-01';";
		        	ResultSet rs2 = statement2.executeQuery(sql);
		        	String resrNos="";String resrDates="";String bookingFees="";String totalFares="";
			        while(rs2.next()) {
			        	resrNos=resrNos+rs2.getString("ResrNo")+";";
			        	resrDates=resrDates+rs2.getString("ResrDate")+";";
			        	bookingFees=bookingFees+rs2.getString("BookingFee")+";";
			        	totalFares=totalFares+rs2.getString("TotalFare")+";";
			        }
//			        System.out.println(resrNos);
			        request.setAttribute("resrNos",resrNos );
			        request.setAttribute("resrDates",resrDates );
			        request.setAttribute("bookingFees",bookingFees );
			        request.setAttribute("totalFares",totalFares );
			        
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/UserCenter.jsp");
			            dispatcher.forward(request, response);

		        connection.close();
		    
		        
	        }
			
			catch(Exception e){
					e.printStackTrace();
			}
	}
}			
				
				
				
				
				
