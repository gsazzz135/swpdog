QUnit.test("Hello test", (assert) => {
	assert.equal(1, 1, "통과!!!!!!");
	
	assert.ok(1 == '1', "pass OK!!!!!!!!!");
	
	assert.strictEqual(1 == '1' ,"Pass StrictEqual!!!!")
	
	let arr1 = [1, 2, 3],
		arr2 = [1, 2, 3],
		arr3 = [1, 2, 4],
		arr4 = [3, 4, 5];
		
	assert.equal(arr1, arr2, "Pass 1=2");
	assert.strictEqual(arr1, arr2, "pass1 s = 2");
	assert.deepEqual(arr1, arr2, "pass1 d = 2");
	assert.deepEqual(arr3, arr2, "pass3 d = 2")
});
