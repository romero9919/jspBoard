<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="inc/header.jsp" flush="true" />
<%@ include file="inc/aside.jsp" %>
<script src="js/jquery.validate.min.js"></script>
<script src="js/validate.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
<script>
//다음주소 api
function dPostcode() {
   new daum.Postcode({
       oncomplete: function(data) {
           // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

           // 각 주소의 노출 규칙에 따라 주소를 조합한다.
           // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
           var addr = ''; // 주소 변수
           var extraAddr = ''; // 참고항목 변수

           //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
           if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
               addr = data.roadAddress;
           } else { // 사용자가 지번 주소를 선택했을 경우(J)
               addr = data.jibunAddress;
           }

           // 우편번호와 주소 정보를 해당 필드에 넣는다.
           document.getElementById('zipcode').value = data.zonecode;
           document.getElementById("addr1").value = addr;
           // 커서를 상세주소 필드로 이동한다.
           document.getElementById("addr2").focus();
       }
   }).open();
}
</script>
    <section>
       <!-- listbox -->
       <div class="listbox">
          <h1 class="text-center mb-5">회원가입</h1>
          <p class="text-danger">* 표시가 되어 있는 부분은 필수 항목입니다.</p>
       
                    <form name="registerform" action="insert" id="registerform" class="registerform" method="post">
                    <div class="row">
                        <div class="col-5 d-flex align-items-center mb-4">
                            <label for="username" class="text-right mr-3 col-4">이름</label>
                            <input type="text" name="username" id="username" 
                                   class="form-control col-8" placeholder="이름" />
                        </div>
                        <div class="col-5 d-flex align-items-center mb-4">
                            <label for="uid" class="text-right mr-3 col-4">아이디</label>
                            <input type="text" name="userid" id="userid" 
                                   class="form-control col-8" placeholder="아이디" />
                        </div>
                        <div class="col-2 mb-4"></div>


                        <div class="col-5 d-flex align-items-center mb-4">
                            <label for="upass" class="text-right mr-3 col-4">비밀번호</label>
                            <input type="password" name="userpass" id="userpass"
                                   class="form-control col-8" placeholder="비밀번호">
                        </div>
                        <div class="col-5 d-flex align-items-center mb-4">
                            <label for="repassword" class="text-right mr-3 col-4">비밀번호 확인</label>
                            <input type="password" name="reuserpass" id="reuserpass"
                                   class="form-control col-8" placeholder="비밀번호확인">
                        </div>
                        <div class="col-2 mb-4"></div>


                        <div class="col-6 d-flex align-items-center mb-4">
                            <label for="email" class="text-right mr-3 col-4">이메일</label>
                            <input type="text" name="useremail" id="useremail"
                                   class="form-control col-8" placeholder="이메일">
                        </div>
                        <div class="col-6 mb-4"></div>

                        
                        <div class="col-8 d-flex align-items-center mb-4">
                          <label for="usertel" class="text-right mr-3 col-3">전화번호</label>
                          <input type="text" name="usertel" id="usertel"
                                 class="form-control col-8 mr-2" placeholder="010" 
                           oninput="autoHyphen(this)" maxlength="13"> 
                        </div>
                        <div class="col-4"></div>


                        <div class="col-5 d-flex">
                          <label for="zip" class="text-right mr-2 col-5">우편번호</label>
                          <input type="number" name="zipcode" id="zipcode"
                                 class="form-control col-7 mx-2 mt-1" placeholder="우편번호" readonly>
                        </div>
                        <div class="col-3">
                            <button type="button" id="zip" class="btn btn-secondary mt-1">우편번호찾기</button>
                        </div>
                        <div class="col-4"></div>

                        <div class="col-12 d-flex">
                            <label for="zip" class="text-right mr-2 col-5">주소</label>
                            <input type="text" name="addr1" id="addr1"
                                 class="form-control col-7 mx-2 mt-1" placeholder="주소" readonly>
                        </div>
                        <div class="col-12 d-flex mb-4">
                          <label for="zip" class="text-right mr-2 col-5 bg-white"></label>
                          <input type="text" name="addr2" id="addr2"
                               class="form-control col-7 mx-2 mt-1" placeholder="상세주소">
                        </div>
                        <div class="col-12 d-flex mb-4">
                          <label for="file" class="text-right mr-2 col-5">사진</label>
                          <input type="file" name="file" id="file"
                               class="form-control col-7 mx-2 mt-1" placeholder="사진업로드">
                        </div>
                        <div class="col-12 text-center">
                            <button class="btn btn-danger px-5 mx-2" type="reset">취소</button>
                            <button class="btn btn-primary px-5 mx-2" type="submit">전송</button>
                        </div>
         
                        <input type="hidden" name="mode" value="join" />
                    </div>
                </form>
       
       </div>
    </section>   
    
<%@ include file="inc/footer.jsp" %>  