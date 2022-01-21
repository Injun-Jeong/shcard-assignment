let latestKrLastNm;
let latestKrFirstNm;

/** ======================= */
/** ======== 이벤트 함수 ===== */
/** ======================= */
/**
 * 함수명: onclick_btnSel
 * 설명: 조회(변환) 버튼 클릭 이벤트 처리 
 */
 function onclick_btnSel() {
	if ( isValidSelParam() ) {
		executeSbmSel();
	} else {
		alert("한글명은 필수 입력값입니다.");
	}
}


/**
 * 함수명: onclick_btnSav
 * 설명: 저정 버튼 클릭 이벤트 처리 
 */
function onclick_btnSav() {
	if ( isValidSavParam() ) {
		executeSbmSav();
	} else {
		alert("선택된 대상이 없습니다.");
	}
}



/** ======================= */
/** ======== 서브미션 ======== */
/** ======================= */
/**
 * 함수명: executeSbmSelList
 * 설명: 조회 서브미션 수행 
 */
function executeSbmSel() {
	clearSelDiv();
	
	$.ajax({
        type: "GET",
        url:"/mavenweb/svc",
        data: {
            "krLastNm": $("#krLastNm").val(),
            "krFirstNm": $("#krFirstNm").val()
        }, 
        success: function(res) {
			successSbmSel(res);	
		}, 
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.info("error");
        }
    })
}


/**
 * 함수명: executeSbmSav
 * 설명: 저장 서브미션 수행 
 */
function executeSbmSav() {
	let enNm = $("#naverEnNm").val().split(" ");
	
	$.ajax({
        type: "POST",
        url:"/mavenweb/svc",
        data: {
			"krLastNm": latestKrLastNm,
            "krFirstNm": latestKrFirstNm,
            "enLastNm": enNm[0],
            "enFirstNm": enNm[1]
        }, 
        success: function(res) {
			successSbmSav();	
		}, 
        error: function(XMLHttpRequest, textStatus, errorThrown) {
            console.info("error");
        }
    });
}



/** ======================= */
/** ======== 후처리 ========= */
/** ======================= */
/**
 * 함수명: successSbmSel
 * 설명: 조회 서브미션 후처리 
 */
function successSbmSel(res) {
	latestKrLastNm = $("#krLastNm").val();
	latestKrFirstNm = $("#krFirstNm").val();
	
	// redis 조회 결과
	setRedisResult(res.infoData);
	// naver 조회 결과
	setNaverResult(res.naverData);
}


/**
 * 함수명: successSbmSav
 * 설명: 저장 서브미션 후처리 
 */
function successSbmSav() {
	$("#krLastNm").val(latestKrLastNm);
	$("#krFirstNm").val(latestKrFirstNm);
	
	// 재조회
	executeSbmSel();
}



/** ======================= */
/** ======== 사용자 정의 함수 == */
/** ======================= */
/**
 * 함수명: clearSelDiv
 * 설명: 조회 div 초기화 
 */
function clearSelDiv() {
	$("#resultSelect").html('');
	$("#resultNaver").html('');	
}


/**
 * 함수명: setRedisResult
 * 설명: redis 조회 결과값 세팅 
 */
function setRedisResult(infoData) {
	let resultData = infoData.enLastNm + " " + infoData.enFirstNm;
	if (resultData == " ") {
		resultData = "조회 결과 없습니다.";
	}
	
	$("#resultSelect").append(resultData);
}


/**
 * 함수명: setNaverResult
 * 설명: 네이버 조회 결과값 세팅 
 */
function setNaverResult(naverData) {
	let resultData = "<select name=\"naverEnNames\" id=\"naverEnNm\"> <option value=\"\">--선택--</option>";
	for (let data of naverData) {
		let enNm = data.enLastNm + " " + data.enFirstNm
		resultData = resultData.concat(" <option value=\"" + enNm + "\">" + enNm + "</option>");
	}
	resultData = resultData.concat("</select>");
	
	$("#resultNaver").append(resultData);
}



/** ======================= */
/** ======== 검증 ========== */
/** ======================= */
/**
 * 함수명: isValidSelParam
 * 설명: 조회 서브미션 필수 입력값 검증 
 */
function isValidSelParam() {
	return $("#krLastNm").val() !== "" && $("#krFirstNm").val() !== "";  
}


/**
 * 함수명: isValidSavParam
 * 설명: 저장 서브미션 필수 입력값 검증 
 */
function isValidSavParam() {
	return $("#naverEnNm").val() !== "";
}




