#include <iostream>
#include <vector>
using namespace std;


class Node{
    public: 
        int data = 0;
        Node* left=NULL;
        Node* right=NULL; 

        Node(int data){
            this->data = data;
        }
};

Node* root;




void constructTree(){
    root = new Node(1);
    root->left = new Node(2);
    root->right = new Node(3);
    root->left->left = new Node(4);
    root->left->right = new Node(5);

    // display(root);

}


void set1(){
    constructTree();
}

int main(){
    
    set1();
    return 0;
}

