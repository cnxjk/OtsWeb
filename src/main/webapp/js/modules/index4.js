/**
 * 项目JS主入口 以依赖layui的layer和form模块为例
 */
layui.define([ 'layer', 'form', 'element' ], function(exports) {
	var layer = layui.layer, form = layui.form, element = layui.element;
	/*---------------------------------------------*/
	// 音频转码worker
	let recorderWorker = new Worker('./js/transformpcm.worker.js')
	// 记录处理的缓存音频
	let buffer = []
	let AudioContext = window.AudioContext || window.webkitAudioContext
	let notSupportTip = '请试用chrome浏览器且域名为localhost或127.0.0.1测试'
	navigator.getUserMedia = navigator.getUserMedia || navigator.webkitGetUserMedia || navigator.mozGetUserMedia || navigator.msGetUserMedia
	recorderWorker.onmessage = function (e) {
	  buffer.push(...e.data.buffer)
	}

	/*------------------语音翻译-------------------*/
	
	/*===================语音翻译===============*/
	var interval;
	function startTime() {
		var timesRun = 0;
		interval = setInterval(function() {
			timesRun += 1;
			var timenumber = timesRun * (1 / 6) * 10;
			element.progress("progress", timenumber.toString() + "%");
			console.log(timenumber)
			if (timesRun === 60) {
				clearInterval(interval);
			}
		}, 1000);
	}

	$("#microphone img").mousedown(
			function(event) {
				startTime();
				$("#microphone img").attr("class",
						"layui-anim layui-anim-scale layui-anim-loop layui-btn ");
			});
	$("#microphone img").mouseup(function(event) {
		clearInterval(interval);
		$("#microphone img").attr("class", "layui-anim layui-anim-scale layui-btn ");
	});
	exports('index4', {}); // 注意，这里是模块输出的核心，模块名必须和use时的模块名一致
});