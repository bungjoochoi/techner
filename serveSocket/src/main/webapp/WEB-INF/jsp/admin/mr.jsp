<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>ServeSocket</title>

<%@include file="/WEB-INF/jsp/admin/dataTableImp.jsp"%>

  <script type="text/javascript" class="init">
  var editor;

	var selectedRow = null;
  	   
 
	$(document).ready(function() {
		$('.datepicker').datepicker({
			  dateFormat: 'yy-mm-dd',
			  //todayBtn: "linked",
			  //clearBtn: true,
			  language: "kr",
			  autoclose: true,
			  todayHighlight: true,
			  toggleActive: true,
			  orientation: 'bottom auto',
		});

		
// 		var dateTable = $('#adminDateList').DataTable( {
// 			data:adminData,
// 	        stateSave: true,
// 	        language : lang_kor,
// 	        "order": [[ 0, "desc" ]],
// 	        "columns":[
// 	        	{ "data": "date" },
// 	        	{ "data": "dayName" }
// 	          ]
	        
// 	   } );

// 		new $.fn.dataTable.FixedHeader( dateTable );

		$.extend( $.fn.dataTable.Editor.display.jqueryui.modalOptions, {
			width: 600,
		    modal: true
		} );

		
		
		/*
		editor = new $.fn.dataTable.Editor({
			ajax: function ( method, url, d, successCallback, errorCallback ) {
				var output = {data:[]};
				if(d.action==='create'){
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/test1"/>',
						async:false,
						dataType:'json',
						data:{
							aaa : "success jwww"
						},
						success: function (data){

							console.log(data);
							
						},
						error: function(response){

						}					

					});
				}else if(d.action ==='edit'){
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/edit"/>',
						async:false,
						dataType:'json',
						data:{
							aaa : "success edit"
						},
						success: function (data){

							console.log(data);
							
						},
						error: function(response){

						}					

					});
				}else if ( d.action === 'remove'){
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/delete"/>',
						async:false,
						dataType:'json',
						data:{
							aaa : "success delete"
						},
						success: function (data){

							console.log(data);
							
						},
						error: function(response){
							
						}
						

					});

				}

				successCallback(output);
			},
			table:"#example",
			idSrc: "id",
		    display: 'jqueryui',
			fields:[{
					label : "firstName",
					name : "firstName"
				},
				{
					label : "position",
					name : "position"
				}
			]

		
			
		});

		$("#example").DataTable({
			dom: "Bfrtip",
			ajax: { 
				type : 'POST',
				data : {
						firstName : "aaa"
					},
				url:'<c:url value="/admin/test"/>',
				dataType:'JSON'
			},
			columns:[
				 { data:"firstName" },
		         { data: "position" }
			],
			"drawCallback": function( settings ) {
		        var api = new $.fn.dataTable.Api( settings );
		        // Output the data for the visible rows to the browser's console
		        // You might do something more useful with it!
// 		        console.log( api.rows( {page:'current'} ).data() );
				
		        
		    },
		    select: true,
		    buttons: [
	            { extend: "create", editor: editor },
	            { extend: "edit",   editor: editor },
	            { extend: "remove", editor: editor }
	        ]
			
			
		});
*/

		// ---------------------------------------------------------------------------------------------
		// ????????? ???????????? > ????????? ?????? ????????? > SAP ?????? editor
		codeUser1editor = new $.fn.dataTable.Editor({
			ajax: function ( method, url, d, successCallback, errorCallback ) {
				var output = {data:[]};
				var rowId;
				for(var key in d.data){
					rowId=key;
				}
				
				var params = d.data[rowId];
				// sap
				params.type1="1";
				if(d.action==='create'){
 					d.data[rowId].type="insert";
 					
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/codeUser1"/>',
						async:false,
						dataType:'json',
						data:params,
						success: function (data){
							codeUser1Table.ajax.reload();
						},
						error: function(response){

						}					

					});
				}else if(d.action ==='edit'){
 					d.data[rowId].type="update";
 					d.data[rowId].id=rowId;
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/codeUser1"/>',
						async:false,
						dataType:'json',
						data:params, 
						success: function (data){
							codeUser1Table.ajax.reload();
						},
						error: function(response){

						}					

					});
				}else if ( d.action === 'remove'){
 					d.data[rowId].type="delete";
 					d.data[rowId].id=rowId;
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/codeUser1"/>',
						async:false,
						dataType:'json',
						data:params,
						success: function (data){
							console.log(data);
						},
						error: function(response){
							console.log(response);
						}
					});

				}

				successCallback(output);
			},
			table:"#codeUser1",
			idSrc: "id",
		    display: 'jqueryui',
			fields:[{
					label : "??????",
					name : "mCode"
				},
				{
					label : "????????????",
					name : "mDesc"
				}
			]

		
			
		});
		
		var codeUser1Table = $("#codeUser1").DataTable({
			dom: "Bfrtip",
			"paging":false,
			ajax: { 
				type : 'POST',
				data : {
						type:"",
						type1 : "1"
					},
				url:'<c:url value="/admin/codeUser1"/>',
				dataType:'JSON'
			},
			columns:[
				 { data:"mCode" },
				 { data:"mDesc" }
			],
			"drawCallback": function( settings ) {
		        var api = new $.fn.dataTable.Api( settings );
		        // Output the data for the visible rows to the browser's console
		        // You might do something more useful with it!
// 		        console.log( api.rows( {page:'current'} ).data() );
		    },
		    select: true,
		    buttons: [
	            { extend: "create", editor: codeUser1editor },
	            { extend: "edit",   editor: codeUser1editor },
	            { extend: "remove", editor: codeUser1editor }
	        ]
		});

		//jwww  multi row ?????? ?????????
		codeUser1Table.on( 'select', function ( e, dt, type, ix ) {
		   var selected = dt.rows({selected: true});
		   if ( selected.count() > 1 ) {
		      dt.rows(ix).deselect();
		   }
		} );


		var saveArr = new Array();
		var table = $('#userCodeList').DataTable( {
	        language : lang_kor
	        } );

      
     	//??????
		<%--$("#company").select2({
			placeholder : "???????????? ????????? ???????????????",
			dropdownAutoWidth: true,
			multiple : true,
			ajax : {
				url : '/companySelectorListAjax',
				processResults : function(data) {
					return {
						results : data.result
					};
				}
			}
		});
		
		$(".company").select2({
			placeholder : "???????????? ????????? ???????????????",
			//dropdownAutoWidth: true,
			//multiple : true,
			ajax : {
				url : '/companySelectorListAjax',
				processResults : function(data) {
					return {
						results : data.result
					};
				}
			}
		});
		
		--%>

