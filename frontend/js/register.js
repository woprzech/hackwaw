$('#register_btn').unbind('click').on('click', register);
$('#registration_confirmation_ok').unbind('click').on('click', return_to_main);

function register() {
  $.ajax({
    type: 'POST',
    url: '/backend/registration',
    contentType: 'application/json',
    data: JSON.stringify({
      email: $('#email').val(),
      phone: $('#phone').val(),
      cafeName: $('#name').val(),
      address: $('#address').val()
    }),
    success: function() {
      $('#registration_confirmation').openModal();
    },
    error: function() {
      Materialize.toast('Rejestracja nie powiodła się.', 2000);
    }
  });
}

function return_to_main() {
  window.location.href = '../';
}
