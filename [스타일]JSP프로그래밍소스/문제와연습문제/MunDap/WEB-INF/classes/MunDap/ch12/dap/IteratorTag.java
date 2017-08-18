package MunDap.ch12.dap;
import java.util.Collection;
import java.util.Iterator;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

public class IteratorTag extends TagSupport {
	 private String var=null;;

	
	  private Collection items=null;;

	 
	  private Iterator iterator=null;;

	  
	  public int doStartTag() throws JspException {
	   
	    iterator = items.iterator();

	    if (iterator.hasNext()) {
	      
	      pageContext.setAttribute(var, iterator.next());

	     
	      return EVAL_BODY_INCLUDE;
	    } else {
	     
	      return SKIP_BODY;
	    }
	  }

	 
	  public int doAfterBody() throws JspException {
	    if (iterator.hasNext()) {
	     
	      pageContext.setAttribute(var, iterator.next());

	     
	      return EVAL_BODY_AGAIN;
	    } else {
	     
	      return SKIP_BODY;
	    }
	  }

	  public void setVar(String s) {
	    this.var = s;
	  }

	  public void setItems(Collection coll) {
	    this.items = coll;
	  }
}
