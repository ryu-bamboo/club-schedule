function findUser(){
	if(document.getElementById("pass").value != document.getElementById("passCheck").value){
			alert("パスワードが一致しません。");
		return;
	}
	
	for(let i=0; i<dataList.length;i++){
		
		if(document.getElementById("name").value == dataList[i].name){
			alert("この名前は既に使用されています。")
		return;
	}
	}
	document.myForm.submit();
}

document.getElementById("register").addEventListener("click",findUser);