package JSPWEB.CH10.CHART;

import java.awt.Font;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartUtilities;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.plot.PiePlot;
import org.jfree.data.general.DefaultPieDataset;

/**
 * Servlet implementation class myCHART
 */
public class myCHART extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public myCHART() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
    
		DefaultPieDataset dataset = new DefaultPieDataset();
		  dataset.setValue("�λ��", 3600000);
		  dataset.setValue("������", 80000000);
		  dataset.setValue("�ѹ���", 90000000);
		  dataset.setValue("��ȹ��", 75000000);
    
		 
    JFreeChart jchart=ChartFactory.createPieChart3D
    ( 
     "����Ƽ ��ݸ���",dataset,true,true,false); 
    
      jchart.getTitle().setFont(new Font("����",Font.BOLD,20));
        jchart.getLegend().setItemFont(new Font("����",Font.BOLD,20)) ;
        
     PiePlot  plot=(PiePlot)jchart.getPlot();
     plot.setLabelFont(new Font("����",Font.BOLD,15));
    
       
   
    response.setContentType("image/jpeg"); 
    ServletOutputStream out=response.getOutputStream();
    ChartUtilities.writeChartAsJPEG(out,jchart,600,400); 
  }




	}


