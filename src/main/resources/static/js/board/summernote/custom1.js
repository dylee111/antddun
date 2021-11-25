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
    dialogsInBody: true,
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
                 sendImg(files[i], this);
             }
       }
    }
  });
});

//확장자 및 사이즈 확인
function checkExtension(fileName){
    var input = fileName.name;
    if (input.match(/(.png|.jpg|.jpeg|.gif|.bmp|.PNG|.JPG|.JPEG|.GIF|.BMP)$/)){
        return true;
    }
    else alert("이미지 파일만 첨부할 수 있어요.");
}

//이미지 업로드
function sendImg(file, el) {
			var form_data = new FormData();
			form_data.append('file', file);
			$.ajax({
				data : form_data,
				type : 'POST',
				url : '/antddun/image',
				enctype : 'multipart/form-data',
				cache : false,
				contentType: false,
				processData : false,
				success : function(url) {
					$(el).summernote('insertImage', url, function($image) {
					    $image.css('width', "50%");
                    });
               },
                error: function(data) {
                        alert("1MB이하 파일만 첨부할 수 있어요.");
                     }
			});
}