if ( typeof(JKL) == 'undefined' ) JKL = function() {};
//JKL.ParseXML 생성자

JKL.ParseXML = function ( url, query ) {
    // debug.print( "new JKL.ParseXML( '"+url+"', '"+query+"' );" );
    this.url    = url;
    this.method = ( typeof(query) == "string" ) ? "POST" : "GET";
    this.query  = ( typeof(query) == "string" ) ? query : null;
    this.async_done = false;
    return this;
};

//  클래스 변수
                                                                                                                                                                                                                                                                 
JKL.ParseXML.MAP_NODETYPE = [
    "",
    "ELEMENT_NODE",                 // 1
    "ATTRIBUTE_NODE",               // 2
    "TEXT_NODE",                    // 3
    "CDATA_SECTION_NODE",           // 4
    "ENTITY_REFERENCE_NODE",        // 5
    "ENTITY_NODE",                  // 6
    "PROCESSING_INSTRUCTION_NODE",  // 7
    "COMMENT_NODE",                 // 8
    "DOCUMENT_NODE",                // 9
    "DOCUMENT_TYPE_NODE",           // 10
    "DOCUMENT_FRAGMENT_NODE",       // 11
    "NOTATION_NODE"                 // 12
];

/*JKL.ParseXML.REQUEST_TYPE  = "application/x-www-form-urlencoded";*/
/*JKL.ParseXML.RESPONSE_TYPE = "text/xml";*/
JKL.ParseXML.ACTIVEX_XMLDOM  = "Microsoft.XMLDOM";  // Msxml2.DOMDocument.5.0
JKL.ParseXML.ACTIVEX_XMLHTTP = "Microsoft.XMLHTTP"; // Msxml2.XMLHTTP.3.0

//  콜백 함수 정의 (ajax)

JKL.ParseXML.prototype.async = function ( func, args ) {
    this.callback_func = func;       // 콜백 함수
    this.callback_arg  = args;       // 첫번째 인수
};

/*
JKL.ParseXML.prototype.onerror = function ( func, args ) {
    this.onerror_func = func;       // 콜백 함수
};*/

//  모든 자식을 배열로
JKL.ParseXML.prototype.setOutputArrayAll = function () {
    this.setOutputArray( true );
}

//  자식 하나는 스칼라로, 다수의 자신은 배열로
JKL.ParseXML.prototype.setOutputArrayAuto = function () {
    this.setOutputArray( null );
}

//  모든 자식을 스칼라로 (첫 형제만)
JKL.ParseXML.prototype.setOutputArrayNever = function () {
    this.setOutputArray( false );
}

//  지정된 자식을 배열로, 남은 자식은 스칼라로
JKL.ParseXML.prototype.setOutputArrayElements = function ( list ) {
    this.setOutputArray( list );
}

//  자식을 어떻게 스칼라·배열로 다룰지 지정
JKL.ParseXML.prototype.setOutputArray = function ( mode ) {
    if ( typeof(mode) == "string" ) {
        mode = [ mode ];                // 문자열을 배열로
    }
    if ( mode && typeof(mode) == "object" ) {
        if ( mode.length < 0 ) {
            mode = false;               // 배열이 []면 false 
        } else {
            var hash = {};
            for( var i=0; i<mode.length; i++ ) {
                hash[mode[i]] = true;
            }
            mode = hash;                // 배열을 해쉬 배열로
            if ( mode["*"] ) {
                mode = true;            // "*"을 포함하면 true
            }
        } 
    } 
    this.usearray = mode;
}

//  원격의 XML 파일을 내려 받아 파싱한다

JKL.ParseXML.prototype.parse = function () {

    var http = new JKL.ParseXML.HTTP( this.method );
    // debug.print( "JKL.ParseXML.HTTP: "+http );
    if ( ! http ) return;

    if ( this.onerror_func ) http.onerror = this.onerror_func;

    if ( this.callback_func ) {                             // 비동기 모드
        var copy = this;
        var proc = function() {
            var dom = http.documentElement();
            var d2j = new JKL.ParseXML.JSON( copy.usearray );
            var json = d2j.parseDocument( dom );
            copy.callback_func( json, copy.callback_arg );  // 콜백
        };
        http.load( this.url, this.query, proc );
    } else {                                                // 동기 모드
        http.load( this.url, this.query, null );
        var dom = http.documentElement();
        var d2j = new JKL.ParseXML.JSON( this.usearray );
        var json = d2j.parseDocument( dom );
        return json;
    }
};

// ================================================================

