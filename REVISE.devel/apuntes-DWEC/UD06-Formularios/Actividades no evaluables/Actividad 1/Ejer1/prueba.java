public class prueba{



    public static void pro(punto m){
        m.setX(200000);
    }

    public static void main(String args[]){

        punto j=new punto();
        pro(j);
        System.out.println(j.getX());


    }
}

class punto{
    private int x;
    private int y;
    public punto(){
        this.x=3;
        this.y=2;
    }
    public void setX(int a){
        this.x=a;
    }
    public int getX(){
        return this.x;
    }
}