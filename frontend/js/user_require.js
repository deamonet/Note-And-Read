let user = layui.sessionData("user");
if (Object.keys(user).length === 0) {
  window.location.href = `./error.html?message=未登录`;
}