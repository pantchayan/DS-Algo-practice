#include <vector>
#include <iostream>
#include <algorithm>

using namespace std;








void twoDVectorSort(vector<vector<int>>& vector1){

    // sorts 2d vector on the basis of values in 4th coloumn 
    
    std::sort(vector1.begin(),
          vector1.end(),
          [] (const std::vector<double> &a, const std::vector<double> &b)
          {
              return a[3] < b[3];
          });
}

void set1(){
    //twoDVectorSort();
}



int main(){
    set1();
    return 0;
}