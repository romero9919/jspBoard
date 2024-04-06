<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
   //HttpSession sess2 = request.getSession();
%>    
          
               <li class="bg-light list-group-item flex-column p-5 mb-5 align-items-start position-relative">
                  <form name="commentForm" id="commentForm" class="d-flex" method="post" action="./insertcomment">
                     <div class="form-box">
                         <div class="mb-2">
                              <label>이름 : </label>
                              <input type="text" name="username" value="<%=sess2.getAttribute("username")%>">
                         </div>
                         <div class="d-flex">
                            <textarea name="comment" id="comment"></textarea>
                            <button type="submit" class="comment-btn">댓글쓰기</button>
                         </div> 
                     </div>    
                     <input type="hidden" name="jboard_id" value="<%=rs.getId() %>">
                     <input type="hidden" name="chit" value="<%=rs.getChit() %>">
                  </form>
               </li> 