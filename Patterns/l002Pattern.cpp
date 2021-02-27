void pattern14(int n) {
    for (int i = 1; i <= 10; i++)
        // cout<<n<<"X"<<i<<"="<<n*i<<endl; its not useful when we want to return string.
        cout << to_string(n) + " * " + to_string(i) + " = " + to_string(n * i) << endl;
}