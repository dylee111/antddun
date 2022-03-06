
![wave](https://capsule-render.vercel.app/api?type=slice&color=f9c74f&height=150&text=개미는%20뚠뚠&fontSize=45&fontAlign=15&desc=일상이%20무기력하고%20지루한%20직장인들을%20대상으로%20건강한%20직장문화%20형성을%20위한%20커뮤니티%20플랫폼%20서비스%20입니다.%20&descSize=14&descAlign=41&descAlignY=80)

<br>

## Table of Contents

- [✏Technologies](#Technologies)
- [👀Demo](#Demo)
- [💬Description](#Description)
- [💡담당역할](#담당역할)
- [✍담당설명](#담당설명)

<br> 

<a name="Technologies"></a>
## ✏Technologies

| Field | Techs |
| ------ | ------ |
| Programming | - HTML5 <br> - Thymeleaf <br> - CSS3 <br> - Java(11) <br> - JS |
| Framework / Library | - Spring Boot <br> - JQuery / Ajax <br> - Bootstrap |
| Server | - H2 Database(Oracle) |

<br>

<details>
<summary>Use Case Diagram</summary>
<div markdown="1">       
<img width="900" alt="image" src="https://user-images.githubusercontent.com/86641773/148582686-7a7b830d-a50b-4a3c-9b99-747c0c015283.png">
</div>
</details>

<br>

<a name="Demo"></a>
## 👀Demo

클릭해서 자세히 볼 수 있어요!

- **메인 페이지**

| 반응형 웹사이트 | 점심메뉴 슬롯 | 월급 실시간 확인 | 
| ------ | ------ | ------ | 
|<img src="https://user-images.githubusercontent.com/86641773/149483986-005ac42a-30bb-443d-82e6-d18df98fc7d6.gif" width="350"/> | <img src="https://user-images.githubusercontent.com/86641773/149075887-9c678785-91aa-402f-a21d-278714095d6b.gif" width="350"/> | <img src="https://user-images.githubusercontent.com/86641773/149527495-2d94a22c-2679-43ee-b175-9ac45bf63c7e.gif" width="350"/> |

- **뚠 충전**

| 뚠 충전하기 | 결제 요청 카톡 |
| ------ | ------ | 
| <img src="https://user-images.githubusercontent.com/86641773/149622516-04ddb61a-b905-4416-be3b-d6448f7d1f23.gif" width="500"/> | <img src="https://user-images.githubusercontent.com/86641773/149622461-72dc638c-f448-48e3-8622-2c2128b6895f.png" width="500"/> |

-----------------

- **소셜 로그인**

| 로그인 페이지 | 필수 기입란 이동 | 로그인 성공 | 
| ------ | ------ | ------ | 
| <img src="https://user-images.githubusercontent.com/86641773/149493247-df7ffe4d-1e11-4b7a-8429-bca6ea3e8080.gif" width="300"/> |<img src="https://user-images.githubusercontent.com/86641773/149493252-70b0d315-8cbc-4868-913c-380fb56c444b.gif" width="300"/> | <img src="https://user-images.githubusercontent.com/86641773/149502096-d1d3093e-8333-4fe2-8848-802acabed9f2.gif" width="300"/> | 
| - 메인페이지에서 로그인 페이지로 이동 <br> - 게시물 조회 클릭 시 로그인 페이지로 이동 | 구글 로그인시 자동 회원가입되며 이후 <br> 필수기입란으로 이동 | 직무 + 연차 + 성 + "개미"로 조합된 닉네임 생성 |

- **정보 게시판(QnA)**

| 리스트 페이징 | 카테고리 | 검색 기능 |
| ------ | ------ | ------ | 
| <img src="https://user-images.githubusercontent.com/86641773/149569502-ef605d64-4b8d-4842-9344-f2fd2bd8f916.gif" width="350"/> | <img src="https://user-images.githubusercontent.com/86641773/149569517-4f3cb951-6a0b-48c9-9cc8-c46b2534ca36.gif" width="350"/>  | <img src="https://user-images.githubusercontent.com/86641773/149619166-56c06abb-843b-4770-81fe-eecc458ecfec.gif" width="300"/> | 


| 좋아요 | 채택하기 | 댓글 블라인드 |
| ------ | ------ | ------ | 
| <img src="https://user-images.githubusercontent.com/86641773/149619202-b1bef410-00c1-4c8f-8c54-739f5b612432.gif" width="350"/> | <img src="https://user-images.githubusercontent.com/86641773/149619207-ee5992d9-3fe5-49ee-9968-2c5d8da380e0.gif" width="350"/>  | <img src="https://user-images.githubusercontent.com/86641773/149619211-60807b45-ac2d-4c8d-8f18-52755182839a.gif" width="300"/> | 
| - UI는 토글 방식으로 구현 <br> - 좋아요을 누를때 마다 Ajax로 게시물 번호를 보내 처리 후 좋아요의 총 개수를 가져와 표시한다. <br> - Model로 좋아요 체크 유무를 받아오고 Thymeleaf의 th:if를 이용해 좋아요 표시  | - Ajax로 버튼 이벤트 시 POST방식으로 멤버 번호, 게시글 번호의 정보와 선택한 댓글의 댓글번호를 전달해 처리한다. | - Thymeleaf의 th:if문을 이용해 제 3자는 채택된 댓글을 볼 수 없다. |


<br><br>

<a name="Description"></a>
## 💬 Description
> 뚠이라는 가상화폐를 충전하여 소소한 잡, 정보게시판(이하 QnA)에서 사용이 가능합니다. <br>
> kakopay 결제 API를 이용하여 간단하게 QR코드와 전화번호로 결제 둘 다 가능합니다. (테스트용으로 실제 결제는 되지 않습니다.)

### 1. 소소한 재미가 있는 메인페이지!
1. 메인 페이지에서 직장인들의 최대 고민 중 하나인 **점심메뉴를 슬롯으로 추천**받을 수 있어요. 
2. 선택항목에서 연봉을 기입해 놓으면 오늘 하루동안 **얼마를 벌었는지 시간단위로 확인**해볼 수 있어요. 
3. 사고 싶었던 것들을 **위시리스트**에 적어보세요! **월급과 적급율을 적으면 D-Day로 변환**시켜준답니다! 

### 2. 소소한 정보
- 정보게시판 QnA에서 이루어지며 직무로 카테고리가 나눠져 있습니다. 
- 작성자는 궁금한 내용을 카테고리 별로 질문할 수 있습니다. 이때 뚠을 같이 걸면 답변율이 상승하겠죠!?
- 뚠은 10단위로 걸 수 있습니다. 게시물 등록 이후 댓글이 하나 이상이라도 달리면 게시물 삭제는 불가능 합니다.
- 작성자는 댓글 중 가장 고마운 한 명에게 채택을 하며 걸었던 뚠을 보낼 수 있습니다. 이후 채택된 댓글은 제 3자가 볼 수 없습니다.
 
### 3. 소소한 잡
- 투잡이 대세인 요즘, 내 직무와 관련한 소소한 일거리들을 구직, 구인이 가능합니다. 
- 주로 전문직종으로 카테고리가 구성되어 있습니다. 
- 판매자와 쪽지를 주고 받을 수 있습니다. 이 때는 닉네임이 아니라 등록된 이메일로 소통을 합니다.

### 4. 자유게시판 
- 일을 하면서 겪는 다양한 얘기들을 자유롭게 나눌 수 있습니다. 
- 일정 시간이 지나면 게시글이 자동삭제되는 펑예 기능이 작업 중에 있습니다. 

​
​


<a name="담당역할"></a>
## 💡담당 역할

| 김란 | 이동영 | 김근혜 | 박단비 | 
| -- | -- | -- | -- |
| [![김란](https://avatars.githubusercontent.com/u/86641847?v=4)](https://github.com/aibeam) | [![이동영](https://avatars.githubusercontent.com/u/76557211?v=4)](https://github.com/dylee111) | [![김근혜](https://avatars.githubusercontent.com/u/85294826?v=4)](https://github.com/kimgeunhye) | [![박단비](https://avatars.githubusercontent.com/u/86641773?v=4")](https://github.com/danbi-park) |
|  - 전체적인 UI 담당<br> - 점심메뉴 슬롯, 위시리스트 | - 회원가입 / 일반 로그인 <br> - 소소한 잡 전체<br> - 쪽지 페이지<br> - API로 뚠 충전 구현<br> - 위시리스트 백엔드 | - 자유게시판 전체 <br> - 웹 에디터 적용 (summernote) | - 소셜 로그인 <br> - 아이디/비밀번호 찾기 <br> - QnA 게시판 전체 <br> - 좋아요 / 카테고리 별 검색 기능 |

 
<br><br>

<a name="담당설명"></a>
## ✍담당 설명

### 🙋🏻‍♀️박단비

<details>
<summary>1. 소셜 로그인</summary>
<div markdown="1">       

 - OAuth를 기반으로 네이버, 페이스북, 구글의 API를 호출하여 로그인시 강제 회원가입을 진행한 후, 추가 필수 기입란으로 이동합니다.
 - 필수 기입란에 이름, 직무, 연차를 조합해 예)IT 1년차 박개미 로 사이트에서 활동할 닉네임이 자동으로 만들어집니다. 
 - 아이디 찾기 : 전화번호를 Ajax로 보내 처리하여 아이디를 찾을 수 있습니다.
 - 비밀번호 찾기 : coolSMS API를 이용해서 인증 문자로 비밀번호를 찾을 수 있습니다.
 
</div>
</details>

<details>
<summary>2. 정보 게시판(QnA)</summary>
<div markdown="2">       

 
- 게시글
  - 게시글을 보려면 로그인이 필요합니다. config에서 ```.antMatchers("/member/**").authenticated()``` 설정.  만약 회원이 아니라면 로그인 화면으로 이동합니다.
  - 게시판 CRUD가 가능하고, 게시물 등록 시 뚠(가상화폐)을 걸 수 있습니다. 뚠은 게시물 테이블에 pending 되어 있습니다.
  - Ajax를 활용하여 좋아요 및 댓글 구현을 했습니다.
 
- 리스트
  - 게시판 리스트는 카테고리 별로 볼 수 있습니다. 
  - 좋아요, 댓글, 조회수가 리스트와 조회 페이지에 표시됩니다.
  - JPQL을 활용하여 검색 기능 구현, 검색조건은 카테고리, 제목, 내용, 제목+내용이 있습니다.
 
</div>
</details>


<details>
<summary>💡 채택 과정 상세 설명</summary>
<div markdown="2">      
 
 <br>
 
  - 댓글이 하나라도 달렸을 시 게시물은 삭제이 불가능합니다. 수정은 할 수 있으니 뚠은 수정할 수 없습니다.
  - 뚠 없이 게시물을 올리면 “뚠 없음”이 표시됩니다.
  - 답변자를 채택하여 뚠을 보낼 수 있습니다. 
  - 채택된 글의 제목과 뚠이 개인 뚠 정보 내역에 표시됩니다.
  - 채택되고 난 후 게시글은 채택완료로 표시되고 채택을 더 이상 할 수 없습니다.
  - 게시글 작성자, 채택된 답변자이외에는 채택된 답변을 볼 수 없습니다. 
 
 </div>
 </details>
 
 ### 이동영
 <details>
 <summary>1. 회원가입</summary>
 <div markdown="1">
<small><a href='https://dongdong-zzangzzang.tistory.com/entry/%ED%9A%8C%EC%9B%90%EA%B0%80%EC%9E%85'/>회원가입 구현</a></small>
 </div>
 </details>
 
 <details>
 <summary>2. 뚠 충전 및 거래</summary>
 <div markdown="2">
  <small><a href='https://dongdong-zzangzzang.tistory.com/entry/%EA%B0%80%EC%83%81%EB%A8%B8%EB%8B%88%EB%9A%A0-%EC%B6%A9%EC%A0%84-%EB%B0%8F-%EA%B1%B0%EB%9E%98-%EA%B5%AC%ED%98%84'/>뚠 충전 및 거래 구현</a></small>
 </div>
 </details>
 
  <details>
 <summary>3. 위시리스트</summary>
 <div markdown="2">
 <small><a href='https://dongdong-zzangzzang.tistory.com/entry/%EC%9C%84%EC%8B%9C-%EB%A6%AC%EC%8A%A4%ED%8A%B8'/>위시리스트</a></small>
 </div>
 </details>
 

