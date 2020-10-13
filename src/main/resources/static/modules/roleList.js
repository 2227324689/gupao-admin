;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form,admin=layui.admin;
    table.render({
        elem: "#LAY-app-content-list",
        url: "/sys/role",
        even: true,
        cols: [[{type: "checkbox", fixed: "left"}, {field: "id", width: 100, title: "角色ID", sort: !0}, {
            field: "roleName",
            title: "角色名",
            minWidth: 100
        }, {field: "remark", title: "备注"},
           {
               field: "status",
               title: "状态",
               templet: "#buttonTpl",
               minWidth: 80,
               align: "center"
           },
            {
            title: "操作",
            minWidth: 150,
            align: "center",
            fixed: "right",
            toolbar: "#table-content-list"
        }]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: "对不起，加载出现异常！",
        toolbar:"#toolbar-operation-btn"
    });table.on("toolbar(LAY-app-content-list)",function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,checkData = checkStatus.data; //获取选中的数据
        switch(obj.event) {
            case 'add':
                layer.open({
                    type: 2
                    ,title: '添加角色'
                    ,content: '/sys/role/form'
                    ,maxmin: true
                    ,scrollbar:false
                    ,area: ['520px', '350px']
                    ,btn: ['确定', '取消']
                    ,yes: function(e, idx){
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type:'post',
                                url: "/sys/role/saveOrUpdate",
                                data:field,
                                cache: false,
                                done:function(res){
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    },function(){
                                        table.reload("LAY-app-content-list",{
                                            curr:1
                                        });
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    }
                });
                break;
            case 'list':
                if(checkData.length === 0){
                    return layer.msg('请选择角色');
                }
                layer.open({
                    type: 2
                    , title: '用户列表'
                    , content: '/sys/role/form/user/'+checkData[0].id
                    , maxmin: true
                    , scrollbar: false
                    , area: ['750px', '450px']
                    , btn: ['确定', '取消']
                });
                break;
            case 'auth':
                if(checkData.length === 0){
                    return layer.msg('请选择角色');
                }
                if(checkData.length>1){
                    return layer.msg('一次只能修改一个角色');
                }
                layer.open({
                    type: 2
                    ,title: '角色权限设置'
                    ,content: '/sys/role/auth/'+checkData[0].id
                    ,maxmin: true
                    ,scrollbar:false
                    ,area: ['420px', '520px']
                    ,btn: ['确定', '取消']
                    ,yes: function(e, idx){
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        a.trigger("click");
                    }
                });
                break;
            default:
                break;
        }
    }); table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        switch (t.event){
            case 'del':
                layer.confirm("确定删除该角色？", function (cf) {
                    admin.req({
                        type: "DELETE",
                        cache:false,
                        url: "/sys/role/delete/"+e.id,
                        done:function(res){
                            layer.msg('删除成功！', {
                                time: 1000 //20s后自动关闭
                            });
                            table.reload("LAY-app-content-list",{
                                curr:1
                            });
                            layer.close(cf);
                        }
                    });
                });
                break;
            case 'edit':
                layer.open({
                    type: 2,
                    title: "编辑角色信息",
                    content: "/sys/role/form",
                    maxmin: !0,
                    area: ['520px', '350px'],
                    btn: ["确定", "取消"],
                    yes: function (e, idx) {
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-edit");
                        l.layui.form.on("submit(layuiadmin-app-form-edit)", function (res) {
                            var data=res.field;
                            var index = parent.layer.getFrameIndex(window.name); //先得到当前iframe层的索引
                            admin.req({
                                type:'post',
                                url: "/sys/role/saveOrUpdate",
                                data:data,
                                cache: false,
                                done:function(res){
                                    layer.msg('保存成功！', {
                                        time: 1000 //20s后自动关闭
                                    },function(){
                                        table.reload("LAY-app-content-list",{
                                            curr:1
                                        });
                                        layer.close(e); //再执行关闭
                                    });
                                }
                            });
                        }), a.trigger("click");
                    },
                    success: function(layero, index){
                        admin.req({
                            type: "GET",
                            cache:false,
                            url: "/sys/role/view/"+e.id,
                            done:function(res){
                                var data=res.data;
                                var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                                for (fd in data){
                                    div.find("input[name=\'"+fd+"\']").not("input:radio").val(data[fd]);
                                }
                                var checked=(data.status==0)?"checked":"";
                                div.find("input[name='status']").prop("checked",checked);
                                div.find("textarea[name='remark']").text(data.remark);
                            }
                        });
                    }
                });
                break;
            case 'manage':
                layer.open({
                    type: 2
                    , title: '用户列表'
                    , content: '/sys/role/form/user/'+e.id
                    , maxmin: true
                    , scrollbar: false
                    , area: ['750px', '450px']
                    , btn: ['确定', '取消']
                });
                break;
            default:
                break;
        }
    }), t("roleList", {})
});