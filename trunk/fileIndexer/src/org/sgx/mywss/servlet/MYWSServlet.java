package org.sgx.mywss.servlet;


import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.sgx.j2s.utils.Utils;
import org.sgx.mywss.Home;
import org.sgx.mywss.MYWSSUtils;
import org.sgx.mywss.tests.Test1;
import org.sgx.mywss.tests.pojomethod.PojoMethodTest;
/**
 * the total url will be something like:
 * 
 * $MAIN_SERVLET_URL?endpoint=endpointId&cmd=sendMessage&msg={'name':'method1', 'params':{'param1':paramvalue}}
 * 
 * @author sgurin
 */
public class MYWSServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	public static final String HTTPPARAM_MSG = "msg";
	public static final String HTTPPARAM_ENDPOINT = "endpoint";
	public static final String HTTPPARAM_CMD = "cmd";	

	public static final String ATTR_RESPONSE_ERROR = "error";
	public static final String ATTR_RESPONSE = "response";
	
	private static final String CMD_LIST_ENDPOINTS = "listEndpoints";
	private static final String CMD_SEND_MESSAGE = "sendMessage";
	private static final Object CMD_LIST_METHOD_NAMES = "listMethodNames";

	public void doGet(HttpServletRequest request, HttpServletResponse response)
	throws ServletException, IOException {
		PrintWriter out = response.getWriter();		
		String cmd = request.getParameter(HTTPPARAM_CMD);
		String responseStr="";		
		
		request.getRemoteAddr();
		if(Utils.isEmpty(cmd) || cmd.equals(CMD_SEND_MESSAGE)){
			String msgjson = request.getParameter(HTTPPARAM_MSG);
			String endpoint = request.getParameter(HTTPPARAM_ENDPOINT);
			if(Utils.isEmpty(msgjson) || Utils.isEmpty(endpoint)){
				responseStr=MYWSSUtils.buildErrorResponse(new Exception("both "+HTTPPARAM_ENDPOINT+" and "+HTTPPARAM_MSG+" parameters must be defined for sending a message. "));
			}				
			try {
				String resp = Home.getInstance().dispatch(endpoint, msgjson);
				responseStr = MYWSSUtils.buildNormalResponse(resp);
			} catch (Exception e) {
				e.printStackTrace();
				responseStr=MYWSSUtils.buildErrorResponse(e);
			}			
		}
		
		//why i comment the following: please use the always defined endpoint called "org.sgx.mywss.Home" for a MYWSS API via 
//		else if(!MYWSSUtils.isEmpty(cmd) && cmd.equals(CMD_LIST_ENDPOINTS)){
//			responseStr=Utils.toString(Home.getInstance().listEndpoints());
//		}
//		else if(!MYWSSUtils.isEmpty(cmd) && cmd.equals(CMD_LIST_METHOD_NAMES)) {
//			String endpoint = request.getParameter(HTTPPARAM_ENDPOINT);
//			if(MYWSSUtils.isEmpty(endpoint)){
//				responseStr=MYWSSUtils.buildErrorResponse(new Exception(HTTPPARAM_ENDPOINT+" parameters must be defined for listing methods "));				
//			}
//			else if(!Home.getInstance().hasEndpoint(endpoint)) {
//				responseStr=MYWSSUtils.buildErrorResponse(new Exception("no endpoint "+HTTPPARAM_ENDPOINT+" registered. "));
//			}
//			else {
//				try{
//					responseStr=Utils.toString(Home.getInstance().listMethodNames(endpoint));
//				}catch (Exception e) {
//					responseStr=MYWSSUtils.buildErrorResponse(e);
//				}
//			}
//		} 
		out.println(responseStr);
	}
	
	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {		
		doGet(req, resp);
	}

	@Override
	public void destroy() {
		super.destroy();
	}

	@Override
	public void init() throws ServletException {
		super.init();
		
		//testing
		Test1.main(null);
		PojoMethodTest.main(null);
	}
}
