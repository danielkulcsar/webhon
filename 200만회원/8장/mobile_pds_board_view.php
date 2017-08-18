<?php //initilize the page
require_once ("inc/init.php");

//require UI configuration (nav, ribbon, etc.)
require_once ("inc/config.ui.php");

/*---------------- PHP Custom Scripts ---------

 YOU CAN SET CONFIGURATION VARIABLES HERE BEFORE IT GOES TO NAV, RIBBON, ETC.
 E.G. $page_title = "Custom Title" */

$page_title = "Home";

/* ---------------- END PHP Custom Scripts ------------- */

//include header
//you can add your custom css in $page_css array.
//Note: all css files are inside css/ folder
$page_css[] = "your_style.css";
include ("inc/header.php");

//include left panel (navigation)
//follow the tree in inc/config.ui.php
//$page_nav["home"]["sub"]["data"]["active"] = true;
$page_nav["home"]["home"]["data"]["active"] = true;
include ("inc/nav.php");
?>
<!-- ==========================CONTENT STARTS HERE ========================== -->
<!-- MAIN PANEL -->
<div id="main" role="main">

	<?php
		//configure ribbon (breadcrumbs) array("name"=>"url"), leave url empty if no url
		//$breadcrumbs["New Crumb"] => "http://url.com"
		$breadcrumbs["Tables"] = "";
		include("inc/ribbon.php");
	?>

	<!-- MAIN CONTENT -->
	<div id="content">
		<!-- widget grid -->
		<section id="widget-grid" class="">
			<!-- row -->
			<div class="row">
				<!-- NEW WIDGET START -->
				<article class="col-sm-12 col-md-12 col-lg-6">
					<!-- Widget ID (each widget will need unique ID)-->
					<div class="jarviswidget" id="wid-id-1" data-widget-colorbutton="false" data-widget-editbutton="false">
						<!-- widget options:
						usage: <div class="jarviswidget" id="wid-id-0" data-widget-editbutton="false">
		
						data-widget-colorbutton="false"
						data-widget-editbutton="false"
						data-widget-togglebutton="false"
						data-widget-deletebutton="false"
						data-widget-fullscreenbutton="false"
						data-widget-custombutton="false"
						data-widget-collapsed="true"
						data-widget-sortable="false"
		
						-->

