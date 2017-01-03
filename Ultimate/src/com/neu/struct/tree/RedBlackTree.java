package com.neu.struct.tree;

/**
 * 问题：如何实现红黑树
 *      红黑树是许多“平衡”搜索树中的一种，可以保证在最坏的情况下基本动态集合操作的时间复杂度为O(lgn)
 * 性质：红黑树是一棵二叉搜索树，它在每个节点上增加了一个存储位来表示节点的颜色，可以是red或black。
 *      通过对任何一条从根到叶子的简单路径上各个节点的颜色进行约束，红黑树确保没有一条路径会比其他路径长出2倍，
 *      因而是近乎于平衡的。
 *      树中每个节点包含5个属性：color、key、left、right、parent。如果一个节点没有子节点或父节点，
 *      则该节点相应指针属性的值为null。我们可以把这些null视为指向二叉搜索树的叶节点的指针，
 *      而把带关键字的节点视为树的内部节点。
 *      一棵红黑树是满足下面性质的二叉搜索树：
 *      1.每个节点或是红色的，或是黑色的
 *      2.根节点是黑色的
 *      3.每个叶节点是黑色的
 *      4.如果一个节点是红色的，则它的两个子节点都是黑色的。
 *      5.对每个节点，从该节点到其所有后代节点的简单路径上，均包含相同数目的黑色节点
 *
 *为了便于处理红黑树代码中的边界条件，使用一个哨兵来代表null。对于一棵红黑树T，
 *哨兵T.nil是一个树中普通节点有相同属性的对象。它的color属性为black，
 *而其它属性key、left、right、parent可以设为任意值
 *所有指向null的指针都用哨兵T.nil的指针替换。使用哨兵后，就可以将节点x的null孩子视为一个普通节点，
 *该哨兵节点的父节点为x。
 *尽管可以为树内的每个null新增一个不同的哨兵节点，使得每个null的父节点都有这样的良定义，
 *但这种做法会浪费空间。取而代之的是，
 *使用一个哨兵来代表说所有的null：所有的叶节点和父节点的根节点。
 *哨兵节点中的parent、left、right、key取值并不重要
 *通常将重点放在红黑树树的内部节点上，因为它们存储了关键字的值
 *
 * 定义：
 *    黑高：从某个节点x出发（不含该节点）到达一个叶子节点的任意一条简单路径上的黑色节点的个数称为该节点黑高。
 *         （从该节点出发的所有下降到其叶节点的简单路径的黑节点个数相同）。
 *         于是定义红黑树的黑高为根节点到其叶节点的黑高
 *    哨兵：哨兵是一个哑对象，其作用是简化边界条件的处理。哨兵基本不能降低数据结构相关操作的渐进时间界，
 *         但可以降低常数因子。在循环语句中使用哨兵的好处往往在于使代码简介，而不是提高速度
 * 引理：
 *    一棵有n个内部节点的红黑树的高度至多为2lg(n+1)
 * Created by lihongyan on 2015/11/8.
 */
public class RedBlackTree {

    class Node{
        private int key;
        private String color;
        private Node left;
        private Node right;
        private Node parent;
        public Node(){
            this.key = 0;
            this.color = "";
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }
        public Node(int key){
            this.key = key;
            this.color = null;
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }
        public Node(int key,String color){
            this.key = key;
            this.color = color;
            this.left = nil;
            this.right = nil;
            this.parent = nil;
        }

        public int getKey() {
            return key;
        }
        public void setKey(int key) {
            this.key = key;
        }
        public String getColor() {
            return color;
        }
        public void setColor(String color) {
            this.color = color;
        }
        public Node getLeft() {
            return left;
        }
        public void setLeft(Node left) {
            this.left = left;
        }
        public Node getRight() {
            return right;
        }
        public void setRight(Node right) {
            this.right = right;
        }
        public Node getParent() {
            return parent;
        }
        public void setParent(Node parent) {
            this.parent = parent;
        }

        @Override
        public String toString() {
            String temp = this.getKey()+":"+this.getColor();
            return temp;
        }
    }

    private Node root = null;
    private Node nil = new Node(0,"black");//定义哨兵节点

