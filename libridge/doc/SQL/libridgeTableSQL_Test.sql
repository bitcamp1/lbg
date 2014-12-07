

insert into book(TiTle, Author, Price, Pub_Date, Reg_Code, Pub, Trans, Type_Code, Reg_Date, ISBN, Book_Img_Url)
value('수학도둑. 33: 심화편','송도수','9500','20130330','2','서울문화사','송도수','7',curdate(),'9788926395233','http://book.daum-img.net/R110x160/BOK00019977284IN?moddttm=20130430155139');

insert into book(TiTle, Author, Price, Publication_Date, Register_Code, Publisher, Translator, Type_Code, Register_Date, ISBN, Book_Img_Url)
value('9788936620714130430','플러스 점프 왕','일신음악연구회','4000','20050901','1','일신서적출판사',NULL,'5',curdate(),'9788936620714','http://book.daum-img.net/R110x160/KOR9788936620714');

insert into member(Name,Address,Phone_Number,Email,ID,Password,Register_Date,Point)
values('김홍태', '부천시 오정구','01011111111','kimhongtae@naver.com','kimhongtae', '1', curdate(),'0');

insert into member(Name,Address,Phone_Number,Email,ID,Password,Register_Date,Point)
values('김연정', '서울 관악구','01022222222','kimyounjung@naver.com','kimyounjung', '1', curdate(),'0');

insert into member(Name,Address,Phone_Number,Email,ID,Password,Register_Date,Point)
values('홍길동', '서울시 용산구','01033333333','hongsungnam@naver.com','hongsungnam', '1', curdate(),'0');

insert into receive_message(Contents, Title, Receive_Member_ID, Receive_Date, Member_Number)
values('소설', '삼국지 세트', 'yyy', curdate(), '1');
insert into receive_message(Contents, Title, Receive_Member_ID, Receive_Date, Member_Number)
values('교양', '플러스 점프 왕', 'sss', curdate(), '2');

insert into receive_message(Contents, Title, Receive_Member_ID, Receive_Date, Member_Number)
values('교육', '수학도둑', 'ppp', curdate(), '3');





select *
from book;





select 
	m1.Member_Number,
	m1.Name,
	m1.address,
	m1.Phone_Number,
	m1.Email,
	m1.Id,
	m1.Password,
	m1.Register_Date,
	m1.Point,
	r1.Receive_Message_Number,
	r1.Contents,
	r1.Title,
	r1.Receive_Member_Id,
	r1.Receive_Date,
	r1.Member_Number
from member m1, receive_Message r1
where m1.Member_Number = r1.Member_Number;



/*강사님*/
select
	*
from member m1, lend l1, personal_library p1, book b1, member m2
where
	m1.mem_no=l1.mem_no
and l1.pers_book_no=p1.pers_book_no
and p1.isbn=b1.isbn
and p1.mem_no=m2.mem_no;





select *
	
from member m1, personal_library p1
where m1.member_number=p1.member_number;

/*all column*/
select 
	m1.Member_Number,
	m1.Name,
	m1.address,
	m1.Phone_Number,
	m1.Email,
	m1.Id,
	m1.Password,
	m1.Register_Date,
	m1.Point,
	b1.Book_Number,
	b1.TiTle,
	b1.Author,
	b1.Price,
	b1.Publication_Date,
	b1.Register_Code,
	b1.Publisher,
	b1.Translator,
	b1.Type_Code,
	b1.Register_Date,
	b1.ISBN,
	b1.Book_Img_Url
from member m1, book b1
where m1.Password = b1.Type_Code;


/*all = p. l. m*/
select 
	p1.Book_Number,
	p1.Member_Number,
	p1.Personal_Book_Number,
	l1.Apply_Number,
	l1.Member_Number,
	l1.Apply_Date,
	l1.Assign_Date,
	l1.Reject_date,
	l1.Personal_Book_Number,
	l1.Apply_Status_Code,
	m1.Member_Number,
	m1.Name,
	m1.address,
	m1.Phone_Number,
	m1.Email,
	m1.Id,
	m1.Password,
	m1.Register_Date,
	m1.Point
	
from Personal_Library p1, Lend l1, Member m1
where p1.Personal_Book_Number = l1.Personal_Book_Number and
l1.Member_Number = m1.Member_Number;


/*로그온이 되지 않은 상태*/
select 

	m1.id,
	m1.password,
	l1.apply_date,
	b1.title,
	l1.apply_status_code,
	m2.id

from member m1, lend l1, personal_library p1, book b1, member m2
where 
	m1.mem_no=l1.mem_no
and l1.pers_book_no=p1.pers_book_no
and p1.isbn=b1.isbn
and p1.mem_no=m2.mem_no;


/*로그온을 한 상태이므로 m1 필요없음*/
select 

	m1.id,
	m1.password,
	l1.apply_date,
	b1.title,
	l1.apply_status_code,
	m2.id
	
from member m1, lend l1, personal_library p1, book b1, member m2
where 
	m1.mem_no=l1.mem_no
and	l1.pers_book_no=p1.pers_book_no
and p1.isbn=b1.isbn
and p1.mem_no=m2.mem_no


order by
m2.id;
	

/*재수정 sql*/

select 

	d1.send_date,
	m2.id,
	b1.title,
	d1.del_code,
	d1.rec_date

from delivery d1, lend l1, personal_library p1, book b1, member m2
where
	d1.apply_no=l1.apply_no
and l1.pers_book_no=p1.pers_book_no
and b1.isbn=p1.isbn
and p1.mem_no=m2.mem_no;




select *
from member;

