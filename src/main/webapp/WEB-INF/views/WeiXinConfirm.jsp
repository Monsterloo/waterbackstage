<%@ page contentType="text/html;charset=UTF-8" import="com.hhh.fund.waterwx.webmodel.*"%>


${UKey}<br/>
<%-- ${"userBean"+UKey} --%>

<%
	String ukey = request.getParameter("UKey");	
	
	SwjUserBean userbean=(SwjUserBean)application.getAttribute("userBean"+ukey);
	if(userbean.getId()==null){
%>
	没找到用户
<%}else { %>
	getId=<%=userbean.getId() %><br/>
	getName=<%=userbean.getName() %><br/>
	getArea=<%=userbean.getArea() %><br/>
<%}