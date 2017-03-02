var lengthCoast = "";
var riverlength = "";
$(function() {  
	$('#imgShow').on('hide.bs.modal', function () {
		setTimeout(function(){
			$("body").attr("style","text-align:center;");
		}, 500);

	});
	loadDict("area");//加载行政区域字典
	loadDict("grade");//加载河道等级字典
	var applyTable = $('#apply_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		info:false,
		ajax: {
			url : "../sign/searchSign",
			type:'post',
			data:{}
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' id='" + data +"' name='qtype' value='" + data +"'>";
		        	 }
		         },
		         {'data':'signinname'},
		         {'data':'area'},
		         {'data':'reachname'},
		         {'data':'grade',
		        	 'render': function ( data, type, full, meta ) {
		        		 if(data == "1"){
		        			return "重要河道";
		        		 }else if(data == '2'){
		        			return "一般河道";
		        		 }else{
		        			 return data;
		        		 }
		        	 }
		         },
		         {'data':'signinDate'},
		         {'data': 'id',
		        	 'render': function ( data, type, full, meta ) {
		        		 return "<p class='yl look' fileid='"+data+"' onclick=\"look('"+data+"');\">查看详情</p>";
		        	 }
		         }
		         ]
	});
	// 全选方法
	$('#checkAll').on('click', function() {
		$("input[name='qtype']").prop('checked', $(this).prop('checked'));
	});

	$('#searchBtn').on('click',function(){
		var area = $("#areaSearch").val();
		var grade = $("#gradeSearch").val();
		var river = $("#riverSearch").val();
		var status;
		if(grade == "重要河道"){
			status="1";
		}else if(grade == '一般河道'){
			status="2";
		}
		var param={
				area:area,
				grade:status,
				reachname:river
		}
		applyTable.settings()[0].ajax.data=param;
		applyTable.ajax.reload();
	});



});
//加载行政区域
function loadDict(dict){
	var requestData = {};
	var opt = "";
	var search = "";
	if(dict == "area"){
		requestData = {dict:'area'};
		opt = "area1";
		search = "areaSearch";
	}else{
		requestData = {dict:'grade'};
		opt = "grade1";
		search = "gradeSearch";
	}
	$.ajax({
		type:'post',
		url : "../response/findDict",
		data :  requestData, 
		dataType : "json",
		success:function(data){
			$("#"+opt).empty().append("<option></option>");
			$("#"+search).empty().append("<option></option>");
			$.each(data, function(i, value){
				$("#"+opt).append("<option>"+value.name+"</option>");
				$("#"+search).append("<option>"+value.name+"</option>");
			});
		}
	});
}
//查看详情
function look(id){
	$("#lookSign").modal("toggle");
	$.ajax({
		type:'post',
		url : "../sign/findById",
		data :  {id:id}, 
		dataType : "json",
		success:function(data){
			$("#looksigninname").text(data.signinname);
			$("#lookarea").text(data.area);
			$("#lookreachname").text(data.reachname);
			var grade = data.grade;
			if(grade == "1"){
				$("#lookgrade").text("重要河道");
			}else if(grade == "2"){
				$("#lookgrade").text("一般河道");
			}
			$("#looksigninDate").text(data.signinDate);
			var position = data.signinposition;
			if(position == "" || position == "null" || position == null){
				position = "";
			}
			$("#looksigninposition").text(position);
			var attchIds = data.attachIds;
			var html="";
			for(var i=0;i<attchIds.length;i++){
				html += '<img style="width:250px;height:100px;" src="../weixin/export/'+attchIds[i]+'">';
			}
			$("#imgAttchLook").empty().append(html);
			$("img").click(function(){
				var src = $(this).attr("src");
				$("#lookSign").modal('toggle');
				$("#imgShow").modal("toggle");
				$(".bigImg").html("<img  style='width:400px;height:350px;' src="+src+"></img>");
			});
		}
	});
}
