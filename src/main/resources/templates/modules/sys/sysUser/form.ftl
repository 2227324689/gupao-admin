

<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <title>用户编辑</title>
    <meta name="renderer" content="webkit">
    <meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=0">
    <link rel="stylesheet" href="/static/layui/css/layui.css" media="all">
</head>
<body>

<div class="layui-form" lay-filter="layuiadmin-app-form-list" id="layuiadmin-app-form-list" style="padding: 20px 30px 0 0;">
    <div class="layui-form-item">
        <input type="text" name="id" class="form-control" hidden="hidden">
        <input type="hidden" id="deptId" name="deptId" value="#{deptId}"/>
        <input type="hidden" id="roleIds" name="roleIds" value="${defaultRole!}"/>
        <label class="layui-form-label">用户名：</label>
        <div class="layui-input-block">
            <input type="text" name="username" lay-verify="required" placeholder="请输入用户名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">真实姓名：</label>
        <div class="layui-input-block">
            <input type="text" name="realName" lay-verify="required" placeholder="请输入真实姓名" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">所属部门：</label>
        <div class="layui-input-block">
            <input type="text" lay-filter="dept-tree" id="dept-tree" placeholder="请输入标题" autocomplete="off" class="layui-input">
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">岗位：</label>
            <div class="layui-input-block">
                <select name="postId" lay-verify="required">
                    <option value="">请选择用户所属岗位</option>
                    <#if postList?? && (postList?size >0)>
                        <#list postList as post>
                        <option value="${post.postId}">${post.postName}</option>
                        </#list>
                    </#if>
                </select>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">角色：</label>
            <div class="layui-input-block">
               <div id="roleChoose"></div>
            </div>
        </div>
    </div>
    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">邮箱：</label>
            <div class="layui-input-block">
                <input type="text" name="email" lay-verify="required|email" autocomplete="off" class="layui-input" placeholder="请输入邮箱">
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">电话：</label>
            <div class="layui-input-block">
                <input type="text" name="phone" lay-verify="required|phone" autocomplete="off" class="layui-input" placeholder="请输入电话">
            </div>
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">城市：</label>
        <div class="layui-input-block">
            <input type="text" name="city" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入城市">
        </div>
    </div>
    <div class="layui-form-item">
        <label class="layui-form-label">地址：</label>
        <div class="layui-input-block">
            <input type="text" name="address" lay-verify="required" autocomplete="off" class="layui-input" placeholder="请输入地址">
        </div>
    </div>

    <div class="layui-row layui-form-item">
        <div class="layui-col-xs6">
            <label class="layui-form-label">性别</label>
            <div class="layui-input-block">
                <input type="radio" name="sex" value="1" title="男" checked=""><div class="layui-unselect layui-form-radio layui-form-radioed"><i class="layui-anim layui-icon layui-anim-scaleSpring"></i><div>男</div></div>
                <input type="radio" name="sex" value="0" title="女"><div class="layui-unselect layui-form-radio"><i class="layui-anim layui-icon"></i><div>女</div></div>
            </div>
        </div>
        <div class="layui-col-xs6">
            <label class="layui-form-label">启用状态：</label>
            <div class="layui-input-block">
                <input type="checkbox" lay-verify="required" lay-filter="status" value="1" checked name="status" lay-skin="switch" lay-text="启用|禁用">
            </div>
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
        index: 'lib/index',
        treeSelect:'treeSelect/treeSelect',
        xmSelect:'xm-select/xm-select'
    }).use(['index', 'form',"treeSelect",'xmSelect'], function(){
        var $ = layui.$,form = layui.form,admin=layui.admin,treeSelect=layui.treeSelect,xmSelect=layui.xmSelect;
        form.on('switch(status)',function(data){
            var status=data.elem.checked?1:0;
            $(data.elem).attr('type', 'hidden').val(status);
        });
        treeSelect.render({
            elem:"#dept-tree",
            data: "/sys/dept/treeSelectList",
            type:"get",
            placeholder:'选择所属部门',
            search:true,
            click:function(d){
                $("#deptId").val(d.current.id);
                treeSelect.checkNode('dept-tree',d.current.id);
            },
            success: function(d){
                treeSelect.checkNode('dept-tree',#{deptId});
            }
        });
        xmSelect.render({
            el:"#roleChoose",
            data:${roleList},
            initValue:${checkedRole},
            on:function(data){
                //arr:  当前多选已选中的数据
                var arr = data.arr;
                var roleId=new Array();
                for (var i=0;i<arr.length;i++){
                    roleId.push(arr[i].value);
                }
                $("#roleIds").val(roleId);
            }
        });
    })
</script>
</body>
</html>