    public RedBlackTree(){
        this.root = this.getNil();
    }
    public RedBlackTree(int key){
        Node temp = new Node();
        temp.setKey(key);
        temp.setColor("black");
        temp.setParent(this.getNil());
        temp.setRight(this.getNil());
        temp.setLeft(this.getNil());
        this.setRoot(temp);
    }
    public Node getRoot() {return root;}
    public void setRoot(Node root) {this.root = root;}

    public Node getNil() {return nil;}
    public void setNil(Node nil) {this.nil = nil;}

    /*
     * 最小关键字结点和最大关键字结点
     * */
    public Node rb_tree_minimum(Node localNode){
        while(localNode.left!=this.getNil()){
            localNode = localNode.left;
        }
        return localNode;
    }
    public Node rb_tree_maximum(Node localRoot){
        while(localRoot.right!=this.getNil()){
            localRoot = localRoot.right;
        }
        return localRoot;
    }
    /*
     * 旋转
     *    搜索树操作:tree_insert()和tree_delete()在含n个关键字的红黑树上，运行花费时间为O(lgn)
     *    由于这两个操作对树做了修改，结果可能违反了红黑树的定义，为了维护这些性质，必须要改变树中某些节点的颜色和指针结构
     *    指针结构的修改是通过旋转来完成的，这是一种能保持二叉搜索树性质的搜索树局部操作
     * 旋转包括：左旋转和右旋转
     * */
    //左旋转（逆时针方向）:前提：要左旋转的节点的右孩子不为T.nil。
    //当在某个节点x上做左旋转时，假设它的右孩子为y而不是T.nil。(要旋转的节点x可以是其右孩子不是T.nil的树内任意节点)
    //过程：左旋以x到y的链为“支轴”进行逆时针方向的旋转。它使y成为该子树新的根节点，x成为y的左孩子，y的左孩子成为x的右孩子
    private void tr_tree_left_rotate(RedBlackTree T,Node x){
        Node y = x.right;//获取要旋转的节点x的右孩子y
        x.right = y.left;//使x的右指针指向y的左孩子，而不再指向y
        if(y.left!=T.getNil()){
            y.left.parent = x;//将y的左孩子的父节点设置为x，完成y的左孩子成为x的右孩子操作
        }
        y.parent = x.parent;//使y的父节点与x的父节点相同
        if(x.parent==T.getNil()){
            T.root = y;//如果x的父节点为T.nil，那么x原来为根节点，所以将y设置为新的根节点
        }else if(x==x.parent.left){
            x.parent.left = y;//如果x为其父节点的左孩子，那么将y变为x的父节点的左孩子，以替换x
        }else{
            x.parent.right = y;//如果x为其父节点的右孩子，那么将y变为x的父节点的右孩子，以替换x
        }
        y.left = x;
        x.parent = y;//衔接x.parent.left(right)=y操作，使x称为y的孩子
    }
    //右旋转（顺时针旋转）：前提：要旋转的节点的左孩子不为T.nil
    //当在某个节点y上做右旋转时，假设它的左孩子为x而不是T.nil。（要旋转的节点y可以是其左孩子不是T.nil的树内任意节点）
    //过程：右旋以y到x的链为“支轴”进行顺时针方向的旋转，它是x称为该子树新的根节点，y成为x的右孩子，x的右孩子称为y的左孩子
    private void rb_tree_right_rotate(RedBlackTree T,Node y){
        Node x = y.left;//寻找要旋转节点y的左孩子x
        y.left = x.right;//使要旋转节点y的左指针指向x的右孩子，而不再指向x
        if(x.right!=T.getNil()){
            x.right.parent = y;
        }
        x.parent = y.parent;//使x的父节点与y的父节点相同
        if(y.parent==T.getNil()){
            T.root = x;//如果y的父节点为空，那么y原来为根节点，将x设置为新的根节点
        }else if(y==y.parent.left){
            x = y.parent.left;//如果y为其父节点的左孩子，那么将x变为y的父节点的左孩子
        }else{
            x = y.parent.right;//如果y为其父节点的右孩子，那么将x变为y的父节点的右孩子
        }
        x.right = y;
        y.parent = x;
    }
    /*
    * 插入（假设z的key属性已经被实现赋值）
    * 我们可以在O(lgn)时间内完成向一棵含n个节点的红黑树中插入一个新节点，为了做到这一点，
    * 利用tree_insert()过程的一个略作修改的版本来将节点z插入树T内，就好像T是一棵普通的二叉搜索树一样，然后将z着为红色。
    * 为保证红黑性质能继续保持，我们调用一个辅助程序rb_insert_fixup()来对节点重新着色并旋转。
    * 调用re_tree_insert(T,z)在红黑树T内插入节点z。
    * */
    public void rb_tree_insert(RedBlackTree T,int key){
        Node z = new Node(key);
        Node y = T.nil;
        Node x = T.getRoot();
        while(x!=T.nil){
            y = x;
            if(key<x.getKey()){
                x = x.left;
            }else{
                x = x.right;
            }
        }
        z.parent = y;
        if(y==T.nil){
            T.root = z;
        }else if(key<y.getKey()){
            y.left = z;
        }else{
            y.right = z;
        }
        z.left = T.nil;
        z.right = T.nil;
        z.setColor("red");
        rb_tree_insert_fixup(T,z);
    }
    //re_insert_fixup()过程分析
    //①在调用rb_tree_fixup()操作时，哪些红黑性质会被破坏？
    // 答：只有性质2和性质4可能被破坏。
    //    性质1显然不会被破坏；
    //    对于性质3，因为新插入的是红节点，并且其左右两个孩子是哨兵节点(黑色)，所以叶节点仍然是黑节点
    //    对于性质5，新插入的节点取代了原来的哨兵节点，但新插入的节点是红色的，并且自带了两个黑色的哨兵节点。
    //    对于性质2，如果红黑树是一棵空树，那么新插入的节点将作为根节点，由于新插入的节点是红色的，所以违反性质2。
    //    对于性质4，如果要插入的节点的父节点是红色的，并且要插入的节点是红色的，所以违反性质4。
    //②每次循环的开头保持的3个不变式：
    //  1.节点z是红色的
    //  2.如果z.parent是根节点，则z.parent是黑色的
    //  3.如果有任何红黑性质被破坏，则至多只有一条被破坏，或是性质2，或是性质4。
    //    如果性质2被破坏，其原因是z是根节点且是红节点；如果性质4被破坏，其原因是z和z.parent都是红节点。

