/* Best.java */

package player;

/**
 *
 *
 *
 *
 */
public class Best {

    public Move bestMove;
    public double bestScore;

    public Best() {
        bestMove = null;
        bestScore = 0;
    }
    public Best(Move best, double score) {
        bestMove = best;
        bestScore = score;
    }

    public void setMove(Move best){
        bestMove = best;
    }
    
    public void setScore(double score) {
        bestScore = score;
    }
}
