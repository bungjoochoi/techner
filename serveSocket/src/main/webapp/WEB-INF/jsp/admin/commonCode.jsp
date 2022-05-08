<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<head>
<title>ServeSocket</title>
  <script type="text/javascript" class="init">
  
  $(document).ready(function() {

      jQuery("#gridlist").jqGrid({
          url : '/admin/commonCodeList',
          datatype : 'json',
          mtype : 'POST',
          colNames : ["No.", '분류코드', '분류코드명', '코드', '코드명', '사용여부', '최종 수정자ID', '최종수정일자' ],
          colModel : [                        
                      {name:'rnum', index:'rnum', width:50, editrules:{ edithidden:true },
                          editable : false,
                          search: false },
                      {name : 'classCode', index : 'classCode', width : 100, editrules : {required : true, edithidden : true},
                          editable : true,editoptions : {size : "50",maxlength : "50"},
                          editoptions: {},
                          stype: "select",
                          searchoptions: {
                              dataUrl: '/common/code/classCodeSelect',
                              buildSelect: function (data) {
                                  if(typeof(data)=='string')
                                     data = $.parseJSON(data);
                                  var rtSlt = '<select name="classCode">';
                                  for ( var idx = 0 ; idx < data.length ; idx ++) {
                                      rtSlt +='<option value="'+data[idx].class_code+'">'+data[idx].class_code_name+'</option>';
                                  }
                                  rtSlt +='</select>';
                                  return rtSlt;
                              },
                              sopt: ["eq"]
                          }},                      {name : 'classCodeName',index : 'classCodeName', align:"center",width : 200,editrules : {required : true,edithidden : true},
                          editable : true,editoptions : {size : "50",maxlength : "50"},
                          search: false},
                          /*{name : 'classCode', index : 'classCode', width : 100, editrules : {required : true, edithidden : true},
                          editable : true,editoptions : {size : "50",maxlength : "50"},
                          search: false},
                      {name : 'classCodeName',index : 'classCodeName', align:"center",width : 200,editrules : {required : true,edithidden : true},
                          editable : true,editoptions : {size : "50",maxlength : "50"},
                          editoptions: {},
                          stype: "select",
                          searchoptions: {
                              dataUrl: '/common/code/classCodeSelect',
                              buildSelect: function (data) {
                                  if(typeof(data)=='string')
                                     data = $.parseJSON(data);
                                  var rtSlt = '<select name="classCode">';
                                  for ( var idx = 0 ; idx < data.length ; idx ++) {
                                      rtSlt +='<option value="'+data[idx].class_code+'">'+data[idx].class_code_name+'</option>';
                                  }
                                  rtSlt +='</select>';
                                  return rtSlt;
                              },
                              sopt: ["eq"]
                          }},*/
                      {name : 'commonCode',index : 'commonCode', align:"center",width : 150,editrules : {required : true,edithidden : true},
                          editable : true,editoptions : {size : "50",maxlength : "50"},
                          search: false},
                      {name : 'commonCodeName',index : 'commonCodeName', align:"center", width : 150,editrules : {required : true,edithidden : true},
                          editable : true,editoptions : {size : "50",maxlength : "50"},
                          search: false},
                      {name : 'useYn',index : 'useYn',width : 50,edittype : "select",formatter : 'select',
                          editoptions : {value : "N:N;Y:Y",defaultValue : "Y"},
                          editrules : {required : true,edithidden : true},
                          editable : true,
                          cellattr : function(rowId, val,rawObject, cm,rdata) {
                              if (val == 'N') {
                                  return 'style="background-color:#FF0000"';
                              } else {
                                  return 'style="background-color:#33FFFF"';
                              };
                          },
                          search: false
                      },
                      {name : 'updOprt',index : 'updOprt', align:"center",width : 50,editrules : {required : false,edithidden : true},
                          editable : false,editoptions : {size : "50",maxlength : "50"},
                          search: false},
                      {name : 'updDate',index : 'updDate', align:"center",width : 150,editrules : {required : false,edithidden : true},
                              editable : false,editoptions : {size : "50",maxlength : "50"}, formatter: "date",
                                 formatoptions: { newformat: " Y-m-d" },
                                 search: false}
                      ],
          pager : jQuery('#gridpager'),
          pagination : true,
//        rowNum : 10,
//        rowList : [ 3, 6, 9 ],
          sortname : 'id',
          viewrecords : true,
          sortorder : "desc",
          caption : "공통코드 관리",
          autowidth : true,
          height : '100%',
          jsonReader: {
              repeatitems: false
          },
          ondblClickRow : function(id) {
              alert("You double click row with id: "+ id);
          },
          onSelectRow : function(id) {},
          loadComplete : function(xhr) {},
          gridComplete : function() {},
          loadError : function(xhr, st, err) {alert(err);},
          editurl : "/admin/commonCodeEdit"
      }).navGrid('#gridpager', {
          edit : true,
          add : true,
          del : true,
          search : true
	      },
	      {
	          url : "/admin/commonCodeEdit",
	          beforeShowForm: function ($form) {
	              $('#classCode').attr('readonly','readonly');
	              $('#commonCode').attr('readonly','readonly');
	          },
	          recreateForm: true,
	          closeAfterEdit: true,
	          errorTextFormat: function (data) {
	              return 'Error: ' + data.resultMsg;
	          }
	      },
	      {
	          url : "/admin/commonCodeEdit",
	          beforeShowForm: function ($form) {
	              $('#classCode').removeAttr('readonly');
	              $('#commonCode').removeAttr('readonly');
	          },
	          closeAfterAdd: true
	      },
	      {
	          url : "/admin/commonCodeEdit",
	          delData : {
	              classCode: function(){
	                  const selId = $('#gridlist').jqGrid('getGridParam', 'selrow');
	                  const divId = $('#gridlist').jqGrid('getCell', selId, 'classCode');
	                  return divId.replace(/-9/,'');
	              },
	              commonCode : function(){
	                  const selId = $('#gridlist').jqGrid('getGridParam', 'selrow');
	                  const cdId = $('#gridlist').jqGrid('getCell', selId, 'commonCode');
	                  return cdId.replace(/-9/,'');
	              }
	          }
	      }
      );

      jQuery("#gridlist").jqGrid({
          pager : '#gridpager',
          recordtext : "View {0} - {1} of {2}",
          emptyrecords : "No records to view",
          loadtext : "Loading...",
          pgtext : "Page {0} of {1}"
      });

      $.extend($.jgrid.edit, {
          closeAfterAdd : true,
          recreateForm : true,
          reloadAfterSubmit : false,
          left : 100,
          top : 100,
          dataheight : '100%',
          width : 500,
          addCaption : "추가",
          editCaption : "편집",
          bSubmit : "저장-전송",
          bCancel : "취소",
          bClose : "닫기",
          saveData : "Data has been changed! Save changes?",
          bYes : "Yes",
          bNo : "No",
          bExit : "Cancel"
      });

      var $grid = $('#gridlist');

      // create the grid
      $grid.jqGrid({
          // jqGrid opetions
      });

      // set searching deafauls
      $.extend($.jgrid.search, {multipleSearch: true, multipleGroup: false, closeAfterSearch: true, overlay: 0});

      // during creating nevigator bar (optional) one don't need include searching button
      $grid.jqGrid('navGrid', '#pager', {add: false, edit: false, del: false, search: false});

      // create the searching dialog
      //$grid.jqGrid('searchGrid');

      var gridSelector = $.jgrid.jqID($grid[0].id), // 'list'
          $searchDialog = $("#searchmodfbox_" + gridSelector),
          $gbox = $("#gbox_" + gridSelector);

      // hide 'close' button of the searchring dialog
      $searchDialog.find("a.ui-jqdialog-titlebar-close").hide();

      // place the searching dialog above the grid
      $searchDialog.insertBefore($gbox);
      $searchDialog.css({position: "relative", zIndex: "auto", float: "left"})
      $gbox.css({clear:"left"});
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
					<span class="core-color">공통코드 관리</span>
				</p>
			</div>
		</div>
	</section>
  <section class="section sub-contents">
    <div class="container">
			<div class="data-table-wrap">
				<table id="gridlist"></table>
				<div id="gridpager"></div>
			</div>
    </div> <!-- /container -->
  </section>

</body>
</html>