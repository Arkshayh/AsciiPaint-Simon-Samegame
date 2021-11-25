package esi.g55019.atl.SameGame.ModelJavaFx;

import esi.g55019.atl.SameGame.Model.Bille;
import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Position;
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
    private int ligne;
    private int colonne;



    public BoardJavaFx(int ligne, int colonne, int nbColor) {
        this.ligne = ligne;
        this.colonne = colonne;
        board = new Board(ligne,colonne, nbColor);
        boardPane = new GridPane();
        setUpBoardPane();
    }

    private void setUpBoardPane(){
        buttonBoard = new Button[ligne][colonne];
        Bille[][] plateauDeBille = board.getPlateau();
        for (int i = 0; i < plateauDeBille.length; i++) {
            for (int j = 0; j < plateauDeBille[0].length; j++) {
                Button button = new Button("  ");
                button.setId(i + " " + j);
                if(plateauDeBille[i][j] != null){
                    button.setBackground(new Background(new BackgroundFill(Color.web(plateauDeBille[i][j].getColor().getColorForJavaFx()),
                            CornerRadii.EMPTY, Insets.EMPTY)));
                    button.setBorder(new Border(new BorderStroke(Color.rgb(104, 104, 105, 0.15),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                    button.setOnAction(new EventHandler<ActionEvent>() {
                        @Override
                        public void handle(ActionEvent event) {
                            String[] pos =  button.getId().split(" ");
                            onClickModify(new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
                        }
                    });
                }
                else{
                    button.setBackground(new Background(new BackgroundFill(Color.web("#000000"
                    ), CornerRadii.EMPTY, Insets.EMPTY)));
                    button.setBorder(new Border(new BorderStroke(Color.rgb(0, 0, 0, 0.15),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));
                }
                buttonBoard[i][j] = button;
                boardPane.add(buttonBoard[i][j], j, i);
            }
        }
    }

    private void onClickModify(Position pos){
        if(board.supprimerColorSetUp(pos)){
            board.faireTomberBille();
            board.concatener();
            setUpBoardPane();
        }
    }



    //TODO ajouter onClick -> devient noir + ses voisins + isDisable / onHover + changeCouleur voisin

    public GridPane getBoardPane() {
        return boardPane;
    }
}
