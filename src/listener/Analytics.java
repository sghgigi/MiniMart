package listener;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionAttributeListener;
import javax.servlet.http.HttpSessionBindingEvent;

/**
 * Application Lifecycle Listener implementation class Analytics
 *
 */
@WebListener
public class Analytics implements HttpSessionAttributeListener {

    /**
     * Default constructor. 
     */
    public Analytics() {
        // TODO Auto-generated constructor stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeRemoved(HttpSessionBindingEvent)
     */
    public void attributeRemoved(HttpSessionBindingEvent event) {
        // TODO Auto-generated method stub
    }

	/**
     * @see HttpSessionAttributeListener#attributeAdded(HttpSessionBindingEvent)
     */
    public void attributeAdded(HttpSessionBindingEvent event) {
    	this.handleStartToCheckout(event);
    	this.handleAddTime(event);
    }

	/**
     * @see HttpSessionAttributeListener#attributeReplaced(HttpSessionBindingEvent)
     */
    public void attributeReplaced(HttpSessionBindingEvent event) {
    	this.handleAddTime(event);
    }
    
    private void handleAddTime(HttpSessionBindingEvent event)
    {
    	if (event.getName().equals("added"))
    	{
    		if (event.getSession().getServletContext().getAttribute("avgAddTime") == null)    		
    			event.getSession().getServletContext().setAttribute("avgAddTime", 0.0);
    		
    		if (event.getSession().getServletContext().getAttribute("totalAddTime") == null)    		
    			event.getSession().getServletContext().setAttribute("totalAddTime", 0);
    		
    		if (event.getSession().getServletContext().getAttribute("addTimeCount") == null)    		
    			event.getSession().getServletContext().setAttribute("addTimeCount", 0);
    		
    		if (event.getSession().getAttribute("start") == null)
    			event.getSession().setAttribute("start", event.getSession().getCreationTime());
    		
    		long previous = Long.parseLong(event.getSession().getAttribute("start").toString());
    		long current = event.getSession().getLastAccessedTime();
			long elapsed = current - previous;
			
			event.getSession().setAttribute("start", current);
			
			double total = Double.parseDouble(event.getSession().getServletContext().getAttribute("totalAddTime").toString()) + elapsed;
    		double count = Double.parseDouble(event.getSession().getServletContext().getAttribute("addTimeCount").toString()) + 1;
    		
    		event.getSession().getServletContext().setAttribute("avgAddTime", total / count);	     		
    	}
    }
    
    private void handleStartToCheckout(HttpSessionBindingEvent event)
    {
    	if (event.getName().equals("checkout"))
    	{
    		if (event.getSession().getServletContext().getAttribute("avgStartToCheckout") == null)    		
    			event.getSession().getServletContext().setAttribute("avgStartToCheckout", 0.0);
    		
    		if (event.getSession().getServletContext().getAttribute("totalStartToCheckout") == null)    		
    			event.getSession().getServletContext().setAttribute("totalStartToCheckout", 0);
    		
    		if (event.getSession().getServletContext().getAttribute("startToCheckoutCount") == null)    		
    			event.getSession().getServletContext().setAttribute("startToCheckoutCount", 0);
    		
    		long elapsed = event.getSession().getLastAccessedTime() - event.getSession().getCreationTime();
    		
    		double total = Double.parseDouble(event.getSession().getServletContext().getAttribute("totalStartToCheckout").toString()) + elapsed;
    		double count = Double.parseDouble(event.getSession().getServletContext().getAttribute("startToCheckoutCount").toString()) + 1;
    		
    		event.getSession().getServletContext().setAttribute("avgStartToCheckout", total / count);    		
    	}
    }
}
