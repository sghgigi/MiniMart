/*package Filter;

import java.io.IOException;
import java.util.List;

import javax.servlet.DispatcherType;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.ItemDAO;
import beans.ItemBean;
import model.MiniMart;

*//**
 * Servlet Filter implementation class Adhoc
 *//*
@WebFilter(dispatcherTypes = {
				DispatcherType.REQUEST, 
				DispatcherType.FORWARD
		}
					, urlPatterns = { "/Front/*" })
public class Adhoc implements Filter {

	private static final String recommended = "2002H815";
	private static final String addedItem = "2002H815";
	
    *//**
     * Default constructor. 
     *//*
    public Adhoc() {
        // TODO Auto-generated constructor stub
    }

	*//**
	 * @see Filter#destroy()
	 *//*
	public void destroy() {
		// TODO Auto-generated method stub
	}

	*//**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 *//*
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		// TODO Auto-generated method stub
		// place your code here
		
		HttpServletRequest req = (HttpServletRequest)request;
		HttpServletResponse res = (HttpServletResponse)response;
		
			System.out.println("filter in add item="+req.getParameter("addedItemID"));
			HttpSession sn = req.getSession();
		
			List<ItemBean> cata = (List<ItemBean>) sn.getAttribute("Catalog");
			ItemDAO dao = new ItemDAO();
			cata.remove(dao.retrieveItem(addedItem));
			cata.add(new ItemBean("2002H815", "Gouda Cheese by BD", 5.88));
			sn.setAttribute("Catalog", cata);
			req.setAttribute("Catalog", cata);
			
		    chain.doFilter(request, response);
		
	}//end doFilter

	*//**
	 * @see Filter#init(FilterConfig)
	 *//*
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
*/