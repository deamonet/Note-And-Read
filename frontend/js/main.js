const page_size = 5;

user = layui.sessionData("user");

let config_get = {
    method: "GET",
    credentials: "include"
};

let protocol = "http";
let domain = "127.0.0.1";
let port = "19201";
let prefix = protocol + "://" + domain + ":" + port;

// 公开访问
let login_url = prefix + "/login";
let register_url = prefix + "/register";
let logout_url = prefix + "/logout";

// 授权访问的用户前缀 
let user_url = prefix + "/user"; // path variable id
let user_controll_url = user_url + "/" + user.userid;

// 授权访问
let reset_password_url = user_controll_url + "/reset-password";

let notebook_url = user_controll_url + "/notebook"; // param id
let note_url = user_controll_url + "/note"; // param id
let note_net_url = user_controll_url + "/note/net"; // notebookid

let readsource_url = user_controll_url + "/source";
let article_url = user_controll_url + "/article"; // id

let search_url = user_controll_url + "/search"; // where[article, note]


function getQueryString(name) {
    let reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)", "i");
    let r = window.location.search.substr(1).match(reg);
    if (r != null) {
        return decodeURIComponent(r[2]);
    }
    return null;
}

function error(message) {
    window.location.href = `./error.html?message=${message}`;
}

function index() {
    window.location.href = "./index.html";
}

// fast api test