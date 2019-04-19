package gamePuzzle;

public class Main {

    public static boolean isTheSameMatrix(Puzzle goal,Puzzle now){
        for(int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                if(now.matrix[i][j] != goal.matrix[i][j]){
                    return false;
                }
            }
        }
        return true;
    }

    public static void main(String [] arg){

        System.out.println(" <<<------------>>> Game Puzzle <<<------------>>> ");

        int [] startLine = {2,8,3,1,6,4,7,-1,5}; int x = 0;// point is (2,1)
        int [] endLine =   {1,2,3,8,-1,4,7,6,5}; int y = 0;
//        int [] startLine = {-1,1,2,8,4,3,7,6,5}; int x = 0;// point is (0,0)
//        int [] endLine =   {1,2,3,8,-1,4,7,6,5}; int ProcessImage = 0;
//        int [] startLine = {1,2,3,4,8,-1,7,6,5}; int x = 0;// point is (1,2)
//        int [] endLine =   {1,2,3,4,5,6,7,8,-1}; int ProcessImage = 0;




        // TODO : Create and setup matrix for start state
        Puzzle start = new Puzzle();
        for(int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                start.matrix[i][j] = startLine[x];x++;
            }
        }
        // TODO : Create and setup matrix for end state
        Puzzle end = new Puzzle();
        for(int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                end.matrix[i][j] = endLine[y];y++;
            }
        }
        System.out.println("--- START MATRIX ---");
        start.showMatrix();
        System.out.println("--------------------------------------");
        System.out.println("--- GOAD MATRIX ---");
        end.showMatrix();
        System.out.println("--------------------------------------");

        start.setUpForMatrixGoal(end);

        boolean hasResult = false;
        System.out.println(" <-<-<-< Process >->->-> ");
        System.out.println("--------------------------------------");

        Point previousPoint = new Point(-1,-1);// TODO : This is previousPoint of each move step , in start is (-1 ; -1) and it'll have another value right on first step move
        Point currentPoint = new Point(2,1);// TODO : This is point of black

        int countStep = 1;
        while(hasResult == false){
            start.movingDirection(start,currentPoint,previousPoint);
            System.out.println("< STEP :" + countStep);countStep++;
            start.showMatrix();
            System.out.print("\n\n");
            // TODO : After each step change puzzle , we'll check matrix
            hasResult = isTheSameMatrix(end , start);
        }
        System.out.println("<<< Solve Success >>>");

    }
}
