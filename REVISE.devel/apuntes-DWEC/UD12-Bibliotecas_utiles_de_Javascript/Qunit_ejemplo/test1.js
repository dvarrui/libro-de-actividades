// Ejemplo de Asercion simple, que esta hecha para ver si va qUnit
// Debe cumplirse siempre
QUnit.test( "hello test", function( assert ) {
  assert.ok( 1 == "1", "Passed!" );
});