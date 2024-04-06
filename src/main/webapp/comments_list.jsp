<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspBoard.service.*, jspBoard.dto.*,java.sql.*, java.text.*, java.util.ArrayList"%> 
<%
   ArrayList<CDto> lists = db.getCommentList(id);
   for(CDto list : lists) {
%>   
 
    <li class="list-group-item p-5 align-items-start">
        <div class="d-flex w-100 justify-content-between">
           <div class="mb-1 col-11 row editform">
                <div class="col-10">
                  [<span class="cusername"><%=list.getUsername() %></span>] 
                  <div class="ccomment"><%=list.getComment() %></div>  
                </div>
                <div class="col-2 text-right cwdate">
                  <%=list.getWdate() %>
                  <input type="hidden" class="cid" value="<%=list.getId() %>">
                </div>
          </div>
          <div class="btn-box col-1 text-right">
            <i class="ri-more-2-fill"></i>
            <div class="edel">
                <a href="#" 
                   class="cedit" 
                   data-userid="<%=list.getUserid()%>">수정</a>
                <a href="cdel?id=<%=list.getId()%>&jboard_id=<%=id %>&chit=<%=rs.getChit() %>" 
                   class="cdel" 
                   data-userid="<%=list.getUserid()%>">삭제</a>
            </div>
          </div>
       </div>
     </li>

 <% } %>   
 