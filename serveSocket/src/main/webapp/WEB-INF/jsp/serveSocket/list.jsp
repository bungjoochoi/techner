<%@ page language="java" contentType="text/html; charset=UTF-8"	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<head>
	<title>ServeSocket</title>

	<script type="text/javascript" class="init">
	
		jQuery(document).ready(function() {
			init();
		});

		//https://datatables.net/
		function init() {
		    $('#serveSocketList thead th').each( function () {
		        var title = $(this).text();
		        $(this).append( '<br><input type="text" placeholder="Search '+title+'" />' );
		    } );
		    
			var columns = [];
			
			var table = $('#serveSocketList').DataTable({
				ajax: {
					url: "/serveSocket/serveSocketListAjax",
					dataSrc: function (data) {
				        for (var i=0; i<data.length; i++)
				        {
				        	columns.push({
				        		'rcvDate': data[i].rcvDate
				        		, 'rcvTime': data[i].rcvTime
				        		, 'gtwayMacid': data[i].gtwayMacid
				        		, 'senseMacid': data[i].senseMacid
				        		, 'senseType': data[i].senseType
				        		, 'battryLevel': data[i].battryLevel
				        		, 'tempperature': data[i].tempperature
				        		, 'humidity': data[i].humidity
				        		, 'emgCd': data[i].emgCd
				        		, 'rcvSsi': data[i].rcvSsi 
				        		, 'xAxis': data[i].xAxis
				        		, 'yAxis': data[i].yAxis
				        		, 'zAxis': data[i].zAxis
				        		, 'fixMacid': data[i].fixMacid
				        		, 'varMacid': data[i].varMacid
				        		, 'attr1': data[i].attr1
				        		, 'attr2': data[i].attr2
				        		, 'attr3': data[i].attr3
				        		, 'insOprt': data[i].insOprt
				        		, 'insDate': data[i].insDate
				        		, 'updOprt': data[i].updOprt
				        		, 'updDate': data[i].updDate
				        	});
				        }
						return columns;
					}
				},
		        "scrollY": 200,
		        "scrollX": true,
		        "scrollCollapse": true,
		        "paging":         false,
		        details: true,
				columns: [
					{data: 'rcvDate'}
					, {data: 'rcvTime'}
					, {data: 'gtwayMacid'}
					, {data: 'senseMacid'}
					, {data: 'senseType'}
					, {data: 'battryLevel'}
					, {data: 'tempperature'}
					, {data: 'humidity'}
					, {data: 'emgCd'}
					, {data: 'rcvSsi'}
					, {data: 'xAxis'}
					, {data: 'yAxis'}
					, {data: 'zAxis'}
					, {data: 'fixMacid'}
					, {data: 'varMacid'}
					, {data: 'attr1'}
					, {data: 'attr2'}
					, {data: 'attr3'}
					, {data: 'insOprt'}
					, {data: 'insDate'}
					, {data: 'updOprt'}
					, {data: 'updDate'}
				],
				responsive: {
		    		details: {
		    			type: 'column'
		            }
		        },
		        aaSorting : [0, 'desc'],
		        language : lang_kor,
		        initComplete: function () {
		            // Apply the search
		            this.api().columns().every( function () {
		                var that = this;
		 
		                $( 'input', this.header() ).on( 'keyup change clear', function () {
		                    if ( that.search() !== this.value ) {
		                        that
		                            .search( this.value )
		                            .draw();
		                    }
		                } );
		            } );
		        }
			});
			
		    new $.fn.dataTable.FixedHeader( table );

		}
		
	</script>
</head>

<body>
	<section class="section">
		<div class="section-sub">
			<img src="../images/common/oval-1.svg" alt="" class="oval-one">
			<img src="../images/common/shape-1.svg" alt="" class="shape-three">
			<img src="../images/common/shape-55.svg" alt="" class="shape-four">
			<img src="../images/common/shape-56.svg" alt="" class="shape-five">
			<img src="../images/common/shape-57.svg" alt="" class="shape-six">
			<img src="../images/common/shape-58.svg" alt="" class="shape-seven">
			<img src="../images/common/shape-59.svg" alt="" class="shape-eight">
			<img src="../images/common/shape-60.svg" alt="" class="shape-nine">
			<img src="../images/common/shape-61.svg" alt="" class="shape-ten">
			<img src="../images/common/shape-62.svg" alt="" class="shape-eleven">

			<div class="sub-hero-wrap">
				<p class="sub-hero-text"><span class="core-color">Data</span> <i class="icon icon-question"></i></p>
				<p class="sub-hero-text2"></p>
			</div>
		</div>
	</section>

	<section class="section sub-contents">
	
		<!-- Begin page content -->
		<div class="container">
	
			<div class="data-table-wrap">
				<table id="serveSocketList" class="table table-striped table-bordered text-center" style="width:100%">
					<thead>
						<tr>
							<th scope="col">수신일자</th>
							<th scope="col">수신시간</th>
							<th scope="col">Gateway Mac Address</th>
							<th scope="col">센스 Mac Address</th>
							<th scope="col">센스 Type</th>
							<th scope="col">배터리잔량</th>
							<th scope="col">온도</th>
							<th scope="col">습도</th>
							<th scope="col">Emergency Code</th>
							<th scope="col">수신감도</th>
							<th scope="col">X축</th>
							<th scope="col">Y축</th>
							<th scope="col">Z축</th>
							<th scope="col">고정 MAC ID</th>
							<th scope="col">가변 MAC ID </th>
							<th scope="col">ATTR1</th>
							<th scope="col">ATTR2</th>
							<th scope="col">ATTR3</th>
							<th scope="col">생성자ID</th>
							<th scope="col">생성일시</th>
							<th scope="col">수정자ID</th>
							<th scope="col">수정일시</th>
						</tr>
					</thead>
					<tbody id="serveSocketList">
					</tbody>
				</table>
			</div>
				
			
		</div>
	</section>
	
</body>

</html>