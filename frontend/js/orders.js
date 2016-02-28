get_orders();

function realize_order() {
  $.ajax({
    type: 'POST',
    url: '/backend/orders/realize',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({
      token: token,
      orderId: $(this).closest('tr')[0].firstChild.innerHTML
    }),
    success: function(response) {
      done();
    },
    error: function() {
      Materialize.toast('Odznaczenie realizacji zamówienia nie powiodło się.', 2000);
    }
  });
}

function done() {
  var row = $(this).closest('tr')[0];
  $(row).fadeToggle();
}

function get_orders() {
  $.ajax({
    type: 'GET',
    url: '/backend/orders?token=' + token,
    success: function(response) {
      for (var i=0; i<response.length; ++i) {
        var current = response[i];
        var nr = current.id;
        var date = new Date(current.orderDate.replace( /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})Z/, "$3/$2/$1 $4:$5:$6"));
        var type = '';
        for (var j=0; j<current.products.length; ++j) {

        }
        var price = current.totalPrice;
        add_order(nr, date, type, price);
      }
    },
    error: function() {
      Materialize.toast('Pobranie zamówień nie powiodło się.', 2000);
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
  $(btn).on('click', realize_order);

  return td;
}
