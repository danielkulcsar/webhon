package JSPWEB.CH08.BOARD;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class BOARDCONTROLLER extends HttpServlet {
	
   
    public BOARDCONTROLLER() {
        super();      
    }
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request,response);
	}


	private void doPro(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException  {
		String action = request.getParameter("action");
        String jspPage = "BOARDLIST.jsp";
        BOARDDAO   DAO=BOARDDAO.getInstance();
        BOARDBean   bean   = new BOARDBean();
      try{
        if ((action == null) || (action.length() < 1))
        {
            action = "default";
        }

        if ("default".equals(action))
        {
            jspPage = "BOARDLIST.JSP";
        }
        else if ("list".equals(action))
        {
            
              ArrayList<BOARDBean>  ar= DAO.selectList();
            request.setAttribute("BOARD", ar);

            jspPage = "BOARDLIST.jsp";
        }else if(action.equals("selectlist"))
		{ 
			
		  int no= Integer.parseInt(request.getParameter("no"));
        	bean   = DAO.searchNo(no);
        	request.setAttribute("selectboard", bean);
        	jspPage="BOARDSELECTNO.jsp";
        	
		}else if(action.equals("insert")) {		
			
			
				jspPage="BOARDINSERT.jsp";
		}else if(action.equals("insertres"))
		{
						bean.setName(request.getParameter("name"));
			bean.setHomepage(request.getParameter("homepage"));
			bean.setEmail(request.getParameter("email"));
			bean.setContents(request.getParameter("contents"));
	           DAO.insertDAO(bean);
			jspPage="BOARDINSERTRES.jsp";
		}
        
        else if(action.equals("update")) {
			bean.setName(request.getParameter("name"));
			bean.setHomepage(request.getParameter("homepage"));
			bean.setEmail(request.getParameter("email"));
			bean.setContents(request.getParameter("contents"));
			  int no= Integer.parseInt(request.getParameter("no"));
		     bean.setNo(no);
		     DAO.updateDAO(bean);
		     
       	 // request.setAttribute("updateboard", bean);
        	jspPage="BOARDUPDATERES.jsp";
        	
        }
		else if(action.equals("delete")) {
			int no= Integer.parseInt(request.getParameter("no"));
			 
			  DAO.deleteDAO(no);
			  jspPage="BOARDDELETERES.jsp";
		}else{
			jspPage = "BOARDLIST.jsp";
		}
      }catch(Exception e){
    	   e.printStackTrace();
      }
	        dispatch(jspPage, request, response);
	    
	}

	    protected void dispatch(String jsp, HttpServletRequest request,
	        HttpServletResponse response) throws ServletException, IOException
	    {
	        if (jsp != null)
	        {
	        	  RequestDispatcher rd = request.getRequestDispatcher(jsp);
	            rd.forward(request, response);
	        	 }
	        
	    }
	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPro(request,response);
	}
	
	
	
    
}
