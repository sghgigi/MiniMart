package ctrl;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.MiniMart;

/**
 * Servlet implementation class Catalog
 */
public class Done extends HttpServlet
{
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Done()
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
		if (sn.getAttribute("account") == null)
		{
			this.getServletContext().getNamedDispatcher("Login").forward(request, response);
		}
		else
		{
			try
			{
				Object account = sn.getAttribute("account");
				String exportPath = this.getServletContext().getRealPath("/export");
				File hash = File.createTempFile((String) account, ".xml", new File(exportPath)); 
				model.export(account, sn.getAttribute("accountName"), sn.getAttribute("cart"), hash);
				request.setAttribute("filepath", this.getServletContext().getContextPath() + "/export/" + hash.getName());
			}
			catch (Exception e)
			{
				System.out.println(e);
			}
			request.setAttribute("view", "Confirmation");
			request.getRequestDispatcher("/Master.jspx").forward(request, response);
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
