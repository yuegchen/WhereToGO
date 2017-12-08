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
import java.util.Random;

public class userActionServlet extends HttpServlet {

	// public static void main(String[] args) {
	// // TODO Auto-generated method stub
	//
	// }
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
				sql = "select  l.airlineId,l.flightno,l.legno,l.DepAirportID,l.DepTime,l.ArrAirportID,l.ArrTime "
						+ "From  includes i,leg l " + "WHERE i.ResrNo=" + rn
						+ " AND l.AirlineID=i.AirlineID and l.FlightNo=i.FlightNo and l.legno=i.legno;";
				ResultSet rs = statement.executeQuery(sql);
				//
				String items = "";
				while (rs.next()) {
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
			} else if (action.equals("cancel")) {
				String rn = request.getParameter("rn");
				sql = "DELETE FROM reservation " + "WHERE ResrNo = " + rn + ";";
				int rs = statement.executeUpdate(sql);
				//
				if (rs == 1) {
				} else {
					request.setAttribute("message", "Sorry, This Transaction cannot be done at this time.");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
					dispatcher.forward(request, response);
					connection.close();
					return;
				}

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/user");
				dispatcher.forward(request, response);
			} else if (action.equals("Mybid")) {
				sql = "select a.AirlineID,a.FlightNo,a.Class,a.Date,a.NYOP,a.Accepted,a.legNo "
						+ "from person p,customer c, auctions a "
						+ "where p.id=c.id and a.AccountNo=c.AccountNo and concat(p.FirstName,p.lastname)='" + login
						+ "'";

				ResultSet rs = statement.executeQuery(sql);
				//
				String items = "";
				while (rs.next()) {
					items += rs.getString("airlineId");
					items += ",";
					items += rs.getString("flightNo");
					items += ",";
					items += rs.getString("legNo");
					items += ",";
					items += rs.getString("Class");
					items += ",";
					items += rs.getString("Date");
					items += ",";
					items += rs.getString("NYOP");
					items += ",";
					items += rs.getString("Accepted");
					items += ";";
				}
				request.setAttribute("items", items);

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MyBids.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("bid")) {
				String airId = request.getParameter("airlineId");
				String flightNo = request.getParameter("flightNo");
				String legNo = request.getParameter("legNo");
				double NYOP = Double.parseDouble(request.getParameter("NYOP"));
				System.out.println(airId);
				sql = "select f.fare,l.DepTime,f.Class " + "from leg l, fare f "
						+ "where l.AirlineID=f.AirlineID and f.FlightNo=l.FlightNo and l.legno=" + legNo
						+ " and l.AirlineID='" + airId + "' and l.FlightNo=" + flightNo + ";";

				ResultSet rs = statement.executeQuery(sql);
				String depTime = "", airClass = "";
				double hiddenFare = 0;
				if (rs.next()) {
					hiddenFare = rs.getInt("fare") * 0.9;
					depTime = rs.getString("DepTime");
					airClass = rs.getString("Class");
				} else {
					request.setAttribute("message", "Sorry, the flight or leg you entered was not found.");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
					dispatcher.forward(request, response);
					connection.close();
					return;
				}
				Random rand = new Random();

				int resrNo = rand.nextInt(900000) + 100000;
				String now = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
				Statement statement5 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				sql = "";
				sql = "select p.firstName, p.lastname, p.id " + "from person p,customer c "
						+ "where p.id=c.id and concat(p.FirstName,p.lastname)='" + login + "'" + ";";
				ResultSet rs5 = statement5.executeQuery(sql);
				String accountNo = "";
				if (rs5.next()) {
					accountNo = rs5.getString("id");
				}
				if (hiddenFare <= NYOP) {

					Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					sql = "INSERT INTO Reservation VALUES (" + resrNo + ", '" + now + "', " + NYOP / 10 + ", " + NYOP
							+ ", NULL, " + accountNo + ");";
					int rs2 = statement2.executeUpdate(sql);

					if (rs2 == 1) {

					} else {
						request.setAttribute("message", "Sorry,This Transaction cannot be completed at this time.");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
						dispatcher.forward(request, response);
						connection.close();
						return;
					}
					Statement statement3 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					sql = "INSERT INTO Includes VALUES (" + resrNo + ", '" + airId + "', " + flightNo + ", " + legNo
							+ ", '" + (depTime.substring(0, 10)) + "');";
					int rs3 = statement3.executeUpdate(sql);

					if (rs3 == 1) {

					} else {
						request.setAttribute("message", "Sorry,This Transaction cannot be completed at this time.");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
						dispatcher.forward(request, response);
						connection.close();
						return;
					}
					Statement statement4 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					int seatNo = rand.nextInt(201);
					String[] seat = { "A", "B", "C", "D", "E", "F", "G", "H" };
					String A = seat[rand.nextInt(8)];
					sql = "INSERT INTO ReservationPassenger VALUES(" + resrNo + ", " + accountNo + ", " + accountNo
							+ ", '" + seatNo + A + "', '" + airClass + "', 'Standard');";
					int rs4 = statement4.executeUpdate(sql);

					if (rs4 == 1) {

					}

					else {
						request.setAttribute("message", "Sorry,This Transaction cannot be completed at this time.");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
						dispatcher.forward(request, response);
						connection.close();
						return;
					}
					Statement statement6 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					sql = "INSERT INTO Auctions " + "(AccountNo,AirlineID,FlightNo,Class,Date,NYOP,Accepted,legNo) "
							+ "values " + "(" + accountNo + ",'" + airId + "'," + flightNo + ",'" + airClass + "','"
							+ now + "'," + NYOP + ",true," + legNo + ");";
					int rs6 = statement6.executeUpdate(sql);
					if (rs6 == 1) {

					} else {
						request.setAttribute("message", "Sorry,This Transaction cannot be completed at this time.");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
						dispatcher.forward(request, response);
						connection.close();
						return;
					}

					request.setAttribute("message", "Congratulation! Your price for " + airId + flightNo
							+ " is accepted! Your Reservation Number is " + resrNo + ".");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
					dispatcher.forward(request, response);
				} else {
					Statement statement6 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
							ResultSet.CONCUR_READ_ONLY);
					sql = "INSERT INTO Auctions " + "(AccountNo,AirlineID,FlightNo,Class,Date,NYOP,Accepted,legNo) "
							+ "values " + "(" + accountNo + ",'" + airId + "'," + flightNo + ",'" + airClass + "','"
							+ now + "'," + NYOP + ",false," + legNo + ");";
					int rs6 = statement6.executeUpdate(sql);
					if (rs6 == 1) {

					} else {
						request.setAttribute("message", "Sorry,This Transaction cannot be completed at this time.");
						RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
						dispatcher.forward(request, response);
						connection.close();
						return;
					}
					request.setAttribute("message", "Sorry, we cannot accept your price at this time.");
					RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/Message.jsp");
					dispatcher.forward(request, response);
					connection.close();
					return;
				}

