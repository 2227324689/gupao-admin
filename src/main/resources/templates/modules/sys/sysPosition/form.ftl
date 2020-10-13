
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>岗位编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <input type="text" name="postId" class="form-control" hidden="hidden">
        <label class="layui-form-label">岗位名称：</label>
        <div class="layui-input-block">
            <input type="text" name="postName" lay-verify="required" placeholder="请输入岗位名称" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <label class="layui-form-label">岗位编号：</label>
        <div class="layui-input-block">
            <input type="text" name="postCode" lay-verify="required" placeholder="请输入岗位编号" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">启用状态：</label>
        <div class="layui-input-block">
            <input type="checkbox" lay-verify="required" lay-filter="status" value="0" checked name="status" lay-skin="switch" lay-text="启用|禁用">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">备注：</label>
        <div class="layui-input-block">
            <textarea name="remark" placeholder="请输入角色备注" class="layui-textarea"></textarea>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认添加">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-edit" id="layuiadmin-app-form-edit" value="确认编辑">
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index'
    }).use(['index', 'form'], function(){
        var $ = layui.$,form = layui.form,admin=layui.admin;
        form.on('switch(status)',function(data){
            var status=data.elem.checked?0:1;
            $(data.elem).attr('type', 'hidden').val(status);
        });
    })
</script>
</body>
</html>