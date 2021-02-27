//Arrays only
#include<iostream>
#include<vector>
using namespace std;
int maximum(vector<int>& arr)
{
    int max=arr[0];
    for(int i=1;i<arr.size();i++)
    {
        if(arr[i] > max)
        {
            max=arr[i];
        }
    }
    return max;
} 
int minimum(vector<int>& arr)
{
    int min=arr[0];
    /* we can use this logic also:
        int min=(int)1e9;     //1e9=10^9
        for(int e:arr)
            if(e<min) min=e;
        return min;    
    */
    for(int i=1;i<arr.size();i++)
    {
        if(arr[i] < min)
        {
            min=arr[i];
        }
    }
    return min;
} 
int findData(vector < int > & arr, int data) {
    for (int i = 0; i < arr.size(); i++) {
        if (arr[i] == data)
            return i;
    }
    return -1;
}
void input(vector < int > & arr) {
    for (int i = 0; i < arr.size(); i++)
        cin >> arr[i];
}
void swap(vector<int>& arr,int a, int b) {
    int temp;
    temp = arr[a];
    arr[a] = arr[b];
    arr[b] = temp;
}
void reverse(vector < int > & arr) {
    int si=0, ei=arr.size()-1;
    while (si < ei) {
        swap(arr,si++,ei--);
    }
}
void sumOfTwoArrays(vector < int > & a1, vector < int > & a2) {
    int carry = 0;
    int i = a1.size() - 1, j = a2.size() - 1;
    vector < int > res(max(a1.size(), a2.size()) + 1, 0);
    int k = res.size() - 1;
    while (k >= 0) {
        int sum = carry;
        if (i >= 0)
            sum += a1[i--];
        if (j >= 0)
            sum += a2[j--];
        res[k--] = sum % 10;
        carry = sum / 10;
    }
    for (int r = 0; r < res.size(); r++) {
        if (r == 0 && res[r] == 0) continue;
        cout << res[r] << endl;
    }

}
void diffOfTwoArrays(vector < int > & a1, vector < int > & a2) {
    int borrow = 0;
    int i = a1.size() - 1, j = a2.size() - 1;
    vector < int > res(max(a1.size(), a2.size()), 0);
    int k = res.size() - 1;
    while (k >= 0) {
        int sum = borrow;
        if (j >= 0)
            sum += a2[j--];
        if (i >= 0)
            sum -= a1[i--];
        //we can write this on one line also
        //int sum=borrow+(j>=0?a2[j--]:0)-(i>=0?a1[i--]:0);
        if (sum < 0) {
            borrow = -1;
            sum += 10;
        } else
            borrow = 0;
        res[k--] = sum;
    }
    
    for (int r = 0; r < res.size(); r++) {
        if (r == 0 && res[r] == 0) continue;
        cout << res[r] << endl;
    }

}
void subArrays(vector < int > & arr) {
    for (int i = 0; i < arr.size(); i++) {
        for (int j = i; j < arr.size(); j++) {
            for (int k = i; k <= j; k++) {
                cout << arr[k] << "\t";
            }
            cout << endl;
        }
    }
}
void barChart(vector < int > & arr) {
    int max = maximum(arr);
    for (int floor = max; floor >= 1; floor--) {
        for (int j = 0; j < arr.size(); j++) {
            if (floor <= arr[j])
                cout << "*\t";
            else
                cout << "\t";
        }
        cout << endl;
    }
}


int main()
{
    int size;
    // int find;
    cin>>size;
  vector<int> arr(size,0);
  for(int i=0;i<arr.size();i++)
  {
      cin>>arr[i];
  }
//   int max=maximum(arr);
//   cout<<"Maximum is:"<<max;
//  int min=minimum(arr);
//  cout<<"Minimum is:"<<min;
    // cin>>find;
    // cout<<findData(arr,find);
  return 0;  
}