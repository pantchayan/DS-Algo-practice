#include <iostream>
#include <string>
#include <utility>
using namespace std;
int main()
{
    int k,flag;
    cin>>k;
    
    
    string bString;
    cin>>bString;
    
    flag = 0;
    
    int totalCount=100;
    int t=10;
    for(int i=0;i<bString.length();i++)
    {
        if(bString[i]=='1')
        {
            totalCount=totalCount+t;
            t=10;
        }
        else
        {
            totalCount=totalCount-t;
            t*=2;
        }
    }
    
    if(totalCount<0) flag = 1;
    if(flag==1)
    {
        cout<<-1;
    }
    else
    {
        cout<<totalCount;
    }

}