// 		function companySelect(isAll, params){
// 			console.log("??????>????");
// 			$.ajax({
// 				type: 'POST',
// 				url: '/user/companySelectorListAjax',
// 				data: params,
// 				//contentType : "application/json; charset=UTF-8",
// 				dataType: 'json',
// 				success: function (data) {
// 					console.log("??????????");
// 					if(isAll) {
// 						$('#user_Company').append("<option value=''>-- ?????? --</option>");
// 						$('#com_Company').append("<option value=''>-- ?????? --</option>");
// 					}
// 					for (var i = 0; i < data.result.length; i++) {
// 						$('#user_Company').append("<option value='" + data.result[i]['id'] + "'>" + data.result[i]['text'] + "</option>");
// 						$('#com_Company').append("<option value='" + data.result[i]['id'] + "'>" + data.result[i]['text'] + "</option>");
// 					}
// 				}
// 			});	
// 		}
		

// 		companySelect(true);
		
		$('#user_Company').select2();
		$('#com_Company').select2();
		
	      
	  	$('#searchUserBtn').click(function(e) {
		  	e.preventDefault();
						
// 	  		userModTabTable.ajax.reload();
			userModTabTable.destroy();
			$("#userModTab tbody").remove();

			userModTabTable = $("#userModTab").DataTable({
				dom: "Bfrtip",
				"pagingType": "full_numbers",
				ajax: { 
					type : 'POST',
					data : {
							type:"",
							type1 : "1",
							coName : $("#user_Company").next().find(".selection").find("[role='textbox']").text()
						},
					url:'<c:url value="/admin/userModTab"/>',
					dataType:'JSON'
				},
				columns:[
//	 				 { data:"mCode", className : 'editable' },
					 { data:"coName"},
					 { data:"usrName"},
					 { data:"uName", className : 'editable'},
					 { data:"usrNameEn", className : 'editable'},
					 { data:"usrRoleName"},
					 { data:"usrRole", className : 'editable'},
					 { data:"uTitle", className : 'editable'},
					 { data:"usrStatusName"},
					 { data:"usrStatus", className : 'editable'},
					 { data:"usrDept", className : 'editable'},
					 { data:"usrTel", className : 'editable'},
					 { data:"usrFax", className : 'editable'},
					 { data:"usrMobile", className : 'editable'},
					 { data:"usrEmail", className : 'editable'}
				],
				"drawCallback": function( settings ) {
			        var api = new $.fn.dataTable.Api( settings );
			        // Output the data for the visible rows to the browser's console
			        // You might do something more useful with it!
//	 		        console.log( api.rows( {page:'current'} ).data() );
			    },
			    select: true,
			    buttons: [
		            { extend: "create", editor: userModTabeditor },
		            { extend: "edit",   editor: userModTabeditor },
		            { extend: "remove", editor: userModTabeditor },
//	 	            { extend: "search", editor: userModTabeditor },
		            'colvis'
		        ],
		        'columnDefs':[{
					'targets':[5,7,9,11,14],
					'visible':false
		        	}
			     ]
			     
		        
			});


			


	  	});
	  	


	 	// ---------------------------------------------------------------------------------------------
		// jwww ????????? ????????????
		
		// Activate the bubble editor on click of a table cell
