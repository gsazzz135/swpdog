const REGIST_URL = "/replies";
let gBno = 0,
	gPage = null,
	gIsEdit = false,
	gRno = 0,
	gReplytext = null;

// 댓글목록
function listPage(page, bno){
	console.log("this>>", this);
	event.preventDefault();
	gBno = bno||gBno;
	page = page || gPage ||1;
	gPage = page;
	let url = "/replies/" + gBno + "/" + page;
	console.debug("url=", url);
	sendAjax(url, (isSuccess, res) => {
		console.log("listPage:res>>", res);
		
		if(isSuccess){
			res.pageData = makePageData(res.pageMaker);
			res.currentPage = page;
			renderHbs("replies", res);			
		}
	});
}

// 댓글페이지
function makePageData(pm){
	console.debug(pm);
	let pageData = {
					prevPage : 0,
					pages : [],
					nextPage : 0
					}
	if(pm.prev){
		pageData.prevPage = pm.startPage - 1;
	}
	
	for(let i = pm.startPage; i <= pm.endPage; i++){
			pageData.pages.push(i);
	}
	
	if(pm.next){
		pageData.nextPage = pm.endPage + 1;
	}
	pageData.delim = '|';
	return pageData;
}

let replyPage = 1;
//페이지번호를 파라미터로 받음
function getPage(page){

	$.getJSON(page, function(data){
		printData(data.list, $("#replies"),$('#replies').html(str));
		printPaging(data.pageMaker, $(".pagenation"));
			
	});
}

//수정되었나 확인
const checkEdit = () => {
	let $replyer = $('#replyer'),
		$replytext = $('#replytext'),
		$btn = $('#btnModReply'),
		replyer = $replyer.val(),
		replytext = $replytext.val();
	
//	console.debug("QQQ>>>>", replytext, event, event.keyCode)	//event.keyCode 콘솔창을 통해서 입력한 키의 코드를 알수 있음
	if(event.keyCode === 13){
		console.debug("***************************")
	}
	
	if(replyer && replytext && !(replytext == gReplytext)){
		$btn.show();
	} else{
		$btn.hide();
	}
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
        let isSuccess = statusText === 'success'; //ajax 호출 성공 여부 "=="같은지체크 / "==="타입까지 같은지 체크
        fn(isSuccess, responseText);
        if(!isSuccess){
            alert("오류가 발생하였습니다. (errorMessage:" + responseText + ")");
        }
    })
}

//댓글수정창 닫기
function closeReply(){
	gRno = 0;
	$('#myModal').modal('hide');
	gReplytext = null;
}

function editReply(rno, replyer, replytext){
	event.preventDefault();
	gIsEdit = !!rno;		// gIsEdit = ron; <- 이렇게 하지 않은 이유는 "!!" 이걸 써줘야 boolean 형식으로 인식
	gRno = rno;
	renderHbs("myModal", {
		gIsEdit : gIsEdit,
		replyer : replyer,
		replytext : replytext
	});
	
	$('#myModal').modal();
	$('#btnModReply').hide();
}

function save(){
	
	let jsonData = getValidData($('#replyer'), $('#replytext'));
	let url = gIsEdit ? "/replies/" + gRno : "/replies/",
		method = gIsEdit ? "PUT" : "POST";
	
	if(!gIsEdit){
		jsonData.bno = gBno;
	}
	
	sendAjax(url, (isSuccess, res) =>{
		if(isSuccess){
            let resultMsg = gIsEdit ? gRno + "번 댓글이 수정되었습니다." : "댓글이 등록되었습니다"
			alert(resultMsg);
            listPage(gIsEdit ? gPage : 1);
            closeReply();
        } else {
        	console.debug("Error on editReply>>", res);
        }
	}, jsonData, method);
	
}

//댓글삭제기능
function removeReply(){
	if(!confirm("삭제하시겠습니까?")) return;
	
	sendAjax("/replies/" + gRno, (isSuccess, res) => {	//	/replies/{rno}이기 때문에 modClicked에 workingRno에 rno를 담아줬으므로 + workingRno해줌 
        if(isSuccess){
            alert("삭제되었습니다.");
            listPage();
            closeReply();
        } else {
        	console.debug("Error on removeReply>>", res);
        }
    } , null, 'DELETE');
}

const readReply = rno => new Promise((resolves, reject) => {
	console.log("QQQ:readReply!!");
	sendAjax("/replies/" + rno, (isSuccess, res) => {
		console.debug("readdddd",res);
		if(isSuccess){
			resolves(res);
		}else{
			reject(Error(res));
		}
	});
	
});