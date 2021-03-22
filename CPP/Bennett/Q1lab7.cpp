#include <iostream>
#include <vector>
using namespace std;

// g++ lab5.cpp -o out && out > output.txt


int main(){
    vector<string> vect3;

    vect3 = {"Anil",
             "Dev",
             "Raj",
             "Sanjeev",
             "Amitabh",
             "Dharmendra"};

    string name = "Amitabh";
    int m = name.length();
    for(int i=0;i<vect3.size();i++){
        int n = vect3[i].length();
        if(n==m){
            bool flag = true;
            for(int j=0;j<n;j++){
                if(name[j] != vect3[i][j]){
                    flag = false;
                    break;
                }
            }
            if(flag){
                cout << i << endl;
                break;
            }
        }
    }
    return 0;
}
