var lengthCoast = "";
var riverlength = "";
var streetName = "";
var leftRigth = "";
var partName = "";
$(function() {  
	var menu = $("#menu").val();
	//导出按钮点击时
	$("#expCsv").click(function(){
		var content;
		$('input[name="exp"]').each(function(i) {
			if ($(this).prop('checked')) {
				content += $(this).attr('value') + ",";
			}
		});
		$("#contentExport").val(content);
		if (confirm("确认导出？")) {
			$("#exportDownload").submit();
			alert("正在导出，请稍后！");
		} 
	});
	//导出功能
	$(".export").click(function(){
		$("#exportChoose").modal('toggle');
		var code = $("#search_code").val();
		var qtype = $("#qtype").val();
		var data = $("#status").val();
		var status;
		if(data == "已回复投诉"){
			status=0;
		}else if(data == '已受理投诉'){
			status=2;
		}else if(data == '无效投诉'){
			status = 3;
		}else if(data == '待受理投诉'){
			status = 1;
		}else{
			status = 4;//未选择类型
		}
		status = menu;
		var river = $("#river").val();
		var coast = $("#coast").val();
		var reach = $("#reach").val();
		var startTime = $('#search_startTime').val();
		var area = $("#area").val();
		var endTime = $('#search_endTime').val();
		$('#allCheck').on('click', function() {
			$("input[name='exp']").prop('checked', $(this).prop('checked'));
		});
		$("#codeExport").val(code);
		$("#qtypeExport").val(qtype);
		$("#statusExport").val(status);
		$("#riverExport").val(river);
		$("#coastExport").val(coast);
		$("#reachExport").val(reach);
		$("#startExport").val(startTime);
		$("#endExport").val(endTime);
		$("#areaExport").val(area);
		var act = $("#exportDownload").attr("action");
		var o;
	});
});
