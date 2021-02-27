#include<iostream>
#include<vector>
using namespace std;

void input(vector<vector<int>>& arr)
{
    int n=arr.size();
    int m=arr[0].size();
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cin>>arr[i][j];
         }
    }
}
void display(vector<vector<int>>& arr)
{
    int n=arr.size();
    int m=arr[0].size();
    for(int i=0;i<n;i++){
        for(int j=0;j<m;j++){
            cout<<arr[i][j]<<" ";
         }
         cout<<endl;
    }
}
    void forEachdisplay(vector<vector<int>>& arr)
    {
        for(vector<int> a:arr){
            for(int e:a){
                cout<<e<<" ";
            }
            cout<<endl;
        }
    }
bool findData(vector<vector<int>>& arr,int data){
        int n=arr.size();
        int m=arr[0].size();
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
              if(arr[i][j]==data)
               return true; 
        }
    }
    return false;
}
int maxEle(vector<vector<int>>& arr){
        int n=arr.size();
        int m=arr[0].size();
        int max=(int)-1e9;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
              if(arr[i][j]>max)
                max=arr[i][j];
            }
    }
    return max;
}
int minEle(vector<vector<int>>& arr){
        int n=arr.size();
        int m=arr[0].size();
        int min=(int)1e9;
        for(int i=0;i<n;i++){
            for(int j=0;j<m;j++){
              if(arr[i][j]<min)
                min=arr[i][j];
            }
    }
    return min;
}
int main()
{
    int n,m;
    cin>>n>>m;
    vector<vector<int>> arr(n,vector<int>(m,0));
    input(arr);
    display(arr);
    cout<<(boolalpha)<<findData(arr,14)<<endl;
    cout<<maxEle(arr)<<endl;
    cout<<minEle(arr)<<endl;

}