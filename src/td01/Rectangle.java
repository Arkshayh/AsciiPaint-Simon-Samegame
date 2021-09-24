package td01;

public class Rectangle {
    private PointTD01 bl; //bottom left corner
    private PointTD01 ur; //upper right corner

    public Rectangle(PointTD01 bottomLeft, PointTD01 upperRight){
        if(bottomLeft.getX() >= upperRight.getX() || bottomLeft.getY() >= upperRight.getY()){
            throw new IllegalArgumentException("bottomLeft must be below and on the left of upperRight" +", received (bottomLeft - upperRight): " +bottomLeft+"-"+upperRight);
        }
        this.bl = new PointTD01(bottomLeft.getX(), bottomLeft.getY());
        this.ur = new PointTD01(upperRight.getX(), upperRight.getY());
    }

    public void move(double dx, double dy){
        bl.move(dx, dy);
        ur.move(dx,dy);
    }

    public double getPerimenter(){
        return 2*((ur.getX()-bl.getX())+(ur.getY()-bl.getY()));
    }

    @Override
    public String toString(){
        return "Rectangle : ["+bl+", "+ur+"]";
    }

}

class TestRectangle{
    public static void main(String[] args) {
        PointTD01 bl = new PointTD01(0,0);
        PointTD01 ur = new PointTD01(5,3);
        Rectangle r = new Rectangle(bl,ur);
        bl.move(10,10);
        System.out.println(r);
        System.out.println("Périmètre : " + r.getPerimenter());
        r.move(2,5);
        System.out.println(r);
        System.out.println("Périmètre :" + r.getPerimenter());
    }
}
