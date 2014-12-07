var fla = 0;
var myLibraryBookInfo = '';
var myLibrary = {};
var myLibraryUserInfo = '';
var myLibraryUserInfos= [];
var myLibraryLatLng = {};
var myLibraryAddrs = [];
var mapCheck = 0;
var markers = [];

myLibrary.init = function () {
	
	console.log('myLibrary.js enter');
	this.initPage();
	this.pageLoad();

	this.clickEvent();
};

myLibrary.initPage = function () {
	$('#myLibraryContents').empty();
	if (myLibrary.pageNo == undefined) {
		myLibrary.pageNo = 1;
	}
	console.log('여기 까지 왔어용!');
	$.ajax('ajax/personalLibrary/myLibraryAll.do', {
		dataType : 'json',
		type : 'POST',
		data : {
			pageNo : myLibrary.pageNo
		},
		success : function (data) {
			console.log('여기는 처음 Ajax 안에 있는곳');
			myLibraryBookInfo = data;
			myLibrary.pageNo = data.pageNo;
			myLibrary.recordCount = data.recordCount;
			myLibrary.pageSize = data.pageSize;
			myLibraryUserInfo = data.member;
			
			var totalPage = parseInt(myLibrary.recordCount / myLibrary.pageSize);
			if ((myLibrary.recordCount % myLibrary.pageSize) > 0) {
				totalPage++;
			}
			myLibrary.totalPage = totalPage;
			console.log(data);
			for(var i = 0; i < data.myLibraryAll.length; i++) {
				if(data.myLibraryAll[i].rentalStatus == '1') {
					if((data.myLibraryAll[i].bookImgUrl == '') || (data.myLibraryAll[i].bookImgUrl == null)){
						$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
						.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : 'img/bookImgNoneR.jpeg'}))))
						.append($('<div>').addClass('myLibrarylendText')));
						myLibrary.bookDetailPopup(i);
					} else{
						$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
						.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : data.myLibraryAll[i].bookImgUrl}))))
						.append($('<div>').addClass('myLibrarylendText')));
						myLibrary.bookDetailPopup(i);
					}
				}
				if(data.myLibraryAll[i].rentalStatus == '2') {
					if((data.myLibraryAll[i].bookImgUrl == '') || (data.myLibraryAll[i].bookImgUrl == null)){
						$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
						.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : 'img/bookImgNoneR.jpeg'}))))
						.append($('<div>').addClass('myLibrarylendText').append('대여중')));
						myLibrary.bookDetailPopup(i);
					} else{
						$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
						.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : data.myLibraryAll[i].bookImgUrl}))))
						.append($('<div>').addClass('myLibrarylendText').append('대여중')));
						myLibrary.bookDetailPopup(i);
					}
				}
				
			}
		},
		error : function (xhr, status, message) {
			alert('초기페이지실패');
		}
	});
};


myLibrary.clickEvent = function () {
	$('#myLibraryKeeping').on('click', function (e) {
		fla = 1;
		myLibrary.pageNo = 1;
		myLibrary.myLibraryKeeping();
		e.stopImmediatePropagation();
	});
	$('#myLibraryDonation').on('click', function (e) {
		fla = 2;
		myLibrary.pageNo = 1;
		myLibrary.myLibraryDonation();
		e.stopImmediatePropagation();
	});
};

myLibrary.myLibraryKeeping = function () {
	$('#myLibraryContents').empty();
	if (myLibrary.pageNo == undefined) {
		myLibrary.pageNo = 1;
	}
	$.ajax('ajax/personalLibrary/myLibrary.do', {
		dataType : 'json',
		type : 'POST',
		data : {
			regCode : '1',
			pageNo : myLibrary.pageNo
		},
		success : function (data) {
			myLibraryBookInfo = data;
			myLibrary.pageNo = data.pageNo;
			myLibrary.recordCount = data.recordCount;
			myLibrary.pageSize = data.pageSize;
			myLibraryUserInfo = data.userInfo;
			mapCheck = 0;
			
			var totalPage = parseInt(myLibrary.recordCount / myLibrary.pageSize);
			if ((myLibrary.recordCount % myLibrary.pageSize) > 0) {
				totalPage++;
			}
			myLibrary.totalPage = totalPage;
			for(var i = 0; i < data.myLibrary.length; i++) {
				if(data.myLibrary[i].regCode == 1) {
					if(data.myLibrary[i].rentalStatus == '1') {
						if((data.myLibrary[i].bookImgUrl == '') || (data.myLibrary[i].bookImgUrl == null)){
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : 'img/bookImgNoneR.jpeg'}))))
							.append($('<div>').addClass('myLibrarylendText')));
							myLibrary.bookDetailPopup(i);
						} else{
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : data.myLibrary[i].bookImgUrl}))))
							.append($('<div>').addClass('myLibrarylendText')));
							myLibrary.bookDetailPopup(i);
						}
					}
					if(data.myLibrary[i].rentalStatus == '2') {
						if((data.myLibrary[i].bookImgUrl == '') || (data.myLibrary[i].bookImgUrl == null)){
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : 'img/bookImgNoneR.jpeg'}))))
							.append($('<div>').addClass('myLibrarylendText').append('대여중')));
							myLibrary.bookDetailPopup(i);
						} else{
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : data.myLibrary[i].bookImgUrl}))))
							.append($('<div>').addClass('myLibrarylendText').append('대여중')));
							myLibrary.bookDetailPopup(i);
						}
					}
				}
			}
		},
		error : function (xhr, status, message) {
			alert('대여페이지실패');
		}
	});
};

