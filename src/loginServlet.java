import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class loginServlet extends HttpServlet {
	
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
			String type=request.getParameter("type");	
           
			
			try{
				Connection connection = ConnectionUtils.getMyConnection();
				 
		        // Create statement
		        Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        String sql="";
		        
		        if(type.equals("customer")){
		        	sql = "select p.firstName, p.lastname, p.id "+
		        			"from person p,customer c "+
		        			"where p.id=c.id and concat(p.FirstName,p.lastname)='"+login+"'"+";";
		        	ResultSet rs = statement.executeQuery(sql);
//		        	
			        boolean notFound=true;
			        if (rs.next()) {
			        	notFound=false;
//			            
			        }
//			       1

			        if(notFound){
			        	request.setAttribute("login", login);
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/loginFail.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			            request.setAttribute("login", login);
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/index2.jsp");
			            dispatcher.forward(request, response);
			        

		        }
		        else if(type.equals("register")){
		        	
			        	
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/login.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        
			        

		        }
		        else if(type.equals("employee")) {
		        	sql = "select p.firstName, p.lastname, p.id, p.Address, p.City, p.State, p.ZipCode, e.StartDate, e.HourlyRate, e.IsManager "+
		        			"from person p, employee e "+
		        			"where p.id=e.id and concat(p.FirstName,p.lastname)='"+login+"'"+";";
		        	ResultSet rs = statement.executeQuery(sql);
		        	Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select p.firstName, p.lastname, p.id, p.Address, p.City, p.State, p.ZipCode, c.AccountNo, c.CreditCardNo, c.Phone, c.Email, c.CreationDate, c.Rating  "+
		        		  "from person p, customer c " +
		        		  "where p.id = c.id"+";";
		        	ResultSet rs1 = statement2.executeQuery(sql);
		        	Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
		        	sql = "select p.firstName, p.lastname, p.id, p.Address, p.City, p.State, p.ZipCode, e.ssn, e.StartDate, e.HourlyRate "+
		        			"from person p, employee e "+
		        			"where p.id=e.id and e.IsManager='false';";
		        	ResultSet rs2 = statement3.executeQuery(sql);
		        	
		        	boolean notFound=true;
			        if (rs.next()) {
			        	notFound=false;
			        	String id = rs.getString("Id");
			        	String firstName = rs.getString("FirstName");
			        	String lastName = rs.getString("LastName");
			        	String address = rs.getString("Address");
			        	String city = rs.getString("City");
			        	String state = rs.getString("State");
			        	String zipCode = rs.getString("ZipCode");
			        	String startDate = rs.getString("StartDate");
			        	String hourlyRate = rs.getString("HourlyRate");
			        	String isManager = rs.getString("IsManager");
			        	request.setAttribute("id", id);
			        	request.setAttribute("firstName", firstName);
			        	request.setAttribute("lastName", lastName);
			        	request.setAttribute("address", address);
			        	request.setAttribute("city", city);
			        	request.setAttribute("state", state);
			        	request.setAttribute("zipCode", zipCode);
			        	request.setAttribute("startDate", startDate);
			        	request.setAttribute("hourlyRate", hourlyRate);
			        	request.setAttribute("isManager", isManager);
			        	if(rs.getBoolean("IsManager")){

				        	int i=0;
				        	String cFirstName="";String cLastName="";String cID="";String cAddress="";
				        	String cCity="";String cState="";String cZipCode="";String cStartDate="";
				        	String cHourlyRate="";
				        	while(rs2.next()){
				        		cFirstName+=rs2.getString("FirstName")+";";
				        		cLastName+=rs2.getString("LastName")+";";
				        		cID+=rs2.getString("Id")+";";
				        		cAddress+=rs2.getString("Address")+";";
				        		cCity+=rs2.getString("City")+";";
				        		cState+=rs2.getString("State")+";";
				        		cZipCode+=rs2.getString("ZipCode")+";";
				        		cStartDate+=rs2.getString("StartDate")+";";
				        		cHourlyRate+=rs2.getString("HourlyRate")+";";
				        		i++;
				        		//System.out.print(i+"\n");
				        	}
				        	request.setAttribute("cID",cID );
				        	request.setAttribute("cFirstName",cFirstName );
					        request.setAttribute("cLastName",cLastName );
					        request.setAttribute("cAddress",cAddress );
					        request.setAttribute("cCity",cCity );
					        request.setAttribute("cState",cState );
					        request.setAttribute("cZipCode",cZipCode );
					        request.setAttribute("cStartDate",cStartDate );
					        request.setAttribute("cHourlyRate",cHourlyRate );
			        		
			        	}else{
			        	int i=0;
			        	String cFirstName="";String cLastName="";String cID="";String cAddress="";
			        	String cCity="";String cState="";String cZipCode="";String cCreditCardNo="";
			        	String cPhone="";String cEmail="";String cCreationDate="";String cRating="";
			        	while(rs1.next()){
			        		cFirstName+=rs1.getString("FirstName")+";";
			        		cLastName+=rs1.getString("LastName")+";";
			        		cID+=rs1.getString("Id")+";";
			        		cAddress+=rs1.getString("Address")+";";
			        		cCity+=rs1.getString("City")+";";
			        		cState+=rs1.getString("State")+";";
			        		cZipCode+=rs1.getString("ZipCode")+";";
			        		cCreditCardNo+=rs1.getString("CreditCardNo")+";";
			        		cPhone+=rs1.getString("Phone")+";";
			        		cEmail+=rs1.getString("Email")+";";
			        		cCreationDate+=rs1.getString("CreationDate")+";";
			        		cRating+=rs1.getString("Rating")+";";
			        		
			        		i++;
			        		System.out.print(i+"\n");
			        	}
			        	request.setAttribute("cID",cID );
			        	request.setAttribute("cFirstName",cFirstName );
				        request.setAttribute("cLastName",cLastName );
				        request.setAttribute("cAddress",cAddress );
				        request.setAttribute("cCity",cCity );
				        request.setAttribute("cState",cState );
				        request.setAttribute("cZipCode",cZipCode );
				        request.setAttribute("cCreditCardNo",cCreditCardNo );
				        request.setAttribute("cPhone",cPhone );
				        request.setAttribute("cEmail",cEmail );
				        request.setAttribute("cCreationDate",cCreationDate );
				        request.setAttribute("cRating",cRating );
			        	}
			        }
			        if(notFound){
			        	request.setAttribute("login", login);
			        	RequestDispatcher dispatcher 
			        	= request.getServletContext().getRequestDispatcher("/loginFail.jsp");
			        	dispatcher.forward(request, response);
			        	connection.close();
			        	return;
			        }
			        if(rs.getBoolean("IsManager")){
			        	request.setAttribute("login", login);
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/manager.jsp");
			            dispatcher.forward(request, response);
			        }else{
			        	request.setAttribute("login", login);
			            RequestDispatcher dispatcher 
			                    = request.getServletContext().getRequestDispatcher("/Customer_Rep.jsp");
			            dispatcher.forward(request, response);
			        }
		        }
		        connection.close();
		    
		        
	        }
			
			catch(Exception e){
					e.printStackTrace();
			}
	}
}			
				
			
				
				
				
				