JKL.ParseXML.HTTP = function( method ) {
    this.req = null;
    this.method = method;
    this.xmlhttp = false;
    this.xmldom = false;
    this.onerror = null;

    if ( window.ActiveXObject ) {
        if ( method == "GET" ) {                    // IE GET
            // debug.print( 'new ActiveXObject( "'+JKL.ParseXML.ACTIVEX_XMLDOM+'" )' );
            this.req = new ActiveXObject( JKL.ParseXML.ACTIVEX_XMLDOM );
            if ( this.req ) this.xmldom = true;
        } else {                                    // IE POST
            // debug.print( 'new ActiveXObject( "'+JKL.ParseXML.ACTIVEX_XMLHTTP+'" )' );
            this.req = new ActiveXObject( JKL.ParseXML.ACTIVEX_XMLHTTP );
            if ( this.req ) this.xmlhttp = true;
        }
//  } else if ( method == "GET" &&
//              document.implementation && 
//              document.implementation.createDocument ) {
//      // debug.print( "document.implementation.createDocument()" );
//      this.req = document.implementation.createDocument("", "", null);
//      if ( this.req ) this.xmldom = true;
    } else if ( window.XMLHttpRequest ) {           // Firefox, Opera
        // debug.print( "new XMLHttpRequest()" );
        this.req = new XMLHttpRequest();
        if ( this.req ) this.xmlhttp = true;
    }
    return this;
};

JKL.ParseXML.HTTP.prototype.load = function( url, query, func ) {

    // 콜백 함수가 지정되어 있으면 비동기모드
    var async = func ? true : false;
    // debug.print( "async: "+ async );

    if ( this.xmlhttp ) {
        // 열기
        // debug.print( "open( '"+this.method+"', '"+url+"', "+async+" );" );
        this.req.open( this.method, url, async );

        // 응답 Content-Type: text/xml (재정의)
        if ( typeof(this.req.overrideMimeType) != "undefined" ) {
            // debug.print( "Content-Type: "+JKL.ParseXML.RESPONSE_TYPE+" (response)" );
            this.req.overrideMimeType( JKL.ParseXML.RESPONSE_TYPE );
        }

        // 요청 Content-Type: application/x-www-form-urlencoded
        if ( typeof(this.req.setRequestHeader) != "undefined" ) {
            // debug.print( "Content-Type: "+JKL.ParseXML.REQUEST_TYPE+" (request)" );
            this.req.setRequestHeader("content-type", JKL.ParseXML.REQUEST_TYPE );
        }
    }

    // 비동기 모드인 경우 콜백 핸드러 설정
    if ( async ) {
        var copy = this;
        copy.already_done = false;                  // 아직 파싱이 안되었다
        var callback = function () {
            if ( copy.req.readyState != 4 ) return;
            // debug.print( "readyState: "+copy.req.readyState );
            if ( copy.xmlhttp ) {
                // debug.print( "status: "+copy.req.status+" (async mode)" );
                if ( copy.req.status != 200 && copy.req.status != 304 ) {
                    if ( copy.onerror ) copy.onerror( copy.req.status );
                    return;
                }
            }
            if ( copy.xmldom ) {
                // Microsoft.XMLDOM에서 파싱 에러
                if ( copy.req.parseError && copy.req.parseError.errorCode != 0 ) {
                    // debug.print( "parseError: "+copy.req.parseError.reason );
                    if ( copy.onerror ) copy.onerror( copy.req.parseError.reason );
                    return;
                }
            }
            if ( copy.already_done ) return;        // 딱 한번만 파싱
            copy.already_done = true;               // 이미파싱됨
            func();                                 // 콜백
        };
        this.req.onreadystatechange = callback;
        // this.req.onload = func;  // document.implementation.createDocument
    }

    if ( this.xmldom ) {
        this.req.async = async;

        // 요청과 쿼리 문자열을 보낸다
        // debug.print( "load( '"+url+"' );" );
        this.req.load( url );
    }

    if ( this.xmlhttp ) {
        // 요청과 쿼리 문자열을 보낸다
        // debug.print( "send( '"+query+"' );" );
        this.req.send( query );
    }

    // 비동기 모드인 경우 그냥 리턴한다
    if ( async ) return;

    if ( this.xmldom ) {
        // Microsoft.XMLDOM에서 파싱 에러
        if ( this.req.parseError && this.req.parseError.errorCode != 0 ) {
            // debug.print( "parseError: "+this.req.parseError.reason );
            if ( this.onerror ) this.onerror( this.req.parseError.reason );
            return;
        }
    }

    if ( this.xmlhttp ) {
        // debug.print( "status: "+this.req.status+" (sync mode)" );
        if ( this.req.status != 200 && this.req.status != 304 ) {
            if ( this.onerror ) this.onerror( this.req.parseError.reason );
            return;
        }
    }
}