    //  每次循环都必须保持这个循环不变式成立，并且在循环终止时，这个循环不变式会给出一个有用的性质。
    //  循环每次迭代会有两种可能的结果：或者指针z沿树上移，或者执行某些旋转后循环终止。

    //证明循环不变式在初始化、终止阶段成立：
    //初始化：在循环的第一次迭代之前，从一棵正常的红黑树开始，并新增一个红节点z
    //   a.当调用rb_tree_insert_fixup()时，z是新增的红节点。所以不变式a成立
    //   b.如果z.parent是根，那么z.parent开始时黑色的，且在调用rb_tree_insert_fixup()之前保持不变
    //   c.在调用rb_tree_insert_fixup()时，性质1、性质3、性质5成立。唯一可能违反的只有性质2、性质4.
    //     1.如果违反了性质2，则红色根节点一定是新增节点z，它是树中唯一的内部节点。因为z的父节点和两个子节点都是黑色的哨兵，没有违反性质4。
    //       这样对性质2的违反是整棵树中唯一违反红黑性质的地方
    //     2.如果违反了性质4，由于z的子节点是黑色的哨兵节点，且该树在z加入之前没有其他性质的违反(目的就是说明z.parent不是根节点，也就是说明没有违反性质2)，
    //       所以违反性质4，必然是因为z和z.parent都是红色的。

    //终止：
    //   a.
    //   b.
    //   c.循环终止是因为z.parent是黑色的（如果z是根节点，那么z.parent是黑色的哨兵节点T.nil，用于说明z.parent始终是黑色的）。
    //     这样，树在循环终止时没有违反性质4。由于整个循环不可能违反性质1、性质3、性质5，所以唯一可能违反的只有性质2，也就满足循环不变式的c部分

