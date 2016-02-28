get_orders();

window.setInterval(function() {
  get_orders();
}, 10000);

function realize_order() {
  thisA = this;
  $.ajax({
    type: 'POST',
    url: '/backend/orders/realize',
    contentType: 'application/json',
    data: JSON.stringify({
      token: token,
      orderId: $(this).closest('tr')[0].firstChild.innerHTML
    }),
    success: function() {
      done(thisA);
    },
    error: function() {
      Materialize.toast('Odznaczenie realizacji zamówienia nie powiodło się.', 2000);
    }
  });
}

function done(a) {
  var row = $(a).closest('tr')[0];
  $(row).fadeToggle();
}

function get_orders() {
  $.ajax({
    type: 'GET',
    url: '/backend/orders?token=' + token,
    success: function(response) {
      remove_orders();
      for (var i=0; i<response.length; ++i) {
        var current = response[i];
        var nr = current.id;
        var date = new Date(current.orderDate.replace( /(\d{4})-(\d{2})-(\d{2})T(\d{2}):(\d{2}):(\d{2})Z/, "$3/$2/$1 $4:$5:$6"));
        var name = current.userName;
        var price = current.totalPrice;
        add_order(nr, date, name, current.positions, price);
      }
    },
    error: function() {
      Materialize.toast('Pobranie zamówień nie powiodło się.', 2000);
    }
  });
}

function remove_orders() {
  $('#tbody_orders').empty();
}

function add_order(nr, date, name, order_list, price) {
  var tr = document.createElement('tr');
  $(tr).append(add_order_cell(nr));
  $(tr).append(add_types(name, order_list));
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

function toggle_list() {
  console.log($(this).children('ul').first()[0])
  var td = $(this).children('ul').first()[0];
  $(td).slideToggle();
}

function add_types(name, order_list) {
  var td = document.createElement('td');
  td.style.cursor = 'pointer';
  td.style.width = '350px';
  $(td).on('click', toggle_list);

  var span = document.createElement('span');
  $(span).html(name);
  var ul1 = document.createElement('ul');
  ul1.className = 'collection';

  var ul = document.createElement('ul');
  ul.className = 'collection';
  ul.style.display = 'none';
  for (var i=0; i<order_list.length; ++i) {
    var current = order_list[i]
    var li = document.createElement('li');
    li.className = 'collection-item';
    $(li).html(current.product.name + ' x' + current.amount + ', cena: ' + current.product.price + ' zł');

    $(ul).append(li);
  }

  $(td).append(span);
  $(td).append(ul);

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
