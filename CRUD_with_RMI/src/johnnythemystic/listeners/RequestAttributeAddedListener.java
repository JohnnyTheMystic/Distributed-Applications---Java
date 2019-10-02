package johnnythemystic.listeners;

import javax.servlet.ServletContextAttributeEvent;
import javax.servlet.ServletContextAttributeListener;
import javax.servlet.annotation.WebListener;
/**
 * Application Lifecycle Listener implementation class RequestAttributeAddedListener
 *
 */
@WebListener
public class RequestAttributeAddedListener implements ServletContextAttributeListener {

    /**
     * Default constructor. 
     */
    public RequestAttributeAddedListener() {
        // TODO Auto-generated constructor stub
    }
    
    /**
     * @see ServletContextAttributeListener#attributeAdded(ServletContextAttributeEvent)
     */
    public void attributeAdded(ServletContextAttributeEvent arg0)  { 
    	 System.out.println("Atributo añadido");
    }

	/**
     * @see ServletContextAttributeListener#attributeRemoved(ServletContextAttributeEvent)
     */
    public void attributeRemoved(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }

	/**
     * @see ServletContextAttributeListener#attributeReplaced(ServletContextAttributeEvent)
     */
    public void attributeReplaced(ServletContextAttributeEvent arg0)  { 
         // TODO Auto-generated method stub
    }
	
	
}
