package esi.g55019.atl.SameGame.ModelJavaFx;

import esi.g55019.atl.SameGame.Model.Bille;
import esi.g55019.atl.SameGame.Model.Board;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardJavaFx {
    private Board board;
    private Button[][] buttonBoard;
    private GridPane boardPane;
    private Bille[][] plateauBille;


    public BoardJavaFx(int ligne, int colonne, int nbColor) {
        board = new Board(ligne,colonne, nbColor);
        plateauBille = board.getPlateau();
        boardPane = new GridPane();
        setUpBoardPane(ligne, colonne);
    }

    private void setUpBoardPane(int ligne, int colonne){
        buttonBoard = new Button[ligne][colonne];

        for (int i = 0; i < plateauBille.length; i++) {
            for (int j = 0; j < plateauBille[0].length; j++) {
                Button button = new Button("  ");
                button.setBackground(new Background(new BackgroundFill(Color.web(plateauBille[i][j].getColor().getColorForJavaFx()),
                        CornerRadii.EMPTY, Insets.EMPTY)));
                button.setBorder(new Border(new BorderStroke(Color.rgb(104, 104, 105, 0.15),
                        BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                buttonBoard[i][j] = button;
                boardPane.add(buttonBoard[i][j], i, j);
            }
        }
    }

    //TODO ajouter onClick -> devient noir + ses voisins + isDisable / onHover + changeCouleur voisin

    public GridPane getBoardPane() {
        return boardPane;
    }
}