// 	    $('#userModTab').on( 'click', 'tbody td:not(:first-child)', function (e) {
// 	    	userModTabeditor.bubble( this );
// 	    } );
		
		$('#userModTab').on( 'click', 'tbody td.editable', function (e) {
			userModTabeditor.inline( this );
	    } );
		
		userModTabeditor = new $.fn.dataTable.Editor({
			"scrollX": true,
			ajax: function ( method, url, d, successCallback, errorCallback ) {
				var output = {data:[]};
				var rowId;
				for(var key in d.data){
					rowId=key;
				}

				// ????????? ????????? ??????..
				
				var params = d.data[rowId];
				// sap
				params.type1="1";
				if(d.action==='create'){
 					d.data[rowId].type="insert";
 					
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/userModTab"/>',
						async:false,
						dataType:'json',
						data:params,
						success: function (data){
							userModTabTable.ajax.reload();
						},
						error: function(response){

						}					

					});
				}else if(d.action ==='edit'){
 					d.data[rowId].type="update";
 					d.data[rowId].id=rowId;
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/userModTab"/>',
						async:false,
						dataType:'json',
						data:params, 
						success: function (data){
							userModTabTable.ajax.reload();
						},
						error: function(response){

						}					

					});
				}else if ( d.action === 'remove'){
 					d.data[rowId].type="delete";
 					d.data[rowId].id=rowId;
					$.ajax({
						type:'POST',
						url:'<c:url value="/admin/userModTab"/>',
						async:false,
						dataType:'json',
						data:params,
						success: function (data){
							console.log(data);
						},
						error: function(response){
							console.log(response);
						}
					});

				}

				successCallback(output);
			},
			table:"#userModTab",
			idSrc: "id",
		    display: 'jqueryui',
			fields:[{
					label : "??????",
					name : "coName"
				},
				{
					label : "?????????ID",
					name : "usrName"
				}
				,
				{
					label : "??????",
					name : "uName"
				},
				{
					label : "??????(??????)",
					name : "usrNameEn"
				},
				{
					label : "???????????????",
					name : "usrRoleName"
				},
				{
					label : "???????????????ID",
					name : "usrRole"
				},
				{
					label : "??????",
					name : "uTitle"
				},
				{
					label : "????????? ??????",
					name : "usrStatusName"
				},
				{
					label : "?????????????????????",
					name : "usrStatus"
				},
				{
					label : "???????????????",
					name : "usrDept"
				},
				{
					label : "????????????",
					name : "usrTel"
				},
				{
					label : "FAX ??????",
					name : "usrFax"
				},
				{
					label : "????????????",
					name : "usrMobile"
				},
				{
					label : "E-Mail",
					name : "usrEmail"
				}
			]

		
			
		});
		
		var userModTabTable = $("#userModTab").DataTable({
			dom: "Bfrtip",
			"pagingType": "full_numbers",
			ajax: { 
				type : 'POST',
				data : {
						type:"",
						type1 : "1",
						coName : $("#user_Company").next().find(".selection").find("[role='textbox']").text()
					},
				url:'<c:url value="/admin/userModTab"/>',
				dataType:'JSON'
			},
			columns:[
// 				 { data:"mCode", className : 'editable' },
				 { data:"coName"},
				 { data:"usrName"},
				 { data:"uName", className : 'editable'},
				 { data:"usrNameEn", className : 'editable'},
				 { data:"usrRoleName"},
				 { data:"usrRole", className : 'editable'},
				 { data:"uTitle", className : 'editable'},
				 { data:"usrStatusName"},
				 { data:"usrStatus", className : 'editable'},
				 { data:"usrDept", className : 'editable'},
				 { data:"usrTel", className : 'editable'},
				 { data:"usrFax", className : 'editable'},
				 { data:"usrMobile", className : 'editable'},
				 { data:"usrEmail", className : 'editable'}
			],
			"drawCallback": function( settings ) {
		        var api = new $.fn.dataTable.Api( settings );
		        // Output the data for the visible rows to the browser's console
		        // You might do something more useful with it!
// 		        console.log( api.rows( {page:'current'} ).data() );
		    },
		    select: true,
		    buttons: [
	            { extend: "create", editor: userModTabeditor },
	            { extend: "remove", editor: userModTabeditor },
	            'colvis'
	        ],
	        'columnDefs':[{
				'targets':[5,7,9,11,14],
				'visible':false
	        	}
		     ]
		     
	        
		});

		//jwww  multi row ?????? ?????????
		userModTabTable.on( 'select', function ( e, dt, type, ix ) {
		   var selected = dt.rows({selected: true});
		   if ( selected.count() > 1 ) {
		      dt.rows(ix).deselect();
		   }
		} );



		//========================================================================================

	  	
	  	
	  	$("#searchType").change(function() {
	       var selected = $("#searchType").children("option:selected").text();
	       $("#searchType").children("option:selected").prop("selected", "selected");
	    });
	  	
	    //????????????, ??????	    	
	    MGSelect('moduleGroupId',true);
	
	    $("#moduleGroupId").change(function(){  
			$('#moduleId').find('option').each(function() {
				$(this).remove();
			});
			var selectVal = $( this ).val();
			if(selectVal != ''){
				var a = 0
				MGSelect('moduleId', true, a , selectVal);	
			}
		});	
	  	
		//???????????????	
		var userRole;
		etcSelect('userRole', userRole, true);
        $("#userRole").select2({
			placeholder: "????????? ????????? ???????????????."
	    });
        
        var coIndu;
	    //console.log(coIndu);
	    etcSelect('industry',coIndu, true );
	    $("#industry").select2({
	    	placeholder: "??????????????? ???????????????."
	    });
	    
		
		var coRole;
		//console.log(coRole);
		etcSelect('companyRole',coRole, true );    
		$("#companyRole").select2({
			placeholder: "?????? ????????? ???????????????."
		});
		
        	    
	    $('#userCdSave1').click(function() {
			var userCodeForm = $("#userCodeForm");
			console.log(userCodeForm);
			if(confirm('????????? ????????? ?????????????????????????')) {
				userCodeForm.attr({
					"action" : "<c:url value="/admin/userCdSave"/>",
					"method" : "post"
				}).submit();
			}
		});	
	    
	    $('#userCdSave').click(function() {
	    	saveData();

			$.ajaxSettings.traditional = true;
			
			if(confirm('?????????????????????????')) {
				$.ajax({
					url: '/admin/userCdSave',
					type: 'POST',
					data: {
						saveArr: saveArr
					},
					success: function (response) {
						location.href = "/admin/bizMR";
					}
				})				
			}

	    }); 
	    
	    saveData = function() {
	    	
// 	    	var cnt = parseInt($("#userCdCnt").val());  ???
			
			//jwww
			var cnt=$('#userCodeList tbody tr').size();
	    	saveArr.push(cnt);
			$('#userCodeList tr').each(function() {
				if (!this.rowIndex) return; // skip first row
					var addParams = [];
					cnt++;
					addParams.push(
							$(this).find("#coName").val(),
							$(this).find("#username").val(), 
							$(this).find("#uName").val(),
							$(this).find("#userNameEN").val(),
							$(this).find("#roleName").val(),
							$(this).find("#uTitle").val(),
							$(this).find("#status").val(),
							$(this).find("#dept").val(),
							$(this).find("#businessDesc").val(),
							$(this).find("#tel").val(),
							$(this).find("#fax").val(),
							$(this).find("#mobile").val(),
							$(this).find("#email").val(),
							$(this).find("#stateCheck").val());	
					
					saveArr.push(addParams);

    		});
	    	
	    	
	    	console.log(saveArr);
	    }
    });	

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
				<p class="sub-hero-text">
					<span class="core-color">ServeSocket</span> ????????????
				</p>
			</div>
		</div>
	</section>
  <section class="section sub-contents">
    <div class="container">
        
	      <ul class="nav nav-tabs" id="myTab" role="tablist">
	        <li class="nav-item">
	          <a class="nav-link active" id="userTab" data-toggle="tab" href="#user" role="tab" aria-controls="user" aria-selected="true">????????? ????????????</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" id="commonCodeTab" data-toggle="tab" href="#commonCode" role="tab" aria-controls="commonCode" aria-selected="false">???????????? ??????</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" id="gatewayMstTab" data-toggle="tab" href="#gatewayMst" role="tab" aria-controls="gatewayMst" aria-selected="false">????????? ??????</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" id="senseMstTab" data-toggle="tab" href="#senseMst" role="tab" aria-controls="senseMst" aria-selected="false">?????? ??????</a>
	        </li>
	        <li class="nav-item">
	          <a class="nav-link" id="iotMstTab" data-toggle="tab" href="#iotMst" role="tab" aria-controls="iotMst" aria-selected="false">?????? ????????? ?????? ??????</a>
	        </li>
	      </ul>
      <div class="tab-content" id="adminContent">

        <div class="tab-pane active" id="user" role="tabpanel" aria-labelledby="user">          
          <div class="row">
            <div class="col-md-12">              
              <div class="search-form">
                <form:form modelAttribute="userSearchForm" name="searchForm">
                  <div class="search-wrap">
                    <div class="row">
                      <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="company">??????</label>
	                      <select class="form-control" id="user_Company" name="company.coId">
	                      </select>
	                    </div>
                	  </div>

                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="modulegroup">????????????</label>
                      <select class="form-control" id="moduleGroupId" name="moduleGroup">
                      </select>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="module">??????</label>
                      <select class="form-control" id="moduleId" name="module1">
                      </select>
                    </div>
                  </div>

                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="uName">??????</label>
                      <input type="text" class="form-control" id="uName" name="uName1" placeholder="????????? ???????????????." value="">
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="tel">????????????</label>
                      <input type="text" class="form-control" id="tel" name="tel1" placeholder="??????????????? ???????????????" value="">
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="mobile">????????????</label>
                      <input type="text" class="form-control" id="mobile" name="mobile1" placeholder="????????????????????? ???????????????." value="">
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="userRole">???????????????</label>
		                <select class="form-control"  id="userRole" name="role">
	                 	</select>
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="userStatus">???????????????</label>
	                    <select class="form-control" id="userStatus" name="status">
	        				<option value="1" >??????</option>
	        				<option value="2">??????</option>
	        				<option value="3">????????????</option>
	      				</select>
                    </div>
                  </div>
                  
                  <div class="col-md-4">
                    <div class="form-group">
                      <label for="email">E-mail</label>
                      <input type="text" class="form-control" id="email" name="email1" placeholder="E-mail??? ???????????????." value="">
                    </div>
                  </div>
                </div>
              </div>

              <div class="search-btn-wrap">
                <div class="form-group">
