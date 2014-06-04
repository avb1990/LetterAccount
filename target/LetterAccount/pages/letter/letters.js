$(document).ready(function() {

	$("#file").change(function() {
		var fileName = this.value;
		var index = fileName.search(/\.[a-z]+/gi);
		if(index!==-1)		
			$("#fileType").attr("value",fileName.substring(index,fileName.length));
		$("#submit").click();
	});
	$("#chooseFileButton").click(function(){
		$("#file").click();
	});
});
