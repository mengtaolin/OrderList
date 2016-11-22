package com.zhizhang.servlet;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import javax.servlet.RequestDispatcher;
import javax.servlet.Servlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.stream.FactoryConfigurationError;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.zhizhang.CheckUtil;
import com.zhizhang.dao.AllOrderCompanyInfo;
import com.zhizhang.dao.CompanyDataInfo;
import com.zhizhang.dao.DepartmentInfo;
import com.zhizhang.dao.OrderCompanyInfo;

import net.sf.json.JSONObject;

/**
 * 初始化数据内容
 * Servlet implementation class InitServlet
 */
@WebServlet(description = "初始化加载数据的servlet", urlPatterns = { "/InitServlet" })
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CompanyDataInfo data;
    private AllOrderCompanyInfo allOrderInfo;
    private Map<String, String> timeMap = null;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * 初始化系统参数，第一步就将配置数据全部导入
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
			String realPath = config.getServletContext().getRealPath("/WEB-INF/");
			SAXReader reader = new SAXReader();
			FileInputStream fis = null;
			this.parseProperties(config, realPath);
			this.parseCompanyData(config, realPath, reader, fis);
			this.parseOrderCompanyData(config, realPath, reader, fis);
	}
	
	private void parseProperties(ServletConfig config, String realPath) {
		InputStream in;
		try {
			in = new BufferedInputStream (new FileInputStream(realPath + "/configs/prop.properties"));
			Properties prop = new Properties();
			prop.load(in);     ///加载属性列表
			Iterator<String> it=prop.stringPropertyNames().iterator();
			this.timeMap = new HashMap<String,String>();
			while(it.hasNext()){
				String key=it.next();
				timeMap.put(key, prop.getProperty(key));
			}
			CheckUtil.init(this.timeMap);
			config.getServletContext().setAttribute("timeMap", timeMap);
			in.close();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * 解析我们本公司的数据
	 * @param config
	 * @param realPath
	 * @param reader
	 * @param fis
	 */
	private void parseCompanyData(ServletConfig config,String realPath,SAXReader reader,FileInputStream fis){
		try {
			fis = new FileInputStream(realPath + "configs/employee.xml");
			Document doc = reader.read(fis);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> childElements = root.elements();
			this.data = new CompanyDataInfo();
			for(Element element : childElements){
				
				DepartmentInfo department = new DepartmentInfo();
				department.parsData(element);
				this.data.departments.add(department);
			}
			config.getServletContext().setAttribute("data", this.data);
			
			fis.close();
			fis = null;
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	/**
	 * 解析快餐公司的数据
	 * @param config
	 * @param realPath
	 * @param reader
	 * @param fis
	 */
	private void parseOrderCompanyData(ServletConfig config,String realPath,SAXReader reader,FileInputStream fis){
		try {
			fis = new FileInputStream(realPath + "configs/OrderConfig.xml");
			Document doc = reader.read(fis);
			Element root = doc.getRootElement();
			@SuppressWarnings("unchecked")
			List<Element> childElements = root.elements();
			this.allOrderInfo = new AllOrderCompanyInfo();
			for(Element element : childElements){
				
				OrderCompanyInfo companyInfo = new OrderCompanyInfo();
				companyInfo.parsData(element);
				this.allOrderInfo.getAllInfo().add(companyInfo);
			}
			config.getServletContext().setAttribute("allOrderInfo", this.allOrderInfo);
			fis.close();
			fis = null;
//			JSONObject jsonObj = new JSONObject();
//			@SuppressWarnings("static-access")
//			JSONObject json = jsonObj.fromObject(allOrderInfo);
//			System.out.println(json.toString());
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (FactoryConfigurationError e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (DocumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	

	/**
	 * 实现初始化后的跳转
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String url = "order/orderList.jsp";
		if(CheckUtil.checkTime() == false){
			url = "order/noOpenTime.jsp";
		}
		RequestDispatcher d = request.getRequestDispatcher(url);
		d.forward(request,response);
	}

	/**
	 * 实现前端的选择部门后再选择对应部门中的员工
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int departId = Integer.parseInt(request.getParameter("selectId"));
		System.out.println("doPost :" + departId);
		DepartmentInfo department = this.data.getDepartmentById(departId);
		
		//转换为json格式，转化Json格式对象必须是Bean对象，既所有属性都实现了get，set方法。
		JSONObject jsonObj = new JSONObject();
		@SuppressWarnings("static-access")
		JSONObject json = jsonObj.fromObject(department);
		
		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		out = null;
	}

}
