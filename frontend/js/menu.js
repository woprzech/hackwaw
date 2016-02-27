add_product_example();


function add_product_example() {
    add_product(1, 'abc', 'Caffe Latte', 10.9, "Kawa");
    add_product(2, 'abc', 'Caffe Latte', 10.9, "Kawa");
}

function add_product(id, name, description, price, category) {
    var tr = document.createElement('tr');
    var categoryItems = ['Kawa', 'Ciastka', 'Inne'];
    tr.setAttribute('id', 'product_' + id);
    $(tr).append(add_textbox_cell(id, 'id', id));
    $(tr).append(add_textbox_cell(id, 'name', name));
    $(tr).append(add_textbox_cell(id, 'description', description));
    $(tr).append(add_textbox_cell(id, 'price', price));
    $(tr).append(add_dropdown_cell(id, 'category', categoryItems, category));
    $(tr).append(add_default_cell(get_remove_button(id)));
    $('#tbody_products').append(tr);
}

function add_default_cell(value) {
    var td = document.createElement('td');
    $(td).html(value);
    return td;
}

function add_dropdown_cell(product_id, label, categoryItems, currentCategory) {
    var td = document.createElement('td');
    var select = document.createElement('select');
    select.setAttribute('id', 'product_' + product_id + '_' + label);
    select.className = 'input-field';
    var selected_item = document.createElement('option');
    selected_item.setAttribute('value', currentCategory);
    selected_item.innerHTML = currentCategory;
    select.appendChild(selected_item); // make sure, that current element is the first option
    for(item in categoryItems) {
        var current_loop_item = document.createElement('option');
        current_loop_item.setAttribute('value', item);
        current_loop_item.innerHTML = item;
        if (item === currentCategory) {
            current_loop_item.setAttribute('selected', 'selected');
        }
        select.appendChild(current_loop_item);
    }
    td.appendChild(select);

    var label = document.createElement('label');
    $(label).val('Wybierz')
    return td;
}

function add_textbox_cell(product_id, label, value) {
    var td = document.createElement('td');
    td.setAttribute('id', 'product_' + product_id + '_' + label)
    $(td).html(value);
    return td;
}

function remove_action(product_id) {
    var row = $('#product_' + product_id).closest('tr')[0];
    $(row).fadeToggle();
}

function get_remove_button(product_id) {
    var btn = document.createElement('a');
    btn.className = 'waves-effect waves-light btn brown done';
    $(btn).html('Usuń')
    btn.setAttribute('onClick', 'remove_action(' + product_id + ')');
    return btn;
}