<?php
include("connect.php");
$query = mysql_query("select * from pds_game where num=$num",$connect);
while($row = mysql_fetch_assoc($query)) {
?>
						<!--header>
							<span class="widget-icon"> <i class="fa fa-folder-o"></i> </span>
							<h2></h2>
		
						</header-->
						<!-- widget div-->
						<div>
							<!-- widget edit box -->
							<div class="jarviswidget-editbox">
								<!-- This area used as dropdown edit box -->
		
							</div>
							<!-- end widget edit box -->
			
						<div class="widget-body no-padding">
						
						<form action="demo-contacts.php" method="post" id="contact-form" class="smart-form" novalidate="novalidate">
							<header>
							<i class=" fa fa-gamepad"></i> <a href="game_board_list.php?game_code=<?=$row['game_code']?>"><?=$row['game_name']?></a> : <?=$row['subject']?>

							</header>
							
							<fieldset>					
								<div class="row">
									<section class="col col-4">							
										<label class="label"><i class="fa fa-user"></i> 작성자 : <a href="#"><?=$row['mb_id']?></a></label>
									</section>
									<section class="col col-4">
										<label class="label"><i class="fa fa-bolt"></i> 조회수 : <?=$row['count_total']?></label>
									</section>
									<section class="col col-4">
										<label class="label"><i class="fa fa-clock-o"></i> 작성일 : <?=$row['upload_time']?></label>
									</section>
								</div>
								<section class="success">
									<label class="label"><i class="fa fa-comment-o"></i> 내용</label><hr><br>
									<label>
										<?=nl2br($row['contents'])?><br>
									</label>
								</section>
								<? if ($row['file_name']) {?>
							<!-- end widget edit box -->
							<br><br>
								<div class="row">
									<section class="col col-6">
										<label class="label"><i class="fa fa-download"></i> 다운로드 (10 GM)</label>
										<label class="label">

											<a href="#"><?=$row['file_name']?> (<?=$row['file_size']?> byte)</a>
											
											<!--input type="text" name="name" id="name" value="<?=$row['file_name']?> (<?=$row['file_size']?> byte)"-->
										</label>
									</section>
									<section class="col col-3">
										<label class="label">다운로드 수</label>
										<label class="label">
											<?=$row['down_total']?>
											<!--input type="email" name="email" id="email" value="<?=$row['down_total']?>"-->
										</label>
									</section>
								</div>
								<? } ?>
							</fieldset>
							
							<footer>
								<button type="button" class="btn btn-primary" onclick="window.history.back();">목록</button>
							</footer>
							
							<div class="message">
								<i class="fa fa-thumbs-up"></i>
								<p>Your message was successfully sent!</p>
							</div>
						</form>						
						
					</div>

							<!-- end widget content -->
						</div>
						<!-- end widget div -->
		
					</div>
					<!-- end widget -->
					</article>
					<!-- end article -->
					<!-- start article -->
				<article class="col-sm-12 col-md-12 col-lg-6">
						<form method="post" class="well padding-bottom-10" onsubmit="return false;">
							<textarea rows="2" class="form-control" placeholder="댓글을 작성해 주세요."></textarea>
							<div class="margin-top-10">
								<button type="submit" class="btn btn-sm btn-primary pull-right">
									댓글쓰기
								</button>
								<div class="btn btn-link profile-link-btn"><i class="fa fa-blank"></i></div>
								<!--a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="" data-original-title="Add Location"><i class="fa fa-location-arrow"></i></a>
								<a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="" data-original-title="Add Voice"><i class="fa fa-microphone"></i></a>
								<a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="" data-original-title="Add Photo"><i class="fa fa-camera"></i></a>
								<a href="javascript:void(0);" class="btn btn-link profile-link-btn" rel="tooltip" data-placement="bottom" title="" data-original-title="Add File"><i class="fa fa-file"></i></a-->
							</div>
						</form>
						<?
						$query = @mysql_query("select * from pds_reply where parent_num=$num",$connect);
						while($row = @mysql_fetch_assoc($query)) {
							if ($row['mb_num']){
							$result = @mysql_query("select * from members_info where mb_num = ".$row['mb_num']."$mb_num",$connect);
							$mb_id = @mysql_result($result,0,"mb_id");
							$mb_profile = @mysql_result($result,0,"mb_img");
							}
						?>			
						<span class="timeline-seperator text-center"> <span><?=$row['post_time']?></span>
							<!--div class="btn-group pull-right">
								<a href="javascript:void(0);" data-toggle="dropdown" class="btn btn-default btn-xs dropdown-toggle"><span class="caret single"></span></a>
							</div--> </span>
						<div class="chat-body no-padding profile-message">
							<ul>
								<li class="message">
								<?if($mb_img) $profile_img = "http://www.gamess.co.kr/images_profile/".$mb_img;
								else $profile_img = "images/profile_54x54.jpg";
								?>
									<img src="<?=$profile_img?>" class="online">
									<span class="message-text"> <a href="javascript:void(0);" class="username"><?=$mb_id?></a><?=nl2br($row['contents'])?></span>
									<ul class="list-inline font-xs">
										<!--li>
											<a href="javascript:void(0);" class="text-info"><i class="fa fa-reply"></i> Reply</a>
										</li>
										<li>
											<a href="javascript:void(0);" class="text-danger"><i class="fa fa-thumbs-up"></i> Like</a>
										</li>
										<li>
											<a href="javascript:void(0);" class="text-muted">Show All Comments (14)</a>
										</li-->
										<li>
											<a href="javascript:void(0);" class="text-primary">Edit</a>
										</li>
										<li>
											<a href="javascript:void(0);" class="text-danger">Delete</a>
										</li>
									</ul>
								</li>
								<!--li class="message message-reply">
									<img src="img/avatars/3.png" class="online">
									<span class="message-text"> <a href="javascript:void(0);" class="username">Serman Syla</a> Haha! Yeah I know what you mean. Thanks for the file Sadi! <i class="fa fa-smile-o txt-color-orange"></i> </span>

									<ul class="list-inline font-xs">
										<li>
											<a href="javascript:void(0);" class="text-muted">1 minute ago </a>
										</li>
										<li>
											<a href="javascript:void(0);" class="text-danger"><i class="fa fa-thumbs-up"></i> Like</a>
										</li>
									</ul>

								</li>
								<li class="message message-reply">
									<img src="img/avatars/4.png" class="online">
									<span class="message-text"> <a href="javascript:void(0);" class="username">Sadi Orlaf </a> Haha! Yeah I know what you mean. Thanks for the file Sadi! <i class="fa fa-smile-o txt-color-orange"></i> </span>

									<ul class="list-inline font-xs">
										<li>
											<a href="javascript:void(0);" class="text-muted">a moment ago </a>
										</li>
										<li>
											<a href="javascript:void(0);" class="text-danger"><i class="fa fa-thumbs-up"></i> Like</a>
										</li>
									</ul>
									<input class="form-control input-xs" placeholder="Type and enter" type="text">
								</li-->
							</ul>
						</div>
							<?}?>
					</div>
							<!-- end widget content -->
						</div>
						<!-- end widget div -->
					</div>
					<!-- end widget -->
					</article>
					<!-- end article -->
		<?}?>
			</div>
			<!-- end row -->
		</section>
		<!-- end widget grid -->

	</div>
	<!-- END MAIN CONTENT -->

