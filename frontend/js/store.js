// variables
var cur_page = '';
var token = '';
var cafeid = '';

// functions
function change_page() {
  update_content(this);
}

function update_content(page_content) {
  if (cur_page !== page_content) {
    $(cur_page).removeClass('active');
    cur_page = page_content;
    $(page_content).addClass('active');
    page = page_content.id.substr(0, page_content.id.lastIndexOf('_'));
    $('#page_content').load("/store/" + page + '.html', function() {
      $.getScript("/js/" + page + ".js");
    });
  }
}

function navbar_login() {
  $('.loggedout').each(function() {
    this.style.display = 'none';
  });
  $('.loggedin').each(function() {
    this.style.display = 'block';
  });
}

function logout() {
  $.ajax({
    type: 'GET',
    url: '/backend/account/logout?token=' + token,
    success: function(response) {
      Cookies.remove('token');
      Cookies.remove('cafeid');
      token = '';
      cafeid = '';
      $('.loggedout').each(function() {
        this.style.display = 'block';
      });
      $('.loggedin').each(function() {
        this.style.display = 'none';
      });
      update_content($('#login_click')[0]);
    },
    error: function() {
      Materialize.toast('Wylogowanie nie powiodło się.', 2000);
    }
  });
}

function login_checker() {
  if (token !== '') {
    update_content($('#menu_click')[0]);
  } else {
    update_content($('#login_click')[0]);
  }
}

function get_url_param(sParam) {
    var sPageURL = decodeURIComponent(window.location.search.substring(1)),
        sURLVariables = sPageURL.split('&'),
        sParameterName,
        i;

    for (i = 0; i < sURLVariables.length; i++) {
        sParameterName = sURLVariables[i].split('=');

        if (sParameterName[0] === sParam) {
            return sParameterName[1] === undefined ? true : sParameterName[1];
        }
    }
};

function if_register() {
  if (get_url_param('action') === 'register') {
    console.log($('#register_click')[0])
    update_content($('#register_click')[0]);
  }
}

$(document).ready(function() {
  if (Cookies.get('cafeid')) {
    token = Cookies.get('token');
    cafeid = Cookies.get('cafeid');
    navbar_login();
  }

  $('#login_click').on('click', change_page);
  $('#menu_click').on('click', change_page);
  $('#orders_click').on('click', change_page);
  $('#logout_click').on('click', logout);
  $('#brand_logo').on('click', login_checker);

  login_checker();
  if_register();
});
