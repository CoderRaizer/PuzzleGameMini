package gamePuzzle;

public class Puzzle {

    int [][] matrix;
    int [][] matrix_goal;
    int numberOfDirections;

    public Puzzle(){
        matrix = new int[3][3];
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                matrix[i][j] = -1;
            }
        }
    }

    // TODO : Setup for Matrix goal to check
    public void setUpForMatrixGoal(Puzzle goal){
        matrix_goal = new int[3][3];
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                matrix_goal[i][j] = goal.matrix[i][j];
            }
        }
    }

    public void showMatrix(){
        for(int i = 0 ; i < 3 ; i++){
            for(int j = 0 ; j < 3 ; j++){
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println("\n");
        }
    }

    public int totalDifferent(Puzzle candidate){
        int total = 0;
        for (int i = 0 ; i < 3 ; i++){
            for (int j = 0 ; j < 3 ; j++){
                if (candidate.matrix[i][j] != matrix_goal[i][j]){
                    total += 1;
                }
            }
        }
        return total;
    }

    public int minDifferentForTwo(int a, int b){
        if(a < b) return a;
        else return b;
    }

    public int minDifferentForThree(int a, int b , int c){
        int min = 9999;
        int [] arr = {a,b,c};
        for(int i = 0 ; i < 3 ; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }

    public int minDifferentForFour(int a, int b, int c, int d){
        int min = 9999;
        int [] arr = {a,b,c,d};
        for (int i = 0 ; i < 3 ; i++){
            if (arr[i] < min){
                min = arr[i];
            }
        }
        return min;
    }


    /* -----------  TODO :  Xem Current Point là tọa độ của nốt đen di chuyển ----------- */
    public void movingDirection(Puzzle now , Point currentPoint , Point previousPoint) {

        // TODO : This is the first state and don't have any move in previous
        if (previousPoint.getX() == -1 && previousPoint.getY() == -1) {

            /*----------------------- INFO : Ở 4 GÓC  -----------------------*/
            if (currentPoint.getX() == 0 && currentPoint.getY() == 0) {
                numberOfDirections = 2;
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveDown.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[0][0] = temp_moveDown.matrix[1][0];
                temp_moveDown.matrix[1][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[0][0] = temp_moveRight.matrix[0][1];
                temp_moveRight.matrix[0][1] = swap;


                // TODO : next'll consider which one should we choice and 'UPDATE now Puzzle'
                int differentForDown = totalDifferent(temp_moveDown);
                int differentForRight = totalDifferent(temp_moveRight);
                // TODO : Find min different
                if(differentForDown == minDifferentForTwo(differentForDown,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForRight == minDifferentForTwo(differentForDown,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 0 && currentPoint.getY() == 2) {
                numberOfDirections = 2;
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[0][2] = temp_moveDown.matrix[1][2];
                temp_moveDown.matrix[1][2] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[0][2] = temp_moveLeft.matrix[0][1];
                temp_moveLeft.matrix[0][1] = swap;

                // TODO : next'll consider which one should we choice
                int differentForDown = totalDifferent(temp_moveDown);
                int differentForLeft = totalDifferent(temp_moveLeft);
                // TODO : Find min different
                if(differentForDown == minDifferentForTwo(differentForDown,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForLeft == minDifferentForTwo(differentForDown,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 2 && currentPoint.getY() == 0) {
                numberOfDirections = 2;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[2][0] = temp_moveUp.matrix[1][0];
                temp_moveUp.matrix[1][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[2][0] = temp_moveRight.matrix[2][1];
                temp_moveRight.matrix[2][1] = swap;


                // TODO : next'll consider which one should we choice
                int differentForUp = totalDifferent(temp_moveUp);
                int differentForRight = totalDifferent(temp_moveRight);
                // TODO : Find min different
                if(differentForUp == minDifferentForTwo(differentForUp,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForRight == minDifferentForTwo(differentForUp,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 2 && currentPoint.getY() == 2) {
                numberOfDirections = 2;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveLeft.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[2][2] = temp_moveUp.matrix[1][2];
                temp_moveUp.matrix[1][2] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[2][2] = temp_moveLeft.matrix[2][1];
                temp_moveLeft.matrix[2][1] = swap;

                // TODO : next'll consider which one should we choice
                int differentForUp = totalDifferent(temp_moveUp);
                int differentForLeft = totalDifferent(temp_moveLeft);
                // TODO : Find min different
                if(differentForUp == minDifferentForTwo(differentForUp,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForLeft == minDifferentForTwo(differentForUp,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }
            /*----------------------- INFO : Ở 4 GÓC  -----------------------*/

            /*----------------------- INFO : Ở GIỮA CÁC CẠNH  -----------------------*/
            if (currentPoint.getX() == 0 && currentPoint.getY() == 1) {
                numberOfDirections = 3;
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[0][1] = temp_moveDown.matrix[1][1];
                temp_moveDown.matrix[1][1] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[0][1] = temp_moveLeft.matrix[0][0];
                temp_moveLeft.matrix[0][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[0][1] = temp_moveRight.matrix[0][2];
                temp_moveRight.matrix[0][2] = swap;

                // TODO : next'll consider which one should we choice
                int differentForDown = totalDifferent(temp_moveDown);
                int differentForLeft = totalDifferent(temp_moveLeft);
                int differentForRight = totalDifferent(temp_moveRight);


                // TODO : Find min different
                if(differentForDown == minDifferentForThree(differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForLeft == minDifferentForThree(differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForRight == minDifferentForThree(differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 1 && currentPoint.getY() == 2) {
                numberOfDirections = 3;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[1][2] = temp_moveUp.matrix[0][2];
                temp_moveUp.matrix[0][2] = swap;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[1][2] = temp_moveDown.matrix[2][2];
                temp_moveDown.matrix[2][2] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[1][2] = temp_moveLeft.matrix[1][1];
                temp_moveLeft.matrix[1][1] = swap;

                // TODO : next'll consider which one should we choice
                int differentForUp = totalDifferent(temp_moveUp);
                int differentForDown = totalDifferent(temp_moveDown);
                int differentForLeft = totalDifferent(temp_moveLeft);

                // TODO : Find min different
                if(differentForUp == minDifferentForThree(differentForUp,differentForDown,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForDown == minDifferentForThree(differentForUp,differentForDown,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForLeft == minDifferentForThree(differentForUp,differentForDown,differentForLeft)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 2 && currentPoint.getY() == 1) {
                numberOfDirections = 3;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveLeft.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[2][1] = temp_moveUp.matrix[1][1];
                temp_moveUp.matrix[1][1] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[2][1] = temp_moveLeft.matrix[2][0];
                temp_moveLeft.matrix[2][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[2][1] = temp_moveRight.matrix[2][2];
                temp_moveRight.matrix[2][2] = swap;

                // TODO : next'll consider which one should we choice
                int differentForUp = totalDifferent(temp_moveUp);
                int differentForLeft = totalDifferent(temp_moveLeft);
                int differentForRight = totalDifferent(temp_moveRight);

                // TODO : Find min different
                if(differentForUp == minDifferentForThree(differentForUp,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForLeft == minDifferentForThree(differentForUp,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForRight == minDifferentForThree(differentForUp,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 1 && currentPoint.getY() == 0) {
                numberOfDirections = 3;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveDown.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[1][0] = temp_moveUp.matrix[0][0];
                temp_moveUp.matrix[0][0] = swap;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[1][0] = temp_moveDown.matrix[2][0];
                temp_moveDown.matrix[2][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[1][0] = temp_moveRight.matrix[1][1];
                temp_moveRight.matrix[1][1] = swap;


                // TODO : next'll consider which one should we choice
                int differentForUp = totalDifferent(temp_moveUp);
                int differentForDown = totalDifferent(temp_moveDown);
                int differentForRight = totalDifferent(temp_moveRight);

                // TODO : Find min different
                if(differentForUp == minDifferentForThree(differentForUp,differentForDown,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForDown == minDifferentForThree(differentForUp,differentForDown,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForRight == minDifferentForThree(differentForUp,differentForDown,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }
            /*----------------------- INFO : Ở GIỮA CÁC CẠNH  -----------------------*/

            /*-----------------------  INFO :Ở TRUNG TÂM  ----------------------*/
            if (currentPoint.getX() == 1 && currentPoint.getY() == 1) {
                numberOfDirections = 4;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[1][1] = temp_moveUp.matrix[0][1];
                temp_moveUp.matrix[0][1] = swap;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[1][1] = temp_moveDown.matrix[2][1];
                temp_moveDown.matrix[2][1] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[1][1] = temp_moveLeft.matrix[1][0];
                temp_moveLeft.matrix[1][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[1][1] = temp_moveRight.matrix[1][2];
                temp_moveRight.matrix[1][2] = swap;

                // TODO : next'll consider which one should we choice
                int differentForUp = totalDifferent(temp_moveUp);
                int differentForDown = totalDifferent(temp_moveDown);
                int differentForLeft = totalDifferent(temp_moveLeft);
                int differentForRight = totalDifferent(temp_moveRight);

                // TODO : Find min different
                if(differentForUp == minDifferentForFour(differentForUp,differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForDown == minDifferentForFour(differentForUp,differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForLeft == minDifferentForFour(differentForUp,differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (differentForRight == minDifferentForFour(differentForUp,differentForDown,differentForLeft,differentForRight)){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }
            /*-----------------------  INFO :Ở TRUNG TÂM ----------------------*/
        }




        /* --------------------  TODO : MATRIX HAS MOVED LEAST ONCE  ------------------- */
        else {
            /*----------------------- INFO : Ở 4 GÓC  -----------------------*/
            if (currentPoint.getX() == 0 && currentPoint.getY() == 0) {
                numberOfDirections = 2;
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveDown.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[0][0] = temp_moveDown.matrix[1][0];
                temp_moveDown.matrix[1][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[0][0] = temp_moveRight.matrix[0][1];
                temp_moveRight.matrix[0][1] = swap;


                /* --- Check return previous point --- */
                if(temp_moveDown.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (temp_moveRight.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 0 && currentPoint.getY() == 2) {
                numberOfDirections = 2;
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[0][2] = temp_moveDown.matrix[1][2];
                temp_moveDown.matrix[1][2] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[0][2] = temp_moveLeft.matrix[0][1];
                temp_moveLeft.matrix[0][1] = swap;

                /* --- Check return previous point --- */
                if(temp_moveDown.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (temp_moveLeft.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveDown.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 2 && currentPoint.getY() == 0) {
                numberOfDirections = 2;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[2][0] = temp_moveUp.matrix[1][0];
                temp_moveUp.matrix[1][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[2][0] = temp_moveRight.matrix[2][1];
                temp_moveRight.matrix[2][1] = swap;

                /* --- Check return previous point --- */
                if (temp_moveUp.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveRight.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (temp_moveRight.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }

            if (currentPoint.getX() == 2 && currentPoint.getY() == 2) {
                numberOfDirections = 2;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveLeft.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[2][2] = temp_moveUp.matrix[1][2];
                temp_moveUp.matrix[1][2] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[2][2] = temp_moveLeft.matrix[2][1];
                temp_moveLeft.matrix[2][1] = swap;

                /* --- Check return previous point --- */
                if(temp_moveUp.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
                if (temp_moveLeft.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            now.matrix[i][j] = temp_moveUp.matrix[i][j];
                        }
                    }
                    // TODO : Here is update current point and previousPoint
                    /* previousPoint */
                    previousPoint.setX(currentPoint.getX());
                    previousPoint.setY(currentPoint.getY());
                    /* Current Point */
                    for (int i = 0 ; i < 3 ; i++){
                        for (int j = 0 ; j < 3 ; j++){
                            if(now.matrix[i][j] == -1){
                                currentPoint.setX(i);
                                currentPoint.setY(j);
                                break;
                            }
                        }
                    }
                    return;
                }
            }
            /*----------------------- INFO : o 4 goc  -----------------------*/

            /*----------------------- INFO : o giua cac canh  -----------------------*/
            if (currentPoint.getX() == 0 && currentPoint.getY() == 1) {
                numberOfDirections = 3;
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();
                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[0][1] = temp_moveDown.matrix[1][1];
                temp_moveDown.matrix[1][1] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[0][1] = temp_moveLeft.matrix[0][0];
                temp_moveLeft.matrix[0][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[0][1] = temp_moveRight.matrix[0][2];
                temp_moveRight.matrix[0][2] = swap;

                /* --- Check return previous point --- */
                if (temp_moveDown.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForLeft == minDifferentForTwo(differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }if (differentForRight == minDifferentForTwo(differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
                if (temp_moveLeft.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForDown == minDifferentForTwo(differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForRight == minDifferentForTwo(differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
                if (temp_moveRight.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    if(differentForDown == minDifferentForTwo(differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForLeft == minDifferentForTwo(differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }

            }

            if (currentPoint.getX() == 1 && currentPoint.getY() == 2) {
                numberOfDirections = 3;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[1][2] = temp_moveUp.matrix[0][2];
                temp_moveUp.matrix[0][2] = swap;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[1][2] = temp_moveDown.matrix[2][2];
                temp_moveDown.matrix[2][2] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[1][2] = temp_moveLeft.matrix[1][1];
                temp_moveLeft.matrix[1][1] = swap;

                /* --- Check return previous point --- */
                if (temp_moveUp.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    if(differentForDown == minDifferentForTwo(differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForLeft == minDifferentForTwo(differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
                if (temp_moveDown.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    if(differentForUp == minDifferentForTwo(differentForUp,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForLeft == minDifferentForTwo(differentForUp,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
                if (temp_moveLeft.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForDown = totalDifferent(temp_moveDown);
                    if(differentForUp == minDifferentForTwo(differentForUp,differentForDown)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForDown == minDifferentForTwo(differentForUp,differentForDown)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
            }

            if (currentPoint.getX() == 2 && currentPoint.getY() == 1) {
                numberOfDirections = 3;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveLeft.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[2][1] = temp_moveUp.matrix[1][1];
                temp_moveUp.matrix[1][1] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[2][1] = temp_moveLeft.matrix[2][0];
                temp_moveLeft.matrix[2][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[2][1] = temp_moveRight.matrix[2][2];
                temp_moveRight.matrix[2][2] = swap;


                /* --- Check return previous point --- */
                if (temp_moveUp.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForLeft == minDifferentForTwo(differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;

                    }
                    if (differentForRight == minDifferentForTwo(differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                }
                if (temp_moveLeft.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForUp == minDifferentForTwo(differentForUp,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForRight == minDifferentForTwo(differentForUp,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
                if (temp_moveRight.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    if(differentForUp == minDifferentForTwo(differentForUp,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForLeft == minDifferentForTwo(differentForUp,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
            }

            if (currentPoint.getX() == 1 && currentPoint.getY() == 0) {
                numberOfDirections = 3;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveDown.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[1][0] = temp_moveUp.matrix[0][0];
                temp_moveUp.matrix[0][0] = swap;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[1][0] = temp_moveDown.matrix[2][0];
                temp_moveDown.matrix[2][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[1][0] = temp_moveRight.matrix[1][1];
                temp_moveRight.matrix[1][1] = swap;

                /* --- Check return previous point --- */
                if (temp_moveUp.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForDown == minDifferentForTwo(differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForRight == minDifferentForTwo(differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
                if (temp_moveDown.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForUp == minDifferentForTwo(differentForUp,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForRight == minDifferentForTwo(differentForUp,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
                if (temp_moveRight.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForDown = totalDifferent(temp_moveDown);
                    if(differentForUp == minDifferentForTwo(differentForUp,differentForDown)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }
                    if (differentForDown == minDifferentForTwo(differentForUp,differentForDown)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
            }
            /*----------------------- INFO : o giua cac canh  -----------------------*/

            /*-----------------------  INFO : o trung tam  ----------------------*/
            if (currentPoint.getX() == 1 && currentPoint.getY() == 1) {
                numberOfDirections = 4;
                Puzzle temp_moveUp = new Puzzle();
                Puzzle temp_moveDown = new Puzzle();
                Puzzle temp_moveLeft = new Puzzle();
                Puzzle temp_moveRight = new Puzzle();

                // TODO : Copy Array
                for (int i = 0; i < 3; i++) {
                    for (int j = 0; j < 3; j++) {
                        temp_moveUp.matrix[i][j] = temp_moveDown.matrix[i][j] = temp_moveLeft.matrix[i][j] = temp_moveRight.matrix[i][j] = now.matrix[i][j];
                    }
                }
                int swap = 0;
                // TODO : move for up
                swap = temp_moveUp.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveUp.matrix[1][1] = temp_moveUp.matrix[0][1];
                temp_moveUp.matrix[0][1] = swap;
                // TODO : move for down
                swap = temp_moveDown.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveDown.matrix[1][1] = temp_moveDown.matrix[2][1];
                temp_moveDown.matrix[2][1] = swap;
                // TODO : move for left
                swap = temp_moveLeft.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveLeft.matrix[1][1] = temp_moveLeft.matrix[1][0];
                temp_moveLeft.matrix[1][0] = swap;
                // TODO : move for right
                swap = temp_moveRight.matrix[currentPoint.getX()][currentPoint.getY()];
                temp_moveRight.matrix[1][1] = temp_moveRight.matrix[1][2];
                temp_moveRight.matrix[1][2] = swap;

                /* --- Check return previous point --- */
                if (temp_moveUp.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForDown == minDifferentForThree(differentForDown,differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForLeft == minDifferentForThree(differentForDown,differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForRight == minDifferentForThree(differentForDown,differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
                else if (temp_moveDown.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForUp == minDifferentForThree(differentForUp,differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForLeft == minDifferentForThree(differentForUp,differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForRight == minDifferentForThree(differentForUp,differentForLeft,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
                else if (temp_moveLeft.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForRight = totalDifferent(temp_moveRight);
                    if(differentForUp == minDifferentForThree(differentForUp,differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForDown == minDifferentForThree(differentForUp,differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForRight == minDifferentForThree(differentForUp,differentForDown,differentForRight)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveRight.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
                else if (temp_moveRight.matrix[previousPoint.getX()][previousPoint.getY()] == -1){
                    // TODO : next'll consider which one should we choice
                    int differentForUp = totalDifferent(temp_moveUp);
                    int differentForDown = totalDifferent(temp_moveDown);
                    int differentForLeft = totalDifferent(temp_moveLeft);
                    if(differentForUp == minDifferentForThree(differentForUp,differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveUp.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForDown == minDifferentForThree(differentForUp,differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveDown.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    } else if (differentForLeft == minDifferentForThree(differentForUp,differentForDown,differentForLeft)){
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                now.matrix[i][j] = temp_moveLeft.matrix[i][j];
                            }
                        }
                        // TODO : Here is update current point and previousPoint
                        /* previousPoint */
                        previousPoint.setX(currentPoint.getX());
                        previousPoint.setY(currentPoint.getY());
                        /* Current Point */
                        for (int i = 0 ; i < 3 ; i++){
                            for (int j = 0 ; j < 3 ; j++){
                                if(now.matrix[i][j] == -1){
                                    currentPoint.setX(i);
                                    currentPoint.setY(j);
                                    break;
                                }
                            }
                        }
                        return;
                    }

                }
            }
            /*-----------------------  INFO : o trung tam  ----------------------*/
        }


    } // TODO : END METHOD


}


// Info : this method only applied with array []
//    private static int[] copyFullArrayUsingClone(int[] source) {
//        return source.clone();
//    }
