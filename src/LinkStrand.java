//Ameya Rao
//Helen Xu
public class LinkStrand implements IDnaStrand{
    private class Node {
        String info;
        Node next;
        public Node(String s){
            info = s;
            next = null;
        }
    }

    private Node myFirst, myLast;
    private long mySize;
    private int myAppends;
    private int myIndex, myLocalIndex;
    private Node myCurrent;

    public LinkStrand(){
        this("");
    }

    public LinkStrand(String s){
        initialize(s);
    }

    @Override
    public long size() {
        return mySize;
    }

    @Override
    public void initialize(String source) {
        myFirst = myLast = myCurrent = new Node(source);
        mySize = source.length();
        myAppends = myIndex = myLocalIndex = 0;
    }

    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    @Override
    public IDnaStrand reverse() {
        Node temp = myFirst;
        Node node = null;
        while (temp != null){
            String reversed = new StringBuilder(temp.info).reverse().toString();
            Node newNode = new Node(reversed);
            newNode.next = node;
            node = newNode;
            temp = temp.next;
        }
        LinkStrand ret = new LinkStrand();
        while (node != null){
            ret.append(node.info);
            node = node.next;
        }
        return ret;
    }

    @Override
    public int getAppendCount() {
        return myAppends;
    }

    @Override
    public char charAt(int index) {
        if (index < 0 || index >= mySize)
            throw new IndexOutOfBoundsException();
        if (index < myIndex) {
            myCurrent = myFirst;
            myIndex = myLocalIndex = 0;
        }
        //while (index-myIndex+1>myCurrent.info.length()) {
        while (index!=myIndex) {
//            myIndex += myCurrent.info.length();
//            //myIndex += myCurrent.info.length()-myLocalIndex-1;
//            myCurrent = myCurrent.next;
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex>=myCurrent.info.length()) {
                myCurrent = myCurrent.next;
                myLocalIndex = 0;
            }
        }
//        myLocalIndex = index - myIndex;
//        myIndex = index;
        return myCurrent.info.charAt(myLocalIndex);
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        Node tmp = myFirst;
        while (tmp!=null) {
            sb.append(tmp.info);
            tmp = tmp.next;
        }
        return sb.toString();
    }
}
