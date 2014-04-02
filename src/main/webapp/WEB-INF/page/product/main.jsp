#set($path="http://localhost:8080/shop")
<html>
<head>
<title> 类别选择 </title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="$path/css/vip.css" type="text/css">
<SCRIPT language=JavaScript src="$path/js/FoshanRen.js"></SCRIPT>
<SCRIPT language=JavaScript src="$path/js/xmlhttp.js"></SCRIPT>

<SCRIPT language=JavaScript>
function checkIt(){
	var objForm = document.forms[0];
	var form = opener.document.forms[0];
	if (form){
		form.typeid.value = objForm.dicId.value;
		form.v_type_name.value = objForm.dicName.value;
	}
	window.close();
}
function getDicName(dicId,strDicName){
	var objForm = document.forms[0];
	objForm.dicId.value = dicId;
	objForm.dicName.value = strDicName;
}
function getTypeList(typeid){
		var typecontent = document.getElementById('typecontent');
		if(typecontent){
			typecontent.innerHTML= "数据正在加载...";
			send_request(function(value){typecontent.innerHTML=value}, '<html:rewrite action="/control/product/type/manage"/>?method=gettypelist&typeid='+ typeid, true);
		}
}
</SCRIPT>
<style>
	.inputText{
		font-family: Verdana, Arial, Helvetica, sans-serif;
		font-size: 12px;
		color: #666666;
		border: 1px solid #999999;
	}
	body {
		font-family: Georgia, "Times New Roman", Times, serif;
		font-size: 12px;
		color: #666666;
	}
</style>
</head>

<body>
>>产品类别列表,请选择分类:<br>

<form method="post" name="main" action="">
<table width="100%" border="0" cellspacing="1" cellpadding="1">
  <input type="hidden" name="dicId">
  <input type="hidden" name="dicName">
	<tr>
		<td id="typecontent">
		 #foreach($ptype in $result)
		    #if(ptype.child>0){
		     <a href="$path/product/selectUI.do?ptypeid=${ptype.typeid}">${ptype.name}</a>
		    }
		    #else
		        <input type="radio" name="ptyped" value="${ptype.typeid}">${ptype.name}
		    #end
		</td>
	</tr>
	<tr>
		<td colspan="2" align="center">
			<input type="button" name="create" value="确认" onclick="javascript:clickIt()">
			<input type="button" name="cancel" value="取消" onclick="javascript:window.close()">
	    </td>
	</tr>
</table>
</form>
</body>
</html>