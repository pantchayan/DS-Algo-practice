#include <iostream>
#include <vector>
#include <string>
using namespace std;


void printMatrix(vector<vector<int>> matrix){
    for(int i=0;i<matrix.size();i++){
        for(int j=0;j<matrix[0].size();j++){
            cout << matrix[i][j] << " ";
        }
        cout << endl;
    }
}

void zTraversal(vector<vector<int>> matrix){
    for (int i = 0; i < matrix.size(); i++)
        cout << matrix[0][i] << " ";
    int i = 1, j = matrix.size() - 2;
    while (i < matrix.size() && j >= 0){
        cout << matrix[i][j] << " ";
        i++;
        j--;
    }
    for (int i = 1; i < matrix.size(); i++)
        cout << matrix[matrix.size() - 1][i] << " ";
}

void zigzagTraversal(vector<vector<int>> matrix){
    int row = 0;
    int col = 0;
    while(row<matrix.size()){
        if(col==0){
            while(col<matrix[0].size()){
                cout << matrix[row][col] <<" ";
                col++;
            }
            col--;
        }
        else{
            while(col>=0){
                cout << matrix[row][col] <<" ";
                col--;
            }
            col++;
        }
        row++;
    }
    cout << endl;
}

int main() {
    int n;
    int m;
    cin >> n;
    cin >> m;
    vector<vector<int>> matrix(n , vector<int> (m, 0));
    string trav;
    cin >> trav;
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin >> matrix[i][j];
        }
    }
    
    printMatrix(matrix);
    
    
    if(trav == "z"){
        zTraversal(matrix);
    }
    else if(trav == "zig"){
        zigzagTraversal(matrix);
    }
    
    
    return 0;
}