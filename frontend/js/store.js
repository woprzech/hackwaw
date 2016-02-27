// variables
var cur_page = '';

// functions
function change_page() {
  if (cur_page !== this) {
    $(cur_page).removeClass('active');
    cur_page = this;
    $(this).addClass('active');
    page = this.id.substr(0, this.id.lastIndexOf('_'));
    $('#page_content').load(page + '.html', function() {
      $.getScript("/js/" + page + ".js");
    });
  }
}

function logout() {

}

$(document).ready(function() {
  $('#menu_click').on('click', change_page);
  $('#orders_click').on('click', change_page);
  $('#logout').on('click', logout);
});