</div>
<!-- END MAIN PANEL -->
<!-- ==========================CONTENT ENDS HERE ========================== -->

<!-- PAGE FOOTER -->
<?php // include page footer
include ("inc/footer.php");
?>
<!-- END PAGE FOOTER -->

<?php //include required scripts
include ("inc/scripts.php");
?>

<!-- PAGE RELATED PLUGIN(S) -->
<script src="<?php echo ASSETS_URL; ?>/js/plugin/datatables/jquery.dataTables.min.js"></script>
<script src="<?php echo ASSETS_URL; ?>/js/plugin/datatables/dataTables.colVis.min.js"></script>
<script src="<?php echo ASSETS_URL; ?>/js/plugin/datatables/dataTables.tableTools.min.js"></script>
<script src="<?php echo ASSETS_URL; ?>/js/plugin/datatables/dataTables.bootstrap.min.js"></script>

<script type="text/javascript">

// DO NOT REMOVE : GLOBAL FUNCTIONS!

$(document).ready(function() {
	
	/* // DOM Position key index //
	
		l - Length changing (dropdown)
		f - Filtering input (search)
		t - The Table! (datatable)
		i - Information (records)
		p - Pagination (paging)
		r - pRocessing 
		< and > - div elements
		<"#id" and > - div with an id
		<"class" and > - div with a class
		<"#id.class" and > - div with an id and class
		
		Also see: http://legacy.datatables.net/usage/features
	*/	

	/* BASIC ;*/
	
	$('#dt_basic').dataTable();

	
	/* END BASIC */
	
	/* COLUMN FILTER  */
    var otable = $('#datatable_fixed_column').DataTable({
    	//"bFilter": false,
    	//"bInfo": false,
    	//"bLengthChange": false
    	//"bAutoWidth": false,
    	//"bPaginate": false,
    	//"bStateSave": true // saves sort state using localStorage
		"sDom": "<'dt-toolbar'<'col-xs-6'f><'col-xs-6'<'toolbar'>>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-xs-6'i><'col-xs-6'p>>"
	
    });
    
    // custom toolbar
    $("div.toolbar").html('<div class="text-right"><img src="img/logo.png" alt="SmartAdmin" style="width: 111px; margin-top: 3px; margin-right: 10px;"></div>');
    	   
    // Apply the filter
    $("#datatable_fixed_column thead th input[type=text]").on( 'keyup change', function () {
    	
        otable
            .column( $(this).parent().index()+':visible' )
            .search( this.value )
            .draw();
            
    } );
    /* END COLUMN FILTER */   

	/* COLUMN SHOW - HIDE */
	$('#datatable_col_reorder').dataTable({
		"sDom": "<'dt-toolbar'<'col-xs-6'f><'col-xs-6'C>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-xs-6'i><'col-xs-6'p>>"
	});
	
	/* END COLUMN SHOW - HIDE */

	/* TABLETOOLS */
	$('#datatable_tabletools').dataTable({
		
		// Tabletools options: 
		//   https://datatables.net/extensions/tabletools/button_options
		"sDom": "<'dt-toolbar'<'col-xs-6'f><'col-xs-6'T>r>"+
				"t"+
				"<'dt-toolbar-footer'<'col-xs-6'i><'col-xs-6'p>>",
        "oTableTools": {
        	 "aButtons": [
             "copy",
             "csv",
             "xls",
                {
                    "sExtends": "pdf",
                    "sTitle": "SmartAdmin_PDF",
                    "sPdfMessage": "SmartAdmin PDF Export",
                    "sPdfSize": "letter"
                },
             	{
                	"sExtends": "print",
                	"sMessage": "Generated by SmartAdmin <i>(press Esc to close)</i>"
            	}
             ],
            "sSwfPath": "js/plugin/datatables/swf/copy_csv_xls_pdf.swf"
        }
	});
	
	/* END TABLETOOLS */

})

</script>

<?php
//include footer
include ("inc/google-analytics.php");
?>