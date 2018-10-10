function registerReply(){
    const BNO = 6;
    const REGIST_URL = "/replies";
    let $replyer = $('#newReplyWriter'),
        $replytext = $('#newReplyText'),
        $errorFocus = null;
    
    let replyer = $replyer.val(),
        replytext = $replytext.val(),
        errorMsg = "";
    
    if(!replyer){
        errorMsg = "작성자를 입력하세요.";
        $errorFocus = $replyer;
    }else if(!replytext){
        errorMsg = "내용을 입력하세요";
        $errorFocus = $replytext;
    }
    
    if(errorMsg){
        alert(errorMsg);
        $errorFocus.focus();
        return;
    }
    
    let jsonData = {
            bno      : BNO,
            replyer  : replyer,
            replytext: replytext
    };
    let sender = $.ajax({
        method: 'POST',
        url: REGIST_URL,
        contentType: "application/json",
        data: JSON.stringify(jsonData),				//stringify: JSON 타입을 String으로 변경
    })
    
    sender.always((responseText,statusText,ajaxResult) => {
        
    	if(responseText === 'ReplyRegisterOK'){
            alert("등록 되었습니다.");
            listAll();
        }else{
        	
            alert("오류가 발생하였습니다! errorMessgae + " + responseText + ")");
        }
    })
}

/*function listAll(){
var bno = 6,
listUrl = "/replies/all/" + bno;
$.getJSON(listUrl, function(data, b, c){
    console.log(">> data=", data, ", b=", b, ", c=", c);
    c.always(function(){
        console.log(c.status);
    })
});    
}
*/

function listAll(){
    const bno = 6;
    const page = 1;
    listUrl = "/replies/all/" + bno + "/" + page;
    $.getJSON(listUrl, (data, b, c) => {
        console.log(">> data=", data, ", b=", b, ", c=", c);
        //$(data).each((a,b) => {console.log(a,b)});
        
        let str = ""; //바뀔 수 있음
        data.list.forEach(
            (d) => {
//                str += '<li data-rno="' + d.rno + '" class="replyLi">' + d.replytext + '</li>';
                str += `<li data-rno="${d.rno}" class="replyLi">
                			${d.replytext}
                			<button onclick=modClicked(this) class="point">수정</button>
                		</li>`;
            }
        );
        $('#replies').html(str);
       
    });
}

function modClicked(btn){
	console.log("bbbbbbbbbbn>>", btn);
	let $btn = $(btn),
		$reply = $btn.parent(),
		rno = $reply.data('rno');
	console.log("qqqqqqqqqqqqq>>", $btn, $reply, rno);
}


//-------------------------------------------
$(function(){
    //$('#h2-title').on('click',listAll);
    listAll();
    $('#btnReplyAdd').click(function(){
        registerReply();
    })
    
});

function showData(){
	let resultJson = []; 		//Json은 배열이형식이므로 Json 결과값을 담을 변수 선언
	$('#replies li').each( (idx, li) =>{		// each 함수를 이용해서 배열의 순서를 주고 내가 선택한 li데이터값을 가르킬 수 있다
		let $li = $(li),		// li를 제이쿼리도 감싸준다.(뒤에 함수들을 사용 할 수 있도록)
			rno = $li.data('rno'),
			replyer = $li.data('replyer'),
			replytext = $li.text().replace(/[\n\r\t]/g, '').trim(); 
			///g는 전역이라는 뜻으로 전체를 의미한다. 안붙이면 앞부분의 \n\r\t만 replace 된다. trim은 필요한 데이터만 출력 할 수 있도록 앞뒤에 필요없는 부분을 없애준다
		resultJson.push({
			rno : rno,
			replyer : replyer,
			replytext : replytext
		});
	});
	console.log(JSON.stringify(resultJson, null, '  ')); //JSON은 상수이기 때문에 대문자, stringfy함수는 public static함수이기 때문에 바로 사용 가능
}

function makeCenterModDiv(){
	let $mod = $('#modDiv')
	
}
