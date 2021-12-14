package esi.g55019.atl.SameGame;

import esi.g55019.atl.SameGame.Model.Board;
import esi.g55019.atl.SameGame.Model.Model;
import esi.g55019.atl.SameGame.Model.Position;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class ModelTest {
    private Model model;

    @BeforeEach
    public void setModel(){
        this.model = new Model();
    }

    @Test
    public void create_board_wrong_row(){
        assertThrows(IllegalArgumentException.class, () -> model.createBoard(500,5,5));
    }

    @Test
    public void create_board_wrong_column(){
        assertThrows(IllegalArgumentException.class, () -> model.createBoard(5,500,5));
    }
    @Test
    public void create_board_wrong_level(){
        assertThrows(IllegalArgumentException.class, () -> model.createBoard(5,5,500));
    }

    @Test
    public void create_board(){
        this.model.createBoard(5,5,5);
    }

    @Test
    public void play_wrong_position(){
        this.model.createBoard(5,5,5);
        assertFalse(this.model.play(new Position(500,500)));
    }

    @Test
    public void play_good_position(){
        this.model.createBoard(5,5,5);
        assertTrue(this.model.play(new Position(0,0)));
    }

    @Test
    public void test_give_up(){
        this.model.createBoard(5,5,5);
        this.model.giveUp();
    }

    @Test
    public void is_not_finish(){
        this.model.createBoard(5,5,5);
        assertFalse(this.model.isFinish());
    }

    @Test
    public void is_finish(){
        Board board = new Board();
        board.giveUp();
        this.model.setBoard(board);
        assertTrue(this.model.isFinish());
    }
}