myLibrary.myLibraryDonation = function () {
	$('#myLibraryContents').empty();
	if (myLibrary.pageNo == undefined) {
		myLibrary.pageNo = 1;
	}
	$.ajax('ajax/personalLibrary/myLibrary.do', {
		dataType : 'json',
		type : 'POST',
		data : {
			regCode : '2',
			pageNo : myLibrary.pageNo
		},
		success : function (data) {
			myLibraryBookInfo = data;
			myLibrary.pageNo = data.pageNo;
			myLibrary.recordCount = data.recordCount;
			myLibrary.pageSize = data.pageSize;
			mapCheck = 1;
			
			var totalPage = parseInt(myLibrary.recordCount / myLibrary.pageSize);
			if ((myLibrary.recordCount % myLibrary.pageSize) > 0) {
				totalPage++;
			}
			myLibrary.totalPage = totalPage;
			for(var i = 0; i < data.myLibrary.length; i++) {
				if(data.myLibrary[i].regCode == 2) {
					if(data.myLibrary[i].rentalStatus == '1') {
						if((data.myLibrary[i].bookImgUrl == '') || (data.myLibrary[i].bookImgUrl == null)){
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : 'img/bookImgNoneR.jpeg'}))))
							.append($('<div>').addClass('myLibrarylendText')));
							myLibrary.bookDetailPopup(i);
						} else{
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : data.myLibrary[i].bookImgUrl}))))
							.append($('<div>').addClass('myLibrarylendText')));
							myLibrary.bookDetailPopup(i);
						}
					}
					if(data.myLibrary[i].rentalStatus == '2') {
						if((data.myLibrary[i].bookImgUrl == '') || (data.myLibrary[i].bookImgUrl == null)){
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : 'img/bookImgNoneR.jpeg'}))))
							.append($('<div>').addClass('myLibrarylendText').append('대여중')));
							myLibrary.bookDetailPopup(i);
						} else{
							$('#myLibraryContents').append($('<div>').addClass('myLibraryBookDiv').append($('<div>').addClass('myLibraryBookInfo')
							.append($('<a>').addClass('myLibrarybookImg' + i).attr('href','#').append($('<img>',{src : data.myLibrary[i].bookImgUrl}))))
							.append($('<div>').addClass('myLibrarylendText').append('대여중')));
							myLibrary.bookDetailPopup(i);
						}
					}
				} 
			}
		},
		error : function (xhr, status, message) {
			alert('기부페이지실패');
		}
	});
};

myLibrary.pageLoad = function () {
	$('#libPrevList').click(function(event) {
		alert('이전');
		event.preventDefault();
		if (myLibrary.pageNo > 1) {
			myLibrary.pageNo--;
			if(fla == 0) {
				myLibrary.initPage();
			}
			if(fla == 1){
				myLibrary.myLibraryKeeping();
			}
			if(fla == 2){
				myLibrary.myLibraryDonation();
			}
		}
	});
	
	$('#libNextList').click(function(event) {
		alert('다음');
		event.preventDefault();
		if (myLibrary.pageNo < myLibrary.totalPage) {
			myLibrary.pageNo++;
			if(fla == 0) {
				myLibrary.initPage();
			}
			if(fla == 1){
				myLibrary.myLibraryKeeping();
			}
			if(fla == 2){
				myLibrary.myLibraryDonation();
			}
		}
	});
};

