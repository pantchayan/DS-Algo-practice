#include <iostream>
#include <vector>
#include <string>
using namespace std;


int main() {
    int n;
    int k;
    
    cin >> n;
    cin >> k;
    
    vector<string> players;
    
    string player;
    for(int i=0;i<n;i++){
        cin >> player;
        players.push_back(player);
    }
        
    int count = 0;
    int currK = 0;
    for(int i=0;i<n;i++){
        if(players[i]=="S"){
            currK = k;
        }
        else{
            if(currK-->0){
                count++;
            }
        }
    }
    
    cout << count << endl;
    
    
    return 0;
}