


let table = document.getElementById('scheduleTable');
let newRow = table.insertRow();


for(let i=0;i<240;i++){
	let row = document.getElementById("body").insertRow();
	let id = "row"+i;
	row.setAttribute("id",id);
	row.style.height = "2px";
	for(let j=0;j<24;j++){
		if(i==j*10){
			let time = row.insertCell();
			time.setAttribute("id","jikoku"+i);
			time.setAttribute("rowSpan", 10);
		}
	}
}

let list = document.getElementById("menuData");

var result = {};


    if( 1 < window.location.search.length )
    {
        // 最初の1文字 (?記号) を除いた文字列を取得する
        var query = window.location.search.substring( 1 );


        // クエリの区切り記号 (&) で文字列を配列に分割する
        var parameters = query.split( '&' );

        for( var i = 0; i < parameters.length; i++ )
        {
            // パラメータ名とパラメータ値に分割する
            var element = parameters[ i ].split( '=' );

            var paramName = decodeURIComponent( element[ 0 ] );
			
            var paramValue = decodeURIComponent( element[ 1 ] );

            // パラメータ名をキーとして連想配列に追加する
            result[ paramName ] = paramValue;

			//整形する
		if(result[paramName].length == 1){
				result[paramName]="0"+result[paramName];
				console.log(result[paramName]);
			}
		}
    }

let date;
	
if(result[ "year" ]){
	document.getElementById("topDate").innerHTML = result[ "year" ]+"-"+result["month"]+"-"+result["date"];
	 date = new Date(result[ "year" ]+"-"+result["month"]+"-"+result["date"]);
}else{
	document.getElementById("topDate").innerHTML = result["date"];
	date = new Date(result["date"]);
}

function makeList(menuList,startingTime){
	for(i=0;i<menuList.length;i++){
			let stTime = new Date(date.toLocaleDateString()+" " + menuList[i].st);
			let ftTime = new Date(date.toLocaleDateString()+" " + menuList[i].ft);
			let dis = (ftTime.getHours() - stTime.getHours())*60+ftTime.getMinutes() - stTime.getMinutes();	
			let rowDecision = (stTime.getHours() - startingTime.getHours())*60+stTime.getMinutes() - startingTime.getMinutes();
			let mRow = document.getElementById("row"+rowDecision);	//数字に直す->開始時間を設定する。
			let momentCell = mRow.insertCell();
			let mCell = mRow.insertCell();
			let dCell = mRow.insertCell();
			let pCell = mRow.insertCell();
			mCell.innerHTML = menuList[i].menu;
			dCell.innerHTML = menuList[i].detail;
			momentCell.innerHTML = dateFormat(stTime,"HH:MM");
			pCell.innerHTML = dis;
			mCell.setAttribute("rowSpan",dis);
			momentCell.setAttribute("rowSpan",dis);
			dCell.setAttribute("rowSpan",dis);
			pCell.setAttribute("rowSpan",dis);
			
			mRow.addEventListener('click',controlMenu);
		}
		
		
}

function controlMenu(){
	let cells = this.getElementsByTagName("td");
	
	let ftHourStr = cells[1].innerHTML.substr(0,2);
	let ftMinutesInt = parseInt(cells[1].innerHTML.substr(3)) + parseInt(cells[4].innerHTML);
	console.log(ftMinutesInt);
	let ft = ftHourStr+":"+ftMinutesInt.toString();
	
	
	document.getElementById("edMenu").value = cells[2].innerHTML;
	document.getElementById("edDetail").value = cells[3].innerHTML;
	document.getElementById("edSt").value = cells[1].innerHTML;
	document.getElementById("edFt").value = ft;
	
}

$('#date').val(date);

