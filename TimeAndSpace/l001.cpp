#include<iostream>
#include<vector>
using namespace std;

void partition(vector<int>& arr, int pivot) {
    int i = 0, j = 0;
    while (i < arr.size()) {
        if (arr[i] <= pivot) {
            swap(arr, i, j);
            i++;
            j++;
        } else
            i++;
    }
}
void sort012(vector < int > & arr) {
    int i = 0, j = 0, k = arr.size() - 1; //region //0[0 to j-1] //1[j to i-1] //undefined[i to k-1] //2[k to  arr.size()-1]
    while (i <= k) {
        if (arr[i] == 1)
            i++;
        else if (arr[i] == 0) {
            swap(arr, i, j);
            j++;
            i++;
        } else {
            swap(arr, i, k);
            k--;
        }
    }

}
void swap(vector<int>& arr, int i, int j) {
        cout<<"Swapping index " + to_string(i) + " and index " + to_string(j);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
}
void sort01(vector<int>& arr) {
        int i = 0, j = 0; //(Regions)  j:0 [0 to j-1]  //i:1 [j to i-1]  //undefined [i to arr.length-1]
        while (i < arr.size()) {
            if (arr[i] == 1)
                i++;
            else {
                swap(arr, i, j);
                i++;
                j++;
            }
        }
    }   
vector < int > mergeTwoSortedArrays(vector < int > & A, vector < int > & B) {
        if (A.size() == 0 || B.size() == 0)
        return A.size() == 0 ? B : A;
    int n = A.size();
    int m = B.size();
    int i = 0, j = 0, k = 0;
    vector < int > res(n + m, 0);
    while (i < n && j < m) {
        if (A[i] < B[j])
            res[k++] = A[i++];
        else
            res[k++] = B[j++];
    }
    while (i < n) {
        res[k++] = A[i++];
    }
    while (j < m) {
        res[k++] = B[j++];
    }
    return res;
}
vector < int > mergeSort(vector < int > & arr, int idx, int edx) {
    if (idx == edx) {
        vector < int > base;
        base.push_back(arr[idx]);
        return base;
    }
    int mid = (idx + edx) / 2;
    vector < int > a = mergeSort(arr, idx, mid);
    vector < int > b = mergeSort(arr, mid + 1, edx);
    return mergeTwoSortedArrays(a, b);
}
void input(vector < int > & arr) {
    for (int i = 0; i < arr.size(); i++) {
        cin >> arr[i];
    }
}

void print(vector < int > & arr) {
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i] << " ";
    }
    cout << endl;
}
void bubbleSort(vector < int > & arr, int n) {
    for (int i = 1; i < n; i++) {
        for (int j = 0; j <n - i; j++) {
            if (arr[j] > arr[j + 1]) {
                int temp = arr[j];
                arr[j] = arr[j + 1];
                arr[j + 1] = temp;
            }
        }
    }

}

void selectionSort(vector<int>& arr) {
    for (int i = 0; i < arr.size() - 1; i++) {
        int min = i;
        for (int j = i + 1; j < arr.size(); j++) {
            if (isSmaller(arr, j, min))
                min = j;
            }
        swap(arr, i, min);
    }
}
 void insertionSort(vector<int>& arr) {
        int i, j;
        for (i = 1; i < arr.size(); i++) {
            for (j = i - 1; j >= 0; j--) {
                if (isGreater(arr, j, j + 1))
                    swap(arr, j, j + 1);
                else
                    break;
            }

        }

    }
    // used for swapping ith and jth elements of array
     void swap(vector<int>& arr, int i, int j) {
        cout<<"Swapping " + to_string(arr[i]) + " and "+ to_string(arr[j]);
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    // return true if jth element is greater than ith element
    bool isGreater(vector<int> &arr, int j, int i) {
        cout<<"Comparing " + to_string(arr[i]) + " and " + to_string(arr[j]);
        if (arr[i] < arr[j]) {
            return true;
        } else {
            return false;
        }
    }   
int main() {
    int n, m;
    cin >> n;
    vector < int > A(n, 0);
    input(A);

    vector < int > ans = mergeSort(A, 0, n);
    cout << "Sorted Array -> ";
    print(ans);
    return 0;
}
// used for swapping ith and jth elements of array
void swap(vector < int > & arr, int i, int j) {
    cout << "Swapping " + to_string(arr[i]) + " and " + to_string(arr[j])<<endl;
    int temp = arr[i];
    arr[i] = arr[j];
    arr[j] = temp;
}

// return true if ith element is smaller than jth element
bool isSmaller(vector < int > & arr, int i, int j) {
    cout << "Comparing " + to_string(arr[i]) + " and " + to_string(arr[j])<<endl;
    if (arr[i] < arr[j]) {
        return true;
    } else {
        return false;
    }
}