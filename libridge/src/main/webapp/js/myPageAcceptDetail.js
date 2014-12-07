var myPageAcceptDetail = {};

myPageAcceptDetail.init = function() {
	this.myAcceptDetail();

	$('#content').on('click', '.backToMyPageBtn', function(event) {
		$('#content').load('pages/myPage/myPage.html');
	});

	$('#content').on('click', 'a.myPageAcceptForm', function(event) {
		$('#content').load('pages/myPage/acceptPage.html');
	});	
};

myPageAcceptDetail.myAcceptDetail = function() {
	
	$.getJSON(
		'json/myAcceptDetail.txt',
		function(data) {

			var acceptDetailListAdd = $("#acceptDetailList");
			myPageAcceptDetail.initializing();
		
			for(var i in data.acceptList) {
				$("<tr>").addClass('myPageDetailTableTr')
						.append($('<td>').text($.datepicker.formatDate('yy-mm-dd',
						new Date(data.acceptList[i].startDate))))
						.append($('<td>').append($('<a>', {
								text:data.acceptList[i].id,
								href:'#','data-no':data.acceptList[i].title
							}).addClass('myPageAcceptForm') ))
						.append($('<td>').text(data.acceptList[i].title))
						.appendTo(acceptDetailListAdd);
			}
		}
	);
};

myPageAcceptDetail.initializing = function() {
	$( '.myPageDetailTableTr' ).remove();
};