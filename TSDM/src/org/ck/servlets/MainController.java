package org.ck.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.ck.beans.TimeSeriesBean;

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
		TimeSeriesBean testBean = new TimeSeriesBean();
		testBean.setTaskType(request.getParameter("taskType"));
		testBean.setAlgorithmType(request.getParameter("algorithmType"));
		
		request.setAttribute("testBean", testBean);
		
		String address = PATH_PREFIX + "Similarity/dtw_results.jsp";
		RequestDispatcher dispatcher = request.getRequestDispatcher(address);
		dispatcher.forward(request, response);
	}

}
