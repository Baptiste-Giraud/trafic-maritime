import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.*;

public class SimulateurGraphique extends Application {
    private List<Bateau> listeBateaux;
    private Map<Bateau, Circle> bateauxCircles = new HashMap<>();
    private Map<Bateau, Text> bateauxTexts = new HashMap<>();

    @Override
    public void init() {
        listeBateaux = new ArrayList<>();
        listeBateaux.add(new Bateau("Bateau 1", 100, 100, 10, 45));
        listeBateaux.add(new Voilier("Voilier", 10, 20, 2, 15));
        listeBateaux.add(new Fregate("Frégate 1", 200, 300, 15, 90));
    }

    @Override
    public void start(Stage primaryStage) {
        Pane rootPane = new Pane();
        rootPane.setStyle("-fx-background-color: #ADD8E6;");
        afficherBateaux(rootPane);
        Scene scene = new Scene(rootPane, 800, 600);
        primaryStage.setTitle("Simulateur de trafic maritime");
        primaryStage.setScene(scene);
        primaryStage.show();

        Timeline timeline = new Timeline(new KeyFrame(Duration.seconds(0.5), event -> {
            Random random = new Random();
            for (Bateau bateau : listeBateaux) {
                bateau.move();
                if (random.nextInt(100) < 10) {
                    bateau.newDirection(random.nextDouble() * 360);
                }
                if (bateau instanceof Voilier && random.nextInt(100) < 2) {
                    Voilier voilier = (Voilier) bateau;
                    voilier.speed();
                }
                Circle cercleBateau = bateauxCircles.get(bateau);
                cercleBateau.setCenterX(bateau.getX());
                cercleBateau.setCenterY(bateau.getY());

                Text texteNomBateau = bateauxTexts.get(bateau);
                TranslateTransition transition = new TranslateTransition(Duration.seconds(0.5), texteNomBateau);
                transition.setToX(bateau.getX() - 15);
                transition.setToY(bateau.getY() + 25);
                transition.play();
            }
        }));

        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void afficherBateaux(Pane pane) {
        for (Bateau bateau : listeBateaux) {
            Circle cercleBateau = new Circle(bateau.getX(), bateau.getY(), 10);
            cercleBateau.setFill(getColorForBateau(bateau));
            pane.getChildren().add(cercleBateau);
            bateauxCircles.put(bateau, cercleBateau);

            Text texteNomBateau = new Text(bateau.getNom());
            pane.getChildren().add(texteNomBateau);
            bateauxTexts.put(bateau, texteNomBateau);

            texteNomBateau.setTranslateX(bateau.getX() - 15);
            texteNomBateau.setTranslateY(bateau.getY() + 25);
        }
    }

    private Color getColorForBateau(Bateau bateau) {
        if (bateau instanceof Voilier) {
            return Color.BLUE;
        } else if (bateau instanceof Fregate) {
            return Color.RED;
        } else {
            return Color.GREEN;
        }
    }
}
