#include<iostream>
#include<vector>
#include<string.h>
using namespace std;
string differenceoftwoconsecutive(string str)
{
    string ans="";
    ans+=str[0];
    for(int i=1;i<str.size();i++)
    {
        char ch0=str[i-1];
        char ch1=str[i];
        ans+=to_string(ch1-ch0);
        ans+=ch1;
    }
    return ans;
}

int main()
{
    return 0;
}