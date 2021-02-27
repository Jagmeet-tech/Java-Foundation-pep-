#include<iostream>
#include<vector>
#include<algorithm>
using namespace std;
void print(vector<int>&arr) {
    for (int i = 0; i < arr.size(); i++) {
      cout<<arr[i]<<endl;
    }
  }
void countSort(vector<int> & arr,int minEle,int maxEle)
{
    vector<int> freq((maxEle-minEle)+1,0);
    for(int ele:arr) freq[ele-minEle]++;  //arr to freq includes -ve indexes also
    int itr=0;
    for(int i=0;i<freq.size();i++)
    {
        int f=freq[i];
        while(f-->0)
            arr[itr++]=i+minEle;
    }
}



int newBinarySearch(vector<int>& arr,int data ,int si,int ei)
{
    int min=1e9;
    int i;
    while(si<=ei)
    {
        int mid=(si+ei)/2;
        int m=data-arr[mid];
        if(min>m)
            {
                min=m;
                i=mid;
            }
        if(arr[mid]<data)
            si=mid+1;
        else if(arr[mid]>data)
            ei=mid-1;
        else
            return mid;        
    }
    return i;
}
void targetSumPair(vector < int > & arr, int target) {
    sort(arr.begin(),arr.end());
    int si = 0, ei = arr.size() - 1;
    while (si < ei) {
        int sum = arr[si] + arr[ei];
        if (sum > target)
            ei--;
        else if (sum < target)
            si++;
        else {
            cout << arr[si] << ", " << arr[ei] << endl;
            si++;
            ei--;
        }
    }

}
int binarySearch(vector<int>& arr,int data ,int si,int ei)
{
    while(si<=ei)
    {
        int mid=(si+ei)/2;
        if(arr[mid]<data)
            si=mid+1;
        else if(arr[mid]>data)
            ei=mid-1;
        else
            return mid;        
    }
    return -1;
}
int nearestEle(vector<int> &arr, int data, int si, int ei)
{
    if (data > arr[ei])
        return arr[ei];
    else if (data < arr[si])
        return arr[si];

    while (si <= ei)
    {
        int mid = (si + ei) / 2;
        if (arr[mid] == data)
            return mid;
        else if (arr[mid] < data)
            si = mid + 1;
        else
            ei = mid - 1;
    }

    return (arr[si] - data <= data - arr[ei] ? arr[si] : arr[ei]);
}
/*int main() {
    int n;
    cin >> n;
    vector < int > arr(n, 0);
    for (int i = 0; i < n; i++) {
        cin >> arr[i];
    }
    int target;
    cin >> target;
    targetSumPair(arr, target);
}*/
int main()
{
    int n;
    cin>>n;
    vector<int> arr(n,0);
    for(int i=0;i<arr.size();i++)
        cin>>arr[i];
    int maxEle=-1e9;
    int minEle=1e9;
    for(int i=0;i<arr.size();i++)
    {
       maxEle= max(maxEle,arr[i]);
       minEle= min(minEle,arr[i]);
    }
    countSort(arr,minEle,maxEle);
    print(arr);
}