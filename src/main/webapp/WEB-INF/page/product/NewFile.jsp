#set($path="http://localhost:8080/shop")
<html>
<head>
<title>äº§åç±»å«ç®¡ç</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="$path/css/vip.css" type="text/css">
<script language="JavaScript">
	//å°æå®çåé¡µé¡µé¢
	function topage(page){
		var form = document.forms[0];
		form.page.value=page;
		form.submit();
	}
</script>
<script language=JavaScript src="/js/FoshanRen.js"></script>
</head>

<body bgcolor="#FFFFFF" text="#000000" marginwidth="0" marginheight="0">
<form action="/producttype/list.do" method="post"  enctype="">
	<input type="file" name="brandlogo" >
 	<input type="hidden" name="page"/>
 	<input type="hidden" name="parentid"/>
 	<input type="hidden" name="name"/>
 	<input type="hidden" name="query"/>
 	<input type="text" name="" size="" maxlength="">
 	<table width="98%" border="0" cellspacing="1" cellpadding="2" align="center">
	    <tr>
	        <td colspan="6"  bgcolor="6f8ac4" align="right"></td>
	    </tr>
	    <tr>
	      <td width="8%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ä»£å·</font></div></td>
	      <td width="5%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">ä¿®æ¹</font></div></td>
	      <td width="20%" bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">äº§åç±»å«åç§°</font></div></td>
		  <td width="10%" nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">åå»ºä¸çº§ç±»å«</font></div></td>
		  <td width="15%" bgcolor="6f8ac4"><div align="center"><font color="#FFFFFF">æå±ç¶ç±»</font></div></td>
		  <td nowrap bgcolor="6f8ac4"> <div align="center"><font color="#FFFFFF">å¤æ³¨</font></div></td>
	    </tr>
	    
	    <tr> 
	      <td bgcolor="f5f5f5" colspan="6" align="center">
	        <table width="100%" border="0" cellspacing="1" cellpadding="3">
	          <tr> 
	            <td width="5%"></td>
	            <td width="85%">
	              <textarea rows="80" cols="20"></textarea>
	               
	              <input name="AddDic" type="button" class="frm_btn" id="AddDic" onClick="javascript:window.location.href='$path/control/product/type/manage"/>?method=addUI&parentid=${param.parentid}'" value="æ·»å ç±»å«"> &nbsp;&nbsp;
				  <input name="query" type="button" class="frm_btn" id="query" onClick="javascript:window.location.href='$path/control/product/type/manage"/>?method=queryUI'" value=" æ¥ è¯¢ "> &nbsp;&nbsp;
				  
				  <input type="submit" value="">
				  <select name>
				  	<option selected="selected">d </option>
				  </select>
	            </td>
	            
	          </tr>
	        </table>
	      </td>
	    </tr>
	  </table>
 </form>
  
</body>
</html>