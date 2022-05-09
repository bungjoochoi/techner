<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header class="header">
    <div class="container">
      <div class="header-logo">
      </div>
      <nav class="navbar navbar-expand-md navbar-light">

        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
          <span class="navbar-toggler-icon"></span>
        </button>

        <div class="collapse navbar-collapse" data-hover="dropdown" id="navbarsExampleDefault">
          <ul class="navbar-nav mr-auto" data-hover="dropdown">
            <!-- 
            <li class="nav-item dropdown active">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown01" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">1.시스템관리 <span class="sr-only">(current)</span></a>
              <div class="dropdown-menu" aria-labelledby="dropdown01">
                <a class="dropdown-item" href="<c:url value='' />">Sample</a>
              </div>
            </li>
             -->
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown02" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">Data관리</a>
              <div class="dropdown-menu" aria-labelledby="dropdown02">
                <a class="dropdown-item" href="<c:url value='/serveSocket/list' />">Data</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown03" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">MY PAGE</a>
              <div class="dropdown-menu" aria-labelledby="dropdown03">
                <a class="dropdown-item" href="<c:url value='/user/myInfo' />">My Information</a>
                <a class="dropdown-item" href="<c:url value='/user/list' />">사용자 현황</a>
                <a class="dropdown-item" href="<c:url value='/company/list' />">회사 정보</a>
              </div>
            </li>            
            <li class="nav-item dropdown">
              <a class="nav-link dropdown-toggle" href="#" id="dropdown04" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">관리자 메뉴</a>
              <div class="dropdown-menu" aria-labelledby="dropdown04">
                <a class="dropdown-item" href="<c:url value='/admin/commonCode' />">공통코드 관리</a>
                <a class="dropdown-item" href="<c:url value='/admin/gatewayMst' />">중계기 관리</a>
                <a class="dropdown-item" href="<c:url value='/admin/senseMst' />">센스 관리</a>
                <a class="dropdown-item" href="<c:url value='/admin/iotMst' />">관리 대상물 매핑 관리</a>
              </div>
            </li>
            <li class="nav-item dropdown">
              <div class="my-status" >
                <a id="my-info" href="#" >
	                <i class="icon icon-avatar"></i>
	                <span class="navbar-my-status">${sessionScope.__USER__.uName}님 환영합니다.</span>
                </a>
                <a class="btn logout" href="#" id="logout">logout</a>
              </div>
            </li>
            
            
          </ul>
        </div>
      </nav>
    </div>
  </header> 
  
<c:if test="${csr.id > 0}">
	<c:if test="${sessionScope.__USER__.company.roleId eq '1'}">
	
	<script type="text/javascript">

	function showRedMine() {
		
        //alert("우선순위관련 수정중입니다..\n2:10까지 수정예정입니다.");
        //return;
        
        //회사명
        var cmpnm = encodeURIComponent('${csr.coName}');
		//응답자 id
        var uid = '${csr.respondent.username}';
		//응답자 email
        var umail = encodeURIComponent('${csr.respondent.email}');
		//응답자 이름
        var unm = '${csr.respondent.uName}';

		//세션id
        var lgn_id = '${sessionScope.__USER__.username}';
		//세션  email
        var lgn_mail = encodeURIComponent('${sessionScope.__USER__.email}');
		//세션 이름
        var lgn_nm = '${sessionScope.__USER__.uName}';

        //sessionScope.__USER__.company.roleId

        var gyn = true;
   
        gyn = confirm("담당자("+unm+")와 다른 사용자("+lgn_nm+")입니다. 로그인 아이디로 등록하시겠습니까?");
     
      //  unm = encodeURIComponent(unm);
        lgn_nm = encodeURIComponent(lgn_nm);

        var qa = "${csr.id}";
        var ans = "1";
        var url = "http://bizcare1.thebne.co.kr/redmine/CallRedmine.aspx";
        //var addu = "?company="+cmpnm+"&creuser="+unm+"&creusermail="+umail+"&qa_no="+qa+"&ans_no="+ans;
        var addu = "";
        if (gyn) {
            addu = "?company="+cmpnm+"&creuser="+lgn_nm+"&creusermail="+lgn_mail+"&qa_no="+qa+"&ans_no="+ans;
        } else {
            addu = "?company="+cmpnm+"&creuser="+unm+"&creusermail="+umail+"&qa_no="+qa+"&ans_no="+ans;
        }
        //alert(encodeURIComponent(cmpnm));
        //return;
        var encAddr = url+addu;
        var xrtn
        opt = ",toolbar=no,menubar=no,location=no,scrollbars=no,status=no";
        //xrtn = window.showModalDialog(encAddr, window, "dialogHeight=600px;dialogWidth=980px;scroll=no; status=no; help=no; center=yes")
        
        window.open(encAddr, window, "width=980; height=600;scroll=no; status=no; help=no; center=yes"); 
        
        var iframeObj = document.getElementById('redmineIfr');
        iframeObj.src = iframeObj.src;
        iframeObj.contentWindow.location.reload();
    }

    function hideRedMine(){
		$("#redmineIfr").toggle(2000);
    }

    $(document).ready(function() {
    	setTimeout(function() { $("#redmineIfr").hide(2000); }, 5000);
    });

	</script>
	
	
	
	
	
	
	
		<div class="redMineReal">
			<iframe id="redmineIfr" src="http://bizcare1.thebne.co.kr/redmine/GetRedmine.aspx?csrno=${csr.id}" width="114px" height="400px" frameborder="0" scrolling="no"></iframe>
			<div class="container">
				<a onclick="showRedMine(); return false;">RedMine</a>
				<br/>
				<a onclick="hideRedMine(); return false;">toggle</a>
			</div>
		</div>
		<div class="redMineBtn">
			<div class="container">
			</div>
		</div>
	</c:if>
</c:if>
  <div id="redMine" class="redMine redMine-inverse redMine-twitch">
    <div class="container">
          
<!--         <div class="redMine">
          <ul>
            <li class="full-nav"> 
                <iframe id="redmineIfr" src="http://bizcare1.thebne.co.kr/redmine/GetRedmine.aspx?csrno=" width="114px" height="400px" frameborder="0" scrolling="no"></iframe>   
            </li>
          </ul>
        </div> -->
      
      <div class="csrDesk">
        <ul class="nav redMine-nav">
          <li class="active">
            <a href="/main">
              <span class="small-nav textAC"> 
                <i class="fas fa-home"></i> 
                <dt class="font11">홈</dt> <dd></dd>
              </span>
            </a>
          </li>
          
          <li class="active">
            <a href="#" onclick="window.scrollTo(0, 300)">
              <span class="small-nav textAC"> 
                <i class="fas fa-arrow-circle-up"></i>
                <dt class="font11">TOP</dt> <dd></dd>
              </span>
            </a>
          </li>
        </ul>
      </div>
    </div>
  </div>


