$(function(){
	loaddictByParent("area");
	var applyTable = $('#apply_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		info:false,
		ajax: {
			url : "../publicSignBoardInfo/searchAll",
			type:'post'
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' id='" + data +"' name='qtype' value='" + data +"'>";
		        	 }
		         },
		         {'data':'riverName'},
		         {'data':'leftOrRightBank'},
		         {'data':'areaName'},		         
		         {'data':'partName'},
		         {'data':'partNameLength'},
		         {'data':'boardPosition'},
		         {'data':'boardCode'},
		         
		         {'data':'distMgrName'},
		         {'data':'streetMgrName'},
		         {'data':'villageMgrName'},
		         {'data':'manageMgrName'},
		         {'data':'createTimeStr'},
		         {'data':'id',
		        	 'render':function(data){
		        		 return '<a href="#" onclick="showboardInfo(\''+data+'\');">查看详情</a>';
		        	 }}
		        ]
	});
	
	$('#searchBtn').on('click',function(){
		var areaName = $("#areaSearch").val();
		var riverName = $("#riverName").val();
		var boardPosition = $("#boardPosition").val();
		var param={
				'areaName':areaName,
				'riverName':riverName,
				'boardPosition':boardPosition
		}
		
		applyTable.settings()[0].ajax.data=param;
		applyTable.ajax.reload();
	});
	
	$('#btn_upload').on('click',function(){
		$("#dataUpload").modal('toggle');
	});
	
	$('#uploadXlsFile').change(function(){
		var importFileName=$("#uploadXlsFile").val();
	    if(importFileName=="" ||importFileName==null ){
		   alert("文件不能为空，请选择文件!");
		   return false;
		}else{
			var d1=/\.[^\.]+$/.exec(importFileName);  
			 if(d1==".xls"){  
				 var formData = new FormData($("#upload_form")[0]);
				   $.ajax({
						type : "POST",
						url : "../publicSignBoardUpload/upload",
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
								//window.location.href="${ctx}/main/serviceList1?appname="+appname;
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

function showboardInfo(id){
	$("#dictInfo").modal('toggle');
	
	$.ajax({
		type : "POST",
		url : "../publicSignBoardInfo/findById",
		data :  {'id':id}, 
		success : function(data) {
			$("#info_riverCode").html(data.riverCode);
			$("#info_riverName").html(data.riverName);
			$("#info_leftRight").html(data.leftOrRightBank);
			$("#info_partName").html(data.partName);
			$("#info_partNameLength").html(data.partNameLength);
			$("#info_areaName").html(data.areaName);
			$("#info_boardCode").html(data.boardCode);
			$("#info_boardPosition").html(data.boardPosition);
			$("#info_createTimeStr").html(data.createTimeStr);
			$("#info_remark").html(data.remark);
			$("#info_distMgrName").html(data.distMgrName);
			$("#info_distMgrOrg").html(data.distMgrOrg);
			$("#info_distMgrTel").html(data.distMgrTel);
			$("#info_distMgrPosition").html(data.distMgrPosition);
			$("#info_streetMgrName").html(data.streetMgrName);
			$("#info_streetMgrOrg").html(data.streetMgrOrg);
			$("#info_streetMgrTel").html(data.streetMgrTel);
			$("#info_streetMgrPosition").html(data.streetMgrPosition);
			$("#info_villageMgrName").html(data.villageMgrName);
			$("#info_villageMgrOrg").html(data.villageMgrOrg);
			$("#info_villageMgrTel").html(data.villageMgrTel);
			$("#info_villageMgrPosition").html(data.villageMgrPosition);
			$("#info_manageMgrName").html(data.manageMgrName);
			$("#info_manageMgrOrg").html(data.manageMgrOrg);
			$("#info_manageMgrTel").html(data.manageMgrTel);
			$("#info_manageMgrPosition").html(data.manageMgrPosition);

		}
	});
}


