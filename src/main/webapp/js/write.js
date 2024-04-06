$(function(){
   $("#content").summernote({
       placeholder: "내용",
       tabsize: 2,
       height:300,
       lang: 'ko-KR',
       callbacks: {
         onImageUpload: function(files){
            sendData(files[0]);
         },
         onMediaDelete: function(files){
            delData(files[0]);
         }   
       }
   });
   
   function sendData(file) {
      const imnum = $("#imnum").val();
      const data = new FormData();
      data.append("file", file);
      data.append("imnum", imnum);
      $.ajax({
         url: "upload",
         type: "post",
         data: data,
         contentType: false,
         processData: false,
         success: function( data ) {
            console.log(data);
            $("#content").summernote("insertImage", data.url);
            $("#imnum").val(data.imnum);
         },
         error: function(jqXHR, textStatus, errorThrown){
            console.error(textStatus + ", " + errorThrown);
         }
      
      });
   }
   
   
   function delData(file) {
      const fileUrl = file.src; //delete 될 image 의 url
      $.ajax({
         url: 'delete',
         type: 'post',
         data: { "fileUrl" : fileUrl },
         success: function(data) {
           if(data){
             console.log("파일삭제");
           }else{
             console.log("파일삭제 실패");
           } 
         },
         error: function(jqXHR, textStatus, errorThrown){
           console.log("삭제실패");   
         }
      });
   }
   
});