;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form,admin=layui.admin;
    table.render({
        elem: "#LAY-app-content-list",
        url: "/sys/position",
        even: true,
        toolbar:"#toolbar-operation-btn",
        skin:'row',
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "postId", width: 100, title: "岗位ID", sort: !0},
            {field: "postCode",title: "岗位代码",minWidth: 100},
            {field: "postName", title: "岗位名称"},
            {field: "createBy",title: "创建人",sort: !0},
            {field:"createTime",title:"注册日期",templet:"<div>{{layui.util.toDateString(d.createTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"},
            {field: "status", title: "状态", templet: "#buttonTpl", minWidth: 80, align: "center"},
            {title: "操作",minWidth: 150,align: "center",fixed: "right",toolbar: "#table-content-list"}
        ]],
        page: !0,
        limit: 10,
        limits: [10, 15, 20, 25, 30],
        text: {
            none: "暂无岗位数据！"
        }
    });
    table.on('toolbar(LAY-app-content-list)',function(obj){
        var checkStatus = table.checkStatus(obj.config.id)
            ,checkData = checkStatus.data; //获取选中的数据
        switch(obj.event){
            case 'add':
                layer.open({
                    type: 2
                    ,title: '添加岗位'
                    ,content: '/sys/position/form'
                    ,maxmin: true
                    ,scrollbar:false
                    ,area: ['630px', '420px']
                    ,btn: ['确定', '取消']
                    ,yes: function(e, idx){
                        var l = window["layui-layer-iframe" + e],
                            a = idx.find("iframe").contents().find("#layuiadmin-app-form-submit");
                        l.layui.form.on("submit(layuiadmin-app-form-submit)", function (res) {
                            var field = res.field; //获取提交的字段
                            admin.req({
                                type:'post',
                                url: "/sys/position/saveOrUpdate",
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
                break
            case 'batchdel':
                if(checkData.length === 0){
                    return layer.msg('请选择数据');
                }
                var ids=[];
                for(var i=0;i<checkData.length;i++){
                    var row=checkData[i];
                    ids.push(row.postId);
                }
                layer.confirm('确定删除吗？', function(index) {
                    admin.req({
                        type: "DELETE",
                        cache:false,
                        url: "/sys/position/batchDelete",
                        data:{ids:ids},
                        done:function(res){
                            layer.msg('删除成功！', {
                                time: 2000 //20s后自动关闭
                            });
                            layer.close(index);
                            table.reload("LAY-app-content-list");
                        }
                    });
                });
                break;
            default:
                break;
        }
    });
    table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        "del" === t.event ? layer.confirm("确定删除该岗位？", function (cf) {
            admin.req({
                type: "DELETE",
                cache:false,
                url: "/sys/position/delete/"+e.postId,
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
        }) : "edit" === t.event && layer.open({
            type: 2,
            title: "编辑岗位信息",
            content: "/sys/position/form/",
            maxmin: !0,
            scrollbar:false,
            area: ['630px', '420px'],
            btn: ["确定", "取消"],
            yes: function (e, idx) {
                var l = window["layui-layer-iframe" + e],
                    a = idx.find("iframe").contents().find("#layuiadmin-app-form-edit");
                l.layui.form.on("submit(layuiadmin-app-form-edit)", function (res) {
                    var data=res.field;
                    admin.req({
                        type:'post',
                        url: "/sys/position/saveOrUpdate",
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
                    url: "/sys/position/view/"+e.postId,
                    done:function(res){
                        var data=res.data;
                        var div = layero.find('iframe').contents().find('#layuiadmin-app-form-list');
                        for (var fd in data){
                            div.find("input[name=\'"+fd+"\']").not("input:radio").val(data[fd]);
                        }
                        var checked=(data.status==0)?"checked":"";
                        div.find("input[name='status']").prop("checked",checked);
                        div.find("textarea[name='remark']").text(data.remark);
                    }
                });
            }
        })
    });
    t("postList", {});
});