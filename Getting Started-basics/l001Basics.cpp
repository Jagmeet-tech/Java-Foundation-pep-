#include<iostream>
using namespace std;
void multiply(int a,int b)
{
    cout<<a*b;
}

void printTableN(int n)
{
    for(int i=1;i<=10;i++)
    // cout<<n<<"X"<<i<<"="<<n*i<<endl; its not useful when we want to return string.
    cout<<to_string(n)+"X"+to_string(i)+"="+to_string(n*i)<<endl;
}
void printTableTillN(int m)
{
    for(int i=1;i<=m;i++)
    {
        printTableN(i);
        cout<<endl;
    }
}
void isEven(int n)
{
    if(n%2==0)
     cout<<"EVEN"<<endl;
     else
      cout<<"ODD"<<endl;
}
void print()
{
    // printf("%%d");
    //  printf("%\n");
}
int countDigits(int n)
{
    int count=0;
    while(n!=0)
    {
        n/=10;
        count++;
    }
    return count;
}
void reverse(int n)
{
    int lastdigit=0;
    while(n!=0)
    {
        lastdigit=n%10;
        n=n/10;
        cout<<lastdigit<<endl;
    }
}
int reverseNumber(int n)
{
    int lastdigit=0,ans=0;
    while(n!=0)
    {
        lastdigit=n%10;
        n/=10;
        ans=ans*10 + lastdigit;
        
    }
    return ans;
}
void fibonacci(int n)
{
    int a=-1,b=1;
    while(n--)
    {
      int c=a+b;
      cout<<c<<endl;
      a=b;
      b=c;
    }

}


bool pythagoreanTriplet(int a,int b,int c)
{
    bool ans=(a*a +b*b==c*c) ||(a*a +c*c==b*b) ||(b*b +c*c==a*a);
    return ans;
}

int rotate(int n,int k)
{
    int len=1;
    while(n!=0)
    {
        len++;
        n/=10;
    }
    int mul=1;
    int div=1;
    for(int i=1;i<=len;i++)
    {
        if(i<=k) div=div*10;
        else mul=mul*10;
    }
    int r= n % div;
    int q= n / div;
    return r*mul +q;

}

int GCD(int divident,int divisor)
{
    //int r=1;
    while(divident % divisor!=0)
    {
        int r=divident % divisor;
        divident=divisor;
        divisor=r;
    }
    return divisor; 
}
int LCM(int num1,int num2,int gcd)
{
    return (num1*(num2/gcd));
}

void digitsOfNumber(int n)
{
    int len=0,pow=1;
    int temp=n;
    while(temp!=0)
    {
        len++;
        temp/=10;
    }
    while(len>1)
    {
        pow=pow*10;
        len--;
    }
    int r=0;
    while(pow!=0)  //we dont write n!=0 as it doesnot handle 1000 no. case as it prints only 1
    {
        r=n/pow;
        n=n%pow;
        pow/=10;
        cout<<r<<endl;
    }
}

int pow(int n)
{
    int pow=1;
    while(n!=0)
    {
        pow=pow*10;
        n--;
    }
    return pow;
}
void inverse(int n)
{
    int inv=0,op=1;
    while(n!=0)
    {
        int od=n%10;
        int ip=od;
        int id=op;
        // make change in inverse using ip and id
        inv=inv+id*pow(ip-1);
        op++;
        n/=10;
    }
    cout<<inv<<endl;
}


int main() 
{
//     int a,b;
//     cin>>a>>b;
//     int n;
//     cin>>n;
//     // multiply(a,b);
//     //cout<<"hello"<<endl;
//     // printTableTillN(n);
//     // isEven(n);
//     // print();
//    cout<< reverseNumber(n);
// int num1, num2;
//     cin >> num1 >> num2;
//     int g = GCD(num1, num2);
//     cout << g << "\n" << LCM(num1, num2, g);
//     return 0;
//  int n;
//  cin>>n;
//  digitsOfNumber(n);
}