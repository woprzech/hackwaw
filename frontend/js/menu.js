load_products();


function load_products() {
    var token = Cookies.get('token');
    var cafe_id = Cookies.get('cafeid');
    var address = '/backend/cafe/getAllProducts?cafeId=' + cafe_id + '&token=' + token;
    var categoriesAddress = '/backend/categories/get?cafeId=' + cafe_id + '&token=' + token;

    var categoiresList = [];
    $.getJSON(categoriesAddress, function(data) {
        categoiresList = data;
    });

    $.getJSON(address, function(data) {
        for(var i = 0; i < data.length; i++) {
            var currentProduct = data[i];
            var id = currentProduct.id;
            var categoryId = currentProduct.category.id;
            var description = currentProduct.description;
            var name = currentProduct.name;
            var price = currentProduct.price;
            add_product(id, name, description, price, categoryId, categoiresList);
        }
    });
}

function add_product(id, name, description, price, categoryId, categoriesList) {
    var tr = document.createElement('tr');
    tr.setAttribute('id', 'product_' + id);
    // $(tr).append(add_default_cell(id, 'id', id));
    $(tr).append(add_textbox_cell(id, 'name', name));
    $(tr).append(add_textbox_cell(id, 'description', description));
    $(tr).append(add_textbox_cell(id, 'price', price));
    $(tr).append(add_dropdown_cell(id, 'category', categoriesList, categoryId));
    $(tr).append(add_default_cell(get_remove_button(id)));
    $('#tbody_products').append(tr);
}

function add_default_cell(value) {
    var td = document.createElement('td');
    $(td).html(value);
    return td;
}

function add_dropdown_cell(product_id, label, categoryItems, currentCategoryId) {
    var td = document.createElement('td');
    var select = document.createElement('select');
    select.setAttribute('id', 'product_' + product_id + '_' + label);
    select.className = 'input-field';
    for(key in categoryItems) {
        var current_option = document.createElement('option');
        $(current_option).val(key);
        $(current_option).html(categoryItems[key].name);
        if (categoryItems[key].id === currentCategoryId) {
            current_option.setAttribute('selected', 'selected');
        }
        select.appendChild(current_option);
    }
    td.appendChild(select);

    var label = document.createElement('label');
    return td;
}

function add_textbox_cell(product_id, label, value) {
    var td = document.createElement('td');
    var input = document.createElement('input');
    input.setAttribute('value', value);
    input.setAttribute('type', 'text');
    input.setAttribute('id', 'product_' + product_id + '_' + label);
    td.appendChild(input);
    return td;
}

function remove_action(product_id) {
    var token = Cookies.get('token');
    $.ajax({
        type: 'POST',
        url: '/backend/cafe/menu/remove',
        contentType: 'application/json',
        data: JSON.stringify({
            'token': token,
            'productId': product_id,
        }),
        success: function(response) {
            var row = $('#product_' + product_id).closest('tr')[0];
            $(row).fadeToggle();
            done();
        },
        error: function() {
            Materialize.toast('Usuwanie produktu nie powiodło się', 2000);
        }
    });
}

function get_remove_button(product_id) {
    var btn = document.createElement('a');
    btn.className = 'waves-effect waves-light btn red done';
    $(btn).html('Usuń')
    btn.setAttribute('onClick', 'remove_action(' + product_id + ')');
    return btn;
}
