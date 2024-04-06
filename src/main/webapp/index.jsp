<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8" isELIgnored="true" %>
<%@ page  import="java.util.ArrayList, 
                  jspBoard.dto.BDto,
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
    ServletContext cont = getServletContext();
 
    TrashFile trf = new TrashFile(cont);

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
    
    /* 페이징 변수 끝 */
    
    DbWorks db = new DbWorks(limitPage, listCount, sname, svalue);

    /* 전체 개시글 의 수를 가져옴 */
    allCount = db.getAllSelect();
    
    //현재페이지, 전체글수, 페이지수, 글목록 수로 Paging 클래스 호출
    Paging myPage = new Paging(pg, allCount, pageCount, listCount);

    ArrayList<BDto> list = null;
    if(sname == null || sname.trim().isEmpty()){    
       list = db.getList();
    }else{
       list = db.getSearchList();
    }
    
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy.MM.dd");
    NumberFormat formatter = NumberFormat.getInstance();   
    
 %>
    <section>
            <!-- listbox -->
            <div class="listbox">
                <h1 class="text-center mb-5">게시판</h1>
                <div class="d-flex justify-content-between py-4">
                    <div>
                        <label>총 게시글</label> :<%=formatter.format(allCount) %>개 / <%=formatter.format(myPage.getTotalPages()) %>page
                    </div>
                    <div>
                        <a href="/jspBoard" class="btn btn-primary">목록</a>
                        <%--
                        <% if(sess.getAttribute("mid") != null){ %>
                          <a href="write.jsp" class="btn btn-primary">글쓰기</a> 
                        <% } %>
                        --%>
                        <c:if test="${not empty sess.mid }">
                           <a href="write.jsp" class="btn btn-primary">글쓰기</a>
                        </c:if>                       
                    </div>
                </div>
                <table class="table table-hover">
                    <colgroup>
                       <col width="8%">
                       <col>
                       <col width="15%">
                       <col width="10%">
                       <col width="15%">
                    </colgroup>
                    <thead>
                        <tr>
                            <th>번호</th>
                            <th>제목</th>
                            <th>글쓴이</th>
                            <th>조회수</th>
                            <th>날짜</th>
                        </tr>
                    </thead>
                    <tbody>
                       <!-- loop --> 
                   <%
                      int num = allCount - limitPage; //게시글 번호
                   %>
                   
                   <%
                      for(int i=0; i<list.size(); i++){
                    	  BDto dto = list.get(i);
                    	  int id = dto.getId();
                    	  int depth = dto.getDepth();
                    	  String title = dto.getTitle();
                    	  String writer = dto.getWriter();
                    	  int hit = dto.getHit();
                    	  int chit = dto.getChit();
                    	  Timestamp dates = dto.getWdate();
                    	  String wdate = sdf.format(dates);
                    	  String styleDepth = "";
                    	  if(depth > 0){
                    		  String padding = (depth*10)+"px";
                    		  String reicon = "<i class=\"ri-corner-down-right-line\"></i>";
                    	      styleDepth = "<span style='display:inline-block;width:"+padding+"'></span>"+reicon+" ";
                    	  }
                    	  String commentHit = "";
                    	  if(chit > 0) {
                    		  commentHit = " ("+chit+")";
                    	  }
                   %>    
                       <tr>
                           <td class="text-center"><%=num %></td>
                           <% if(sess.getAttribute("mid") != null){ %>
                           <td><a href="contents.jsp?id=<%=id%>&cpg=<%=pg%>">
                               <%=styleDepth%><%=title %><%=commentHit %>
                            </a><span></span>
                            <!-- 
                               <i class="ri-file-image-fill"></i>
                               <i class="ri-file-hwp-fill"></i>
                               <i class="ri-file-music-fill"></i>
                            -->   
                            </td>
                           <% }else{ %>
                            <td>
                               <a href="javascript:void(0)"><%=styleDepth%><%=title %><%=commentHit %>
                               </a><span></span>
                            <!-- 
                               <i class="ri-file-image-fill"></i>
                               <i class="ri-file-hwp-fill"></i>
                               <i class="ri-file-music-fill"></i>
                            -->   
                            </td>
                           <% } %> 
                           <td class="text-center"><%=writer %></td>
                           <td class="text-center"><%=hit %></td>
                           <td class="text-center"><%=wdate %></td>
                       </tr>
                  <% 
                        num--;
                      } 
                  %>     
                  
                       <!-- /loop -->
                    </tbody>
                </table>
                <div class="d-flex justify-content-between py-4">
                    <div>
                    </div>
                    <%
                    //검색일때 처리
                    String query = "";
                    if(sname != null) {
                      query = "&searchname="+sname+"&searchvalue="+svalue; 
                    }
                    %>
                    <ul class="paging">
                        <li>
                            <a href="?cpg=1<%=query%>"><i class="ri-arrow-left-double-line"></i></a>
                        </li>
                        <li>
                        <%                        
                           if(myPage.getStartPage() - 1 == 0){
                        %>
                            <a href="?cpg=<%=myPage.getStartPage()%><%=query%>"><i class="ri-arrow-left-s-line"></i></a>
                        <% }else{ %>
                            <a href="?cpg=<%=myPage.getStartPage()-1%><%=query%>"><i class="ri-arrow-left-s-line"></i></a>
                        <% } %>
                      
                        </li>
                        <%
                        //시작페이지 6
                        for(int i = myPage.getStartPage(); i <= myPage.getEndPage(); i++){
                           if(pg == i) {
                        	   out.println("<li><a href=\"?cpg="+i+query+"\" class=\"active\">"+i+"</a></li>");
                           }else{
                        	   out.println("<li><a href=\"?cpg="+i+query+"\">"+i+"</a></li>");
                           }
                        }
                        //끝페이지 10
                        %>
                        
                        <li>
                          <%
                           if(myPage.getEndPage() + 1 > myPage.getTotalPages()){
                          %>
                            <a href="?cpg=<%=myPage.getEndPage()%><%=query%>"><i class="ri-arrow-right-s-line"></i></a>
                         <%
                           }else{ 
                         %>   
                            <a href="?cpg=<%=myPage.getEndPage()+1%><%=query%>"><i class="ri-arrow-right-s-line"></i></a>
                         <%
                           }
                         %>
                        </li>
                        <li>
                            <a href="?cpg=<%=myPage.getTotalPages()%><%=query%>"><i class="ri-arrow-right-double-line"></i></a>
                        </li>
                    </ul>
                    <div>
           
                        <a href="/jspBoard" class="btn btn-primary">목록</a>
                        <% if(sess.getAttribute("mid") != null){ %>
                          <a href="write.jsp" class="btn btn-primary">글쓰기</a> 
                        <% } %>  
                                         
                    </div>
               </div>
               <form name="searchform" id="searchform" class="searchform" method="get">
                   <div class="input-group my-3">
                        <div class="input-group-prepend">
                             <button type="button" 
                                    class="btn btn-outline-secondary dropdown-toggle" 
                                    data-toggle="dropdown"
                                    value="title">
                                제목검색
                              </button>
                              <input type="hidden" name="searchname" id="searchname" value="title">
                              <div class="dropdown-menu">
                                <a class="dropdown-item" href="writer">이름검색</a>
                                <a class="dropdown-item" href="title">제목검색</a>
                                <a class="dropdown-item" href="content">내용검색</a>
                            </div>
                        </div>
                       <input type="search" name="searchvalue" class="form-control" placeholder="검색">
                       <div class="input-group-append">
                          <button type="submit" class="btn btn-primary"><i class="ri-search-line"></i></button>
                       </div>
                   </div>
               </form>
            </div>
            <!-- /listbox-->
         </section>
    <%@ include file="inc/footer.jsp" %>     