				RequestDispatcher dispatcher = request.getServletContext().getRequestDispatcher("/MyBids.jsp");
				dispatcher.forward(request, response);
			} else if (action.equals("bestSeller")) {

				sql = "select i.airlineId, i.flightno, count(distinct i.ResrNo) as popularity " + "from includes i "
						+ "group by concat(AirlineID,'-',FlightNo) " + "order by popularity desc " + "limit 5;";

				ResultSet rs = statement.executeQuery(sql);
				//
				String items = "";
				while (rs.next()) {
					items += rs.getString("airlineId");
					items += ",";
					items += rs.getString("flightNo");
					items += ",";
					items += rs.getString("popularity");
					items += ";";
				}
				request.setAttribute("items", items);

				RequestDispatcher dispatcher = request.getServletContext()
						.getRequestDispatcher("/BestSellerFlights.jsp");
				dispatcher.forward(request, response);

			} else if (action.equals("fs")) {
				Statement statement5 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,
						ResultSet.CONCUR_READ_ONLY);
				sql = "";
				sql = "select p.firstName, p.lastname, p.id " + "from person p,customer c "
						+ "where p.id=c.id and concat(p.FirstName,p.lastname)='" + login + "'" + ";";
				ResultSet rs5 = statement5.executeQuery(sql);
				String accountNo = "";
				if (rs5.next()) {
					accountNo = rs5.getString("id");
				}
				
				sql = "  SELECT * FROM FlightReservation FR "+
			 "WHERE NOT EXISTS ( "+
			 "SELECT * FROM Reservation R, Includes I "+
			 "WHERE R.ResrNo = I.ResrNo AND FR.AirlineID = I.AirlineID "+
			 "AND FR.FlightNo = I.FlightNo AND R.AccountNo = "+accountNo+") "+
			 "ORDER BY FR.ResrCount DESC "+
			 "limit 5;";

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
			else if (action.equals("all")) {
				Statement statement2 = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
	        	sql = "select distinct r.ResrNo,r.ResrDate,r.BookingFee,r.TotalFare "+
"from person p,customer c, reservation r, includes i "+
"where p.id=c.id and concat(p.FirstName,p.lastname)='"+login+"' and c.AccountNo=r.AccountNo and r.ResrNo=i.ResrNo;";
	        	ResultSet rs2 = statement2.executeQuery(sql);
	        	String items="";
		        while(rs2.next()) {
		        	items += rs2.getString("ResrNo");
					items += ",";
					items += rs2.getString("ResrDate");
					items += ",";
					items += rs2.getString("BookingFee");
					items += ",";
					items += rs2.getString("TotalFare");
					items += ";";
		       
		        }
		       
		        request.setAttribute("items",items );
		       
		        
		            RequestDispatcher dispatcher 
		                    = request.getServletContext().getRequestDispatcher("/AllResr.jsp");
		            dispatcher.forward(request, response);
			}
			connection.close();

		}

		catch (Exception e) {
			e.printStackTrace();
		}
	}
}
