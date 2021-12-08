package esi.g55019.atl.SameGame.ViewJavaFx;

import esi.g55019.atl.SameGame.ControllerJavaFx.ControllerJavaFx;
import esi.g55019.atl.SameGame.Model.Bille;
import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Position;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;

public class BoardFx {
    private Button[][] buttonBoard;
    private GridPane boardPane;
    private Board board;
    private ControllerJavaFx controllerJavaFx;

    public BoardFx(Board board, ControllerJavaFx controllerJavaFx) {
        this.board = board;
        this.controllerJavaFx = controllerJavaFx;
        this.boardPane = new GridPane();
        this.buttonBoard = new Button[board.getLigne()][board.getColonne()];
        updateBoard();
    }

    public void updateBoard() {
        buttonBoard = new Button[board.getLigne()][board.getColonne()];
        Bille[][] plateauDeBille = board.getPlateau();
        for (int i = 0; i < plateauDeBille.length; i++) {
            for (int j = 0; j < plateauDeBille[0].length; j++) {
                Button button = new Button("  ");
                button.setId(i + " " + j);
                if (plateauDeBille[i][j] != null) {
                    button.setBackground(new Background(new BackgroundFill(
                            Color.web(plateauDeBille[i][j].getColor().getColorForJavaFx()),
                            CornerRadii.EMPTY, Insets.EMPTY)));

                    button.setBorder(new Border(new BorderStroke(Color.rgb(104, 104, 105, 0.15),
                            BorderStrokeStyle.SOLID, CornerRadii.EMPTY, new BorderWidths(3))));

                    button.setOnAction(event -> {
                        String[] pos = button.getId().split(" ");

                        if (board.getVoisinASupprimer(
                                new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1]))).size() != 1) {
                            controllerJavaFx.clickOnBoardButton(new Position(Integer.parseInt(pos[0]), Integer.parseInt(pos[1])));
                        }
                    });
                } else {
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

    public GridPane getBoardPane() {
        return boardPane;
    }

    public void setBoard(Board board){
        this.board = board;
    }
}
