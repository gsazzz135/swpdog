QUnit.test("Hello test", (assert) => {
	assert.equal(1, 1, "통과!!!!!!");
});

const pageMaker = {
		"displayPageNum": 10,
		"totalCount": 1866,
		"startPage": 1,
		"endPage": 10,
		"prev": false,
		"next": true,
		"cri": {
			"page": 1,
			"perPageNum": 10,
			"searchType": null,
			"keyword": null,
			"pageStart": 0
		}
};

const gTestMakePageResultExpect = {
		"prevPage" : 0,
		"pages" : [
			1,
			2,
			3,
			4,
			5,
			6,
			7,
			8,
			9,
			10
		],
		"delim" : "|",
		"nextPage" : 11
};

QUnit.test("Test ListPage() for First Page", (assert) => {
		let page = 1,
			url = "/replies/" + 294 + "/" + page;
		console.debug("url=", url);
		
		const done = assert.async();							// assert.async() -> assert를 모두 실행하고 done를 만나면 넘어가!
		
		sendAjax(url, (isSuccess, res) => {
			console.log("listPage:res>>", res);
			assert.ok(isSuccess, "AJAX Success!!!!!!");
			
			let isDone = false;
				try {
					if(isSuccess){
					res.currentPage = page;
					res.pageData = makePageData(res.pageMaker);
					
					assert.equal(res.list.length, 10, "Pass List Count 10!");		
					assert.deepEqual(res.pageData, gTestMakePageResultExpect, "makePageData() PASS!!");
					
					let firstReply = res.list[0];
					readReply(firstReply.rno).then(
							success => {
								assert.deepEqual(firstReply, success, "Reply Data OK!!");
								done();
								isDone = true;
							},
							error => {
								console.error("ERROR on READ!!", error);
								done();
								isDone = true;
							}
					);
					
					} else {
						throw new Error("list ajax fail!!");
					}
			} catch(Err) {
				if(!isDone){
					done();
				}
			}
		});
})
