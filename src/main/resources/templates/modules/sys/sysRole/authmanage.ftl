<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>权限编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
    <link rel="stylesheet" href="/static/modules/elemTree/eleTree.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <div class="layui-input-block">
            <div id="roleMenu"></div>
            <input type="hidden" value="1" id="deptId" name="deptId"/>
        </div>
    </div>
    <div class="layui-form-item layui-hide">
        <input type="button" lay-submit lay-filter="layuiadmin-app-form-submit" id="layuiadmin-app-form-submit" value="确认修改">
    </div>
</div>
<script src="/static/layui/layui.js"></script>
<script>
    layui.config({
        base: '/static/' //静态资源所在路径
    }).extend({
        //主入口模块
        index: 'lib/index',
        eleTree:'elemTree/eleTree'
    }).use(['index', 'form','eleTree'], function(){
        var $ = layui.$,form = layui.form,tree=layui.tree,admin=layui.admin,eleTree=layui.eleTree;
        var el=eleTree.render({
            elem:"#roleMenu",
            showCheckbox:true,
            highlightCurrent:true,
            checkStrictly:true,
            defaultCheckedKeys:${checkedKeys},
            data:getTreeData()
        });
        function getTreeData(){
            var data;
            admin.req({
                type: "GET",
                cache:false,
                async:false,
                url: "/sys/menu/roleMenuTreeselect/#{roleId}",
                done:function(res){
                    data=res.data;
                }
            });
            return data;
        }
        form.on("submit(layuiadmin-app-form-submit)", function (res){
            var checkedNodes= el.getChecked(false,true);
            var ids=new Array();
            for(var i=0;i<checkedNodes.length;i++){
                ids.push(checkedNodes[i].id);
            }
            admin.req({
                type:"POST",
                url:"/sys/menu/saveMenuPermission",
                data:{
                    "roleId":#{roleId},
                    "ids":ids
                },
                done:function(res){
                    layer.msg('保存成功！', {
                        time: 1000 //1s后自动关闭
                    },function(){
                        var index = parent.layer.getFrameIndex(window.name);
                        parent.layer.close(index); //再执行关闭
                        parent.layui.table.reload("LAY-app-content-list");
                    });
                }
            });
        })
    })
</script>
</body>
</html>