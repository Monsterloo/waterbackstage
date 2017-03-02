$(document).ready(function(){
	var noticelist = $("#notice_list"), noticeform = $("#notice_form");
	var totalPages = 0;/*总页数*/
	var curpage = 0, pagesize = 10;
	
	function is_weixin(){  
	   var ua = navigator.userAgent.toLowerCase();  
	   if(ua.match(/MicroMessenger/i)=="micromessenger") {  
	        return true;  
	   } else {  
	         return false;  
	   }  
	}  
	
	/**
	 * 生成通知列表
	 * @param pageno
	 * @param pagesize
	 * @returns
	 */
	function listNotice(pageno, size){
		if(!size)
			size = pagesize;
		$.get("../insidenotice/list/"+pageno+"/pagesize/"+size, function(data,status){
			if(status == "success"){
				var content = data.content;
				totalPages = data.totalPages;
				var html = '';
				for(var i=0; i<content.length; i++){
					var notice = content[i];
					html += '<div class="weui_media_box weui_media_text" style="width:85%;">';
					html += '<h4 class="weui_media_title">' + notice.title + '</h4>';
					html +='<pre style="color: #999;font-size: 13px;white-space: pre-wrap; word-wrap: break-word;">' + notice.content + '</pre>';
					if(notice.status == "2"){
						html += '<p class="weui_media_desc">发送时间：'+notice.ctime+'</p>';
						if(is_weixin()){
							html += '<ul class="weui_media_info">';
							html += '<li class="weui_media_info_meta delete_notice" style="color: #ef4f4f;cursor:pointer;" id="'+notice.id+'">删除</li>';
							html += '</ul>';
						}
						html += '</div>';
					}else{
						if(is_weixin()){
							html += '<ul class="weui_media_info">';
							html += '<li class="weui_media_info_meta modify_notice" style="color: #3cc51f;cursor:pointer;" id="'+notice.id+'">修改</li>';
							html += '<li class="weui_media_info_meta send_notice" style="color: #3cc51f;cursor:pointer;" id="'+notice.id+'">发送</li>';
							html += '<li class="weui_media_info_meta weui_media_info_meta_extra delete_notice" style="color: #ef4f4f;cursor:pointer;" id="'+notice.id+'">删除</li>';
							html += '</ul>';
						}
						html += '</div>';
					}
					
				}
				if(loading){
					noticelist.append(html);
				}else{
					noticelist.empty().append(html);
				}
				
				loading = false;
				click_modifyNotice();
				click_deleteNotice();
				click_sendNotice();
			}
		});
	}
	 listNotice(curpage, pagesize);
	
	/*滚动刷新*/
	var _height = $(document).height();
	$("#select_container").height(_height - 60);
	noticelist.height(_height - 50);
	var loading = false;
	noticelist.infinite().on("infinite", function() {
       if(totalPages == 0 || curpage == totalPages) 
    	   return;
	   if(loading) return;
	   loading = true;
	   curpage++;
	   setTimeout(function() {
		   listNotice(curpage, pagesize);
	   }, 2000);
	});
	
	/**
	 * 新增内部通知
	 */
	if(is_weixin()){
		$("#notice_add").on("click",function(){
			var data = {"id":"","title":"","content":"","sonsumes":""};
			fillNoticeForm(data);
		});
	}else{
		$("#notice_add").hide();
	}
	
	$("#notice_cancel").on("click", function(){
		$("#container").show();
		noticeform.hide();
	});
	
	
	function click_modifyNotice(){
		$(".modify_notice").each(function(index, ele){
			var jqele = $(ele);
			jqele.off("click");
			jqele.on("click",function(){
				var id = jqele.attr("id");
				$.get("../insidenotice/get/"+id, function(data, status){
					if("success" == status){
						fillNoticeForm(data);
					}
				});
			});
		});
	}
	
	/**
	 * 点击修改
	 * @param data
	 * @returns
	 */
	function fillNoticeForm(data){
		$("#id").val(data.id);
		$("#title").val(data.title);
		$("#content").html(data.content);
		displayUser(data.sonsumes);
		$("#container").hide();
		noticeform.show();
	}
	
	/**
	 * 显示选择的用户
	 * @param sonsumes
	 * @returns
	 */
	function displayUser(sonsumes){
		var html = '<div class="weui_media_box weui_media_text"><ul class="weui_media_info">';
		for(var i=0; i<sonsumes.length; i++){
			var user = sonsumes[i];
			if(user.ctype == 1){
				html += '<li class="weui_media_info_meta modify_notice" style="color: #000000;" ><i class="icon-user"></i>'+user.name+'</li>';
			}else{
				html += '<li class="weui_media_info_meta modify_notice" style="color: #000000;" ><i class="icon-user-md"></i>'+user.name+'</li>';
			}
		}
		html += '</ul></div>';
		$("#sonsumes_div").html(html);
		$("#sonsumes").val(JSON.stringify(sonsumes));
	}
	
	/**
	 * 点击删除
	 * @returns
	 */
	function click_deleteNotice(){
		$(".delete_notice").each(function(index, it){
			var ele = $(it);
			ele.off("click");
			ele.on("click", function(){
				var id = ele.attr("id");
				if(confirm("是否要删除记录？")){
					$.get("../insidenotice/delete/"+id, function(data, status){
						if("success" == status){
							$.toast("删除成功");
							listNotice(0, curpage * pagesize);
						}
					});
				}
			});
		});
	}
	
	/**
	 * 发送通知
	 * @returns
	 */
	function click_sendNotice(){
		$(".send_notice").each(function(index, it){
			var ele = $(it);
			ele.off("click");
			ele.on("click", function(){
				var id = ele.attr("id");
				if(confirm("是否要确定发送此通知？")){
					$.get("../insidenotice/send/"+id, function(data, status){
						if("success" == status){
							$.toast("发送通知成功");
							listNotice(0, curpage * pagesize);
						}
					});
				}
			});
		});
	}
	
	/**
	 * 保存通知
	 */
	$("#notice_ok").on("click", function(){
		
		var title = $("#title").val();
		var content = $("#content").val();
		var sonsumes = $("#sonsumes").val();
		var id = $("id").val();
		if(!title){
			$.toast("请填写标题", "text");
			return;
		}
		if(!content){
			$.toast("请填写通知 内容", "text");
			return;
		}
		if(!sonsumes){
			$.toask("请选择要通知用户","text");
			return;
		}
		var data = {"id":id, "title":title, "content":content,"userrole":sonsumes};
		$.post("../insidenotice/save", data,
				function(data, status){
			if("success" == status){
				$.toptip('保存成功', 'success');
				$("#container").show();
				noticeform.hide();
				listNotice(0, curpage * pagesize);
			}
		});
	});
	
	/**
	 * 填充角色列表
	 */
	$.get("../weixin/list/0/pagesize/20", function(data, status){
		if("success" == status){
			var html = '', role;
			for(var i=0; i<data.content.length; i++){
				role = data.content[i];
				html+='<label class="weui_cell weui_check_label"><div class="weui_cell_hd">';
				html+=' <input type="checkbox" class="weui_check" name="'+role.name+'" ctype="2" id="'+role.id+'">';
			    html+='<i class="weui_icon_checked"></i></div><div class="weui_cell_bd weui_cell_primary">';
			    html+='<p>'+role.name+'</p></div></label>'
			}
			$("#role_list_checkbox").html(html);
		}
	});
	
	/**
	 * 填充用户列表
	 * @param pageno
	 * @param size
	 * @returns
	 */
	var _curpageuser = 0;
	function fillUserList(pageno, size){
		$.get("../insidenotice/listuser/"+pageno+"/pagesize/"+size, function(data, status){
			if("success" == status){
				var html = '', user;
				for(var i=0; i<data.content.length; i++){
					user = data.content[i];
					html+='<label class="weui_cell weui_check_label"><div class="weui_cell_hd">';
					html+=' <input type="checkbox" class="weui_check" name="'+user.name+'" ctype="1" id="'+user.userid+'">';
				    html+='<i class="weui_icon_checked"></i></div><div class="weui_cell_bd weui_cell_primary">';
				    html+='<p>'+user.name+'</p></div></label>'
				}
				$("#user_list_checkbox").append(html);
				loading = false;
				_curpageuser++;
				if(_curpageuser < data.totalPages){
					fillUserList(_curpageuser, 20);
				}
			}
		});
	}
	
	fillUserList(_curpageuser, 20);

		
	/**
	 * 弹出选择用户和角色后，确定操作
	 */
	$("#select_ok").on("click", function(){
		var sonsumes = new Array();
		var checkboxs = $("#select_container").find("input");
		for(var i=0; i<checkboxs.length; i++){
			var box = checkboxs[i];
			if(box.checked){
				var user = {};
				user.cid = box.id;
				user.name = box.name;
				user.ctype = box.getAttribute("ctype");
				sonsumes.push(user);
			}
		}
		displayUser(sonsumes);
	});

});