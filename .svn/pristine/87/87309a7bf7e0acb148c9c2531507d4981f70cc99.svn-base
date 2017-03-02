$(function() { 
	loaddictByParent("area");
	//loaddictByParent("pollResType");
	var data = {};
	var applyTable = $('#apply_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		info:false,
		ajax: {
			url : "../outFall/searchAllOutFallList",
			type:'post',
			data:data
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' id='" + data +"' name='qtype' value='" + data +"'>";
		        	 }
		         },
		         {'data':'rivername'},
		         {'data':'area'},
		         {'data':'leftorrightbank'},		         
		         {'data':'outfallcode'},
		         {'data':'secondaryunit'},
		         {'data':'position'},
		         {'data':'outfallshape'},
		         {'data':'polldescription'},
		         {'data':'rectificationmeasures'},
		         {'data':'drainageTo'},
		         {'data':'therectificationresponsibilityunitStr'},
		         {'data':'createTimeStr'},
		         {'data':'id',
		        	 'render':function(data){
		        		 return '<a href="#" onclick="showOutFallInfo(\''+data+'\');">查看详情</a>';
		        	 }}
		         ]
	});
	
	
	$('#searchBtn').on('click',function(){
		var riverSearch = $("#riverSearch").val();
		var areaSearch = $("#areaSearch").val();
		var outfallshape = $("#outfallshape").val();
		var param={
				'rivername':riverSearch,
				'area':areaSearch,
				'outfallshape':outfallshape
		}
		applyTable.settings()[0].ajax.data=param;
		applyTable.ajax.reload();
	});
	
	
	$('#btn_upload').on('click',function(){
		$("#dataUpload").modal('toggle');
	});
	
	// 修改操作
	/*$('#btn_modify').on('click', function() {
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
					url : "../outFall/findById",
					data :  {id:appname}, 
					success : function(data) {
						var data = data.data;
						var river = data.river;
						console.log(data.data);
						$("#dataid").val(data.id);
						$("#riverid").val(river.id);
						$("#riverCode").val(data.riverCode);
						$("#riverName").val(data.riverName);
						$("#leftRight").val(data.leftRight);
						$("#partName").val(data.partName);
						$("#distMgrName").val(data.distMgrName);
						$("#distMgrOrg").val(data.distMgrOrg);
						$("#distMgrTel").val(data.distMgrTel);
						$("#distMgrPosition").val(data.distMgrPosition);
						$("#streetMgrName").val(data.streetMgrName);
						$("#streetMgrOrg").val(data.streetMgrOrg);
						$("#streetMgrTel").val(data.streetMgrTel);
						$("#streetMgrPosition").val(data.streetMgrPosition);
						$("#villageMgrName").val(data.villageMgrName);
						$("#villageMgrOrg").val(data.villageMgrOrg);
						$("#villageMgrTel").val(data.villageMgrTel);
						$("#villageMgrPosition").val(data.villageMgrPosition);
						$("#manageMgrName").val(data.manageMgrName);
						$("#manageMgrOrg").val(data.manageMgrOrg);
						$("#manageMgrTel").val(data.manageMgrTel);
						$("#manageMgrPosition").val(data.manageMgrPosition);
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
	});*/
	
	
	$('#uploadXlsFile').change(function(){
		var importFileName=$("#uploadXlsFile").val();
	    if(importFileName=="" ||importFileName==null ){
		   alert("文件不能为空，请选择文件!");
		   return false;
		}else{
			var d1=/\.[^\.]+$/.exec(importFileName);  
			 if(d1==".xls"||d1==".xlsx"){  
				 var formData = new FormData($("#upload_form")[0]);
				   $.ajax({
						type : "POST",
						url : "../outFallUpload/upload",
						cache: false,
						async: true,
						contentType: false,  
				        processData: false, 
						data :  formData, 
						dataType : "json",
						success : function(data) {
							if(data == true){
								$("#dataUpload").modal('toggle');
								$("#uploadXlsFile").val('');
								applyTable.ajax.reload();
							} else {
								alert("产品名称与导入文件中产品不相同，导入失败");
							}
						},
						error:function(data){
							alert("文件导入失败！");
						}
					});	   
			 }else{
				 alert("文件格式不对，请选择xls格式文件！");  
			 }
			}
	});
	
});

function loaddictByParent(dict){
	var requestData = {};
	var opt = "";
	var search = "";
	if(dict == "area"){
		requestData = {dict:'area'};
		opt = "areaSearch";
	}
	if(dict=='pollResType'){
		requestData = {dict:'pollResType'};
		opt = "pollResTypeSearch";
	}
	$.ajax({
		type:'post',
		url : "../response/findDict",
		data :  requestData, 
		dataType : "json",
		success:function(data){
			$("#"+opt).empty().append("<option></option>");
			$.each(data, function(i, value){
				$("#"+opt).append("<option>"+value.name+"</option>");
			});
		}
	});
}


function showOutFallInfo(id){
	$("#dictInfo").modal('toggle');
	
	$.ajax({
		type : "POST",
		url : "../outFall/findById",
		data :  {'id':id}, 
		success : function(data) {
			$("#info_area").html(data.area);
			$("#info_coordinate").html(data.coordinate);
			$("#info_createTimeStr").html(data.createTimeStr);
			$("#info_drainageTo").html(data.drainageTo);
			$("#info_leftRight").html(data.leftorrightbank);
			$("#info_position").html(data.position);
			$("#info_riverName").html(data.rivername);
			
			
			$("#info_outfallcode").html(data.outfallcode);
			$("#info_outfallshape").html(data.outfallshape);
			$("#info_outfallsize").html(data.outfallsize);
			$("#info_outfalltype").html(data.outfalltype);
			
			$("#info_rectificationmeasures").html(data.rectificationmeasures);
			$("#info_secondaryunit").html(data.secondaryunit);
			$("#info_therectificationdataonsibilityunit").html(data.therectificationdataonsibilityunitStr);
			$("#info_timeofcompletion").html(data.timeofcompletion);
			$("#info_streetname").html(data.streetname);
			$("#info_streetmanager").html(data.streetmanager);
			$("#info_village").html(data.village);
			$("#info_villagemanager").html(data.villagemanager);
			$("#info_polldescription").html(data.polldescription);
			$("#info_remark").html(data.remark);
		}
	});
}
