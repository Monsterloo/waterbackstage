var lengthCoast = "";
var riverlength = "";
$(function() {
	
	loadDictByParent("area","areaSearch");
	loadDictByParent("area","area");
	loadDictByParent("pollrivers","pollrivers");
	loadDictByParent("pollrivers","belongPollRiver");
	
	var applyTable = $('#apply_table').DataTable({
		processing: true,
		serverSide: true,
		lengthChange:false,
		paging: true,
		searching:false,
		ordering: false,
		info:false,
		ajax: {
			url : "../river/searchAllRiver",
			type:'post',
			data:{}
		},
		columns:[
		         {'data': 'id',
		        	 'render':function(data){
		        		 return "<input type='checkbox' id='" + data +"' name='qtype' value='" + data +"'>";
		        	 }
		         },
		         {'data':'riverCode'},
		         {'data':'riverName'},
		         {'data':'area'},
		         {'data':'start'},
		         {'data':'end'},
		         {'data':'belongPollRiverStr'},
		         {'data':'id',
		        	 'render':function(data){
		        		 return '<a href="#" onclick="showRiverInfo(\''+data+'\');">查看详情</a>';
		        	 }
		         }
		         ]
	});
	// 全选方法
	$('#checkAll').on('click', function() {
		$("input[name='qtype']").prop('checked', $(this).prop('checked'));
	});

	// 新增操作
	$('#btn_add').on('click', function() { 
		// 清空所有的input
		$('#dictForm input').val("");

		$('#myModalLabel').text('新增河道');		// 修改label
		$("#dictUpdate").modal('toggle');

		// 更换Action位置
		/* $('#dictForm').attr('action', '${ctx}/saveApp'); */

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
					url : "../river/findById",
					dataType:"json",
					data :  {id:appname}, 
					success : function(data) {
						$("#id").val(data.id);
						$("#riverCodeText").val(data.riverCode);
						$("#riverCode").val(data.riverCode);
						$("#riverCodeText").attr("disabled","disabled")
						$("#riverName").val(data.riverName);
						$("#area").val(data.area);
						$("#start").val(data.start);
						$("#end").val(data.end);
						$("#grade").val(data.grade);
						$("#length").val(data.length);
						$("#width").val(data.width);
						$("#coverArea").val(data.coverArea);
						$("#belongPollRiver").val(data.belongPollRiver);
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
					url : "../river/deleteRiver",
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
	
	
	$('#searchBtn').on('click',function(){
		var river = $("#riverSearch").val();
		var area = $("#areaSearch").val();
		var pollrivers = $("#pollrivers").val();
		
		var param={river:river,area:area,pollrivers:pollrivers};
		
		applyTable.settings()[0].ajax.data=param;
		
		applyTable.ajax.reload();
		
	});
	
	
});

function loadDictByParent(parent,eleId){
	var data = {"dict":parent};
	$.ajax({
		url:'../river/findDict',
		data:data,
		dataType : "json",
		async:false,
		success:function(data){
			$("#"+eleId).empty().append("<option value=''></option>");
			$.each(data, function(i, value){
				if(parent == "area"){
					$("#"+eleId).append("<option>"+value.name+"</option>");
				}else if(parent == "pollrivers"){
					$("#"+eleId).append("<option value='"+value.code+"'>"+value.name+"</option>");
				}else if(parent == "belongPollRiver"){
					$("#"+eleId).append("<option value='"+value.code+"'>"+value.name+"</option>");
				}
				
			});
		},
		error:function(data){
			alert(data);
		}
		
	});
	
	
}


function save(){
	$.ajax({
		type : "POST",
		url : "../river/saveRiver",
		data :  $("#dictForm").serialize(), 
		success : function(data) {
			$("#dictUpdate").modal('toggle');
			window.location.reload();
		}
	});
}


function showRiverInfo(id){
	
	$("#dictInfo").modal('toggle');
	$.ajax({
		type : "POST",
		url : "../river/findById",
		data :  {'id':id}, 
		success : function(data) {
			$("#info_id").html(data.id);
			$("#info_riverCode").html(data.riverCode);
			$("#info_riverName").html(data.riverName);
			$("#info_area").html(data.area);
			$("#info_start").html(data.start);
			$("#info_end").html(data.end);
			$("#info_grade").html(data.grade);
			$("#info_length").html(data.length);
			$("#info_width").html(data.width);
			$("#info_coverArea").html(data.coverArea);
			$("#info_belongPollRiver").html(data.belongPollRiverStr);
		}
	});
}

