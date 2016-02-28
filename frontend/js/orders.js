get_orders();

$('.done').each(function() {
  $(this).on("click", done);
});

function done() {
  var row = $(this).closest('tr')[0];
  $(row).fadeToggle();
}

function get_orders() {
  $.ajax({
    type: 'GET',
    url: '/backend/orders?token=' + token,
    success: function(response) {
      console.log(response)
    },
    error: function() {
      Materialize.toast('Logowanie nie powiodło się.', 2000);
    }
  });
}

function add_order(nr, date, type, price) {
  var tr = document.createElement('tr');
  $(tr).append(add_order_cell(nr));
  $(tr).append(add_order_cell(type));
  $(tr).append(add_order_cell(get_order_hour(date)));
  $(tr).append(add_order_cell(get_order_price(price)));
  $(tr).append(get_button());
  $('#tbody_orders').append(tr);
}

function add_order_cell(val) {
  var td = document.createElement('td');
  $(td).html(val);
  return td;
}

function get_order_hour(date) {
  return date_str = date.getHours() + ':' + (date.getMinutes() < 10 ? '0' : '') +
    date.getMinutes();
}

function get_order_price(price) {
  return ((price * 100 % 100) % 10) === 0 ? price + '0' : price;
}

function get_button() {
  var td = document.createElement('td');
  td.style.width = '40%';
  td.className = 'center';

  var btn = document.createElement('a');
  btn.className = 'waves-effect waves-light btn brown done';
  $(btn).html('Zamówienie zrealizowane')
  $(td).append(btn);

  return td;
}
