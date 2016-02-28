$('#register_btn').on('click', register);
$('#registration_confirmation_ok').on('click', return_to_main);

function register() {
  $.ajax({
    type: 'POST',
    url: '/backend/registration',
    dataType: 'json',
    contentType: 'application/json',
    data: JSON.stringify({
      email: $('#email').val(),
      phone: $('#phone').val(),
      cafename: $('#name').val(),
      address: $('#address').val()
    }),
    success: function(response) {
      $('#registration_confirmation').openModal();
    },
    error: function() {
      Materialize.toast('Rejestracja nie powiodła się.', 2000);
    }
  });
}

function return_to_main() {
  window.location.href = '/';
}