myLibrary.bookDetailPopup = function (i) {
$('.myLibrarybookImg' + i).on('click', function (e) {
	console.log(i);
	myLibraryUserInfos =[];
	myLibraryLatLng = {};
	myLibraryAddrs = [];
	markers = [];
	$('#myLibraryKeepingPopup').empty();
	if(mapCheck == 1){
		$('#myLibraryKeepingPopup').attr('title','Libridge').append($('<div>').append($('<img>',{src:myLibraryBookInfo.myLibrary[i].bookImgUrl})).addClass('myLibraryDetailBookInfo'))
		.append($('<div>').append($('<div>').addClass('bookDetailTitle').append(myLibraryBookInfo.myLibrary[i].title))
				.append($('<div>').addClass('bookDetailContents').append(myLibraryBookInfo.myLibrary[i].author))
				.append($('<div>').addClass('bookDetailContents').append(myLibraryBookInfo.myLibrary[i].trans))
				.append($('<div>').addClass('bookDetailContents').append(myLibraryBookInfo.myLibrary[i].price))
				.append($('<div>').attr('id','map')));
		$('#myLibraryKeepingPopup').dialog({
				resizable: false,
				width : 500,
			    height : 400,
			    modal: true
		});
		myLibrary.memberDonationlatlng(i);
		} else {
			$('#myLibraryKeepingPopup').attr('title','Libridge').append($('<div>').append($('<img>',{src:myLibraryBookInfo.myLibrary[i].bookImgUrl})).addClass('myLibraryDetailBookInfo'))
			.append($('<div>').append($('<div>').addClass('bookDetailTitle').append(myLibraryBookInfo.myLibrary[i].title))
					.append($('<div>').addClass('bookDetailContents').append(myLibraryBookInfo.myLibrary[i].author))
					.append($('<div>').addClass('bookDetailContents').append(myLibraryBookInfo.myLibrary[i].trans))
					.append($('<div>').addClass('bookDetailContents').append(myLibraryBookInfo.myLibrary[i].price)));
			$('#myLibraryKeepingPopup').dialog({
				resizable: false,
				width : 500,
			    height : 400,
			    modal: true
			});
		}
		
		e.stopImmediatePropagation();
	});
};

myLibrary.memberDonationlatlng = function (i) {
	$.ajax('ajax/personalLibrary/donationMap.do', {
		type : 'POST',
		datatype : 'json',
		async: false,
		data : {
			persBookNo : myLibraryBookInfo.myLibrary[i].persBookNo,
			pageNo : myLibrary.pageNo
		},
		success : function (data) {
			console.log(data);
			var kount = 1;
			myLibraryUserInfos[0] = myLibraryUserInfo.addr1;
			if(!(data.donation.length == 0)){
				for(var j = 0; j < data.donation.length; j++){
					myLibraryUserInfos[j+1] = data.donation[j].addr1;
					if(j == data.donation.length - 1){
						for(var k=0; k < myLibraryUserInfos.length; k++){
							console.log(myLibraryUserInfos);
							kount = myLibraryUserInfos.length;
							myLibrary.daumLatLng(myLibraryUserInfos[k], kount);
						}
					}
				}
			} else {
				myLibrary.daumLatLng(myLibraryUserInfos[0], kount);
			}
		},
		error : function (xhr, status, message) {
			alert('member주소정보 가져오기 실패');
		}
	});
};
myLibrary.daumLatLng = function (myLibraryUserInfos, kount) {
	myLibrary.mapFilter();
	console.log(myLibraryUserInfos);
	
	$.ajax('http://apis.daum.net/local/geo/addr2coord?apikey=489ce4b369d961a41dd2feafe4c91faaaa5cad41&output=json&q='+myLibraryUserInfos, {
		dataType : 'json',
		type : 'GET',
		success : function (data) {
			var xjapyo = data.channel.item[0].point_x;
			var yjapyo = data.channel.item[0].point_y;
				myLibraryLatLng = {y : yjapyo, x : xjapyo};
				myLibraryAddrs[myLibraryAddrs.length] = myLibraryLatLng;
				
				if(myLibraryAddrs.length == kount){
					myLibrary.googleMap(myLibraryAddrs);
				}
				console.log(myLibraryAddrs[0]+'<=============');
				console.log(myLibraryAddrs[1]+'<=============');
				console.log(myLibraryAddrs[2]+'<=============');
		},
		error : function (xhr, status, message) {
			alert('좌표실패');
		}
	});
};

myLibrary.googleMap = function (myLibraryAddrs) {
		var map;
		var inde = myLibraryAddrs.length;
		console.log('이거는 랭값이다');
		console.log(inde);
		console.log('이거는 주소 좌표 모음이다');
		console.log(myLibraryAddrs);
		var mapOptions = {
		        center: new google.maps.LatLng(myLibraryAddrs[inde-1].y, myLibraryAddrs[inde-1].x),
		        zoom: 6,
		        mapTypeId: google.maps.MapTypeId.ROADMAP
		      }; 
		map = new google.maps.Map($('#map')[0],
		        mapOptions);
		for(var count=0; count < myLibraryAddrs.length; count++){
			markersopser = 
				{
					position : new google.maps.LatLng(myLibraryAddrs[count].y, myLibraryAddrs[count].x),
					map : map,
					icon : 'img/PingIcon'+count+'.png'
				};
			markers.push(markersopser);
			new google.maps.Marker(markers[count]);
		}
};

myLibrary.mapFilter = function (myLibraryUserInfo) {
	$.ajaxPrefilter( "json", function( options, originalOptions, jqXHR ) {
		if( options.crossDomain ) {
			return 'jsonp';
		} else {
		return 'json';
		}
	});
};