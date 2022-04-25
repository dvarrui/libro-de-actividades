/* Creado por Alejandro */
#include <iostream>

using namespace std;

int main ()
{
  int edad;

  cout << "Introduce tu edad : ";
  cin >> edad;

  if (edad >= 18)
  {
    cout << endl << "Eres mayor de edad" << endl;
  }
  else
  {
    cout << endl << "Eres menor de edad" << endl;
  }

  return 0;
}
