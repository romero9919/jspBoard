$(document).ready(function(){
    //경고창을 팝업으로
    $.validator.setDefaults({
       onkeyup: false,
       onclick: false,
       onfocusout: false,
       showErrors: function(errorMap, errorList){
          if(this.numberOfInvalids()){  //만약 에러가 있으면
            alert(errorList[0].message);  //alert으로 출력
          }
       }
    });
             
    //영문과 숫자만 사용가능
    $.validator.addMethod("alphanumeric" , function(value, element) {
       return this.option(element) || /^[a-zA-Z0-9]+$/.test(value);
    });          
             
    $("#registerform").validate({
        rules: {
          username: {
             required: true
          },
          userid: {
             required: true,
             rangelength: [3, 10],
             remote: { type: "post", url: "inc/check.jsp?mode=id" }
          },
          userpass: {
             required: true,
          },
          reuserpass: {
             required: true,
             equalTo: "#userpass"
          },
          useremail: {
             required: true,
             remote: { type: "post", url: "inc/check.jsp?mode=email" }
          },
          tel1: {
             required: true,
             minlength: 2,
          },
          tel2: {
             required: true,
             minlength: 3,
          },
          tel3: {
             required: true,
             minlength: 3,
          },
        },
        messages : {
          username: {
             required: "이름을 입력 하세요."
          },
          userid: {
             required: "아이디를 입력하세요.",
             rangelength: "아이디는 {0}자에서 {1}자까지 사용이 가능합니다.",
             remote: "이미 등록되어 있는 아이디 입니다."
          },
          userpass: {
             required: "비밀번호를 입력하세요."
          },
          reuserpass: {
             required: "비밀번호를 확인하세요.",
             equalTo: "비밀번호가 같지 않습니다. 다시 확인하세요."
          },
          useremail:{
             required: "이메일을 입력하세요.",
             remote: "이미 등록되어 있는 이메일입니다."
          },
          tel1:{
             required: "전화번호를 입력하세요.",
             minlength: "전화번호가 너무 짧아요"
          },
          tel2:{
             required: "전화번호를 입력하세요.",
             minlength: "전화번호가 너무 짧아요"
          },
          tel3:{
             required: "전화번호를 입력하세요.",
             minlength: "전화번호가 너무 짧아요"
          },
          //폼 전송 핸들러
          submitHandler: function(form){
             form.submit();
          }
       }
    });
  
});

const autoHyphen = (target) => {
   target.value = target.value.replace(/[^0-9]/g, "")
                  .replace(/^(\d{0,3})(\d{0,4})(\d{0,4})/g, "$1-$2-$3")
                  .replace(/(-{1,2})$/g,"");
}

