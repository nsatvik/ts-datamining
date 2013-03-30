package org.ck.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Map;
import java.util.TreeMap;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ck.beans.TimeSeriesBean;
import org.ck.gui.Constants;
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
public class MainController extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
	private static final String PATH_PREFIX = "/jsp/";
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
		runAlgorithm(tsBean);
		
		request.getSession().setAttribute("testBean", tsBean);
		
		String address = PATH_PREFIX + "Similarity/dtw_results.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

	/**
	 * Categorizes tsBean based on Task
	 * @param tsBean
	 */
	private void runAlgorithm(TimeSeriesBean tsBean)
	{
		switch(tsBean.getTaskType())
		{
		case SIMILARITY:
			runSimilarityAlgorithm(tsBean);
			break;
		case FORTUNE_TELLER:
			break;
		case ANOMALY_DETECTIVE:
			break;
		default:
			//Forward to errorPage.jsp ---- to be created
		}
	}

	/**
	 * Runs a similarity algorithm 
	 * @param tsBean
	 */
	private void runSimilarityAlgorithm(TimeSeriesBean tsBean)
	{
		switch(tsBean.getAlgorithmType())
		{
		case DTW:
			AlgorithmUtils.runDTWAlgorithm(tsBean);
			break;
		case SAX:
			break;
		case COMMON_SUBSEQUENCE:
			break;
		default:
			//Forward to errorPage.jsp ---- to be created
		}
	}
	
	/**
	 * This method initializes a time series bean from request parameters.
	 * The bean has scope = session
	 * @param request
	 * @return
	 */
	private TimeSeriesBean initTimesSeriesBean(HttpServletRequest request)
	{
		TimeSeriesBean tsBean = (TimeSeriesBean) request.getSession().getAttribute("testBean");
		
		if(tsBean == null)
			tsBean = new TimeSeriesBean();
		
		tsBean.setTaskType(Constants.TaskType.valueOf(request.getParameter("taskType")));
		tsBean.setAlgorithmType(Constants.AlgorithmType.valueOf(request.getParameter("algorithmType")));
		tsBean.setDataset(Constants.DatasetOptions.valueOf(request.getParameter("dataset")));
		
		DataHolder.setDataset(tsBean.getDataset());
		tsBean.setSample(new Sample(request.getServletContext().getRealPath("/") 
				+ DataHolder.TRAINING_FILE_NAME, 
				DataHolder.SAMPLE_NAME));
		
		log.info("I AM HERE");
		log.info(DataHolder.TRAINING_FILE_NAME);
		return tsBean;
	}

}
