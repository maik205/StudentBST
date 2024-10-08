package studentbst;

public class BinarySearchTree<T extends Comparable<T>> {

    Node<T> root;

    BinarySearchTree() {
    }

    public void insert(T x) {
        if (this.root == null) {
            this.root = new Node<T>(x);
            return;
        }
        Node<T> traveller = root;
        while (true) {
            if (x.compareTo(traveller.info) >= 0) {
                if (traveller.right != null) {
                    traveller = traveller.right;
                } else {
                    traveller.right = new Node<T>(x, null, null, traveller);
                    break;
                }
            } else {
                if (traveller.left != null) {
                    traveller = traveller.left;
                } else {
                    traveller.left = new Node<T>(x, null, null, traveller);
                    break;
                }
            }
        }
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();

        traversePreOrder((Node<T> node) -> {
            sb.append(node.toString());
            sb.append(" ");
        }, root);

        return sb.toString();
    }

    public void traversePreOrder(ICallbackFn<T> callbackFn, Node<T> startNode) {
        if (startNode == null) {
            return;
        }
        if (startNode.left == null && startNode.right == null) {
            callbackFn.callback(startNode);
            return;
        }
        callbackFn.callback(startNode);
        if (startNode.left != null) {
            traversePreOrder(callbackFn, startNode.left);
        }
        if (startNode.right != null) {
            traversePreOrder(callbackFn, startNode.right);
        }

    }

    public void traverseInOrder(ICallbackFn<T> callbackFn, Node<T> startNode) {
        if (startNode == null) {
            return;
        }
        if (startNode.left == null && startNode.right == null) {
            callbackFn.callback(startNode);
            return;
        }
        if (startNode.left != null) {
            traverseInOrder(callbackFn, startNode.left);
        }
        callbackFn.callback(startNode);
        if (startNode.right != null) {
            traverseInOrder(callbackFn, startNode.right);
        }

    }

    public void traversePostOrder(ICallbackFn<T> callbackFn, Node<T> startNode) {
        if (startNode == null) {
            return;
        }
        if (startNode.left == null && startNode.right == null) {
            callbackFn.callback(startNode);
            return;
        }
        if (startNode.left != null) {
            traversePostOrder(callbackFn, startNode.left);
        }
        if (startNode.right != null) {
            traversePostOrder(callbackFn, startNode.right);
        }
        callbackFn.callback(startNode);

    }

    public Node<T> searchNode(T x) {
        if (this.root == null) {
            return null;
        }

        /**
         * This is an iterative approach, there is a traveller Node to go left
         * or right depending on the comparison.
         */
        // Node<T> traveller = root;
        // while (true) {
        // if (x >= traveller.info) {
        // if (traveller.right.info == x)
        // return traveller;
        // if (traveller.right != null) {
        // traveller = traveller.right;
        // } else {
        // return null;
        // }
        // } else {
        // if (traveller.left.info == x)
        // return traveller;
        // if (traveller.left != null) {
        // traveller = traveller.left;
        // } else {
        // return null;
        // }
        // }
        // }
        return searchNode(x, this.root);
    }

    /**
     * The recursive approach's base case is where the target is not found,
     * returning null and where the node is found. The function recurses
     * depending on the comparison between the working node's info and the
     * target.
     *
     * @param x    target
     * @param node working node
     * @return null if target is not found, the Node if target is found.
     */
    private Node<T> searchNode(T x, Node<T> node) {
        if (node == null) {
            System.out.println("Node not found");
            return null;
        }
        if (node.info.compareTo(x) == 0) {
            return node;
        }
        if (x.compareTo(node.info) >= 0) {
            return searchNode(x, node.right);
        } else {
            return searchNode(x, node.left);
        }
    }

    public void visitNode(Node<T> node, ICallbackFn<T> callbackFn) {
        callbackFn.callback(this.searchNode(node.info));
    }

    public void deleteByMerging(T target) {
        Node<T> targetNode = this.searchNode(target);
        System.out.println(targetNode);
        // Haven't handled edge case where target is root
        if (targetNode != null) {
            if (targetNode.right == null && targetNode.left == null) {
                System.out.println("deleting a leaf");
                if (targetNode.parent.left == targetNode) {
                    targetNode.parent.left = null;
                    return;
                }
                if (targetNode.parent.right == targetNode) {
                    targetNode.parent.right = null;
                    return;
                }
            }
            if ((targetNode.right == null && targetNode.left != null)) {
                System.out.println("deleting a node w 1 child");

                if (targetNode.parent.left == targetNode) {
                    targetNode.parent.left = targetNode.left;
                    return;
                }
                if (targetNode.parent.right == targetNode) {
                    targetNode.parent.right = targetNode.left;
                    return;
                }
            }
            if ((targetNode.right != null && targetNode.left == null)) {
                System.out.println("deleting a node w 1 child");
                if (targetNode.parent.left == targetNode) {
                    targetNode.parent.left = targetNode.right;
                    return;
                }
                if (targetNode.parent.right == targetNode) {
                    targetNode.parent.right = targetNode.right;
                    return;
                }
            }

            if (targetNode.right != null && targetNode.left != null) {
                Node<T> rmLeftNode = getRightmostNode(targetNode.left);
                rmLeftNode.right = targetNode.right;
                if (targetNode.parent.left == targetNode) {
                    targetNode.parent.left = targetNode.left;
                }
                if (targetNode.parent.right == targetNode) {
                    targetNode.parent.right = targetNode.left;
                }
                targetNode.right = null;
            }
        }

    }

    public void deleteByCopying(T target) {
        Node<T> tNode = this.searchNode(target);
        if (tNode != null) {
            Node<T> lrmNode = getRightmostNode(tNode.left);
            System.out.println(lrmNode);
            System.out.println(tNode);
            System.out.println(tNode.left);
            tNode.info = lrmNode.info;
            if (lrmNode.parent.right == lrmNode) {
                lrmNode.parent.right = null;
            }
            if (lrmNode.parent.left == lrmNode)
                lrmNode.parent.left = null;
        }
    }

    private Node<T> getRightmostNode(Node<T> pNode) {
        if (pNode.right == null) {
            return pNode;
        }
        return getRightmostNode(pNode.right);
    }
}
