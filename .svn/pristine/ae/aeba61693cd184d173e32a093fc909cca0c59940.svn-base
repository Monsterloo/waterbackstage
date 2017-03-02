
function workflow() {
	$.datepicker.setDefaults($.datepicker.regional['zh-CN']);
	this.hash = {}, this.menuhash = {}, this.selectmenu = {};
	var self = this;
	var getFlowList = function(pageNo, param) {
		var pagesize = 20;
		$.get("../admin/flow/list/" + pageNo + "/pagesize/"+pagesize+"?displayname="+param ,
				function(data, status) {
					if ("success" == status) {
						data.pageNo = pageNo;
						fillFlowListTmpl(data, param);
					}
				});
	}
	var fillFlowListTmpl = function(flows, param) {
		var html = $.templates.processlist.render(flows);
		$("#main-content").html(html);
		$("#flow_design").click(function() {
			flowDesigner("");
		});
		$("#flow_deploy").click(flowDeploy);
		$("#flow_query").click(function() {
			var name = $("#displayName").val();
			if (!name) {
				alert("请输入要查询的流程名");
				return;
			}
			getFlowList(0,name);
		});
		$("#Pagination").pagination(flows.totalElements, {
			num_edge_entries : 1,
			num_display_entries : 4,
			items_per_page : 20,
			callback : getFlowList,
			current_page : flows.pageNo,
			prev_text : "前一页",
			next_text : "后一页",
			param:param
		});
		$(".processdesign").click(function() {
			var index = $(this).attr("data-index");
			var flow = flows.content[index];
			flowDesigner(flow.id);
		});

		$(".processedit").click(function() {
			var index = $(this).attr("data-index");
			var flow = flows.content[index];

			$.get("../admin/flow/edit?id=" + flow.id, function(data, status) {
				if ("success" == status) {
					var proce = {
						"id" : flow.id,
						"content" : data
					};
					flowedit(proce);
				}
			});
		});
		$(".processdelete").click(function(){
			var index = $(this).attr("data-index");
			var flow = flows.content[index];
			$.get("../admin/flow/delete/"+flow.id, function(data, status){
				if("success" == status){
					fillFlowListTmpl(flows.pageNo, param);
				}
			});
		});
	}

	var deployValidate = function(fromname) {
		$(fromname).validate(
				{
					rules : {
						snakerFile : "required",
						snakerFile : {
							required : true,
						}
					},
					messages : {
						snakerFile : "请选择一个流程定义文件",
						snakerFile : {
							required : "请选择一个流程定义文件",
						}
					},
					errorElement : "em",
					errorPlacement : function(error, element) {
						error.addClass("help-block");

						if (element.prop("type") === "checkbox") {
							error.insertAfter(element.parent("label"));
						} else {
							error.insertAfter(element);
						}
					},
					highlight : function(element, errorClass, validClass) {
						$(element).parents().addClass("has-error").removeClass(
								"has-success");
					},
					unhighlight : function(element, errorClass, validClass) {
						$(element).parents().addClass("has-success")
								.removeClass("has-error");
					},
					submitHandler : function(form) {
						$(form).ajaxSubmit({
							type : "post",
							url : "../admin/flow/deploy",
							success : function(data1) {
								hideDialog();
								getFlowList(0,'');
							},
							error : function(msg) {
								alert("文件上传失败");
							}
						});
					}
				});
	}

	var flowedit = function(data) {
		var html = $.templates.processedit.render(data);
		$("#main-content").html(html);
		deployValidate("#editForm");
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			$("#editForm").submit();
		});
	}

	var flowDeploy = function() {
		var html = $.templates.processdeploy.render();
		$("#myModalLabel").html("选择流程定义文件");
		$("#mydialog_body").html(html);
		showDialog();
		deployValidate("#deployForm");
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			$("#deployForm").submit();
		});
	}

	var flowDesigner = function(id) {
		var html = $.templates.processdesigner.render(id);
		$("#main-content").html(html);
	}

	var sysmanager = function(table, fn) {
		var html = $.templates.sysmanager.render(table);
		$("#main-content").html(html);
		$("#addButton").unbind("click");
	}

	/*******流程实例***********/
	var floworderlist = function(pageno){
		var pagesize = 20;
		$.get("../admin/flow/orders/"+pageno+"/pagesize/"+pagesize, function(data, status){
			if("success" == status){
				var table = {title : "流程实例"};
					table.thead = ["流程名称", "实例启动时间", "实例结束时间", "期望完成时间", "实例创建人",
					 "状态", "操作"];
					sysmanager(table);
					$("#sys_toolbar").remove();
					fillfloworderlist(data, pageno, pagesize);
			}
		});
	};
	var fillfloworderlist = function(data, pageno, pagesize){
		var html = $.templates.flowordertable.render(data);
		$("#systbody").html(html);
		if (data.totalElements != 0) {
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 1,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback : floworderlist,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param : {}
			});
		}
		$(".toview-floworder").click(function(){
			var index = $(this).attr("data-index");
			var row = data.content[index];
			var order={"title":row.process.displayName+"实例流程图","processId":row.process.id ,"orderId":row.id};
			html = $.templates.flowtoview.render(order);
			$("#main_table").hide();
			$("#main-content").append(html);
			$("#permission_cencal").click(function(){
				$("#flowtoview_process").remove();
				$("#main_table").show();
			});
		});
	}
	
	/****************系统管理**************************/
	var userlist = function(pageno, param) {
		if (!param)
			param = {};
		var pagesize = 20;
		$.post("../users/list/" + pageno + "/pagesize/" + pagesize, param,
				function(data, status) {
					if ("success" == status) {
						var table = {
							title : "用户列表"
						}
						table.thead = [ "登录账户", "创建时间", "名称", "邮箱", "电话",
								"管理员", "状态", "操作" ];
						sysmanager(table);
						filluerlist(data, pageno, pagesize, param);
					}
				});
	}
	
	/** 用户添加角色的列表***/
	var userToRoleList = function(pageno){
		var pagesize = 20;
		$.get("../admin/role/list/"+pageno+"/pagesize/"+pagesize, function(data, status){
			if("success" == status){
				var html = $.templates.addResToRolelist.render(data);
				$("#mydialog_body").html(html);
				$(":radio").each(function() {
				//$(":checkbox").each(function() {
					if (self.hash[this.value]) {
						this.checked = true;
					}else{
						this.checked = false;
					}
					$(this).click(function() {
						self.hash={};
						if (this.checked) {
							self.hash[this.value] = this.nextSibling.nodeValue;
						} else {
							delete self.hash[this.value];
						}
					});
				});
				if (data.totalElements != 0) {
					$("#dialog_pagination").pagination(data.totalElements, {
						num_edge_entries : 1,
						num_display_entries : 3,
						items_per_page : pagesize,
						callback : userToRoleList,
						current_page : pageno,
						prev_text : "前一页",
						next_text : "后一页",
						param : {}
					});
				};
			}
		});
	}

	var filluerlist = function(data, pageno, pagesize, param) {
		var area=$("#hide_area").val();
		var role=$("#hide_role").val();
		var html = $.templates.usertable.render(data);
		
		$("#systbody").html(html);
		if (!param) {
			param = {
				"displayName" : ""
			};
		}
		html = $.templates.usertoolbar.render(param);
		$("#sys_toolbar").html(html);
		if(param){
			$("#username").val(param.displayName);
		}
		$("#exportButton").attr("style","display:block;float:right;");
		if (data.totalElements != 0) {
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 1,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback : userlist,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param : param
			});
		}
		
		$("#userForm1").validate({
			submitHandler : function(form) {
				var param = {
					"displayName" : form.username.value
				};
				userlist(0, param);
				return false;
			}
		});
		$(".fundlocked-user").click(function() {
			var index = $(this).attr("data-index");
			var row = data.content[index];
			$.get("../admin/user/lock/" + row.loginname, function(data, status) {
				if ("success" == status) {
					userlist(pageno, param);
				} else {
					alert("操作失败");
				}
			});
		});
		
		if(role=="areamanager"){
			$(".romover-user").hide();
		}else{
			$(".romover-user").click(function() {
				if (confirm("确定要删除用户，删除后不可恢复？")) {
					var index = $(this).attr("data-index");
					var row = data.content[index];
					$.get("../swjUser/deleteUser/" + row.userId, function(
							data, status) {
						if ("success" == status) {
							userlist(pageno, param);
						} else {
							alert("操作失败");
						}
					});
				}
			});
		}
		$("#addButton").click(function() {
			var html = $.templates.adduser.render();
			
			$("#myModalLabel").html("新增用户");
			$("#mydialog_body").html(html);
			if("areamanager" == role){
				$("#areaSelect").append("<option >"+area+"</option>");
			}else{
				//行政区域信息
				$.ajax({
					type : "POST",
					url : "../swjUser/findArea",
					data :  {}, 
					dataType : "json",
					success : function(data) {
						$("#areaSelect").empty().append("<option></option>");
						$.each(data, function(i, value){
							$("#areaSelect").append("<option >"+value.name+"</option>");
						});
					}
				});
			}
			
			showDialog();
			$("#myModal_dialog_ok").unbind("click");
			$("#myModal_dialog_ok").click(function() {
				$("#userForm").submit();
			});
			uservalidate(pageno, param);
		});
		/* 修改用户 */
		$(".modify-user").click(function(pageno) {
			var index = $(this).attr("data-index");
			var row = data.content[index];
			$.get("../admin/user/finduser/" + row.userId, function(data, status) {
				if ("success" == status) {
					var html = $.templates.edituser.render(data);
				
				
				//行政区域信息
				$.ajax({
					type : "POST",
					url : "../swjUser/findArea",
					data :  {}, 
					dataType : "json",
					success : function(data) {
						$("#areaSelect").empty().append("<option></option>");
						$.each(data, function(i, value){
							$("#areaSelect").append("<option >"+value.name+"</option>");
						});

					}

				});
					$("#myModalLabel").html("修改用户");
					$("#mydialog_body").html(html);
					showDialog();
					$("#myModal_dialog_ok").unbind("click");
					$("#myModal_dialog_ok").click(function() {
						$("#userForm").submit();
					});
				}
				uservalidate(pageno, param);
			});
		
			
			
		});
		$("#exportButton").click(function(){
			
			if (confirm("确认导出？")) {
				$("#exportForm").submit();
				alert("正在导出，请稍后！");
			} 
		});
		
		/******给用户添加角色******/
		$(".addRoleToUser").click(function(){
			var index = $(this).attr("data-index");
			var row = data.content[index];
			$.get("../admin/user/inrole/"+row.username, function(data, status){
				if("success" == status){
					if(data){
						console.log(data)
						self.hash = {};
						for(var i = 0; i<data.length; i++){
							self.hash[data[i].id] = true;
						}
					}
					$("#myModalLabel").html("给用户"+row.displayName+"添加角色");
					
					showDialog();
					userToRoleList(0);
					$("#myModal_dialog_ok").unbind("click");
					$("#myModal_dialog_ok").click(function() {
						var roleIds = new Array();

						for(var id in self.hash){
							roleIds.push(id);
						}
						
						var str = "";
						if(roleIds){
							str = roleIds.join(",");
						}
						/*$.post("../users/saverole",{"username":row.username,"roleIds":str},
								function(data,status){
							self.hash = {};
							hideDialog();
						});*/
						$.post("../admin/user/saverole",{"username":row.username,"roleIds":str},
								function(data,status){
							self.hash = {};
							hideDialog();
						});
					});
				}
		});
			
		});
	};

	var uservalidate = function(pageno) {
		var validate = $("#userForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : "post",
					url : "../swjUser/saveUser",
					success : function(data1) {
						if (data1 === "true") {
							hideDialog();
							$("#userForm")[0].reset();
							userlist(pageno);
						} else {
							alert("用户姓名，电话已存在");
							//alert(data1);
						}
					},
					error : function(msg) {
						alert("新增失败");
					}
				});
			},
			rules : {
				loginname : {
					required : true,
					minlength : 5,
					remote: {
					    url: "../admin/user/existusername",
					    type: "post",
					    dataType: "json",
					    data: {username: function() {return $("#loginname").val();}}
					}
				},
				displayName : {
					required : true,
					minlength : 5
				},
				email : {
					required : false,
					email : true
				},
				password : {
					required : true,
					minlength : 6
				},
				confirm_password : {
					equalTo : "#password"
				}
			},
			messages : {
				loginname : {
					required : "必填",
					minlength : "不能少于5个字符",
					remote:"用户名已经被注册了"
				},
				email : {
					//required : "必填",
					email : "E-Mail格式不正确"
				},
				displayName : {
					required : "请填写一个昵称"
				},
				password : {
					required : "不能为空",
					minlength : "不能少于6个字符"
				},
				confirm_password : {
					equalTo : "两次密码输入不一致"
				}
			}
		});
	}

	var rolelist = function(pageno) {
		var pagesize = 20;
		$.get("../admin/role/list/" + pageno + "/pagesize/" + pagesize,
				function(data, status) {
					if ("success" == status) {
						var table = {
							title : "角色列表"
						}
						table.thead = [ "角色名", "描述", "操作" ];
						sysmanager(table);
						fillrolelist(data, pageno, pagesize);
					}
				});
	}

	var rolevalidata = function(pageno) {
		$("#roleForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : "post",
					url : "../admin/role/save",
					success : function(data1) {
						hideDialog();
						$('#roleForm')[0].reset()
						rolelist(pageno);
					},
					error : function(msg) {
						alert("操作失败:" + msg);
					}
				});
			},
			rules : {
				name : {
					required : true
				}
			},
			messages : {
				name : {
					required : "必填"
				}
			}
		});
	}

	var rolesave = function(title, pageno, role) {
		var html = $.templates.addrole.render(role);
		$("#myModalLabel").html(title);
		$("#mydialog_body").html(html);
		showDialog();
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			$("#roleForm").submit();
		});
		rolevalidata(pageno);
	};
	var roleResList = function(pageno) {
		var pagesize = 20;
		$.get("../admin/resources/list/" + pageno + "/pagesize/" + pagesize,
				function(data, status) {
					var html = $.templates.addResToRolelist.render(data);
					$("#permissionRes").html(html);
					if (data.totalElements != 0) {
						$("#respage").pagination(data.totalElements, {
							num_edge_entries : 1,
							num_display_entries : 4,
							items_per_page : pagesize,
							callback : roleResList,
							current_page : pageno,
							prev_text : "前一页",
							next_text : "后一页",
							param : {}
						});
					};
					
					$(":checkbox").each(function() {
						if (self.hash[this.value]) {
							this.checked = true;
						}
						$(this).click(function() {
							if (this.value) {
								self.hash[this.value] = "res";
							} else {
								delete self.hash[this.value];
							}

						});
					});

				});
	}

	var returnRoel = function() {
		$('#permissionMenu').jstree("destroy");
		$("#permission_role").remove();
		$("#main_table").show();
	}

	var fillrolelist = function(data, pageno, pagesize) {
		var html = $.templates.roletable.render(data);
		$("#systbody").html(html);
		if (data.totalElements != 0) {
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 1,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback : rolelist,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param : {}
			});
		}
		;

		$("#addButton").click(function() {
			var role = {
				id : '',
				name : '',
				desc : ''
			}
			rolesave("新增角色", pageno, role);
		});

		/* 修改角色 */
		$(".modify-role").click(function() {
			var index = $(this).attr("data-index");
			var row = data.content[index];
			$.get("../admin/role/id/" + row.id, function(role, status) {
				if ("success" == status) {
					rolesave("修改角色", pageno, role);
				}
			});
		});
		/* 角色所属用户 */
		$(".rolebelongsUser").click(function() {
			var index = $(this).attr("data-index");
			var row = data.content[index];
			$.get("../admin/role/id/" + row.id, function(role, status) {
				if ("success" == status) {
					var html = $.templates.rolebelong.render(role);
					$("#myModalLabel").html(role.name + "所属的用户");
					$("#mydialog_body").html(html);
					showDialog();
					$("#myModal_dialog_ok").click(function() {
						hideDialog();
					});
				}
			});

		});
		/* 删除角色 */
		$(".romover-role").click(
				function() {
					if (confirm("是否要删除此角色？")) {
						var index = $(this).attr("data-index");
						var row = data.content[index];
						$.get("../admin/role/delete/" + row.id, function(retsu,
								status) {
							if ("success" == status) {
								rolelist(pageno);
							}
						});
					}
				});
		/** 角色权限** */
		$(".rolebelongsRes").click(function() {
			var index = $(this).attr("data-index");
			var row = data.content[index];
			/** *取角色的权** */
			$.get('../admin/role/getpermission/'+ row.id,
				function(data, status) {
				if ("success" == status) {
					if (data.resIds) {
						for (var i = 0; i < data.resIds.length; i++) {
							self.hash[data.resIds[i]] = "res";
						}
					}
				    if(data.menuIds) {
						for (var i = 0; i < data.menuIds.length; i++) {
							self.selectmenu[data.menuIds[i]] = "menu";
						}
					}
					var html = $.templates.addpermission.render("设置"+ row.name+ "的权限");
					$("#main_table").hide();
					$("#main-content").append(html);
					roleResList(0);
					var permissmenu = $('#permissionMenu').jstree({
							'core' : {'data' : {
											'url' : '../admin/menu/parent/?lazy',
											'data' : function(node) {
												return {'id' : node.id};
											}
										}
									},
							"checkbox" : {"keep_selected_style" : false},
							"plugins" : ["checkbox","wholerow" ]
							}).on("load_node.jstree",function(e,data) {
								var children = data.node.children_d;
								var id = "";
								for (var i = 0; i < children.length; i++) {
									id = children[i];
									if (id === "0")
										continue;
									else {
										if (self.selectmenu[id]) {
											$('#permissionMenu').jstree(true).select_node(id);
											delete self.selectmenu[id];
										}
										self.menuhash[id] = false;
									}
								}
					});

					/** 保存角色权限** */
					$("#permission_create").click(function() {
																		
					var ref = $('#permissionMenu').jstree(true);
					var sel = ref.get_selected();
					var resIds = new Array();
					for ( var id in self.hash) {
						resIds.push(id);
					}
					var menuIds = new Array();
					for ( var id in self.selectmenu) {
						menuIds.push(id);
					}
					for (var i = 0; i < sel.length; i++) {
						menuIds.push(sel[i])
					}
					$.post("../admin/role/permission",
					{"roleId" : row.id,"resIds" : resIds.join(","),"menuIds" : menuIds.join(",")},
					function(data,status) {
						if ("success" === status) {
							self.hash = {};
							self.selectmenu = {};
							self.menuhash = {};
							returnRoel();
							alert("保存权限成功");
						}
					});
				});
				$("#permission_cencal").click(function() {
					returnRoel();
				});
			}
		});
	});
	}

	/** ****资源管理***** */
	var resourcselist = function(pageno) {
		var pagesize = 10;
		$.get("../admin/resources/list/" + pageno + "/pagesize/" + pagesize,
				function(data, status) {
					if ("success" == status) {
						var table = {
							title : "权限管理资源列表"
						}
						table.thead = ["序号", "名称", "资源对象", "操作"];
						sysmanager(table);
						fillreslist(data, pageno, pagesize);
					}
				});
	}

	var resValidate = function(pageno) {
		$("#resForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : "post",
					url : "../admin/resources/save",
					success : function(data1) {
						hideDialog();
						$('#resForm')[0].reset()
						resourcselist(pageno);
					},
					error : function(msg) {
						alert("修改失败:" + msg);
					}
				});
			},
			rules : {
				name : {
					required : true
				},
				resobj : {
					required : true
				}
			},
			messages : {
				name : {
					required : "必填"
				},
				resobj : {
					required : "必填"
				}
			}
		});
	};

	var resSave = function(title, pageno, res) {
		var html = $.templates.addresources.render(res);
		$("#myModalLabel").html(title);
		$("#mydialog_body").html(html);
		showDialog();
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			$("#resForm").submit();
		});
		resValidate(pageno);
	};

	var fillreslist = function(data, pageno, pagesize) {
		var html = $.templates.resourcestable.render(data);
		$("#systbody").html(html);
		if (data.totalElements != 0) {
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 1,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback : resourcselist,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param : {}
			});
		}
		$("#addButton").click(function() {
			var res = {
				id : '',
				name : '',
				resobj : ''
			}
			resSave("新增资源", pageno, res);
		});

		$(".romover-resources").click(
				function() {
					if (confirm("是否要删除此资源？")) {
						var index = $(this).attr("data-index");
						var row = data.content[index];
						$.get("../admin/resources/delete/" + row.id, function(
								retsu, status) {
							if ("success" == status) {
								resourcselist(pageno);
							}
						});
					}
				});

		$(".modify-resources").click(function() {
			var index = $(this).attr("data-index");
			var row = data.content[index];
			$.get("../admin/resources/id/" + row.id, function(res, status) {
				if ("success" == status) {
					resSave("修改资源", pageno, res);
				}
			});
		});

	}/* end fillreslist */
	/** ******loginlog********** */
	var loginloglist = function(pageno, param) {
		var pagesize = 20;
		$.post("../admin/loginlog/list/" + pageno + "/pagesize/" + pagesize,
				param, function(data, status) {
					if ("success" == status) {
						var table = {
							title : "登录日志"
						}
						table.thead = ["序号", "登录名", "IP", "登录时间", "操作" ];
						sysmanager(table);
						fillLoginLogList(data, pageno, pagesize, param);
					}
				});
	}

	var fillLoginLogList = function(data, pageno, pagesize, param) {
		var html = $.templates.loginlogtable.render(data);
		$("#systbody").html(html);
		if (!param) {
			param = {
				"stime" : "",
				"etime" : ""
			};
		}
		html = $.templates.logtoolbar.render(param);
		$("#sys_toolbar").html(html);
		$("#stime").datepicker({
			dateFormat : "yy-mm-dd"
		});
		$("#etime").datepicker({
			dateFormat : "yy-mm-dd"
		});
		$("#Pagination").pagination(data.totalElements, {
			num_edge_entries : 2,
			num_display_entries : 4,
			items_per_page : pagesize,
			current_page : pageno,
			callback : loginloglist,
			prev_text : "前一页",
			next_text : "后一页",
			param:param
		});
		$(".romover-loginlog").click(
				function() {
					if (confirm("确定要删除吗？")) {
						var index = $(this).attr("data-index");
						var row = data.content[index];
						$.get('../admin/loginlog/delete/' + row.id, function(
								result, status) {
							if ("success" === status) {
								loginloglist(pageno);
							}
						});
					}
				});
		$("#queryForm").validate({
			submitHandler : function(form) {
				var param = {
					"stime" : form.stime.value,
					"etime" : form.etime.value
				};
				loginloglist(0, param);
				return false;
			}
		});
	}

	/** ********菜单管理************ */

	var getselectTree = function() {
		var ref = $('#treemanager').jstree(true);
		return ref;
	}

	var menusave = function(title, menu) {
		var html = $.templates.addmenu.render(menu);
		$("#myModalLabel").html(title);
		$("#mydialog_body").html(html);
		showDialog();
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			$("#menuForm").submit();
		});
		menuValidate(menu.parentId);
	}

	var menuValidate = function(pid) {
		$("#menuForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : "post",
					url : "../admin/menu/save",
					success : function(data1) {
						hideDialog();
						$('#menuForm')[0].reset()
						var ref = getselectTree();
						if ($("#myModalLabel").text() == "新增菜单") {
							ref.create_node(pid, data1);
						} else {
							ref.rename_node(data1, data1.text);
						}
					},
					error : function(msg) {
						alert("操作失败");
					}
				});
			},
			rules : {
				name : {
					required : true
				},
				url : {
					required : true
				}
			},
			messages : {
				name : {
					required : "必填"
				},
				url : {
					required : "必填"
				}
			}
		});
	}

	var menumanager = function() {
		var html = $.templates.menumanager.render("树形菜单展示");
		$("#main-content").html(html);
		$('#treemanager').jstree({
			'core' : {
				'data' : {
					'url' : '../admin/menu/parent/?lazy',
					'data' : function(node) {
						return {
							'id' : node.id
						};
					}
				},
				'check_callback' : true,
				'themes' : {
					'responsive' : false
				}
			},
			'force_text' : true,
			'plugins' : [ 'state', 'wholerow' ]
		});

		$("#menu_create").click(function() {
			var ref = getselectTree();
			var sel = ref.get_selected();
			if (!sel.length) {
				return false;
			}

			var menu = {
				id : '',
				name : '',
				url : '',
				parentId : sel[0],
				order : 1,
				display : true
			}
			menusave("新增菜单", menu);
		});
		$("#menu_rename").click(function() {
			var ref = getselectTree();
			var sel = ref.get_selected();
			if (!sel.length || sel[0] === "0") {
				return false;
			}
			$.get('../admin/menu/id/' + sel[0], function(data, status) {
				if ("success" == status) {
					menusave("修改菜单", data);
				}
			});
		});
		$("#menu_delete").click(function() {
			var ref = getselectTree();
			var sel = ref.get_selected();
			if (!sel.length || sel[0] === "0") {
				return false;
			}else{
				var node = ref.get_node(sel[0]);
				if(node.children.length > 0){
					alert("此菜单还有子菜单，不能删除");
					return false;
				}
			}
			if (confirm("确定要删除此菜单项，删除后数据不可恢复？")) {
				$.get("../admin/menu/delete/" + sel[0], function(data, status) {
					if ("success" == status) {
						ref.delete_node(sel);
					}
				});
			}
		});

	}/** end menu */
	/** ********department************ */
	var departsave = function(title, depart) {
		var html = $.templates.adddepart.render(depart);
		$("#myModalLabel").html(title);
		$("#mydialog_body").html(html);
		showDialog();
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			$("#departForm").submit();
		});
		departValidate(depart.parentId);
	}
	var departValidate = function(pid) {
		$("#departForm").validate({
			submitHandler : function(form) {
				$(form).ajaxSubmit({
					type : "post",
					url : "../admin/department/save",
					success : function(data1) {
						hideDialog();
						$('#departForm')[0].reset()
						var ref = getselectTree();
						if ($("#myModalLabel").text() == "新增部门") {
							ref.create_node(pid, data1);
						} else {
							ref.rename_node(data1, data1.text);
						}

					},
					error : function(msg) {
						alert("操作失败");
					}
				});
			},
			rules : {
				name : {
					required : true
				},
				url : {
					required : true
				}
			},
			messages : {
				name : {
					required : "必填"
				},
				url : {
					required : "必填"
				}
			}
		});
	}
	
	var departUserlist = function(departid){
		var pagesize = 20;
		$.get("../admin/department/users/"+departid,function(data, status){
			if(status == "success"){
				if(data){
					var html =  $.templates.departmentusertable.render(data);
					$("#systbody").html(html);
					self.hash = {};
					for(var i=0; i< data.length; i++){
						var row = data[i];
						self.hash[row.userId] = true;
					}
				}else{
					$("#systbody").html("");
				}
			}
		});
	}

	var departmanager = function() {
		var html = $.templates.departmentmanager.render("组织架构树形");
		$("#main-content").html(html);
		
		var thead = {"thead":[ "用户名称", "EMail", "电话", "选择" ]};
		html = $.templates.tablehead.render(thead);
		$("#departmentuser").html(html);
		$('#treemanager').jstree({
			'core' : {
				'data' : {
					'url' : '../departments/parent/?lazy',
					'data' : function(node) {
						return {
							'id' : node.id
						};
					}
				},
				'check_callback' : true,
				'themes' : {
					'responsive' : false
				}
			},
			'force_text' : true,
			'plugins' : [ 'state', 'wholerow' ]
		}).on("changed.jstree", function (e, data) {
			var sel = data.selected;
			if(sel[0]){
				departUserlist(sel[0]);
			}
			
		});

		$("#depart_create").click(function() {
			var ref = getselectTree();
			var sel = ref.get_selected();
			if (!sel.length) {
				return false;
			}

			var depart = {
				id : '',
				text : '',
				parentId : sel[0]
			}
			departsave("新增部门", depart);
		});
		$("#depart_rename").click(function() {
			var ref = getselectTree();
			var sel = ref.get_selected();
			if (!sel.length || sel[0] === "0") {
				return false;
			}
			$.get('../admin/department/id/' + sel[0], function(data, status) {
				if ("success" == status) {
					departsave("修改部门", data);
				}
			});
		});
		$("#depart_delete").click(
		function() {
			var ref = getselectTree();
			var sel = ref.get_selected();
			
			if (!sel.length || sel[0] === "0") {
				return false;
			}else{
				var node = ref.get_node(sel[0]);
				if(node.children.length > 0){
					alert("此部门还有子部门，不能删除");
					return false;
				}
			}
			if (confirm("确定要删除此部门，删除后数据不可恢复？")) {
				$.get("../admin/department/delete/" + sel[0], function(
						data, status) {
					if ("success" == status) {
						ref.delete_node(sel);
					}
				});
			}
		});
		$("#departuser_add").click(function(){
			var ref = getselectTree();
			var sel = ref.get_selected();
			if (!sel.length || sel[0] === "0") {
				alert("请选择一部门");
				return false;
			}
			addDepartUserList(0);
			
			$("#myModalLabel").html("添加用户到部门");
			showDialog();
			$("#myModal_dialog_ok").unbind("click");
			$("#myModal_dialog_ok").click(function() {
				var ids = new Array();
				for(var id in self.hash){
					ids.push(id);
				}
				var str = "";
				if(ids)
					str = ids.join(",");
				$.post("../admin/department/adduser",{"departid":sel[0],"accountid":str},function(data, status){
					if("success" == status){
						hideDialog();
						$("#dialog_pagination").empty();
						departUserlist(sel[0]);
					}
				});
			});
		});
		$("#departuser_delete").click(function(){
			if(confirm("确定要删除这些用户？")){
				
				var ref = getselectTree();
				var sel = ref.get_selected();
				if (!sel.length || sel[0] === "0") {
					return false;
				}
				var uIds = new Array();
				$("#departmentuser").find('input').each(function(){
					if(this.checked){
						uIds.push(this.value);
						delete self.hash[this.value];
					}
				});
				var str  = "";
				if(uIds){
					str = uIds.join(",");
				}
				$.get("../admin/department/deleteuser/"+sel[0]+"/userid/"+str, function(data, status){
					if(status == "success"){
						departUserlist(sel[0]);
					}
				});
			}
		});
	}
	
	var addDepartUserList = function(pageno, name){
		var pagesize = 40;
		if(!name){
			name="";
		}
		$.get("../users/listbase/"+pageno+"/pagesize/"+pagesize+"?name="+encodeURI(name), function(data, status){
			if("success" == status){
				var html = $.templates.adduserdepartdialog.render(data);
				$("#mydialog_body").html(html);
				$("#dialog_pagination").pagination(data.totalElements, {
					num_edge_entries : 1,
					num_display_entries : 3,
					items_per_page : pagesize,
					current_page : pageno,
					callback : addDepartUserList,
					prev_text : "前一页",
					next_text : "后一页",
					param:encodeURI(name)
				});
				$("#selname").val(name);
				$("#funded-user").click(function() {
					var name = $("#selname").val();
					addDepartUserList(0,name);
					
					/*var name = $("#displayName").val();
					if (!name) {
						alert("请输入要查询的流程名");
						return;
					}
					getFlowList(0,name);*/
				});
				$("#mydialog_body").find('input').each(function(){
					if(self.hash[this.value]){
						this.checked = true;
					}
					$(this).click(function() {
						if (this.checked) {
							self.hash[this.value] = true;
						} else {
							delete self.hash[this.value];
						}
					});
				});
			}
		});
	}
	
	
	/*var addDepartUserList = function(pageno){
		var pagesize = 40;
		$.get("../admin/user/listbase/"+pageno+"/pagesize/"+pagesize, function(data, status){
			if("success" == status){
				var html = $.templates.adduserdepartdialog.render(data);
				$("#mydialog_body").html(html);
				$("#dialog_pagination").pagination(data.totalElements, {
					num_edge_entries : 1,
					num_display_entries : 3,
					items_per_page : pagesize,
					current_page : pageno,
					callback : addDepartUserList,
					prev_text : "前一页",
					next_text : "后一页",
					param:{}
				});
				$("#funded-user").click(function() {
					alert(1);
					var name = $("#selname").val();
					
					var name = $("#displayName").val();
					if (!name) {
						alert("请输入要查询的流程名");
						return;
					}
					getFlowList(0,name);
				});
				$("#mydialog_body").find('input').each(function(){
					if(self.hash[this.value]){
						this.checked = true;
					}
					$(this).click(function() {
						if (this.checked) {
							self.hash[this.value] = true;
						} else {
							delete self.hash[this.value];
						}
					});
				});
			}
		});
	}*/
	/********数据词典***********/
	var dictionarylist =function(pageno,pagesize){
		var pagesize = 20
		$.get("../admin/dictonary/list/"+pageno+"/pagesize/"+pagesize, 
			function(data,status){
				if ("success" == status) {
					var table = {title:"数据字典"}
					table.thead = ["分类","编号","名称","状态","上级编号","操作"];
					sysmanager(table);
					filldictlist(data, pageno, pagesize);
				}
		});
	}

	var dictValidate = function(pageno){
		$("#dictForm").validate({
			submitHandler: function(form){
				$(form).ajaxSubmit({
					type : "post",
					url :"../admin/dictonary/save",
					success : function(data1){
						hideDialog();
						$("#dictForm")[0].reset();
						dictionarylist(pageno);
					},
					error:function(msg){
						alert("修改失败"+msg);
					}
				});
			},
			rules:{code:{required:true},name:{required:true}},
			messages:{code:{required:"必填"},name:{required:"必填"}}
		});
	};

	var dictSave  = function(title,pageno,dict){
		var html = $.templates.adddict.render(dict);
		$("#myModalLabel").html(title);
		$("#mydialog_body").html(html);
		showDialog();
		$("#myModal_dialog_ok").unbind("click");
		
		$("#myModal_dialog_ok").click(function() {
			$("#dictForm").submit();
		});
		dictValidate(pageno);
	};
	
	var filldictlist = function(data,pageno,pagesize){
		var html = $.templates.dicttable.render(data);
		$("#systbody").html(html);
		if(data.totalElements != 0){
			$("#Pagination").pagination(data.totalElements, {
				num_edge_entries : 1,
				num_display_entries : 4,
				items_per_page : pagesize,
				callback: dictionarylist,
				current_page : pageno,
				prev_text : "前一页",
				next_text : "后一页",
				param:{}
			});	
		}
		$("#addButton").click(function(){
			var dict = {category:'',code:'',name:'',status:'',parent:''}
			dictSave("新增数据字典", pageno, dict);
		});
		$(".romover-dict").click(function(){
			if(confirm("是否删除此数据？")){
				var index=$(this).attr("data-index");
				var row = data.content[index];
				$.get("../admin/dictonary/delete/"+row.id,function(data, status){
					if ("success" == status) {
						dictionarylist(pageno);
					}else{
						alert("操作失败");
					}
				});
			}
		});
		
		$(".modify-dict").click(function(){
			var index = $(this).attr("data-index");
			var row = data.content[index];
			console.log(row.id);
			$.get("../admin/dictonary/id/"+row.id,
				function(dict, status) {
					if ("success" == status) {
						dictSave("修改数据字典", pageno, dict);
					}
			});
		});
		
	}
	/*****选择流程任务参与角色*****/
	var flowRoleselectDialog = function(element, props_k, assignee){
		$("#myModalLabel").html("选择角色");
		showDialog();
		userToRoleList(0);
		$("#myModal_dialog_ok").unbind("click");
		$("#myModal_dialog_ok").click(function() {
			var roleIds = new Array();
			var roleNames = new Array();
			for(var id in self.hash){
				roleIds.push(id);
				roleNames.push(self.hash[id]);
			}
			var str = "", strname="";
			if(roleIds){
				str = roleIds.join(",");
				strname = roleNames.join(",");
			}
			element.title = str;
			element.value = strname;
			props_k.value = strname;
			assignee.value = str;
			
			self.hash = {};
			hideDialog();
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
	$('#myModal_dialog').on('hide.bs.modal', function () {
		$("#dialog_pagination").empty();
	});
	
	/*******************生成后台管理菜单*********************/
	function leftMenuBar(){
		$.get("../admin/user/usermenu", function(data, status){
			if("success" == status){
				var len = data.length;
				var html='<ul class="metismenu" id="admin_left_menu">';
				for(var i = 0; i < len; i++){
					var menu = data[i];
					
					var img = "";
					if(menu.name=="市民投诉管理"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/main.png"/>';
					}else if(menu.name=="统计分析"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/charts.png"/>';
					}else if(menu.name=="投诉分布地图"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/map.png"/>';
					}else if(menu.name=="内部通知"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/insideNotice.png"/>';
					}else if(menu.name=="用户统计"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/userStatistical.png"/>';
					}else if(menu.name=="河长制信息"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/riverinfo.png"/>';
					}else if(menu.name=="投诉处理监控"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/monitor.png"/>';
					}else if(menu.name=="系统管理"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/systemManager.png"/>';
					}else if(menu.name=="河道信息"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/rivers.png"/>';
					}else if(menu.name=="污染源信息管理"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/pollresource.png"/>';
					}else if(menu.name=="公示牌"){
						img = '<img style="position:absolute;top:10px;left:5px;display:block;width:20px;height:20px;" src="../img/publicboard.png"/>';
					}
					
					//style="background:url(\''+backgroundUrl+'\') no-repeat 4px center;"
					
					//|| menu.name == "流程管理" || menu.name == "PageOffice示例"
					if(menu.name == "污染源信息管理" ||menu.name == "系统管理" || menu.name == "统计分析" ||  menu.name == "巡河记录" || menu.name == "用户统计" ||
							menu.name == "问题分类"|| menu.name == "河长制信息" ||menu.name == "河道信息" || menu.name == "投诉处理监控" || menu.name == "公示牌" || menu.name == "无效投诉信息"|| menu.name == "投诉分布地图"|| menu.name == "市民投诉管理"){
						if(menu.child && menu.subMenu != null){
							html+='<li style="position:relative;">'+img+'<a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
							html += leftSubMenu(menu.subMenu);
							html+='</li>';
						}else{
							html+='<li style="position:relative;">'+img+'<a  href="javascript:gotoPage(\''+menu.url+'\',\''+menu.name+'\');" >'+menu.name+'</a>';
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
				html+='<li class="active"><a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
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
	
	return {
		getFlowList : getFlowList,
		userlist : userlist,
		rolelist : rolelist,
		resourcselist : resourcselist,
		loginloglist : loginloglist,
		menumanager : menumanager,
		departmanager : departmanager,
		dictionarylist:dictionarylist,
		floworderlist:floworderlist,
		flowRoleselectDialog:flowRoleselectDialog,
		leftMenuBar:leftMenuBar
	};
};
$(document).ready(function(){
	window.flow = workflow();
	flow.leftMenuBar();
});

function gotoPage(url, name){
	var path = "../admin/main?menuId=";
	var poi = url.indexOf(path);
	if(poi == 0){
		var m = url.substring(path.length);
		$("#title_text").text(name);
		if ("menu_dep" == m) {
			flow.departmanager();
		} else if ("menu_user" == m) {
			flow.userlist(0);
		} else if ("menu_role" == m) {
			flow.rolelist(0);
		} else if ("menu_resourcse" == m) {
			flow.resourcselist(0);
		} else if ("menu_menu" == m) {
			flow.menumanager();
		} else if ("menu_loginLog" == m) {
			flow.loginloglist(0);
		} else if ("menu_flow_definition" == m) {
			flow.getFlowList(0,'');
		} else if ("menu_dictionary" == m) {
			flow.dictionarylist(0);
		} else if ("menu_flow_instance" == m){
			flow.floworderlist(0);
		}
	}else{ 
		window.location.href=url;
	}
}
