import heapq
class node:
    def __init__(self, freq, symbol, left=None, right=None):
        self.freq = freq
        self.symbol = symbol
        self.left = left
        self.right = right
        self.hufftree = ''

    def __lt__(self, nxt):
        return self.freq < nxt.freq
def printNodes(node, val=''):
    newVal = val + str(node.hufftree)
    if(node.left):
        printNodes(node.left, newVal)
    if(node.right):
        printNodes(node.right, newVal)
    if(not node.left and not node.right):
        tree[node.symbol]=newVal
tree={}
message=input()
char = []
for i in message:
    if i not in char:
        char.append(i)
freq = [message.count(i) for i in char]
nodes = []
for x in range(len(char)):
    heapq.heappush(nodes, node(freq[x], char[x]))

while len(nodes) > 1:
    left = heapq.heappop(nodes)
    right = heapq.heappop(nodes)
    left.hufftree = 0
    right.hufftree = 1
    newNode = node(left.freq+right.freq, left.symbol+right.symbol, left, right)
    heapq.heappush(nodes, newNode)
printNodes(nodes[0])
encrypted=''
for i in message:
    encrypted+=tree[i]
print('Huffman tree : ')
print(tree)
print('Given message : '+message)
print('Encrypted message : '+encrypted)
