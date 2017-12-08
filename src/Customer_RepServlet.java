import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class Customer_RepServlet extends HttpServlet{
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String login = request.getParameter("login");
		
		String action = request.getParameter("action");

		try {
			Connection connection = ConnectionUtils.getMyConnection();

			// Create statement
			Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			String sql = "";

			if (action.equals("itinerary")) {
				String rn = request.getParameter("rn");
				sql = "select  l.airlineId,l.flightno,l.legno,l.DepAirportID,l.DepTime,l.ArrAirportID,l.ArrTime "+
"From  includes i,leg l "+
"WHERE i.ResrNo="+rn+" AND l.AirlineID=i.AirlineID and l.FlightNo=i.FlightNo and l.legno=i.legno;";
				ResultSet rs = statement.executeQuery(sql);
				//
				String items="";
				while(rs.next()) {
					items += rs.getString("airlineId");
					items += ",";
					items += rs.getString("flightNo");
					items += ",";
					items += rs.getString("legNo");
					items += ",";
					items += rs.getString("DepAirportID");
					items += ",";
					items += rs.getString("DepTime");
					items += ",";
					items += rs.getString("ArrAirportID");
					items += ",";
					items += rs.getString("ArrTime");
					items += ";";
					
				
				}
				request.setAttribute("items", items);
				request.setAttribute("rn", rn);
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/itinerary.jsp");
				dispatcher.forward(request, response);
			}
			else if (action.equals("cancel")) {
				String cID = request.getParameter("cID");
				sql = "DELETE FROM Customer "+
						"WHERE Id = "+cID+";";
				int rs = statement.executeUpdate(sql);
				//
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
			}
			else if (action.equals("edit")) {
				String cID = request.getParameter("cID");
				
				String firstName = (String)request.getParameter("cFirstName");
			  	String lastName = (String)request.getParameter("cLastName");
			  	String cAddress=(String)request.getParameter("cAddress");
			  	String cCity=(String)request.getParameter("cCity");
			  	String cState=(String)request.getParameter("cState");
			  	String cZipCode=(String)request.getParameter("cZipCode");
			  	String Phone = (String)request.getParameter("cPhone");
			  	String Rating=(String)request.getParameter("cRating");
			  	String CreditCardNo=(String)request.getParameter("cCreditCardNo");
			  	String CreationDate=(String)request.getParameter("cCreationDate");
			  	String email = (String)request.getParameter("cEmail");
				
			  	if(Rating != null) {
			  		Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Customer "+
							"Set Rating = "+ Rating+" "+
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
			  	if(Phone != null) {
			  		Statement statement9 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Customer "+
							"Set Phone = "+"'"+ Phone+"' "+
							"WHERE Id = "+cID+";";
					statement9.executeUpdate(sql);
			  	}
			  	if(CreditCardNo != null) {
			  		Statement statement10 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Customer "+
							"Set CreditCardNo = "+"'"+ CreditCardNo+"' "+
							"WHERE Id = "+cID+";";
					statement10.executeUpdate(sql);
			  	}
			  	if(CreationDate != null) {
			  		Statement statement11 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Customer "+
							"Set CreationDate = "+"'"+ CreationDate+"' "+
							"WHERE Id = "+cID+";";
					statement11.executeUpdate(sql);
			  	}
			  	if(email != null) {
			  		Statement statement12 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
			  		sql = "Update Customer "+
							"Set Email = "+"'"+ email+"' "+
							"WHERE Id = "+cID+";";
					statement12.executeUpdate(sql);
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
			}
			else if (action.equals("add")) {
				String id = (String)request.getParameter("cID");
			  	String firstName = (String)request.getParameter("cFirstName");
			  	String lastName = (String)request.getParameter("cLastName");
			  	String cAddress=(String)request.getParameter("cAddress");
			  	String cCity=(String)request.getParameter("cCity");
			  	String cState=(String)request.getParameter("cState");
			  	String cZipCode=(String)request.getParameter("cZipCode");
			  	String Phone = (String)request.getParameter("cPhone");
			  	String Rating=(String)request.getParameter("cRating");
			  	String CreditCardNo=(String)request.getParameter("cCreditCardNo");
			  	String CreationDate=new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());;;
			  	String email = (String)request.getParameter("cEmail");
				sql = "INSERT INTO Person " + 
						"(Id,FirstName,LastName,Address,City,State,ZipCode) " + 
						"values "+
						"("+id+","+"'"+firstName+"'"+","+"'"+lastName+"'"+","+"'"+cAddress+"'"+","+"'"+cCity+"'"+","+"'"+cState+"'"+","+cZipCode+");";
				int rs = statement.executeUpdate(sql);
				
				Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				sql = "INSERT INTO Customer " + 
						"(Id,AccountNo,CreditCardNo,Phone,Email,CreationDate,Rating) " + 
						"values "+
						"("+id+","+id+","+"'"+CreditCardNo+"'"+","+"'"+Phone+"'"+","+"'"+email+"'"+","+"'"+CreationDate+"'"+","+Rating+");";
				int rs2 = statement2.executeUpdate(sql);
				
				Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				sql = "INSERT INTO Passenger "+
"(Id,AccountNo,Phone,Email) "+
"values "+
" ("+id+","+id+",'"+Phone+"','"+email+"');";
				int rs3 = statement3.executeUpdate(sql);
				
				if(rs==1&&rs2==1&&rs3==1){}
				else{
					request.setAttribute("message","Sorry, This Transaction cannot be done at this time." );
		        	RequestDispatcher dispatcher 
		        	= request.getServletContext().getRequestDispatcher("/Message.jsp");
		        	dispatcher.forward(request, response);
		        	return;
				}
				
				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/login");
				dispatcher.forward(request, response);
			}
			else if (action.equals("fs")) {
				String firstName = (String)request.getParameter("cFN");
			  	String lastName = (String)request.getParameter("cLN");
				Statement statement5 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				sql = "select p.firstName, p.lastname, p.id " + "from person p,customer c "
						+ "where p.id=c.id and concat(p.FirstName,p.lastname)='" + firstName+lastName + "'" + ";";
				ResultSet rs5 = statement5.executeQuery(sql);
				String accountNo = "";
				if (rs5.next()) {
					accountNo = rs5.getString("id");
				}
				sql = "  SELECT * FROM FlightReservation FR "+
			 "WHERE NOT EXISTS ( "+
			 "SELECT * FROM Reservation R, Includes I "+
			 "WHERE R.ResrNo = I.ResrNo AND FR.AirlineID = I.AirlineID "+
			 "AND FR.FlightNo = I.FlightNo AND R.AccountNo = "+accountNo+" ) "+
			 "ORDER BY FR.ResrCount DESC "+
			 "limit 5;";
//				System.out.println(firstName+lastName+accountNo);
				ResultSet rs = statement.executeQuery(sql);
				//
				String items = "";
				while (rs.next()) {
					items += rs.getString("airlineId");
					items += ",";
					items += rs.getString("flightNo");
					items += ",";
					items += rs.getString("ResrCount");
					items += ";";
				}
				request.setAttribute("items", items);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/FlightSuggestion.jsp");
				dispatcher.forward(request, response);
			}

			connection.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
