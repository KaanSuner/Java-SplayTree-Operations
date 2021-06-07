# Java-SplayTree-Operations
A program that has node insertion, deletion and search operation on Splay trees.
### Sample Input:
* insert 1
* insert 2
* remove 2
* insert 5
* remove 1
* find 5
* insert 19
* find 15
* insert 12
* remove 3
### Sample Output:
After each operation executed, it printslevel order traversal of tree. If there is no left or right
child element of any internal node, it prints a hyphen instead. For above input file, the output
must be like below:
* 1
* 2 1 -
* 1
* 5 1 -
* 5
* 5
* 19 5 -
* 5 - 19
* 12 5 19
* 5 - 12 - - - 19
