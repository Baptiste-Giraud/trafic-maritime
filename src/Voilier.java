public class Voilier extends Bateau {
    private double vitesseVoilier = 3;
    public Voilier(String nom, double x, double y, double vitesse, double direction) {
        super(nom, x, y, vitesse, direction);
    }
    public void move() {
        x += this.vitesseVoilier * Math.cos(Math.toRadians(direction));
        y += vitesseVoilier * Math.sin(Math.toRadians(direction));

        if (x <= SCENE_MIN || x >= SCENE_WIDTH) {
            direction = 180 - direction;
        }

        if (y <= SCENE_MIN || y >= SCENE_HEIGHT) {
            direction = 360 - direction;
        }
    }

    public void speed(){
       this.vitesseVoilier = 6;
    }
}