
$(function(){
	  $('#writeMessage').on('click',function(e){
			e.preventDefault();
			$('#message_popup').bPopup(
				{opacity : 0.3, positionStyle : 'fixed'}
				);
	  });
	 
	  $( ':button' ).button().click(
			  function( event ) {event.preventDefault(); }
			  ); 
	  $( ':button' ).css({'padding':"0 5px 0 5px", "font-size":"10px",
		  'color':'white','background':'#3DB7CC'});
	  $('#message_popup_header').css(
			  {'background':'black','height':'25px','color':'white',
				 'padding':'3px'
				  }
	  		  );
	  $('#message_textarea').each(function() {
	        $.data(this, 'default', this.value);
	    }).focus(function() {
	        if (!$.data(this, 'edited')) {
	            this.value = "";
	            $(this).addClass("edited");
	        }
	    }).change(function() {
	        $.data(this, 'edited', this.value != "");
	    }).blur(function() {
	        if (!$.data(this, 'edited')) {
	            this.value = $.data(this, 'default');
	$(this).removeClass("edited"); }  });
	  
	  $('#message_nav').corner('6px');
	  $('#message_content').corner('6px');
	  $('#message_footer').corner('6px');
	  
	  $('#message_nav_menu').on('click', '#receive_box', function() {
		  $('#table').load('receiveMessage.html');
	 });
	  $('#message_nav_menu').on('click', '#send_box', function() {
		  $('#table').load('sendMessage.html');
	 });
	  $('#message_nav_menu').on('click', '#storage_box', function() {
		  $('#table').load('storageMessage.html');
	 });
	  var article= {};
	  article.init= function(){
		  
	  }; // 초기화
	  article.getJSON = function(){
		  $.getJSON('',function(data){
			  
		  }//function(data){}끝
		  );// $.getJSON();끝
	  };//article.getJSON = function(){}; 끝
});






$(function(){
	var article= {};
	  article.init= function(){
		  article.writeArticle();
		  $('#document').on('click','',function(){}).load();
	 }; // 초기화
	 
	 
	article.getJSON = function(){
		  $.getJSON('',function(data){
			  
		  }//function(data){}끝
		  );// $.getJSON();끝
	};//article.getJSON = function(){}; 끝
});