insert into member(mem_no,Name,Addr,Pho_No,Email,ID,Password,Reg_Date,Point)
values('6','김홍태', '부천시 오정구','01011111111','kimhongtae@naver.com','kimhongtae', '1', curdate(),'0');

insert into member(mem_no,Name,Addr,Pho_No,Email,ID,Password,Reg_Date,Point)
values('8','홍길동', '서울시 강남구','01011122011','honggildong@naver.com','kim', '2', curdate(),'0');

insert into member(mem_no,Name,Addr,Pho_No,Email,ID,Password,Reg_Date,Point)
values('9','유관순', '경상남도 울산시','01011111335','you@naver.com','you', '3', curdate(),'200');

insert into member(mem_no,Name,Addr,Pho_No,Email,ID,Password,Reg_Date,Point)
values('3','박문수', '경상남도 울산시','01011111445','park@naver.com','ppp', '4', curdate(),'200');

insert into member(mem_no,Name,Addr,Pho_No,Email,ID,Password,Reg_Date,Point)
values('2','임꺽정', '경상남도 울산시','01011141445','park@naver.com','imm', '5', curdate(),'200');



select *
from lend;



insert into lend(apply_no,mem_No,apply_Date, assign_Date, reject_Date, pers_Book_No, apply_Status_Code)
values('1','9','2013-03-04','2013-02-05', '2013-02-05', '6','수락');

insert into lend(apply_no,mem_No,apply_Date, assign_Date, reject_Date, pers_Book_No, apply_Status_Code)
values('2','8','2013-04-04','2013-04-05', '2013-04-07', '5','거부');

insert into lend(apply_no,mem_No,apply_Date, assign_Date, reject_Date, pers_Book_No, apply_Status_Code)
values('3','10','2013-03-04','2013-04-05', '2013-04-09', '4','1');

insert into lend(mem_No,apply_Date, assign_Date, reject_Date, pers_Book_No, apply_Status_Code)
values('1','2013-03-04','2013-04-05', '2013-04-09', '2','수락');

insert into lend(mem_No,apply_Date, assign_Date, reject_Date, pers_Book_No, apply_Status_Code)
values('5','2013-03-04','2013-04-05', '2013-04-09', '3','3');



select *
from personal_library;

insert into personal_library(pers_book_no,mem_No,isbn)
values('6','1','9788937450501');

insert into personal_library(pers_book_no,mem_No,isbn)
values('5','2','9788937450501');

insert into personal_library(mem_No,isbn)
values('4','3','9788952708632');


insert into personal_library(mem_No,isbn)
values('3','9788937450501');

insert into personal_library(mem_No,isbn)
values('1','9788952708632');

insert into personal_library(mem_No,isbn)
values('1','9788952708632');

insert into personal_library(mem_No,isbn)
values('2','9788952708632');





select *
from book;

insert into book(TiTle, Author, Price, Pub_Date, Reg_Code, Pub, Trans, Type_Code, Reg_Date, ISBN, Book_Img_Url)
value('만약 고교야구 여자 매니저가 피터드러커를 읽는다면','이와사키 나쓰미','12000','20110501','1','동아일보사','권일영','1',curdate(),'9788970908489','http://book.daum-img.net/R110x160/KOR9788970908489?moddttm=20130425102446');

insert into book(TiTle, Author, Price, Pub_Date, Reg_Code, Pub, Trans, Type_Code, Reg_Date, ISBN, Book_Img_Url)
value('삼국지 세트','나관중','90000','20020305','2','민음사','이문열 평역','1',curdate(),'9788937450501','http://book.daum-img.net/P110x160/KOR9788937450501?moddttm=20130425102446');

insert into book(TiTle, Author, Price, Pub_Date, Reg_Code, Pub, Trans, Type_Code, Reg_Date, ISBN, Book_Img_Url)
value('인터넷 전원주가 한다면 나도 한다','전원주','9500','20110501','1','시공사', '전원주','3',curdate(),'9788952708632','http://book.daum-img.net/R110x160/KOR9788952708632');


select *
from delivery;

insert into delivery(del_no, apply_no, del_code, send_date, rec_date, return_date, return_compl_date)
values('1', '1', '1', '2013-05-01', '2013-05-04', '2013-05-05', '2013-05-06');

insert into delivery(del_no, apply_no, del_code, send_date, rec_date, return_date, return_compl_date)
values('2', '2', '2', '2013-04-01', '2013-04-04', '2013-04-05', '2013-04-06');





	


INSERT ALL
INTO Personal_Library VALUES (Book_Number, Member_Number, Personal_Book_Number)
INTO Lend VALUES (Apply_Number, Member_Number, Apply_Date, Assign_Date,Reject_date, 
	Personal_Book_Number, Apply_Status_Code,)
INTO Member VALUES (empno, ename, mgr)
SELECT empno, ename, hiredate, mgr
FROM emp
WHERE depno = 20




	

select
	*
from member m1, lend l1, personal_library p1, book b1, member m2
where
	m1.mem_no=l1.mem_no
and l1.pers_book_no=p1.pers_book_no
and p1.isbn=b1.isbn
and p1.mem_no=m2.mem_no;



	




select *
from member;


insert into lend(apply_Date, assign_Date, reject_Date, pers_Book_No, apply_Status_Code)
values(curdate(),curdate(),curdate(),'1');


INSERT ALL
INTO Personal_Library VALUES (Book_Number, Member_Number, Personal_Book_Number)
INTO Lend VALUES (Apply_Number, Member_Number, Apply_Date, Assign_Date,Reject_date, 
	Personal_Book_Number, Apply_Status_Code,)
INTO Member VALUES (empno, ename, mgr)
SELECT empno, ename, hiredate, mgr
FROM emp
WHERE depno = 20


