insert into job_list(jno, job) values(1,'경영지원');
insert into job_list(jno, job) values(2,'마케팅');
insert into job_list(jno, job) values(3,'IT');
insert into job_list(jno, job) values(4,'디자인');
insert into job_list(jno, job) values(5,'영업·유통');
insert into job_list(jno, job) values(6,'물류·자재');
insert into job_list(jno, job) values(7,'상품기획');
insert into job_list(jno, job) values(8,'생산');
insert into job_list(jno, job) values(9,'건설·건축');
insert into job_list(jno, job) values(10,'R&D');
insert into job_list(jno, job) values(11,'의료·제약');
insert into job_list(jno, job) values(12,'미디어');

insert into jayu_category(jayu_cate_no, jayu_cate_name) values(1,'소근소근');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(2,'취직·이직');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(3,'JOB담');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(4,'연애');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(5,'결혼·육아');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(6,'재태크');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(7,'취미');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(8,'뷰티·패션');
insert into jayu_category(jayu_cate_no, jayu_cate_name) values(9,'스포츠·게임');


insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'IT');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'디자인·설계');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'미디어');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'번역');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'문서작업');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'마케팅');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'취업·이직');
insert into soso_category(cate_no, soso_cate_name) values(CATEGORY_SEQ.NEXTVAL,'실무교육');


insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 1',	10000,	'제목입니다 1',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 2',	10001,	'제목입니다 2',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 3',	10002,	'제목입니다 3',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 4',	10003,	'제목입니다 4',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 5',	10004,	'제목입니다 5',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 6',	10005,	'제목입니다 6',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 7',	10006,	'제목입니다 7',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 8',	10007,	'제목입니다 8',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 9',	10008,	'제목입니다 9',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 10',10009,	'제목입니다 10',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 11',10010,	'제목입니다 11',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 12',10011,	'제목입니다 12',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 13',10012,	'제목입니다 13',	1,	1);
insert into soso_job_board(SOSO_NO,MODDATE,REGDATE,CNT,CONTENT,DDUN,TITLE,CATEGORY_CATE_NO,MEMBER_MNO) values(SOSOJOB_SEQ.NEXTVAL, sysdate, sysdate,0,'내용입니다 14',10013,	'제목입니다 14',	1,	1);

insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 4',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 4',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 6',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 7',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 8',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 9',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 9',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 11',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 12',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 13',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 14',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 15',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 16',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 17',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 18',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 19',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 20',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 21',
1, 	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 22',
1, 	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 23',
2,	1);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 24',
2,	2);
insert into jayu_board(JAYU_NO,MODDATE,REGDATE,CONTENT,TITLE,JAYU_CATEGORY_JAYU_CATE_NO,MEMBER_MNO) values(JAYU_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 25',
2,	1);


insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 4',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 4',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 6',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 7',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 8',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 9',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 9',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 11',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 12',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 13',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 14',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 15',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 16',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 17',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 18',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 19',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 20',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 21',
1, 	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 22',
1, 	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 23',
2,	1, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 24',
2,	2, 100, FALSE);
insert into qna_board(qna_no,MODDATE,REGDATE,CONTENT,TITLE,job_list_jno,MEMBER_MNO, DDUN, IS_SOLVED) values(QNA_SEQ.NEXTVAL, sysdate, sysdate,'내용입니다', '제목입니다 25',
2,	1, 100, FALSE);