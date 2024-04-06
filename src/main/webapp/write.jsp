<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="inc/header.jsp" %>
<script src="js/summernote-bs4.js"></script>
<script src="js/lang/summernote-ko-KR.min.js"></script>
<script src="js/write.js"></script>
<%@ include file="inc/aside.jsp" %>
         <section>
                <div class="write">
                    <h2 class="text-center mt-4 mb-5 pb-4">글쓰기</h2>
                    <form action="insert" name="writeform" id="writeform" class="writeform row" method="post">
                        <!-- 게스트일때 적용 -->
                        <div class="col-12 row">
                            <div class="col-6 row form-group">
                                <label class="form-label">이름</label>
                                <input type="text" name="writer" id="writer" class="form-control" placeholder="이름">
                            </div>
                            <div class="col-6 row form-group">
                                <label class="form-label">비밀번호</label>
                                <input type="password" name="pass" id="password" class="form-control" placeholder="비밀번호">
                            </div>
                        </div>
                        <div class="col-12 row form-group">
                            <label class="form-label">제목</label>
                            <input type="text" name="title" id="title" class="form-control col-10" placeholder="제목">
                        </div>
                        <div class="col-12">
                            <textarea name="content" id="content" class="form-control"></textarea>
                        </div>
                    
            
                        <!-- /게스트일때 적용 -->
                        <div class="col-12 text-center my-5">
                            <a href="index.html" class="btn btn-danger px-5 mx-2">취소</a>
                            <button class="btn btn-primary px-5 mx-2" type="submit">글쓰기</button>
                        </div>
                        <input type="hidden" name="mode" value="write" />
                        <input type="hidden" name="imnum" id="imnum" />
                       
                        <!-- 
                        <input type="hidden" name="refid" value="" />
                        <input type="hidden" name="depth" value="" />
                        <input type="hidden" name="renum" value="" />
                        -->
                     </form>
                </div>
                
            </section>
<%@ include file="inc/footer.jsp" %> 