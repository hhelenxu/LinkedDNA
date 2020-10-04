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

    /**
     * Returns the total length of the LinkStrand object
     * @return number of characters total
     */
    @Override
    public long size() {
        return mySize;
    }

    /**
     * Helper method to the constructor.
     * Initializes instance variables
     * @param original DNA string
     */
    @Override
    public void initialize(String source) {
        myFirst = myLast = myCurrent = new Node(source);
        mySize = source.length();
        myAppends = myIndex = myLocalIndex = 0;
    }

    /**
     * Returns an IDnaStrand object containing the specificed string
     * @param source is data from which object constructed
     * @return IDnaStrand object
     */
    @Override
    public IDnaStrand getInstance(String source) {
        return new LinkStrand(source);
    }

    /**
     * Appends a new Node containing the given dna string to the end
     * @param dna is the string appended to this strand
     * @return IDnaStrand object
     */
    @Override
    public IDnaStrand append(String dna) {
        myLast.next = new Node(dna);
        myLast = myLast.next;
        mySize += dna.length();
        myAppends++;
        return this;
    }

    /**
     * Reverses the entire DNA string
     * @return reversed IDnaStrand object
     */
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

    /**
     * Returns the number of appends
     * @return number of appends
     */
    @Override
    public int getAppendCount() {
        return myAppends;
    }

    /**
     * Finds character of DNA at specified location
     * @param index specifies which character will be returned
     * @return character at the given index
     */
    @Override
    public char charAt(int index) {
        if (index < 0 || index >= mySize)
            throw new IndexOutOfBoundsException();
        if (index < myIndex) {
            myCurrent = myFirst;
            myIndex = myLocalIndex = 0;
        }
        while (index!=myIndex) {
            myIndex++;
            myLocalIndex++;
            if (myLocalIndex>=myCurrent.info.length()) {
                myCurrent = myCurrent.next;
                myLocalIndex = 0;
            }
        }
//        wrong but don't see why
//        while (index-myIndex+1>myCurrent.info.length()) {
//            myIndex += myCurrent.info.length();
//            myCurrent = myCurrent.next;
//        }
//        myLocalIndex = index - myIndex;
//        myIndex = index;
        return myCurrent.info.charAt(myLocalIndex);
    }

    /**
     * Converting LinkStrand object to its string representation
     * @return string form of LinkStrand object
     */
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
