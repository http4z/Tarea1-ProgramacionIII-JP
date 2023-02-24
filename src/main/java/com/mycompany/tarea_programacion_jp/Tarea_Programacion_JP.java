//Alumno: Josue Ignacio Paz Moran
//Carné: 1290-21-5213
//Curso: Programación III
//Fecha: 23/02/2023
//Carrera: Ingeniería en Sistemas - Sede Antigua Guatemala - Diario Vespertino

#include <iostream>
#include <fstream>
#include <cstdlib>
#include <ctime>
#include <vector>
#include <algorithm>

using namespace std;

	template <typename T>
	struct arbol
	{
	    T val;
	    arbol* izq;
	    arbol* der;
	    arbol(T x) : val(x), izq(nullptr), der(nullptr) {}
	};
	
	template <typename T>
	void ingresar(arbol<T>*& nodo, T val)
	{
	    if (!nodo)
	    {
	        nodo = new arbol<T>(val);
	        return;
	    }
	
	    if (val < nodo->val)
	    {
	        ingresar(nodo->izq, val);
	    }
	    else
	    {
	        ingresar(nodo->der, val);
	    }
	}
	
	template <typename T>
	void traverse(arbol<T>* nodo, vector<T>& nums)
	{
	    if (nodo)
	    {
	        traverse(nodo->izq, nums);
	        nums.push_back(nodo->val);
	        traverse(nodo->der, nums);
	    }
	}
	
	template <typename T>
	vector<T> inorderTraversal(arbol<T>* raiz)
	{
	    vector<T> nums;
	    traverse(raiz, nums);
	    return nums;
	}

int main()
{
   
    ofstream archivo("Aleatorios.txt");

    srand(time(NULL));
	const int limite = 1000000;
	cout << "Generando...";
	for (int i = 0; i < limite; i++)
	{
	    int num = rand() % 20000001 - 10000000;
	    if (rand() % 2 == 0) {
	        num = -num; 
	    }
	    archivo << num << endl;
	}


	cout << " Hecho!" << endl;
    archivo.close();

    vector<int> numeros;
    ifstream archivo_lectura("Aleatorios.txt");
    int num;
    while (archivo_lectura >> num)
    {
        numeros.push_back(num);
    }

    char opcion;
    cout << "Ordenar los numeros aleatorios? (s/n): ";
    cin >> opcion;

    if (opcion == 's' || opcion == 'S')
    {
        arbol<int>* raiz = nullptr;
        for (int num : numeros)
        {
            ingresar(raiz, num);
        }

        vector<int> ordenados = inorderTraversal(raiz);

        ofstream ordenado("Ordenados.txt");

        for (int num : ordenados)
        {
            ordenado << num << endl;
        }

        ordenado.close();

        cout << "Hecho!" << endl;
    }
    else
    {
        cout << "Los numeros aleatorios no se han ordenado." << endl;
    }
    archivo_lectura.close();

    return 0;
}
