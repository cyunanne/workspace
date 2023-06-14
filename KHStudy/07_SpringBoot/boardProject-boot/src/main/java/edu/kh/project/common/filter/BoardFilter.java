package edu.kh.project.common.filter;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;

public class BoardFilter implements Filter {

	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		
		HttpServletRequest req = (HttpServletRequest)request;
		
		
		// /board/1
		// /board2/1/insert
		// /obard2/1/update
		
		String[] arr = req.getRequestURI().split("/");
		
		try {
			String boardCode = arr[2];
			List<Map<String, Object>> boardTypeList 
				= (List<Map<String, Object>>) (req.getServletContext().getAttribute("boardTypeList"));

			for (Map<String, Object> boardType : boardTypeList) {
				if ((boardType.get("BOARD_CODE") + "").equals(boardCode)) { // BOARD_CODE : BigInteger -> String
					req.setAttribute("boardName", boardType.get("BOARD_NAME"));
				}
			}
			
		} catch(Exception e) {
			e.printStackTrace();
		} finally {
			chain.doFilter(request, response);
		}
		
	}
	
}
