

var reqPopup = document.getElementById('req-js-popup');  

  var reqBlackBg = document.getElementById('req-js-black-bg');
  var reqCloseBtn = document.getElementById('req-js-close-btn');

document.getElementById("requstGet").addEventListener("click",function(){
			if(!reqPopup) return;
			reqPopup.classList.add('req-is-show')});

reqCloseBtn.addEventListener("click",function(){
			if(!reqPopup) return;
			reqPopup.classList.remove('req-is-show')});
			


function makeRequList(requMenuList){
	for(i=0;i<requMenuList.length;i++){
			let row = document.createElement("tr");
			let menuCell = row.insertCell();
			let detailCell = row.insertCell();
			let timeCell = row.insertCell();
			
			menuCell.innerHTML = requMenuList[i].menu;
			detailCell.innerHTML = requMenuList[i].detail;
			timeCell.innerHTML = requMenuList[i].time+"分";
			
			document.getElementById("requestTable").appendChild(row);
		}
		
		
}