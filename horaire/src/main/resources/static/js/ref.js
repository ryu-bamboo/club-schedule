
// li要素を作成
var li = document.createElement('li');

function makeRefList(){
	for(let i=0;i<refList.length;i++){
		if(refList[i].date == dateFormat(startingTime, "yyyy-mm-dd")){
			// li要素を作成
			var li = document.createElement('li');
			// ul要素に追加
			li.innerHTML = refList[i].reflection;
			
			 document.getElementById("reflectionList").appendChild(li);	
		}
	}
}

var popup = document.getElementById('js-popup');  

  var blackBg = document.getElementById('js-black-bg');
  var closeBtn = document.getElementById('js-close-btn');

document.getElementById("refOpen").addEventListener("click",function(){
			if(!popup) return;
			popup.classList.add('is-show')});

closeBtn.addEventListener("click",function(){
			if(!popup) return;
			popup.classList.remove('is-show')});