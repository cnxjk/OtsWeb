package controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.alibaba.fastjson.JSONObject;

import util.WebTTSWS;

/**
 * 
 * 语音合成
 * 
 * @author chinaxjk
 *
 */
@SuppressWarnings("serial")
@WebServlet("/speechSynthesis")
public class SpeechSynthesis extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		super.doPost(req, resp);
	}

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		// TODO Auto-generated method stub
		resp.setContentType("application/json; charset=utf-8");
		resp.setCharacterEncoding("UTF-8");
		PrintWriter out = null;
		String reText = req.getParameter("requestText");
		String ent = req.getParameter("requestEnt");
		String projectPath = getServletContext().getRealPath("/resources/");
		WebTTSWS webTTSWS = new WebTTSWS();
		String fileWavName = null;
		try {
			JSONObject jsonObject = new JSONObject();
			out = resp.getWriter();
			fileWavName = webTTSWS.getVoice(reText, projectPath, ent);
			jsonObject.put("fileWavName", fileWavName);
			out.write(jsonObject.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
