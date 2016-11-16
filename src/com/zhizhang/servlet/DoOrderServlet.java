package com.zhizhang.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhizhang.dao.AllOrderCompanyInfo;
import com.zhizhang.dao.CompanyDataInfo;
import com.zhizhang.dao.DepartmentInfo;
import com.zhizhang.dao.OrderCompanyInfo;
import com.zhizhang.dao.OrderListInfo;
import com.zhizhang.dao.OrderPriceInfo;
import com.zhizhang.dao.SelectOrderInfo;

/**
 * Servlet implementation class DoOrderServlet
 */
@WebServlet(description = "处理下单的Servlet", urlPatterns = { "/DoOrderServlet" })
public class DoOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public DoOrderServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		this.doPost(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Map<String,String[]> map = request.getParameterMap();
		int count = 0;
		String[] selectList = new String[map.size() - 2];
		CompanyDataInfo compInfo = (CompanyDataInfo)this.getServletContext().getAttribute("data");
		SelectOrderInfo selectOrderInfo = new SelectOrderInfo();
		DepartmentInfo departmentInfo = null;
		for(String key : map.keySet()){
			String[] value = map.get(key);
			if(count == 0)
			{
				selectOrderInfo.setDepartmentId(Integer.parseInt(value[0]));
				departmentInfo = compInfo.getDepartmentById(selectOrderInfo.getDepartmentId());
				selectOrderInfo.setDepartment(departmentInfo.getName());
			}else if(count == 1){
				selectOrderInfo.setEmployeeId(Integer.parseInt(value[0]));
				selectOrderInfo.setEmployee(departmentInfo.getEmployeeInfoById(selectOrderInfo.getEmployeeId()).getName());
			}else{
				selectList[count - 2] = key;
			}
			count ++;
		}
		AllOrderCompanyInfo info = (AllOrderCompanyInfo)this.getServletContext().getAttribute("allOrderInfo");
		int len = selectList.length;
		OrderPriceInfo[] orderPriceInfoList = new OrderPriceInfo[len];
		for(int i = 0;i < len; i++){
			String[] list = selectList[i].split("_");
			OrderCompanyInfo orderCompInfo = info.getOrderInfoById(Integer.parseInt(list[0]));
			OrderListInfo orderListInfo = orderCompInfo.getListInfoById(Integer.parseInt(list[1]));
			OrderPriceInfo orderPriceInfo = new OrderPriceInfo();
			orderPriceInfo.setOrderId(orderListInfo.getId());
			orderPriceInfo.setOrderName(orderListInfo.getName());
			orderPriceInfo.setPrice(Float.parseFloat(list[2]));
			orderPriceInfo.setCompId(orderCompInfo.getId());
			orderPriceInfo.setCompName(orderCompInfo.getName());
			orderPriceInfoList[i] = orderPriceInfo;
		}
		selectOrderInfo.setOrderPriceInfo(orderPriceInfoList);
		request.setAttribute("selectOrderInfo", selectOrderInfo);
		request.getRequestDispatcher("order/success.jsp").forward(request, response);
		
	}

}
