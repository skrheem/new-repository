package mvc.controller;

import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import mvc.command.CommandHandler;
import mvc.command.NullHandler;

public class ControllerUsingURI extends HttpServlet {

	// MVC 패턴2에서는 서블릿이 컨트롤러의 역할을 맡는다.
	// 클라이언트의 요청을 컨트롤러가 어떻게 파악할 것인지
	// 이동 경로를 어떻게 잡을 것인지, 무엇을 실행할지 처리하는 프로그램
	
	// Map<키, 값> : 키로 String형 데이터를, 값으로 CommandHandler 타입의 인스턴스를 받는 맵
	private Map<String, CommandHandler> commandHandlerMap = new HashMap<>();

	// 서블릿이 초기화됐을 때 한번만 실행되는 메서드
	public void init() throws ServletException {
		// web.xml에 정의해둔 configFile이라는 이름의 파라미터를 가져온다.
		// 이 파라미터에는 파일의 주소(/WEB-INF/commandHandler.properties)가 담겨있다.
		String configFile = getInitParameter("configFile");
		Properties prop = new Properties();
		String configFilePath = getServletContext().getRealPath(configFile);
		
		try (FileReader fis = new FileReader(configFilePath)) {
			prop.load(fis);
		} catch (IOException e) {
			throw new ServletException(e);
		}
		
		Iterator<?> keyIter = prop.keySet().iterator();
		
		while (keyIter.hasNext()) {
			String command = (String) keyIter.next();
			String handlerClassName = prop.getProperty(command);
			try {
				Class<?> handlerClass = Class.forName(handlerClassName);
				CommandHandler handlerInstance = (CommandHandler) handlerClass.newInstance();
				commandHandlerMap.put(command, handlerInstance);
			} catch (ClassNotFoundException | InstantiationException | IllegalAccessException e) {
				throw new ServletException(e);
			}
		}
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		process(request, response);
	}

	private void process(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String command = request.getRequestURI();
		if (command.indexOf(request.getContextPath()) == 0) {
			command = command.substring(request.getContextPath().length());
		}
		CommandHandler handler = commandHandlerMap.get(command);
		if (handler == null) {
			handler = new NullHandler();
		}
		String viewPage = null;
		try {
			viewPage = handler.process(request, response);
			System.out.println(viewPage); // 필요에 따라 삭제할 수 있습니다.
		} catch (Exception e) {
			throw new ServletException(e);
		}
		if (viewPage != null) {
			RequestDispatcher dispatcher = request.getRequestDispatcher(viewPage);
			dispatcher.forward(request, response);
		}
	}
}
