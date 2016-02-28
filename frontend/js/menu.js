$('#add_empty').on('click', add_empty_product);
var menu_last_id = 0;
var categoriesList = [];

load_products();

function load_products() {
  var token = Cookies.get('token');
  var cafe_id = Cookies.get('cafeid');
  var address = '/backend/cafe/getMenu?cafeId=' + cafe_id + '&token=' + token;
  var categoriesAddress = '/backend/categories/get?cafeId=' + cafe_id + '&token=' + token;

  $.getJSON(categoriesAddress, function(data) {
    categoriesList = data;
  });
  $.ajax({
    type: 'POST',
    url: '/backend/cafe/getMenu',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({
      token: token
    }),
    success: function(data) {
      for(var i = 0; i < data.length; i++) {
        var currentProduct = data[i];
        var id = currentProduct.id;
        var categoryId = currentProduct.category.id;
        var description = currentProduct.description;
        var name = currentProduct.name;
        var price = currentProduct.price;
        if (menu_last_id < id) {
          menu_last_id = id;
        }
        add_product(id, name, description, price, categoryId, categoriesList);
      }
    }
  });
}

function add_empty_product() {
  menu_last_id++;
  var tr = add_product(menu_last_id, '', '', '', 0, true);

  $(tr).attr('data-new', 'true');
}

function add_product(id, name, description, price, categoryId, invert) {
  var tr = document.createElement('tr');
  // $(tr).append(add_default_cell(id, 'id', id));
  $(tr).append(add_hidden_id(id));
  $(tr).append(add_textbox_cell('name', name));
  $(tr).append(add_textbox_cell('description', description));
  $(tr).append(add_textbox_cell('price', price));
  $(tr).append(add_dropdown_cell('category', categoriesList, categoryId));
  $(tr).append(add_edit_button(id));
  $(tr).append(get_remove_button(id));
  $('#tbody_products').append(tr);

  if (invert === true) {
    $(tr).find('.menu_label').css('display', 'none');
    $(tr).find('.menu_input').css('display', 'inline-block');
  }

  return tr;
}

function add_default_cell(value) {
  var td = document.createElement('td');
  $(td).html(value);
  return td;
}

function add_hidden_id(value) {
  var td = document.createElement('td');
  td.style.display = 'none';
  td.className = 'id'
  $(td).html(value);

  return td;
}

function add_dropdown_cell(name, categoryItems, currentCategoryId) {
  var td = document.createElement('td');
  var select = document.createElement('select');
  select.className = 'input-field menu_input ' + name;
  for(key in categoryItems) {
    var current_option = document.createElement('option');
    $(current_option).val(key);
    $(current_option).html(categoryItems[key].name);
    if (categoryItems[key].id === currentCategoryId) {
      current_option.setAttribute('selected', 'selected');

      var label = document.createElement('span');
      label.className = 'menu_label';
      $(label).text($(current_option).html());
      $(td).append(label);
    }
    $(select).append(current_option);
  }
  $(td).append(select);
  return td;
}

function add_textbox_cell(name, value) {
  var td = document.createElement('td');

  var label = document.createElement('span');
  $(label).text(value);
  label.className = 'menu_label';
  $(td).append(label);

  var input = document.createElement('input');
  $(input).attr('value', value);
  $(input).attr('type', 'text');
  input.className = 'menu_input ' + name;
  td.appendChild(input);
  return td;
}

function remove_action() {
  var tr = $(this).closest('tr')[0];
  var id = $(tr).find('.id').text();

  if ($(tr).attr('data-new') !== undefined) {
    $(tr).fadeToggle();
    return;
  }

  $.ajax({
    type: 'POST',
    url: '/backend/cafe/menu/remove',
    contentType: 'application/json',
    data: JSON.stringify({
      'token': token,
      'productId': id,
    }),
    success: function(response) {
      $(tr).fadeToggle();
    },
    error: function() {
      Materialize.toast('Usuwanie produktu nie powiodło się', 2000);
    }
  });
}

function edit_action() {
  var tr = $(this).closest('tr')[0];
  $(tr).find('.menu_label').css('display', 'none');
  $(tr).find('.menu_input').css('display', 'inline-block');
}

function update_label(element, value) {
  var label = $(element).parent().find('span');
  label.text(value);
}

function save_action() {
  var tr = $(this).closest('tr')[0];

  var id = $(tr).find('.id');
  var name = $(tr).find('.name');
  var description = $(tr).find('.description');
  var price = $(tr).find('.price');
  var category = $(tr).find('.category');

  $.ajax({
    type: 'POST',
    url: '/backend/cafe/menu/product/update',
    contentType: 'application/json',
    data: JSON.stringify({
      token: token,
      productId: id.text(),
      name: name.val(),
      description: description.val(),
      price: price.val(),
      categoryId: parseInt(category.val()) + 1
    }),
    success: function(data) {
      update_label(id, id.text());
      update_label(name, name.val());
      update_label(description, description.val());
      update_label(price, price.val());
      update_label(category, categoriesList[category.val()].name);
      console.log(categoriesList[category.val()].name)

      if ($(tr).attr('data-new') !== undefined) {
        $(tr).removeAttr('data-new');
      }

      $(tr).find('.menu_label').css('display', 'inline-block');
      $(tr).find('.menu_input').css('display', 'none');
    },
    error: function() {
      Materialize.toast('Aktualizacja produktu nie powiodła się.', 2000);
    }
  });
}

function add_edit_button() {
  var td = document.createElement('td');
  td.style.width = '10%';

  var btn = document.createElement('a');
  btn.className = 'waves-effect waves-light btn orange done menu_label';
  $(btn).html('Edytuj')
  $(btn).on('click', edit_action);

  var btn1 = document.createElement('a');
  btn1.className = 'waves-effect waves-light btn green done menu_input';
  $(btn1).html('Zapisz')
  $(btn1).on('click', save_action);

  $(td).append(btn);
  $(td).append(btn1);
  return td;
}

function get_remove_button(product_id) {
  var td = document.createElement('td');
  td.style.width = '10%';

  var btn = document.createElement('a');
  btn.className = 'waves-effect waves-light btn red done';
  $(btn).html('Usuń')
  $(btn).on('click', remove_action);

  $(td).append(btn);
  return td;
}
