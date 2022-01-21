package svc;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fasterxml.jackson.databind.ObjectMapper;

import ctr.AppCtr;
import model.CtrRequest;
import model.CtrResponse;
import model.InfoData.InfoDataBuilder;

@SuppressWarnings("serial")
@WebServlet("/svc")
public class AppSvc extends HttpServlet {
	private ObjectMapper objectMapper = new ObjectMapper();

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 메시지 처리
		CtrRequest reqCtr = new CtrRequest();
		reqCtr.setInfoData(new InfoDataBuilder()
								.enLastNm(request.getParameter(""))
								.enFirstNm(request.getParameter(""))
								.krLastNm(request.getParameter("krLastNm"))
								.krFirstNm(request.getParameter("krFirstNm"))
								.build());
        
		// 조회 서비스 호출
		CtrResponse resCtr = AppCtr.sel(reqCtr);
        
        // 응답 메시지 조립
		response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
		PrintWriter out = response.getWriter();
        String result = objectMapper.writeValueAsString(resCtr);
        out.print(result);
	}
	
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 요청 메시지 처리
		CtrRequest reqCtr = new CtrRequest();
		reqCtr.setInfoData(new InfoDataBuilder()
									.enLastNm(request.getParameter("enLastNm"))
									.enFirstNm(request.getParameter("enFirstNm"))
									.krLastNm(request.getParameter("krLastNm"))
									.krFirstNm(request.getParameter("krFirstNm"))
									.build());
		
		// 저장 서비스 호출
        AppCtr.sav(reqCtr);
	}
}
