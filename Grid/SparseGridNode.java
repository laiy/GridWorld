public class SparseGridNode
{
    private Object occupant;
    private int col;
    private SparseGridNode next;

    public SparseGridNode(Object o, int c, SparseGridNode s) {
    	occupant = o;
    	col = c;
    	next = s;
    }

    public Object getOccupant() {
    	return occupant;
    }

    public int getCol() {
    	return col;
    }

    public SparseGridNode getNext() {
    	return next;
    }

    public void setOccupant(Object o) {
    	occupant = o;
    }

    public void setCol(int c) {
    	col = c;
    }

    public void setNext(SparseGridNode s) {
    	next = s;
    }
}