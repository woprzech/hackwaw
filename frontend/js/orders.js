$('.done').each(function() {
  $(this).on("click", done);
});

function done() {
  var row = $(this).closest('tr')[0];
  $(row).fadeToggle();
}
