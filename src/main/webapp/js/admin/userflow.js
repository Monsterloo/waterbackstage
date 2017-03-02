var userflow = function(){
	var sysmanager = function(table) {
		var html = $.templates.mainTableHead.render(table);
		$("#main-content").html(html);
		$("#addButton").unbind("click");
	}
	var historyworkitems = function(pageno){
		var pagesize = 20;
		$.get("../workflow/history/"+pageno+"/pagesize/"+pagesize, function(data, status){
			if("success" == status){
				var table = {title : "已办事务列表"};
				table.thead = ["流程名称", "流程编号", "流程启动时间", "任务名称", "任务创建时间"];
				sysmanager(table);
				$("#sys_toolbar").remove();
				fillhistoryworkitems(data, pageno, pagesize);
			}
		});
	}
	
	var fillhistoryworkitems = function(data, pageno, pagesize){
		var html = $.templates.historytaskTable.render(data);
		$("#systbody").html(html);
		if(data.totalElements > 0){
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 1,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback : historyworkitems,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param:{}
			});
		}
	};
	var todotasklist = function(){
		$.get("../workflow/todolist", function(data, status){
			if("success" == status){
				var html = $.templates.mainNotTableHead.render("待办事务");
				$("#main-content").html(html);
				var majorWorks = data.majorWorks;/**待办**/
				html = "";
				if(majorWorks.totalElements != 0){
					html = $.templates.todoTaskList.render(majorWorks);
				}
				var aidantWorks = data.aidantWorks; /***协办***/
				if(aidantWorks.totalElements != 0){
					html = $.templates.todoTaskList.render(aidantWorks);
				}
				var works = data.ccWorks;/***送办***/
				if(works.totalElements != 0){
					html = $.templates.ccTaskList.render(works);
				}
				if(html == ""){
					$("#mainbody").html("暂无要处理的事务");
				}else{
					$("#mainbody").html(html);
				}
				/**处理任务**/
				$(".handler_task").click(function(event){
					event.stopPropagation(); 
					var type = parseInt(this.getAttribute("tasktype"));
					var index =parseInt(this.getAttribute("data-index"));
					var row, url;
					if(type == 0){
						row = majorWorks.content[index];
					}else{
						row = aidantWorks.content[index];
					}

					showflow(row.processId,row.orderId,row.taskId,approveleave);

				});
				/****处理查看关闭等操作***/
				$("span.btn-link").click(function(event){
					event.stopPropagation(); 
					var type = parseInt(this.getAttribute("tasktype"));
					var index =parseInt(this.getAttribute("data-index"));
					var row, url;
					if(type == 0){
						row = majorWorks.content[index];
					}else{
						row = aidantWorks.content[index];
					}
					showflow(row.processId,row.orderId,row.taskId, showleave);
				});
			}
		});
	};
	
	/**
	 * 通用流程启动函数
	 * @param name 流程名称
	 * @param callback 显示表单的回调函数据
	 */
	var startflow = function(name, callback){
		$.get("../workflow/start/"+name, function(data, status){
			$.get(data.node.form+"?processId="+data.processId+"&orderId=&taskId=&taskName="+data.node.name,
					function(nodedata, status){
				if("success" == status){
					if(nodedata.manager && (nodedata.manager.length > 0)){
						callback(nodedata);
					}else{
						alert("当前登录用户，没有加入部门");
					}
				}
			});
		});
	}
	/**
	 * 通用流程显示函数
	 * @param processId 流程ID
	 * @param orderId 实例ID
	 * @param taskId 任务ID
	 * @param callback 显示表单的回调函数据
	 */
	var showflow = function(processId, orderId, taskId, callback){
		
		$.get("../workflow/commflowdisplay?processId="+processId+"&orderId="+orderId+"&taskId="+taskId,
				function(data, status){
			if("success" == status){
				callback(data);
			}
		});
	};
	
	/****************demo流程********************/
	function leaveFormValidate (){
		$("#leaveForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : "post",
					url : "../workflow/process",
					success : function(data1) {
						if (data1 === "true") {
							hideDialog();
							$("#leaveForm")[0].reset();
							leavelist(0);
						} else {
							alert(data1);
						}
					},
					error : function(msg) {
						alert("新增失败");
					}
				});
			},
			rules : {
				reason:{required : true},
				day : {required : true}
			},
			messages : {
				reason:{required : "理由懒必填"},
				day : {required : "请假天数必填"}
			}
		});
	};
	function addleave(data){
		var leave = {"processId":data.processId,"orderId":data.orderId,"taskId":data.taskId};
		leave.applyId = data.operator.id;
		leave.applyName = data.operator.name;
		var ids = new Array(), names = new Array();
		leave.manager = new Array();
		for(var i=0; i< data.manager.length; i++){
			leave.manager.push({"approveDeptId":data.manager[i].id ,"approveDeptName":data.manager[i].name});
		}
		
		html =  $.templates.addLeaveOrder.render(leave);
		$("#myModalLabel").html("请假申请");
		$("#mydialog_body").html(html);
		showDialog();
		var ok = $("#myModal_dialog_ok");
		ok.unbind("click");
		ok.click(function(){
			$("#leaveForm").submit();
		});
		leaveFormValidate();
	}
	
	/**
	 * 请假流程审批
	 */
	function approveleave(data){
		showleave(data);
		if(data.node){
			$.get(data.node.form+"?processId="+data.processId+"&orderId="+data.orderId+"&taskId="+data.taskId+"&taskName="+data.node.name,
					function(nodedata, status){
				if("success" == status){
					nodedata.taskName = data.node.displayName;
					var html =  $.templates.approvalbodyNUll.render(nodedata);
					$("#approval_process").html(html);
				}
			});
			$("#leaveForm").validate({
				submitHandler : function(form) {
					$(form).ajaxSubmit({
						type : "post",
						url : "../workflow/process",
						success : function(data1) {
							if (data1 === "true") {
								hideDialog();
								$("#leaveForm")[0].reset();
								todotasklist();
							} else {
								alert(data1);
							}
						},
						error : function(msg) {
							alert("新增失败");
						}
					});
				}
			});
			var ok = $("#myModal_dialog_ok");
			ok.unbind("click");
			ok.click(function(){
				$("#leaveForm").submit();
			});
		}

	}
	
	function showleave(data){
		//如果没有实例ID，则表示是新建流程，data.node当前节点为流程第一个任务节点。
		var html = "";
		var len = data.histtask.length;//已完成的节点
		for(var i= len -1; i >= 0; i--){
			var task = data.histtask[i];
			if(i == len -1){
				var leave = JSON.parse(task.variable);
				leave.orderId = data.orderId;
				leave.taskId = data.taskId;
				html =  $.templates.showLeaveOrder.render(leave);
				
			}else{
				var obj = JSON.parse(task.variable);;
				obj.taskName = task.displayName;
				html += $.templates.approvalbody.render(obj);
				//$("#approval_process").html(html);
			}
		}
		$("#myModalLabel").html("请假申请");
		$("#mydialog_body").html(html);
		showDialog();
	}
	
	
	var leavelist = function(pageno){
		var pagesize = 20;
		$.get("../demo/leave/list/"+pageno+"/pagesize/"+pagesize+"/leave", function(data, status){
			if("success" == status){
				var table = {title : "请假单列表"};
				table.thead = ["事由", "天数", "流程状态", "操作"];
				sysmanager(table);
				for(var i=0; i < data.content.length; i++){
					var obj = JSON.parse(data.content[i].variable);
					data.content[i].variable = obj;
				}
				fillleavelist(data, pageno, pagesize);
			}
		});
	}
	var fillleavelist = function(data, pageno, pagesize){
		var html = $.templates.leaveOrderlistByuser.render(data);
		$("#systbody").html(html);
		if(data.totalElements > 0){
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 2,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback : leavelist,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param:{}
			});
		}
		/**查看**/
		$(".check-leave").click(function(){
			var orderId = this.getAttribute("orderid");
			showflow("",orderId,"", showleave);
		});
		$("#addButton").unbind("click");
		$("#addButton").click(function(){
			startflow("leave", addleave);
		});
	}
	
	var showDialog = function() {
		$('#myModal_dialog').modal({
			keyboard : true,
			backdrop : 'static'
		});
	}
	var hideDialog = function() {
		$('#myModal_dialog').modal('hide');
	}
	
	/*******************生成后台管理菜单*********************/
	function leftMenuBar(){
		$.get("../admin/user/usermenu", function(data, status){
			if("success" == status){
				var len = data.length;
				var html='<ul class="metismenu" id="admin_left_menu">';
				for(var i = 0; i < len; i++){
					var menu = data[i];
					if(menu.name != "系统管理" && menu.name != "流程管理" && menu.name != "PageOffice示例"){
						if(menu.child){
							html+='<li class="active"><a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
							html += leftSubMenu(menu.subMenu);
							html+='</li>';
						}else{
							html+='<li><a href="javascript:gotoPage(\''+menu.url+'\',\''+menu.name+'\');" >'+menu.name+'</a>';
							html+='</li>';
						}
					}
				}
				html += '</ul>';
				$("#left_menu_bar").html(html);
				$('#admin_left_menu').metisMenu({ toggle: false });
			}
		});
	}
	
	function leftSubMenu(submenu){
		var len = submenu.length;
		var html='<ul aria-expanded="true">';
		for(var i=0; i<len; i++){
			var menu = submenu[i];
			if(menu.child){
				html+='<li><a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
				html += leftSubMenu(menu.subMenu);
				html+='</li>';
			}else{
				html+='<li><a href="javascript:gotoPage(\''+menu.url+'\',\''+menu.name+'\');" >'+menu.name+'</a>';
				html+='</li>';
			}
		}
		html += '</ul>';
		return html;
	}
	
	return {historyworkitems:historyworkitems,
		todotasklist:todotasklist,
		leavelist:leavelist,
		leftMenuBar:leftMenuBar};
}

$(document).ready(function(){
	window.uflow = userflow();
	uflow.leftMenuBar();
});
function gotoPage(url, name){
	var path = "../demo/main?menuId=";
	var poi = url.indexOf(path);
	if(poi == 0){
		var m = url.substring(path.length);
		$("#title_text").text(name);
		if ("menu_todo" == m) {
			uflow.todotasklist();
		} else if ("menu_flow_historytask" == m) {
			uflow.historyworkitems(0);
		} else if ("menu_leave" == m) {
			uflow.leavelist(0);
		}
	}else{
		window.location.href=url;
	}
}