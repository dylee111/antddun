$(document).ready(function() {
    var fontList = ['맑은 고딕','굴림','돋움','바탕','궁서', 'Arial','Courier New','Verdana'];

    $('#summernote').summernote({
    lang : 'ko-KR',
    height : 200,
    fontNames: fontList,
    fontNamesIgnoreCheck: fontList,
    focus : true,
    minHeight : null,
    maxHeight : null,
    toolbar : toolbar,

    toolbar: [
         // 글자 설정
         ['font', ['fontname','fontsize']],
         // 글자색
         ['color', ['color']],
         // 글머리 기호, 번호매기기, 문단정렬
         ['para', ['ul', 'ol', 'paragraph']],
         // 굵기, 기울임꼴, 밑줄, 취소 선, 위첨자전환, 첨자전환, 서식지우기
         ['style', ['bold','italic','underline','strikethrough','superscript','subscript']],
         // 표만들기
         ['table', ['table']],
         // 그림, 동영상, 링크, 수평선
         ['insert',['picture','video','link','hr']]
    ],
    fontNames: ['맑은 고딕', 'Arial','Courier New','Verdana'],
    fontSizes: ['12','14','16','18','20','22','24','28','30','36','50','72'],
    styleTags: ['span'],
    callbacks : {
       onImageUpload : function(files, editor, welEditable) {
             for (var i = 0; i < files.length; i++) {
                 sendFile(files[i], this);
             }
       }
    }
  });
});