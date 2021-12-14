package esi.g55019.atl.SameGame;

import esi.g55019.atl.SameGame.Model.Bille;
import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class BoardTest {
    private Board board;

    @BeforeEach
    public void setBoard(){
        board = new Board();
        System.out.println(board.toString());
    }

    @Test
    public void board_not_enough_row(){
        assertThrows(IllegalArgumentException.class, () -> new Board(4,5,3));
    }

    @Test
    public void board_not_enough_column(){
        assertThrows(IllegalArgumentException.class, () -> new Board(5,4,3));
    }

    @Test
    public void board_not_enough_color(){
        assertThrows(IllegalArgumentException.class, () -> new Board(5,5,2));
    }

    @Test
    public void board_too_many_row(){
        assertThrows(IllegalArgumentException.class, () -> new Board(50,5,3));
    }
    @Test
    public void board_too_many_column(){
        assertThrows(IllegalArgumentException.class, () -> new Board(5,50,3));
    }
    @Test
    public void board_too_many_colors(){
        assertThrows(IllegalArgumentException.class, () -> new Board(5,5,6));
    }

    @Test
    public void initialize_board(){
        Board test = new Board(5,5,5);
        Bille[][] testPlateau = test.getPlateau();
        testPlateau[0][0].getColor();
    }

    @Test
    public void supprimer_color_setUp_true(){
        assertTrue(board.supprimerColorSetUp(new Position(0,0)));
    }

    @Test
    public void supprimer_color_setUp_when_OOB(){
        assertThrows(IndexOutOfBoundsException.class, () -> board.supprimerColorSetUp(new Position(-1,-1)));
    }

    @Test
    public void supprimer_color_setUp_false_when_null(){
        board.supprimerColorSetUp(new Position(0,0));
        assertFalse(board.supprimerColorSetUp(new Position(0,0)));
    }

    @Test
    public void get_voisin_a_supprimer_when_inside_and_not_alone(){
        assertTrue(board.getVoisinASupprimer(new Position(0,0)).size() == 3);
    }
    @Test
    public void get_voisin_a_supprimer_when_outisde(){
        assertThrows(IndexOutOfBoundsException.class, () -> board.getVoisinASupprimer(new Position(-10,-10)));
    }
    @Test
    public void get_voisin_a_supprimer_when_inside_and_alone(){
        assertTrue(board.getVoisinASupprimer(new Position(4,0)).size() == 1);
    }

    @Test
    public void isInside_true(){
        assertTrue(this.board.isInside(new Position(0,0)));
    }

    @Test
    public void isInside_false(){
        assertFalse(this.board.isInside(new Position(6,6)));
    }

    @Test
    public void faire_tomber_bille(){
        this.board.supprimerColorSetUp(new Position(1,0));
        this.board.faireTomberBille();
        Board test = new Board();
        test.supprimerColorSetUp(new Position(1,0));
        test.faireTomberBille();
        assertTrue(this.board.equalsForTest(test.getPlateau()));
    }

    @Test
    public void concatener_check(){
        this.board.supprimerColorSetUp(new Position(0,3));
        this.board.faireTomberBille();
        this.board.concatener();

        Board test = new Board();
        test.supprimerColorSetUp(new Position(0,3));
        test.faireTomberBille();
        test.concatener();

        assertTrue(this.board.equalsForTest(test.getPlateau()));
    }

    @Test
    public void is_not_finish(){
        assertFalse(this.board.isFinish());
    }

    @Test
    public void isfinish(){
        this.board.supprimerColorSetUp(new Position(0,0));
        this.board.supprimerColorSetUp(new Position(1,0));
        this.board.supprimerColorSetUp(new Position(2,0));
        this.board.supprimerColorSetUp(new Position(0,3));
        this.board.faireTomberBille();
        this.board.concatener();
        assertTrue(this.board.isFinish());
    }

    @Test
    public void isfinish_after_give_up(){
        this.board.giveUp();
        assertTrue(this.board.isFinish());
    }

    @Test
    public void is_finish_but_not_win(){
        this.board.supprimerColorSetUp(new Position(0,0));
        this.board.supprimerColorSetUp(new Position(1,0));
        this.board.supprimerColorSetUp(new Position(2,0));
        this.board.supprimerColorSetUp(new Position(0,3));
        this.board.faireTomberBille();
        this.board.concatener();
        assertFalse(this.board.isWin());
    }

    @Test
    public void is_win(){
        this.board.giveUp();
        assertTrue(this.board.isWin());
    }

    @Test
    public void is_win_when_not_finish(){
        assertFalse(this.board.isWin());
    }

    @Test
    public void bille_remaing_start(){
        assertTrue(this.board.nbOfBillesRemaining() == 25);
    }

    @Test
    public void bille_remaining_win(){
        this.board.giveUp();
        assertTrue(this.board.nbOfBillesRemaining()== 0);
    }

    @Test
    public void bille_remaining(){
        this.board.supprimerColorSetUp(new Position(0,0));
        this.board.faireTomberBille();
        this.board.concatener();

        assertTrue(this.board.nbOfBillesRemaining() ==22);
    }

}