    //证明z.parent.parent的存在？
    //   因为只有在z.parent是红色时才能进入while循环，所以在三种情况中并根据性质2，
    //   z.parent不可能是根节点，所以z.parent.parent存在。
    //证明z.parent.parent是黑色的？
    //   因为只有在z.parent是红色的才能进入while循环，所以在三种情况中，z.parent.parent一定是黑色的
    //结论：在六种情况中，z.parent.parent一定存在并且是黑色的。
    //循环：实际需要考虑while循环中的6中情况，而其中三种情况与另外三种情况是对称的，这取决于z.parent是z.parent.parent的左孩子还是右孩子
    //z.parent是z.parent.parent的左孩子
    //     情况1：z的叔节点是红色的。（此时z.parent一定是红色的，因为对于z.parent.parent到其子代叶节点的简单路径上，包含的黑节点的数目相同）
    //        相应操作：情况1违反了性质4，因为z和z.parent都是红色的。在这种情况下，无论z是左孩子还是右孩子都同样处理。
    //                这种情况在z.parent和z的叔节点都是红色的情况下发生，因为z.parent.parent是黑色的（已证明），
    //                所以将z.parent和z的叔节点都着为黑色，以此解决z.parent和z都是红色节点的问题。
    //                接着将z.parent.parent着为红色，以保持性质5。将z上移到z.parent.parent来重复while循环，z在树中上移了两层。
    //        证明：情况1执行之后，在下一次循环的开头，三个循环不变式依然成立？
    //             a.因为在情况1执行中，将z.parent.parent着为红色，并使z'指向z.parent.parent，所以在下次循环的开始z仍为红色。
    //             b.在这次迭代中z'.parent是z.parent.parent.parent，在上一次迭代中并没有修改z'.parent的颜色，所以其颜色并没有改变。
    //               如果它在上次迭代时是根节点，是黑色的，在这次迭代的开头，它仍然是根节点且为黑色的。
    //             c.在上次迭代时，将z.parent.parent着为红色，保持了性质5。而且在上次迭代中也没有破坏性质1、性质3。
    //               所以在本次while循环中仍然只有性质2、性质4可能被破坏。
    //               如果z.parent.parent在下一次迭代开始时是根节点。而在这次迭代中情况1修正了唯一被破坏的性质4（将z.parent.parent设置为红色、z.parent和z的叔节点设置为黑节点）。
    //               由于z.parent.parent是根节点且是红色的，所以，性质2成了唯一被破坏的性质，循环不变式c成立。
    //               如果z.parent.parent在下一次迭代开始时不是根节点。则情况1不会导致性质2的破坏（根节点仍然是黑色的）。
    //               情况1修正了在这次迭代的开始唯一违反的性质4（将z.parent.parent设置为红色、z.parent和z的叔节点设置为黑节点）。
    //               然后，它把z.parent.parent设置为红色而z.parent.parent.parent不变。如果z.parent.parent.parent是黑色的，则没有违反性质4；
    //               如果z.parent.parent.parent是红色的，则违反了性质4，循环不变式c成立。
    //     情况2：z的叔节点是黑色的，并且z是z.parent的右孩子
    //        相应操作：1.将z上移到父节点z.parent(z')
    //                2.对z'进行一次左旋转
    //        为了保持性质5，情况2中进行了一次左旋转，将情况2转变为情况3。
    //     情况3：z的叔节点是黑色的，并且z是z.parent的左孩子
    //        相应操作：1.将z.parent设置为黑色
    //                2.将z.parent.parent设置为红色
    //                3.对z.parent.parent执行一次右旋转
    //        情况3更改了某些节点的颜色，以及一个同样为了保持性质5而进行的右旋转。
    //     在情况2和情况3中，z的叔节点是黑色的。通过z是z.parent的右孩子还是左孩子来区别这两种情况。在情况2中，节点z是它的父节点的右孩子，
    //     可以立即使用一个左旋转来将此情况转变为情况3，此时节点z为左孩子。因为z和z.parent都是红色的，所以对节点的黑高和性质5都没有影响。
    //     无论是直接进入情况2，还是通过情况3进入情况2，z的叔节点总是黑色的（因为否则就要执行情况1）。此外，节点z.parent.parent存在。
    //     因为在情况2中，对z进行了一次左旋转，旋转之前将z上移一层，而旋转操作本身又将z下移一层，所以z.parent.parent的身份不变。
    //        证明：在情况2、情况3中依然保持了循环不变式。
    //            a.在情况2中，让z指向红色的z.parent。在情况2和情况3中z和z.parent的颜色都不再改变。
    //            b.在情况3，把z.parent着为黑色，使得如果z.parent在下一次迭代开始时是根节点，则它是黑色的。
    //            c.如同情况1，性质1、性质3、性质5在情况2和情况3中得以保持。由于节点z在情况2和情况3中都不是根节点，所以性质2没有被破坏。
    //              情况2和情况3不会引起性质2的违反，因为唯一着为红色的节点在情况3中通过旋转成为一个黑色节点的子节点。
    //              情况2和情况3修正了对性质4的违反，也不会引起其他红黑性质的改变。
    // z.parent是z.parent.parent的右孩子
    //     情况4：z的叔节点是红色的
    //     情况5：z的叔节点是黑色的，并且z是z.parent的右孩子
    //     情况6：z的叔节点是黑色的，并且z是z.parent的左孩子
    private void rb_tree_insert_fixup(RedBlackTree T,Node z){
        while(z.parent.getColor()=="red"){
            if(z.parent==z.parent.parent.left){
                Node y = z.parent.parent.right;
                if(y.getColor()=="red"){
                    z.parent.color = "black";
                    y.color = "black";
                    z.parent.parent.color = "red";
                    z = z.parent.parent;
                }else if(z==z.parent.right){
                    z = z.parent;
                    tr_tree_left_rotate(T,z);
                }else {
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    rb_tree_right_rotate(T, z.parent.parent);
                }
            }else{
                Node y = z.parent.parent.left;
                if(y.getColor()=="red"){
                    z.parent.color = "black";
                    y.color = "black";
                    z.parent.parent.color = "red";
                    z = z.parent.parent;
                }else if(z==z.parent.left){
                    z = z.parent;
                    rb_tree_right_rotate(T,z);
                }else {
                    z.parent.color = "black";
                    z.parent.parent.color = "red";
                    tr_tree_left_rotate(T, z.parent.parent);
                }
            }
        }
        T.root.color = "black";
    }
    /*
    * 删除
    * 与n个节点的红黑树上的其他操作一样，删除一个节点要花费O(lgn)时间。从一棵红黑树中删除节点的过程是基于tree_delete()过程的。
    * 首先需要特别设计一个供rb_tree_delete()调用的子过程rb_tree_delete_transplant()，并将其应用到红黑树上。
    * */
    //rb_tree_delete()操作与tree_delete()类似，只是多了一些代码。多出的代码记录节点y(z的后继节点)的踪迹，y有可能导致红黑性质的破坏。
    //  ①当z的子节点少于2个时，将z从树中删除，并让y成为z。
    //  ②当z有两个子节点时，y是z的后继，并且将y移至树中z的位置上。在结点被移除或者在树中移动之前，必须记住y的颜色，并且记录节点x(y的右孩子)的踪迹，
    //   将x移至树中y的原来位置，因为节点x也可能引起红黑性质的破坏，删除结点z之后，rb_tree_delete()调用辅助过程rb_tree_delete_fixup()，
    //   该过程通过改变颜色和执行旋转来恢复红黑性质。
    //始终维持节点y为从树中删除的节点或者移至树内的节点。
    //①当z的子节点少于2个时，将y指向z，将z从树中删除；
    //②当z有2个子节点时，将y指向z的后继，y将移至树中z的位置。
    //由于节点y的颜色可能改变，变量y_original_color存储了发生改变前的颜色。如果y_original_color最后的颜色是黑色的，那么删除或者移动y会引起红黑性质的破坏；
    //如果y_original_color最后的颜色是红色的，那么删除或移动y不会引起红黑性质的破坏。
    //  原因：a.树中的黑高没有变化
    //       b.不存在两个相邻的红结点。因为y在树中占据了z的位置，再考虑到z的颜色，树中y的新位置不可能有两个相邻的红结点。
    //         另外，如果y不是z的右孩子，则y的原右孩子x代替y。如果y是红色的，则x一定是黑色的，因此用x替换y不可能使两个红结点相邻
    //       c.如果y是红色的，就不可能是根节点，所以根节点仍旧是黑色的。
    //保存节点x的踪迹，使它移至节点y的原始位置上。因为节点x移动到节点y的原始位置，属性x.parent总是被设置指向树中y父节点的原始位置，甚至x是哨兵也是这样。
    //如果结点y是黑色的，则会产生3个问题：
    //   a.如果y是原来的根节点，而y的一个红孩子成为新的根节点，违反了性质2.
    //   b.如果x和x.parent(y.parent)是红色的，则违反了性质4
    //   c.在树中移动y将导致先前包含y的任何简单路径上黑节点个数少1，因此y的任何祖先都不满足性质5.
    //
    //解决y是黑节点问题的方法：
    //   解决这一问题的方法是将现在占有y原来位置的节点x视为还有一重额外的黑色。也就是说，将任意包含节点x的简单路径上黑节点的个数加1.
    //   当将黑节点删除或移动时，将其黑色“下推”给节点x。节点x额外的黑色是针对x节点的，而不是反映在它的color属性上的。
    public void rb_tree_delete(RedBlackTree T,Node z){
        Node y = z;
        String y_original_color = y.getColor();
        Node x = T.getNil();
        if(z.left==T.getNil()){
            x = z.right;
            rb_tree_delete_transplant(T,z,z.right);
        }else if(z.right==T.getNil()){
            x = z.left;
            rb_tree_delete_transplant(T,z,z.left);
        }else{
            y = rb_tree_minimum(z.right);
            y_original_color = y.getColor();
            x = y.right;
            if(y.parent==z){
                x.parent = y;
            }else{
                rb_tree_delete_transplant(T,y,y.right);
                y.right = z.right;
                y.right.parent = z;
            }
            rb_tree_delete_transplant(T,z,y);
            y.left = z.left;
            y.left.parent = y;
            y.color = z.color;
        }
        if(y_original_color=="black"){
            rb_tree_delete_fixup(T,x);
        }
    }
    //过程rb_tree_delete_fixup()中while循环的目标是将额外的黑色沿树上移，直到：
    //  1.x指向红黑节点，之后将x着为（单个）黑色
    //  2.x指向根节点，此时可以简单地“移除”额外的黑色
    //  3.执行适当的旋转和重新着色，退出循环。
    //在while循环中，x总是指向一个具有双重黑色的非根节点，根据x是x.parent的左孩子还是右孩子进行分类。
    //保持指针w指向x的兄弟，由于x是具有双重黑色的，故w不可能是T.nil，因为否则，
    //从x.parent至w到叶节点的简单路径上的黑节点个数就会小于从x.parent到x的简单路径上黑节点数。
    //代码中包含四种情况：每种情况中的关键思想：从子树的根（包括根）到每棵子树之间的黑节点个数并不被变换改变。
    //因此，性质5在变换之前成立，那么变换之后也仍然成立
    // 情况1.x的兄弟节点w是红色的
    //      通过交换x.parent和w的颜色，以及对x.parent执行一次左旋，可将情况1转化为情况2、3或4.这些情况是由w的子节点的颜色来区分的。
    // 情况2.x的兄弟节点w是黑色的，且w的两个子节点都是黑色的。
    //      因为w也是黑色的，所以从x和w上去掉一重黑色，使得x只有一重黑色而w为红色。为了补偿从x和w中去掉的一重黑色，
    //      在原来是红色或黑色的x.parent上新增一重额外的黑色。通过将x.parent作为新节点x来重复while循环。
    //      注意到如果通过情况1进入到情况2，则新节点x是红黑色的，因为原来的x.parent是红色的。
    //      因此，新节点x的color属性值为red，并且在测试循环条件后循环终止。然后，将新节点x着为（单一）黑色。
    // 情况3.x的兄弟节点w是黑色的，w的左孩子是红色的，w的右孩子是黑色的
    //      可以交换w和其左孩子w.left的颜色，然后对w进行右旋转而不违反红黑树的任何性质。
    //      现在x的新兄弟节点w是一个有红色右孩子的黑色节点，这样就将情况3转换成了情况4
    // 情况4.x的兄弟节点w是黑色的，且w的右孩子是红色的
    //      通过进行某些颜色修改并对x.parent做一次左旋转，可以去掉x的额外黑色，从而使它变为单重黑色，
    //      而且不破坏红黑树的任何性质。将x设置为根后，当while循环测试其循环条件时，循环终止。
    private void rb_tree_delete_fixup(RedBlackTree T,Node x){
        while(x!=T.getNil()&&x.getColor()=="black"){
            if(x==x.parent.left){
                Node w = x.parent.right;
                if(w.getColor()=="red"){
                    w.color = "black";
                    x.parent.color = "red";
                    tr_tree_left_rotate(T,x.parent);
                    w = x.parent.right;
                }
                if(w.left.color=="black"&&w.right.color=="black"){
                    w.color = "red";
                    x = x.parent;
                }else if(w.right.color=="black"){
                    w.left.color = "black";
                    w.color = "red";
                    rb_tree_right_rotate(T,w);
                    w = x.parent.right;
                }
                w.color = x.parent.color;
                x.parent.color = "black";
                w.right.color = "black";
                tr_tree_left_rotate(T,x.parent);
                x = T.root;
            }else{
                Node w = x.parent.left;
                if(w.color=="red"){
                    w.color = "black";
                    x.parent.color = "red";
                    rb_tree_right_rotate(T,x.parent);
                    w = x.parent.left;
                }
                if(w.right.color=="black"&&w.left.color=="black"){
                    w.color = "red";
                    x = x.parent;
                }else if(w.right.color=="black"){
                    w.right.color = "black";
                    w.color = "red";
                    tr_tree_left_rotate(T,w);
                    w = x.parent.left;
                }
                w.color = x.parent.color;
                x.parent.color = "black";
                w.left.color = "black";
                rb_tree_right_rotate(T,x.parent);
                x = T.root;
            }
        }
        x.color = "black";
    }
    //rb_tree_transplant()用一棵以v为根的子树来替换一棵以u为根的子树，
    //节点u的双亲就变为节点v的双亲，并且最后v成为u的双亲的相应孩子
    private void rb_tree_delete_transplant(RedBlackTree T,Node u,Node v){
        if(u.parent==T.getNil()){
            T.root = v;
        }else if(u==u.parent.left){
            u.parent.left = v;
        }else{
            u.parent.right = v;
        }
        v.parent = u.parent;
    }

