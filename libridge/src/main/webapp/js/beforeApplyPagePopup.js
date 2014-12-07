var applyPagePopup = {};

applyPagePopup.applyBookInfo = function() {
	
	$.ajax({
		url : 'ajax/book/bookInfo.do',
		type : 'POST',
		dataType : 'json',
		data : {
			isbn : indexGlobal.isbn
		},
		success : function (data) {
			
			console.log('책 정보 전부');
			console.log(data);
			
			var bookDetail = data.bookInfo;
			var tagLeftSide = $('#leftSide');
			
			console.log(bookDetail);
			
//			BookCover
			$('<div>').addClass('detailBookImg').append( $('<img>', {src : bookDetail.bookImgUrl}).addClass('bookImg')).appendTo(tagLeftSide);
			
			$('<div>').addClass('detailBookInfo').appendTo(tagLeftSide);
			var tagDetailBookInfo = $('.detailBookInfo');
			
//			Title
			$('<h1>').addClass('detailBookInfoTitle').text(bookDetail.title).appendTo(tagDetailBookInfo);
			
//			Author
			$('<span>').attr('id','bookAuthor').addClass('detailBookInfoEtc').text(bookDetail.author).appendTo(tagDetailBookInfo);
			
//			Translator
			if (bookDetail.trans != null) {
				
//				Translator
				$('<span>').attr('id','bookTrans').addClass('detailBookInfoEtc').text(bookDetail.trans).appendTo(tagDetailBookInfo);
				
			}
			
//			Pub
			$('<span>').attr('id','bookPub').addClass('detailBookInfoEtc').text(bookDetail.pub).appendTo(tagDetailBookInfo);
			
//			PubDate
			$('<span>').attr('id','bookPubDate').addClass('detailBookInfoEtc').text($.datepicker.formatDate('yy-mm-dd',
					new Date(bookDetail.pubDate))).appendTo(tagDetailBookInfo);
			
			$('<div>').addClass('seperateLine').appendTo(tagDetailBookInfo);
			
			
			console.log(data.numOneBK);
			console.log(data.numOneBK);
			
//			키핑과 기부 권수 정보
			$('<div>').addClass('numberOfBookDiv').appendTo(tagDetailBookInfo);
			var tagNumberOfBookDiv = $('.numberOfBookDiv');
			
			$('<p>').addClass('numberOfBookK').appendTo(tagNumberOfBookDiv);
			$('<p>').addClass('numberOfBookD').appendTo(tagNumberOfBookDiv);
			
			var tagNumberOfBookK = $('.numberOfBookK');
			var tagNumberOfBookD = $('.numberOfBookD');
			
			$('<span>').addClass('numberOfBook').text('현재 키핑된 도서수').appendTo(tagNumberOfBookK);
			$('<span>').addClass('numberOfBook').text(data.numOneBK).appendTo(tagNumberOfBookK);
			$('<span>').addClass('numberOfBook').text('권').appendTo(tagNumberOfBookK);
			$('<span>').addClass('numberOfBook').text('현재 기부된 도서수').appendTo(tagNumberOfBookD);
			$('<span>').addClass('numberOfBook').text(data.numOneBD).appendTo(tagNumberOfBookD);
			$('<span>').addClass('numberOfBook').text('권').appendTo(tagNumberOfBookD);
			
//			취소 or 신청 버튼
			$('<span>').addClass('backToMyPageBtn').append($('<a>', {href : '#', html : '마이 페이지로'}).addClass('wide')).appendTo($('#detailBookInfo'));
		},
		
		error : function (jqXHR,  textStatus, errorThrown) {
			console.log('신청페이지 책 상세 정보 보기 실패');
			console.log('applyPagePopup.js -> BookController.java -> bookInfo');
		}
	});
	
};

applyPagePopup.applyOwnerInfo = function() {
	
	$('#ownerSelectPhrase').text('도서 신청을 하시려면 회원가입 또는 로그인을 해주세요.');

};