#include <iostream>
#include <vector>
using namespace std;

int main()
{
    int n;
    cin >> n;
    vector<int> minions;

    for (int i = 0; i < n; i++)
    {
        int m;
        cin >> m;
        minions.push_back(m);
    }
    int k;
    cin >> k;

    bool flag = false;

    int min = 100000000;
    int max = -1;
    for (int i = 0; i < n; i++)
    {
        // cout << minions[i];
        if (minions[i] < min)
        {
            min = minions[i];
        }
        if (minions[i] > max)
        {
            max = minions[i];
        }
    }

    int ans = max - (k * min);
    if (ans <= 0)
        cout << "Yes" << endl;
    else
        cout << "No" << endl;

    return 0;
}