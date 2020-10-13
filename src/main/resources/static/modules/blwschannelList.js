;layui.define(["table", "form"], function (t) {
    var e = layui.$, table = layui.table, n = layui.form,admin=layui.admin;
    table.render({
        elem: "#LAY-app-content-list",
        url: "/blws/channel",
        even: true,
        cols: [[
            {type: "checkbox", fixed: "left"},
            {field: "id", width: 50, title: "ID", sort: !0},
            {field: "channelId",title: "channelId",minWidth: 50},
            {field: "showImgUrl",title: "展示的海报图",minWidth: 100},
            {field: "name",title: "课程名称",minWidth: 100},
            {field: "watchUrl",title: "直播间地址",minWidth: 100},
            {field: "content",title: "课程描述",minWidth: 100},
            // /**观看页状态（live-直播中,playback-回放中,end-已结束,waiting-未开始）',*/
            {field: "watchStatus",title: "观看页状态",minWidth: 50,templet:"#watchStatusbl"},
            {field: "startTime",title: "直播开始时间",minWidth: 100,templet:"<div>{{layui.util.toDateString(d.startTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>"},
            {field: "lastSyncTime",title: "最后一次同步时间",templet:"<div>{{layui.util.toDateString(d.lastSyncTime, 'yyyy年MM月dd日 HH:mm:ss')}}</div>",minWidth: 100},
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
    });table.on("tool(LAY-app-content-list)", function (t) {
        var e = t.data;
        switch (t.event){
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