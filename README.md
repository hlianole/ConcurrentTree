# Concurrent Java AVL tree 

A self-balancing Binary Search Tree (AVL) which can be safely used
by different threads in one time.

## Operations:

`get(key)` where key is a byte[] array. Get operation (reading)
is not blocking and can be processed by many threads in one time.

`put(key, value)` where key and value are byte[] arrays. Put operation (writing)
is blocking which allows thread-safe tree balancing after every insertion.