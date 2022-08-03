QUnit.test("Test basico de ejemplo", function( assert ) {
  var value = "hola";
  // Asercion que si no se cumple se muestra "esperabamos hola"
  assert.equal(value, "hola", "esperabamos hola");
});
// Test preparado aposta (en este ejemplo) para que no se cumpla
QUnit.test("test fallando", function( assert ) {
  var value = "hola";
  assert.equal(value, "mundo", "Esperabamos mundo");
});
// Test que tiene multiples aserciones
QUnit.test("Multiples aserciones", function( assert ) {
  var value = "hola";
  assert.equal(value, "hola", "Esperabamos hola");
  assert.notEqual(1, 2, "1 tiene que ser diferente a 2");
  assert.ok(1 === 2, "este assertion es feo");
  assert.equal(1, 2, "este assertion es lindo");
  assert.ok(true, "debería ser true");
});