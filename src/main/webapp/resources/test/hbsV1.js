const Templates = {},
$htmls = $('script[type="text/x-handlebars-template"]');

$htmls.each( (idx, h) => {
	let tid = $(h).attr('id');
	Templates[tid] = Handlebars.compile($(h).html());
});

let renderHbs = function(tid, jsonData, tag){
	tag = tag || 'div';
	let $tmpl = $('#' + tid);
	let html = Templates[tid](jsonData);
	let cssClass = $tmpl.attr('class') || "";
	$tmpl.replaceWith(`<${tag} id="${tid}" class="${cssClass}">` + html + '</${tag}>')
};

moment.locale('ko');				// 전부다 한글로 나오게 한다
Handlebars.registerHelper('eq', function(a, b){
	return a == b;
});

Handlebars.registerHelper('fromNow', function(dt, option){
	return moment(dt).fromNow();
});

Handlebars.registerHelper('fullTime', function(dt, option){
	return moment(dt).format('lllll');
//	return moment(dt).locale('ko').format('lllll');    // local을 여기에 주면 fullTime만 한글로 나온다
});

Handlebars.registerHelper('transHtml', function(str){
	if(!str) return str;
	return str.replace(/[\r\n]/g, '<br>');
});