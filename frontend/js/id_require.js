let id = getQueryString("id");

if (id === null || id === "") {
    window.location.href = "./error.html?message=未指定ID";
}