package com.zhizhang.servlet;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zhizhang.CheckUtil;
import com.zhizhang.dao.selectOrder.DayOrderInfo;
import com.zhizhang.dao.selectOrder.SelectOrderInfo;
import com.zhizhang.dao.selectOrder.TimeOrderInfo;

/**
 * Servlet implementation class EverydayServlet
 */
@WebServlet("/EverydayServlet")
public class EverydayServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private SAXReader reader = null;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EverydayServlet() {
        super();
        reader = new SAXReader();
        // TODO Auto-generated constructor stub
    }

	/**
	 * everydayType 0为初始化出错，将WEB-INF/configs/everyDayOrder创建为一个文件，
	 * 				1为初始化出错，WEB-INF/configs/everyDayOrder没有创建 
	 * 				2为该文件夹下面没有对应的文件
	 * 				3为找到对应的文件并读取了文件数据
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String date = CheckUtil.getCurDate();
		request.setAttribute("time", date);
		String realPath = this.getServletContext().getRealPath(CheckUtil.getPropValue("orderDir"));
		File file = new File(realPath);
		if(file.exists() == false){
			file.mkdirs();
			request.setAttribute("everydayType", 1);
		}else if(file.isDirectory()){
			browserFile(file, date, request);
		}else{
			request.setAttribute("everydayType", 0);
		}
		RequestDispatcher d = request.getRequestDispatcher("/order/everydayOrder.jsp");
		d.forward(request,response);
	}
	
	private void browserFile(File file, String dateStr,HttpServletRequest request){
		File[] files = file.listFiles();
		
		FileInputStream fis;
		
		try {
			for(File subFile : files){
				if(subFile.isDirectory() && subFile.getName() == dateStr){//日期文件夹
					DayOrderInfo dayInfo = new DayOrderInfo();
					dayInfo.setDate(dateStr);
					dayInfo.setTime(CheckUtil.getCurTime());
					File[] subFiles = subFile.listFiles();
					
					int len = subFiles.length;
					if(len <= 0){
						request.setAttribute("everydayType", 2);
					}else{
						TimeOrderInfo[] timeOrderInfos = new TimeOrderInfo[len];
						
						for(int i = 0;i < len;i ++){//早餐，午餐，晚餐文件夹
							TimeOrderInfo timeOrderInfo = new TimeOrderInfo();
							File timeFile = subFiles[i];
							if(timeFile.exists() && timeFile.isDirectory()){
								timeOrderInfo.setTime(subFiles[i].getName());
								File[] selectFiles = timeFile.listFiles();
								int selectFilesLen = selectFiles.length;
								if(selectFilesLen > 0){
									SelectOrderInfo[] selectOrderInfos = new SelectOrderInfo[selectFilesLen];
									for(int j = 0;j < selectFilesLen;j ++)
									{
										File selectFile = selectFiles[j];
										fis = new FileInputStream(selectFile);
										Document doc = reader.read(fis);
										Element root = doc.getRootElement();
										SelectOrderInfo selectOrderInfo = new SelectOrderInfo();
										selectOrderInfo.parseFromXml(root);
										selectOrderInfos[j] = selectOrderInfo;
									}
									timeOrderInfo.setSelectOrderInfo(selectOrderInfos);
								}
							}
							timeOrderInfos[i] = timeOrderInfo;
						}
						dayInfo.setTimeOrderInfo(timeOrderInfos);
						request.setAttribute("dayInfo", dayInfo);
						request.setAttribute("everydayType", 3);
					}
				}
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
