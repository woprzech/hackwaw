$('#login_btn').on('click', login);

function login() {
  $.ajax({
    type: 'GET',
    url: '/backend/account/login?login=' + $('#login').val() + '&password=' + $('#password').val(),
    success: function(response) {
      token = response.token;
      cafeid = response.id;
      Cookies.set('token', token);
      Cookies.set('cafeid', cafeid);
      navbar_login();
      update_content($('#menu_click')[0]);
    },
    error: function() {
      Materialize.toast('Logowanie nie powiodło się.', 2000);
    }
  });
}
