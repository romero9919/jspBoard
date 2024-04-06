<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.sql.*, jspBoard.dao.*, jspBoard.dto.BDto,java.text.SimpleDateFormat" %>    
<%@ include file="inc/header.jsp" %>
<%@ include file="inc/aside.jsp" %>
<% 
   String id =request.getParameter("id");
   String mode =request.getParameter("mode");
   HttpSession sess = request.getSession();
   if("ADMIN".equals(sess.getAttribute("role"))){
	  if("edit".equals(mode)){ 
%>
        <form id="gopage" action="edit.jsp" method="post">
           <input type="hidden" name="id" value="<%=id %>">
        </form>
        <script>
           this.document.getElementById("gopage").submit();
        </script>
<%  
	  }else if("del".equals(mode)){
%>		  
	     <form id="gopage" action="del" method="post">
	         <input type="hidden" name="id" value="<%=id %>">
	     </form>
	     <script>
	         let y = confirm("정말로 삭제하시겠습니까?");
	         if(y){
	            this.document.getElementById("gopage").submit();
	         }else{
	        	this.history.go(-1);
	         }   
	     </script>
<%	     
	  }
   }
   String modeText = "삭제";
   if(mode.equals("edit")){
	  modeText = "수정";   
   }
%>
    <section>
       <!-- listbox -->
       <div class="listbox">
               <h2 class="text-center"><%=modeText %></h2>
               <p class="text-center"><%=modeText %>하려면 비밀번호를 입력하세요.</p>
           <div class="pass-box mt-4">
              <form name="passform" id="passform" action="passok" method="post">
                 <input type="password" id="cpass" name="cpass" class="form-control" placeholder="비밀번호">
                 <div class="btn-box text-center mt-3">
                    <a href="<%=request.getHeader("referer") %>" class="btn btn-warning px-4 mr-2">취소</a>
                    <button type="submit" class="btn btn-success px-4 ml-2"><%=modeText %></button> 
                 </div>
                 <input type="hidden" name="mode" value="<%=mode %>">
                 <input type="hidden" name="id" value="<%=id %>">
              </form>
           </div>
          
       </div>
    </section>        

<%@ include file="inc/footer.jsp" %>  