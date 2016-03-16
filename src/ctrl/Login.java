package ctrl;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MiniMart;

/**
 * Servlet implementation class Login
 */
public class Login extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login()
	{
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		MiniMart model = (MiniMart) this.getServletContext().getAttribute("model");
		HttpSession sn = request.getSession();
		
		String view = "Login";
		if (request.getParameter("loginButton") != null)
		{
			String accountNumber = request.getParameter("accountNum");
			if (model.authenticate(accountNumber, request.getParameter("pass")))
			{
				sn.setAttribute("account", accountNumber);
				sn.setAttribute("accountName", model.getCustomer(accountNumber));
				view = "Cart";
			}
			else
			{
				request.setAttribute("error", "Could not be t be authenticated!");
			}
		}
		request.setAttribute("view", view);
		request.getRequestDispatcher("/Master.jspx").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException
	{
		this.doGet(request, response);
	}
}
