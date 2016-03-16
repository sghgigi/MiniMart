package ctrl;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MiniMart;

/**
 * Servlet implementation class Front
 */
@WebServlet({"/Front/*"})
public class Front extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	@Override
	public void init() throws ServletException
	{
		this.getServletContext().setAttribute("model", new MiniMart());
	}

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Front()
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
		String pathInfo = request.getPathInfo();
		if (pathInfo == null || pathInfo.equals("/")  || pathInfo.equals("/*"))
		{
			response.sendRedirect(request.getContextPath() + "/Front/Catalog");
		}
		else
		{
			RequestDispatcher rd = this.getServletContext().getNamedDispatcher(pathInfo.substring(1));
			if (rd != null)
			{
				rd.forward(request, response);
			}
			else
			{
				request.setAttribute("view", "404");
				request.getRequestDispatcher("/Master.jspx").forward(request, response);
			}
		}
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
