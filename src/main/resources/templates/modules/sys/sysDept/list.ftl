<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>咕泡云课堂 - 组织机构管理</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/style/admin.css" media="all">
    <link rel="stylesheet" href="/static/modules/elemTree/eleTree.css" media="all">
</head>
<body>

<div class="layui-fluid">
    <div class="layui-row layui-col-space10">
        <div class="layui-col-md4">
            <div class="layui-card">
                <div class="layui-card-header">部门列表</div>
                <div class="layui-card-body">
                    <div id="departTreeMenu" lay-filter="departTreeMenu"></div>
                </div>
            </div>
        </div>
        <div class="layui-col-md8">
            <div class="layui-card">
                <div class="layui-card-header">部门信息维护</div>
                <div class="layui-card-body">
                    <form class="layui-form" lay-filter="depart-edit-form" id="depart-edit-form">
                        <input type="hidden" name="id" id="id"/>
                        <input type="hidden" name="parentId" id="parentId"/>
                        <div class="layui-form-item">
                            <label class="layui-form-label">所属上级部门：</label>

                            <div class="layui-input-block">
                                <input type="text" lay-filter="tree" id="tree" name="parentTree"  placeholder="请输入标题" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">部门名称：</label>
                            <div class="layui-input-block">
                                <input type="text" name="deptName" required  lay-verify="required" placeholder="请输入部门名称" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">显示排序：</label>
                            <div class="layui-input-block">
                                <input type="number" name="sort" required  lay-verify="required" placeholder="请输入排序值" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-row layui-col-space10 layui-form-item">
                            <div class="layui-col-lg6">
                                <label class="layui-form-label">负责人：</label>
                                <div class="layui-input-block">
                                    <input type="text" name="leader" lay-verify="required" placeholder="请输入负责人名字" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                            <div class="layui-col-lg6">
                                <label class="layui-form-label">联系电话：</label>
                                <div class="layui-input-block">
                                    <input type="text" name="phone"  placeholder="请输入负责人联系电话" autocomplete="off" class="layui-input">
                                </div>
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <label class="layui-form-label">负责人邮箱：</label>
                            <div class="layui-input-block">
                                <input type="text" name="email"  lay-verify="email" placeholder="请输入负责人邮箱" autocomplete="off" class="layui-input">
                            </div>
                        </div>
                        <div class="layui-form-item">
                            <div class="layui-input-block">
                                <button class="layui-btn" lay-submit lay-filter="dept-form-submit">立即提交</button>
                                <button type="button" lay-reset lay-filter="dept-from-reset" id="dept-from-reset" class="layui-btn layui-btn-normal">置为新增模式</button>
                            </div>
                        </div>
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>

<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        index: 'lib/index', //主入口模块
        deptList:'sys/dept/deptList',
        treeSelect:'treeSelect/treeSelect',
        eleTree:'elemTree/eleTree'
    }).use(['index', 'deptList'], function(){
        var table = layui.table
                ,form = layui.form,admin=layui.admin,$=layui.$;
        //监听搜索
        form.on('submit(dept-form-submit)', function(data){
            var field = data.field;
            admin.req({
                type:'post',
                url: "/sys/dept/saveOrUpdate",
                data:field,
                cache: false,
                done:function(res){
                    layer.msg('保存成功！', {
                        time: 1000 //20s后自动关闭
                    },function(){
                        $("#depart-edit-form")[0].reset();
                        form.render();
                        $("#parentId").val("");
                        $("#id").val("");
                    });
                }
            });
            return false;
        });
        $("#dept-from-reset").on("click",function(){
            $("#depart-edit-form")[0].reset();
            form.render();
            $("#parentId").val("");
            $("#id").val("");
        })
    });
</script>
</body>
</html>
