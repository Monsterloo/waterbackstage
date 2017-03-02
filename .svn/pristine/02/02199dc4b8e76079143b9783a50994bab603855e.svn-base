
function adminmenu(){
	/*******************生成后台管理菜单*********************/
	function leftMenuBar(){
		var loc=location.href;
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

					//|| menu.name == "流程管理" || menu.name == "PageOffice示例" 
					if(menu.name == "污染源信息管理" ||menu.name == "系统管理" || menu.name == "统计分析" ||  menu.name == "巡河记录" || menu.name == "用户统计" ||
							menu.name == "问题分类"|| menu.name == "河长制信息" ||menu.name == "河道信息" || menu.name == "投诉处理监控" || menu.name == "公示牌" || menu.name == "无效投诉信息"|| menu.name == "投诉分布地图"|| menu.name == "市民投诉管理"){
						if(menu.child){
							var isSelect="";
							var subLen=menu.subMenu.length;
							for(var j=0; j<subLen; j++){
								var sMenu = menu.subMenu[j];
								if(sMenu.url!=""&&sMenu.url!="#"&&loc.indexOf(sMenu.url.replace("../",""))>=0){isSelect =" class='active' ";}
							}
							html+='<li style="position:relative;" '+isSelect+'>'+img+'<a href="'+menu.url+'" aria-expanded="true">'+menu.name+'<span class="glyphicon arrow"></span></a>';
							html += leftSubMenu(menu.subMenu);
							html+='</li>';
						}else{
							var isSelect="";
							if(menu.url!=""&&menu.url!="#"&&loc.indexOf(menu.url.replace("../",""))>=0){isSelect ="background:#4074db; ";}
							html+='<li style="position:relative;" >'+img+'<a href="'+menu.url+'" style="'+ isSelect +'" >'+menu.name+'</a>';
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
				html+='<li><a href="'+menu.url+'" >'+menu.name+'</a>';
				html+='</li>';
			}
		}
		html += '</ul>';
		return html;
	}
	
	return {
		leftMenuBar:leftMenuBar
	};
}
/*$(document).ready(function(){
	window.menu = adminmenu();
	menu.leftMenuBar();
});*/
window.onload = function(){
	window.menu = adminmenu();
	menu.leftMenuBar();
}