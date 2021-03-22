#include <iostream>
#include <vector>
using namespace std;

int main()
{
    // MATRIX AND VECTORS ===============
    int matrix[3][4] = {{1, 2, 3, 4}, {5, 6, 7, 8}, {9, 10, 11, 12}};

    vector<int> vect1{1, 2, 3, 4};

    vector<int> vect2(4, 0);

    for (auto it = vect2.rbegin(); it < vect2.rend(); it++)
    {
        // cout << *it << " ";
    }
    cout << endl;

    for (int i = 0; i < vect1.size(); i++)
    {
        // cout << vect1[i] << " ";
    }

    // STRINGS ===============
    string name;

    // vector<string> vect3;

    // vect3 = {"Anil",
    //          "Dev",
    //          "Raj",
    //          "Sanjeev",
    //          "Amitabh",
    //          "Dharmendra"};

    // // int n = 5;
    // // while(n-->0){
    // //     cin >> name;
    // //     vect3.push_back(name);
    // // }

    // for(int i=0;i<vect3.size();i++){
    //     cout << vect3[i] << " ";
    // }
    cout << endl;
    return 0;
}
