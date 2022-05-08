
//전화번호 형식 포맷
function phoneFomatter(num,type){
    var formatNum = '';
    if(num != null){
	    if(num.length==11){
	        if(type==0){
	            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-****-$3');
	        }else{
	            formatNum = num.replace(/(\d{3})(\d{4})(\d{4})/, '$1-$2-$3');
	        }
	    }else if(num.length==8){
	        formatNum = num.replace(/(\d{4})(\d{4})/, '$1-$2');
	    }else{
	        if(num.indexOf('02')==0){
	            if(type==0){
	                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-****-$3');
	            }else{
	                formatNum = num.replace(/(\d{2})(\d{4})(\d{4})/, '$1-$2-$3');
	            }
	        }else{
	            if(type==0){
	                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-***-$3');
	            }else{
	                formatNum = num.replace(/(\d{3})(\d{3})(\d{4})/, '$1-$2-$3');
	            }
	        }
	    }
    }
    console.log('formatNum >>> ' , formatNum);
    return formatNum;
}

//사업자번호 형식 포맷
function businessFomatter(num){
    var formatNum = '';
    if(num != null){
	    if(num.length==10){
	        formatNum = num.replace(/(\d{3})(\d{2})(\d{5})/, '$1-$2-$3');
	    }
    }
    return formatNum;
}

//숫자 콤마 찍기
function comma(num){
    var len, point, str; 
       
    num = num + ""; 
    point = num.length % 3 ;
    len = num.length; 
   
    str = num.substring(0, point); 
    while (point < len) { 
        if (str != "") str += ","; 
        str += num.substring(point, point + 3); 
        point += 3; 
    } 
     
    return str;
 
}


//DataTables Default
var lang_eng = {
    "decimal" : "",
    "emptyTable" : "No data available in table",
    "info" : "Showing _START_ to _END_ of _TOTAL_ entries",
    "infoEmpty" : "Showing 0 to 0 of 0 entries",
    "infoFiltered" : "(filtered from _MAX_ total entries)",
    "infoPostFix" : "",
    "thousands" : ",",
    "lengthMenu" : "Show _MENU_ entries",
    "loadingRecords" : "Loading...",
    "processing" : "Processing...",
    "search" : "Search : ",
    "zeroRecords" : "No matching records found",
    "paginate" : {
        "first" : "First",
        "last" : "Last",
        "next" : "Next",
        "previous" : "Previous"
    },
    "aria" : {
        "sortAscending" : " :  activate to sort column ascending",
        "sortDescending" : " :  activate to sort column descending"
    }
};

// Korean
var lang_kor = {
    "decimal" : "",
    "emptyTable" : "데이터가 없습니다.",
    "info" : "_START_ - _END_ (총 _TOTAL_ 개)",
    "infoEmpty" : "0개",
    "infoFiltered" : "(전체 _MAX_ 개 중 검색결과)",
    "infoPostFix" : "",
    "thousands" : ",",
    "lengthMenu" : "_MENU_ 개씩 보기",
    "loadingRecords" : "로딩중...",
    "processing" : "처리중...",
    "search" : "검색 : ",
    "zeroRecords" : "검색된 데이터가 없습니다.",
    "paginate" : {
        "first" : "첫 페이지",
        "last" : "마지막 페이지",
        "next" : "다음",
        "previous" : "이전"
    },
    "aria" : {
        "sortAscending" : " :  오름차순 정렬",
        "sortDescending" : " :  내림차순 정렬"
    }
};

//날짜 포맷
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};


//콤보 자동생성

function commSelect(objStr,isAll,params){
	$.ajax({
		type: 'POST',
		url: '/common/code/'+objStr+'ListAjax',
		data: params,
		dataType: 'json',
		success: function (data) {
			if(isAll) {
				$('#'+objStr).append("<option value=''>-- 전체 --</option>");
			} else {
				$('#'+objStr).append("<option value=''>-- 선택 --</option>");
			}
			for (var i = 0; i < data.result.length; i++) {
				$('#'+objStr).append("<option value='" + data.result[i]['id'] + "'>" + data.result[i]['text'] + "</option>");
			}
		}
	});		
}

function makeSelect(objStr, mg, isAll){
	$.ajax({
		type: 'POST',
		url: '/common/code/'+objStr+'ListAjax',
		//data: params,
		dataType: 'json',
		//contentType : "application/json; charset=UTF-8",
		success: function (data) {
			if(isAll) {
				$('#'+objStr).append("<option value='0'>-- 전체 --</option>");
			} else {
				$('#'+objStr).append("<option value=''>-- 선택 --</option>");
			}
			for (var i = 0; i < data.result.length; i++) {
				if(data.result[i]['id'] == mg){
					$('#'+objStr).append("<option value='" + data.result[i]['id'] + "' selected='selected'>" + data.result[i]['text'] + "</option>");
				}
				else{
					$('#'+objStr).append("<option value='" + data.result[i]['id'] + "'>" + data.result[i]['text'] + "</option>");
				}
			}
		}
	});		
}

function etcSelect(objStr, mg, isAll, selectVal){
	var params = {};
	params.id = selectVal;
	$.ajax({
		type: 'POST',
		url: '/common/code/'+objStr+'Select',
		data: params,
		dataType: 'json',
		//contentType : "application/json; charset=UTF-8",
		success: function (data) {
			if(isAll) {
				$('#'+objStr).append("<option value=''>-- 전체 --</option>");
			}
			for (var i = 0; i < data.result.length; i++) {
				if(data.result[i]['id'] == mg){
					$('#'+objStr).append("<option value='" + data.result[i]['id'] + "' selected='selected'>" + data.result[i]['text'] + "</option>");
				}
				else{
					$('#'+objStr).append("<option value='" + data.result[i]['id'] + "'>" + data.result[i]['text'] + "</option>");
				}
			}
		}
	});		
}

var markingErrorField = function (response) {
    var errorFields = response.responseJSON.errors;

    if(!errorFields){
        alert(response.response.message);
        return;
    }

    var $field, error;
    for(var i=0, length = errorFields.length; i<length;i++){
        error = errorFields[i];
        $field = $('#'+error['field']);

        if($field && $field.length > 0){
            $field.siblings('.error-message').remove();
            $field.after('<span class="error-message  text-danger">'+error.defaultMessage+'</span>');
            if(i==0){
            	$('html').scrollTop(0);
            }
        }
    }
};

var markingErrorFieldName = function (response) {
	var errorFields = response.responseJSON.errors;
	
	if(!errorFields){
		alert(response.response.message);
		return;
	}
	
	var $field, error;
	for(var i=0, length = errorFields.length; i<length;i++){
		error = errorFields[i];
		$field = $('[name="'+error['field']+'"]');
		
		if($field && $field.length > 0){
			$field.siblings('.error-message').remove();
			$field.after('<span class="error-message  text-danger">'+error.defaultMessage+'</span>');
			if(i==0){
				$('html').scrollTop(0);
            }
		}
	}
};


function testSelect(unm,optname,currname){
	if(unm == currname){
		$("select[name='"+optname+"'] option[value='1']").prop('selected',true);
		$("select[name='"+optname+"'] option[value='2']").prop('disabled',true);

	}else{
		$("select[name='"+optname+"'] option[value='2']").prop('disabled',false);
	}
};