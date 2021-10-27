package com.anish.calabashbros;

import java.util.Random;
import java.awt.Color;


public class Matrix {
    private Calabash[][] matrix;
    private int row, col;
    
    public Matrix(int row, int col){
        this.row = row;
        this.col = col;
        matrix = new Calabash[row][col];
    }

    public void put(Color color, int rank, World world, int row, int col){
        matrix[row][col] = new Calabash(color, rank, world);
        world.put(matrix[row][col],8 + col, 8 + row);
    }

    public Calabash[] toArray(){
        Calabash[] result = new Calabash[row * col];
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int index = i * col +  j;
                result[index] = matrix[i][j];
            }
        }
        return result;
    }

    public int[] makeRandomArray(int start, int end){
        int lenth = end - start + 1;
        int[] result = new int[lenth];
        int[] temp = new int[lenth];
        for(int i = 0; i < lenth; i++){
            temp[i] = i + start;
        }
        int partition = lenth;
        Random rand = new Random();
        int index;
        for(int i = 0; i < lenth; i++){
            index = Math.abs(rand.nextInt()%partition);
            partition--;
            result[i] = temp[index];
            temp[index] = temp[partition];
        }
        return result;
    }

    public void initMatrix(World world){
        int[] r = makeRandomArray(0, 255);
        int[] g = makeRandomArray(0, 255);
        int[] b = makeRandomArray(0, 255);
        int[] rank = makeRandomArray(0, row*col);
        for(int i = 0; i < row; i++){
            for(int j = 0; j < col; j++){
                int index = i * j;
                matrix[i][j] = new Calabash(new Color(r[index],g[index],b[index]), rank[index], world);
            }
        }
    }
}
