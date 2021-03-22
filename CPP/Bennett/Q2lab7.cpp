#include <iostream>
#include <vector>
using namespace std;

// g++ lab5.cpp -o out && out > output.txt

double * ProblemSolution(int a, int b){
    double da = a;
    double db = b;
    double ans1 = da*20+34/db;
    double *ans;
    *ans = ans1;
    return ans;
}

int main(){
    double ans = ProblemSolution(5,10);
    cout << ans << endl;
    return 0;
}