<!--                   <button type="submit" class="btn btn-search"  id="searchUserBtn"><i class="icon icon-search"></i>??????</button> -->
                  <button class="btn btn-search"  id="searchUserBtn"><i class="icon icon-search"></i>??????</button>
                </div>
              </div>

                </form:form>

              </div>
              
              
              
              <div class="edit-box">
                <div class="table-wrapper overflow">
                  <div class="table-title">
                  </div>
<!--                   <table id="userTB" class="table dataTable edit-table table-striped table-bordered text-center" style="width:2000px"> -->
<!--                   <table class="table dataTable edit-table table-striped table-bordered text-center" style="width:2000px"> -->
                  
                  <table id="userModTab" class="display" style="width:3500px">
				        <thead>
				            <tr>
				                <th>??????</th>
				                <th>?????????ID</th>
				                <th>??????</th>
				                <th>??????(??????)</th>
				                <th>???????????????</th>
				                <th>???????????????ID</th>
				                <th>??????</th>
				                <th>???????????????</th>
				                <th>???????????????ID</th>
				                <th>???????????????</th>
				                <th>????????????</th>
				                <th>FAX??????</th>
				                <th>????????????</th>
				                <th>E-Mail</th>
				            </tr>
				        </thead>
				  </table>
                  <!-- 
                  <form:form modelAttribute="userCodeForm">
                  <input type="hidden" class="form-control" id="userCdCnt" name="userCdCnt" value="${cdCount}"/>
                  <table id="userCodeList" class="table dataTable edit-table table-striped table-bordered text-center" style="width:2000px">
                    <colgroup>
                      <col style="*;">
                      <col style="width:40px;">
                    </colgroup>
                    <thead>
                        <tr>
                            <th>??????</th>
	                        <th>????????? ID</th>
	                        <th>??????</th>
	                        <th>??????(??????)</th>
	                        <th>???????????????</th>
	                        <th>??????</th>
	                        <th>???????????????</th>
	                        <th>???????????????</th>
	                        <th>???????????????</th>
	                        <th>????????????</th>
	                        <th>Fax??????</th>
	                        <th>????????????</th>
	                        <th>E-mail</th>
	                        <th>??????</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${userList}" var="user"> 
	                  	<tr>
		                    <td><input type="text" readonly="readonly" class="form-control" id="coName" value="${user.company.coName}"> </td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="username" value="${user.username}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="uName" value="${user.uName}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="userNameEN" value="${user.userNameEN}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="roleName" value="${user.roleName}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="uTitle" value="${user.uTitle}"></td>
		                    <c:choose>
		                    	<c:when test="${user.status eq 1}">
		                    		<td><input type="text" readonly="readonly" class="form-control" id="status" value="??????"></td>
		                    	</c:when>
		                    	<c:when test="${user.status eq 2}">
		                    		<td><input type="text" readonly="readonly" class="form-control" id="status" value="??????"></td>
		                    	</c:when>
		                    	<c:otherwise>
		                    		<td><input type="text" readonly="readonly" class="form-control" id="status" value="????????????"></td>
		                    	</c:otherwise>
		                    </c:choose>		                    
		                    <td><input type="text" readonly="readonly" class="form-control" id="dept" value="${user.dept}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="businessDesc" value="${user.businessDesc}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="tel" value="${user.tel}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="fax" value="${user.fax}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="mobile" value="${user.mobile}"></td>
		                    <td><input type="text" readonly="readonly" class="form-control" id="email" value="${user.email}"></td>
		                    <td><a class="btn del-btn"><i class="fas fa-times"></i></a></td>
	                  </tr>
                  	</c:forEach>                       
                    </tbody>
                  </table>
                  </form:form> userCodeForm -->
                  
                </div>
              </div>
            </div>
          </div>
        </div>


		<!-- commonCodeTab -->
        <div class="tab-pane" id="commonCode" role="tabpanel" aria-labelledby="commonCode">
          
          <div class="row">
            <div class="col-md-12">
              
              <div class="search-form">
                <form:form modelAttribute="commonCodeSearchForm">
                  <div class="search-wrap">
                    <div class="row">
                      <div class="col-md-4">     
                      <label for="company">??????</label>                 
            	   	    <div class="form-group">
            	   	    
            	   	    <select class="form-control" id="com_Company" name="coId">
	                    </select>
	                    </div>
                	  </div>
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="modulegroup">????????????</label>
							<input type="text" class="form-control" id="companyCode" name="companyCode" placeholder="??????????????? ???????????????." value="">
                        </div>
                      </div>
                      
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="modulegroup">????????????</label>
							<input type="text" class="form-control" id="coDesc" name="coDesc" placeholder="" value="">
                        </div>
                      </div>
                      
                      <div class="col-md-4">
                        <div class="form-group">
                          <label for="modulegroup">????????????</label>
                          <select class="form-control" id="companyRole" name="companyRole">
	                      </select>
                        </div>
                      </div>

                       <div class="col-md-4">
	                    <div class="form-group">
	                      <label for="company">????????????</label>
	                      <select class="form-control" id="industry" name="industry">
	                      </select>
	                    </div>
	                  </div>
	                  
	                  <div class="col-md-4">
                        <div class="form-group">
                          <label for="modulegroup">????????????</label>
							<input type="text" class="form-control" id="email" name="email" placeholder="??????????????? ???????????????." value="">
                        </div>
                      </div>
	                  <div class="col-md-4">
                        <div class="form-group">
                          <label for="modulegroup">???????????????</label>
							<input type="text" class="form-control" id="email" name="email" placeholder="??? ??????????????? ???????????????." value="">
                        </div>
                      </div>
                    </div>
                  </div>

                  <div class="search-btn-wrap">
                    <div class="form-group">
                      <button type="submit" class="btn btn-search"><i class="icon icon-search"></i>??????</button>
                    </div>
                  </div>

                </form:form>

              </div>
              <div class="edit-box">
                <div class="table-wrapper overflow">
                  <div class="table-title">
                      <div class="row">
                          <div class="col-sm-12 text-right">
                              <button type="button" class="btn btn-inner edit-btn"><i class="fas fa-pen"></i> ??????</button>
                              <button type="button" class="btn btn-inner save-btn"><i class="fa fa-check"></i> ??????</button>
                              <button type="button" class="btn btn-inner add-btn"><i class="fa fa-plus"></i> ??????</button>
                          </div>
                      </div>
                  </div>
                  
                  <table id="commonCodeCodeList"  class="table dataTable edit-table table-striped table-bordered text-center" style="width:2000px">
                    <colgroup>
                      <col style="*;">
                      <col style="width:40px;">
                    </colgroup>
                    <thead>
                        <tr>
                          <th>????????????</th>
                          <th>?????????</th>
                          <th>?????????(En)</th>
                          <th>????????????</th>
                          <th>OrderIdx</th>
                          <th>????????????</th>
                          <th>??????</th>
                          <th>??????</th>
                          <th>??????</th>
                          <th>Tel</th>
                          <th>Fax</th>
                          <th>Date Created</th>
                          <th>Last Updated</th>
                          <th>????????????(Role)</th>
                          <th>???????????????</th>
                          <th>????????????</th>
                          <th>????????????</th>
                          <th>????????????</th>
                          <th>??????</th>
                        </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${companyList}" var="company">
                        <tr>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.companyCode}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.coName}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.coNameEN}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.coDesc}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.orderIdx}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.industry}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.businessType}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.businessCondition}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.address}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.tel}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.fax}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.dateCreated}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.lastUpdated}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.roleName}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.contractMM}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.dueDateS}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.dueDateE}"></td>
                          <td><input type="text" readonly="readonly" class="form-control" value="${company.contractStatus}"></td>
                          <td><a class="btn del-btn"><i class="fas fa-times"></i></a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                  </table>
                </div>
              </div>
            </div>

            
          </div>
        </div>
    </div> <!-- /container -->
  </section>

</body>
</html>