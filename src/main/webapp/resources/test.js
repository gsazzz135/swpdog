const BNO = 6;
const REGIST_URL = "/replies";
const LIST_URL = "/replies/" + BNO + "/";

let workingRno = 0,		// 전역변수로 선언(내가 선택한 댓글의 해당번호를 담아둔다)
	workingText ="",
	$workingReply = null;

// 댓글목록
function listPage(page){
	page = page || 1;
	
	sendAjax(LIST_URL + page, (isSuccess, res) => {
		console.log("listPage:res>>", res);
		
		if(isSuccess){
			let data = res.list,
				pageMaker = res.pageMaker;
			
			let str ="";
			data.forEach(
					(d) => {
						str += `<li data-rno= "${d.rno}" class= "replyLi">
							${d.rno}<span>${d.replytext}</span>
							<button onclick="modClicked(this)" class="point">수정</button>
							</li>`;
					}
			);
			$('#replies').html(str);
			
			printPage(pageMaker);
		}
	});
}

// 댓글페이지
function printPage(pm){
	console.debug("pageMaerk:", pm);
	let str = '',
	tmpPage = 0;
	if(pm.prev){
		tmpPage = pm.startPage - 1;
		str += `<li><a href="#" onclick="listPage(tmpPage)" data-page="${tmpPage}">&lt;&lt;</a></li>`;
	}
	let currentPage = pm.cri.page;
	for(let i = pm.startPage; i <= pm.endPage; i++ ){
		let activeClass = currentPage === i ? 'active' : "";
		str += `<li><a href="#" onclick="listPage(${i})" class="${activeClass}" data-page="${i}">${i}</a></li>`;
	}
	if(pm.next){
		tmpPage = pm.endPage + 1
		str += `<li><a href="#" onclick="listPage(tmpPage)" data-page="${tmpPage}">&gt;&gt;</a></li>`;
	}
	$('ul.pagenation').html(str);
}

let replyPage = 1;
//페이지번호를 파라미터로 받음
function getPage(page){

	$.getJSON(page, function(data){
		printData(data.list, $("#replies"),$('#replies').html(str));
		printPaging(data.pageMaker, $(".pagenation"));
			
	});
}


//댓글 등록
function regist(){
    
    let jsonData = getValidData($('#newReplyWriter'), $('#newReplyText'));
    if(!jsonData){
        return;
    }
    
    jsonData.bno = BNO;
    
    sendAjax(REGIST_URL, (isSuccess) => {
        if(isSuccess){
            alert("등록이 완료 되었습니다.");
            listPage();
            closeMod();
        }
    } , jsonData, 'POST');
}

//댓글창 등록할때 예외처리
function getValidData($replyer, $replytext){
	let errorFocus = null,
	replyer = $replyer.val(),
	replytext = $replytext.val(),
	errorMsg = "";						// 내용이없으므로 NULL상태임. 그래서 false가 나옴.
	
	if(!replyer){
		errorMsg = "작성자를 입력하세요.";
		$errorFocus = $replyer;
	} else if(!replytext){
		errorMsg = "내용을 입력하세요";
		$errorFocus = $replytext;
	}
	
	if(errorMsg){
		alert(errorMsg);
		$errorFocus.focus();
		return;				// 에러가 발생했을 경우 밑의 소스를 실행하면 안되므로 return해줘서 끝내준다.
	}
	
	return {replyer: replyer, replytext: replytext};
}


function sendAjax(url, fn, jsonData, method){
    let options = {
                        method: method || 'GET',
                        url: url,
                        contentType: "application/json"
                   };
    //jsonData가 있을 때만 data : JSON.stringify(jsonData) 추가
    if(jsonData){
        options.data = JSON.stringify(jsonData);
        console.log(options);
    }
    
    $.ajax(options).always((responseText, statusText, ajaxResult) =>{
        let isSuccess = statusText === 'success'; //ajax 호출 성공 여부
        fn(isSuccess, responseText);
        if(!isSuccess){
            alert("오류가 발생하였습니다. (errorMessage:" + responseText + ")");
        }
    })
}

function showJson(){
    let result = [];			// Json은 배열이형식이므로 Json 결과값을 담을 변수 선언
    $('#replies li').each ( (idx, li) => {		// each 함수를 이용해서 배열의 순서를 주고 내가 선택한 li데이터값을 가르킬 수 있다
        let $li = $(li),		// li를 제이쿼리도 감싸준다.(뒤에 함수들을 사용 할 수 있도록)
            rno = $li.data('rno')
            replyer = $li.data('replyer')
            replytext = $li.text()
        	//g는 전역이라는 뜻으로 전체를 의미한다. 안붙이면 앞부분의 \n\r\t만 replace 된다. trim은 필요한 데이터만 출력 할 수 있도록 앞뒤에 필요없는 부분을 없애준다
        result.push({
            rno: rno,
            replyer: replyer,
            replytext: replytext
        })
    })
    result = JSON.stringify(result, null, '  ');	//JSON은 상수이기 때문에 대문자, stringfy함수는 public static함수이기 때문에 바로 사용 가능
    console.log(result);
}

var trunkSpace = function(text){
	if(!text){ 
		return "";
	}
	return text.replace(/[\n\r\t]/g,'').trim(); 
}

//댓글수정
function editReply(){
	if(!confirm("수정하시겠습니까?")) return;
	
	let editeReplytext = $('#replytext').val();		//editeReplytext에 새로 입력한 값<$('#replytext').val()>을 집어넣는 변수
		
	if(editeReplytext == workingText){
		alert("내용이 수정되지 않았습니다.");
		return;
	}
	
	
	let jsonData = {replytext : editeReplytext};	
	sendAjax("/replies/" + workingRno, (isSuccess, res) =>{
		if(isSuccess){
            alert("수정되었습니다.");
//            listPage();
            $workingReply.find('span').text(editeReplytext);
            closeReply();
        } else {
        	console.debug("Error on editReply>>", res);
        }
	}, jsonData, 'PUT');
	
}

//댓글수정창 가운대로
function movCenterModDiv(){
    $modDiv = $('#modDiv');
    $modDiv.css({'margin-left':$modDiv.width()/2*(-1)});
    $modDiv.css({'margin-top':$modDiv.height()/2*(-1)});
}

//댓글수정창 닫기
function closeReply(){
	workingRno = 0;
	$('#modRno').text('');
	$('#modDiv').hide('slow');
}

function modClicked(btn){
	console.log("bbbbbbbbbbbbbtn>>",btn);
    let $btn = $(btn),
        $reply = $btn.parent(),
        rno = $reply.data('rno'),
    	replytext = trunkSpace($reply.find('span').text());
    console.log("QQQQQQQQQQQQQQQQ>>", $reply, rno);
    $('#modDiv').show('slow');
    $('#replytext').val(replytext);
    $('#modRno').text(rno);
    workingRno = rno;		// 댓글수정창이 열릴때 workingRno에 댓글번호를 담음
    workingText = replytext;
    $workingReply = $reply;
}


//댓글삭제기능
function removeReply(){
	if(!confirm("삭제하시겠습니까?")) return;
	
	sendAjax("/replies/" + workingRno, (isSuccess, res) => {	//	/replies/{rno}이기 때문에 modClicked에 workingRno에 rno를 담아줬으므로 + workingRno해줌 
        if(isSuccess){
            alert("삭제되었습니다.");
//            listPage();
            
            closeReply();
            getPage("/replies/" + bno + "/" + replyPage);
        } else {
        	console.debug("Error on removeReply>>", res);
        }
    } , null, 'DELETE');
}



listPage();