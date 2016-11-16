package com.zhizhang.servlet;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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

import com.zhizhang.dao.AllOrderCompanyInfo;
import com.zhizhang.dao.CompanyDataInfo;
import com.zhizhang.dao.DepartmentInfo;
import com.zhizhang.dao.OrderCompanyInfo;

import net.sf.json.JSONObject;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet(description = "初始化加载数据的servlet", urlPatterns = { "/InitServlet" })
public class InitServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private CompanyDataInfo data;
    private AllOrderCompanyInfo allOrderInfo;
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InitServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
			String realPath = config.getServletContext().getRealPath("/WEB-INF/");
			SAXReader reader = new SAXReader();
			FileInputStream fis = null;
			this.parseCompanyData(config, realPath, reader, fis);
			this.parseOrderCompanyData(config, realPath, reader, fis);
			System.out.println("hello");
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
			JSONObject jsonObj = new JSONObject();
			@SuppressWarnings("static-access")
			JSONObject json = jsonObj.fromObject(allOrderInfo);
			System.out.println(json.toString());
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
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		RequestDispatcher d = request.getRequestDispatcher("order/orderList.jsp");
		d.forward(request,response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int departId = Integer.parseInt(request.getParameter("selectId"));
		System.out.println("doPost :" + departId);
		DepartmentInfo department = this.data.getDepartmentById(departId);
		JSONObject jsonObj = new JSONObject();
		@SuppressWarnings("static-access")
		JSONObject json = jsonObj.fromObject(department);
		
		response.setHeader("Content-type", "text/html;charset=UTF-8");  
		//这句话的意思，是告诉servlet用UTF-8转码，而不是用默认的ISO8859  
		response.setCharacterEncoding("UTF-8");  
		PrintWriter out = response.getWriter();
		out.write(json.toString());
		out.flush();
		out.close();
		
	}

}
