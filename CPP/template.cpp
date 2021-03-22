#include <iostream>
#include <vector>
using namespace std;

// g++ lab5.cpp -o out && out > output.txt


int main(){
    int matrix[3][4] = {{1,2,3,4},{5,6,7,8},{9,10,11,12}};


    vector<int> vect1 {1,2,3,4};

    vector<int> vect2(4,0);

    for(auto it= vect2.rbegin();it<=vect2.rend();it++){
        cout << *it << endl;
    }

    return 0;
}
