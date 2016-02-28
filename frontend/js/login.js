$('#login_btn').on('click', login);

function login() {
  $.ajax({
    type: 'POST',
    url: '/backend/account/login',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({
      login: $('#login').val(),
      password: $('#password').val(),
      token: ''
    }),
    success: function(response) {
      console.log(response);
      token = response.token;
      cafeid = response.cafeAccount.id;
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
