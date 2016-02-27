// variables
var cur_page = '';
var token = '';

// functions
function change_page() {
  update_content(this);
}

function update_content(page_content) {
  console.log(page_content)
  if (cur_page !== page_content) {
    $(cur_page).removeClass('active');
    cur_page = page_content;
    $(page_content).addClass('active');
    page = page_content.id.substr(0, page_content.id.lastIndexOf('_'));
    $('#page_content').load(page + '.html', function() {
      $.getScript("/js/" + page + ".js");
    });
  }
}

function navbar_login() {
  // TODO logowanie + ciastka
  $('.loggedout').each(function() {
    this.style.display = 'none';
  });
  $('.loggedin').each(function() {
    this.style.display = 'block';
  });
}

function logout() {
  // TODO wylogowanie + ciastka
  $('.loggedout').each(function() {
    this.style.display = 'block';
  });
  $('.loggedin').each(function() {
    this.style.display = 'none';
  });
  update_content($('#login_click')[0])

}

$(document).ready(function() {
  $('#login_click').on('click', change_page);
  $('#menu_click').on('click', change_page);
  $('#orders_click').on('click', change_page);
  $('#logout_click').on('click', logout);
});
