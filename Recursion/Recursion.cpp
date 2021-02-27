#include<iostream>
#include<vector>
using namespace std;

void printDecresing(int n) {
    if (n == 0)
        return;
    cout << n << endl;
    printDecresing(n - 1);
}

void printIncreasing(int n) {
    if (n == 0)
        return;
    printIncreasing(n - 1);
    cout << n << endl;
}

int factorial(int n){
    if(n==0)
        return 1;
    return (n*factorial(n-1));    
}

void printIncDec(int n) {
    if (n == 0)
        return;
    cout << n << endl;
    printIncDec(n - 1);
    cout << n << endl;
}

void printRectangle(int nst,int cst,int n,int nrows)
{
    if(n+1!=nrows)
    {
        if(cst==nst+1)
        {
            cout<<endl;
            cst=1;
            nrows+=1;
            return(printRectangle(nst,cst,n,nrows));
         }
    cout<<"*";
    return(printRectangle(nst,cst+1,n,nrows));
    }
}
void printTriangle(int nst,int cst,int n,int nrows)
{
    if(n+1!=nrows) 
    {if(cst==nst+1)
       {
        cout<<endl;
        cst=1;
        nst+=1;
        nrows+=1;
        return(printTriangle(nst,cst,n,nrows));
       }
    cout<<"*";
    return(printTriangle(nst,++cst,n,nrows));
    }
}
bool isPalindrome(vector<int>& arr,int idx,int endx)
{
    if(idx<=endx)
    {
        if(arr[idx]!=arr[endx])
            return false;
        return(isPalindrome(arr,idx+1,endx-1));
    }
    return true;
}
void reverseArray(vector<int>& arr,int idx,int endx)
{
    if(idx<=endx)
    {
        swap(arr[idx],arr[endx]);
        reverseArray(arr,idx+1,endx-1);
    }
}
void inverseArray(vector<int>& arr,int idx)
{
    if(idx==arr.size()) return;
    int val=arr[idx];
    inverseArray(arr,idx+1);
    arr[val]=idx;
}
bool isPalindromeIgnorecase(string s,int idx,int endx)
{
    if(idx<=endx)
    {
        if(s[idx]>='a' && s[idx]<='z'&& s[endx]>='A' && s[endx]<='Z')
        {
          s[idx]=toupper(s[idx]);
        } 
        if(s[idx]>='A' && s[idx]<='Z'&& s[endx]>='a' && s[endx]<='z'  )
        {
            s[idx]=tolower(s[idx]);
        }
        if(s[idx]!=s[endx])
           return false;
        isPalindrome(s,idx+1,endx-1);
    }
    return true;
    
}

int main() {
    int nst=1,n,cst=1,nrows=1;
    cin >> n>>nst;
   // printRectangle(nst,cst+1,n,nrows);
    printTriangle(nst,cst,n,nrows);
    // printDecresing(n);
    //   vector<int> arr(n,0);
    // for(int i=0;i<n;i++)
    //     cin>>arr[i];
    // int d;
    // cin>>d;
    // reverseArray(arr,0,n-1);
    // for(int i=0;i<n;i++)
    //     cout<<arr[i]<<" ";
    // cout<<endl;
    // cout<<arr[d];
    return 0;
}