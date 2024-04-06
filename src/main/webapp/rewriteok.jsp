<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page errorPage="error.jsp" %>
<%@ page import="java.sql.*, jspBoard.dao.JBoardDao" %> 
<% request.setCharacterEncoding("utf-8"); %>
<jsp:useBean id="db" class="jspBoard.dao.DBConnect" scope="page"/>
<jsp:useBean id="bDto" class="jspBoard.dto.BDto" scope="page" />
<jsp:setProperty name="bDto" property="*" /> 
<%
   String cpg = request.getParameter("cpg");
   Connection conn = db.getConnection(); 
   JBoardDao dao = new JBoardDao(conn);
   if(bDto.getDepth() > 0){
      bDto.setRefid(bDto.getRefid());
   }else{
	  bDto.setRefid(bDto.getId());
   }
   bDto.setDepth(bDto.getDepth()+1);
   bDto.setRenum(bDto.getRenum());
   int rs = dao.insertDB(bDto);

   //response.sendRedirect("index.jsp");
%>
  
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<script>
  alert("글을 등록했습니다.");
  location.href="./index.jsp?cpg=<%=cpg%>";
</script>

</head>
<body>
<h1>데이터가 성공적으로 등록 되었습니다.</h1>
</body>
</html>