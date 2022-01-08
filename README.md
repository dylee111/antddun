# 개미는 뚠뚠


> 일상이 무기력하고 지루한 직장인들을 대상으로 건강한 직장문화 형성을 위한 커뮤니티 플랫폼 서비스 

<br>

**프로토타입** : [카카오 오븐 이동](https://ovenapp.io/view/NKAy5eNMdLwLmpZ5BZ8s01KNhjtVcovU/)

## UML 다이어그램
<details>
<summary>Use Case Diagram</summary>
<div markdown="1">       
<img width="900" alt="image" src="https://user-images.githubusercontent.com/86641773/148582686-7a7b830d-a50b-4a3c-9b99-747c0c015283.png">
</div>
</details>

<details>
<summary>Class Diagram</summary>
<div markdown="1">       
</div>
</details>


<br>

## Technologies used (libraries & versions)
Programming Language : Java(11), JS, HTML, CSS3

Framework / Library : Spring Boot, JQuery, Bootstrap

Server : H2 Database(Oracle)

<br>

## 맡은 역할 
### 소셜로그인
- 총 네이버, 페이스북, 구글로 로그인시 강제 회원가입을 통해 로그인이 가능합니다.
- 또한 소셜로 로그인 할 시 SOCIAL이라는 권한을 따로 추가하여 첫 소셜 로그인 직후 successHandler를 바로 회원가입에 필요한 추가정보 기입란으로 이동하도록 했습니다. 네이버와 달리 전화번호를 받을 수 없는 페이스북과 구글로 로그인했을 때에는 thymeleaf의 th:if문을 이용하여 전화번호 기입란의 여부를 정했습니다. 

### 정보 게시판(QnA)
> 직무가 곧 카테고리 이며 직무와 관련된 질문을 할 수 있습니다. 또한 질 높은 답변을 위해 가상화폐 뚠을 걸 수 있으며 채택하여 적절한 답을 준 답변자에게 뚠을 보낼 수 있습니다. 또한 채택이 되고 나면 제3자는 답변의 내용을 볼 수 없습니다. 

**<게시글>**
- 게시글을 보려면 로그인이 필요합니다. 컨트롤러에서 이를 판별하고 로그인 상태가 아닐 시 로그인 화면으로 이동합니다.
- 게시판 CRUD가 가능하고, 게시물 등록 시 뚠(가상화폐)을 걸 수 있습니다. 뚠은 게시물 테이블에 pending 되어 있습니다.
- 댓글이 하나라도 달렸을 시 게시물은 삭제이 불가능합니다. 수정은 할 수 있으니 뚠은 수정할 수 없습니다.
- 뚠 없이 게시물을 올리면 “뚠 없음”이 표시됩니다.
- 답변자를 채택하여 뚠을 보낼 수 있습니다. 
- 채택된 글의 제목과 뚠이 개인 뚠 정보 내역에 표시됩니다.
- 채택되고 난 후 게시글은 채택완료로 표시되고 채택을 더 이상 할 수 없습니다.
- 게시글 작성자, 채택된 답변자이외에는 채택된 답변을 볼 수 없습니다. 

**<리스트>**
- 게시판 리스트는 카테고리 별로 볼 수 있습니다. 
- 좋아요, 댓글, 조회수가 리스트와 조회 페이지에 표시됩니다.
- 카테고리, 제목과 내용에 따라 검색하여 궁금한 질문을 더 빠르게 찾을 수 있습니다. 



​
