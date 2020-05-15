<#include "/admin/utils/ui.ftl"/>
<@layout>
<section class="content-header">
    <h1>传相册</h1>
    <ol class="breadcrumb">
        <li><a href="${base}/admin">首页</a></li>
        <li><a href="${base}/admin/photo/list">相册管理</a></li>
        <li class="active">传相册</li>
    </ol>
</section>
<section class="content container-fluid">
    <div class="row">
        <div class="col-md-12">
            <form id="qForm" class="form-horizontal form-label-left" method="post" action="save">
                <input type="hidden" id="thumbnail" name="photoUrl">
                <div class="box">
                    <div class="box-header with-border">
                        <h3 class="box-title">传相册</h3>
                    </div>
                    <div class="box-body">
                        <div class="form-group">
                            <label class="col-lg-2 control-label">一句话描述</label>
                            <div class="col-lg-3">
                                <input type="text" name="description" class="form-control" required>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-lg-2 control-label">缩略图</label>
                            <div class="col-lg-3">
                                <div class="thumbnail-box">
                                    <div class="convent_choice" id="thumbnail_image" <#if view.thumbnail?? && view.thumbnail?length gt 0> style="background: url(${base + view.thumbnail}) no-repeat scroll top;" </#if>>
                                        <div class="upload-btn">
                                            <label>
                                                <span>点击选择一张图片</span>
                                                <input id="upload_btn" type="file" name="file" accept="image/*" title="点击添加图片">
                                            </label>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="box-footer">
                        <button type="submit" class="btn btn-primary">提交</button>
                    </div>
                </div>
            </form>
        </div>
    </div>
</section>
<script type="text/javascript">
var J = jQuery;

$(function() {
    var upload_url = _MTONS.BASE_PATH + '/admin/photo/upload';
    $('#upload_btn').change(function(){
        $(this).upload(upload_url, function(data){
            if (data.status == 200) {
                var path = data.path;
                $("#thumbnail_image").css("background", "url(" + path + ") no-repeat scroll center 0 rgba(0, 0, 0, 0)");
                $("#thumbnail").val(path);
            } else {
                layer.msg(data.message, {icon: 5});
            }
        });
    });
})
</script>
</@layout>