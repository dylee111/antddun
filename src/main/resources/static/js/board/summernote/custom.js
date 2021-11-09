$(document).ready(function() {
  $('#summernote').summernote({
    lang : 'ko-KR',
    height : 300,
    focus : true,
    minHeight : null,
    maxHeight : null,
    toolbar : toolbar,
    placeholder: '&#8251;개미는 뚠뚠의 취지와 관련 없는 인신공격 및 허위사실 유포 글은 통보없이 즉시 삭제 처리 될 수 있습니다.',

    toolbar: [
         // 스타일 설정
         ['format', ['style']],
         // 글자 설정
         ['font', ['fontname','fontsize']],
         // 굵기, 기울임꼴, 밑줄, 취소 선, 위첨자전환, 첨자전환, 서식지우기
         ['style', ['bold','italic','underline','strikethrough','superscript','subscript','clear']],
         // 글자색
         ['color', ['color']],
         // 표만들기
         ['table', ['table']],
         // 글머리 기호, 번호매기기, 문단정렬
         ['para', ['ul', 'ol', 'paragraph']],
         // 줄간격
         ['height', ['height']],
         // 그림, 동영상, 링크, 수평선
         ['insert',['picture','video','link','hr']],
         // 코드보기, 확대해서보기, 도움말
         ['view', ['codeview','fullscreen', 'help']]
    ],
    fontNames: ['맑은 고딕','궁서','굴림체','굴림','돋움체','바탕체','Arial', 'Arial Black', 'Comic Sans MS', 'Courier New'],
    styleTags: ['p','h1','h2','h3','h4','h5','h6']
  });
});