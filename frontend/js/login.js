$('#login_btn').on('click', login);

function login() {
  token = 'x';
  navbar_login();
  update_content($('#menu_click')[0]);
}
