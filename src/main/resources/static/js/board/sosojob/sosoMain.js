$(document).ready(function() {
    var contextPath = $('#contextPathHolder').attr('data-contextPath') ? $('#contextPathHolder').attr('data-contextPath') : '';
 //카테고리 페이지 이동
    function getCateList(category, page){
         /*[+
         var cateurl = contextPath + [[@{/sosojob/sosoList}]] +
             "?sosoCategory=" +category + "&page=" + page;
         +]*/

         location.href=cateurl;
    }

}); //end
