var lengthCoast = "";
var riverlength = "";
$(function() {  
	loadRole();//根据权限加载行政区域
	loadDict("grade");//加载河道等级字典
	var data={};
	var area=$("#hide_area").val();
	var role=$("#hide_role").val();
	if(role=="areamanager"){
		data={area:area};
	}
	/**
	 * {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' id='" + data +"' name='qtype' value='" + data +"'>";
		        	 }
		         },**/
	var applyTable = $('#apply_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		info:false,
		ajax: {
			url : "../waterwx/searchQuestionItemAll",
			type:'post',
			data:data
		},
		columns:[
		         {'data':'status',
		        	 'render': function ( data, type, full, meta ) {
		        		 if(data == '0'){
		        			 return "已回复投诉";
		        		 }else if(data == '2'){
		        			 return "<font color='#EEAD0E'>已受理投诉</font>";
		        		 }else if(data == '3'){
		        			 return "<font color='#7AC5CD'>无效投诉</font>";
		        		 }else if(data == '5'){
		        			 return "<font color=''>完结投诉</font>";
		        		 }else{
		        			 return "<font color='#FF0000'>待受理投诉</font>";
		        		 }
		        	 }
		         },
		         {'data':'code'},
		         {'data':'nickname'},
		         {'data':'questiontype'},
		         {'data':'area'},
		         {'data':'complainDate'},
		         {'data':'questioncontent'},
		         {'data':'questionposition'},
		         {'data':'cityName'},
		         {'data':'citycontent'},
		         {'data':'areaName'},
		         {'data':'areacontent'},
		         {'data': 'id',
		        	 'render': function ( data, type, full, meta ) {
		        		 	 return "<p class='yl details' fileid='"+data+"' onclick=\"details('"+data+"');\"><font color='#EEAD0E'>查看详情</font></p>" ;
		        		 }
		         }
		         ]
	});
	$('#searchBtn').on('click',function(){
		var area = $("#areaSearch").val();
		//var grade = $("#gradeSearch").val();
		var river = $("#riverSearch").val();
		var code = $("#search_code").val();
		var questiontype = $("#questiontype").val();
		var status;
		/*if(grade == "重要河道"){
			status="1";
		}else if(grade == '一般河道'){
			status="2";
		}*/
		
		var param={
				area:area,
				grade:status,
				river:river,
				code:code,
				questiontype:questiontype
		}
		applyTable.settings()[0].ajax.data=param;
		applyTable.ajax.reload();
	});
	// 全选方法
	$('#checkAll').on('click', function() {
		$("input[name='qtype']").prop('checked', $(this).prop('checked'));
	});

	// 新增操作
	$('#btn_add').on('click', function() { 
		// 清空所有的input
		$('#dictForm input').val("");

		$('#myModalLabel').text('新增河涌');		// 修改label
		$("#dictUpdate").modal('toggle');

		// 更换Action位置
		/* $('#dictForm').attr('action', '${ctx}/saveApp'); */

	});

	//问题类型
	$.ajax({
		type : "POST",
		url : "../waterwx/getquestiontype",
		data :  {}, 
		dataType : "json",
		async:false,
		success : function(data) {
			//$("#qtype").empty().append("<option>请选择</option>");
			$.each(data, function(i, value){
				//$("#qtype").append("<option >"+value.detail+"</option>");
				$("#selecttype11").append("<option>"+value.detail+"</option>");
				$("#questiontype").append("<option>"+value.detail+"</option>");
				
			});

		}

	});

	// 修改操作
	$('#btn_modify').on('click', function() {
		// 判断表格中是否有行被选中
		var appname;
		var check = $("input[name='qtype']").attr("checked");
		var num = 0;
		$('input[name="qtype"]').each(function(i) {
			if ($(this).prop('checked')) {
				appname = $(this).attr('value');
				num++;
				$.ajax({
					type : "POST",
					url : "../response/findById",
					data :  {id:appname}, 
					success : function(data) {
						var resp = data.resp;
						var river = data.river;
						console.log(resp.resp);
						$("#respid").val(resp.id);
						$("#riverid").val(river.id);
						$("#riverCode").val(resp.riverCode);
						$("#riverName").val(resp.riverName);
						$("#leftRight").val(resp.leftRight);
						$("#partName").val(resp.partName);
						$("#distMgrName").val(resp.distMgrName);
						$("#distMgrOrg").val(resp.distMgrOrg);
						$("#distMgrTel").val(resp.distMgrTel);
						$("#distMgrPosition").val(resp.distMgrPosition);
						$("#streetMgrName").val(resp.streetMgrName);
						$("#streetMgrOrg").val(resp.streetMgrOrg);
						$("#streetMgrTel").val(resp.streetMgrTel);
						$("#streetMgrPosition").val(resp.streetMgrPosition);
						$("#villageMgrName").val(resp.villageMgrName);
						$("#villageMgrOrg").val(resp.villageMgrOrg);
						$("#villageMgrTel").val(resp.villageMgrTel);
						$("#villageMgrPosition").val(resp.villageMgrPosition);
						$("#manageMgrName").val(resp.manageMgrName);
						$("#manageMgrOrg").val(resp.manageMgrOrg);
						$("#manageMgrTel").val(resp.manageMgrTel);
						$("#manageMgrPosition").val(resp.manageMgrPosition);
						$("#area1").val(river.area);
						if(river.grade == 1){
							$("#grade1").val("重要河道");
						}else if(river.grade == 2){
							$("#grade1").val("一般河道");
						}else{
							$("#grade1").val(river.grade);
						}
						$("#length").val(river.length);
						$("#width").val(river.width);
						$("#coverArea").val(river.coverArea);
						$("#start").val(river.start);
						$("#end").val(river.end);
						$("#remark").val(river.remark);
					}
				});
				return;
			}
		});
		if(num>1){
			alert("请只选择一条需要修改的数据！");
			return;
		}
		if (appname == null) {
			alert("请选择一条需要修改的数据！");
			return;
		}

		$('#myModalLabel').text('修改河道信息');		// 修改label
		$("#dictUpdate").modal('toggle');
	});

	// 删除操作
	$('#btn_del').on('click', function() {
		// 判断表格中是否有行被选中
		var appname;

		$('input[name="qtype"]').each(function(i) {
			if ($(this).prop('checked')) {
				appname += $(this).attr('value') + ";";
			}
		});

		if (appname == null) {
			alert("请选择一条需要删除的数据！");
			return;
		}
		else {
			if (confirm("确认删除选中的河道！？")) {
				$.ajax({
					type : "POST",
					url : "../response/deleteResponse",
					data :  {id:appname}, 
					success : function(data) {
						alert("删除成功");
						$("#dictUpdate").modal('toggle');
						window.location.reload();
					}
				});
			}

		}
	});
});
//点击地区下拉框时 obj 区分是搜索框，还是投诉受理页面
function loadArea(){
	//地区
	$.ajax({
		type : "POST",
		url : "../waterwx/findAllArea",
		data :  {}, 
		dataType : "json",
		success : function(data) {
			$.each(data, function(i, value){
				$("#area").append("<option class='areaOption'  >"+value+"</option>");
				$("#area1").append("<option class='areaOption'  >"+value+"</option>");
			});

		}

	});
}
//查看详情
function details(qId){
	//$("#answer_table tbody").empty();
	$("#dictUpdate").modal('toggle');
	$("#qId").val(qId);
	$.ajax({
		type : 'post',
		data : {'qId':qId},
		url : "../waterwx/getQuestionItem",
		success : function(data) {
			$("#code1").val(data.code);
			$("#selecttype11").val(data.questiontype);
			$("#questioncontent1").val(data.questioncontent);//问题描述
			$("#area1").val(data.area);
			$("#nickname").val(data.nickname);
			//$("#phone1").val(data.phone);
			$("#questionposition").val(data.questionposition);//问题位置
			//xpc增加的字段
			//$("#importance").text(data.importance);
			$("#ciryAssignedcode").text(data.ciryAssignedcode);
			$("#areaAssignedcode").text(data.areaAssignedcode);
			$("#remark").text(data.remark);
			var reachname = data.reachname;
			$("#id1").val(data.id);
			rivervalue = data.reachname;
			lrvalue = data.leftRight;
			reachvalue = data.partName;
			//areapersonname = data.areaPersonName;
			//streetpersonname = data.streetPersonName;
			//villagepersonname = data.villagePersonName;
			var attchIds = data.attachIds;
			var areaContent=data.areacontent;
			var citycontent=data.citycontent;
			var area = $("#area1").val();
			if(area != "请选择" && area != "" && area != null){
				//loadRiver(area,"river1");
				/*if(rivervalue != "请选择" && rivervalue !="" && rivervalue !=null){
					loadCoastAndReach(area,rivervalue,"coast1");
				}
				if(streetpersonname != "请选择" && streetpersonname !="" && streetpersonname !=null){
					getStreetPersonName(area,rivervalue);
				}
				if(villagepersonname != "请选择" && villagepersonname !="" && villagepersonname !=null){
					getVillagePerson1(area,rivervalue);
				}*/
			}
			$("#area1").on("change",function(){
				var areaValue = $("#area1").val();
				/*$("#river1").val("");
				
				loadRiver(areaValue,"river1");
				//清空左右岸以及河段input框
				$("#coast1").parent().find(".combo-input").val("");
				$("#reach1").parent().find(".combo-input").val("");
				$("#streetpersonname").val("");
				$("#villagepersonname").val("");*/
			});
			
			
			var html="";
			for(var i=0;i<attchIds.length;i++){
				html += '<img style="width:250px;height:100px;" src="../weixin/export/'+attchIds[i]+'">';
			}
			$("#imgAttch").empty().append(html);
			$("img").click(function(){
				var src = $(this).attr("src");
				$("#dictUpdate").modal('toggle');
				$("#imgShow").modal("toggle");
				$(".bigImg").html("<img  style='width:500px;height:350px;' src="+src+"></img>");
				//$('#imgAttch').lightBox();
				//$(this).style("height",$(this).style("height")==="100px"?"300px":"100px");
			});
			var x = data.x; // 经度，浮点数，范围为180 ~ -180。
			var y = data.y; // 纬度，浮点数，范围为90 ~ -90
			var ggPoint = new BMap.Point(x,y);

			//地图初始化
			var bm = new BMap.Map("allmap");
			bm.centerAndZoom(ggPoint, 15);
			bm.addControl(new BMap.NavigationControl());
			var location=y+","+x;
			//坐标转换完之后的回调函数
			translateCallback = function (data){
				if(data.status === 0) {
					var qsData = {'ak':"WPoOm3CViGGFjSZDkE2TPwsc",'location':data.points[0].lat+","+data.points[0].lng,'output':"json","pois":0};
					$.ajax({  
						type: "get",  
						async: false,  
						url: "http://api.map.baidu.com/geocoder/v2/",  
						dataType: "jsonp",
						data:qsData,
						success: function(data1){
							var address = data1.result.addressComponent;
							var label = address.district+address.street+address.street_number;
							var marker = new BMap.Marker(data.points[0]);
							bm.addOverlay(marker);
							var label = new BMap.Label(label,{offset:new BMap.Size(20,-10)});
							marker.setLabel(label); //添加百度label
							bm.setCenter(data.points[0]);
						},  
						error: function(){  
							alert('fail');  
						}  
					});
				}
			}
//市分派情况详细信息
			
			var htmlcity="";
			htmlcity=setRepayValue(citycontent,"市分派详情");
			$("#cityAttch").empty().append(htmlcity);
			//区受理情况详细信息
			
			var htmlarea="";
			htmlarea=setRepayValue(areaContent,"区受理详情");
			$("#areaAttch").empty().append(htmlarea);
			
			
			setTimeout(function(){
				var convertor = new BMap.Convertor();
				var pointArr = [];
				pointArr.push(ggPoint);
				convertor.translate(pointArr, 1, 5, translateCallback)
			}, 1000);
		},
		error:function(e){
			alert("error")	;
		}
	});
	
	
	
	/*var answer_table = $('#answer_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		destroy:true,
		info:false,
		ajax: {
			url : "../waterwx/searchAnswer",
			type:'post',
			data:{'questionId':qId}
		},
		
	});*/
}
//市分派详情
function setRepayValue(json,title) {
  
        json = eval('(' + json + ')');
        //{"count":0,"list":[]}
        var html = "";
        html += '<label for="questioncontent" class="control-label  col-lg-2">'+title+'</label>';
        if (json.count != 0) {
            for (var i = 0; i < json.count; i++) {
                var obj = json.list[i];
               // <label for="questioncontent" class="control-label  col-lg-2">备注</label>
                //<textarea id="remark" name="remark" style="width:80%;height:30px;"></textarea>
               if(json.list[i]!=null){
                	html +=  ""+json.list[i].content ;
                }
            }
        }
        return html;
}
function save(){
	var grade = $("#grade1").val();
	var area = $("#area1").val();
	if(grade == "重要河道"){
		grade = "1";
	}else if(grade == "一般河道"){
		grade = "2";
	}else{
		grade = "3";
	}
	$("#grade").val(grade);
	$("#area").val(area);
	if (confirm("确认保存？")) {
		$.ajax({
			type : "POST",
			url : "../response/saveResponse",
			data :  $("#dictForm").serialize(), 
			success : function(data) {
				$("#dictUpdate").modal('toggle');
				alert("保存成功");
				window.location.reload();
			}
		});


	} else {

		return false;

	}
}
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

function loadRole(){
	var area=$("#hide_area").val();
	var role=$("#hide_role").val();
	if(role==null||role==''||role!="areamanager"){
		loadDict("area");
	}else{
		$("#area1").append("<option>"+area+"</option>");
		$("#areaSearch").append("<option>"+area+"</option>");
		$('#dictForm input').val("");
		$('#area').val(area);
	}
	
}