JKL.ParseXML.HTTP.prototype.documentElement = function() {
    if ( ! this.req ) return;
    if ( this.xmlhttp && this.req.responseXML ) {
        return this.req.responseXML.documentElement;
    }
    if ( this.xmldom ) {
        return this.req.documentElement;
    }
}

// ================================================================

JKL.ParseXML.JSON = function( usearray ) {
    this.usearray = usearray;
    return this;
}

//  DOM을 JSON으로 변환한다

JKL.ParseXML.JSON.prototype.parseDocument = function ( root ) {
    // debug.print( "parseDocument: "+root );
    if ( ! root ) return;

    var ret = this.parseElement( root );            // 루트 노드 파싱
    // debug.print( "parsed: "+ret );

    if ( this.usearray == true ) {                  // 언제나 배열로
        ret = [ ret ];
    } else if ( this.usearray == false ) {          // 언제나 스칼라로
        //
    } else if ( this.usearray == null ) {           // 자동
        //
    } else if ( this.usearray[root.nodeName] ) {    // 지정된 태그
        ret = [ ret ];
    }

    var json = {};
    json[root.nodeName] = ret;                      // 루트 노드 이름
    return json;
};

//  DOM에서 JSON으로 바꾼다

JKL.ParseXML.JSON.prototype.parseElement = function ( elem ) {
    // debug.print( "nodeType: "+JKL.ParseXML.MAP_NODETYPE[elem.nodeType]+" <"+elem.nodeName+">" );

    //  COMMENT_NODE

    if ( elem.nodeType == 7 ) {
        return;
    }

    //  TEXT_NODE CDATA_SECTION_NODE

    if ( elem.nodeType == 3 || elem.nodeType == 4 ) {
        var bool = elem.nodeValue.match( /[^\u0000-\u0040]/ );
        if ( bool == null ) return;     // ignore white spaces
        // debug.print( "TEXT_NODE: "+elem.nodeValue.length+ " "+bool );
        return elem.nodeValue;
    }

    var retval;
    var cnt = {};

    //  속성을 파싱한다

    if ( elem.attributes && elem.attributes.length ) {
        retval = {};
        for ( var i=0; i<elem.attributes.length; i++ ) {
            var key = elem.attributes[i].nodeName;
            if ( typeof(key) != "string" ) continue;
            var val = elem.attributes[i].nodeValue;
            if ( ! val ) continue;
            if ( typeof(cnt[key]) == "undefined" ) cnt[key] = 0;
            cnt[key] ++;
            this.addNode( retval, key, cnt[key], val );
        }
    }

    //  자식 노드를 파싱한다(회귀적)

    if ( elem.childNodes && elem.childNodes.length ) {
        var textonly = true;
        if ( retval ) textonly = false;        // 속성이 존재한다
        for ( var i=0; i<elem.childNodes.length && textonly; i++ ) {
            var ntype = elem.childNodes[i].nodeType;
            if ( ntype == 3 || ntype == 4 ) continue;
            textonly = false;
        }
        if ( textonly ) {
            if ( ! retval ) retval = "";
            for ( var i=0; i<elem.childNodes.length; i++ ) {
                retval += elem.childNodes[i].nodeValue;
            }
        } else {
            if ( ! retval ) retval = {};
            for ( var i=0; i<elem.childNodes.length; i++ ) {
                var key = elem.childNodes[i].nodeName;
                if ( typeof(key) != "string" ) continue;
                var val = this.parseElement( elem.childNodes[i] );
                if ( ! val ) continue;
                if ( typeof(cnt[key]) == "undefined" ) cnt[key] = 0;
                cnt[key] ++;
                this.addNode( retval, key, cnt[key], val );
            }
        }
    }
    return retval;
};

JKL.ParseXML.JSON.prototype.addNode = function ( hash, key, cnts, val ) {
    if ( this.usearray == true ) {              // 배열로
        if ( cnts == 1 ) hash[key] = [];
        hash[key][hash[key].length] = val;      // 넣기
    } else if ( this.usearray == false ) {      // 스칼라로
        if ( cnts == 1 ) hash[key] = val;       // 첫번째 형제만
    } else if ( this.usearray == null ) {
        if ( cnts == 1 ) {                      // 첫번재 형제
            hash[key] = val;
        } else if ( cnts == 2 ) {               // 두번째 형제
            hash[key] = [ hash[key], val ];
        } else {                                // 세번째 형제와 그 다음
            hash[key][hash[key].length] = val;
        }
    } else if ( this.usearray[key] ) {
        if ( cnts == 1 ) hash[key] = [];
        hash[key][hash[key].length] = val;      // 넣기
    } else {
        if ( cnts == 1 ) hash[key] = val;       // 첫번째 형제만
    }
};
