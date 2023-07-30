public class Bateau {
    public String nom;
    public double x;
    public double y;
    public double vitesse;
    public double direction;

    public static final double SCENE_WIDTH = 800;
    public static final double SCENE_HEIGHT = 600;
    public static final double SCENE_MIN = 5;


    public Bateau(String nom, double x, double y, double vitesse, double direction){
        this.nom = nom;
        this.x = x;
        this.y = y;
        this.vitesse = vitesse;
        this.direction = direction;
    }

    public void move() {
        x += vitesse * Math.cos(Math.toRadians(direction));
        y += vitesse * Math.sin(Math.toRadians(direction));

        if (x <= SCENE_MIN || x >= SCENE_WIDTH) {
            direction = 180 - direction;
        }

        if (y <= SCENE_MIN || y >= SCENE_HEIGHT) {
            direction = 360 - direction;
        }
    }

    public void newDirection(double i) {
        direction = i;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public String getNom() {
        return nom;
    }

    public double getVitesse() {
        return vitesse;
    }

    public double getDirection() {
        return direction;
    }
}
