<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jspBoard.service.*, 
                jspBoard.dto.BDto,
                java.text.SimpleDateFormat" %>  
                  
<%@ include file="inc/header.jsp" %>
<%@ include file="inc/aside.jsp" %>
<%   
   HttpSession sess2 = request.getSession(true);
   Cookie[] cooks2 = request.getCookies();

   String userid = (String) sess2.getAttribute("userid");
   String id = request.getParameter("id"); 
   String cpg = request.getParameter("cpg");
   if(cpg == null) cpg = "1";

   SimpleDateFormat sdf = new SimpleDateFormat("yyyy년 MM월 dd일 hh시 mm분 ss초");
  
   DbWorks db = new DbWorks();
   db.setId(id);
   BDto rs = db.getSelectOne();
   
   Boolean addCook = true;
   
   if((cooks2 != null) && (cooks2.length>0)){
	  for(Cookie cook:cooks2){
		 if(cook.getName().equals("cid") && cook.getValue().equals(id)){
			 addCook = false;
		 }
	  }
   }
   if(addCook){ 
      int addHit = rs.getHit() + 1;
      db.getUpdate(addHit);
      //쿠키생성
      Cookie cookie = new Cookie("cid", id);
      cookie.setMaxAge(600);
      response.addCookie(cookie);
   }   
   
   String wdate = sdf.format(rs.getWdate());
%>
    <section>
       <!-- listbox -->
       <div class="listbox">
          <h3 class="mt-5"><i class="ri-arrow-right-double-line"></i> <%=rs.getTitle() %></h3> 
          <div class="mt-2 mb-5 pt-2 border-top text-right">
             <span class="mr-4"><label class="font-italic">hit:</label> <%=rs.getHit() %></span>
             <span class="mr-4 font-weight-bold"><%=rs.getWriter() %></span>
             <span class="mr-2"><%=wdate%> </span>
          </div>
          <!-- 
          <div class="mt-2 pt-2 border-top file-box">
             <span>
                    <label class="font-italic">file :</label>
                    <a href="#">asdf.gif</a>  <a href="#">afdfd.asdf</a>
             </span>
          </div>
          -->
          <div class="content-box mt-3">
             <%=rs.getContent() %>
          </div>
          
          <div class="my-5 pt-5 text-right">
             <a href="./?cpg=<%=cpg %>" class="btn btn-primary mr-3">목록</a>
             <a href="rewrite.jsp?id=<%=id %>&refid=<%=rs.getRefid() %>&depth=<%=rs.getDepth() %>&renum=<%=rs.getRenum() %>&cpg=<%=cpg %>" class="btn btn-primary">답글쓰기</a>
             <a href="pass.jsp?id=<%=id %>&mode=edit" class="btn btn-primary">수정</a>
             <a href="pass.jsp?id=<%=id %>&mode=del" class="btn btn-danger">삭제</a>                      
          </div>
          
       </div>
       
          
       <link rel="stylesheet" href="css/summernote-bs4.css">
       <script src="js/summernote-bs4.js"></script>
       <script>
            $(function(){
              $("#comment").summernote({
                  width:'85%',
                  height:'100px',
                  toolbar: [
                    ['style', ['bold', 'italic', 'underline', 'clear']],
                    ['font', ['strikethrouth','superscript', 'subscript']],
                    ['color', ['color']],
                    ['para', ['ul', 'ol', 'paragraph']]
                  ] 
               });
            }); 

       </script>
       
       <ul class="list-group list-group-flush comments">
          <%@ include file="comments_list.jsp" %>
          <%@ include file="comments_write.jsp" %>          
       </ul>
    </section>        
    <script>
      $(function(){
    	  $(".cdel").click(function(e){
    		  e.preventDefault();
    		  const $userid = $(this).data("userid");
    		  if($userid == "<%=userid%>" || "<%=userid%>" == "admin"){
    			  if(confirm("정말로 삭제하시겠습니까?")){
    			     const $link = $(this).attr("href");
    			     location.href=$link+"&userid="+$userid;
    			  }   
    		  }else{
    			  alert("삭제할 권한이 없습니다.");
    			  return;
    		  }
    	  });
    	  $(".cedit").click(function(e){
    		 e.preventDefault();
    		 let $userid, $num, username, comment, wdate, id, $form;

    		 $userid = $(this).data("userid");
    		 $num = $(".cedit").index(this);
    		// alert($num);
             username = $('.cusername').eq($num).text();
             comment = $('.ccomment').eq($num).html();
             id = $(".cid").eq($num).val();
             
             $form = '<form name="commentForm" id="commentform'+$num+'"'+ 
                         'class="d-flex w-100 bg-white mt-3" method="post" action="./updatecomment">'+
                         '<div class="form-box">'+
                         '<div class="mb-2">'+
                         '<label>이름 : </label>'+
                         '<input type="text" name="username"'+
                         'value="'+username+'">'+
                         '</div>'+
                         '<div class="d-flex">'+
                         '<textarea name="comment" class="c_comment">'+comment+'</textarea>'+
                         '<button type="submit" class="comment-btn">댓글수정</button>'+
                         '</div></div>'+    
                         '<input type="hidden" name="id" value="'+id+'"></form>';
             
    		 if($userid == "<%=userid%>" || "<%=userid%>" == "admin"){
    			 let contDiv = $("#commentform"+$num);
    			 //console.dir(contDiv);
    			 if(contDiv.length > 0){
    				 contDiv.remove();
    			 }else{
    			    //$('.editform').eq($num).html($form);
    			    $(".editform").eq($num).append($form);
    			    $('.c_comment').summernote({
                       width:'85%',
                       height:'100px',
                       toolbar: [
                         ['style', ['bold', 'italic', 'underline', 'clear']],
                         ['font', ['strikethrouth','superscript', 'subscript']],
                         ['color', ['color']],
                         ['para', ['ul', 'ol', 'paragraph']]
                       ] 
    			   });
    			}   
    		 }else{
                 alert("수정할 권한이 없습니다.");
                 return;
             }
    		 
    	  });
      });
    </script>
<%@ include file="inc/footer.jsp" %>  