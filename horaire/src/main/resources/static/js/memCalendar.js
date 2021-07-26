
/*
   -----------------------------------------------------
   カレンダー（リンク機能付き）
   Ver. 1.0.2
   update 2011.3.5
   Copyright (C) WEB-JOZU  URL:http://www.web-jozu.com/
   -----------------------------------------------------
*/

function resistData(dateNum){	
	var target = document.getElementById("send"+dateNum);
	console.log("send"+dateNum);
	console.log(target);
      target.method = "get";
	target.action="http://localhost:8080/memMainform";
      target.submit();
}

function holidaySet(MM,DD,WEEK,DAY,TTL){
	holiMM[i] = MM; holiDD[i] = DD; holiWEEK[i] = WEEK; holiDAY[i] = DAY; holiTTL[i++] = TTL;
}

function linkdaySet(MM,DD,ACT,URL,TTL){
	this['link' + MM + 'MM']['ok'] = "ok";

	this['link' + MM + 'MM']['d' + DD] = new Array;
	this['link' + MM + 'MM']['d' + DD]['act'] = ACT;
	this['link' + MM + 'MM']['d' + DD]['url'] = URL;
	this['link' + MM + 'MM']['d' + DD]['ttl'] = TTL;
}

function showCalen(MM){
	calenData = createCalen(MM);
	if(document.getElementById) {
		document.getElementById('calenArea').innerHTML = calenData;
	}
}

function showTtl(MM,DD){
	if(document.getElementById && this['link' + MM + 'MM']['d' + DD]['ttl']) {
		document.getElementById('d' + MM + DD).innerHTML = '<span class="linkPopup"><span class="popArrow">△</span><span class="popTxt">' + this['link' + MM + 'MM']['d' + DD]['ttl'] + '</span>';
	}
}

function hideTtl(MM,DD){
	if(document.getElementById) {
		document.getElementById('d' + MM + DD).innerHTML = '';
	}
}

function linkGo(MM,DD){
	actObj = this['link' + MM + 'MM']['d' + DD]['act'];
	urlObj = this['link' + MM + 'MM']['d' + DD]['url'];

	if(actObj == 0 || urlObj == ""){

	}else if(actObj == 1){
		location.href = 'http://' + urlObj;

	}else if(actObj == 2){
		newWin1 = window.open('http://' + urlObj, null);
		newWin1.focus();

	}else if(actObj == 3){
		newWin2 = window.open('http://' + urlObj, 'newWin', 'width=' + winWW + ', height=' + winHH + ', menubar=yes, toolbar=yes, scrollbars=' + winSCL + '');
		newWin2.focus();
	}
}


//リンクを設定
for(i=1; i<=12; i++){
	this['link' + i + 'MM'] = new Array;
	this['link' + i + 'MM']['ok'] = "";
}

i = 0;





/* ---------- 設定領域 start ---------- */

//リンクを設定
//linkdaySet(月,日,ウィンドウ [0:リンクなし, 1:自win, 2:別win, 3:PopUpWin],リンク先,説明)

		for(i=0;i<startingTime.length;i++){
			let date = new Date(startingTime[i].date);
			linkdaySet(date.getMonth()+1,date.getDate(),2,'localhost:8080/mainform','練習日です');
		}



winSCL = "no";	//スクロール [yes, no]

winWW = 900;	//幅
winHH = 900;	//高さ

//現在の月から何ヶ月前まで表示
bfMonNm = 6;

//現在の月から何ヶ月後まで表示
afMonNm = 6;

/* ---------- 設定領域 end ---------- */
console.log(typeof(document.getElementById("setDate")));
console.log(typeof(document.getElementById("setTime")));



function dateReset(){
	//現在の日付を取得
	nowDate = new Date();
	theYear = nowDate.getFullYear();
	theMonth = nowDate.getMonth();
	theDate = nowDate.getDate();
	theDay = nowDate.getDay();

	//現在の日付を保持
	nowMonth = theMonth;
	nowYear = theYear;
}

dateReset();


//リンクの数を取得
linkNum = i;


