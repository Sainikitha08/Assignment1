

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
//		response.getWriter().append("Served at: ").append(request.getContextPath());
		
		response.setContentType("text/html");
		
		PrintWriter out = response.getWriter();
		String username = request.getParameter("username");
//		int age = Integer.parseInt(request.getParameter("age"));
		String password = request.getParameter("password");
//		out.write("Data Fetched Successfully");
		
		// Connecting to the database
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/dxc","root","root");
//			String cmd = "INSERT INTO Demo VALUES(?,?);";
			String cmd = "SELECT * FROM user ";
			PreparedStatement pst = conn.prepareStatement(cmd);
			
//			pst.setString(1, username);
//			pst.setInt(2, age);
			
			ResultSet rs = pst.executeQuery();
			boolean flag = false;
			
			while(rs.next()) {
				String _username = rs.getString("username");
				String _password = rs.getString("password");
				
				if(_username.equalsIgnoreCase(username) && _password.equalsIgnoreCase(password)) {
					flag = true;
					out.println("<h3> Logged In Successfully! </h3>");
				}
			} 
			
			if(flag == false) {
				out.println("<h3> Wrong Username or Password, Try Again! </h3>");
			}
			
//			if(retVal > 1) {
//				out.write("Data written Successfully to Database..!");
//			}
			
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			out.println("<h3> Go back to <a href=\"home.html\">HomePage</a></h3>");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
