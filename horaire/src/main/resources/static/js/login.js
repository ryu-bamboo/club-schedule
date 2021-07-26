function findUser(){
	for(let i=0; i<dataList.length;i++){
		if(document.getElementById("name").value == dataList[i].name){
			if(document.getElementById("name").value == "ryu"){
				if(document.getElementById("pass").value == "Ryu8211p"){
					location.href = "http://localhost:8080/calendar";
				}
			}else if(document.getElementById("pass").value == dataList[i].password){
				location.href = "http://localhost:8080/memCalendar";
				return;
			}else{
				alert("パスワードが間違っています");
				return;
			}
		}else{
			continue;
		}
	}
	alert("登録されていません。");
}

document.getElementById("login").addEventListener("click",findUser);
