<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page  import="java.util.ArrayList, 
                  jspBoard.dto.MDto,
                  jspBoard.service.*,
                  java.sql.Timestamp,
                  java.text.SimpleDateFormat,
                  java.text.NumberFormat" %>  
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>                  
<jsp:include page="inc/header.jsp" flush="true" />
<jsp:include page="inc/aside.jsp" />

<%
	
	HttpSession sess = request.getSession(true);
	
	String sname = request.getParameter("searchname");  //검색
	String svalue = request.getParameter("searchvalue");
	
    
	/* 페이징을 위한 변수 */
    int pg; //받아올 현재 페이지 번호
    int allCount; //1. 전체 개시글 수 
    int listCount = 10; //2. 한 페이지에 보일 목록 수
    int pageCount = 10; //3. 한 페이지에 보일 페이지 수  
    int limitPage; //4. 쿼리문으로 보낼 시작번호
     
    String cpg = "1";
    cpg = request.getParameter("cpg");
    pg = (cpg == null)?1:Integer.parseInt(cpg);  //3항 연산   
    limitPage = (pg-1)*listCount;  //(현재페이지-1)x목록수 

    
    
	DbWorks db = new DbWorks(limitPage, listCount, sname, svalue);

	/* 전체 개시글 의 수를 가져옴 */
    allCount = db.getAllSelect();
    
    //현재페이지, 전체글수, 페이지수, 글목록 수로 Paging 클래스 호출
    Paging myPage = new Paging(pg, allCount, pageCount, listCount);

	ArrayList<MDto> list = null;
	
	if(sname == null || sname.trim().isEmpty()){    
		list = db.getUserList();
	}else{
	    list = db.getUserSearchList();
	}
	
%>	
<%
	if(sess.getAttribute("role") == null){

%>
	<script>
		location.href = "/jspBoard/";
	</script>
<%		
	} else {
		
		String role = (sess.getAttribute("role")).toString();

		if(role.equals("admin")){
		
%>
	<section>
	<div class="listbox">
		<form >
		<table class="table table-hover">
			<colgroup>
            	<col width="15%">
            	<col width="15%">
                <col>
                <col width="15%">
                <col width="10%">
            </colgroup>
			<thead>
				<tr>
					<th class="text-center">아이디</th>
					<th class="text-center">이름</th>
					<th class="text-center">이메일</th>
					<th class="text-center">권한</th>
				</tr>
			</thead>
			<tbody>
			
			<%
                int num = allCount - limitPage; //게시글 번호
           	%>
			<%
            	for(int i=0; i<list.size(); i++){
                    MDto dto = list.get(i);
                    int id = dto.getId();
                    String userid = dto.getUserid();
                    String username = dto.getUsername();
                    String email = dto.getUseremail();
                    String userrole = dto.getRole();
                   
            %>    
				<tr>
						<td class="text-center"><input class="form-control" type="text" value=<%=userid %> /></td>
						<td class="text-center"><input class="form-control" type="text" value=<%=username %> /></td>
	                    <td class="text-center"><input class="form-control" type="text" value=<%=email %> /></td>
	                    <td class="text-center"><input class="form-control" type="text" value=<%=userrole %> /></td>
				</tr>
			<%
					num--;
                }
			%>	
					
			</tbody>
		</table>
			<button type="submit" class="btn btn-primary">수정</button>
       	</form>
	</div>
	</section>
<%

		}else{
		
%>

	<script>
		alert("권한이 없습니다.");
		location.href = "/jspBoard/";
	</script>

<%

		}
	}
%>
	
<%@ include file="inc/footer.jsp" %>     