//カレンダー表示 最後まで
function createCalen(MM){

	i = 0;
	holiMM = new Array;
	holiDD = new Array;
	holiWEEK = new Array;
	holiDAY = new Array;
	holiTTL = new Array;
	

	theMonth = MM;
	
	if(theMonth >= 12){
		theYear++;
		theMonth = 0;
	} else if(theMonth <= -1){
		theYear--;
		theMonth = 11;
	}
	
	
	//月の日数取得
	monNum = new Date(theYear, theMonth + 1, 0).getDate();
	
	//1日の曜日を取得
	firstDay = new Date(theYear, theMonth, 1).getDay();
	
	//月の週を取得
	theWeek = Math.ceil((monNum + firstDay) / 7);
	
	
	//祝日を設定
	holidaySet(1,1,0,0,'元旦');
	holidaySet(1,0,2,1,'成人の日');
	holidaySet(2,11,0,0,'建国記念の日');
	
	if(theYear%4 == 0 || theYear%4 == 1){
		holidaySet(3,20,0,0,'春分の日');
	}else{
		holidaySet(3,21,0,0,'春分の日');
	}
	
	holidaySet(4,29,0,0,'昭和の日');
	holidaySet(5,3,0,0,'憲法記念日');
	holidaySet(5,4,0,0,'みどりの日');
	holidaySet(5,5,0,0,'こどもの日');
	holidaySet(7,0,3,1,'海の日');
	holidaySet(9,0,3,1,'敬老の日');
	
	if(theYear >= 2012 && theYear <= 2044 && theYear%4 == 0){
		holidaySet(9,22,0,0,'秋分の日');
	}else{
		holidaySet(9,23,0,0,'秋分の日');
	}
	
	holidaySet(10,0,2,1,'体育の日');
	holidaySet(11,3,0,0,'文化の日');
	holidaySet(11,23,0,0,'勤労感謝の日');
	holidaySet(12,23,0,0,'天皇誕生日');
	
	//祝日の数を取得
	holiNum = i;
	

	diffY = (theYear - nowYear) * 12;
	diffM = theMonth - nowMonth;

	//月前後ボタンの表示非表示
	if(afMonNm > diffY + diffM){
		rtBtn = '<a href="javascript:showCalen(theMonth + 1); void(0);">＞</a>';
	}else{
		rtBtn = '&nbsp;';
	}

	if(bfMonNm > - diffY - diffM){
		ltBtn = '<a href="javascript:showCalen(theMonth - 1); void(0);">＜</a>';
	}else{
		ltBtn = '&nbsp;';
	}

	
	//カレンダー表示
	calenData = '';
	calenData += '<table class="calTable">';
	calenData += '<tr>';
	calenData += '<td class="btn">' + ltBtn + '</td>';
	calenData += '<td colspan="5" class="mon"><span class="yView">' + theYear + '年</span>&nbsp;' + (theMonth + 1) + '月&nbsp;</td>';
	calenData += '<td class="btn">' + rtBtn + '</td>';
	calenData += '</tr>';
	calenData += '<tr>';
	calenData += '<th>SUN</th>';
	calenData += '<th>MON</th>';
	calenData += '<th>TUE</th>';
	calenData += '<th>WED</th>';
	calenData += '<th>THU</th>';
	calenData += '<th>FRI</th>';
	calenData += '<th>SAT</th>';
	calenData += '</tr>';
	
	
	//重なり順用
	zNum = 32;
	
	dateNum = 0;
	subHoli = "off"
	nationHoli = "off";
	weekSun = 0;
	weekMon = 0;
	weekTue = 0;
	weekWed = 0;
	weekThu = 0;
	weekFri = 0;
	weekSat = 0;
	weekNum = new Array;
	for(i=0; i<=6; i++){
		weekNum[i] = 0;
	}
	
	for(i=0; i<theWeek; i++){
	
		calenData += '<tr>';
	
		for(j=0; j<7; j++){
	
			calenData += '<td';
	
			if(i == 0 && j == firstDay){
				dateNum++;
			}
	
			holiMMDDCk = "off";
			for(k=0; k<holiNum; k++){
	
				holiMMCk = "off";
				holiDDCk = "off";
				if((theMonth + 1) == holiMM[k]){
					holiMMCk = "on";
				}
	
				if(holiMMCk == "on" && dateNum != 0 && holiDD[k] == 0 && holiWEEK[k] - 1 == weekNum[holiDAY[k]] && holiDAY[k] == j){
					holiDDCk = "on";
	
					if(dateNum != 0 && holiMM[k + 1] == holiMM[k] && holiDD[k + 1] == 0 && holiWEEK[k + 1] - 1 == weekNum[holiDAY[k + 1]] && holiDAY[k + 1] == j + 2){
						nationHoli = "on";
					}else if(dateNum != 0 && holiMM[k + 1] == holiMM[k] && holiDD[k + 1] == dateNum + 2 && holiWEEK[k + 1] == 0 && holiDAY[k + 1] == 0){
						nationHoli = "on";
					}
	
				}else if(holiMMCk == "on" && dateNum != 0 && holiDD[k] == dateNum && holiWEEK[k] == 0 && holiDAY[k] == 0){
					holiDDCk = "on";
	
					if(dateNum != 0 && holiMM[k + 1] == holiMM[k] && holiDD[k + 1] == 0 && holiWEEK[k + 1] - 1 == weekNum[holiDAY[k + 1]] && holiDAY[k + 1] == j + 2){
						nationHoli = "on";
					}else if(dateNum != 0 && holiMM[k + 1] == holiMM[k] && holiDD[k + 1] == dateNum + 2 && holiWEEK[k + 1] == 0 && holiDAY[k + 1] == 0){
						nationHoli = "on";
					}
	
				}
	
				if(holiMMCk == "on" && holiDDCk == "on"){
					holiMMDDCk = "on";
					viewTtl = holiTTL[k];
				}
	
			}
	
			if(holiMMDDCk == "on"){
				if(dateNum == theDate && theMonth == nowDate.getMonth() && theYear == nowDate.getFullYear()){
					calenData += ' class="sun today" title="' + viewTtl + '"';
				}else{
					calenData += ' class="sun" title="' + viewTtl + '"';
				}
	
				if(j == 0){
					subHoli = "on";
				}
	
			}else if(nationHoli == "on"){
				if(dateNum == theDate && theMonth == nowDate.getMonth() && theYear == nowDate.getFullYear()){
					calenData += ' class="sun today" title="国民の休日"';
				}else{
					calenData += ' class="sun" title="国民の休日"';
				}
	
				nationHoli = "off";
		
			}else if(subHoli == "on"){
				if(dateNum == theDate && theMonth == nowDate.getMonth() && theYear == nowDate.getFullYear()){
					calenData += ' class="sun today" title="振替休日"';
				}else{
					calenData += ' class="sun" title="振替休日"';
				}
	
				subHoli = "off";
		
			}else if(j == 0){
				if(dateNum == theDate && theMonth == nowDate.getMonth() && theYear == nowDate.getFullYear()){
					calenData += ' class="sun today"';
				}else{
					calenData += ' class="sun"';
				}
		
			}else if(j == 6){
				if(dateNum == theDate && theMonth == nowDate.getMonth() && theYear == nowDate.getFullYear()){
					calenData += ' class="sat today"';
				}else{
					calenData += ' class="sat"';
				}
	
			}else if(dateNum == theDate && theMonth == nowDate.getMonth() && theYear == nowDate.getFullYear()){
				calenData += ' class="today"';
			}
	
			calenData += '>';
	
	
			if((i == 0 && j < firstDay) || dateNum > monNum){
				calenData += '&nbsp;';
				
			}else{
				aObj = "";
				if(this['link' + (theMonth + 1) + 'MM']['ok'] == "ok" && this['link' + (theMonth + 1) + 'MM']['d' + dateNum]){
					
					calenData += '<span style="position:relative; display:block; z-index:' + zNum-- + ';"><span id="d' + (theMonth + 1) + dateNum + '"></span><a href="javascript:linkGo(\'' + (theMonth + 1) + '\',\'' + dateNum + '\');void(0);"   onmouseover="showTtl(\'' + (theMonth + 1) + '\',\'' + dateNum + '\')" onclick="resistData('+dateNum+')" onmouseout="hideTtl(\'' + (theMonth + 1) + '\',\'' + dateNum + '\')" class="linkArea">';
					aObj = "on";
				}
				calenData += dateNum;

				if(aObj == "on"){
					calenData += '</a></span><form id="send'+dateNum+'" name="send'+dateNum+'"><input type="hidden" name="year" value='+theYear+'><input type="hidden" name="month" value='+(theMonth+1)+'><input  type="hidden" name="date" value='+dateNum+'></form>';
					aObj = "";
				}

				dateNum++;
	
				switch(j){
					case 0: weekNum[0] = ++weekSun; break;
					case 1: weekNum[1] = ++weekMon; break;
					case 2: weekNum[2] = ++weekTue; break;
					case 3: weekNum[3] = ++weekWed; break;
					case 4: weekNum[4] = ++weekThu; break;
					case 5: weekNum[5] = ++weekFri; break;
					case 6: weekNum[6] = ++weekSat; break;
				}
			}
	
			calenData += '</td>';
		}
	
		calenData += '</tr>';
	}
	
	calenData += '<tr>';
	calenData += '<td colspan="7" class="webJozu"><a href="http://www.web-jozu.com/" target="_blank" title="WEB上手 - JavaScript・PHP・Flashのサンプルや素材集">&copy;WEB上手</a></td>';
	calenData += '</tr>';
	calenData += '</table>';

	return calenData;
}
//最初から

showCalen(nowMonth);
