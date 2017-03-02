$.templates({sysmanager:'<div id="main_table" class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:title}}</div>'+
	'<div class="panel-body"><div class="pull-right" id="sys_toolbar">'+
	'<button class="btn btn-success" id="addButton" type="button" ><i class="fa fa-plus"></i> 新增</button>'+
	'<button class="btn btn-success" id="exportButton" style="display:none;" type="button" ><i class="fa fa-plus"></i> 导出</button><br/><br/></div>'+
		'<table class="table table-bordered table-hover"><thead><tr>'+
		  '{{for thead}}<th>{{: #data}}</th>{{/for}}'+
		  '</tr></thead><tbody id="systbody"></tbody></table>'+
		  '<div class="clearfix" ><ul id="Pagination" class="pagination pagination-sm pull-right"></ul></div>'+
	'</div></div>',
	tablehead:'<table class="table table-bordered table-hover"><thead><tr>'+
	  '{{for thead}}<th>{{: #data}}</th>{{/for}}'+
	  '</tr></thead><tbody id="systbody"></tbody></table>',
	  usertoolbar:'<div><form class="navbar-form navbar-left" id="userForm1" role="search"><div class="form-group"><input type="text" id="username" value="{{:username}}" name="username" class="form-control" placeholder="用户名"> '+
		'</div> <button type="submit" class="btn btn-success"><span class="fa fa-search"></span>查询</button>'+
		'<div class="pull-right" id="sys_toolbar">'+
		'<button class="btn btn-success" id="addButton" type="button" ><i class="fa fa-plus"></i> 新增</button>'+
		'<button class="btn btn-success" id="exportButton" style="display:none;" type="button" ><i class="fa fa-plus"></i> 导出</button><br/><br/></div>'+
		'</form></div>',
	usertable:'{{for content}}<tr><td>{{:username}}</td><td>{{:createtime}}</td><td>{{:displayName}}</td>'+
	'<td>{{:email}}</td><td>{{:phone}}</td><td>{{if admin}}是{{else}}否{{/if}}</td><td>{{if locked}}锁定{{else}}正常{{/if}}</td>'+
	'<td>{{if !admin}}<a href="#"  data-index="{{:#getIndex()}}" class="fundlocked-user">{{if locked}}<span class="fa fa-unlock fa-lg"></span> 解锁{{else}}<span class="fa fa-lock fa-lg"></span> 锁定{{/if}}</a>&nbsp;&nbsp;'+
	'<a href="#"  data-index="{{:#getIndex()}}" class="addRoleToUser"><span class="fa fa-tags"></span>&nbsp;添加角色</a>&nbsp;&nbsp;'+
	'<a href="#" data-index="{{:#getIndex()}}" style="color: red;" class="romover-user"><span class="fa fa-remove"></span> 删除</a>&nbsp;'+
	'&nbsp;&nbsp;{{/if}}</td></tr>{{/for}}<form id="exportForm" method="post" action="../swjUser/exportUser"><input name="area" id="areaExport" type="hidden"><input name="name" id="nameExport" type="hidden"></form>',
	adduser:'<form id="userForm" class="form-horizontal" role="form">'+
			'<input id="userId" name="id" type="hidden"/>'+
			'<div class="form-group">'+
			'<label class="col-sm-2 control-label"><span style="color: red;">*</span>登录名   </label><div class="col-sm-10">'+
			'<input id="loginname" name="loginname" type="text" class="form-control" placeholder="请输入登录名"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label"><span style="color: red;">*</span>密码 </label><div class="col-sm-10">'+
			'	<input id="password" name="password" type="password"  class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label"><span style="color: red;">*</span>确认密码</label><div class="col-sm-10">'+
			'	<input id="confirm_password" name="confirm_password" type="password" class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label"><span style="color: red;">*</span>姓名 </label><div class="col-sm-10">'+
			'	<input id="displayName" name="name" type="text" class="form-control"/>'+
			'</div></div>'+
			/*'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">customerId</label><div class="col-sm-10">'+
			'	<input id="customerId" name="customerId" type="text" class="form-control"/>'+
			'</div></div>'+*/
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">邮箱 </label><div class="col-sm-10">'+
			'	<input id="email" name="email"   type="text" class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label"><span style="color: red;">*</span>行政区域</label><div class="col-sm-10">'+
			'<select id="areaSelect" class="form-control" name="area"></select>'+
			//'	<input id="area" name="area" type="text" class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">职务 </label><div class="col-sm-10">'+
			'	<input id="job" name="job" type="text"  class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label  class="col-sm-2 control-label"><span style="color: red;">*</span>电话 </label><div class="col-sm-10">'+
			'	<input id="phone" name="phone" type="text" class="form-control"/>'+
			'</div></div></form>',
		edituser:'<form id="userForm" class="form-horizontal" role="form">'+
			'<input id="userId" name="id" value="{{:userid}}" type="hidden"/>'+
			'<div class="form-group">'+
			'<label class="col-sm-2 control-label">登录名</label><div class="col-sm-10">'+
			'<input id="username" name="loginname" value="{{:username}}" type="text" class="form-control" placeholder="请输入用户名 "/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">密码 </label><div class="col-sm-10">'+
			'	<input id="password" name="password" value="{{:password}}" type="password"  class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">确认密码</label><div class="col-sm-10">'+
			'	<input id="confirm_password" name="confirm_password" type="password" value="{{:password}}" class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">姓名 </label><div class="col-sm-10">'+
			'	<input id="displayName" name="name" value="{{:displayName}}" type="text" class="form-control"/>'+
			'</div></div>'+
			/*'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">customerId</label><div class="col-sm-10">'+
			'	<input id="customerId" name="customerId" type="text" class="form-control"/>'+
			'</div></div>'+*/
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">邮箱 </label><div class="col-sm-10">'+
			'	<input id="email" name="email" value="{{:email}}"   type="text" class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">行政区域</label><div class="col-sm-10">'+
			'<select id="areaSelect" class="form-control" name="area" value="{{:areaSelect}}"></select>'+
			//'	<input id="area" name="area" type="text" class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label class="col-sm-2 control-label">职务 </label><div class="col-sm-10">'+
			'	<input id="job" name="job" type="text" value="{{:job}}"  class="form-control"/>'+
			'</div></div>'+
			'<div class="form-group">'+
			'	<label  class="col-sm-2 control-label">电话 </label><div class="col-sm-10">'+
			'	<input id="phone" name="phone" type="text" value="{{:phone}}" class="form-control"/>'+
			'</div></div></form>',
  roletable : '{{for content}}<tr><td>{{:name}}</td><td>{{:desp}}</td>'+
			'<td><a href="#" data-index="{{:#index}}" class="modify-role"><span class="fa fa-pencil"></span> 修改</a>&nbsp;&nbsp;'+
			'<a href="#" data-index="{{:#index}}" class="rolebelongsUser"><span class="fa fa-user"></span> 所属用户</a>&nbsp;&nbsp;'+
			'<a href="#" data-index="{{:#index}}" class="rolebelongsRes"><span class="fa fa-link"></span> 角色权限</a>&nbsp;&nbsp;'+
			'<a href="#" data-index="{{:#index}}" style="color:red;" class="romover-role"><span class="fa fa-remove"></span> 删除</a></td></tr>{{/for}}',
  addrole : '<form id="roleForm" role="form">'+
  			'<input name="id" value="{{:id}}" type="hidden"/>'+
		    '<div class="form-group">'+
			'<label>角色名</label>'+
			'<input id="name" name="name" type="text" value="{{:name}}" class="form-control"/>'+
			'</div>'+
			'<div class="form-group">'+
			'<label for="name">描述</label>'+
			'<textarea name="desp" id="desp" class="form-control" rows="3">{{:desp}}</textarea>'+
			'</div></form>',
 rolebelong:'{{if users}}{{for users}}<h3><span class="label label-success">{{:displayName}}</span></h3>{{/for}}{{else}}还没有用户加入{{/if}}',
 
 resourcestable:'{{for content}}<tr><td>{{:#index +1}}</td><td>{{:name}}</td><td>{{:resobj}}</td>'+
	'<td><a href="#" data-index="{{:#index}}" class="modify-resources"><span class="fa fa-pencil"></span> 修改</a>&nbsp;&nbsp;'+
	'<a href="#" data-index="{{:#index}}" style="color:red;" class="romover-resources"><span class="fa fa-remove"></span> 删除</a></td></tr>{{/for}}',
 addresources:'<form id="resForm" role="form">'+
	'<input name="id" value="{{:id}}" type="hidden"/>'+
    '<div class="form-group"><label for="name">名称</label>'+
	'<input id="name" name="name" type="text" value="{{:name}}" class="form-control"/></div>'+
	'<div class="form-group"><label for="resobj">资源对象</label>'+
	'<input id="resobj" name="resobj" type="text" value="{{:resobj}}" class="form-control"/>'+
	'</div></form>',
 loginlogtable:'{{for content}}<tr><td>{{:#index+1}}</td><td>{{:loginName}}</td><td>{{:ip}}</td><td>{{:loginTime}}</td><td>'+
	'<a href="#" data-index="{{:#index}}" style="color:red;" class="romover-loginlog"><span class="fa fa-remove"></span>删除</a></td></tr>{{/for}}',
 logtoolbar:'<div><form class="navbar-form navbar-left" id="queryForm" role="search"><div class="form-group"><input type="text" id="stime" value="{{:stime}}" name="stime" class="form-control" placeholder="开始日期"> '+
	'<input type="text" id="etime" name="etime" value="{{:etime}}" class="form-control" placeholder="结束日期"></div> <button type="submit" class="btn btn-success"><span class="fa fa-search"></span>查询</button>'+
	'</form></div>',
 menumanager:'<div class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:#data}}</div>'+
    '<div class="panel-body" ><div class="pull-left">'+
	'<button type="button" class="btn btn-success btn-sm" id="menu_create"><i class="fa fa-asterisk"></i> 新建</button>&nbsp;&nbsp;'+
	'<button type="button" class="btn btn-warning btn-sm" id="menu_rename"><i class="fa fa-pencil"></i> 修改</button>&nbsp;&nbsp;'+
	'<button type="button" class="btn btn-danger btn-sm" id="menu_delete"><i class="fa fa-remove"></i> 删除</button></div><br/><br/>'+
 	'<div id="treemanager"></div></div></div>',
  addmenu:'<form id="menuForm" class="form-horizontal" role="form">'+
	'<input name="id" value="{{:id}}" type="hidden"/><input name="parentId" value="{{:parentId}}" type="hidden"/>'+
    '<div class="form-group">'+
	'<label class="col-sm-2 control-label">名称</label><div class="col-sm-10">'+
	'<input id="name" name="name" type="text" value="{{:name}}" class="form-control"/></div></div>'+
	'<div class="form-group"><label class="col-sm-2 control-label">URL</label><div class="col-sm-10">'+
	'<input id="url" name="url" type="text" value="{{:url}}" class="form-control"/></div></div>'+
	'<div class="form-group"><label class="col-sm-2 control-label">显示顺序</label><div class="col-sm-10">'+
	'<input id="order" name="order" type="number" value="{{:order}}" min="1" class="form-control"/></div></div>'+
	'<div class="form-group"><div class="col-sm-offset-2 col-sm-10"><div class="checkbox"><label>'+
	'<input id="display" name="display" type="checkbox" {{if display}} checked {{/if}} />是否显示</div></div></div>'+
	'</form>',
departmentmanager:'<div id="permission_role" class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:#data}}</div>'+
 	'<div class="panel-body" ><div class="row"><div class="col-md-4"><div class="panel panel-info"> '+
	'<div class="panel-heading"><h3 class="panel-title">公司组织架构树</h3></div><div class="panel-body"><div class="pull-left">'+
	'<button type="button" class="btn btn-success btn-sm" id="depart_create"><i class="fa fa-asterisk"></i> 新建</button>&nbsp;&nbsp;'+
	'<button type="button" class="btn btn-warning btn-sm" id="depart_rename"><i class="fa fa-pencil"></i> 修改</button>&nbsp;&nbsp;'+
	'<button type="button" class="btn btn-danger btn-sm" id="depart_delete"><i class="fa fa-remove"></i> 删除</button></div><br/><br/>'+
	'<div id="treemanager"></div></div></div>'+
	'</div><div class="col-md-8">'+
	'<div class="panel panel-info"> <div class="panel-heading"><h3 class="panel-title">部门成员列表</h3></div><div class="panel-body">'+
	'<div class="pull-right" ><button class="btn btn-success btn-sm" id="departuser_add" type="button" ><i class="fa fa-asterisk"></i> 新增成员</button> '+
	'<button type="button" class="btn btn-danger btn-sm" id="departuser_delete"><i class="fa fa-remove"></i> 删除成员</button></div><br/><br/>'+
	'<div id="departmentuser"></div></div></div>'+
	'</div></div></div></div>',
 departmentusertable:'{{for #data}}<tr><td>{{:displayName}}</td><td>{{:email}}</td><td>{{:phone}}</td><td><input type="checkbox" value="{{:userId}}" /></td>{{/for}}',
 adddepart:'<form id="departForm" class="form-horizontal" role="form">'+
	'<input name="id" value="{{:id}}" type="hidden"/><input name="parentId" value="{{:parentId}}" type="hidden"/>'+
    '<div class="form-group">'+
	'<label class="col-sm-2 control-label">名称</label><div class="col-sm-10">'+
	'<input id="name" name="name" type="text" value="{{:name}}" class="form-control"/></div></div>'+
	'</form>',
 adduserdepartdialog:'<div>用户名<input type="text" name="selname" id="selname" style="width:80px;"><button type="button" class="funded-user" id="funded-user">查询</button></div>{{for content}}<label class="btn btn-info"><input type="checkbox" value="{{:id}}"/>{{:name}}</label> {{/for}}',
 processlist:'<div class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">流程定义列表</div>'+
	'<div class="panel-body"><div class="pull-right"><input id="displayName" type="text" placeholder="流程名称...">&nbsp;&nbsp;'+
 	'<button id="flow_query" type="button" class="btn btn-default"><i class="fa fa-search"></i> 查询</button>&nbsp;&nbsp;'+
	'<button type="button" class="btn btn-success" id="flow_design"><i class="fa fa-edit"></i> 流程设计</button>&nbsp;&nbsp;'+
	'<button id="flow_deploy" type="button" class="btn btn-success"><i class="fa fa-cloud-upload"></i> 流程部署</button></div>'+
	'<br/><br/><table id="processTable" class="table table-bordered table-hover"><thead><tr><th>流程名称</th><th>状态</th><th>版本</th><th>创建时间</th><th>操作</th></tr></thead>'+
	'<tbody>{{for content}}<tr><td>{{:displayName}}</td><td>{{if state===1}}启用{{else}}禁用{{/if}}</td><td>{{:version}}</td><td>{{:createTime}}</td>'+
	'<td><a href="#" data-index="{{:#index}}" class="processdesign"><span class="fa fa-edit"></span> 设计</a>&nbsp;&nbsp;'+
	'<a href="#" data-index="{{:#index}}"  class="processedit"><span class="fa fa-pencil"></span> 修改</a>&nbsp;&nbsp;'+
	'{{if state===1}}<a href="#" data-index="{{:#getIndex()}}" class="processdelete" style="color: red;"><span class="fa fa-trash"></span> 禁用 </a>{{/if}}</tr>{{/for}}</tbody></table>'+
	'<div class="clearfix" ><ul id="Pagination" class="pagination pagination-sm pull-right"></ul></div>'+
	'</div>',
 processedit:'<div class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">流程修改</div>'+
	'<div class="panel-body"><form id="editForm" class="form-inline" role="form" method="post"> <div class="form-group">'+
    ' <label for="inputfile">流程定义文件(必需)</label>'+
    '<input type="file" id="snakerFile" name="snakerFile" required>'+
    '<input type="hidden" name="id" value="{{:id}}" /></div><button type="submit" class="btn btn-success"><i class="fa fa-upload"></i> 上传文件</button></form>'+
    '<br/><pre>{{:content}}</pre></div>',
 processdesigner:'<div class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">流程实设计工具</div>'+
	'<div class="panel-body"><iframe id="flowiframe" src="../admin/flow/designer?processId={{:#data}}" scrolling="no" width="100%" height="750px" frameborder="no" border="0" marginwidth="0" marginheight="0"></div>',
 processdeploy:'<form id="deployForm" role="form" method="post"> <div class="form-group">'+
   '<label for="inputfile">流程定义文件(必需)</label><input type="file" id="snakerFile" name="snakerFile" required></div></form>',
 addpermission:'<div id="permission_role" class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:#data}}</div>'+
   '<div class="panel-body" ><div class="pull-right">'+
	'<button type="button" class="btn btn-success btn-sm" id="permission_create"><i class="fa fa-check"></i> 确定</button>&nbsp;&nbsp;'+
	'<button type="button" class="btn btn-warning btn-sm" id="permission_cencal"><i class="fa fa-reply"></i> 取消</button></div><br/><br/>'+
	'<div class="row"><div class="col-md-6">'+
	'<div class="panel panel-info"> <div class="panel-heading"><h3 class="panel-title">资源列表</h3></div><div class="panel-body" id="permissionRes"> </div></div>'+
	'</div><div class="col-md-6">'+
	'<div class="panel panel-info"> <div class="panel-heading"><h3 class="panel-title">菜单项</h3></div><div class="panel-body" id="permissionMenu"> </div></div>'+
	'</div></div></div></div>',
	 addResToRolelist:'<ul class="list-group">{{for content}}<li class="list-group-item">'+
		'<label><input type="radio" name ="radio"  value="{{:id}}"> {{:desp}}</label></li>{{/for}}</ul>'+
		'<div class="clearfix" ><ul id="respage" class="pagination pagination-sm pull-right"></ul></div>',
		/*addResToRolelist:'<ul class="list-group">{{for content}}<li class="list-group-item">'+
	'<label><input type="checkbox" value="{{:id}}"> {{:desp}}</label></li>{{/for}}</ul>'+
	'<div class="clearfix" ><ul id="respage" class="pagination pagination-sm pull-right"></ul></div>',*/
 flowordertable:'{{for content}}<tr><td>{{:process.displayName}}</td><td>{{:createTime}}</td><td>{{:endTime}}</td><td>{{:expireTime}}</td><td>{{:creator}}</td>'+
 	'<td>{{if orderState == 0}}已结束{{else}}运行中{{/if}}</td>'+
 	'<td><a href="#" data-index="{{:#index}}" class="toview-floworder"><i class="fa fa-file-o"></i> 查看</a></td></tr>{{/for}}',
 flowtoview: '<div id="flowtoview_process" class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:title}}</div>'+
    '<div class="panel-body" ><div class="pull-right">'+
	'<button type="button" class="btn btn-warning btn-sm" id="permission_cencal"><i class="fa fa-share"></i> 返回</button></div><br/><br/>'+
	'<iframe src="../admin/flow/display?processId={{:processId}}&orderId={{:orderId}}" scrolling="no" width="100%" height="750px" frameborder="no" border="0" marginwidth="0" marginheight="0">'+
	'</div></div></div></div>',
 	

dicttable: '{{for content}}<tr><td>{{:category}}</td><td>{{:code}}</td><td>{{:name}}</td><td>{{if status==0}}可用{{else}}禁用{{/if}}</td><td>{{:parent}}</td>'+
	'<td><a href="#" data-index="{{:#index}}" class="modify-dict"><span class="glyphicon glyphicon-pencil"></span>修改</a>&nbsp;&nbsp;'+
	'<a href="#" data-index="{{:#index}}" style="color:red;" class="romover-dict"><span class="glyphicon glyphicon-remove"></span>删除</a></td></tr>{{/for}}',
adddict:'<form id="dictForm" class="form-horizontal" role="form" >'+
	'<input id="id" name="id" value="{{:id}}" type="hidden"/>'+
	'<div class="form-group">'+
	'<label class="col-sm-2 control-label">分类 </label><div class="col-sm-10">'+
	'<input id="category" name="category" value="{{:category}}" type="text" class="form-control" placeholder="请输入类别 "/>'+
	'</div></div>'+
	'<div class="form-group">'+
	'<label class="col-sm-2 control-label">编号 </label><div class="col-sm-10">'+
	'<input id="code" name="code" value="{{:code}}" type="text" class="form-control" placeholder="请输入编号"/>'+
	'</div></div>'+
	'<div class="form-group">'+
	'<label class="col-sm-2 control-label">名称 </label><div class="col-sm-10">'+
	'<input id="name" name="name" value="{{:name}}" type="text" class="form-control" placeholder="请输入名称"/>'+
	'</div></div>'+
	'<div class="form-group">'+
	'<label class="col-sm-2 control-label">状态 </label><div class="col-sm-10">'+
	'<select class="form-control" id="status"  name="status" value="{{:status}}"><option value="0" selected>可用</option><option value="1">禁用</option></select>'+
	'</div></div>'+
	'<div class="form-group">'+
	'<label class="col-sm-2 control-label">上级编号 </label><div class="col-sm-10">'+
	'<input id="parent" name="parent" value="{{:parent}}" type="text" class="form-control" placeholder="请输入上级编号 "/>'+
	'</div></div></form>'});

