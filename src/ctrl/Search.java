package ctrl;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.MiniMart;

/**
 * Servlet implementation class Catalog
 */
public class Search extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Search()
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
		String searchField = request.getParameter("searchField");
		request.setAttribute("catalog", model.retrieveItems(searchField));
		request.setAttribute("searchField", searchField);
		request.setAttribute("view", "Catalog");
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
