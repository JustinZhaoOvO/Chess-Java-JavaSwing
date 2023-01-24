package Chess.ChessTimer;
//CreateTime: 2022-04-02 7:01 p.m.

public class ChessTimer {
    private long totalTime;
    private long startinstant = 0;
    private long pastTime = 0;
    public ChessTimer(long time){
        this.totalTime = time;
    }

    public void start(){
        if (startinstant != 0){
            pastTime += (System.currentTimeMillis() - this.startinstant);
        }
        this.startinstant = System.currentTimeMillis();
    }

    public void stop(){
        if (startinstant != 0) {
            pastTime += (System.currentTimeMillis() - this.startinstant);
            startinstant = 0;
        }
    }

    public void ChangeTime(int change){
        if ( change > 0){
            if (this.getRemainTime() + change < 5999000){
                this.totalTime += change;
            }else{
                this.totalTime += (5999000 - getRemainTime());
            }
        }else if ( change < 0 && this.getRemainTime() > 0){
            if (this.getRemainTime() + change  > 0){
                this.totalTime += change;
            }else{
                this.totalTime -= getRemainTime();
            }
        }
    }

    public long getRemainTime(){
        long result = totalTime - pastTime;
        if (startinstant != 0){
            result -= (System.currentTimeMillis() - startinstant);
        }
        return result;
    }
}
