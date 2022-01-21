<%@ page language="java" contentType="text/html; charset=utf-8" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>과제</title>
<script src="https://code.jquery.com/jquery-3.6.0.js" integrity="sha256-H+K7U5CnXl1h5ywQfKtSj8PCmoN9aaq30gDh27Xc0jk=" crossorigin="anonymous" charset="utf-8"></script>

</head>
<body>
	<script src="js/scripts.js" charset="utf-8"></script>
	<div>
		한글명<br>
		성: <input type="text" id="krLastNm" name="krLastNm" size="20">
		이름: <input type="text" id="krFirstNm" name="krFirstNm" size="20">
		<button type="button" id="btnSel" class="favorite styled" onclick="onclick_btnSel()"> 조회 </button>
	</div>
	<div>
		Redis 조회 결과: <div id="resultSelect"></div>
	</div>

	<div>
		<br>네이버 영어이름 변환기<br>
		<div id="resultNaver"></div>
		<button type="button" id="btnSav" class="favorite styled" onclick="onclick_btnSav()"> 저장 </button>
	</div>
</body>



<script>    
    // 특수문자 정규식 변수(공백 미포함)
    var replaceChar = /[~!@\#$%^&*\()\-=+_'\;<>0-9\/.\`:\"\\,\[\]?|{}]/gi;
 
    // 완성형 아닌 한글 정규식
    var replaceNotFullKorean = /[ㄱ-ㅎㅏ-ㅣ]/gi;
    
	$(document).ready(function(){        
        $("#krLastNm").on("focusout", function() {
            var x = $(this).val();
            if (x.length > 0) {
                if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
                    x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
                }
                $(this).val(x);
            }
            }).on("keyup", function() {
                $(this).val($(this).val().replace(replaceChar, ""));

       });
    });   
	
	$(document).ready(function(){        
        $("#krFirstNm").on("focusout", function() {
            var x = $(this).val();
            if (x.length > 0) {
                if (x.match(replaceChar) || x.match(replaceNotFullKorean)) {
                    x = x.replace(replaceChar, "").replace(replaceNotFullKorean, "");
                }
                $(this).val(x);
            }
            }).on("keyup", function() {
                $(this).val($(this).val().replace(replaceChar, ""));

       });
    });
</script>
</html>