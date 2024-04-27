layui.use("layer", function () {
    var layer = layui.layer;
    var $ = layui.jquery;

    $("#logout").click(function () {
        layui.sessionData('user', null);
        
        let config = {
            method: 'POST',
            body: json_data,
            credentials: 'include'
        };
        
        fetch(logout_url, config).then(response => response.json()).then(json => {
            if (json.code == 200) {
                console.log("服务器端注销成功");
            }
        });
    });
});