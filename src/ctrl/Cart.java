package ctrl;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MiniMart;

/**
 * Servlet implementation class Catalog
 */
public class Cart extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Cart()
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
		String itemToAdd = request.getParameter("itemToAdd");
		String itemQty = request.getParameter("itemQty");
		if (itemToAdd != null)
		{
			try
			{
				sn.setAttribute("cart", model.buildCart(sn.getAttribute("cart"), itemToAdd, itemQty));
			}
			catch (Exception e)
			{
				request.setAttribute("error", e.getMessage());
			}
		}
		Enumeration<String> en = sn.getAttributeNames();
		while (en.hasMoreElements())
		{
			String name = en.nextElement();
			request.setAttribute(name, sn.getAttribute(name));
		}
		request.setAttribute("view", "Cart");
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