    /*
    * 遍历
    * */
    public void rb_tree_pre_order(RedBlackTree T,Node localRoot){
        if(localRoot!=T.getNil()){
            System.out.print(localRoot + " ");
            rb_tree_pre_order(T, localRoot.left);
            rb_tree_pre_order(T, localRoot.right);
        }
    }
    public void rb_tree_in_order(RedBlackTree T,Node localRoot){
        if(localRoot!=T.getNil()){
            rb_tree_in_order(T, localRoot.left);
            System.out.print(localRoot + " ");
            rb_tree_in_order(T, localRoot.right);
        }
    }
    public void rb_tree_post_order(RedBlackTree T,Node localRoot){
        if (localRoot != T.getNil()){
            rb_tree_post_order(T, localRoot.left);
            rb_tree_post_order(T, localRoot.right);
            System.out.print(localRoot + " ");
        }
    }

    /*
    * 构建红黑树
    * */
    public void rb_build_redblacktree(RedBlackTree T,int[] nodes){
        for(int i=0;i<nodes.length;i++){
            rb_tree_insert(T,nodes[i]);
        }
    }

    /*
    * 根据给定的key,获取节点
    * */
    public Node rb_tree_get_node(RedBlackTree T,int key){
        Node temp = T.getRoot();
        if(temp==T.getNil()){
            return T.getNil();
        }
        while(temp!=T.getNil()){
            if(temp.getKey()>key){
                temp = temp.left;
            }else if(temp.getKey()<key){
                temp = temp.right;
            }else{
                return temp;
            }
        }
        return temp;
    }

    public static void main(String[] args){
        RedBlackTree rbt = new RedBlackTree();
        int[] nodes = {1,2,3,4,5,6,7,8,9,10};
        rbt.rb_build_redblacktree(rbt,nodes);
        rbt.rb_tree_pre_order(rbt,rbt.getRoot());
        System.out.println();
        rbt.rb_tree_in_order(rbt, rbt.getRoot());
        System.out.println();
        rbt.rb_tree_post_order(rbt, rbt.getRoot());
    }
}
