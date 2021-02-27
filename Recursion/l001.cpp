#include<iostream>
#include<vector>
using namespace std;
void printStairPaths(int sc, int dc,string myans) {
    if (sc == dc) {
        cout<< myans<<endl;
        return ;
    }
    //horizontal
    for (int jump = 1; jump <= 3; jump++) {
        if (sc + jump <= dc) {
               printStairPaths(sc + jump, dc,myans +to_string(jump));
        }
    }
}
vector < string > getStairPaths(int sc, int dc) {
    //in this plzz check you have to pass n not n-1 because for n=3 indexes are i=0,1,2,3;
    //jumps are 1,2,3
    if (sc == dc) {
        vector < string > base;
        base.push_back("");
        return base;
    }
    vector < string > myans;
    //horizontal
    for (int jump = 1; jump <= 3; jump++) {
        if (sc + jump <= dc) {
            vector < string > res = getStairPaths(sc + jump, dc);
            for (string s: res)
                myans.push_back(to_string(jump) + s);
        }
    }
    return myans;
}
void printMazePaths_Jumps(int sr, int sc, int dr, int dc, string ans) {
    if (sr == dr && sc == dc) {
        cout << ans << endl;
        return;
    }

    for (int jump = 1; sc + jump <= dc; jump++)
        printMazePaths_Jumps(sr, sc + jump, dr, dc, ans + 'h' + to_string(jump));
    for (int jump = 1; sr + jump <= dr; jump++)
        printMazePaths_Jumps(sr + jump, sc, dr, dc, ans + 'v' + to_string(jump));
    for (int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++)
        printMazePaths_Jumps(sr + jump, sc + jump, dr, dc, ans + 'd' + to_string(jump));
}

vector < string > getMazePathsJumps(int sr, int sc, int dr, int dc) {
    if (sr == dr && sc == dc) {
        vector < string > base;
        base.push_back("");
        return base;
    }
    vector < string > myans;
    //horizontal jumps
    for (int jump = 1; sc + jump <= dc; jump++) {
        vector < string > res = getMazePathsJumps(sr, sc + jump, dr, dc);
        for (string s: res)
            myans.push_back("h" + to_string(jump) + s);
    }
    //vertical jumps
    for (int jump = 1; sr + jump <= dr; jump++) {
        vector < string > res = getMazePathsJumps(sr + jump, sc, dr, dc);
        for (string s: res)
            myans.push_back("v" + to_string(jump) + s);
    }
    //diagonal jumps
    for (int jump = 1; sr + jump <= dr && sc + jump <= dc; jump++) {
        vector < string > res = getMazePathsJumps(sr + jump, sc + jump, dr, dc);
        for (string s: res)
            myans.push_back("d" + to_string(jump) + s);
    }
    return myans;
}
int  printMazePaths(int sr, int sc, int dr, int dc, string myans){
    if(sr==dr && sc==dc)
    {
        cout<<myans<<endl;
        return 1;
    }  
    int count=0;
    //horizontal  
    if(sc+1<=dc)
        count+=printMazePaths(sr,sc+1,dr,dc,myans+"h");
    //vertical  
    if(sr+1<=dr)
        count+=printMazePaths(sr+1,sc,dr,dc,myans+"v");
    return count;        
}


vector < string > getMazePathsDiagonal(int sr, int sc, int dr, int dc) {
    if (sr == dr && sc == dc) {
        vector < string > base;
        base.push_back("");
        return base;
    }
    vector < string > myans;
    //horizontal
    if (sc + 1 <= dc) {
        vector < string > res = getMazePathsDiagonal(sr, sc + 1, dr, dc);
        for (string s: res)
            myans.push_back("h" + s);
    }
    //vertical
    if (sr + 1 <= dr) {
        vector < string > res = getMazePathsDiagonal(sr + 1, sc, dr, dc);
        for (string s: res)
            myans.push_back("v" + s);
    }
    //diagonal
    if (sr + 1 <= dr && sc + 1 <= dc) {
        vector < string > res = getMazePathsDiagonal(sr + 1, sc+1, dr, dc);
        for (string s: res)
            myans.push_back("d" + s);
    }
    return myans;

}

vector < string > getMazePaths(int sr, int sc, int dr, int dc) {
    if (sr == dr && sc == dc) {
        vector < string > base;
        base.push_back("");
        return base;
    }
    vector < string > myans;
    //horizontal
    if (sc + 1 <= dc) {
        vector < string > res = getMazePaths(sr, sc + 1, dr, dc);
        for (string s: res)
            myans.push_back("h" + s);
    }
    //vertical
    if (sr + 1 <= dr) {
        vector < string > res = getMazePaths(sr + 1, sc, dr, dc);
        for (string s: res)
            myans.push_back("v" + s);
    }
    return myans;

}
void display(vector < string > & arr) {
    cout << "[";
    for (int i = 0; i < arr.size(); i++) {
        cout << arr[i];
        if (i < arr.size() - 1) cout << ", ";
    }

    cout << "]" << endl;
}

int main() {
    int n;
    cin >> n;
    vector < string > ans = getStairPaths(0, n - 1);
    display(ans);

    return 0;
}