package td02;

public class PinnablePoint extends PointTd02 {
    private boolean pinned;

    public PinnablePoint(double x, double y) {
        super(x, y);
        this.pinned = false;
    }

    public void pin(){
        pinned = true;
    }

    @Override
    public PointTd02 move(double dx, double dy){
        super.move(dx, dy);
        if(!pinned){
            super.move(dx, dy);
        }
        else{
            throw new IllegalStateException("Le point est pinned et ne peut être déplacer !!!!");
        }
        return this;
    }

    @Override
    public String toString(){
       return super.toString() + " - "+ (pinned? "pinned": "not pinned");
    }

    public static void main(String[] args) {
        PointTd02 p = new PinnablePoint(0,0);
        System.out.println(p);
        p.move(1,1);
        System.out.println(p);
        PinnablePoint pp = (PinnablePoint) p;
        pp.pin();
        p.move(1,1);
        System.out.println(p);
    }
}

