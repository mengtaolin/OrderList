package com.zhizhang.servlet;

import java.io.File;
import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.zhizhang.CheckUtil;
import com.zhizhang.XMLUtil;
import com.zhizhang.dao.AllOrderCompanyInfo;
import com.zhizhang.dao.CompanyDataInfo;
import com.zhizhang.dao.DepartmentInfo;
import com.zhizhang.dao.OrderCompanyInfo;
import com.zhizhang.dao.OrderListInfo;
import com.zhizhang.dao.selectOrder.OrderPriceInfo;
import com.zhizhang.dao.selectOrder.SelectOrderInfo;

/**
 * Servlet implementation class DoOrderServlet
 */
@WebServlet(description = "处理下单的Servlet", urlPatterns = { "/DoOrderServlet" })
public class DoOrderServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    private XMLUtil xmlUtil = new XMLUtil();
    /**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

	/**
	 * 提交数据内容
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		if(CheckUtil.checkTime() == false){
			return;
		}
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
		this.createXmlFile(selectOrderInfo);
		
		request.setAttribute("selectOrderInfo", selectOrderInfo);
		request.getRequestDispatcher("order/success.jsp").forward(request, response);
		
	}
	
	private void createXmlFile(SelectOrderInfo selectOrderInfo){
		
		String timeType = CheckUtil.timeType(CheckUtil.TIME_TYPE1);
		String realPath = this.getServletContext().getRealPath(CheckUtil.getPropValue("orderDir"));
		String dateStr = CheckUtil.getCurDate();
		String fileName = dateStr + "\\" + timeType + "\\" + selectOrderInfo.getDepartmentId() + "_" + selectOrderInfo.getEmployeeId() + ".xml";
		String dirName = realPath + dateStr;
		File file = new File(dirName);
		if(file.exists() == false){
			file.mkdirs();
		}
		File subFile = new File(dirName + "\\" + timeType);
		if(subFile.exists() == false){
			subFile.mkdirs();
		}
		xmlUtil.create(selectOrderInfo, realPath + fileName);
		file = null;
		subFile = null;
	}

}
