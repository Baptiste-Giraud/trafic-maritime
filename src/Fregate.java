public class Fregate extends Bateau {

    public Fregate(String nom, double x, double y, double vitesse, double direction) {
        super(nom, x, y, vitesse, direction);
    }
    public void move() {
        double vitesseFregate = 30;
        x += vitesseFregate * Math.cos(Math.toRadians(direction));
        y += vitesseFregate * Math.sin(Math.toRadians(direction));

        if (x <= SCENE_MIN || x >= SCENE_WIDTH) {
            direction = 180 - direction;
        }

        if (y <= SCENE_MIN || y >= SCENE_HEIGHT) {
            direction = 360 - direction;
        }
    }

    public void attackAir(){
        System.out.println(this.nom+" : Tir dans les airs");
    }

    public void attackAmph(){
        System.out.println(this.nom+" : Tir sous l'eau");
    }
}
