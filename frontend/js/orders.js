add_order_example();

$('.done').each(function() {
  $(this).on("click", done);
});

function done() {
  var row = $(this).closest('tr')[0];
  $(row).fadeToggle();
}

function add_order_example() {
  var date = new Date(2016, 02, 27, 16, 01, 0, 0);
  var date2 = date;
  date2.setMinutes(date.getMinutes() + 2);

  add_order(1, date, 'Caffe Latte', 10.9);
  add_order(2, date2, 'Flat White', 8.99);
}

function add_order(nr, date, type, price) {
  var tr = document.createElement('tr');
  $(tr).append(add_order_cell(nr));
  $(tr).append(add_order_cell(type));
  $(tr).append(add_order_cell(get_order_hour(date)));
  $(tr).append(add_order_cell(get_order_price(price)));
  $(tr).append(add_order_cell(get_button()));
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
  var btn = document.createElement('a');
  btn.className = 'waves-effect waves-light btn brown done';
  $(btn).html('Zakończ zamówienie')
  return btn;
}
