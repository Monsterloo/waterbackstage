$.templates({mainTableHead:'<div id="main_table" class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:title}}</div>'+
	'<div class="panel-body"><div class="pull-right" id="sys_toolbar"><button class="btn btn-success" id="addButton" type="button" ><i class="fa fa-plus"></i> 新增</button><br/><br/></div>'+
	'<table class="table table-bordered table-hover"><thead><tr>'+
	'{{for thead}}<th>{{: #data}}</th>{{/for}}'+
	'</tr></thead><tbody id="systbody"></tbody></table>'+
	'<div class="clearfix" ><ul id="Pagination" class="pagination pagination-sm pull-right"></ul></div>'+
	'</div></div>',
historytaskTable:'{{for content}}<tr><td>{{:processName}}</td><td>{{:orderNo}}</td><td>{{:orderCreateTime}}</td><td>{{:taskName}}</td>'+
	'<td>{{:taskCreateTime}}</td></tr>{{/for}}',
mainNotTableHead:'<div id="main_table" class=\"row\"><div class="col-lg-12"><div class="panel panel-default"><div class="panel-heading">{{:#data}}</div>'+
	'<div class="panel-body" id="mainbody">'+
	'<div class="clearfix" ><ul id="Pagination" class="pagination pagination-sm pull-right"></ul></div></div></div></div></div>',
todoTaskList:'{{for content}}<a href="#" tasktype="{{:taskType}}" data-index="{{:#index}}" class="list-group-item handler_task"><span style="background-color: #337ab7;" class="badge pull-left">{{if taskType==0}}待办{{else}}协办{{/if}}</span>'+
	' {{:processName}}的{{:taskName}}单,已于：{{:taskCreateTime}}送达，请您及时处理。'+
	'<span class="pull-right"><span tasktype="{{:taskType}}" data-index="{{:#index}}" class="btn-link"><i class="fa fa-file-text"></i> 查看</span></span></a>{{/for}}',
ccTaskList:'<ul class="list-group">{{for content}}<li class="list-group-item"><span class="badge pull-left">抄送</span>'+
    '{{:processName}}在{{:createTime}}由{{:creator}}启动，目前状态为：{{if orderState == 0}}已结束{{else}}运行中{{/if}}<span class="pull-right">'+
    '<span class="btn-link" tasktype="cc" data-index="{{:#index}}"><i class="fa fa-file-text"></i> 查看</span> '+
    '<span class="btn-link" tasktype="close" data-index="{{:#index}}"><i class="fa fa-close"></i> 关闭</span></span></li>{{/for}}</ul>',
    
 approvalbodyNUll:'<div class="form-group"><label>{{:taskName}}审批结果:</label> &nbsp;&nbsp;'+
 	'<input type="radio" name="method" value="0" checked> 同意'+
    '<input type="radio" name="method" value="-1"> 不同意</div>'+
    '<div class="form-group"><label>{{:taskName}}审批意见:</label>'+
    '<textarea class="form-control" name="description" rows="2"></textarea></div>'+
    '{{if manager}}<div class="form-group"><label>总经理 : </label>&nbsp;&nbsp;{{for manager}}'+
    '<input type="checkbox" name="S_approveBossId" value="{{:id}}" checked required> {{:name}}'+
    '<input type="hidden" id="approveBossName" name="S_approveBossName" value="{{:name}}">'+
    '{{/for}}</div>{{/if}}',
approvalbody:'<div class="form-group"><label>{{:taskName}}结果:</label> {{if method==0}}同意{{else}}不同意{{/if}}</div>'+
    '<div class="form-group"><label >{{:taskName}}意见:</label> {{:description}}</div>',
 leaveOrderlistByuser:'{{for content}}<tr><td>{{:variable.reason}}</td><td>{{:variable.day}}</td><td>{{if orderState==1}}运行中{{else}}完成{{/if}}</td>'+
 	'<td><a href="#" orderid="{{:id}}" class="check-leave"><i class="fa fa-file-text"></i> 查看</a></td></tr>{{/for}}',
 	
 addLeaveOrder:'<form class="form-horizontal" role="form" id="leaveForm">'+
 	   '<div id="approval_process"></div>'+
 	   '<input type="hidden" name="processId" value="{{:processId}}" />'+
	   '<input type="hidden" name="orderId" value="{{:orderId}}" />'+
	   '<input type="hidden" name="taskId" value="{{:taskId}}" />'+
	   '<div class="form-group">'+
	   '<label for="firstname" class="col-sm-2 control-label">请假人</label>'+
	   '<div class="col-sm-10 checkbox-inline">{{:applyName}}'+
	   '<input type="hidden" id="applyId" name="S_applyId" value="{{:applyId}}">'+
	   '<input type="hidden" id="applyName" name="S_applyName" value="{{:applyName}}">'+
       '</div></div>'+
       '<div class="form-group">'+
       '<label for="lastname" class="col-sm-2 control-label">理由</label>'+
       '<div class="col-sm-10">{{if orderId == ""}}'+
       '<input type="text" class="form-control" id="reason" name="S_reason" placeholder="请输理由">'+
       '{{else}}{{:reason}}<input type="hidden" name="reason" value="{{:reason}}" />{{/if}}'+
       '</div></div>'+
       '<div class="form-group">'+
       '<label for="lastname" class="col-sm-2 control-label">天数</label>'+
       '<div class="col-sm-10">{{if orderId == ""}}'+
       '<input type="number" class="form-control" id="day" name="I_day" min="0" />'+
       '{{else}}{{:day}}<input type="hidden"name="day" value="{{:day}}"/>{{/if}}'+
       '</div></div>'+
       '<div class="form-group">'+
       '<label for="lastname" class="col-sm-2 control-label">部门经理</label>'+
       '<div class="col-sm-10">{{for manager}}'+
       '<label class="checkbox-inline">'+
       '<input type="checkbox" name="S_approveDeptId" value="{{:approveDeptId}}" checked required> {{:approveDeptName}}'+
       '<input type="hidden" id="approveDeptName" name="S_approveDeptName" value="{{:approveDeptName}}">'+
       '</label>{{/for}}'+
       '</div></div></from>',
 showLeaveOrder:'<form class="form-horizontal" role="form" id="leaveForm">'+
  '<div style="margin:0px 10px;" id="approval_process"></div></div>'+
  '<input type="hidden" name="processId" value="{{:processId}}" />'+
  '<input type="hidden" name="orderId" value="{{:orderId}}" />'+
  '<input type="hidden" name="taskId" value="{{:taskId}}" />'+
  '<div class="form-group">'+
  '<label for="firstname" class="col-sm-2">请假人</label>'+
  '<div class="col-sm-10">{{:applyName}}</div></div>'+
  '<div class="form-group">'+
  '<label for="lastname" class="col-sm-2">理由</label>'+
  '<div class="col-sm-10">{{:reason}}</div></div>'+
  '<div class="form-group">'+
  '<label for="lastname" class="col-sm-2">天数</label>'+
  '<div class="col-sm-10">{{:day}} 天</div></div></form>'
  
});



