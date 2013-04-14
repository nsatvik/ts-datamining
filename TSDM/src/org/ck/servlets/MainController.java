package org.ck.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ck.beans.GraphBean;
import org.ck.beans.TimeSeriesBean;
import org.ck.gui.Constants;
import org.ck.gui.GraphDrawer;
import org.ck.gui.Constants.DatasetOptions;
import org.ck.sample.DataHolder;
import org.ck.sample.Sample;
import org.ck.similarity.DynamicTimeWarper;

/**
 * Servlet implementation class MainController
 * This is the main controller that gets requests from JSP pages, generates results through beans 
 * 		and forwards them to other jsp pages
 */
@WebServlet("/MainController")
public class MainController extends HttpServlet implements Constants
{
	private static final long serialVersionUID = 1L;
	
	private Logger log = Logger.getLogger(MainController.class.getName());
       
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{				
		TimeSeriesBean tsBean = initTimesSeriesBean(request);
		//GraphBean graphBean = initGraphBean(request, tsBean);
		
		runAlgorithm(tsBean, request, response);	
		
		//If control comes here, that means no forwarding took place
		response.getWriter().println("NOT FORWARDED");
	}

	/**
	 * Categorizes tsBean based on Task
	 * @param tsBean
	 * @param response 
	 * @param request 
	 * @throws IOException 
	 * @throws ServletException 
	 */
	private void runAlgorithm(TimeSeriesBean tsBean, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		String address = "";
		
		switch(tsBean.getTaskType())
		{
		case SIMILARITY:
			address = runSimilarityAlgorithm(tsBean);			
			break;
		case FORTUNE_TELLER:
			address = runFortuneTellingAlgorithm(tsBean);
			break;
		case ANOMALY_DETECTIVE:
			break;
		default:
			//Forward to errorPage.jsp ---- to be created
			address = PATH_PREFIX + "../error_page.jsp";
		}
		
		request.getSession().setAttribute("tsBean", tsBean);
		
		if(!address.isEmpty())
		{
			RequestDispatcher dispatcher = request.getRequestDispatcher(address);
			dispatcher.forward(request, response);
		}
	}

	/**
	 * Runs a Fortune Telling algorithm, to predict your future and help YOU decide
	 * 		how to go about your life. Pay me Rs. 100
	 * @param tsBean
	 */
	private String runFortuneTellingAlgorithm(TimeSeriesBean tsBean) 
	{
		log.info("Fortune Telling Algo");
		switch(tsBean.getAlgorithmType())
		{
		case MOVING_AVERAGE:
			return AlgorithmUtils.runMovingAverageSmoother(tsBean);	
		case MOVING_EXPONENTIAL:
			return AlgorithmUtils.runExponentialSmoother(tsBean);
			
		default:				
		}
		return "";
	}


	/**
	 * Runs a similarity algorithm 
	 * @param tsBean
	 */
	private String runSimilarityAlgorithm(TimeSeriesBean tsBean)
	{
		switch(tsBean.getAlgorithmType())
		{
		case DTW:
			return AlgorithmUtils.runDTWAlgorithm(tsBean);			
		case SAX:
			break;
		case COMMON_SUBSEQUENCE:
			break;
		default:
			//Forward to errorPage.jsp ---- to be created			
		}
		return "";
	}
	
	/**
	 * This method initializes a graph bean from request parameters.
	 * The bean has scope = session
	 * @param request
	 * @param tsBean
	 * @return
	 */
	private GraphBean initGraphBean(HttpServletRequest request,
			TimeSeriesBean tsBean)
	{
		GraphDrawer graphDrawer = new GraphDrawer(tsBean.getSample());
		GraphBean graphBean = (GraphBean) request.getSession().getAttribute("graphBean");
		
		if(graphBean == null)
			graphBean = new GraphBean();
		graphBean.setUrl(graphDrawer.graphSampleSubset(0, 59));
		return graphBean;
	}
	
	/**
	 * This method initializes a time series bean from request parameters.
	 * The bean has scope = session
	 * @param request
	 * @return
	 */
	private TimeSeriesBean initTimesSeriesBean(HttpServletRequest request)
	{
		TimeSeriesBean tsBean = (TimeSeriesBean) request.getSession().getAttribute("tsBean");
		
		if(tsBean == null)
			tsBean = new TimeSeriesBean();
		
		tsBean.setTaskType(request.getParameter("taskType"));
		tsBean.setAlgorithmType(request.getParameter("algorithmType"));
		tsBean.setDataset(request.getParameter("dataset"));
		tsBean.setSubTaskType(request.getParameter("subTaskType"));
		tsBean.setParams(request.getParameter("params"));
		
		DataHolder.setDataset(tsBean.getDataset());
		tsBean.setSample(new Sample(request.getServletContext().getRealPath("/") 
				+ DataHolder.TRAINING_FILE_NAME, 
				DataHolder.SAMPLE_NAME));
		
		log.log(Level.INFO, tsBean.toString());
		return tsBean;
	}

}
