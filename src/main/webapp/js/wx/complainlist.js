var pageNo=1;
var pageSize=10;

function loadMore(){

 var totalPage=$("#totalPage").val();
     pageSize=$("#pageSize").val();
 if(pageNo<totalPage){
	 //$("#more").hide();
	 //$("#loader").show();
	 pageNo++;
	 var t=new Date().getTime();
	 var url="/weixin/swjquestion/complainList";
	 $.get(url,{
		 "more":"more",
		 "pageNo":pageNo,
		 "t":t
	 },function (data){
		 if(data.length>0){
			// $("#loader").hide();
			// $("#more").show();
			 var html="";
			 var index=pageSize*(pageNo-1);
			
			 for(i=0;i<data.length;i++){
				 index++;
				 var status="";
				 var con=data[i];
				 var url="/weixin/swjquestion/questionInfo?id="+con.id;
				 var s=con.status;
				 if(s==0){
					 status="<font color='#2e9a3b'>已处理</font>";
				 }else if(s==1){
					 status="<font color='#d93a3a'>未处理</font>";
				 }else if(s==2){
					 status="<font color='#ff7e00'>处理中</font>";
				 }
				 status ="<span class='weui_cell_span'>"+status+"</span>";
				 
				 var questioncontent = con.questioncontent.substring(0,10);
				 var questiontype = con.questiontype.substring(0,8);
				 var date = new Date(con.complainDate.time).Format('yyyy-MM-dd');
				 html +=" <a class='weui_cell' href='"+url+"'><div class='weui_cell_bd weui_cell_primary'>";
				 html +="<p>"+questioncontent+"</p><p class='weui_cell_p'><span class='weui_cell_span'>"+questiontype+"</span>"+status;
				 html +="<span class='weui_cell_span'>"+date+"</span></p></div><div class='weui_cell_ft'></div></a>";
			 }
			 $("#listware").append(html);
			 $("#pageNo").text(pageNo);
		     if(pageNo>=totalPage){
		    	 complate();
		     }
		 }else{
			 complate();
		 }
		 
	 },"json");
 }else{
	 complate();
 }	
	
};


function complate(){
	$("#loadmoreinfo").hide();
    //$("#more").hide();
    //$("#complate").html("已加载完!"); 
	//$("#loadmoreinfo").fadeOut(5000);
};

Date.prototype.Format = function (fmt) { 
    var o = {
        "M+": this.getMonth() + 1, //月份 
        "d+": this.getDate(), //日 
        "h+": this.getHours(), //小时 
        "m+": this.getMinutes(), //分 
        "s+": this.getSeconds(), //秒 
        "q+": Math.floor((this.getMonth() + 3) / 3), //季度 
        "S": this.getMilliseconds() //毫秒 
    };
    if (/(y+)/.test(fmt)) fmt = fmt.replace(RegExp.$1, (this.getFullYear() + "").substr(4 - RegExp.$1.length));
    for (var k in o)
    if (new RegExp("(" + k + ")").test(fmt)) fmt = fmt.replace(RegExp.$1, (RegExp.$1.length == 1) ? (o[k]) : (("00" + o[k]).substr(("" + o[k]).length)));
    return fmt;
}
function pageInit(){
	pageNo=1;
}