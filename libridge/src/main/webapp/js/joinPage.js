var joinMem = {};

joinMem.init = function(){
	
	$('#joinMemberId input').keyup(function() {
		
		console.log('blur 테스트');
		console.log($('#memberId').val());
		
		joinMem.idcheck();
		
	});
	
	$('.joinMemberBtn').hover(function() {
		$(this).addClass('reverse');
	}, function() {
		$(this).removeClass('reverse');
	});
	
	$('#zipCodeLooking').click(function() {
		search_post();
	});
	
	$('#content').on('click', '#joinBtn', function(event){
		
		if($('#memberId').val()==''){
			alert('아이디는 필수입력 사항입니다');
			$('#memberId').focus();
//			$('#content').load('pages/member/joinPage.html');
			
		} else if($('#memberPwd').val()==''){
			alert('비밀번호는 필수입력 사항입니다');
			$('#memberPwd').focus();
//			$('#content').load('pages/member/joinPage.html');
			
		} else if($('#memberPwd').val()!=$('#confirmPwd').val()){
			alert('비밀번호가 일치하지 않습니다');
			$('#memberPwd').val('');
			$('#confirmPwd').val('');
			$('#memberId').focus();
			
//			$('#content').load('pages/member/joinPage.html');
			
		} else if($('#name').val()==''){
			alert('이름은 필수입력 사항입니다');
			$('#memberId').focus();
			
//			$('#content').load('pages/member/joinPage.html');
			
		} else {
			
			var postFront = $('#joinMemberZipCode1').val().slice(0,3);
			var postBack = $('#joinMemberZipCode1').val().slice(4);
			
			console.log(postFront);
			console.log(postBack);
			
			$.ajax('ajax/member/add.do',{
				type : 'POST',
				dataType: 'json',
				data:{
					id:$('#memberId').val(),
					password:$('#memberPwd').val(),
					name:$('#name').val(),
					email:$('#email').val(),
					phoNo:$('#phoNo').val(),
					zipCode:postFront + postBack,
					addr1:$('#addr1').val(),
					addr2:$('#addr2').val()
					
				},
				success:function(data){
					if(data.status='success') {
					console.log(data);
					window.alert("가입이 완료되었습니다.");
					$('#content').load('pages/main/mainContents.html');
					}
				},
				
				error:function(xhr, status, message){
					alert('회원가입 실패입니다');
		    	}
			});
		}
	});
};

joinMem.idcheck = function() {
	
	$.ajax({
		url : 'ajax/member/idCheck.do',
		dataType : 'json',
		type : 'POST',
		data : {requestId : $('#memberId').val()},
		success : function(data) {
			
			if(data.status == 'success') {
				console.log(data.message);
			} else {
				$('#content').off('click', '#joinBtn');
			}
		},
		
		error:function(xhr, status, message){
			alert('blur에 의한 ajax요청 실패');
		}
	});
	
};

function search_post() {
	window.open("pages/member/jp2.html", "search_open", "top=0, left=0, width=520px, height=240, resizable=1, scrollbars=no");
}