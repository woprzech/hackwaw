$('#login_btn').on('click', login);

function login() {
  navbar_login();
  update_content($('#menu_click')[0]);
}
